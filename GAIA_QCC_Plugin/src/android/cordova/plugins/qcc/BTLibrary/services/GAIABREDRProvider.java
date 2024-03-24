package cordova.plugins.qcc.BTLibrary.services;

import android.bluetooth.BluetoothManager;
import android.os.Handler;
import android.util.Log;
import cordova.plugins.qcc.BTLibrary.Utils;
import cordova.plugins.qcc.BTLibrary.libraries.vmupgrade.UpgradeError;
import cordova.plugins.qcc.GaiaLibrary.gaia.UpgradeGaiaManager;
import java.io.File;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class GAIABREDRProvider extends BREDRProvider implements UpgradeGaiaManager.GaiaManagerListener {
    private final String TAG;
    private final DataAnalyser mAnalyser;
    private final Handler mHandler;
    private final Handler mListener;
    private boolean mShowDebugLogs;
    private UpgradeGaiaManager mUpgradeGaiaManager;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    @interface Messages {
        public static final int CONNECTION_STATE_HAS_CHANGED = 0;
        public static final int ERROR = 2;
        public static final int GAIA_PACKET = 1;
        public static final int GAIA_READY = 3;
        public static final int UPGRADE_MESSAGE = 4;
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.UpgradeGaiaManager.GaiaManagerListener
    public void onRWCPEnabled(boolean z) {
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.UpgradeGaiaManager.GaiaManagerListener
    public void onRWCPNotSupported() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GAIABREDRProvider(Handler handler, BluetoothManager bluetoothManager) {
        super(bluetoothManager);
        this.TAG = "GAIABREDRProvider";
        this.mShowDebugLogs = false;
        this.mHandler = new Handler();
        this.mAnalyser = new DataAnalyser();
        this.mListener = handler;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void startUpgrade(File file) {
        UpgradeGaiaManager upgradeGaiaManager = this.mUpgradeGaiaManager;
        if (upgradeGaiaManager != null) {
            upgradeGaiaManager.startUpgrade(file);
        } else {
            Log.e("GAIABREDRProvider", "Upgrade has not been enabled.");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getResumePoint() {
        UpgradeGaiaManager upgradeGaiaManager = this.mUpgradeGaiaManager;
        if (upgradeGaiaManager != null) {
            return upgradeGaiaManager.getResumePoint();
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void abortUpgrade() {
        UpgradeGaiaManager upgradeGaiaManager;
        if (getState() != 2 || (upgradeGaiaManager = this.mUpgradeGaiaManager) == null) {
            return;
        }
        upgradeGaiaManager.abortUpgrade();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isUpgrading() {
        UpgradeGaiaManager upgradeGaiaManager = this.mUpgradeGaiaManager;
        return upgradeGaiaManager != null && upgradeGaiaManager.isUpgrading();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void sendConfirmation(int i, boolean z) {
        UpgradeGaiaManager upgradeGaiaManager = this.mUpgradeGaiaManager;
        if (upgradeGaiaManager != null) {
            upgradeGaiaManager.sendConfirmation(i, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void enableUpgrade(boolean z) {
        if (z && this.mUpgradeGaiaManager == null) {
            this.mUpgradeGaiaManager = new UpgradeGaiaManager(this, 1);
        } else {
            if (z) {
                return;
            }
            this.mUpgradeGaiaManager = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void enableDebugLogs(boolean z) {
        showDebugLogs(z);
        this.mUpgradeGaiaManager.enableDebugLogs(z);
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.UpgradeGaiaManager.GaiaManagerListener
    public void onVMUpgradeDisconnected() {
        if (isUpgrading()) {
            return;
        }
        this.mUpgradeGaiaManager.reset();
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.UpgradeGaiaManager.GaiaManagerListener
    public void onResumePointChanged(int i) {
        sendMessageToListener(4, 2, Integer.valueOf(i));
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.UpgradeGaiaManager.GaiaManagerListener
    public void onUpgradeError(UpgradeError upgradeError) {
        Log.e("GAIABREDRProvider", "ERROR during upgrade: " + upgradeError.getString());
        sendMessageToListener(4, 3, upgradeError);
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.UpgradeGaiaManager.GaiaManagerListener
    public void onUploadProgress(double d) {
        sendMessageToListener(4, 4, Double.valueOf(d));
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.UpgradeGaiaManager.GaiaManagerListener
    public boolean sendGAIAUpgradePacket(byte[] bArr, boolean z) {
        return sendData(bArr);
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.UpgradeGaiaManager.GaiaManagerListener
    public void onUpgradeFinish() {
        sendMessageToListener(4, 0, null);
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.UpgradeGaiaManager.GaiaManagerListener
    public void askConfirmation(int i) {
        if (this.mListener != null) {
            sendMessageToListener(4, 1, Integer.valueOf(i));
        } else {
            sendConfirmation(i, true);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // cordova.plugins.qcc.BTLibrary.services.BREDRProvider
    public void showDebugLogs(boolean z) {
        this.mShowDebugLogs = z;
        Log.i("GAIABREDRProvider", "Debug logs are now " + (z ? "activated" : "deactivated") + ".");
        super.showDebugLogs(z);
    }

    @Override // cordova.plugins.qcc.BTLibrary.services.BREDRProvider
    void onConnectionStateChanged(int i) {
        sendMessageToListener(0, Integer.valueOf(i));
        if (i == 0 || i == 3) {
            this.mAnalyser.reset();
            UpgradeGaiaManager upgradeGaiaManager = this.mUpgradeGaiaManager;
            if (upgradeGaiaManager != null) {
                upgradeGaiaManager.reset();
            }
        }
    }

    @Override // cordova.plugins.qcc.BTLibrary.services.BREDRProvider
    void onConnectionError(int i) {
        sendMessageToListener(2, Integer.valueOf(i));
    }

    @Override // cordova.plugins.qcc.BTLibrary.services.BREDRProvider
    void onCommunicationRunning() {
        sendMessageToListener(3);
        if (isUpgrading()) {
            this.mHandler.post(new Runnable() { // from class: cordova.plugins.qcc.BTLibrary.services.GAIABREDRProvider.1
                @Override // java.lang.Runnable
                public void run() {
                    GAIABREDRProvider.this.mUpgradeGaiaManager.onGaiaReady();
                }
            });
        }
    }

    @Override // cordova.plugins.qcc.BTLibrary.services.BREDRProvider
    void onDataFound(byte[] bArr) {
        this.mAnalyser.analyse(bArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onGAIAPacketFound(byte[] bArr) {
        if (this.mShowDebugLogs) {
            Log.d("GAIABREDRProvider", "Receive potential GAIA packet: " + Utils.getStringFromBytes(bArr));
        }
        UpgradeGaiaManager upgradeGaiaManager = this.mUpgradeGaiaManager;
        if (upgradeGaiaManager != null) {
            upgradeGaiaManager.onReceiveGAIAPacket(bArr);
        } else {
            sendMessageToListener(1, bArr);
        }
    }

    private void sendMessageToListener(int i) {
        Handler handler = this.mListener;
        if (handler != null) {
            handler.obtainMessage(i).sendToTarget();
        }
    }

    private void sendMessageToListener(int i, Object obj) {
        Handler handler = this.mListener;
        if (handler != null) {
            handler.obtainMessage(i, obj).sendToTarget();
        }
    }

    private void sendMessageToListener(int i, int i2, Object obj) {
        Handler handler = this.mListener;
        if (handler != null) {
            handler.obtainMessage(i, i2, 0, obj).sendToTarget();
        }
    }

    /* loaded from: classes2.dex */
    private class DataAnalyser {
        final byte[] mmData;
        int mmExpectedLength;
        int mmFlags;
        int mmReceivedLength;

        private DataAnalyser() {
            this.mmData = new byte[263];
            this.mmReceivedLength = 0;
            this.mmExpectedLength = 263;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void reset() {
            this.mmReceivedLength = 0;
            this.mmExpectedLength = 263;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void analyse(byte[] bArr) {
            int length = bArr.length;
            for (int i = 0; i < length; i++) {
                int i2 = this.mmReceivedLength;
                if (i2 > 0 && i2 < 263) {
                    byte[] bArr2 = this.mmData;
                    bArr2[i2] = bArr[i];
                    if (i2 == 2) {
                        this.mmFlags = bArr[i];
                    } else if (i2 == 3) {
                        this.mmExpectedLength = bArr[i] + 8 + ((this.mmFlags & 1) == 0 ? 0 : 1);
                    }
                    int i3 = i2 + 1;
                    this.mmReceivedLength = i3;
                    if (i3 == this.mmExpectedLength) {
                        byte[] bArr3 = new byte[i3];
                        System.arraycopy(bArr2, 0, bArr3, 0, i3);
                        reset();
                        GAIABREDRProvider.this.onGAIAPacketFound(bArr3);
                    }
                } else if (bArr[i] == -1) {
                    this.mmReceivedLength = 1;
                } else if (i2 >= 263) {
                    Log.w("GAIABREDRProvider", "Packet is too long: received length is bigger than the maximum length of a GAIA packet. Resetting analyser.");
                    reset();
                }
            }
        }
    }
}
