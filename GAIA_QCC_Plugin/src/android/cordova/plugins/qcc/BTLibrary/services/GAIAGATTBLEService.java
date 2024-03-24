package cordova.plugins.qcc.BTLibrary.services;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import cordova.plugins.qcc.BTLibrary.Utils;
import cordova.plugins.qcc.BTLibrary.libraries.ble.BLEService;
import cordova.plugins.qcc.BTLibrary.libraries.ble.BLEUtils;
import cordova.plugins.qcc.BTLibrary.libraries.vmupgrade.UpgradeError;
import cordova.plugins.qcc.BTLibrary.models.gatt.GATT;
import cordova.plugins.qcc.BTLibrary.models.gatt.GATTServices;
import cordova.plugins.qcc.BTLibrary.models.gatt.GattServiceBattery;
import cordova.plugins.qcc.BTLibrary.receivers.BondStateReceiver;
import cordova.plugins.qcc.BTLibrary.rwcp.RWCPClient;
import cordova.plugins.qcc.GaiaLibrary.gaia.UpgradeGaiaManager;
import java.io.File;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.UUID;

/* loaded from: classes2.dex */
public class GAIAGATTBLEService extends BLEService implements BondStateReceiver.BondStateListener, UpgradeGaiaManager.GaiaManagerListener, BluetoothService, RWCPClient.RWCPListener {
    private static final int RSSI_WAITING_TIME = 1000;
    private UpgradeGaiaManager mUpgradeGaiaManager;
    private boolean mShowDebugLogs = false;
    private final String TAG = "GAIAGATTBLEService";
    private final List<Handler> mAppListeners = new ArrayList();
    private final IBinder mBinder = new LocalBinder();
    private final ArrayList<UUID> mNotifiedCharacteristics = new ArrayList<>();
    private final BondStateReceiver mBondStateReceiver = new BondStateReceiver(this);
    private boolean mIsGattReady = false;
    private boolean mIsGaiaReady = false;
    private boolean mIsRWCPSupported = true;
    private final GATTServices mGattServices = new GATTServices();
    private boolean mUpdateRssi = false;
    private final Handler mHandler = new Handler();
    private final RWCPClient mRWCPClient = new RWCPClient(this);
    private final Queue<Double> mProgressQueue = new LinkedList();
    private long mTransferStartTime = 0;
    private boolean mBondingInitiated = false;
    private int mAttemptsForPairingInduction = 0;
    private final Runnable mRssiRunnable = new Runnable() { // from class: cordova.plugins.qcc.BTLibrary.services.GAIAGATTBLEService.1
        @Override // java.lang.Runnable
        public void run() {
            if (GAIAGATTBLEService.this.mUpdateRssi) {
                GAIAGATTBLEService gAIAGATTBLEService = GAIAGATTBLEService.this;
                gAIAGATTBLEService.mUpdateRssi = gAIAGATTBLEService.requestReadRssi();
            }
        }
    };

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface GattMessage {
        public static final int BATTERY_LEVEL_UPDATE = 3;
        public static final int BODY_SENSOR_LOCATION = 5;
        public static final int GATT_STATE = 11;
        public static final int HEART_RATE_MEASUREMENT = 4;
        public static final int LINK_LOSS_ALERT_LEVEL = 1;
        public static final int MTU_SUPPORTED = 9;
        public static final int MTU_UPDATED = 10;
        public static final int RSSI_LEVEL = 2;
        public static final int RWCP_ENABLED = 7;
        public static final int RWCP_SUPPORTED = 6;
        public static final int TRANSFER_FAILED = 8;
        public static final int TX_POWER_LEVEL = 0;
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface GattState {
        public static final int DISCOVERING_SERVICES = 0;
        public static final int IN_USE_BY_SYSTEM = 1;
    }

    @Override // cordova.plugins.qcc.BTLibrary.services.BluetoothService
    public int getTransport() {
        return 0;
    }

    @Override // cordova.plugins.qcc.BTLibrary.libraries.ble.BLEService
    protected void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
    }

    @Override // cordova.plugins.qcc.BTLibrary.services.BluetoothService
    public boolean startRssiUpdates(boolean z) {
        return false;
    }

    @Override // cordova.plugins.qcc.BTLibrary.services.BluetoothService
    public synchronized void addHandler(Handler handler) {
        if (!this.mAppListeners.contains(handler)) {
            this.mAppListeners.add(handler);
        }
    }

