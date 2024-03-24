package cordova.plugins.qcc.BTLibrary.libraries.vmupgrade;

import android.os.Handler;
import android.util.Log;
import cordova.plugins.qcc.BTLibrary.libraries.vmupgrade.codes.OpCodes;
import cordova.plugins.qcc.BTLibrary.libraries.vmupgrade.codes.ResumePoints;
import cordova.plugins.qcc.BTLibrary.libraries.vmupgrade.codes.ReturnCodes;
import cordova.plugins.qcc.BTLibrary.libraries.vmupgrade.packet.VMUException;
import cordova.plugins.qcc.BTLibrary.libraries.vmupgrade.packet.VMUPacket;
import java.io.File;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes2.dex */
public class UpgradeManager {
    private final String TAG;
    private boolean hasToAbort;
    private boolean hasToRestartUpgrade;
    private boolean isUpgrading;
    private byte[] mBytesFile;
    private int mBytesToSend;
    private File mFile;
    private final Handler mHandler;
    private final UpgradeManagerListener mListener;
    private int mMaxLengthForDataTransfer;
    private int mResumePoint;
    private boolean mSendMultiplePackets;
    private boolean mShowDebugLogs;
    private int mStartAttempts;
    private int mStartOffset;
    private boolean wasLastPacket;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface ConfirmationType {
        public static final int BATTERY_LOW_ON_DEVICE = 5;
        public static final int COMMIT = 2;
        public static final int IN_PROGRESS = 3;
        public static final int TRANSFER_COMPLETE = 1;
        public static final int WARNING_FILE_IS_DIFFERENT = 4;
    }

    /* loaded from: classes2.dex */
    public interface UpgradeManagerListener {
        void askConfirmationFor(int i);

        void disconnectUpgrade();

        void onFileUploadProgress(double d);

        void onResumePointChanged(int i);

        void onUpgradeFinished();

        void onUpgradeProcessError(UpgradeError upgradeError);

        void sendUpgradePacket(byte[] bArr, boolean z);
    }

    public UpgradeManager(UpgradeManagerListener upgradeManagerListener) {
        this.TAG = "UpgradeManager";
        this.isUpgrading = false;
        this.mStartAttempts = 0;
        this.mStartOffset = 0;
        this.mMaxLengthForDataTransfer = 8;
        this.wasLastPacket = false;
        this.hasToAbort = false;
        this.mHandler = new Handler();
        this.mBytesToSend = 0;
        this.mShowDebugLogs = false;
        this.hasToRestartUpgrade = false;
        this.mSendMultiplePackets = false;
        this.mListener = upgradeManagerListener;
    }

    public UpgradeManager(UpgradeManagerListener upgradeManagerListener, int i) {
        this.TAG = "UpgradeManager";
        this.isUpgrading = false;
        this.mStartAttempts = 0;
        this.mStartOffset = 0;
        this.mMaxLengthForDataTransfer = 8;
        this.wasLastPacket = false;
        this.hasToAbort = false;
        this.mHandler = new Handler();
        this.mBytesToSend = 0;
        this.mShowDebugLogs = false;
        this.hasToRestartUpgrade = false;
        this.mSendMultiplePackets = false;
        this.mListener = upgradeManagerListener;
        this.mMaxLengthForDataTransfer = i - 3;
    }

    public void setFile(File file) {
        this.mFile = file;
    }

    public void showDebugLogs(boolean z) {
        this.mShowDebugLogs = z;
        Log.i("UpgradeManager", "Debug logs are now " + (z ? "activated" : "deactivated") + ".");
    }

    public void startUpgrade() {
        Log.w("UpgradeManager", "startUpgrade() is deprecated, please use startUpgrade(int maxLength, boolean sendMultiplePackets) instead.");
    }

    public void startUpgrade(int i, boolean z) {
        if (i < 8) {
            Log.w("UpgradeManager", "setPacketMaxLengthForDataTransfer: given length is too short, minimum value is setup: 8");
            this.mMaxLengthForDataTransfer = 8;
        } else {
            this.mMaxLengthForDataTransfer = i - 3;
        }
        this.mSendMultiplePackets = z;
        File file = this.mFile;
        if (file == null) {
            this.mListener.onUpgradeProcessError(new UpgradeError(6));
            return;
        }
        try {
            this.mBytesFile = VMUUtils.getBytesFromFile(file);
            startUpgradeProcess();
        } catch (VMUException e) {
            UpgradeError upgradeError = new UpgradeError(e);
            Log.e("UpgradeManager", "Error occurs when attempt to start the process: " + upgradeError.getString());
            this.mListener.onUpgradeProcessError(upgradeError);
        }
    }

