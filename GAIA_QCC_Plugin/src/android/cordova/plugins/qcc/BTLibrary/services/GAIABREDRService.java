package cordova.plugins.qcc.BTLibrary.services;

import android.app.Service;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import cordova.plugins.qcc.BTLibrary.libraries.ble.BLEUtils;
import cordova.plugins.qcc.BTLibrary.models.gatt.GATTServices;
import cordova.plugins.qcc.BTLibrary.receivers.BondStateReceiver;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class GAIABREDRService extends Service implements BluetoothService, BondStateReceiver.BondStateListener {
    private GAIABREDRProvider mGAIABREDRProvider;
    private boolean mShowDebugLogs = false;
    private final String TAG = "GAIABREDRService";
    private final List<Handler> mAppListeners = new ArrayList();
    private final IBinder mBinder = new LocalBinder();
    private final BondStateReceiver mBondStateReceiver = new BondStateReceiver(this);
    private final ProviderHandler mProviderHandler = new ProviderHandler(this);

    private void onErrorReceived(int i) {
    }

    @Override // cordova.plugins.qcc.BTLibrary.services.BluetoothService
    public GATTServices getGattSupport() {
        return null;
    }

    @Override // cordova.plugins.qcc.BTLibrary.services.BluetoothService
    public int getTransport() {
        return 1;
    }

    @Override // cordova.plugins.qcc.BTLibrary.services.BluetoothService
    public boolean isGattReady() {
        return false;
    }

    @Override // cordova.plugins.qcc.BTLibrary.services.BluetoothService
    public boolean requestBatteryLevels() {
        return false;
    }

    @Override // cordova.plugins.qcc.BTLibrary.services.BluetoothService
    public boolean requestBodySensorLocation() {
        return false;
    }

    @Override // cordova.plugins.qcc.BTLibrary.services.BluetoothService
    public boolean requestHeartMeasurementNotifications(boolean z) {
        return false;
    }

    @Override // cordova.plugins.qcc.BTLibrary.services.BluetoothService
    public boolean requestLinkLossAlertLevel() {
        return false;
    }

    @Override // cordova.plugins.qcc.BTLibrary.services.BluetoothService
    public boolean requestTxPowerLevel() {
        return false;
    }

    @Override // cordova.plugins.qcc.BTLibrary.services.BluetoothService
    public boolean sendHeartRateControlPoint(byte b) {
        return false;
    }

    @Override // cordova.plugins.qcc.BTLibrary.services.BluetoothService
    public boolean sendImmediateAlertLevel(int i) {
        return false;
    }

    @Override // cordova.plugins.qcc.BTLibrary.services.BluetoothService
    public boolean sendLinkLossAlertLevel(int i) {
        return false;
    }

    @Override // cordova.plugins.qcc.BTLibrary.services.BluetoothService
    public void setStateToDisconnected() {
    }

    @Override // cordova.plugins.qcc.BTLibrary.services.BluetoothService
    public boolean startRssiUpdates(boolean z) {
        return false;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        if (this.mShowDebugLogs) {
            Log.i("GAIABREDRService", "Service bound");
        }
        registerBondReceiver();
        return this.mBinder;
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        if (this.mShowDebugLogs) {
            Log.i("GAIABREDRService", "Service unbound");
        }
        unregisterBondReceiver();
        if (this.mAppListeners.isEmpty() && !isUpgrading()) {
            disconnectDevice();
        }
        return super.onUnbind(intent);
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        if (this.mGAIABREDRProvider == null) {
            this.mGAIABREDRProvider = new GAIABREDRProvider(this.mProviderHandler, (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE));
        }
        this.mGAIABREDRProvider.showDebugLogs(this.mShowDebugLogs);
    }

    @Override // android.app.Service
    public void onDestroy() {
        disconnectDevice();
        if (this.mShowDebugLogs) {
            Log.i("GAIABREDRService", "Service destroyed");
        }
        super.onDestroy();
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
    public boolean connectToDevice(String str) {
        if (this.mGAIABREDRProvider.getState() == 2) {
            Log.w("GAIABREDRService", "connection failed: a device is already connected.");
            return false;
        }
        return this.mGAIABREDRProvider.connect(str, this);
    }

    @Override // cordova.plugins.qcc.BTLibrary.services.BluetoothService
    public boolean reconnectToDevice() {
        return this.mGAIABREDRProvider.reconnectToDevice(this);
    }

    @Override // cordova.plugins.qcc.BTLibrary.services.BluetoothService
    public void disconnectDevice() {
        this.mGAIABREDRProvider.disconnect();
    }

    @Override // cordova.plugins.qcc.BTLibrary.services.BluetoothService
    public void enableDebugLogs(boolean z) {
        this.mShowDebugLogs = z;
        this.mGAIABREDRProvider.enableDebugLogs(z);
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
    public BluetoothDevice getDevice() {
        return this.mGAIABREDRProvider.getDevice();
    }

    @Override // cordova.plugins.qcc.BTLibrary.services.BluetoothService
    public int getConnectionState() {
        int state = this.mGAIABREDRProvider.getState();
        int i = 1;
        if (state != 1) {
            i = 2;
            if (state != 2) {
                i = 3;
                if (state != 3) {
                    return 0;
                }
            }
        }
        return i;
    }

    @Override // cordova.plugins.qcc.BTLibrary.services.BluetoothService
    public boolean sendGAIAPacket(byte[] bArr) {
        return this.mGAIABREDRProvider.sendData(bArr);
    }

    @Override // cordova.plugins.qcc.BTLibrary.services.BluetoothService
    public boolean isGaiaReady() {
        return this.mGAIABREDRProvider.isGaiaReady();
    }

    @Override // cordova.plugins.qcc.BTLibrary.services.BluetoothService
    public void startUpgrade(File file) {
        this.mGAIABREDRProvider.startUpgrade(file);
    }

    @Override // cordova.plugins.qcc.BTLibrary.services.BluetoothService
    public int getResumePoint() {
        return this.mGAIABREDRProvider.getResumePoint();
    }

    @Override // cordova.plugins.qcc.BTLibrary.services.BluetoothService
    public void abortUpgrade() {
        this.mGAIABREDRProvider.abortUpgrade();
    }

    @Override // cordova.plugins.qcc.BTLibrary.services.BluetoothService
    public boolean isUpgrading() {
        return this.mGAIABREDRProvider.isUpgrading();
    }

    @Override // cordova.plugins.qcc.BTLibrary.services.BluetoothService
    public void sendConfirmation(int i, boolean z) {
        this.mGAIABREDRProvider.sendConfirmation(i, z);
    }

    @Override // cordova.plugins.qcc.BTLibrary.services.BluetoothService
    public void enableUpgrade(boolean z) {
        this.mGAIABREDRProvider.enableUpgrade(z);
    }

    @Override // cordova.plugins.qcc.BTLibrary.receivers.BondStateReceiver.BondStateListener
    public void onBondStateChange(BluetoothDevice bluetoothDevice, int i) {
        BluetoothDevice device = getDevice();
        if (bluetoothDevice == null || device == null || !bluetoothDevice.getAddress().equals(device.getAddress())) {
            return;
        }
        if (this.mShowDebugLogs) {
            Log.i("GAIABREDRService", "ACTION_BOND_STATE_CHANGED for " + bluetoothDevice.getAddress() + " with bond state " + BLEUtils.getBondStateName(i));
        }
        sendMessageToListener(1, Integer.valueOf(i));
        if (i == 12) {
            bluetoothDevice.fetchUuidsWithSdp();
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

    /* JADX INFO: Access modifiers changed from: private */
    public void handleMessageFromProvider(Message message) {
        String str;
        int i = message.what;
        if (i == 0) {
            int intValue = ((Integer) message.obj).intValue();
            if (this.mShowDebugLogs) {
                Log.i("GAIABREDRService", "Handle a message from BR/EDR Provider: CONNECTION_STATE_HAS_CHANGED: ".concat(intValue == 2 ? "CONNECTED" : intValue == 1 ? "CONNECTING" : intValue == 3 ? "DISCONNECTING" : intValue == 0 ? "DISCONNECTED" : "UNKNOWN"));
            }
            onConnectionStateHasChanged(intValue);
            return;
        }
        if (i == 1) {
            byte[] bArr = (byte[]) message.obj;
            if (this.mShowDebugLogs) {
                Log.i("GAIABREDRService", "Handle a message from BR/EDR Provider: GAIA_PACKET");
            }
            onGaiaDataReceived(bArr);
            return;
        }
        if (i == 2) {
            int intValue2 = ((Integer) message.obj).intValue();
            if (intValue2 == 0) {
                str = "CONNECTION_FAILED";
            } else {
                str = intValue2 == 1 ? "CONNECTION_LOST" : "UNKNOWN " + intValue2;
            }
            Log.w("GAIABREDRService", "Handle a message from BR/EDR Provider: ERROR: " + str);
            onErrorReceived(intValue2);
            return;
        }
        if (i == 3) {
            if (this.mShowDebugLogs) {
                Log.i("GAIABREDRService", "Handle a message from BR/EDR Provider: GAIA_READY");
            }
            sendMessageToListener(4);
        } else if (i == 4) {
            sendMessageToListener(7, message.arg1, message.obj);
        } else if (this.mShowDebugLogs) {
            Log.d("GAIABREDRService", "Handle a message from BR/EDR Provider: UNKNOWN MESSAGE: " + message.what + " obj: " + message.obj);
        }
    }

    private void onConnectionStateHasChanged(int i) {
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
        if (i == 0 && isUpgrading()) {
            reconnectToDevice();
        }
    }

    private void onGaiaDataReceived(byte[] bArr) {
        sendMessageToListener(3, bArr);
    }

    private void registerBondReceiver() {
        IntentFilter intentFilter = new IntentFilter("android.bluetooth.device.action.BOND_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.device.action.PAIRING_REQUEST");
        registerReceiver(this.mBondStateReceiver, intentFilter);
    }

    private void unregisterBondReceiver() {
        unregisterReceiver(this.mBondStateReceiver);
    }

    /* loaded from: classes2.dex */
    public class LocalBinder extends Binder {
        public LocalBinder() {
        }

        public GAIABREDRService getService() {
            return GAIABREDRService.this;
        }
    }

    /* loaded from: classes2.dex */
    private static class ProviderHandler extends Handler {
        final WeakReference<GAIABREDRService> mmReference;

        ProviderHandler(GAIABREDRService gAIABREDRService) {
            this.mmReference = new WeakReference<>(gAIABREDRService);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            this.mmReference.get().handleMessageFromProvider(message);
        }
    }
}