    @Override // cordova.plugins.qcc.BTLibrary.services.BluetoothService
    public synchronized void removeHandler(Handler handler) {
        if (this.mAppListeners.contains(handler)) {
            this.mAppListeners.remove(handler);
        }
    }

    @Override // cordova.plugins.qcc.BTLibrary.services.BluetoothService
    public void disconnectDevice() {
        if (isDisconnected()) {
            resetDeviceInformation();
        } else {
            unregisterNotifications();
            disconnectFromDevice();
        }
    }

    @Override // cordova.plugins.qcc.BTLibrary.services.BluetoothService
    public int getBondState() {
        BluetoothDevice device = getDevice();
        if (device != null) {
            return device.getBondState();
        }
        return 10;
    }

    @Override // cordova.plugins.qcc.BTLibrary.services.BluetoothService
    public void enableDebugLogs(boolean z) {
        showDebugLogs(z);
        this.mShowDebugLogs = z;
        this.mRWCPClient.showDebugLogs(z);
        UpgradeGaiaManager upgradeGaiaManager = this.mUpgradeGaiaManager;
        if (upgradeGaiaManager != null) {
            upgradeGaiaManager.enableDebugLogs(z);
        }
    }

    @Override // cordova.plugins.qcc.BTLibrary.services.BluetoothService
    public GATTServices getGattSupport() {
        return this.mGattServices;
    }

    @Override // cordova.plugins.qcc.BTLibrary.services.BluetoothService
    public boolean sendGAIAPacket(byte[] bArr) {
        return sendGaiaCommandEndpoint(bArr);
    }

    @Override // cordova.plugins.qcc.BTLibrary.services.BluetoothService
    public boolean isGaiaReady() {
        return this.mIsGaiaReady;
    }

    @Override // cordova.plugins.qcc.BTLibrary.services.BluetoothService
    public boolean isGattReady() {
        return this.mIsGattReady;
    }

    public boolean setMtuSize(int i) {
        return requestMtuSize(i);
    }

    public boolean enableRWCP(boolean z) {
        if (!this.mIsRWCPSupported && z) {
            Log.w("GAIAGATTBLEService", "Request to enable or disable RWCP received but the feature is not supported.");
            return false;
        }
        this.mUpgradeGaiaManager.setRWCPMode(z);
        return true;
    }

    public void getRWCPStatus() {
        if (this.mIsRWCPSupported) {
            this.mUpgradeGaiaManager.getRWCPStatus();
        } else {
            Log.w("GAIAGATTBLEService", "getRWCPStatus(): RWCP is not supported, cannot get its status.");
        }
    }

    public int getRWCPInitialWindow() {
        return this.mRWCPClient.getInitialWindowSize();
    }

    public boolean setRWCPInitialWindow(int i) {
        return this.mRWCPClient.setInitialWindowSize(i);
    }

    public int getRWCPMaximumWindow() {
        return this.mRWCPClient.getMaximumWindowSize();
    }

    public boolean setRWCPMaximumWindow(int i) {
        return this.mRWCPClient.setMaximumWindowSize(i);
    }

    @Override // cordova.plugins.qcc.BTLibrary.services.BluetoothService
    public void startUpgrade(File file) {
        if (this.mUpgradeGaiaManager != null) {
            super.getBluetoothGatt().requestConnectionPriority(1);
            this.mUpgradeGaiaManager.startUpgrade(file);
            this.mProgressQueue.clear();
            this.mTransferStartTime = 0L;
            return;
        }
        Log.e("GAIAGATTBLEService", "Upgrade has not been enabled.");
    }

    @Override // cordova.plugins.qcc.BTLibrary.services.BluetoothService
    public int getResumePoint() {
        UpgradeGaiaManager upgradeGaiaManager = this.mUpgradeGaiaManager;
        if (upgradeGaiaManager != null) {
            return upgradeGaiaManager.getResumePoint();
        }
        return 0;
    }

    @Override // cordova.plugins.qcc.BTLibrary.services.BluetoothService
    public void abortUpgrade() {
        if (this.mUpgradeGaiaManager != null) {
            if (this.mRWCPClient.isRunningASession()) {
                this.mRWCPClient.cancelTransfer();
            }
            this.mProgressQueue.clear();
            this.mUpgradeGaiaManager.abortUpgrade();
        }
    }

    @Override // cordova.plugins.qcc.BTLibrary.services.BluetoothService
    public boolean isUpgrading() {
        UpgradeGaiaManager upgradeGaiaManager = this.mUpgradeGaiaManager;
        return upgradeGaiaManager != null && upgradeGaiaManager.isUpgrading();
    }

