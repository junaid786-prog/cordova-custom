package cordova.plugins.qcc.BTLibrary.models.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.util.Log;
import cordova.plugins.qcc.BTLibrary.models.gatt.GATT;
import java.util.Iterator;
import java.util.UUID;

/* loaded from: classes2.dex */
public class GattServiceGaia {
    private final String TAG = "GattServiceGaia";
    private BluetoothGattService mGattService = null;
    private BluetoothGattCharacteristic mGaiaResponseCharacteristic = null;
    private BluetoothGattCharacteristic mGaiaCommandCharacteristic = null;
    private BluetoothGattCharacteristic mGaiaDataCharacteristic = null;
    private boolean mIsRWCPTransportSupported = false;

    public boolean isSupported() {
        return isServiceAvailable() && isCharacteristicGaiaCommandAvailable() && isCharacteristicGaiaDataAvailable() && isCharacteristicGaiaResponseAvailable();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean checkService(BluetoothGattService bluetoothGattService) {
        if (!bluetoothGattService.getUuid().equals(GATT.UUIDs.SERVICE_GAIA_UUID)) {
            return false;
        }
        this.mGattService = bluetoothGattService;
        Iterator<BluetoothGattCharacteristic> it = bluetoothGattService.getCharacteristics().iterator();
        while (true) {
            if (!it.hasNext()) {
                return true;
            }
            BluetoothGattCharacteristic next = it.next();
            UUID uuid = next.getUuid();
            if (uuid.equals(GATT.UUIDs.CHARACTERISTIC_GAIA_RESPONSE_UUID)) {
                this.mGaiaResponseCharacteristic = next;
            } else if (uuid.equals(GATT.UUIDs.CHARACTERISTIC_GAIA_COMMAND_UUID) && (next.getProperties() & 8) > 0) {
                this.mGaiaCommandCharacteristic = next;
            } else if (uuid.equals(GATT.UUIDs.CHARACTERISTIC_GAIA_DATA_ENDPOINT_UUID) && (next.getProperties() & 2) > 0) {
                this.mGaiaDataCharacteristic = next;
                int properties = next.getProperties();
                boolean z = (properties & 4) > 0 && (properties & 16) > 0;
                this.mIsRWCPTransportSupported = z;
                if (!z) {
                    Log.i("GattServiceGaia", "GAIA Data Endpoint characteristic does not provide the required properties for RWCP - WRITE_NO_RESPONSE or NOTIFY.");
                }
            }
        }
    }

    public boolean isRWCPTransportSupported() {
        return this.mIsRWCPTransportSupported;
    }

    public boolean isServiceAvailable() {
        return this.mGattService != null;
    }

    public boolean isCharacteristicGaiaCommandAvailable() {
        return this.mGaiaCommandCharacteristic != null;
    }

    public boolean isCharacteristicGaiaDataAvailable() {
        return this.mGaiaDataCharacteristic != null;
    }

    public boolean isCharacteristicGaiaResponseAvailable() {
        return this.mGaiaResponseCharacteristic != null;
    }

    public BluetoothGattCharacteristic getGaiaCommandCharacteristic() {
        return this.mGaiaCommandCharacteristic;
    }

    public BluetoothGattCharacteristic getGaiaDataCharacteristic() {
        return this.mGaiaDataCharacteristic;
    }

    public BluetoothGattCharacteristic getGaiaResponseCharacteristic() {
        return this.mGaiaResponseCharacteristic;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void reset() {
        this.mGattService = null;
        this.mGaiaDataCharacteristic = null;
        this.mGaiaResponseCharacteristic = null;
        this.mGaiaCommandCharacteristic = null;
        this.mIsRWCPTransportSupported = false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("GAIA Service ");
        if (isServiceAvailable()) {
            sb.append("available with the following characteristics:\n\t- GAIA COMMAND");
            sb.append(isCharacteristicGaiaCommandAvailable() ? " available" : " not available or with wrong properties");
            sb.append("\n\t- GAIA DATA");
            sb.append(isCharacteristicGaiaDataAvailable() ? " available" : " not available or with wrong properties");
            sb.append("\n\t- GAIA RESPONSE");
            sb.append(isCharacteristicGaiaResponseAvailable() ? " available" : " not available or with wrong properties");
        } else {
            sb.append("not available.");
        }
        return sb.toString();
    }
}
