package cordova.plugins.qcc.BTLibrary.receivers;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* loaded from: classes2.dex */
public class BondStateReceiver extends BroadcastReceiver {
    private final BondStateListener mListener;

    /* loaded from: classes2.dex */
    public interface BondStateListener {
        void onBondStateChange(BluetoothDevice bluetoothDevice, int i);
    }

    public BondStateReceiver(BondStateListener bondStateListener) {
        this.mListener = bondStateListener;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        BluetoothDevice bluetoothDevice;
        if (intent.getAction().equals("android.bluetooth.device.action.BOND_STATE_CHANGED")) {
            BluetoothDevice bluetoothDevice2 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            int intExtra = intent.getIntExtra("android.bluetooth.device.extra.BOND_STATE", -1);
            if (bluetoothDevice2 == null || intExtra <= -1) {
                return;
            }
            this.mListener.onBondStateChange(bluetoothDevice2, intExtra);
            return;
        }
        if (!intent.getAction().equals("android.bluetooth.device.action.PAIRING_REQUEST") || (bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE")) == null) {
            return;
        }
        this.mListener.onBondStateChange(bluetoothDevice, 11);
    }
}
