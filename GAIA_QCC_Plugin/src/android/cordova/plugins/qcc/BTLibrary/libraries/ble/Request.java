package cordova.plugins.qcc.BTLibrary.libraries.ble;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class Request {
    private int mAttempts = 0;
    private final boolean mBooleanData;
    private final BluetoothGattCharacteristic mCharacteristic;
    private final byte[] mData;
    private final BluetoothGattDescriptor mDescriptor;
    private final int mInteger;
    private final int mType;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface RequestType {
        public static final int CHARACTERISTIC_NOTIFICATION = 0;
        public static final int READ_CHARACTERISTIC = 1;
        public static final int READ_CHARACTERISTIC_TO_INDUCE_PAIRING = 6;
        public static final int READ_DESCRIPTOR = 4;
        public static final int READ_RSSI = 7;
        public static final int REQUEST_MTU = 8;
        public static final int WRITE_CHARACTERISTIC = 2;
        public static final int WRITE_DESCRIPTOR = 5;
        public static final int WRITE_NO_RESPONSE_CHARACTERISTIC = 3;
    }

    public static Request createCharacteristicNotificationRequest(BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z) {
        return new Request(0, bluetoothGattCharacteristic, null, null, z, 0);
    }

    public static Request createReadCharacteristicRequest(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return new Request(1, bluetoothGattCharacteristic, null, null, false, 0);
    }

    public static Request createReadCharacteristicRequestToInducePairing(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return new Request(6, bluetoothGattCharacteristic, null, null, false, 0);
    }

    public static Request createReadDescriptorRequest(BluetoothGattDescriptor bluetoothGattDescriptor) {
        return new Request(4, null, bluetoothGattDescriptor, null, false, 0);
    }

    public static Request createWriteCharacteristicRequest(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr) {
        return new Request(2, bluetoothGattCharacteristic, null, bArr, false, 0);
    }

    public static Request createWriteNoResponseCharacteristicRequest(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr) {
        return new Request(3, bluetoothGattCharacteristic, null, bArr, false, 0);
    }

    public static Request createWriteDescriptorRequest(BluetoothGattDescriptor bluetoothGattDescriptor, byte[] bArr) {
        return new Request(5, null, bluetoothGattDescriptor, bArr, false, 0);
    }

    public static Request createReadRssiRequest() {
        return new Request(7, null, null, null, false, 0);
    }

    public static Request createMtuRequest(int i) {
        return new Request(8, null, null, null, false, i);
    }

    public static String getRequestTypeLabel(int i) {
        switch (i) {
            case 0:
                return "CHARACTERISTIC_NOTIFICATION";
            case 1:
                return "READ_CHARACTERISTIC";
            case 2:
                return "WRITE_CHARACTERISTIC";
            case 3:
                return "WRITE_NO_RESPONSE_CHARACTERISTIC";
            case 4:
                return "READ_DESCRIPTOR";
            case 5:
                return "WRITE_DESCRIPTOR";
            case 6:
                return "READ_CHARACTERISTIC_TO_INDUCE_PAIRING";
            case 7:
                return "READ_RSSI";
            case 8:
                return "REQUEST_MTU";
            default:
                return "UNKNOWN " + i;
        }
    }

    private Request(int i, BluetoothGattCharacteristic bluetoothGattCharacteristic, BluetoothGattDescriptor bluetoothGattDescriptor, byte[] bArr, boolean z, int i2) {
        this.mType = i;
        this.mCharacteristic = bluetoothGattCharacteristic;
        this.mDescriptor = bluetoothGattDescriptor;
        this.mData = bArr;
        this.mBooleanData = z;
        this.mInteger = i2;
    }

    public BluetoothGattCharacteristic buildWriteCharacteristic() {
        BluetoothGattCharacteristic bluetoothGattCharacteristic;
        if (this.mType != 2 || (bluetoothGattCharacteristic = this.mCharacteristic) == null || (bluetoothGattCharacteristic.getProperties() & 8) <= 0) {
            return null;
        }
        byte[] bArr = this.mData;
        if (bArr != null) {
            this.mCharacteristic.setValue(bArr);
        }
        this.mCharacteristic.setWriteType(2);
        return this.mCharacteristic;
    }

    public BluetoothGattCharacteristic buildWriteNoResponseCharacteristic() {
        BluetoothGattCharacteristic bluetoothGattCharacteristic;
        if (this.mType != 3 || (bluetoothGattCharacteristic = this.mCharacteristic) == null || (bluetoothGattCharacteristic.getProperties() & 4) <= 0) {
            return null;
        }
        byte[] bArr = this.mData;
        if (bArr != null) {
            this.mCharacteristic.setValue(bArr);
        }
        this.mCharacteristic.setWriteType(1);
        return this.mCharacteristic;
    }

    public BluetoothGattCharacteristic buildReadCharacteristic() {
        int i = this.mType;
        if (i == 1 || i == 6) {
            return this.mCharacteristic;
        }
        return null;
    }

    public BluetoothGattCharacteristic buildNotifyCharacteristic() {
        if (this.mType == 0) {
            return this.mCharacteristic;
        }
        return null;
    }

    public BluetoothGattDescriptor buildWriteDescriptor() {
        if (this.mType != 5) {
            return null;
        }
        byte[] bArr = this.mData;
        if (bArr != null) {
            this.mDescriptor.setValue(bArr);
        }
        return this.mDescriptor;
    }

    public BluetoothGattDescriptor buildReadDescriptor() {
        if (this.mType == 4) {
            return this.mDescriptor;
        }
        return null;
    }

    public int getAttempts() {
        return this.mAttempts;
    }

    public int getType() {
        return this.mType;
    }

    public BluetoothGattCharacteristic getCharacteristic() {
        return this.mCharacteristic;
    }

    public BluetoothGattDescriptor getDescriptor() {
        return this.mDescriptor;
    }

    public boolean getBooleanData() {
        return this.mBooleanData;
    }

    public int getInteger() {
        return this.mInteger;
    }

    public void setAttempts(int i) {
        this.mAttempts = i;
    }

    public void increaseAttempts() {
        this.mAttempts++;
    }
}
