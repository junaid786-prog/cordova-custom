package cordova.plugins.qcc.BTLibrary.services;

import android.bluetooth.BluetoothDevice;
import android.os.Handler;
import cordova.plugins.qcc.BTLibrary.models.gatt.GATTServices;
import java.io.File;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes2.dex */
public interface BluetoothService {

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface Messages {
        public static final int CONNECTION_STATE_HAS_CHANGED = 0;
        public static final int DEVICE_BOND_STATE_HAS_CHANGED = 1;
        public static final int GAIA_PACKET = 3;
        public static final int GAIA_READY = 4;
        public static final int GATT_MESSAGE = 6;
        public static final int GATT_READY = 5;
        public static final int GATT_SUPPORT = 2;
        public static final int UPGRADE_MESSAGE = 7;
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface State {
        public static final int CONNECTED = 2;
        public static final int CONNECTING = 1;
        public static final int DISCONNECTED = 0;
        public static final int DISCONNECTING = 3;
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface Transport {
        public static final int BLE = 0;
        public static final int BR_EDR = 1;
        public static final int UNKNOWN = -1;
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface UpgradeMessage {
        public static final int UPGRADE_ERROR = 3;
        public static final int UPGRADE_FINISHED = 0;
        public static final int UPGRADE_REQUEST_CONFIRMATION = 1;
        public static final int UPGRADE_STEP_HAS_CHANGED = 2;
        public static final int UPGRADE_UPLOAD_PROGRESS = 4;
    }

    void abortUpgrade();

    void addHandler(Handler handler);

    boolean connectToDevice(String str);

    void disconnectDevice();

    void enableDebugLogs(boolean z);

    void enableUpgrade(boolean z);

    int getBondState();

    int getConnectionState();

    BluetoothDevice getDevice();

    GATTServices getGattSupport();

    int getResumePoint();

    int getTransport();

    boolean isGaiaReady();

    boolean isGattReady();

    boolean isUpgrading();

    boolean reconnectToDevice();

    void removeHandler(Handler handler);

    boolean requestBatteryLevels();

    boolean requestBodySensorLocation();

    boolean requestHeartMeasurementNotifications(boolean z);

    boolean requestLinkLossAlertLevel();

    boolean requestTxPowerLevel();

    void sendConfirmation(int i, boolean z);

    boolean sendGAIAPacket(byte[] bArr);

    boolean sendHeartRateControlPoint(byte b);

    boolean sendImmediateAlertLevel(int i);

    boolean sendLinkLossAlertLevel(int i);

    void setStateToDisconnected();

    boolean startRssiUpdates(boolean z);

    void startUpgrade(File file);
}
