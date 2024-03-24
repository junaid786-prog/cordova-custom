package cordova.plugins.qcc.BTLibrary.receivers;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* loaded from: classes2.dex */
public class BREDRDiscoveryReceiver extends BroadcastReceiver {
    private final BREDRDiscoveryListener mListener;

    /* loaded from: classes2.dex */
    public interface BREDRDiscoveryListener {
        void onDeviceFound(BluetoothDevice bluetoothDevice);
    }

    public BREDRDiscoveryReceiver(BREDRDiscoveryListener bREDRDiscoveryListener) {
        this.mListener = bREDRDiscoveryListener;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        BluetoothDevice bluetoothDevice;
        if (!intent.getAction().equals("android.bluetooth.device.action.FOUND") || (bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE")) == null) {
            return;
        }
        this.mListener.onDeviceFound(bluetoothDevice);
    }
}