    @Override // cordova.plugins.qcc.BTLibrary.services.BluetoothService
    public void sendConfirmation(int i, boolean z) {
        UpgradeGaiaManager upgradeGaiaManager = this.mUpgradeGaiaManager;
        if (upgradeGaiaManager != null) {
            upgradeGaiaManager.sendConfirmation(i, z);
        }
    }

    @Override // cordova.plugins.qcc.BTLibrary.services.BluetoothService
    public boolean requestLinkLossAlertLevel() {
        return this.mGattServices.gattServiceLinkLoss.isSupported() && requestReadCharacteristic(this.mGattServices.gattServiceLinkLoss.getAlertLevelCharacteristic());
    }

    @Override // cordova.plugins.qcc.BTLibrary.services.BluetoothService
    public boolean requestTxPowerLevel() {
        return this.mGattServices.gattServicetxPower.isSupported() && requestReadCharacteristic(this.mGattServices.gattServicetxPower.getTxPowerLevelCharacteristic());
    }

    @Override // cordova.plugins.qcc.BTLibrary.services.BluetoothService
    public boolean requestBatteryLevels() {
        if (!this.mGattServices.isBatteryServiceSupported()) {
            return false;
        }
        boolean z = true;
        for (int i = 0; i < this.mGattServices.gattServiceBatteries.size(); i++) {
            if (!requestReadCharacteristic(this.mGattServices.gattServiceBatteries.get(this.mGattServices.gattServiceBatteries.keyAt(i)).getBatteryLevelCharacteristic())) {
                z = false;
            }
        }
        return z;
    }

    @Override // cordova.plugins.qcc.BTLibrary.services.BluetoothService
    public boolean requestBodySensorLocation() {
        return this.mGattServices.gattServiceHeartRate.isBodySensorLocationCharacteristicAvailable() && requestReadCharacteristic(this.mGattServices.gattServiceHeartRate.getBodySensorLocationCharacteristic());
    }

    @Override // cordova.plugins.qcc.BTLibrary.services.BluetoothService
    public boolean sendLinkLossAlertLevel(int i) {
        if (this.mGattServices.gattServiceLinkLoss.isSupported()) {
            return requestWriteCharacteristic(this.mGattServices.gattServiceLinkLoss.getAlertLevelCharacteristic(), new byte[]{(byte) i});
        }
        return false;
    }

    @Override // cordova.plugins.qcc.BTLibrary.services.BluetoothService
    public boolean sendImmediateAlertLevel(int i) {
        if (this.mGattServices.gattServiceimmediateAlert.isSupported()) {
            return requestWriteNoResponseCharacteristic(this.mGattServices.gattServiceimmediateAlert.getAlertLevelCharacteristic(), new byte[]{(byte) i});
        }
        return false;
    }

    @Override // cordova.plugins.qcc.BTLibrary.services.BluetoothService
    public boolean sendHeartRateControlPoint(byte b) {
        if (this.mGattServices.gattServiceHeartRate.isHeartRateControlPointCharacteristicAvailable()) {
            return requestWriteCharacteristic(this.mGattServices.gattServiceHeartRate.getHeartRateControlPointCharacteristic(), new byte[]{b});
        }
        return false;
    }

    @Override // cordova.plugins.qcc.BTLibrary.services.BluetoothService
    public boolean requestHeartMeasurementNotifications(boolean z) {
        if (this.mGattServices.gattServiceHeartRate.isHeartRateMeasurementCharacteristicAvailable() && this.mGattServices.gattServiceHeartRate.isClientCharacteristicConfigurationDescriptorAvailable()) {
            return requestCharacteristicNotification(this.mGattServices.gattServiceHeartRate.getHeartRateMeasurementCharacteristic(), z);
        }
        return false;
    }

