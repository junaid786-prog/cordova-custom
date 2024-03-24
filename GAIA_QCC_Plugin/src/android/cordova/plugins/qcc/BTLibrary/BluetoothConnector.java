package cordova.plugins.qcc.BTLibrary;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import cordova.plugins.qcc.BTLibrary.BluetoothStateReceiver;

/* loaded from: classes2.dex */
public abstract class BluetoothConnector implements BluetoothStateReceiver.BroadcastReceiverListener {
    static final boolean DEBUG = false;
    private BroadcastReceiver mBluetoothStateReceiver;
    BluetoothAdapter mBtAdapter;

    @Override // cordova.plugins.qcc.BTLibrary.BluetoothStateReceiver.BroadcastReceiverListener
    public void onBluetoothDisabled() {
    }

    @Override // cordova.plugins.qcc.BTLibrary.BluetoothStateReceiver.BroadcastReceiverListener
    public void onBluetoothEnabled() {
    }

    public void initBlueToothConnector(Context context) {
        this.mBtAdapter = ((BluetoothManager) context.getSystemService("bluetooth")).getAdapter();
        this.mBluetoothStateReceiver = new BluetoothStateReceiver(this);
    }

    public boolean isBluetoothEnabled() {
        BluetoothAdapter bluetoothAdapter = this.mBtAdapter;
        return bluetoothAdapter != null && bluetoothAdapter.isEnabled();
    }
}
