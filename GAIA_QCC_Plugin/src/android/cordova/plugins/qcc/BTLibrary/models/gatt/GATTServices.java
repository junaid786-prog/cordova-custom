package cordova.plugins.qcc.BTLibrary.models.gatt;

import android.bluetooth.BluetoothGattService;
import androidx.collection.SimpleArrayMap;
import cordova.plugins.qcc.BTLibrary.models.gatt.GATT;
import java.util.List;

/* loaded from: classes2.dex */
public class GATTServices {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private boolean isSupported = false;
    public final GattServiceGaia gattServiceGaia = new GattServiceGaia();
    public final GattServiceLinkLoss gattServiceLinkLoss = new GattServiceLinkLoss();
    public final GattServiceImmediateAlert gattServiceimmediateAlert = new GattServiceImmediateAlert();
    public final GattServiceTxPower gattServicetxPower = new GattServiceTxPower();
    public final GattServiceHeartRate gattServiceHeartRate = new GattServiceHeartRate();
    public final GattServiceDeviceInformation gattServiceDeviceInformation = new GattServiceDeviceInformation();
    public final SimpleArrayMap<Integer, GattServiceBattery> gattServiceBatteries = new SimpleArrayMap<>();

    public void setSupportedGattServices(List<BluetoothGattService> list) {
        this.isSupported = true;
        reset();
        for (BluetoothGattService bluetoothGattService : list) {
            if (!this.gattServiceGaia.checkService(bluetoothGattService) && !this.gattServiceLinkLoss.checkService(bluetoothGattService) && !this.gattServiceimmediateAlert.checkService(bluetoothGattService) && !this.gattServicetxPower.checkService(bluetoothGattService)) {
                if (bluetoothGattService.getUuid().equals(GATT.UUIDs.SERVICE_BATTERY_UUID)) {
                    GattServiceBattery gattServiceBattery = new GattServiceBattery();
                    gattServiceBattery.checkService(bluetoothGattService);
                    this.gattServiceBatteries.put(Integer.valueOf(bluetoothGattService.getInstanceId()), gattServiceBattery);
                } else if (!this.gattServiceHeartRate.checkService(bluetoothGattService)) {
                    this.gattServiceDeviceInformation.checkService(bluetoothGattService);
                }
            }
        }
    }

    public void reset() {
        this.isSupported = false;
        this.gattServiceLinkLoss.reset();
        this.gattServiceGaia.reset();
        this.gattServiceimmediateAlert.reset();
        this.gattServicetxPower.reset();
        this.gattServiceBatteries.clear();
        this.gattServiceHeartRate.reset();
        this.gattServiceDeviceInformation.reset();
    }

    public boolean isGattProfileProximitySupported() {
        return this.gattServiceLinkLoss.isSupported();
    }

    public boolean isGattProfileHeartRateSupported() {
        return this.gattServiceHeartRate.isSupported() && this.gattServiceDeviceInformation.isSupported();
    }

    public boolean isGattProfileFindMeSupported() {
        return this.gattServiceimmediateAlert.isSupported();
    }

    public boolean isBatteryServiceSupported() {
        return !this.gattServiceBatteries.isEmpty();
    }

    public boolean isSupported() {
        return this.isSupported;
    }

    public String toString() {
        String str = (this.gattServiceGaia.toString() + "\n\n" + this.gattServiceLinkLoss.toString() + "\n\n" + this.gattServiceimmediateAlert.toString() + "\n\n" + this.gattServicetxPower.toString() + "\n\n" + this.gattServiceHeartRate.toString() + "\n\n" + this.gattServiceDeviceInformation.toString()) + "\n\n" + this.gattServiceBatteries.size() + " BATTERY Service(s) available:";
        for (int i = 0; i < this.gattServiceBatteries.size(); i++) {
            int intValue = this.gattServiceBatteries.keyAt(i).intValue();
            str = str + "\ninstance " + intValue + ": " + this.gattServiceBatteries.get(Integer.valueOf(intValue)).toString();
        }
        return str;
    }
}
