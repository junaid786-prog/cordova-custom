package cordova.plugins.qcc.BTLibrary;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import cordova.plugins.qcc.BTLibrary.receivers.BREDRDiscoveryReceiver;
import cordova.plugins.qcc.BTLibrary.services.BluetoothService;
import cordova.plugins.qcc.BTLibrary.services.GAIABREDRService;
import cordova.plugins.qcc.BTLibrary.services.GAIAGATTBLEService;
import cordova.plugins.qcc.GaiaLibrary.OnGaiaMessageReceivedListener;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/* loaded from: classes2.dex */
public class SolosBluetoothConnector extends BluetoothConnector implements BREDRDiscoveryReceiver.BREDRDiscoveryListener {
    private static final boolean DEBUG = true;
    private OnBluetoothConnectionListener mBtConnectionListener;
    private Context mContext;
    private OnGaiaMessageReceivedListener mGaiaMsgReceivedListener;
    private boolean mIsScanning;
    public BluetoothService mService;
    private LeScanCallback mLeScanCallback = new LeScanCallback();
    private int mTransport = -1;
    private BluetoothDevice mCurrentBtDevice = null;
    private String mCurrentDeviceUUID = "";
    private final BREDRDiscoveryReceiver mDiscoveryReceiver = new BREDRDiscoveryReceiver(this);
    private BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: cordova.plugins.qcc.BTLibrary.SolosBluetoothConnector.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE);
                if (intExtra != 10) {
                    if (intExtra != 12) {
                        return;
                    }
                    SolosBluetoothConnector.this.startService();
                } else {
                    if (SolosBluetoothConnector.this.mService == null || SolosBluetoothConnector.this.mService.getConnectionState() == 0) {
                        return;
                    }
                    SolosBluetoothConnector.this.mService.disconnectDevice();
                    SolosBluetoothConnector.this.mService.setStateToDisconnected();
                }
            }
        }
    };
    private Set<BluetoothDevice> mScannedBlueToothDevices = new HashSet();
    private HashMap<String, BluetoothDevice> mBondedUUIDToBtDevice = new HashMap<>();
    private HashMap<String, BluetoothDevice> mBondedMACToBtDevice = new HashMap<>();
    private final ServiceConnection mServiceConnection = new BluetoothServiceConnection(this);
    private Handler mHandler = new MessageHandler(this);

    public SolosBluetoothConnector(Context context) {
        this.mContext = context;
        initBlueToothConnector(context);
        this.mContext.registerReceiver(this.mReceiver, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
        startService();
    }

    public void setOnBluetoothConnectionListener(OnBluetoothConnectionListener onBluetoothConnectionListener) {
        this.mBtConnectionListener = onBluetoothConnectionListener;
    }

    public void setOnGaiaMessageReceivedListener(OnGaiaMessageReceivedListener onGaiaMessageReceivedListener) {
        this.mGaiaMsgReceivedListener = onGaiaMessageReceivedListener;
    }

    public boolean isBluetoothEnable() {
        return super.isBluetoothEnabled();
    }

    @Override // cordova.plugins.qcc.BTLibrary.BluetoothConnector, cordova.plugins.qcc.BTLibrary.BluetoothStateReceiver.BroadcastReceiverListener
    public void onBluetoothEnabled() {
        super.onBluetoothEnabled();
        startService();
    }

    @Override // cordova.plugins.qcc.BTLibrary.BluetoothConnector, cordova.plugins.qcc.BTLibrary.BluetoothStateReceiver.BroadcastReceiverListener
    public void onBluetoothDisabled() {
        super.onBluetoothDisabled();
    }

    public void startScan() {
        scanNow(true);
        for (BluetoothDevice bluetoothDevice : this.mBtAdapter.getBondedDevices()) {
            this.mBondedUUIDToBtDevice.put(UUID.nameUUIDFromBytes(bluetoothDevice.getAddress().getBytes()).toString(), bluetoothDevice);
            this.mBondedMACToBtDevice.put(bluetoothDevice.getAddress(), bluetoothDevice);
            this.mBtConnectionListener.onBondedDeviceScanned(bluetoothDevice);
        }
    }

    public void stopScan() {
        scanNow(false);
    }

    private void scanNow(boolean z) {
        if (z && !this.mIsScanning) {
            this.mIsScanning = true;
            this.mBtAdapter.startLeScan(this.mLeScanCallback);
        } else {
            if (z || !this.mIsScanning) {
                return;
            }
            this.mIsScanning = false;
            this.mBtAdapter.stopLeScan(this.mLeScanCallback);
        }
    }

    public void startConnectByMAC(String str) {
        this.mCurrentBtDevice = this.mBondedMACToBtDevice.get(str);
        if (this.mService == null) {
            startService();
        } else {
            connectToDevice();
        }
    }

    public void startConnectByUUID(String str) {
        Log.i("ContentValues", "startConnectByUUID = " + str);
        this.mCurrentBtDevice = this.mBondedUUIDToBtDevice.get(str);
        this.mCurrentDeviceUUID = str;
        if (this.mService == null) {
            startService();
        } else {
            connectToDevice();
        }
    }

    public void startGaiaService() {
        startService();
    }

    public void startDisconnectByUUID(String str) {
        BluetoothService bluetoothService = this.mService;
        if (bluetoothService == null || this.mCurrentDeviceUUID == null || bluetoothService.getConnectionState() != 2 || !str.equals(this.mCurrentDeviceUUID)) {
            return;
        }
        this.mService.disconnectDevice();
    }

    @Override // cordova.plugins.qcc.BTLibrary.receivers.BREDRDiscoveryReceiver.BREDRDiscoveryListener
    public void onDeviceFound(BluetoothDevice bluetoothDevice) {
        if (this.mScannedBlueToothDevices == null || bluetoothDevice == null || bluetoothDevice.getName() == null || bluetoothDevice.getName().length() <= 0) {
            return;
        }
        int size = this.mScannedBlueToothDevices.size();
        this.mScannedBlueToothDevices.add(bluetoothDevice);
        if (this.mScannedBlueToothDevices.size() != size) {
            this.mBtConnectionListener.onScannedDeviceScanned(bluetoothDevice);
        }
        this.mBtConnectionListener.onScannedDevicesReady(getScannedBluetoothDevices());
    }

    public ArrayList<BluetoothDevice> getScannedBluetoothDevices() {
        ArrayList<BluetoothDevice> arrayList = new ArrayList<>();
        arrayList.addAll(this.mScannedBlueToothDevices);
        return arrayList;
    }

    public ArrayList<BluetoothDevice> getBondedBluetoothDevices() {
        ArrayList<BluetoothDevice> arrayList = new ArrayList<>();
        arrayList.addAll(this.mBtAdapter.getBondedDevices());
        return arrayList;
    }

    public void registerReceiver() {
        this.mContext.registerReceiver(this.mDiscoveryReceiver, new IntentFilter("android.bluetooth.device.action.FOUND"));
    }

    public void unregisterReceiver() {
        this.mContext.unregisterReceiver(this.mDiscoveryReceiver);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class LeScanCallback implements BluetoothAdapter.LeScanCallback {
        private LeScanCallback() {
        }

        @Override // android.bluetooth.BluetoothAdapter.LeScanCallback
        public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
            if (SolosBluetoothConnector.this.mScannedBlueToothDevices == null || bluetoothDevice == null || bluetoothDevice.getName() == null || bluetoothDevice.getName().length() <= 0) {
                return;
            }
            SolosBluetoothConnector.this.mScannedBlueToothDevices.add(bluetoothDevice);
            SolosBluetoothConnector.this.mBtConnectionListener.onScannedDevicesReady(SolosBluetoothConnector.this.getScannedBluetoothDevices());
            SolosBluetoothConnector.this.mBtConnectionListener.onBondedDevicesReady(SolosBluetoothConnector.this.getBondedBluetoothDevices());
        }
    }

    private int getTransport() {
        String[] strArr = {"XIAOMI", "motorola", "ASUS", "google"};
        String lowerCase = Build.MANUFACTURER.toLowerCase();
        if (Build.VERSION.SDK_INT >= 33) {
            for (int i = 0; i < 4; i++) {
                if (strArr[i].toLowerCase().equals(lowerCase)) {
                    return 1;
                }
            }
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean startService() {
        Class cls;
        if (this.mService != null) {
            return false;
        }
        int transport = getTransport();
        int i = transport == 0 ? 0 : transport == 1 ? 1 : -1;
        this.mTransport = i;
        if (i == -1) {
            return false;
        }
        if (i == 0) {
            cls = GAIAGATTBLEService.class;
        } else {
            cls = GAIABREDRService.class;
        }
        return this.mContext.bindService(new Intent(this.mContext, (Class<?>) cls), this.mServiceConnection, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initService() {
        BluetoothService bluetoothService = this.mService;
        if (bluetoothService != null) {
            bluetoothService.addHandler(this.mHandler);
            if (this.mService.getDevice() == null) {
                connectToDevice();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void uninitService() {
        BluetoothService bluetoothService = this.mService;
        if (bluetoothService != null) {
            bluetoothService.removeHandler(this.mHandler);
        }
    }

    private void connectToDevice() {
        BluetoothDevice bluetoothDevice = this.mCurrentBtDevice;
        if (bluetoothDevice == null) {
            return;
        }
        this.mGaiaMsgReceivedListener.onDeviceConnected(bluetoothDevice);
        if (this.mService.connectToDevice(this.mCurrentBtDevice.getAddress())) {
            return;
        }
        Log.w("ContentValues", "connection failed");
        this.mBtConnectionListener.onDeviceErrorConnected();
    }

    public boolean sendGAIAPacket(byte[] bArr) {
        BluetoothService bluetoothService = this.mService;
        return bluetoothService != null && bluetoothService.sendGAIAPacket(bArr);
    }

    /* loaded from: classes2.dex */
    private static class MessageHandler extends Handler {
        final WeakReference<SolosBluetoothConnector> mReference;

        MessageHandler(SolosBluetoothConnector solosBluetoothConnector) {
            this.mReference = new WeakReference<>(solosBluetoothConnector);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            SolosBluetoothConnector solosBluetoothConnector = this.mReference.get();
            if (solosBluetoothConnector == null || solosBluetoothConnector.mGaiaMsgReceivedListener == null) {
                return;
            }
            solosBluetoothConnector.mGaiaMsgReceivedListener.handleMessageFromService(message);
            if (message.what == 0 && ((Integer) message.obj).intValue() == 0) {
                if (solosBluetoothConnector.mService == null || !solosBluetoothConnector.mService.isUpgrading()) {
                    solosBluetoothConnector.mCurrentDeviceUUID = "";
                    solosBluetoothConnector.mCurrentBtDevice = null;
                    solosBluetoothConnector.mGaiaMsgReceivedListener.onDeviceConnected(null);
                }
            }
        }
    }

    /* loaded from: classes2.dex */
    private class BluetoothServiceConnection implements ServiceConnection {
        final WeakReference<SolosBluetoothConnector> solosBtConnector;

        BluetoothServiceConnection(SolosBluetoothConnector solosBluetoothConnector) {
            this.solosBtConnector = new WeakReference<>(solosBluetoothConnector);
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            SolosBluetoothConnector solosBluetoothConnector = this.solosBtConnector.get();
            if (solosBluetoothConnector == null || solosBluetoothConnector.mGaiaMsgReceivedListener == null) {
                return;
            }
            if (componentName.getClassName().equals(GAIAGATTBLEService.class.getName())) {
                SolosBluetoothConnector.this.mService = ((GAIAGATTBLEService.LocalBinder) iBinder).getService();
            } else if (componentName.getClassName().equals(GAIABREDRService.class.getName())) {
                SolosBluetoothConnector.this.mService = ((GAIABREDRService.LocalBinder) iBinder).getService();
            }
            solosBluetoothConnector.mGaiaMsgReceivedListener.onServiceConnected(SolosBluetoothConnector.this.mTransport);
            solosBluetoothConnector.initService();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            SolosBluetoothConnector solosBluetoothConnector;
            if (!componentName.getClassName().equals(GAIAGATTBLEService.class.getName()) || (solosBluetoothConnector = this.solosBtConnector.get()) == null || solosBluetoothConnector.mGaiaMsgReceivedListener == null) {
                return;
            }
            solosBluetoothConnector.mGaiaMsgReceivedListener.onServiceDisconnected();
            solosBluetoothConnector.uninitService();
            SolosBluetoothConnector.this.mService = null;
        }
    }
}
