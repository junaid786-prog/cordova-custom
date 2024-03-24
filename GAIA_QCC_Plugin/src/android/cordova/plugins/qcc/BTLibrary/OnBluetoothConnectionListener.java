package cordova.plugins.qcc.BTLibrary;

import android.bluetooth.BluetoothDevice;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public interface OnBluetoothConnectionListener {
    void onBluetoothDeviceOff();

    void onBluetoothDeviceOn();

    void onBondedDeviceScanned(BluetoothDevice bluetoothDevice);

    void onBondedDevicesReady(ArrayList<BluetoothDevice> arrayList);

    void onDeviceDisconnected();

    void onDeviceErrorConnected();

    void onDeviceSuccessConnected(BluetoothDevice bluetoothDevice);

    void onDisconnectedMessage();

    void onScannedDeviceScanned(BluetoothDevice bluetoothDevice);

    void onScannedDevicesReady(ArrayList<BluetoothDevice> arrayList);
}
