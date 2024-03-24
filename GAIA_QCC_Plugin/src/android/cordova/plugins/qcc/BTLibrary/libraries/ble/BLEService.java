package cordova.plugins.qcc.BTLibrary.libraries.ble;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.os.Handler;
import android.util.ArrayMap;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.UUID;

/* loaded from: classes2.dex */
public abstract class BLEService extends Service {
    private static final int DEFAULT_DELAY_FOR_NOTIFICATION_REQUEST = 1000;
    private static final int DEFAULT_DELAY_FOR_REQUEST = 60000;
    public static final int MTU_SIZE_DEFAULT = 23;
    public static final int MTU_SIZE_MAXIMUM = 512;
    public static final int MTU_SIZE_MINIMUM = 23;
    private static final int REQUEST_MAX_ATTEMPTS = 2;
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothGatt mBluetoothGatt;
    private BluetoothDevice mDevice;
    private final String TAG = "BLEService";
    private int mConnectionState = 0;
    private final Queue<Request> mRequestsQueue = new LinkedList();
    private boolean isQueueProcessing = false;
    private final Handler mHandler = new Handler();
    private TimeOutRequestRunnable mTimeOutRequestRunnable = null;
    private final ArrayMap<UUID, BluetoothGattCharacteristic> mCharacteristics = new ArrayMap<>();
    private boolean mShowDebugLogs = false;
    private int mDelay = DEFAULT_DELAY_FOR_REQUEST;
    private int mMtuSize = 23;
    private final BluetoothGattCallback mGattCallback = new BluetoothGattCallback() { // from class: cordova.plugins.qcc.BTLibrary.libraries.ble.BLEService.1
        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            BLEService.this.receiveConnectionStateChange(bluetoothGatt, i, i2);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            BLEService.this.receiveServicesDiscovered(bluetoothGatt, i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            BLEService.this.receiveCharacteristicRead(bluetoothGatt, bluetoothGattCharacteristic, i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            BLEService.this.onReceivedCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            BLEService.this.receiveDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor, i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            BLEService.this.receiveCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorRead(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            BLEService.this.receiveDescriptorRead(bluetoothGatt, bluetoothGattDescriptor, i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onReadRemoteRssi(BluetoothGatt bluetoothGatt, int i, int i2) {
            BLEService.this.receiveRemoteRssiRead(bluetoothGatt, i, i2);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onMtuChanged(BluetoothGatt bluetoothGatt, int i, int i2) {
            BLEService.this.receiveMtuChanged(bluetoothGatt, i, i2);
        }
    };

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface State {
        public static final int CONNECTED = 2;
        public static final int CONNECTING = 1;
        public static final int DISCONNECTED = 0;
        public static final int DISCONNECTING = 3;
    }

    protected abstract void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i);

    protected abstract void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i);

    protected abstract void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2);

    protected abstract void onDescriptorRead(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i);

    protected abstract void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i);

    protected abstract void onMtuChanged(BluetoothGatt bluetoothGatt, int i, int i2);

    protected abstract void onReceivedCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic);

    protected abstract void onRemoteRssiRead(BluetoothGatt bluetoothGatt, int i, int i2);

