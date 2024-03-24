package info.android.custom.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.BluetoothA2dp;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.UUID;

public class ClassicBluetoothPlugin extends CordovaPlugin {
    private BluetoothAdapter mBTAdapter;
    private final String TAG = "ClassicBluetoothPlugin";
    private final HashMap<String, btInfoClass> mDeviceInfoList = new HashMap<>();
    private boolean mScanning = false;
    private ConnectionThread mConnectionThread = null;
    final BroadcastReceiver restartDiscovery = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ClassicBluetoothPlugin.this.mBTAdapter.startDiscovery();
        }
    };
    final BroadcastReceiver blReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "receive 1: " + "()");

            if ("android.bluetooth.device.action.FOUND".equals(intent.getAction())) {
                Log.d(TAG, "execute: " + "recieve()");
                BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                String uuid = UUID.nameUUIDFromBytes(bluetoothDevice.getAddress().getBytes()).toString();
                String name = bluetoothDevice.getName();

                Log.d(TAG, "received:: " + uuid +  ":: " + name);

                if (name != null) {
                    ClassicBluetoothPlugin.this.mDeviceInfoList.put(uuid, new btInfoClass(name, uuid, bluetoothDevice.getAddress()));
                    ClassicBluetoothPlugin.this.fireEvent(String.format("onDeviceFound({name:\"%s\",uuid:\"%s\",macAddr:\"%s\"})", name, uuid, bluetoothDevice.getAddress()));
                }
            }
        }
    };

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }

    @Override
    public boolean execute(String str, JSONArray jSONArray, CallbackContext callbackContext) throws JSONException {
        if (str.equals("startScan")) {
            Log.e(TAG, "execute: " + "startScan()");
            startScan();
            callbackContext.success("Scan Started");
            return true;
        }
        if (str.equals("stopScan")) {
            Log.e(TAG, "execute: " + "stopScan()");
            stopScan();
            return true;
        }
        if (!str.equals("connect")) {
            Log.e(TAG, "execute: " + "connect()");
            return false;
        }
        connect(jSONArray);
        return true;
    }

    @Override
    protected void pluginInitialize() {
        this.mBTAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    @Override
    public void onDestroy() {
        try {
            this.cordova.getActivity().unregisterReceiver(this.blReceiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            this.cordova.getActivity().unregisterReceiver(this.restartDiscovery);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void startScan() {
        if (this.mScanning) {
            return;
        }
        this.mScanning = true;
        this.mDeviceInfoList.clear();
        this.cordova.getActivity().registerReceiver(this.restartDiscovery, new IntentFilter("android.bluetooth.adapter.action.DISCOVERY_FINISHED"));
        this.cordova.getActivity().registerReceiver(this.blReceiver, new IntentFilter("android.bluetooth.device.action.FOUND"));
        this.mBTAdapter.startDiscovery();
        Log.d(TAG, "scan: " + "scanning");

    }

    private void stopScan() {
        if (this.mScanning) {
            this.mScanning = false;
            this.mBTAdapter.cancelDiscovery();
            this.cordova.getActivity().unregisterReceiver(this.blReceiver);
            this.cordova.getActivity().unregisterReceiver(this.restartDiscovery);
            Log.d(TAG, "scan: " + "stop scanning");
        }
        Log.e(TAG, "scan: " + "not scanning");
    }

    private void connect(JSONArray jSONArray) throws JSONException, NullPointerException {
        ConnectionThread connectionThread = this.mConnectionThread;
        if (connectionThread == null || !connectionThread.isAlive()) {
            stopScan();
            JSONObject jSONObject = jSONArray.getJSONObject(0);
            String string = !jSONObject.isNull("name") ? jSONObject.getString("name") : null;
            String string2 = jSONObject.isNull("uuid") ? null : jSONObject.getString("uuid");

            Log.d(TAG, "connect: " + string + "::" + string2);

            btInfoClass btinfoclass = this.mDeviceInfoList.get(string2);
            if (string != null && string2 != null && btinfoclass != null) {
                BluetoothDevice remoteDevice = this.mBTAdapter.getRemoteDevice(btinfoclass.getMacAddr());
                if (remoteDevice.getBondState() == 10) {
                    this.mConnectionThread = new BluetoothCreateBondThread(remoteDevice);
                } else {
                    this.mConnectionThread = new BluetoothA2dpConnectionThread(remoteDevice);
                }
                this.mConnectionThread.start();
                return;
            }
            fireEvent(String.format("onConnectResult({result:\"%s\",name:\"%s\",uuid:\"%s\",macAddr:\"%s\"})", "failure", "", "", ""));
        }
    }

    private void fireEvent(final String str) {
        this.cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "fire: " + str);
                String jsCode = "cordova.fireWindowEvent('classicBluetoothOnConnectResult', 'new here');";
                //ClassicBluetoothPlugin.this.webView.loadUrl("javascript:" + jsCode);
                ClassicBluetoothPlugin.this.webView.sendJavascript(jsCode);
            }
        });
    }

    public class btInfoClass {
        String macAddr;
        String name;
        String uuid;

        public btInfoClass(String str, String str2, String str3) {
            this.name = str;
            this.uuid = str2;
            this.macAddr = str3;
        }

        public String getName() {
            return this.name;
        }

        public String getUuid() {
            return this.uuid;
        }

        public String getMacAddr() {
            return this.macAddr;
        }
    }

    public abstract class ConnectionThread extends Thread {
        protected final BluetoothDevice mmDevice;

        public abstract void cancel();

        public ConnectionThread(BluetoothDevice bluetoothDevice) {
            this.mmDevice = bluetoothDevice;
        }

        protected void notifyFailure(String str) {
            ClassicBluetoothPlugin.this.fireEvent(String.format("onConnectResult({result:\"%s\",name:\"%s\",uuid:\"%s\",macAddr:\"%s\"})", "failure", "", "", ""));
        }

        protected void notifySuccess() {
            ClassicBluetoothPlugin.this.fireEvent(String.format("onConnectResult({result:\"%s\",name:\"%s\",uuid:\"%s\",macAddr:\"%s\"})", "success", this.mmDevice.getName(), UUID.nameUUIDFromBytes(this.mmDevice.getAddress().getBytes()).toString(), this.mmDevice.getAddress()));
        }
    }

    public class BluetoothCreateBondThread extends ConnectionThread {
        private final int TIMEOUT;
        final BroadcastReceiver bondingReceiver;

        public BluetoothCreateBondThread(BluetoothDevice bluetoothDevice) {
            super(bluetoothDevice);
            this.TIMEOUT = 5000; // Set your desired timeout here
            this.bondingReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    if ("android.bluetooth.device.action.BOND_STATE_CHANGED".equals(intent.getAction()) && ((BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE")).getAddress().equals(BluetoothCreateBondThread.this.mmDevice.getAddress()) && intent.getIntExtra("android.bluetooth.device.extra.BOND_STATE", Integer.MIN_VALUE) == 12) {
                        BluetoothCreateBondThread.this.notifySuccess();
                        BluetoothCreateBondThread.this.interrupt();
                    }
                }
            };
        }

        @Override
        public void run() {
            try {
                ClassicBluetoothPlugin.this.cordova.getActivity().registerReceiver(this.bondingReceiver, new IntentFilter("android.bluetooth.device.action.BOND_STATE_CHANGED"));
                if (!this.mmDevice.createBond()) {
                    notifyFailure("createBond() failure");
                } else {
                    sleep(TIMEOUT);
                    notifyFailure("timeout");
                }
            } catch (InterruptedException unused) {
            } catch (Throwable th) {
                ClassicBluetoothPlugin.this.cordova.getActivity().unregisterReceiver(this.bondingReceiver);
                throw th;
            }
            ClassicBluetoothPlugin.this.cordova.getActivity().unregisterReceiver(this.bondingReceiver);
        }

        @Override
        public void cancel() {
            notifyFailure("cancelled");
            interrupt();
        }
    }

    public class BluetoothA2dpConnectionThread extends ConnectionThread {
        private final int TIMEOUT;
        private final BroadcastReceiver connectionReceiver;
        private BluetoothA2dp mmBluetoothA2dp;
        private final BluetoothProfile.ServiceListener profileListener;

        public BluetoothA2dpConnectionThread(BluetoothDevice bluetoothDevice) {
            super(bluetoothDevice);
            this.TIMEOUT = 5000; // Set your desired timeout here
            this.mmBluetoothA2dp = null;
            this.profileListener = new BluetoothProfile.ServiceListener() {
                @Override
                public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
                    if (i == 2) {
                        BluetoothA2dpConnectionThread.this.mmBluetoothA2dp = (BluetoothA2dp) bluetoothProfile;
                        BluetoothA2dpConnectionThread.this.connect();
                    }
                }

                @Override
                public void onServiceDisconnected(int i) {
                    if (i == 2) {
                        BluetoothA2dpConnectionThread.this.mmBluetoothA2dp = null;
                    }
                }
            };
            this.connectionReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    String action = intent.getAction();
                    if (action == null || !action.equals("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED")) {
                        return;
                    }
                    BluetoothDevice bluetoothDevice2 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                    int intExtra = intent.getIntExtra("android.bluetooth.profile.extra.STATE", 0);
                    if (bluetoothDevice2.getAddress().equals(BluetoothA2dpConnectionThread.this.mmDevice.getAddress()) && intExtra == 2) {
                        BluetoothA2dpConnectionThread.this.notifySuccess();
                        BluetoothA2dpConnectionThread.this.interrupt();
                    }
                }
            };
        }

        @Override
        public void run() {
            try {
                ClassicBluetoothPlugin.this.mBTAdapter.getProfileProxy(ClassicBluetoothPlugin.this.cordova.getActivity(), this.profileListener, 2);
                sleep(TIMEOUT);
                notifyFailure("timeout");
                ClassicBluetoothPlugin.this.cordova.getActivity().unregisterReceiver(this.connectionReceiver);
                if (this.mmBluetoothA2dp == null) {
                    return;
                }
            } catch (InterruptedException unused) {
                ClassicBluetoothPlugin.this.cordova.getActivity().unregisterReceiver(this.connectionReceiver);
                if (this.mmBluetoothA2dp == null) {
                    return;
                }
            } catch (Throwable th) {
                ClassicBluetoothPlugin.this.cordova.getActivity().unregisterReceiver(this.connectionReceiver);
                if (this.mmBluetoothA2dp != null) {
                    ClassicBluetoothPlugin.this.mBTAdapter.closeProfileProxy(2, this.mmBluetoothA2dp);
                }
                throw th;
            }
            ClassicBluetoothPlugin.this.mBTAdapter.closeProfileProxy(2, this.mmBluetoothA2dp);
        }

        @Override
        public void cancel() {
            notifyFailure("cancelled");
            interrupt();
        }

        private void connect() {
            try {
                ClassicBluetoothPlugin.this.cordova.getActivity().registerReceiver(this.connectionReceiver, new IntentFilter("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED"));
                BluetoothA2dp.class.getDeclaredMethod("connect", BluetoothDevice.class).invoke(this.mmBluetoothA2dp, this.mmDevice);
            } catch (Exception e) {
                Log.e("ClassicBluetoothPlugin", "BluetoothA2dpConnectionThread.connect() exception", e);
                notifyFailure(e.getMessage());
                interrupt();
            }
        }
    }
}