    public boolean resumeUpgrade() {
        if (this.isUpgrading) {
            resetUpload();
            sendSyncReq();
        }
        return this.isUpgrading;
    }

    public boolean isUpgrading() {
        return this.isUpgrading;
    }

    public void receiveVMUPacket(byte[] bArr) {
        try {
            VMUPacket vMUPacket = new VMUPacket(bArr);
            if (!this.isUpgrading && vMUPacket.getOpCode() != 8) {
                Log.w("UpgradeManager", "Received VMU packet while application is not upgrading anymore, opcode received: " + OpCodes.getString(vMUPacket.getOpCode()));
                return;
            }
            if (this.mShowDebugLogs) {
                Log.d("UpgradeManager", "Received " + OpCodes.getString(vMUPacket.getOpCode()) + ": " + VMUUtils.getHexadecimalStringFromBytes(vMUPacket.getData()));
            }
            handleVMUPacket(vMUPacket);
        } catch (VMUException e) {
            startAbortion(new UpgradeError(e));
        }
    }

    public void receiveVMControlSucceed() {
        Log.w("UpgradeManager", "method receiveVMControlSucceed is deprecated, please use onSuccessfulTransmission() instead.");
    }

    public void onSuccessfulTransmission() {
        if (this.wasLastPacket) {
            if (this.mResumePoint == 0) {
                this.wasLastPacket = false;
                setResumePoint(1);
                sendValidationDoneReq();
                return;
            }
            return;
        }
        if (this.hasToAbort) {
            this.hasToAbort = false;
            abortUpgrade();
        } else {
            if (this.mBytesToSend <= 0 || this.mResumePoint != 0 || this.mSendMultiplePackets) {
                return;
            }
            sendNextDataPacket();
        }
    }

    public void abortUpgrade() {
        if (this.isUpgrading) {
            sendAbortReq();
            this.isUpgrading = false;
        }
    }

    public void sendConfirmation(int i, boolean z) {
        if (i == 1) {
            sendTransferCompleteReq(z);
            if (z) {
                return;
            }
            this.hasToAbort = true;
            return;
        }
        if (i == 2) {
            sendCommitCFM(z);
            if (z) {
                return;
            }
            this.hasToAbort = true;
            return;
        }
        if (i == 3) {
            sendInProgressRes(z);
            if (z) {
                return;
            }
            abortUpgrade();
            return;
        }
        if (i == 4) {
            this.hasToRestartUpgrade = z;
            sendAbortReq();
        } else {
            if (i != 5) {
                return;
            }
            if (z) {
                sendSyncReq();
            } else {
                abortUpgrade();
            }
        }
    }

    public int getResumePoint() {
        return this.mResumePoint;
    }

    public void receiveVMDisconnectSucceed() {
        Log.w("UpgradeManager", "method receiveVMDisconnectSucceed() is deprecated, please use onUpgradeDisconnected() instead.");
    }

    public void onUpgradeDisconnected() {
        if (this.hasToRestartUpgrade) {
            this.hasToRestartUpgrade = false;
            startUpgradeProcess();
        }
    }

    private void startUpgradeProcess() {
        boolean z = this.isUpgrading;
        if (!z && this.mBytesFile != null) {
            this.isUpgrading = true;
            resetUpload();
            sendSyncReq();
        } else if (z) {
            this.mListener.onUpgradeProcessError(new UpgradeError(5));
        } else {
            this.mListener.onUpgradeProcessError(new UpgradeError(6));
        }
    }

    private void sendVMUPacket(VMUPacket vMUPacket, boolean z) {
        byte[] bytes = vMUPacket.getBytes();
        if (this.isUpgrading) {
            if (this.mShowDebugLogs) {
                Log.d("UpgradeManager", "send " + OpCodes.getString(vMUPacket.getOpCode()) + ": " + VMUUtils.getHexadecimalStringFromBytes(bytes));
            }
            this.mListener.sendUpgradePacket(bytes, z);
            return;
        }
        Log.w("UpgradeManager", "Sending failed as application is no longer upgrading for opcode: " + OpCodes.getString(vMUPacket.getOpCode()));
    }

    private void startAbortion(UpgradeError upgradeError) {
        Log.e("UpgradeManager", "Error occurs during upgrade process: " + upgradeError.getString() + "\nStart abortion...");
        this.mListener.onUpgradeProcessError(upgradeError);
        abortUpgrade();
    }

