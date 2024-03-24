package cordova.plugins.qcc.BTLibrary.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* loaded from: classes2.dex */
public class BluetoothStateReceiver extends BroadcastReceiver {
    private final BroadcastReceiverListener mListener;

    /* loaded from: classes2.dex */
    public interface BroadcastReceiverListener {
        void onBluetoothDisabled();

        void onBluetoothEnabled();
    }

    public BluetoothStateReceiver(BroadcastReceiverListener broadcastReceiverListener) {
        this.mListener = broadcastReceiverListener;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
            int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE);
            if (intExtra == 10) {
                this.mListener.onBluetoothDisabled();
            } else if (intExtra == 12) {
                this.mListener.onBluetoothEnabled();
            }
        }
    }
}
