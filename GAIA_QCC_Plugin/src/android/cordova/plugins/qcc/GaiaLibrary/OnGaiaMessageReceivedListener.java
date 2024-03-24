package cordova.plugins.qcc.GaiaLibrary;

import android.bluetooth.BluetoothDevice;
import android.os.Message;

/* loaded from: classes2.dex */
public interface OnGaiaMessageReceivedListener {
    void handleMessageFromService(Message message);

    void onDeviceConnected(BluetoothDevice bluetoothDevice);

    void onServiceConnected(int i);

    void onServiceDisconnected();
}