    private void setResumePoint(int i) {
        this.mResumePoint = i;
        this.mListener.onResumePointChanged(i);
    }

    private void askForConfirmation(int i) {
        this.mListener.askConfirmationFor(i);
    }

    private void stopUpgrade() {
        this.isUpgrading = false;
        this.mListener.disconnectUpgrade();
    }

    private void resetUpload() {
        this.mStartAttempts = 0;
        this.mBytesToSend = 0;
        this.mStartOffset = 0;
    }

    private void onFileUploadProgress() {
        double d = 100.0d;
        double length = (this.mStartOffset * 100.0d) / this.mBytesFile.length;
        if (length < 0.0d) {
            d = 0.0d;
        } else if (length <= 100.0d) {
            d = length;
        }
        this.mListener.onFileUploadProgress(d);
    }

    private void sendNextDataPacket() {
        onFileUploadProgress();
        int i = this.mBytesToSend;
        int i2 = this.mMaxLengthForDataTransfer;
        if (i >= i2 - 1) {
            i = i2 - 1;
        }
        byte[] bArr = this.mBytesFile;
        int length = bArr.length;
        int i3 = this.mStartOffset;
        boolean z = length - i3 <= i;
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, i3, bArr2, 0, i);
        if (z) {
            this.wasLastPacket = true;
            this.mBytesToSend = 0;
        } else {
            this.mStartOffset += i;
            this.mBytesToSend -= i;
        }
        sendData(z, bArr2);
    }

    private void sendSyncReq() {
        byte[] mD5FromFile = VMUUtils.getMD5FromFile(this.mFile);
        byte[] bArr = new byte[4];
        if (mD5FromFile.length >= 4) {
            System.arraycopy(mD5FromFile, mD5FromFile.length - 4, bArr, 0, 4);
        } else if (mD5FromFile.length > 0) {
            System.arraycopy(mD5FromFile, 0, bArr, 0, mD5FromFile.length);
        }
        sendVMUPacket(new VMUPacket(19, bArr), false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendStartReq() {
        sendVMUPacket(new VMUPacket(1), false);
    }

    private void sendStartDataReq() {
        setResumePoint(0);
        sendVMUPacket(new VMUPacket(21), false);
    }

    private void sendData(boolean z, byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length + 1];
        bArr2[0] = z ? (byte) 1 : (byte) 0;
        System.arraycopy(bArr, 0, bArr2, 1, bArr.length);
        sendVMUPacket(new VMUPacket(4, bArr2), true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendValidationDoneReq() {
        sendVMUPacket(new VMUPacket(22), false);
    }

    private void sendTransferCompleteReq(boolean z) {
        sendVMUPacket(new VMUPacket(12, new byte[]{(byte) (!z ? 1 : 0)}), false);

    }

    private void sendInProgressRes(boolean z) {
        sendVMUPacket(new VMUPacket(14, new byte[]{(byte) (!z ? 1 : 0)}), false);
    }

    private void sendCommitCFM(boolean z) {
        sendVMUPacket(new VMUPacket(16, new byte[]{(byte) (!z ? 1 : 0)}), false);
    }

    private void sendAbortReq() {
        sendVMUPacket(new VMUPacket(7), false);
    }

    private void sendErrorConfirmation(byte[] bArr) {
        sendVMUPacket(new VMUPacket(31, bArr), false);
    }

    private void handleVMUPacket(VMUPacket vMUPacket) {
        int opCode = vMUPacket.getOpCode();
        if (opCode == 2) {
            receiveStartCFM(vMUPacket);
            return;
        }
        if (opCode == 3) {
            receiveDataBytesREQ(vMUPacket);
            return;
        }
        if (opCode == 8) {
            receiveAbortCFM();
            return;
        }
        if (opCode == 11) {
            receiveTransferCompleteIND();
            return;
        }
        if (opCode == 15) {
            receiveCommitREQ();
            return;
        }
        if (opCode == 20) {
            receiveSyncCFM(vMUPacket);
            return;
        }
        if (opCode == 23) {
            receiveValidationDoneCFM(vMUPacket);
        } else if (opCode == 17) {
            receiveErrorWarnIND(vMUPacket);
        } else {
            if (opCode != 18) {
                return;
            }
            receiveCompleteIND();
        }
    }

    private void receiveErrorWarnIND(VMUPacket vMUPacket) {
        byte[] data = vMUPacket.getData();
        sendErrorConfirmation(data);
        int returnCode = ReturnCodes.getReturnCode(VMUUtils.extractShortFromByteArray(data, 0, 2, false));
        if (returnCode == 129) {
            askForConfirmation(4);
        } else if (returnCode == 33) {
            askForConfirmation(5);
        } else {
            startAbortion(new UpgradeError(3, returnCode));
        }
    }

    private void receiveSyncCFM(VMUPacket vMUPacket) {
        byte[] data = vMUPacket.getData();
        if (data.length >= 6) {
            int resumePoint = ResumePoints.getResumePoint(data[0]);
            VMUUtils.extractIntFromByteArray(data, 1, 4, false);
            byte b = data[5];
            if (resumePoint == 3) {
                setResumePoint(resumePoint);
            } else {
                this.mResumePoint = resumePoint;
            }
        } else {
            this.mResumePoint = 0;
        }
        sendStartReq();
    }

    private void receiveStartCFM(VMUPacket vMUPacket) {
        byte[] data = vMUPacket.getData();
        if (data.length >= 3) {
            VMUUtils.extractShortFromByteArray(data, 1, 2, false);
            byte b = data[0];
            if (b != 0) {
                if (b == 9) {
                    int i = this.mStartAttempts;
                    if (i < 5) {
                        this.mStartAttempts = i + 1;
                        this.mHandler.postDelayed(new Runnable() { // from class: cordova.plugins.qcc.BTLibrary.libraries.vmupgrade.UpgradeManager.1
                            @Override // java.lang.Runnable
                            public void run() {
                                UpgradeManager.this.sendStartReq();
                            }
                        }, (long) 2000);
                        return;
                    } else {
                        this.mStartAttempts = 0;
                        startAbortion(new UpgradeError(1));
                        return;
                    }
                }
                startAbortion(new UpgradeError(2));
                return;
            }
            this.mStartAttempts = 0;
            int i2 = this.mResumePoint;
            if (i2 == 1) {
                sendValidationDoneReq();
                return;
            }
            if (i2 == 2) {
                askForConfirmation(1);
                return;
            }
            if (i2 == 3) {
                askForConfirmation(3);
                return;
            } else if (i2 == 4) {
                askForConfirmation(2);
                return;
            } else {
                sendStartDataReq();
                return;
            }
        }
        startAbortion(new UpgradeError(2));
    }

    private void receiveDataBytesREQ(VMUPacket vMUPacket) {
        byte[] data = vMUPacket.getData();
        if (data.length == 8) {
            this.mBytesToSend = VMUUtils.extractIntFromByteArray(data, 0, 4, false);
            int extractIntFromByteArray = VMUUtils.extractIntFromByteArray(data, 4, 4, false);
            int i = this.mStartOffset;
            if (extractIntFromByteArray <= 0 || extractIntFromByteArray + i >= this.mBytesFile.length) {
                extractIntFromByteArray = 0;
            }
            int i2 = i + extractIntFromByteArray;
            this.mStartOffset = i2;
            int i3 = this.mBytesToSend;
            int i4 = i3 > 0 ? i3 : 0;
            this.mBytesToSend = i4;
            int length = this.mBytesFile.length - i2;
            if (i4 >= length) {
                i4 = length;
            }
            this.mBytesToSend = i4;
            if (this.mSendMultiplePackets) {
                while (this.mBytesToSend > 0) {
                    sendNextDataPacket();
                }
                return;
            } else {
                sendNextDataPacket();
                return;
            }
        }
        startAbortion(new UpgradeError(2));
    }

    private void receiveValidationDoneCFM(VMUPacket vMUPacket) {
        byte[] bytes = vMUPacket.getBytes();
        if (bytes.length == 2) {
            this.mHandler.postDelayed(new Runnable() { // from class: cordova.plugins.qcc.BTLibrary.libraries.vmupgrade.UpgradeManager.2
                @Override // java.lang.Runnable
                public void run() {
                    UpgradeManager.this.sendValidationDoneReq();
                }
            }, VMUUtils.extractLongFromByteArray(bytes, 0, 2, false));
        } else {
            sendValidationDoneReq();
        }
    }

    private void receiveTransferCompleteIND() {
        setResumePoint(2);
        askForConfirmation(1);
    }

    private void receiveCommitREQ() {
        setResumePoint(4);
        askForConfirmation(2);
    }

    private void receiveCompleteIND() {
        this.isUpgrading = false;
        this.mListener.onUpgradeFinished();
    }

    private void receiveAbortCFM() {
        stopUpgrade();
    }
}
