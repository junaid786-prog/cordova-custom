package cordova.plugins.qcc.BTLibrary.services;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.ParcelUuid;
import android.util.Log;
import cordova.plugins.qcc.BTLibrary.Utils;
import cordova.plugins.qcc.BTLibrary.receivers.UUIDReceiver;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.UUID;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public abstract class BREDRProvider implements UUIDReceiver.UUIDListener {
    private static final long UNREGISTER_UUID_RECEIVER_DELAY_MS = 5000;
    private final BluetoothAdapter mBluetoothAdapter;
    private UUID mUUIDTransport;
    private final String TAG = "BREDRProvider";
    private BluetoothDevice mDevice = null;
    private ConnectionThread mConnectionThread = null;
    private CommunicationThread mCommunicationThread = null;
    private int mState = 4;
    private boolean mShowDebugLogs = false;
    private Handler mHandler = new Handler();
    private boolean isWaitingForUUIDs = false;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    @interface Errors {
        public static final int CONNECTION_FAILED = 0;
        public static final int CONNECTION_LOST = 1;
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    @interface State {
        public static final int CONNECTED = 2;
        public static final int CONNECTING = 1;
        public static final int DISCONNECTED = 0;
        public static final int DISCONNECTING = 3;
        public static final int NO_STATE = 4;
    }

    private boolean btIsSecure() {
        return true;
    }

    private static String getConnectionStateName(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? "UNKNOWN" : "NO STATE" : "DISCONNECTING" : "CONNECTED" : "CONNECTING" : "DISCONNECTED";
    }

    abstract void onCommunicationRunning();

    abstract void onConnectionError(int i);

    abstract void onConnectionStateChanged(int i);

    abstract void onDataFound(byte[] bArr);

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class UUIDs {
        private static final UUID SPP = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
        private static final UUID GAIA = UUID.fromString("00001107-D102-11E1-9B23-00025B00A5A5");

        private UUIDs() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BREDRProvider(BluetoothManager bluetoothManager) {
        if (this.mShowDebugLogs) {
            Log.d("BREDRProvider", "Creation of a new instance of BREDRProvider: " + this);
        }
        if (bluetoothManager == null) {
            this.mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            Log.i("BREDRProvider", "No available BluetoothManager, BluetoothAdapter initialised with BluetoothAdapter.getDefaultAdapter.");
        } else {
            this.mBluetoothAdapter = bluetoothManager.getAdapter();
        }
        if (this.mBluetoothAdapter == null) {
            Log.e("BREDRProvider", "Initialisation of the Bluetooth Adapter failed: unable to initialize BluetoothAdapter.");
        }
    }

    @Override // cordova.plugins.qcc.BTLibrary.receivers.UUIDReceiver.UUIDListener
    public void onUUIDFound(BluetoothDevice bluetoothDevice, ParcelUuid[] parcelUuidArr) {
        if (!this.isWaitingForUUIDs || parcelUuidArr == null || parcelUuidArr.length <= 0 || bluetoothDevice == null) {
            return;
        }
        UUID uUIDTransport = getUUIDTransport(parcelUuidArr);
        if (uUIDTransport == null) {
            Log.w("BREDRProvider", "UUIDs found but nothing to match");
        } else {
            this.isWaitingForUUIDs = false;
            connect(bluetoothDevice, uUIDTransport);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void showDebugLogs(boolean z) {
        this.mShowDebugLogs = z;
        Log.i("BREDRProvider", "Debug logs are now " + (z ? "activated" : "deactivated") + ".");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BluetoothDevice getDevice() {
        return this.mDevice;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean connect(String str, Context context) {
        if (this.mShowDebugLogs) {
            Log.d("BREDRProvider", "Request received to connect to a device with address " + str);
        }
        if (str == null) {
            Log.w("BREDRProvider", "connection failed: Bluetooth address is null.");
            return false;
        }
        if (str.length() == 0) {
            Log.w("BREDRProvider", "connection failed: Bluetooth address null or empty.");
            return false;
        }
        if (!isBluetoothAvailable()) {
            Log.w("BREDRProvider", "connection failed: unable to get the adapter to get the device object from BT address.");
            return false;
        }
        if (!BluetoothAdapter.checkBluetoothAddress(str)) {
            Log.w("BREDRProvider", "connection failed: unknown BT address.");
            return false;
        }
        BluetoothDevice remoteDevice = this.mBluetoothAdapter.getRemoteDevice(str);
        if (remoteDevice == null) {
            Log.w("BREDRProvider", "connection failed: get device from BT address failed.");
            return false;
        }
        return connect(remoteDevice, context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean reconnectToDevice(Context context) {
        BluetoothDevice bluetoothDevice = this.mDevice;
        return bluetoothDevice != null && connect(bluetoothDevice, context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean disconnect() {
        if (this.mShowDebugLogs) {
            StringBuilder sb = new StringBuilder("Receives request to disconnect from device ");
            BluetoothDevice bluetoothDevice = this.mDevice;
            Log.d("BREDRProvider", sb.append(bluetoothDevice != null ? bluetoothDevice.getAddress() : "null").toString());
        }
        if (this.mState == 0) {
            Log.w("BREDRProvider", "disconnection failed: no device connected.");
            return false;
        }
        setState(3);
        cancelConnectionThread();
        cancelCommunicationThread();
        setState(0);
        StringBuilder sb2 = new StringBuilder("Provider disconnected from BluetoothDevice ");
        BluetoothDevice bluetoothDevice2 = this.mDevice;
        Log.i("BREDRProvider", sb2.append(bluetoothDevice2 != null ? bluetoothDevice2.getAddress() : "null").toString());
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean sendData(byte[] bArr) {
        if (this.mShowDebugLogs) {
            Log.d("BREDRProvider", "Request received for sending data to a device.");
        }
        synchronized (this) {
            if (this.mState != 2) {
                Log.w("BREDRProvider", "Attempt to send data failed: provider not currently connected to a device.");
                return false;
            }
            CommunicationThread communicationThread = this.mCommunicationThread;
            if (communicationThread == null) {
                Log.w("BREDRProvider", "Attempt to send data failed: CommunicationThread is null.");
                return false;
            }
            return communicationThread.sendStream(bArr);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isGaiaReady() {
        CommunicationThread communicationThread;
        return this.mState == 2 && (communicationThread = this.mCommunicationThread) != null && communicationThread.mmIsRunning;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized int getState() {
        return this.mState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void setState(int i) {
        if (this.mShowDebugLogs) {
            Log.d("BREDRProvider", "Connection state changes from " + getConnectionStateName(this.mState) + " to " + getConnectionStateName(i));
        }
        this.mState = i;
        onConnectionStateChanged(i);
    }

    private UUID getUUIDTransport(ParcelUuid[] parcelUuidArr) {
        if (parcelUuidArr == null) {
            return null;
        }
        for (ParcelUuid parcelUuid : parcelUuidArr) {
            UUID uuid = parcelUuid.getUuid();
            if (checkUUID(uuid)) {
                return uuid;
            }
        }
        return null;
    }

    private boolean checkUUID(UUID uuid) {
        return uuid.equals(UUIDs.SPP) || uuid.equals(UUIDs.GAIA);
    }

    private void cancelConnectionThread() {
        ConnectionThread connectionThread = this.mConnectionThread;
        if (connectionThread != null) {
            connectionThread.cancel();
            this.mConnectionThread = null;
        }
    }

    private void cancelCommunicationThread() {
        CommunicationThread communicationThread = this.mCommunicationThread;
        if (communicationThread != null) {
            communicationThread.cancel();
            this.mCommunicationThread = null;
        }
    }

    private boolean connect(BluetoothDevice bluetoothDevice, Context context) {
        if (this.mShowDebugLogs) {
            Log.d("BREDRProvider", "Request received to connect to a BluetoothDevice " + bluetoothDevice.getAddress());
        }
        if (this.mState == 2) {
            Log.w("BREDRProvider", "connection failed: a device is already connected");
            return false;
        }
        if (bluetoothDevice.getType() != 1 && bluetoothDevice.getType() != 3) {
            Log.w("BREDRProvider", "connection failed: the device is not BR/EDR compatible.");
            return false;
        }
        if (!isBluetoothAvailable()) {
            Log.w("BREDRProvider", "connection failed: Bluetooth is not available.");
            return false;
        }
        if (!BluetoothAdapter.checkBluetoothAddress(bluetoothDevice.getAddress())) {
            Log.w("BREDRProvider", "connection failed: device address not found in list of devices known by the system.");
            return false;
        }
        ParcelUuid[] uuids = bluetoothDevice.getUuids();
        if (uuids == null) {
            Log.i("BREDRProvider", "No UUIDs found, starting fetch UUIDS with SDP procedure.");
            fetchUuidsWithSdp(bluetoothDevice, context);
            return true;
        }
        UUID uUIDTransport = getUUIDTransport(uuids);
        if (uUIDTransport == null && bluetoothDevice.getBondState() != 12) {
            Log.i("BREDRProvider", "connection: device not bonded, no UUID available, attempt to connect using SPP.");
            uUIDTransport = UUIDs.SPP;
        } else if (uUIDTransport == null) {
            Log.w("BREDRProvider", "connection failed: device bonded and no compatible UUID available.");
            return false;
        }
        return connect(bluetoothDevice, uUIDTransport);
    }

    private boolean connect(BluetoothDevice bluetoothDevice, UUID uuid) {
        if (this.mShowDebugLogs) {
            Log.d("BREDRProvider", "Request received to connect to a BluetoothDevice with UUID " + uuid.toString());
        }
        if (this.mState == 2 && this.mCommunicationThread != null) {
            Log.w("BREDRProvider", "connection failed: Provider is already connected to a device with an active communication.");
            return false;
        }
        cancelConnectionThread();
        cancelCommunicationThread();
        setState(1);
        BluetoothSocket createSocket = createSocket(bluetoothDevice, uuid);
        if (createSocket == null) {
            Log.w("BREDRProvider", "connection failed: creation of a Bluetooth socket failed.");
            return false;
        }
        if (this.mShowDebugLogs) {
            Log.d("BREDRProvider", "Request connect to BluetoothDevice " + createSocket.getRemoteDevice().getAddress() + " over RFCOMM starts.");
        }
        this.mUUIDTransport = uuid;
        this.mDevice = bluetoothDevice;
        ConnectionThread connectionThread = new ConnectionThread(createSocket);
        this.mConnectionThread = connectionThread;
        connectionThread.start();
        return true;
    }

    private void fetchUuidsWithSdp(BluetoothDevice bluetoothDevice, final Context context) {
        this.isWaitingForUUIDs = true;
        final UUIDReceiver uUIDReceiver = new UUIDReceiver(this, bluetoothDevice);
        context.registerReceiver(uUIDReceiver, new IntentFilter("android.bluetooth.device.action.UUID"));
        bluetoothDevice.fetchUuidsWithSdp();
        this.mHandler.postDelayed(new Runnable() { // from class: cordova.plugins.qcc.BTLibrary.services.BREDRProvider.1
            @Override // java.lang.Runnable
            public void run() {
                context.unregisterReceiver(uUIDReceiver);
                if (BREDRProvider.this.isWaitingForUUIDs && BREDRProvider.this.getState() == 1) {
                    BREDRProvider.this.isWaitingForUUIDs = false;
                    Log.e("BREDRProvider", "Connection failed: no corresponding UUID found.");
                    BREDRProvider.this.setState(0);
                    BREDRProvider.this.onConnectionError(0);
                }
            }
        }, 5000L);
    }

    private boolean isBluetoothAvailable() {
        return this.mBluetoothAdapter != null;
    }

    private BluetoothSocket createSocket(BluetoothDevice bluetoothDevice, UUID uuid) {
        if (this.mShowDebugLogs) {
            Log.d("BREDRProvider", "Creating Bluetooth socket for device " + bluetoothDevice.getAddress() + " using UUID " + uuid);
        }
        try {
            if (btIsSecure()) {
                return bluetoothDevice.createInsecureRfcommSocketToServiceRecord(uuid);
            }
            return bluetoothDevice.createRfcommSocketToServiceRecord(uuid);
        } catch (IOException e) {
            Log.w("BREDRProvider", "Exception occurs while creating Bluetooth socket: " + e.toString());
            Log.i("BREDRProvider", "Attempting to invoke method to create Bluetooth Socket.");
            try {
                return (BluetoothSocket) bluetoothDevice.getClass().getMethod("createRfcommSocket", Integer.TYPE).invoke(bluetoothDevice, 1);
            } catch (Exception unused) {
                Log.w("BREDRProvider", "Exception occurs while creating Bluetooth socket by invoking method: " + e.toString());
                return null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onConnectionFailed() {
        setState(0);
        onConnectionError(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onConnectionLost() {
        setState(0);
        onConnectionError(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSocketConnected(BluetoothSocket bluetoothSocket) {
        Log.i("BREDRProvider", "Successful connection to device: " + getDevice().getAddress());
        if (this.mShowDebugLogs) {
            Log.d("BREDRProvider", "Initialisation of ongoing communication by creating and running a CommunicationThread.");
        }
        cancelConnectionThread();
        cancelCommunicationThread();
        setState(2);
        CommunicationThread communicationThread = new CommunicationThread(bluetoothSocket);
        this.mCommunicationThread = communicationThread;
        communicationThread.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class ConnectionThread extends Thread {
        private final String THREAD_TAG;
        private final BluetoothSocket mmConnectorSocket;

        private ConnectionThread(BluetoothSocket bluetoothSocket) {
            this.THREAD_TAG = "ConnectionThread";
            setName("ConnectionThread" + getId());
            this.mmConnectorSocket = bluetoothSocket;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                if (BREDRProvider.this.mShowDebugLogs) {
                    Log.d("ConnectionThread", "Attempt to connect device over BR/EDR: " + BREDRProvider.this.mDevice.getAddress() + " using " + (BREDRProvider.this.mUUIDTransport.equals(UUIDs.SPP) ? "SPP" : "GAIA"));
                }
                BREDRProvider.this.mBluetoothAdapter.cancelDiscovery();
                this.mmConnectorSocket.connect();
                BREDRProvider.this.onSocketConnected(this.mmConnectorSocket);
            } catch (IOException e) {
                Log.w("ConnectionThread", "Exception while connecting: " + e.toString());
                try {
                    this.mmConnectorSocket.close();
                } catch (IOException e2) {
                    Log.w("ConnectionThread", "Could not close the client socket", e2);
                }
                BREDRProvider.this.onConnectionFailed();
                BREDRProvider.this.mConnectionThread = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void cancel() {
            interrupt();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class CommunicationThread extends Thread {
        private final InputStream mmInputStream;
        private final OutputStream mmOutputStream;
        private final BluetoothSocket mmSocket;
        private boolean mmIsRunning = false;
        private final String THREAD_TAG = "CommunicationThread";

        CommunicationThread(BluetoothSocket bluetoothSocket) {
            InputStream inputStream;
            setName("CommunicationThread" + getId());
            this.mmSocket = bluetoothSocket;
            OutputStream outputStream = null;
            try {
                inputStream = bluetoothSocket.getInputStream();
                try {
                    outputStream = bluetoothSocket.getOutputStream();
                } catch (IOException e) {
                    e = e;
                    Log.e("CommunicationThread", "Error occurred when getting input and output streams", e);
                }
            } catch (IOException e2) {
                inputStream = null;
                e2.printStackTrace();
            }
            this.mmInputStream = inputStream;
            this.mmOutputStream = outputStream;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            if (this.mmInputStream == null) {
                Log.w("CommunicationThread", "Run thread failed: InputStream is null.");
                BREDRProvider.this.disconnect();
                return;
            }
            if (this.mmOutputStream == null) {
                Log.w("CommunicationThread", "Run thread failed: OutputStream is null.");
                BREDRProvider.this.disconnect();
                return;
            }
            BluetoothSocket bluetoothSocket = this.mmSocket;
            if (bluetoothSocket == null) {
                Log.w("CommunicationThread", "Run thread failed: BluetoothSocket is null.");
                BREDRProvider.this.disconnect();
            } else if (!bluetoothSocket.isConnected()) {
                Log.w("CommunicationThread", "Run thread failed: BluetoothSocket is not connected.");
                BREDRProvider.this.disconnect();
            } else {
                listenStream();
            }
        }

        private void listenStream() {
            byte[] bArr = new byte[1024];
            if (BREDRProvider.this.mShowDebugLogs) {
                Log.d("CommunicationThread", "Start to listen for incoming streams.");
            }
            this.mmIsRunning = true;
            BREDRProvider.this.onCommunicationRunning();
            while (BREDRProvider.this.mState == 2 && this.mmIsRunning) {
                try {
                    int read = this.mmInputStream.read(bArr);
                    if (read > 0) {
                        byte[] bArr2 = new byte[read];
                        System.arraycopy(bArr, 0, bArr2, 0, read);
                        if (BREDRProvider.this.mShowDebugLogs) {
                            Log.d("CommunicationThread", "Reception of data: " + Utils.getStringFromBytes(bArr2));
                        }
                        BREDRProvider.this.onDataFound(bArr2);
                    }
                } catch (IOException e) {
                    Log.e("CommunicationThread", "Reception of data failed: exception occurred while reading: " + e.toString());
                    this.mmIsRunning = false;
                    if (BREDRProvider.this.mState == 2) {
                        BREDRProvider.this.onConnectionLost();
                    }
                    BREDRProvider.this.mCommunicationThread = null;
                }
            }
            if (BREDRProvider.this.mShowDebugLogs) {
                Log.d("CommunicationThread", "Stop to listen for incoming streams.");
            }
        }

        boolean sendStream(byte[] bArr) {
            if (BREDRProvider.this.mShowDebugLogs) {
                Log.d("BREDRProvider", "Process sending of data to the device starts");
            }
            BluetoothSocket bluetoothSocket = this.mmSocket;
            if (bluetoothSocket == null) {
                Log.w("CommunicationThread", "Sending of data failed: BluetoothSocket is null.");
                return false;
            }
            if (!bluetoothSocket.isConnected()) {
                Log.w("CommunicationThread", "Sending of data failed: BluetoothSocket is not connected.");
                return false;
            }
            if (BREDRProvider.this.mState != 2) {
                Log.w("CommunicationThread", "Sending of data failed: Provider is not connected.");
                return false;
            }
            OutputStream outputStream = this.mmOutputStream;
            if (outputStream == null) {
                Log.w("CommunicationThread", "Sending of data failed: OutputStream is null.");
                return false;
            }
            try {
                outputStream.write(bArr);
                this.mmOutputStream.flush();
                if (!BREDRProvider.this.mShowDebugLogs) {
                    return true;
                }
                Log.d("BREDRProvider", "Success sending of data.");
                return true;
            } catch (IOException e) {
                Log.w("CommunicationThread", "Sending of data failed: Exception occurred while writing data: " + e.toString());
                return false;
            }
        }

        void cancel() {
            if (BREDRProvider.this.mShowDebugLogs) {
                Log.d("BREDRProvider", "Thread is cancelled.");
            }
            this.mmIsRunning = false;
            try {
                this.mmSocket.close();
            } catch (IOException e) {
                Log.w("CommunicationThread", "Cancellation of the Thread: Close of BluetoothSocket failed: " + e.toString());
            }
        }
    }
}
