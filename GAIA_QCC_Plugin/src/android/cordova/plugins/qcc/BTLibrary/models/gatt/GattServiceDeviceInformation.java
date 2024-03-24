package cordova.plugins.qcc.BTLibrary.models.gatt;

import android.bluetooth.BluetoothGattService;
import cordova.plugins.qcc.BTLibrary.models.gatt.GATT;

/* loaded from: classes2.dex */
class GattServiceDeviceInformation {
    private BluetoothGattService mGattService = null;

    public boolean isSupported() {
        return isServiceAvailable();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean checkService(BluetoothGattService bluetoothGattService) {
        if (!bluetoothGattService.getUuid().equals(GATT.UUIDs.SERVICE_DEVICE_INFORMATION_UUID)) {
            return false;
        }
        this.mGattService = bluetoothGattService;
        return true;
    }

    public boolean isServiceAvailable() {
        return this.mGattService != null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void reset() {
        this.mGattService = null;
    }

    public String toString() {
        return "DEVICE INFORMATION Service ".concat(isServiceAvailable() ? "available." : "not available.");
    }
}
