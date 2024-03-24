package cordova.plugins.qcc.BTLibrary.libraries.ble;

import android.util.ArrayMap;
import java.util.UUID;

/* loaded from: classes2.dex */
public class Services {
    private static final String CSR_UUID = "-d102-11e1-9b23-00025b00a5a5";
    private static final String GATT_UUID = "-0000-1000-8000-00805f9b34fb";
    public static final String SERVICE_ALERT_NOTIFICATION = "Alert Notification";
    public static final String SERVICE_BATTERY = "Battery";
    public static final String SERVICE_BLOOD_PRESSURE = "Blood Pressure";
    public static final String SERVICE_CSR_GAIA = "CSR GAIA";
    public static final String SERVICE_CURRENT_TIME = "Current Time Service";
    public static final String SERVICE_CYCLING_POWER = "Cycling Power";
    public static final String SERVICE_CYCLING_SPEED_AND_CADENCE = "Cycling Speed and Cadence";
    public static final String SERVICE_DEVICE_INFORMATION = "Device Information";
    public static final String SERVICE_ENVIRONMENTAL_SENSING = "Environmental Sensing";
    public static final String SERVICE_GENERIC_ACCESS = "Generic Access";
    public static final String SERVICE_GENERIC_ATTRIBUTE = "Generic Attribute";
    public static final String SERVICE_GLUCOSE = "Glucose";
    public static final String SERVICE_HEALTH_THERMOMETER = "Health Thermometer";
    public static final String SERVICE_HEART_RATE = "Heart Rate";
    public static final String SERVICE_HUMAN_INTERFACE_DEVICE = "Human Interface Device";
    public static final String SERVICE_IMMEDIATE_ALERT = "Immediate Alert";
    public static final String SERVICE_LINK_LOSS = "Link Loss";
    public static final String SERVICE_LOCATION_AND_NAVIGATION = "Location and Navigation";
    public static final String SERVICE_NEXT_DST_CHANGE = "Next DST Change Service";
    public static final String SERVICE_PHONE_ALERT_STATUS = "Phone Alert Status Service";
    public static final String SERVICE_REFERENCE_TIME_UPDATE = "Reference Time Update Service";
    public static final String SERVICE_RUNNING_SPEED_AND_CADENCE = "Running Speed and Cadence";
    public static final String SERVICE_SCAN_PARAMETERS = "Scan Parameters";
    public static final String SERVICE_TX_POWER = "Tx Power";
    private static final ArrayMap<String, String> mServices;

    static {
        ArrayMap<String, String> arrayMap = new ArrayMap<>();
        mServices = arrayMap;
        arrayMap.put("00001811-0000-1000-8000-00805f9b34fb", SERVICE_ALERT_NOTIFICATION);
        arrayMap.put("0000180f-0000-1000-8000-00805f9b34fb", SERVICE_BATTERY);
        arrayMap.put("00001810-0000-1000-8000-00805f9b34fb", SERVICE_BLOOD_PRESSURE);
        arrayMap.put("00001805-0000-1000-8000-00805f9b34fb", SERVICE_CURRENT_TIME);
        arrayMap.put("00001818-0000-1000-8000-00805f9b34fb", SERVICE_CYCLING_POWER);
        arrayMap.put("00001816-0000-1000-8000-00805f9b34fb", SERVICE_CYCLING_SPEED_AND_CADENCE);
        arrayMap.put("0000180a-0000-1000-8000-00805f9b34fb", SERVICE_DEVICE_INFORMATION);
        arrayMap.put("0000181a-0000-1000-8000-00805f9b34fb", SERVICE_ENVIRONMENTAL_SENSING);
        arrayMap.put("00001800-0000-1000-8000-00805f9b34fb", SERVICE_GENERIC_ACCESS);
        arrayMap.put("00001801-0000-1000-8000-00805f9b34fb", SERVICE_GENERIC_ATTRIBUTE);
        arrayMap.put("00001808-0000-1000-8000-00805f9b34fb", SERVICE_GLUCOSE);
        arrayMap.put("00001809-0000-1000-8000-00805f9b34fb", SERVICE_HEALTH_THERMOMETER);
        arrayMap.put("0000180d-0000-1000-8000-00805f9b34fb", SERVICE_HEART_RATE);
        arrayMap.put("00001812-0000-1000-8000-00805f9b34fb", SERVICE_HUMAN_INTERFACE_DEVICE);
        arrayMap.put("00001802-0000-1000-8000-00805f9b34fb", SERVICE_IMMEDIATE_ALERT);
        arrayMap.put("00001803-0000-1000-8000-00805f9b34fb", SERVICE_LINK_LOSS);
        arrayMap.put("00001819-0000-1000-8000-00805f9b34fb", SERVICE_LOCATION_AND_NAVIGATION);
        arrayMap.put("00001807-0000-1000-8000-00805f9b34fb", SERVICE_NEXT_DST_CHANGE);
        arrayMap.put("0000180e-0000-1000-8000-00805f9b34fb", SERVICE_PHONE_ALERT_STATUS);
        arrayMap.put("00001806-0000-1000-8000-00805f9b34fb", SERVICE_REFERENCE_TIME_UPDATE);
        arrayMap.put("00001814-0000-1000-8000-00805f9b34fb", SERVICE_RUNNING_SPEED_AND_CADENCE);
        arrayMap.put("00001813-0000-1000-8000-00805f9b34fb", SERVICE_SCAN_PARAMETERS);
        arrayMap.put("00001804-0000-1000-8000-00805f9b34fb", SERVICE_TX_POWER);
        arrayMap.put("00001100-d102-11e1-9b23-00025b00a5a5", SERVICE_CSR_GAIA);
    }

    public static UUID getStringServiceUUID(String str) {
        if (!mServices.containsValue(str)) {
            return null;
        }
        int i = 0;
        while (true) {
            ArrayMap<String, String> arrayMap = mServices;
            if (i >= arrayMap.size()) {
                return null;
            }
            if (arrayMap.valueAt(i).equals(str)) {
                return UUID.fromString(arrayMap.keyAt(i));
            }
            i++;
        }
    }

    public static String getServiceName(String str) {
        String str2 = mServices.get(str);
        return str2 == null ? "Unknown Service" : str2;
    }

    public static boolean isService(String str) {
        return mServices.containsKey(str);
    }
}