    protected abstract void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i);

    /* JADX INFO: Access modifiers changed from: protected */
    public void showDebugLogs(boolean z) {
        this.mShowDebugLogs = z;
        Log.i("BLEService", "Debug logs are now " + (z ? "activated" : "deactivated") + ".");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public synchronized void setDelayForRequest(int i) {
        this.mDelay = i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean requestCharacteristicNotification(UUID uuid, boolean z) {
        if (this.mShowDebugLogs) {
            Log.d("BLEService", "Request received for notification on characteristic with UUID " + uuid.toString() + " for " + (z ? "activation" : "deactivation"));
        }
        if (this.mConnectionState != 2) {
            Log.w("BLEService", "request characteristic notification not initiated: device is disconnected.");
            return false;
        }
        BluetoothGattCharacteristic bluetoothGattCharacteristic = this.mCharacteristics.get(uuid);
        if (bluetoothGattCharacteristic == null) {
            Log.w("BLEService", "request characteristic notification not initiated: characteristic not found for UUID " + uuid + ".");
            return false;
        }
        return requestCharacteristicNotification(bluetoothGattCharacteristic, z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean requestCharacteristicNotification(BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z) {
        byte[] bArr;
        if (this.mShowDebugLogs) {
            Log.d("BLEService", "Request received for notification on characteristic with UUID " + bluetoothGattCharacteristic.getUuid().toString() + " for " + (z ? "activation" : "deactivation"));
        }
        if (this.mConnectionState != 2) {
            Log.w("BLEService", "request characteristic notification not initiated: device is disconnected.");
            return false;
        }
        if (bluetoothGattCharacteristic == null) {
            Log.w("BLEService", "request characteristic notification not initiated: characteristic is null.");
            return false;
        }
        if (!this.mCharacteristics.containsKey(bluetoothGattCharacteristic.getUuid())) {
            Log.w("BLEService", "request characteristic notification not initiated: unknown characteristic UUID.");
            return false;
        }
        BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(Characteristics.CLIENT_CHARACTERISTIC_CONFIG);
        if (descriptor == null) {
            Log.w("BLEService", "request characteristic notification not initiated: no CLIENT_CHARACTERISTIC_CONFIGURATION descriptor.");
            return false;
        }
        Request createCharacteristicNotificationRequest = Request.createCharacteristicNotificationRequest(bluetoothGattCharacteristic, z);
        if (z) {
            bArr = BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE;
        } else {
            bArr = BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE;
        }
        addToRequestsQueue(createCharacteristicNotificationRequest);
        addToRequestsQueue(Request.createWriteDescriptorRequest(descriptor, bArr));
        return true;
    }

    protected boolean requestWriteCharacteristic(UUID uuid, byte[] bArr) {
        if (this.mShowDebugLogs) {
            Log.d("BLEService", "Request received for write on characteristic with UUID " + uuid.toString());
        }
        if (this.mConnectionState != 2) {
            Log.w("BLEService", "request write characteristic not initiated: device is disconnected.");
            return false;
        }
        BluetoothGattCharacteristic bluetoothGattCharacteristic = this.mCharacteristics.get(uuid);
        if (bluetoothGattCharacteristic == null) {
            Log.w("BLEService", "request write characteristic not initiated: characteristic not found for UUID " + uuid + ".");
            return false;
        }
        return requestWriteCharacteristic(bluetoothGattCharacteristic, bArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean requestWriteCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr) {
        if (this.mShowDebugLogs) {
            Log.d("BLEService", "Request received for write on characteristic with UUID " + bluetoothGattCharacteristic.getUuid().toString());
        }
        if (this.mConnectionState != 2) {
            Log.w("BLEService", "request write characteristic not initiated: device is disconnected.");
            return false;
        }
        if (bluetoothGattCharacteristic == null) {
            Log.w("BLEService", "request write characteristic not initiated: characteristic is null.");
            return false;
        }
        if (!this.mCharacteristics.containsKey(bluetoothGattCharacteristic.getUuid())) {
            Log.w("BLEService", "request write characteristic not initiated: unknown characteristic UUID.");
            return false;
        }
        if ((bluetoothGattCharacteristic.getProperties() & 8) <= 0) {
            Log.w("BLEService", "request write characteristic not initiated: characteristic does not have the WRITE property.");
            return false;
        }
        addToRequestsQueue(Request.createWriteCharacteristicRequest(bluetoothGattCharacteristic, bArr));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean requestWriteNoResponseCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr) {
        if (this.mShowDebugLogs) {
            Log.d("BLEService", "Request received for write without response on characteristic with UUID " + bluetoothGattCharacteristic.getUuid().toString());
        }
        if (this.mConnectionState != 2) {
            Log.w("BLEService", "request write without response characteristic not initiated: device is disconnected.");
            return false;
        }
        if (bluetoothGattCharacteristic == null) {
            Log.w("BLEService", "request write without response characteristic not initiated: characteristic is null.");
            return false;
        }
        if (!this.mCharacteristics.containsKey(bluetoothGattCharacteristic.getUuid())) {
            Log.w("BLEService", "request write without response characteristic not initiated: unknown characteristic UUID.");
            return false;
        }
        if ((bluetoothGattCharacteristic.getProperties() & 4) <= 0) {
            Log.w("BLEService", "request write without response characteristic not initiated: characteristic does not have the WRITE NO RESPONSE property.");
            return false;
        }
        addToRequestsQueue(Request.createWriteNoResponseCharacteristicRequest(bluetoothGattCharacteristic, bArr));
        return true;
    }

    protected boolean requestReadCharacteristic(UUID uuid) {
        if (this.mShowDebugLogs) {
            Log.d("BLEService", "Request received for read on characteristic with UUID " + uuid.toString());
        }
        if (this.mConnectionState != 2) {
            Log.w("BLEService", "request read characteristic not initiated: device is disconnected.");
            return false;
        }
        BluetoothGattCharacteristic bluetoothGattCharacteristic = this.mCharacteristics.get(uuid);
        if (bluetoothGattCharacteristic == null) {
            Log.w("BLEService", "request read characteristic not initiated: characteristic not found for UUID " + uuid + ".");
            return false;
        }
        return requestReadCharacteristic(bluetoothGattCharacteristic);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean requestReadCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (this.mShowDebugLogs) {
            Log.d("BLEService", "Request received for read on characteristic with UUID " + bluetoothGattCharacteristic.getUuid().toString());
        }
        if (this.mConnectionState != 2) {
            Log.w("BLEService", "request read characteristic not initiated: device is disconnected.");
            return false;
        }
        if (bluetoothGattCharacteristic == null) {
            Log.w("BLEService", "request read characteristic not initiated: characteristic is null.");
            return false;
        }
        if (!this.mCharacteristics.containsKey(bluetoothGattCharacteristic.getUuid())) {
            Log.w("BLEService", "request read characteristic not initiated: unknown characteristic UUID.");
            return false;
        }
        if ((bluetoothGattCharacteristic.getProperties() & 2) <= 0) {
            Log.w("BLEService", "request read characteristic not initiated: characteristic does not have the READ property.");
            return false;
        }
        addToRequestsQueue(Request.createReadCharacteristicRequest(bluetoothGattCharacteristic));
        return true;
    }

    protected boolean requestReadCharacteristicForPairing(UUID uuid) {
        if (this.mShowDebugLogs) {
            Log.d("BLEService", "Request received for read to induce pairing on characteristic with UUID " + uuid.toString());
        }
        if (this.mConnectionState != 2) {
            Log.w("BLEService", "request read to induce pairing characteristic not initiated: device is disconnected.");
            return false;
        }
        BluetoothGattCharacteristic bluetoothGattCharacteristic = this.mCharacteristics.get(uuid);
        if (bluetoothGattCharacteristic == null) {
            Log.w("BLEService", "request read to induce pairing characteristic not initiated: characteristic not found for UUID " + uuid + ".");
            return false;
        }
        return requestReadCharacteristicForPairing(bluetoothGattCharacteristic);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean requestReadCharacteristicForPairing(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (this.mShowDebugLogs) {
            Log.d("BLEService", "Request received for read to induce pairing on characteristic with UUID " + bluetoothGattCharacteristic.getUuid().toString());
        }
        if (this.mConnectionState != 2) {
            Log.w("BLEService", "request read to induce pairing characteristic not initiated: device is disconnected.");
            return false;
        }
        if (bluetoothGattCharacteristic == null) {
            Log.w("BLEService", "request read to induce pairing characteristic not initiated: characteristic is null.");
            return false;
        }
        if (!this.mCharacteristics.containsKey(bluetoothGattCharacteristic.getUuid())) {
            Log.w("BLEService", "request read to induce pairing characteristic not initiated: unknown characteristic UUID.");
            return false;
        }
        if ((bluetoothGattCharacteristic.getProperties() & 2) <= 0) {
            Log.w("BLEService", "request read to induce pairing characteristic not initiated: characteristic does not have the READ property.");
            return false;
        }
        Request createReadCharacteristicRequestToInducePairing = Request.createReadCharacteristicRequestToInducePairing(bluetoothGattCharacteristic);
        createReadCharacteristicRequestToInducePairing.setAttempts(1);
        addToRequestsQueue(createReadCharacteristicRequestToInducePairing);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean requestReadDescriptor(BluetoothGattDescriptor bluetoothGattDescriptor) {
        if (this.mShowDebugLogs) {
            Log.d("BLEService", "Request received for read on descriptor with UUID " + bluetoothGattDescriptor.getUuid().toString());
        }
        if (this.mConnectionState != 2) {
            Log.w("BLEService", "request read on descriptor not initiated: device is disconnected.");
            return false;
        }
        if (bluetoothGattDescriptor == null) {
            Log.w("BLEService", "request read on descriptor not initiated: descriptor is null.");
            return false;
        }
        if (!this.mCharacteristics.containsKey(bluetoothGattDescriptor.getCharacteristic().getUuid())) {
            Log.w("BLEService", "request read on descriptor not initiated: unknown characteristic UUID.");
            return false;
        }
        addToRequestsQueue(Request.createReadDescriptorRequest(bluetoothGattDescriptor));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean requestReadRssi() {
        if (this.mShowDebugLogs) {
            Log.d("BLEService", "Request received for read RSSI level");
        }
        if (this.mConnectionState != 2) {
            Log.w("BLEService", "request read RSSI level not initiated: device is disconnected.");
            return false;
        }
        BluetoothDevice bluetoothDevice = this.mDevice;
        if (bluetoothDevice == null) {
            Log.w("BLEService", "request read RSSI level not initiated: device is null.");
            return false;
        }
        if (bluetoothDevice.getType() != 2) {
            Log.w("BLEService", "request read RSSI level not initiated: device is not LE only.");
            return false;
        }
        addToRequestsQueue(Request.createReadRssiRequest());
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean requestMtuSize(int i) {
        if (this.mShowDebugLogs) {
            Log.d("BLEService", "Request received for request MTU.");
        }
        if (this.mConnectionState != 2) {
            Log.w("BLEService", "request MTU not initiated: device is disconnected.");
            return false;
        }
        if (this.mDevice == null) {
            Log.w("BLEService", "request MTU not initiated: device is null.");
            return false;
        }
        if (i < 23 || i > 512) {
            Log.w("BLEService", "request MTU not initiated: value (" + i + ") not in interval [23, 512].");
            return false;
        }
        addToRequestsQueue(Request.createMtuRequest(i));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean initialize() {
        if (this.mShowDebugLogs) {
            Log.d("BLEService", "Request received for initialisation of the Bluetooth components");
        }
        if (this.mBluetoothAdapter != null && this.mShowDebugLogs) {
            Log.d("BLEService", "Bluetooth adapter already initialized");
            return true;
        }
        BluetoothManager bluetoothManager = (BluetoothManager) getSystemService("bluetooth");
        if (bluetoothManager == null) {
            Log.e("BLEService", "Initialisation of the Bluetooth Adapter failed: unable to initialize BluetoothManager.");
            return false;
        }
        BluetoothAdapter adapter = bluetoothManager.getAdapter();
        this.mBluetoothAdapter = adapter;
        if (adapter != null) {
            return true;
        }
        Log.e("BLEService", "Initialisation of the Bluetooth Adapter failed: unable to initialize BluetoothManager.");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getConnectionState() {
        return this.mConnectionState;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BluetoothDevice getDevice() {
        return this.mDevice;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getMtuSize() {
        return this.mMtuSize;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BluetoothGatt getBluetoothGatt() {
        return this.mBluetoothGatt;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean connectToDevice(String str) {
        if (this.mShowDebugLogs) {
            Log.d("BLEService", "Request received to connect to a device with address " + str);
        }
        if (!BluetoothAdapter.checkBluetoothAddress(str)) {
            Log.w("BLEService", "request connect to device not initiated: bluetooth address is unknown.");
            return false;
        }
        BluetoothDevice remoteDevice = this.mBluetoothAdapter.getRemoteDevice(str);
        if (remoteDevice == null) {
            Log.w("BLEService", "request connect to device not initiated: unable to get a BluetoothDevice from address " + str);
            return false;
        }
        return connectToDevice(remoteDevice);
    }

    protected boolean connectToDevice(BluetoothDevice bluetoothDevice) {
        if (this.mShowDebugLogs) {
            Log.d("BLEService", "Request received to connect to a BluetoothDevice");
        }
        if (bluetoothDevice == null) {
            Log.w("BLEService", "request connect to BluetoothDevice failed: device is null.");
            return false;
        }
        if (this.mConnectionState == 2) {
            Log.w("BLEService", "request connect to BluetoothDevice failed: a device is already connected.");
            return false;
        }
        if (this.mBluetoothAdapter == null) {
            Log.w("BLEService", "request connect to BluetoothDevice failed: no BluetoothAdapter initialized.");
            return false;
        }
        this.mDevice = bluetoothDevice;
        setState(1);
        Log.d("BLEService", "request connect to BluetoothDevice " + this.mDevice.getAddress() + " over GATT starts.");
        this.mBluetoothGatt = bluetoothDevice.connectGatt(this, false, this.mGattCallback, 2);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean reconnectToDevice() {
        if (this.mShowDebugLogs) {
            Log.d("BLEService", "Request received to reconnect to a BluetoothDevice");
        }
        if (this.mDevice == null) {
            Log.w("BLEService", "request reconnect to BluetoothDevice failed: device is null.");
            return false;
        }
        if (this.mConnectionState == 2) {
            Log.w("BLEService", "request reconnect to BluetoothDevice failed: a device is already connected.");
            return false;
        }
        if (this.mBluetoothAdapter == null) {
            Log.w("BLEService", "request reconnect to BluetoothDevice failed: no BluetoothAdapter initialized.");
            return false;
        }
        setState(1);
        Log.d("BLEService", "request reconnect to BluetoothDevice " + this.mDevice.getAddress() + " over GATT starts.");
        this.mBluetoothGatt = this.mDevice.connectGatt(this, true, this.mGattCallback, 2);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void disconnectFromDevice() {
        resetQueue();
        this.mCharacteristics.clear();
        if (this.mShowDebugLogs) {
            Log.d("BLEService", "Request received to disconnect from a BluetoothDevice");
        }
        if (this.mBluetoothAdapter == null) {
            Log.i("BLEService", "request disconnect from BluetoothDevice: BluetoothAdapter is null.");
            setState(0);
        } else if (this.mBluetoothGatt == null) {
            Log.i("BLEService", "request disconnect from BluetoothDevice: BluetoothGatt is null.");
            setState(0);
        } else {
            Log.i("BLEService", "Request disconnect from BluetoothDevice " + this.mBluetoothGatt.getDevice().getAddress() + " starts.");
            setState(3);
            this.mBluetoothGatt.disconnect();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public synchronized void setState(int i) {
        if (this.mShowDebugLogs) {
            Log.d("BLEService", "Connection state changes from " + BLEUtils.getConnectionStateName(this.mConnectionState) + " to " + BLEUtils.getConnectionStateName(i));
        }
        this.mConnectionState = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void receiveConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
        if (this.mShowDebugLogs) {
            Log.d("BLEService", "GattCallback - onConnectionStateChange, newState=" + i2 + ", status=" + i);
        }
        if (i == 0 && i2 == 2) {
            setState(2);
            Log.i("BLEService", "Successful connection to device: " + bluetoothGatt.getDevice().getAddress());
            if (this.mBluetoothGatt == null) {
                this.mBluetoothGatt = bluetoothGatt;
            }
        } else if (i2 == 0) {
            if (this.mConnectionState == 3) {
                Log.i("BLEService", "Successful disconnection from device: " + bluetoothGatt.getDevice().getAddress());
            } else {
                Log.i("BLEService", "Disconnected from device: " + bluetoothGatt.getDevice().getAddress());
            }
            setState(0);
            resetQueue();
            this.mCharacteristics.clear();
            if (this.mShowDebugLogs) {
                Log.d("BLEService", "Device disconnected, closing BluetoothGatt object.");
            }
            bluetoothGatt.close();
            this.mBluetoothGatt = null;
        }
        onConnectionStateChange(bluetoothGatt, i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void receiveServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
        if (this.mShowDebugLogs) {
            Log.d("BLEService", "GattCallback - onServicesDiscovered, status=" + i);
        }
        if (i == 0) {
            Iterator<BluetoothGattService> it = bluetoothGatt.getServices().iterator();
            while (it.hasNext()) {
                for (BluetoothGattCharacteristic bluetoothGattCharacteristic : it.next().getCharacteristics()) {
                    this.mCharacteristics.put(bluetoothGattCharacteristic.getUuid(), bluetoothGattCharacteristic);
                }
            }
        } else {
            Log.w("BLEService", "Unsuccessful status for GATT Services discovery on callback: " + BLEUtils.getGattStatusName(i, false));
        }
        processNextRequest();
        onServicesDiscovered(bluetoothGatt, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0034, code lost:
    
        if (r0.request.getType() == 6) goto L11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void receiveCharacteristicRead(android.bluetooth.BluetoothGatt r7, android.bluetooth.BluetoothGattCharacteristic r8, int r9) {
        /*
            r6 = this;
            boolean r0 = r6.mShowDebugLogs
            java.lang.String r1 = "BLEService"
            if (r0 == 0) goto L26
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "GattCallback - onCharacteristicRead, characteristic="
            r0.<init>(r2)
            java.util.UUID r2 = r8.getUuid()
            java.lang.StringBuilder r0 = r0.append(r2)
            java.lang.String r2 = "status="
            java.lang.StringBuilder r0 = r0.append(r2)
            java.lang.StringBuilder r0 = r0.append(r9)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r1, r0)
        L26:
            cordova.plugins.qcc.BTLibrary.libraries.ble.BLEService$TimeOutRequestRunnable r0 = r6.mTimeOutRequestRunnable
            r2 = 1
            if (r0 == 0) goto L37
            cordova.plugins.qcc.BTLibrary.libraries.ble.Request r0 = cordova.plugins.qcc.BTLibrary.libraries.ble.BLEService.TimeOutRequestRunnable.m750$$Nest$fgetrequest(r0)
            int r0 = r0.getType()
            r3 = 6
            if (r0 != r3) goto L37
            goto L38
        L37:
            r3 = r2
        L38:
            cordova.plugins.qcc.BTLibrary.libraries.ble.Request r0 = r6.onReceiveCallback(r3, r8)
            r3 = 0
            if (r0 == 0) goto L40
            goto L41
        L40:
            r2 = r3
        L41:
            if (r9 == 0) goto L71
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "Unsuccessful read characteristic for characteristic "
            r4.<init>(r5)
            java.util.UUID r5 = r8.getUuid()
            java.lang.String r5 = r5.toString()
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r5 = " - status: "
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r3 = cordova.plugins.qcc.BTLibrary.libraries.ble.BLEUtils.getGattStatusName(r9, r3)
            java.lang.StringBuilder r3 = r4.append(r3)
            java.lang.String r3 = r3.toString()
            android.util.Log.w(r1, r3)
            if (r2 == 0) goto L76
            r6.onRequestFailed(r0)
            goto L76
        L71:
            if (r2 == 0) goto L76
            r6.processNextRequest()
        L76:
            r6.onCharacteristicRead(r7, r8, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cordova.plugins.qcc.BTLibrary.libraries.ble.BLEService.receiveCharacteristicRead(android.bluetooth.BluetoothGatt, android.bluetooth.BluetoothGattCharacteristic, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void receiveRemoteRssiRead(BluetoothGatt bluetoothGatt, int i, int i2) {
        if (this.mShowDebugLogs) {
            Log.d("BLEService", "GattCallback - onRemoteRssiRead, rssi=" + i + " status=" + i2);
        }
        Request onReceiveCallback = onReceiveCallback(7);
        boolean z = onReceiveCallback != null;
        if (i2 != 0) {
            Log.w("BLEService", "Unsuccessful remote rssi read - status: " + BLEUtils.getGattStatusName(i2, false));
            if (z) {
                onRequestFailed(onReceiveCallback);
            }
        } else if (z) {
            processNextRequest();
        }
        onRemoteRssiRead(bluetoothGatt, i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void receiveMtuChanged(BluetoothGatt bluetoothGatt, int i, int i2) {
        if (this.mShowDebugLogs) {
            Log.d("BLEService", "GattCallback - onMtuChanged, mtu=" + i + " status=" + i2);
        }
        Request onReceiveCallback = onReceiveCallback(8);
        boolean z = onReceiveCallback != null;
        if (i2 != 0) {
            Log.w("BLEService", "Unsuccessful MTU request - status: " + BLEUtils.getGattStatusName(i2, false));
            if (z) {
                onRequestFailed(onReceiveCallback);
            }
        } else if (z) {
            processNextRequest();
        }
        this.mMtuSize = i;
        onMtuChanged(bluetoothGatt, i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void receiveDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
        if (this.mShowDebugLogs) {
            Log.d("BLEService", "GattCallback - onDescriptorWrite, descriptor=" + bluetoothGattDescriptor.getUuid() + "status=" + i);
        }
        Request onReceiveCallback = onReceiveCallback(5, bluetoothGattDescriptor);
        boolean z = onReceiveCallback != null;
        if (i != 0) {
            Log.w("BLEService", "Unsuccessful write descriptor for characteristic " + bluetoothGattDescriptor.getCharacteristic().getUuid().toString() + " - status: " + BLEUtils.getGattStatusName(i, false));
            if (z) {
                onRequestFailed(onReceiveCallback);
            }
        } else if (z) {
            processNextRequest();
        }
        onDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void receiveDescriptorRead(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
        if (this.mShowDebugLogs) {
            Log.d("BLEService", "GattCallback - onDescriptorRead, descriptor=" + bluetoothGattDescriptor.getUuid() + "status=" + i);
        }
        Request onReceiveCallback = onReceiveCallback(4, bluetoothGattDescriptor);
        boolean z = onReceiveCallback != null;
        if (i != 0) {
            Log.w("BLEService", "Unsuccessful read descriptor for characteristic " + bluetoothGattDescriptor.getCharacteristic().getUuid().toString() + " - status: " + BLEUtils.getGattStatusName(i, false));
            if (z) {
                onRequestFailed(onReceiveCallback);
            }
        } else if (z) {
            processNextRequest();
        }
        onDescriptorRead(bluetoothGatt, bluetoothGattDescriptor, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0033, code lost:
    
        if (r0.request.getType() == 3) goto L11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void receiveCharacteristicWrite(android.bluetooth.BluetoothGatt r7, android.bluetooth.BluetoothGattCharacteristic r8, int r9) {
        /*
            r6 = this;
            boolean r0 = r6.mShowDebugLogs
            java.lang.String r1 = "BLEService"
            if (r0 == 0) goto L26
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "GattCallback - onCharacteristicWrite, characteristic="
            r0.<init>(r2)
            java.util.UUID r2 = r8.getUuid()
            java.lang.StringBuilder r0 = r0.append(r2)
            java.lang.String r2 = "status="
            java.lang.StringBuilder r0 = r0.append(r2)
            java.lang.StringBuilder r0 = r0.append(r9)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r1, r0)
        L26:
            cordova.plugins.qcc.BTLibrary.libraries.ble.BLEService$TimeOutRequestRunnable r0 = r6.mTimeOutRequestRunnable
            if (r0 == 0) goto L36
            cordova.plugins.qcc.BTLibrary.libraries.ble.Request r0 = cordova.plugins.qcc.BTLibrary.libraries.ble.BLEService.TimeOutRequestRunnable.m750$$Nest$fgetrequest(r0)
            int r0 = r0.getType()
            r2 = 3
            if (r0 != r2) goto L36
            goto L37
        L36:
            r2 = 2
        L37:
            cordova.plugins.qcc.BTLibrary.libraries.ble.Request r0 = r6.onReceiveCallback(r2, r8)
            r2 = 0
            if (r0 == 0) goto L40
            r3 = 1
            goto L41
        L40:
            r3 = r2
        L41:
            if (r9 == 0) goto L71
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "Unsuccessful write characteristic for characteristic "
            r4.<init>(r5)
            java.util.UUID r5 = r8.getUuid()
            java.lang.String r5 = r5.toString()
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r5 = " - status: "
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r2 = cordova.plugins.qcc.BTLibrary.libraries.ble.BLEUtils.getGattStatusName(r9, r2)
            java.lang.StringBuilder r2 = r4.append(r2)
            java.lang.String r2 = r2.toString()
            android.util.Log.w(r1, r2)
            if (r3 == 0) goto L76
            r6.onRequestFailed(r0)
            goto L76
        L71:
            if (r3 == 0) goto L76
            r6.processNextRequest()
        L76:
            r6.onCharacteristicWrite(r7, r8, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cordova.plugins.qcc.BTLibrary.libraries.ble.BLEService.receiveCharacteristicWrite(android.bluetooth.BluetoothGatt, android.bluetooth.BluetoothGattCharacteristic, int):void");
    }

    private boolean readCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (this.mShowDebugLogs) {
            Log.d("BLEService", "Process request read characteristic for characteristic " + bluetoothGattCharacteristic.getUuid());
        }
        if (this.mBluetoothAdapter == null) {
            Log.w("BLEService", "Read characteristic cannot be processed: BluetoothAdapter is null.");
            return false;
        }
        BluetoothGatt bluetoothGatt = this.mBluetoothGatt;
        if (bluetoothGatt == null) {
            Log.w("BLEService", "Read characteristic cannot be processed: BluetoothGatt is null.");
            return false;
        }
        boolean readCharacteristic = bluetoothGatt.readCharacteristic(bluetoothGattCharacteristic);
        if (this.mShowDebugLogs) {
            Log.d("BLEService", "Request read characteristic dispatched to system: " + readCharacteristic);
        }
        return readCharacteristic;
    }

    private boolean setCharacteristicNotification(BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z) {
        if (this.mShowDebugLogs) {
            Log.d("BLEService", "Process request set characteristic notification for characteristic " + bluetoothGattCharacteristic.getUuid() + " with enabled=" + z);
        }
        if (this.mBluetoothAdapter == null) {
            Log.w("BLEService", "Set characteristic notification cannot be processed: BluetoothAdapter is null.");
            return false;
        }
        BluetoothGatt bluetoothGatt = this.mBluetoothGatt;
        if (bluetoothGatt == null) {
            Log.w("BLEService", "Set characteristic notification cannot be processed: BluetoothGatt is null.");
            return false;
        }
        boolean characteristicNotification = bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, z);
        if (this.mShowDebugLogs) {
            Log.d("BLEService", "Request set characteristic notification dispatched to system: " + characteristicNotification);
        }
        return characteristicNotification;
    }

    private boolean readDescriptor(BluetoothGattDescriptor bluetoothGattDescriptor) {
        if (this.mShowDebugLogs) {
            Log.d("BLEService", "Process request read descriptor for descriptor " + bluetoothGattDescriptor.getUuid());
        }
        if (this.mBluetoothAdapter == null) {
            Log.w("BLEService", "Read descriptor cannot be processed: BluetoothAdapter is null.");
            return false;
        }
        BluetoothGatt bluetoothGatt = this.mBluetoothGatt;
        if (bluetoothGatt == null) {
            Log.w("BLEService", "Read descriptor cannot be processed: BluetoothGatt is null.");
            return false;
        }
        boolean readDescriptor = bluetoothGatt.readDescriptor(bluetoothGattDescriptor);
        if (this.mShowDebugLogs) {
            Log.d("BLEService", "Request read descriptor dispatched to system: " + readDescriptor);
        }
        return readDescriptor;
    }

    private boolean writeDescriptor(BluetoothGattDescriptor bluetoothGattDescriptor) {
        if (this.mShowDebugLogs) {
            Log.d("BLEService", "Process request write descriptor for descriptor " + bluetoothGattDescriptor.getUuid());
        }
        if (this.mBluetoothAdapter == null) {
            Log.w("BLEService", "Write descriptor cannot be processed: BluetoothAdapter is null.");
            return false;
        }
        BluetoothGatt bluetoothGatt = this.mBluetoothGatt;
        if (bluetoothGatt == null) {
            Log.w("BLEService", "Write descriptor cannot be processed: BluetoothGatt is null.");
            return false;
        }
        boolean writeDescriptor = bluetoothGatt.writeDescriptor(bluetoothGattDescriptor);
        if (this.mShowDebugLogs) {
            Log.d("BLEService", "Request write descriptor dispatched to system: " + writeDescriptor);
        }
        return writeDescriptor;
    }

    private boolean writeCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (this.mShowDebugLogs) {
            Log.d("BLEService", "Process request write characteristic for characteristic " + bluetoothGattCharacteristic.getUuid());
        }
        if (this.mBluetoothAdapter == null) {
            Log.w("BLEService", "Write characteristic cannot be processed: BluetoothAdapter is null.");
            return false;
        }
        BluetoothGatt bluetoothGatt = this.mBluetoothGatt;
        if (bluetoothGatt == null) {
            Log.w("BLEService", "Write characteristic cannot be processed: BluetoothGatt is null.");
            return false;
        }
        boolean writeCharacteristic = bluetoothGatt.writeCharacteristic(bluetoothGattCharacteristic);
        if (this.mShowDebugLogs) {
            Log.d("BLEService", "Request write characteristic dispatched to system: " + writeCharacteristic);
        }
        return writeCharacteristic;
    }

    private boolean readRemoteRssi() {
        if (this.mShowDebugLogs) {
            Log.d("BLEService", "Process read remote RSSI");
        }
        if (this.mBluetoothAdapter == null) {
            Log.w("BLEService", "Read remote RSSI cannot be processed: BluetoothAdapter is null.");
            return false;
        }
        BluetoothGatt bluetoothGatt = this.mBluetoothGatt;
        if (bluetoothGatt == null) {
            Log.w("BLEService", "Read remote RSSI cannot be processed: BluetoothGatt is null.");
            return false;
        }
        boolean readRemoteRssi = bluetoothGatt.readRemoteRssi();
        if (this.mShowDebugLogs) {
            Log.d("BLEService", "Request read remote RSSI dispatched to system: " + readRemoteRssi);
        }
        return readRemoteRssi;
    }

    private boolean requestMTU(int i) {
        if (this.mShowDebugLogs) {
            Log.d("BLEService", "Process request MTU");
        }
        if (this.mBluetoothAdapter == null) {
            Log.w("BLEService", "Request MTU cannot be processed: BluetoothAdapter is null.");
            return false;
        }
        if (this.mBluetoothGatt == null) {
            Log.w("BLEService", "Request MTU cannot be processed: BluetoothGatt is null.");
            return false;
        }
        boolean requestMtu = this.mBluetoothGatt.requestMtu(i);
        if (this.mShowDebugLogs) {
            Log.d("BLEService", "Request read remote RSSI dispatched to system: " + requestMtu);
        }
        return requestMtu;
    }

    protected List<BluetoothGattService> getSupportedGattServices() {
        BluetoothGatt bluetoothGatt = this.mBluetoothGatt;
        if (bluetoothGatt == null) {
            Log.w("BLEService", "getSupportedGattServices() - BluetoothGatt is null.");
            return null;
        }
        return bluetoothGatt.getServices();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onRequestFailed(Request request) {
        if (request != null && request.getAttempts() < 2) {
            addToRequestsQueue(request);
        } else if (request != null) {
            Log.w("BLEService", "Request " + Request.getRequestTypeLabel(request.getType()) + " failed");
            if (request.getType() == 6 && this.mDevice.getBondState() == 10) {
                Log.i("BLEService", "Induce pairing by creating bond manually.");
                this.mDevice.createBond();
            }
        } else {
            Log.w("BLEService", "An unknown request failed (null request object).");
        }
        processNextRequest();
    }

    private Request onReceiveCallback(int i, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        TimeOutRequestRunnable timeOutRequestRunnable = this.mTimeOutRequestRunnable;
        if (timeOutRequestRunnable != null && timeOutRequestRunnable.request.getType() == i && bluetoothGattCharacteristic != null && this.mTimeOutRequestRunnable.request.getCharacteristic() != null && this.mTimeOutRequestRunnable.request.getCharacteristic().getUuid().equals(bluetoothGattCharacteristic.getUuid())) {
            Request request = this.mTimeOutRequestRunnable.request;
            cancelTimeOutRequestRunnable();
            return request;
        }
        Log.w("BLEService", "Received unexpected callback for characteristic " + (bluetoothGattCharacteristic != null ? bluetoothGattCharacteristic.getUuid() : "null") + " with request type = " + Request.getRequestTypeLabel(i));
        return null;
    }

    private Request onReceiveCallback(int i) {
        TimeOutRequestRunnable timeOutRequestRunnable = this.mTimeOutRequestRunnable;
        if (timeOutRequestRunnable != null && timeOutRequestRunnable.request.getType() == i) {
            Request request = this.mTimeOutRequestRunnable.request;
            cancelTimeOutRequestRunnable();
            return request;
        }
        Log.w("BLEService", "Received unexpected callback for request type = " + Request.getRequestTypeLabel(i));
        return null;
    }

    private Request onReceiveCallback(int i, BluetoothGattDescriptor bluetoothGattDescriptor) {
        TimeOutRequestRunnable timeOutRequestRunnable = this.mTimeOutRequestRunnable;
        if (timeOutRequestRunnable != null && timeOutRequestRunnable.request.getType() == i && bluetoothGattDescriptor != null && this.mTimeOutRequestRunnable.request.getDescriptor() != null && this.mTimeOutRequestRunnable.request.getDescriptor().getUuid().equals(bluetoothGattDescriptor.getUuid())) {
            Request request = this.mTimeOutRequestRunnable.request;
            cancelTimeOutRequestRunnable();
            return request;
        }
        Log.w("BLEService", "Received unexpected callback for descriptor " + (bluetoothGattDescriptor != null ? bluetoothGattDescriptor.getUuid() : "null") + " with request type = " + Request.getRequestTypeLabel(i));
        return null;
    }

    private void addToRequestsQueue(Request request) {
        synchronized (this.mRequestsQueue) {
            if (request.getAttempts() < 2) {
                if (this.mShowDebugLogs) {
                    Log.d("BLEService", "Add request of type " + Request.getRequestTypeLabel(request.getType()) + "to the Queue of requests to process.");
                }
                this.mRequestsQueue.add(request);
            } else {
                Log.w("BLEService", "Request " + Request.getRequestTypeLabel(request.getType()) + " failed after " + request.getAttempts() + " attempts.");
            }
            if (!this.isQueueProcessing) {
                processNextRequest();
            }
        }
    }

    private void resetQueue() {
        synchronized (this.mRequestsQueue) {
            if (this.mShowDebugLogs) {
                Log.d("BLEService", "Reset the Queue of requests to process.");
            }
            this.mRequestsQueue.clear();
            this.isQueueProcessing = false;
            cancelTimeOutRequestRunnable();
        }
    }

    private void cancelTimeOutRequestRunnable() {
        TimeOutRequestRunnable timeOutRequestRunnable = this.mTimeOutRequestRunnable;
        if (timeOutRequestRunnable != null) {
            this.mHandler.removeCallbacks(timeOutRequestRunnable);
            this.mTimeOutRequestRunnable = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to find 'out' block for switch in B:24:0x0054. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:27:0x013b A[Catch: all -> 0x0163, TryCatch #0 {, blocks: (B:5:0x0008, B:7:0x000e, B:10:0x0010, B:12:0x0019, B:13:0x001b, B:15:0x001d, B:17:0x0022, B:18:0x0025, B:20:0x0027, B:22:0x0036, B:23:0x0050, B:24:0x0054, B:27:0x013b, B:28:0x0161, B:30:0x0059, B:31:0x0072, B:32:0x0087, B:34:0x009c, B:39:0x00a4, B:41:0x00b9, B:44:0x00c1, B:46:0x00d6, B:49:0x00dd, B:51:0x00f2, B:54:0x00f9, B:56:0x010e, B:59:0x0115, B:61:0x012a, B:64:0x0134, B:65:0x0137), top: B:4:0x0008 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void processNextRequest() {
        /*
            Method dump skipped, instructions count: 380
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cordova.plugins.qcc.BTLibrary.libraries.ble.BLEService.processNextRequest():void");
    }

    private void processNotificationCharacteristicRequest(Request request) {
        BluetoothGattCharacteristic buildNotifyCharacteristic = request.buildNotifyCharacteristic();
        boolean z = buildNotifyCharacteristic != null && setCharacteristicNotification(buildNotifyCharacteristic, request.getBooleanData());
        if (!z && request.getAttempts() < 2) {
            addToRequestsQueue(request);
            processNextRequest();
        } else {
            if (z) {
                this.mHandler.postDelayed(new Runnable() { // from class: cordova.plugins.qcc.BTLibrary.libraries.ble.BLEService.2
                    @Override // java.lang.Runnable
                    public void run() {
                        BLEService.this.processNextRequest();
                    }
                }, 1000L);
                return;
            }
            request.setAttempts(2);
            TimeOutRequestRunnable timeOutRequestRunnable = new TimeOutRequestRunnable(request);
            this.mTimeOutRequestRunnable = timeOutRequestRunnable;
            this.mHandler.postDelayed(timeOutRequestRunnable, 1000L);
        }
    }

    public void setStateToDisconnected() {
        receiveConnectionStateChange(this.mBluetoothGatt, 0, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class TimeOutRequestRunnable implements Runnable {
        private final Request request;

        TimeOutRequestRunnable(Request request) {
            this.request = request;
        }

        @Override // java.lang.Runnable
        public void run() {
            BLEService.this.mTimeOutRequestRunnable = null;
            Log.w("BLEService", "Request " + Request.getRequestTypeLabel(this.request.getType()) + ": TIME OUT");
            BLEService.this.onRequestFailed(this.request);
        }
    }
}
