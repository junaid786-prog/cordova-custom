package cordova.plugins.qcc.GaiaLibrary.gaia;

import android.util.Log;
import cordova.plugins.qcc.BTLibrary.libraries.vmupgrade.UpgradeError;
import cordova.plugins.qcc.BTLibrary.libraries.vmupgrade.UpgradeManager;
import cordova.plugins.qcc.GaiaLibrary.gaia.packets.GaiaPacket;
import cordova.plugins.qcc.GaiaLibrary.gaia.packets.GaiaPacketBLE;
import java.io.File;

/* loaded from: classes2.dex */
public class UpgradeGaiaManager extends AGaiaManager implements UpgradeManager.UpgradeManagerListener {
    private final String TAG;
    private boolean mIsRWCPEnabled;
    private final GaiaManagerListener mListener;
    private int mPayloadSizeMax;
    private final UpgradeManager mUpgradeManager;

    /* loaded from: classes2.dex */
    public interface GaiaManagerListener {
        void askConfirmation(int i);

        void onRWCPEnabled(boolean z);

        void onRWCPNotSupported();

        void onResumePointChanged(int i);

        void onUpgradeError(UpgradeError upgradeError);

        void onUpgradeFinish();

        void onUploadProgress(double d);

        void onVMUpgradeDisconnected();

        boolean sendGAIAUpgradePacket(byte[] bArr, boolean z);
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.GaiaManager
    protected void onSendingFailed(GaiaPacket gaiaPacket) {
    }

    public UpgradeGaiaManager(GaiaManagerListener gaiaManagerListener, int i) {
        super(i);
        this.TAG = "UpgradeGaiaManager";
        this.mIsRWCPEnabled = false;
        this.mListener = gaiaManagerListener;
        this.mPayloadSizeMax = i == 1 ? 254 : 16;
        UpgradeManager upgradeManager = new UpgradeManager(this);
        this.mUpgradeManager = upgradeManager;
        upgradeManager.showDebugLogs(false);
    }

    public void enableDebugLogs(boolean z) {
        showDebugLogs(z);
        this.mUpgradeManager.showDebugLogs(z);
    }

    public void startUpgrade(File file) {
        if (this.mUpgradeManager.isUpgrading()) {
            return;
        }
        registerNotification(18);
        this.mUpgradeManager.setFile(file);
        sendUpgradeConnect();
    }

    public void abortUpgrade() {
        this.mUpgradeManager.abortUpgrade();
    }

    public int getResumePoint() {
        return this.mUpgradeManager.getResumePoint();
    }

    public void sendConfirmation(int i, boolean z) {
        if (this.mUpgradeManager.isUpgrading()) {
            this.mUpgradeManager.sendConfirmation(i, z);
        }
    }

    public void setRWCPMode(boolean z) {
        this.mIsRWCPEnabled = z;
        sendSetDataEndPointMode(new byte[]{z ? (byte) 1 : (byte) 0});
    }

    public void getRWCPStatus() {
        sendGetDataEndPointMode();
    }

    public boolean isRWCPEnabled() {
        return this.mIsRWCPEnabled;
    }

    public void onRWCPNotSupported() {
        this.mIsRWCPEnabled = false;
    }

    public void onTransferFinished() {
        this.mUpgradeManager.onSuccessfulTransmission();
    }

    @Override // cordova.plugins.qcc.BTLibrary.libraries.vmupgrade.UpgradeManager.UpgradeManagerListener
    public void sendUpgradePacket(byte[] bArr, boolean z) {
        sendUpgradeControl(bArr, z);
    }

    @Override // cordova.plugins.qcc.BTLibrary.libraries.vmupgrade.UpgradeManager.UpgradeManagerListener
    public void onUpgradeProcessError(UpgradeError upgradeError) {
        this.mListener.onUpgradeError(upgradeError);
        int error = upgradeError.getError();
        if (error == 1 || error == 2 || error == 3 || error == 4) {
            this.mUpgradeManager.abortUpgrade();
        }
    }

    @Override // cordova.plugins.qcc.BTLibrary.libraries.vmupgrade.UpgradeManager.UpgradeManagerListener
    public void onResumePointChanged(int i) {
        this.mListener.onResumePointChanged(i);
    }

    @Override // cordova.plugins.qcc.BTLibrary.libraries.vmupgrade.UpgradeManager.UpgradeManagerListener
    public void onUpgradeFinished() {
        this.mListener.onUpgradeFinish();
        disconnectUpgrade();
    }

    @Override // cordova.plugins.qcc.BTLibrary.libraries.vmupgrade.UpgradeManager.UpgradeManagerListener
    public void onFileUploadProgress(double d) {
        this.mListener.onUploadProgress(d);
    }

    @Override // cordova.plugins.qcc.BTLibrary.libraries.vmupgrade.UpgradeManager.UpgradeManagerListener
    public void askConfirmationFor(int i) {
        this.mListener.askConfirmation(i);
    }

