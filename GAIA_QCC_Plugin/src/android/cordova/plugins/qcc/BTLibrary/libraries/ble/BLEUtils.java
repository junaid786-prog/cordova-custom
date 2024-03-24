package cordova.plugins.qcc.BTLibrary.libraries.ble;

import cordova.plugins.qcc.BTLibrary.libraries.ble.ErrorStatus;

/* loaded from: classes2.dex */
public class BLEUtils {
    public static String getBondStateName(int i) {
        return i == 10 ? "BOND_NONE" : i == 12 ? "BOND_BONDED" : i == 11 ? "BOND_BONDING" : "UNKNOWN";
    }

    public static String getConnectionStateName(int i) {
        return i == 2 ? "CONNECTED" : i == 1 ? "CONNECTING" : i == 3 ? "DISCONNECTING" : i == 0 ? "DISCONNECTED" : "UNKNOWN";
    }

    public static String getIntToHexadecimal(int i) {
        return String.format("0x%04X", Integer.valueOf(i & 65535));
    }

    public static String getGattStatusName(int i, boolean z) {
        boolean z2;
        String intToHexadecimal = getIntToHexadecimal(i);
        StringBuilder sb = new StringBuilder();
        if (i == 0) {
            sb.append("Status ").append(intToHexadecimal).append(": SUCCESS");
        } else {
            sb.append("Error status ").append(intToHexadecimal).append(": ");
            String bluetoothGattStatusLabel = ErrorStatus.getBluetoothGattStatusLabel(i, z);
            boolean z3 = true;
            if (bluetoothGattStatusLabel.length() > 0) {
                sb.append("\n\t> BluetoothGatt - ").append(bluetoothGattStatusLabel);
                z2 = true;
            } else {
                z2 = false;
            }
            String label = ErrorStatus.ATT.getLabel(i, z);
            if (label.length() > 0) {
                sb.append("\n\t> ATT - ").append(label);
                z2 = true;
            }
            String label2 = ErrorStatus.HCI.getLabel(i, z);
            if (label2.length() > 0) {
                sb.append("\n\t> HCI - ").append(label2);
                z2 = true;
            }
            String label3 = ErrorStatus.GattApi.getLabel(i, z);
            if (label3.length() > 0) {
                sb.append("\n\t> gatt_api.h - ").append(label3);
            } else {
                z3 = z2;
            }
            if (!z3) {
                sb.append("UNDEFINED");
            }
        }
        return sb.toString();
    }
}
