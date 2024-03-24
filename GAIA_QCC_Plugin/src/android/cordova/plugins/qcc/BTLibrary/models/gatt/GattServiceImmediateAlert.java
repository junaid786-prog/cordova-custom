package cordova.plugins.qcc.BTLibrary.models.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import cordova.plugins.qcc.BTLibrary.models.gatt.GATT;

/* loaded from: classes2.dex */
public class GattServiceImmediateAlert {
    private BluetoothGattService mGattService = null;
    private BluetoothGattCharacteristic mAlertLevelCharacteristic = null;

    public boolean isSupported() {
        return isServiceAvailable() && isAlertLevelCharacteristicAvailable();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean checkService(BluetoothGattService bluetoothGattService) {
        if (!bluetoothGattService.getUuid().equals(GATT.UUIDs.SERVICE_IMMEDIATE_ALERT_UUID)) {
            return false;
        }
        this.mGattService = bluetoothGattService;
        for (BluetoothGattCharacteristic bluetoothGattCharacteristic : bluetoothGattService.getCharacteristics()) {
            if (bluetoothGattCharacteristic.getUuid().equals(GATT.UUIDs.CHARACTERISTIC_ALERT_LEVEL_UUID) && (bluetoothGattCharacteristic.getProperties() & 4) > 0) {
                this.mAlertLevelCharacteristic = bluetoothGattCharacteristic;
            }
        }
        return true;
    }

    public boolean isServiceAvailable() {
        return this.mGattService != null;
    }

    public boolean isAlertLevelCharacteristicAvailable() {
        return this.mAlertLevelCharacteristic != null;
    }

    public BluetoothGattCharacteristic getAlertLevelCharacteristic() {
        return this.mAlertLevelCharacteristic;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void reset() {
        this.mGattService = null;
        this.mAlertLevelCharacteristic = null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("IMMEDIATE ALERT Service ");
        if (isServiceAvailable()) {
            sb.append("available with the following characteristics:\n\t- ALERT LEVEL");
            sb.append(isAlertLevelCharacteristicAvailable() ? " available" : " not available or with wrong properties");
        } else {
            sb.append("not available.");
        }
        return sb.toString();
    }
}