    @Override // cordova.plugins.qcc.BTLibrary.libraries.vmupgrade.UpgradeManager.UpgradeManagerListener
    public void disconnectUpgrade() {
        cancelNotification(18);
        sendUpgradeDisconnect();
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.GaiaManager
    public void reset() {
        super.reset();
    }

    public boolean isUpgrading() {
        return this.mUpgradeManager.isUpgrading();
    }

    public void onGaiaReady() {
        if (this.mUpgradeManager.isUpgrading()) {
            if (this.mIsRWCPEnabled) {
                setRWCPMode(true);
            }
            registerNotification(18);
            sendUpgradeConnect();
        }
    }

    public void setPacketMaximumSize(int i) {
        this.mPayloadSizeMax = i - 4;
    }

    private void sendUpgradeConnect() {
        createRequest(createPacket(GAIA.COMMAND_VM_UPGRADE_CONNECT));
    }

    private void sendUpgradeDisconnect() {
        createRequest(createPacket(GAIA.COMMAND_VM_UPGRADE_DISCONNECT));
    }

    private void sendUpgradeControl(byte[] bArr, boolean z) {
        if (z && this.mIsRWCPEnabled) {
            GaiaPacket createPacket = createPacket(GAIA.COMMAND_VM_UPGRADE_CONTROL, bArr);
            try {
                if (this.mListener.sendGAIAUpgradePacket(createPacket.getBytes(), true)) {
                    return;
                }
                Log.w("UpgradeGaiaManager", "Fail to send GAIA packet for GAIA command: " + createPacket.getCommandId());
                onSendingFailed(createPacket);
                return;
            } catch (GaiaException e) {
                Log.w("UpgradeGaiaManager", "Exception when attempting to create GAIA packet: " + e.toString());
                return;
            }
        }
        createRequest(createPacket(GAIA.COMMAND_VM_UPGRADE_CONTROL, bArr));
    }

    private void sendSetDataEndPointMode(byte[] bArr) {
        createRequest(new GaiaPacketBLE(10, GAIA.COMMAND_SET_DATA_ENDPOINT_MODE, bArr));
    }

    private void sendGetDataEndPointMode() {
        createRequest(new GaiaPacketBLE(10, GAIA.COMMAND_GET_DATA_ENDPOINT_MODE));
    }

    private void registerNotification(int i) {
        try {
            createRequest(GaiaPacketBLE.buildGaiaNotificationPacket(10, GAIA.COMMAND_REGISTER_NOTIFICATION, i, null, getTransportType()));
        } catch (GaiaException e) {
            Log.e("UpgradeGaiaManager", e.getMessage());
        }
    }

    private void cancelNotification(int i) {
        try {
            createRequest(GaiaPacketBLE.buildGaiaNotificationPacket(10, 16386, i, null, getTransportType()));
        } catch (GaiaException e) {
            Log.e("UpgradeGaiaManager", e.getMessage());
        }
    }

    private boolean receiveEventNotification(GaiaPacket gaiaPacket) {
        byte[] payload = gaiaPacket.getPayload();
        if (payload.length > 0) {
            if (gaiaPacket.getEvent() != 18 || this.mUpgradeManager == null) {
                return false;
            }
            createAcknowledgmentRequest(gaiaPacket, 0, null);
            byte[] bArr = new byte[payload.length - 1];
            System.arraycopy(payload, 1, bArr, 0, payload.length - 1);
            this.mUpgradeManager.receiveVMUPacket(bArr);
            return true;
        }
        createAcknowledgmentRequest(gaiaPacket, 5, null);
        return true;
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.GaiaManager
    protected void receiveSuccessfulAcknowledgement(GaiaPacket gaiaPacket) {
        int command = gaiaPacket.getCommand();
        if (command == 558) {
            this.mListener.onRWCPEnabled(this.mIsRWCPEnabled);
            return;
        }
        if (command != 686) {
            switch (command) {
                case GAIA.COMMAND_VM_UPGRADE_CONNECT /* 1600 */:
                    if (this.mUpgradeManager.isUpgrading()) {
                        this.mUpgradeManager.resumeUpgrade();
                        return;
                    }
                    int i = this.mPayloadSizeMax;
                    if (this.mIsRWCPEnabled) {
                        i--;
                        if (i % 2 != 0) {
                            i--;
                        }
                    }
                    this.mUpgradeManager.startUpgrade(i, isRWCPEnabled());
                    return;
                case GAIA.COMMAND_VM_UPGRADE_DISCONNECT /* 1601 */:
                    this.mUpgradeManager.onUpgradeDisconnected();
                    this.mListener.onVMUpgradeDisconnected();
                    return;
                case GAIA.COMMAND_VM_UPGRADE_CONTROL /* 1602 */:
                    this.mUpgradeManager.onSuccessfulTransmission();
                    return;
                default:
                    return;
            }
        }
        boolean z = gaiaPacket.getPayload()[1] == 1;
        this.mIsRWCPEnabled = z;
        this.mListener.onRWCPEnabled(z);
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.GaiaManager
    protected void receiveUnsuccessfulAcknowledgement(GaiaPacket gaiaPacket) {
        if (gaiaPacket.getCommand() == 1600 || gaiaPacket.getCommand() == 1602) {
            sendUpgradeDisconnect();
            return;
        }
        if (gaiaPacket.getCommand() == 1601) {
            this.mListener.onVMUpgradeDisconnected();
        } else if (gaiaPacket.getCommand() == 558 || gaiaPacket.getCommand() == 686) {
            this.mIsRWCPEnabled = false;
            this.mListener.onRWCPNotSupported();
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.GaiaManager
    protected void hasNotReceivedAcknowledgementPacket(GaiaPacket gaiaPacket) {
        if (gaiaPacket.getCommand() == 557) {
            this.mListener.onVMUpgradeDisconnected();
        } else if (gaiaPacket.getCommand() == 558 || gaiaPacket.getCommand() == 686) {
            this.mListener.onRWCPNotSupported();
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.GaiaManager
    protected boolean manageReceivedPacket(GaiaPacket gaiaPacket) {
        if (gaiaPacket.getCommand() != 16387) {
            return false;
        }
        return receiveEventNotification(gaiaPacket);
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.GaiaManager
    protected boolean sendGAIAPacket(byte[] bArr) {
        return this.mListener.sendGAIAUpgradePacket(bArr, false);
    }

    /* loaded from: classes2.dex */
    private static final class TransferModes {
        private static final byte MODE_NONE = 0;
        private static final byte MODE_RWCP = 1;

        private TransferModes() {
        }
    }
}