    @Override // cordova.plugins.qcc.BTLibrary.services.BluetoothService
    public void enableUpgrade(boolean z) {
        if (z && this.mUpgradeGaiaManager == null) {
            UpgradeGaiaManager upgradeGaiaManager = new UpgradeGaiaManager(this, 0);
            this.mUpgradeGaiaManager = upgradeGaiaManager;
            upgradeGaiaManager.enableDebugLogs(this.mShowDebugLogs);
        } else {
            if (z) {
                return;
            }
            this.mUpgradeGaiaManager = null;
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        if (this.mShowDebugLogs) {
            Log.i("GAIAGATTBLEService", "Service bound");
        }
        return this.mBinder;
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        if (this.mShowDebugLogs) {
            Log.i("GAIAGATTBLEService", "Service unbound");
        }
        if (this.mAppListeners.isEmpty()) {
            disconnectDevice();
        }
        return super.onUnbind(intent);
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        showDebugLogs(false);
        this.mRWCPClient.showDebugLogs(false);
        initialize();
        setDelayForRequest(60000);
    }

    @Override // android.app.Service
    public void onDestroy() {
        unregisterBondReceiver();
        if (this.mShowDebugLogs) {
            Log.i("GAIAGATTBLEService", "Service destroyed");
        }
        super.onDestroy();
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
        sendMessageToListener(7, 2, Integer.valueOf(i));
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.UpgradeGaiaManager.GaiaManagerListener
    public void onUpgradeError(UpgradeError upgradeError) {
        Log.e("GAIAGATTBLEService", "ERROR during upgrade: " + upgradeError.getString());
        sendMessageToListener(7, 3, upgradeError);
        if (this.mRWCPClient.isRunningASession()) {
            this.mRWCPClient.cancelTransfer();
            this.mProgressQueue.clear();
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.UpgradeGaiaManager.GaiaManagerListener
    public void onUploadProgress(double d) {
        if (this.mUpgradeGaiaManager.isRWCPEnabled()) {
            this.mProgressQueue.add(Double.valueOf(d));
        } else {
            sendMessageToListener(7, 4, Double.valueOf(d));
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.UpgradeGaiaManager.GaiaManagerListener
    public boolean sendGAIAUpgradePacket(byte[] bArr, boolean z) {
        UpgradeGaiaManager upgradeGaiaManager = this.mUpgradeGaiaManager;
        if (upgradeGaiaManager != null && upgradeGaiaManager.isRWCPEnabled() && z) {
            if (this.mTransferStartTime <= 0) {
                this.mTransferStartTime = System.currentTimeMillis();
            }
            return this.mRWCPClient.sendData(bArr);
        }
        return sendGaiaCommandEndpoint(bArr);
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.UpgradeGaiaManager.GaiaManagerListener
    public void onUpgradeFinish() {
        sendMessageToListener(7, 0, null);
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.UpgradeGaiaManager.GaiaManagerListener
    public void askConfirmation(int i) {
        if (sendMessageToListener(7, 1, Integer.valueOf(i))) {
            return;
        }
        sendConfirmation(i, true);
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.UpgradeGaiaManager.GaiaManagerListener
    public void onRWCPEnabled(boolean z) {
        requestCharacteristicNotification(this.mGattServices.gattServiceGaia.getGaiaDataCharacteristic(), z);
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.UpgradeGaiaManager.GaiaManagerListener
    public void onRWCPNotSupported() {
        this.mIsRWCPSupported = false;
        sendMessageToListener(6, 6, false);
    }

    @Override // cordova.plugins.qcc.BTLibrary.rwcp.RWCPClient.RWCPListener
    public boolean sendRWCPSegment(byte[] bArr) {
        boolean requestWriteNoResponseCharacteristic = requestWriteNoResponseCharacteristic(this.mGattServices.gattServiceGaia.getGaiaDataCharacteristic(), bArr);
        if (requestWriteNoResponseCharacteristic && this.mShowDebugLogs) {
            Log.i("GAIAGATTBLEService", "Attempt to send RWCP segment on DATA ENDPOINT characteristic: " + Utils.getStringFromBytes(bArr));
        } else if (!requestWriteNoResponseCharacteristic) {
            Log.w("GAIAGATTBLEService", "Attempt to send RWCP segment on DATA ENDPOINT characteristic FAILED: " + Utils.getStringFromBytes(bArr));
        }
        return requestWriteNoResponseCharacteristic;
    }

    @Override // cordova.plugins.qcc.BTLibrary.rwcp.RWCPClient.RWCPListener
    public void onTransferFailed() {
        abortUpgrade();
        sendMessageToListener(6, 8);
    }

    @Override // cordova.plugins.qcc.BTLibrary.rwcp.RWCPClient.RWCPListener
    public void onTransferFinished() {
        this.mUpgradeGaiaManager.onTransferFinished();
        this.mProgressQueue.clear();
    }

    @Override // cordova.plugins.qcc.BTLibrary.rwcp.RWCPClient.RWCPListener
    public void onTransferProgress(int i) {
        if (i > 0) {
            //sendMessageToListener(7, 4, Double.valueOf(d));
        }
    }

    @Override // cordova.plugins.qcc.BTLibrary.libraries.ble.BLEService, cordova.plugins.qcc.BTLibrary.services.BluetoothService
    public BluetoothDevice getDevice() {
        return super.getDevice();
    }

    @Override // cordova.plugins.qcc.BTLibrary.libraries.ble.BLEService
    public int getMtuSize() {
        return super.getMtuSize();
    }

    @Override // cordova.plugins.qcc.BTLibrary.libraries.ble.BLEService, cordova.plugins.qcc.BTLibrary.services.BluetoothService
    public int getConnectionState() {
        int connectionState = super.getConnectionState();
        int i = 1;
        if (connectionState != 1) {
            i = 2;
            if (connectionState != 2) {
                i = 3;
                if (connectionState != 3) {
                    return 0;
                }
            }
        }
        return i;
    }

    @Override // cordova.plugins.qcc.BTLibrary.libraries.ble.BLEService, cordova.plugins.qcc.BTLibrary.services.BluetoothService
    public boolean connectToDevice(String str) {
        return super.connectToDevice(str);
    }

    @Override // cordova.plugins.qcc.BTLibrary.receivers.BondStateReceiver.BondStateListener
    public void onBondStateChange(BluetoothDevice bluetoothDevice, int i) {
        BluetoothDevice device = getDevice();
        if (bluetoothDevice == null || device == null || !bluetoothDevice.getAddress().equals(device.getAddress())) {
            return;
        }
        Log.i("GAIAGATTBLEService", "ACTION_BOND_STATE_CHANGED for " + bluetoothDevice.getAddress() + " with bond state " + BLEUtils.getBondStateName(i));
        sendMessageToListener(1, Integer.valueOf(i));
        if (i == 12) {
            sendMessageToListener(6, 11, 1);
            onGattReady();
        } else if (i == 11) {
            this.mBondingInitiated = true;
        }
    }

    private boolean sendMessageToListener(int i) {
        if (!this.mAppListeners.isEmpty()) {
            for (int i2 = 0; i2 < this.mAppListeners.size(); i2++) {
                this.mAppListeners.get(i2).obtainMessage(i).sendToTarget();
            }
        }
        return !this.mAppListeners.isEmpty();
    }

    private boolean sendMessageToListener(int i, Object obj) {
        if (!this.mAppListeners.isEmpty()) {
            for (int i2 = 0; i2 < this.mAppListeners.size(); i2++) {
                this.mAppListeners.get(i2).obtainMessage(i, obj).sendToTarget();
            }
        }
        return !this.mAppListeners.isEmpty();
    }

    private boolean sendMessageToListener(int i, int i2, Object obj) {
        if (!this.mAppListeners.isEmpty()) {
            for (int i3 = 0; i3 < this.mAppListeners.size(); i3++) {
                this.mAppListeners.get(i3).obtainMessage(i, i2, 0, obj).sendToTarget();
            }
        }
        return !this.mAppListeners.isEmpty();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // cordova.plugins.qcc.BTLibrary.libraries.ble.BLEService
    public synchronized void setState(int i) {
        super.setState(i);
        int i2 = 2;
        if (i != 2) {
            i2 = 1;
            if (i != 1) {
                i2 = 3;
                if (i != 3) {
                    i2 = 0;
                }
            }
        }
        sendMessageToListener(0, Integer.valueOf(i2));
    }

    @Override // cordova.plugins.qcc.BTLibrary.libraries.ble.BLEService
    protected void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
        Log.i("GAIAGATTBLEService", "onConnectionStateChange: " + BLEUtils.getGattStatusName(i, true));
        if (i == 0 && i2 == 2) {
            sendMessageToListener(6, 11, 0);
            Log.i("GAIAGATTBLEService", "Attempting to start service discovery: " + bluetoothGatt.discoverServices());
        } else if (i2 == 0) {
            resetDeviceInformation();
            if (isUpgrading()) {
                this.mHandler.postDelayed(new Runnable() { // from class: cordova.plugins.qcc.BTLibrary.services.GAIAGATTBLEService.2
                    @Override // java.lang.Runnable
                    public void run() {
                        GAIAGATTBLEService.this.reconnectToDevice();
                    }
                }, 1000L);
            } else {
                sendMessageToListener(0, 0);
            }
        }
    }

    @Override // cordova.plugins.qcc.BTLibrary.libraries.ble.BLEService
    protected void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
        if (i == 0) {
            this.mGattServices.setSupportedGattServices(bluetoothGatt.getServices());
            sendMessageToListener(2, this.mGattServices);
            if (this.mGattServices.gattServiceGaia.isSupported()) {
                requestReadCharacteristicForPairing(this.mGattServices.gattServiceGaia.getGaiaDataCharacteristic());
            } else {
                onGattReady();
            }
            if (this.mShowDebugLogs) {
                Log.i("GAIAGATTBLEService", this.mGattServices.toString());
            }
        }
    }

    @Override // cordova.plugins.qcc.BTLibrary.libraries.ble.BLEService
    protected void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        if (bluetoothGattCharacteristic != null) {
            UUID uuid = bluetoothGattCharacteristic.getUuid();
            if (this.mIsGattReady || !uuid.equals(GATT.UUIDs.CHARACTERISTIC_GAIA_DATA_ENDPOINT_UUID)) {
                if (i == 0 && uuid.equals(GATT.UUIDs.CHARACTERISTIC_ALERT_LEVEL_UUID) && bluetoothGattCharacteristic.getService().getUuid().equals(GATT.UUIDs.SERVICE_LINK_LOSS_UUID)) {
                    sendMessageToListener(6, 1, Integer.valueOf(bluetoothGattCharacteristic.getIntValue(17, 0).intValue()));
                    return;
                }
                if (i == 0 && uuid.equals(GATT.UUIDs.CHARACTERISTIC_TX_POWER_LEVEL_UUID)) {
                    sendMessageToListener(6, 0, Integer.valueOf(bluetoothGattCharacteristic.getIntValue(33, 0).intValue()));
                    return;
                }
                if (i == 0 && uuid.equals(GATT.UUIDs.CHARACTERISTIC_BATTERY_LEVEL_UUID)) {
                    sendMessageToListener(6, 3, Integer.valueOf(bluetoothGattCharacteristic.getService().getInstanceId()));
                    return;
                } else {
                    if (i == 0 && uuid.equals(GATT.UUIDs.CHARACTERISTIC_BODY_SENSOR_LOCATION_UUID)) {
                        sendMessageToListener(6, 5, Integer.valueOf(bluetoothGattCharacteristic.getValue()[0]));
                        return;
                    }
                    return;
                }
            }
            if (i == 0) {
                if (this.mShowDebugLogs) {
                    Log.i("GAIAGATTBLEService", "Successful read characteristic to induce pairing: no need to bond device.");
                }
                onGattReady();
                return;
            }
            if (i == 15 || i == 5 || i == 8 || i == 137 || i == 133 || i == 47) {
                if (this.mAttemptsForPairingInduction < 3) {
                    this.mHandler.postDelayed(new Runnable() { // from class: cordova.plugins.qcc.BTLibrary.services.GAIAGATTBLEService.3
                        @Override // java.lang.Runnable
                        public void run() {
                            if (!GAIAGATTBLEService.this.mBondingInitiated && GAIAGATTBLEService.this.isConnected()) {
                                GAIAGATTBLEService.this.mAttemptsForPairingInduction++;
                                GAIAGATTBLEService gAIAGATTBLEService = GAIAGATTBLEService.this;
                                gAIAGATTBLEService.requestReadCharacteristicForPairing(gAIAGATTBLEService.mGattServices.gattServiceGaia.getGaiaDataCharacteristic());
                                return;
                            }
                            GAIAGATTBLEService.this.mAttemptsForPairingInduction = 0;
                        }
                    }, 1000L);
                    return;
                }
                this.mAttemptsForPairingInduction = 0;
                if (isUpgrading()) {
                    Log.w("GAIAGATTBLEService", "Unsuccessful READ characteristic to induce pairing after 3 attempts, aborting upgrade.");
                    abortUpgrade();
                    sendMessageToListener(7, 3, 4);
                } else {
                    Log.w("GAIAGATTBLEService", "Unsuccessful READ characteristic to induce pairing after 3 attempts, disconnecting device.");
                }
                disconnectDevice();
            }
        }
    }

    @Override // cordova.plugins.qcc.BTLibrary.libraries.ble.BLEService
    protected void onReceivedCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic != null) {
            UUID uuid = bluetoothGattCharacteristic.getUuid();
            if (uuid.equals(GATT.UUIDs.CHARACTERISTIC_GAIA_RESPONSE_UUID)) {
                byte[] value = bluetoothGattCharacteristic.getValue();
                if (value != null) {
                    UpgradeGaiaManager upgradeGaiaManager = this.mUpgradeGaiaManager;
                    if (upgradeGaiaManager != null) {
                        upgradeGaiaManager.onReceiveGAIAPacket(value);
                        return;
                    } else {
                        sendMessageToListener(3, value);
                        return;
                    }
                }
                return;
            }
            if (uuid.equals(GATT.UUIDs.CHARACTERISTIC_HEART_RATE_MEASUREMENT_UUID)) {
                sendMessageToListener(6, 4, this.mGattServices.gattServiceHeartRate.getHeartRateMeasurementValues());
                return;
            }
            if (uuid.equals(GATT.UUIDs.CHARACTERISTIC_GAIA_DATA_ENDPOINT_UUID)) {
                byte[] value2 = bluetoothGattCharacteristic.getValue();
                if (value2 != null) {
                    this.mRWCPClient.onReceiveRWCPSegment(value2);
                    return;
                }
                return;
            }
            if (this.mShowDebugLogs) {
                Log.i("GAIAGATTBLEService", "Received notification over characteristic: " + bluetoothGattCharacteristic.getUuid());
            }
        }
    }

    @Override // cordova.plugins.qcc.BTLibrary.libraries.ble.BLEService
    protected void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
        UUID uuid = bluetoothGattDescriptor.getUuid();
        UUID uuid2 = bluetoothGattDescriptor.getCharacteristic().getUuid();
        if (i == 0) {
            this.mNotifiedCharacteristics.add(uuid2);
            if (this.mShowDebugLogs) {
                Log.i("GAIAGATTBLEService", "Successful write descriptor " + uuid.toString() + " for characteristic " + uuid2.toString());
            }
        } else {
            Log.w("GAIAGATTBLEService", "Unsuccessful write descriptor " + uuid.toString() + " for characteristic " + uuid2.toString() + " with status " + BLEUtils.getGattStatusName(i, false));
        }
        if (i == 0 && this.mGattServices.gattServiceGaia.isSupported() && uuid.equals(GATT.UUIDs.DESCRIPTOR_CLIENT_CHARACTERISTIC_CONFIGURATION_UUID) && uuid2.equals(GATT.UUIDs.CHARACTERISTIC_GAIA_RESPONSE_UUID)) {
            this.mIsGaiaReady = true;
            sendMessageToListener(4);
            if (isUpgrading()) {
                this.mUpgradeGaiaManager.onGaiaReady();
                return;
            }
            return;
        }
        if (i == 0 && this.mGattServices.gattServiceHeartRate.isSupported() && uuid2.equals(GATT.UUIDs.CHARACTERISTIC_HEART_RATE_MEASUREMENT_UUID)) {
            if (this.mShowDebugLogs) {
                Log.d("GAIAGATTBLEService", "Received successful onDescriptorWrite for Heart Rate Measurement");
            }
        } else if (this.mGattServices.gattServiceGaia.isRWCPTransportSupported() && uuid.equals(GATT.UUIDs.DESCRIPTOR_CLIENT_CHARACTERISTIC_CONFIGURATION_UUID) && uuid2.equals(GATT.UUIDs.CHARACTERISTIC_GAIA_DATA_ENDPOINT_UUID)) {
            if (i == 0) {
                sendMessageToListener(6, 7, Boolean.valueOf(Arrays.equals(bluetoothGattDescriptor.getValue(), BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE)));
                return;
            }
            this.mIsRWCPSupported = false;
            this.mUpgradeGaiaManager.onRWCPNotSupported();
            sendMessageToListener(6, 6, false);
        }
    }

    @Override // cordova.plugins.qcc.BTLibrary.libraries.ble.BLEService
    protected void onRemoteRssiRead(BluetoothGatt bluetoothGatt, int i, int i2) {
        if (i2 == 0 && this.mUpdateRssi) {
            sendMessageToListener(6, 2, Integer.valueOf(i));
            this.mHandler.postDelayed(this.mRssiRunnable, 1000L);
        }
    }

    @Override // cordova.plugins.qcc.BTLibrary.libraries.ble.BLEService
    protected void onMtuChanged(BluetoothGatt bluetoothGatt, int i, int i2) {
        if (i2 == 0) {
            Log.i("GAIAGATTBLEService", "MTU size had been updated to " + i);
            sendMessageToListener(6, 10, Integer.valueOf(i));
        } else {
            Log.w("GAIAGATTBLEService", "MTU request failed, mtu size is: " + i);
            sendMessageToListener(6, 9, false);
        }
        int i3 = i - 3;
        UpgradeGaiaManager upgradeGaiaManager = this.mUpgradeGaiaManager;
        if (upgradeGaiaManager != null) {
            upgradeGaiaManager.setPacketMaximumSize(i3);
        }
    }

    @Override // cordova.plugins.qcc.BTLibrary.libraries.ble.BLEService
    protected void onDescriptorRead(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
        if (i == 0 && bluetoothGattDescriptor.getCharacteristic().getUuid().equals(GATT.UUIDs.CHARACTERISTIC_BATTERY_LEVEL_UUID)) {
            GattServiceBattery gattServiceBattery = this.mGattServices.gattServiceBatteries.get(Integer.valueOf(bluetoothGattDescriptor.getCharacteristic().getService().getInstanceId()));
            if (gattServiceBattery != null) {
                gattServiceBattery.updateDescription();
            }
        }
    }

    @Override // cordova.plugins.qcc.BTLibrary.libraries.ble.BLEService, cordova.plugins.qcc.BTLibrary.services.BluetoothService
    public boolean reconnectToDevice() {
        return super.reconnectToDevice();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isConnected() {
        return super.getConnectionState() == 2;
    }

    private boolean isDisconnected() {
        return super.getConnectionState() == 0;
    }

    private void resetDeviceInformation() {
        this.mIsGattReady = false;
        this.mIsGaiaReady = false;
        this.mUpdateRssi = false;
        this.mBondingInitiated = false;
        this.mAttemptsForPairingInduction = 0;
        UpgradeGaiaManager upgradeGaiaManager = this.mUpgradeGaiaManager;
        if (upgradeGaiaManager != null) {
            upgradeGaiaManager.reset();
        }
        this.mRWCPClient.cancelTransfer();
        this.mProgressQueue.clear();
        this.mNotifiedCharacteristics.clear();
    }

    @Override // cordova.plugins.qcc.BTLibrary.libraries.ble.BLEService, cordova.plugins.qcc.BTLibrary.services.BluetoothService
    public void setStateToDisconnected() {
        super.setStateToDisconnected();
    }

    private void registerBondReceiver() {
        IntentFilter intentFilter = new IntentFilter("android.bluetooth.device.action.BOND_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.device.action.PAIRING_REQUEST");
        registerReceiver(this.mBondStateReceiver, intentFilter);
    }

    private void unregisterBondReceiver() {
        unregisterReceiver(this.mBondStateReceiver);
    }

    private void unregisterNotifications() {
        for (int i = 0; i < this.mNotifiedCharacteristics.size(); i++) {
            requestCharacteristicNotification(this.mNotifiedCharacteristics.get(i), false);
        }
    }

    private void onGattReady() {
        this.mIsGattReady = true;
        if (this.mShowDebugLogs) {
            Log.i("GAIAGATTBLEService", "GATT connection is ready to be used.");
        }
        sendMessageToListener(5);
        if (this.mGattServices.gattServiceGaia.isSupported()) {
            if (this.mShowDebugLogs) {
                Log.i("GAIAGATTBLEService", "GAIA is supported, start request for GAIA notifications.");
            }
            requestCharacteristicNotification(this.mGattServices.gattServiceGaia.getGaiaResponseCharacteristic(), true);
        }
        if (this.mGattServices.isBatteryServiceSupported()) {
            for (int i = 0; i < this.mGattServices.gattServiceBatteries.size(); i++) {
                if (this.mShowDebugLogs) {
                    Log.i("GAIAGATTBLEService", "Battery service is supported, request presentation format descriptors for service " + (i + 1) + ".");
                }
                GattServiceBattery gattServiceBattery = this.mGattServices.gattServiceBatteries.get(this.mGattServices.gattServiceBatteries.keyAt(i));
                if (gattServiceBattery.isPresentationFormatDescriptorAvailable()) {
                    requestReadDescriptor(gattServiceBattery.getPresentationFormatDescriptor());
                }
            }
        }
    }

    private boolean sendGaiaCommandEndpoint(byte[] bArr) {
        if (this.mGattServices.gattServiceGaia.isCharacteristicGaiaCommandAvailable()) {
            return requestWriteCharacteristic(this.mGattServices.gattServiceGaia.getGaiaCommandCharacteristic(), bArr);
        }
        Log.w("GAIAGATTBLEService", "Attempt to send data over CHARACTERISTIC_CSR_GAIA_COMMAND_ENDPOINT failed: characteristic not available.");
        return false;
    }

    /* loaded from: classes2.dex */
    public class LocalBinder extends Binder {
        public LocalBinder() {
        }

        public GAIAGATTBLEService getService() {
            return GAIAGATTBLEService.this;
        }
    }
}
