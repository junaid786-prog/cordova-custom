package cordova.plugins.qcc.BTLibrary.models.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import cordova.plugins.qcc.BTLibrary.models.gatt.GATT;

/* loaded from: classes2.dex */
public class GattServiceTxPower {
    private BluetoothGattService mGattService = null;
    private BluetoothGattCharacteristic mTxPowerLevelCharacteristic = null;

    public boolean isSupported() {
        return isServiceAvailable() && isTxPowerLevelCharacteristicAvailable();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean checkService(BluetoothGattService bluetoothGattService) {
        if (!bluetoothGattService.getUuid().equals(GATT.UUIDs.SERVICE_TX_POWER_UUID)) {
            return false;
        }
        this.mGattService = bluetoothGattService;
        for (BluetoothGattCharacteristic bluetoothGattCharacteristic : bluetoothGattService.getCharacteristics()) {
            if (bluetoothGattCharacteristic.getUuid().equals(GATT.UUIDs.CHARACTERISTIC_TX_POWER_LEVEL_UUID) && (bluetoothGattCharacteristic.getProperties() & 2) > 0) {
                this.mTxPowerLevelCharacteristic = bluetoothGattCharacteristic;
            }
        }
        return true;
    }

    public boolean isServiceAvailable() {
        return this.mGattService != null;
    }

    public boolean isTxPowerLevelCharacteristicAvailable() {
        return this.mTxPowerLevelCharacteristic != null;
    }

    public BluetoothGattCharacteristic getTxPowerLevelCharacteristic() {
        return this.mTxPowerLevelCharacteristic;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void reset() {
        this.mGattService = null;
        this.mTxPowerLevelCharacteristic = null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("TX POWER Service ");
        if (isServiceAvailable()) {
            sb.append("available with the following characteristics:\n\t- TX POWER LEVEL");
            sb.append(isTxPowerLevelCharacteristicAvailable() ? " available" : " not available or with wrong properties");
        } else {
            sb.append("not available.");
        }
        return sb.toString();
    }
}
