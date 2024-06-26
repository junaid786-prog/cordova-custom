package cordova.plugins.qcc.BTLibrary.libraries.ble;

import android.util.ArrayMap;
import java.util.UUID;

/* loaded from: classes2.dex */
public class Characteristics {
    public static final String CHARACTERISTIC_ALERT_CATEGORY_ID = "Alert Category ID";
    public static final String CHARACTERISTIC_ALERT_CATEGORY_ID_BIT_MASK = "Alert Category ID Bit Mask";
    public static final String CHARACTERISTIC_ALERT_LEVEL = "Alert Level";
    public static final String CHARACTERISTIC_ALERT_NOTIFICATION_CONTROL_POINT = "Alert Notification Control Point";
    public static final String CHARACTERISTIC_ALERT_STATUS = "Alert Status";
    public static final String CHARACTERISTIC_APPARENT_WIND_DIRECTION = "Apparent Wind Direction";
    public static final String CHARACTERISTIC_APPARENT_WIND_SPEED = "Apparent Wind Speed";
    public static final String CHARACTERISTIC_APPEARANCE = "Appearance";
    public static final String CHARACTERISTIC_BATTERY_LEVEL = "Battery Level";
    public static final String CHARACTERISTIC_BLOOD_PRESSURE_FEATURE = "Blood Pressure Feature";
    public static final String CHARACTERISTIC_BLOOD_PRESSURE_MEASUREMENT = "Blood Pressure Measurement";
    public static final String CHARACTERISTIC_BODY_SENSOR_LOCATION = "Body Sensor Location";
    public static final String CHARACTERISTIC_BOOT_KEYBOARD_INPUT_REPORT = "Boot Keyboard Input Report";
    public static final String CHARACTERISTIC_BOOT_KEYBOARD_OUTPUT_REPORT = "Boot Keyboard Output Report";
    public static final String CHARACTERISTIC_BOOT_MOUSE_INPUT_REPORT = "Boot Mouse Input Report";
    public static final String CHARACTERISTIC_CSC_FEATURE = "CSC Feature";
    public static final String CHARACTERISTIC_CSC_MEASUREMENT = "CSC Measurement";
    public static final String CHARACTERISTIC_CSR_ACCELERATION = "CSR Acceleration";
    public static final String CHARACTERISTIC_CSR_ANGULAR_RATE = "CSR Angular Rate";
    public static final String CHARACTERISTIC_CSR_GAIA_COMMAND_ENDPOINT = "CSR GAIA Command Endpoint";
    public static final String CHARACTERISTIC_CSR_GAIA_DATA_ENDPOINT = "CSR GAIA Data Endpoint";
    public static final String CHARACTERISTIC_CSR_GAIA_RESPONSE_ENDPOINT = "CSR GAIA Response Endpoint";
    public static final String CHARACTERISTIC_CSR_MAGNETOMETER_CALIBRATION = "CSR Magnetometer Calibration";
    public static final String CHARACTERISTIC_CURRENT_TIME = "Current Time";
    public static final String CHARACTERISTIC_CYCLING_POWER_CONTROL_POINT = "Cycling Power Control Point";
    public static final String CHARACTERISTIC_CYCLING_POWER_FEATURE = "Cycling Power Feature";
    public static final String CHARACTERISTIC_CYCLING_POWER_MEASUREMENT = "Cycling Power Measurement";
    public static final String CHARACTERISTIC_CYCLING_POWER_VECTOR = "Cycling Power Vector";
    public static final String CHARACTERISTIC_DATE_TIME = "Date Time";
    public static final String CHARACTERISTIC_DAY_DATE_TIME = "Day Date Time";
    public static final String CHARACTERISTIC_DAY_OF_WEEK = "Day of Week";
    public static final String CHARACTERISTIC_DESCRIPTOR_VALUE_CHANGED = "Descriptor Value Changed";
    public static final String CHARACTERISTIC_DEVICE_NAME = "Device Name";
    public static final String CHARACTERISTIC_DST_OFFSET = "DST Offset";
    public static final String CHARACTERISTIC_ELEVATION = "Elevation";
    public static final String CHARACTERISTIC_EXACT_TIME_256 = "Exact Time 256";
    public static final String CHARACTERISTIC_FIRMWARE_REVISION_STRING = "Firmware Revision String";
    public static final String CHARACTERISTIC_GLUCOSE_FEATURE = "Glucose Feature";
    public static final String CHARACTERISTIC_GLUCOSE_MEASUREMENT = "Glucose Measurement";
    public static final String CHARACTERISTIC_GLUCOSE_MEASUREMENT_CONTEXT = "Glucose Measurement Context";
    public static final String CHARACTERISTIC_GUST_FACTOR = "Gust Factor";
    public static final String CHARACTERISTIC_HARDWARE_REVISION = "Hardware Revision String";
    public static final String CHARACTERISTIC_HEART_RATE_CONTROL_POINT = "Heart Rate Control Point";
    public static final String CHARACTERISTIC_HEART_RATE_MEASUREMENT = "Heart Rate Measurement";
    public static final String CHARACTERISTIC_HEAT_INDEX = "Heat Index";
    public static final String CHARACTERISTIC_HID_CONTROL_POINT = "HID Control Point";
    public static final String CHARACTERISTIC_HID_INFORMATION = "HID Information";
    public static final String CHARACTERISTIC_HUMIDITY = "Humidity";

    /* renamed from: CHARACTERISTIC_IEEE_11073_20601_REGULATORY_CERTIFICATION_DATA_LIST */
    public static final String f295xf873077f = "IEEE 11073-20601 Regulatory Certification Data List";
    public static final String CHARACTERISTIC_INTERMEDIATE_CUFF_PRESSURE = "Intermediate Cuff Pressure";
    public static final String CHARACTERISTIC_INTERMEDIATE_TEMPERATURE = "Intermediate Temperature";
    public static final String CHARACTERISTIC_IRRADIANCE = "Irradiance";
    public static final String CHARACTERISTIC_LN_CONTROL_POINT = "LN Control Point";
    public static final String CHARACTERISTIC_LN_FEATURE = "LN Feature";
    public static final String CHARACTERISTIC_LOCAL_TIME_INFORMATION = "Local Time Information";
    public static final String CHARACTERISTIC_LOCATION_AND_SPEED = "Location and Speed";
    public static final String CHARACTERISTIC_MAGNETIC_DECLINATION = "Magnetic Declination";
    public static final String CHARACTERISTIC_MAGNETIC_FLUX_DENSITY_2D = "Magnetic Flux Density - 2D";
    public static final String CHARACTERISTIC_MAGNETIC_FLUX_DENSITY_3D = "Magnetic Flux Density - 3D";
    public static final String CHARACTERISTIC_MANUFACTURER_NAME_STRING = "Manufacturer Name String";
    public static final String CHARACTERISTIC_MEASUREMENT_INTERVAL = "Measurement Interval";
    public static final String CHARACTERISTIC_MODEL_NUMBER_STRING = "Model Number String";
    public static final String CHARACTERISTIC_NAVIGATION = "Navigation";
    public static final String CHARACTERISTIC_NEW_ALERT = "New Alert";
    public static final String CHARACTERISTIC_PERIPHERAL_PREFERRED_CONNECTION_PARAMETERS = "Peripheral Preferred Connection Parameters";
    public static final String CHARACTERISTIC_PERIPHERAL_PRIVACY_FLAG = "Peripheral Privacy Flag";
    public static final String CHARACTERISTIC_PNP_ID = "PnP ID";
    public static final String CHARACTERISTIC_POLLEN_CONCENTRATION = "Pollen concentration";
    public static final String CHARACTERISTIC_POSITION_QUALITY = "Position Quality";
    public static final UUID CHARACTERISTIC_PRESENTATION_FORMAT;
    public static final String CHARACTERISTIC_PRESSURE = "Pressure";
    public static final String CHARACTERISTIC_PROTOCOL_MODE = "Protocol Mode";
    public static final String CHARACTERISTIC_RAINFALL = "Rainfall";
    public static final String CHARACTERISTIC_RECONNECTION_ADDRESS = "Reconnection Address";
    public static final String CHARACTERISTIC_RECORD_ACCESS_CONTROL_POINT = "Record Access Control Point";
    public static final String CHARACTERISTIC_REFERENCE_TIME_INFORMATION = "Reference Time Information";
    public static final String CHARACTERISTIC_REPORT = "Report";
    public static final String CHARACTERISTIC_REPORT_MAP = "Report Map";
    public static final String CHARACTERISTIC_RINGER_CONTROL_POINT = "Ringer Control Point";
    public static final String CHARACTERISTIC_RINGER_SETTING = "Ringer Setting";
    public static final String CHARACTERISTIC_RSC_FEATURE = "RSC Feature";
    public static final String CHARACTERISTIC_RSC_MEASUREMENT = "RSC Measurement";
    public static final String CHARACTERISTIC_SCAN_INTERVAL_WINDOW = "Scan Interval Window";
    public static final String CHARACTERISTIC_SCAN_REFRESH = "Scan Refresh";
    public static final String CHARACTERISTIC_SC_CONTROL_POINT = "SC Control Point";
    public static final String CHARACTERISTIC_SENSOR_LOCATION = "Sensor Location";
    public static final String CHARACTERISTIC_SERIAL_NUMBER_STRING = "Serial Number String";
    public static final String CHARACTERISTIC_SERVICE_CHANGED = "Service Changed";
    public static final String CHARACTERISTIC_SOFTWARE_REVISION_STRING = "Software Revision String";
    public static final String CHARACTERISTIC_SUPPORTED_NEW_ALERT_CATEGORY = "Supported New Alert Category";
    public static final String CHARACTERISTIC_SUPPORTED_UNREAD_ALERT_CATEGORY = "Supported Unread Alert Category";
    public static final String CHARACTERISTIC_SYSTEM_ID = "System ID";
    public static final String CHARACTERISTIC_TEMPERATURE = "Temperature";
    public static final String CHARACTERISTIC_TEMPERATURE_MEASUREMENT = "Temperature Measurement";
    public static final String CHARACTERISTIC_TEMPERATURE_TYPE = "Temperature Type";
    public static final String CHARACTERISTIC_TIME_ACCURACY = "Time Accuracy";
    public static final String CHARACTERISTIC_TIME_SOURCE = "Time Source";
    public static final String CHARACTERISTIC_TIME_UPDATE_CONTROL_POINT = "Time Update Control Point";
    public static final String CHARACTERISTIC_TIME_UPDATE_STATE = "Time Update State";
    public static final String CHARACTERISTIC_TIME_WITH_DST = "Time with DST";
    public static final String CHARACTERISTIC_TIME_ZONE = "Time Zone";
    public static final String CHARACTERISTIC_TRUE_WIND_DIRECTION = "True Wind Direction";
    public static final String CHARACTERISTIC_TRUE_WIND_SPEED = "True Wind Speed";
    public static final String CHARACTERISTIC_TX_POWER_LEVEL = "Tx Power Level";
    public static final String CHARACTERISTIC_UNREAD_ALERT_STATUS = "Unread Alert Status";
    public static final String CHARACTERISTIC_UV_INDEX = "UV Index";
    public static final String CHARACTERISTIC_WIND_CHILL = "Wind Chill";
    public static final UUID CLIENT_CHARACTERISTIC_CONFIG;
    private static final String CSR_UUID = "-d102-11e1-9b23-00025b00a5a5";
    private static final String GATT_UUID = "-0000-1000-8000-00805f9b34fb";
    private static final ArrayMap<String, String> mCharacteristics;

    static {
        ArrayMap<String, String> arrayMap = new ArrayMap<>();
        mCharacteristics = arrayMap;
        CLIENT_CHARACTERISTIC_CONFIG = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");
        CHARACTERISTIC_PRESENTATION_FORMAT = UUID.fromString("00002904-0000-1000-8000-00805f9b34fb");
        arrayMap.put("00002a05-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_SERVICE_CHANGED);
        arrayMap.put("00002a00-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_DEVICE_NAME);
        arrayMap.put("00002a01-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_APPEARANCE);
        arrayMap.put("00002a04-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_PERIPHERAL_PREFERRED_CONNECTION_PARAMETERS);
        arrayMap.put("00002a19-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_BATTERY_LEVEL);
        arrayMap.put("00002a25-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_SERIAL_NUMBER_STRING);
        arrayMap.put("00002a27-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_HARDWARE_REVISION);
        arrayMap.put("00002a26-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_FIRMWARE_REVISION_STRING);
        arrayMap.put("00002a28-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_SOFTWARE_REVISION_STRING);
        arrayMap.put("00002a29-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_MANUFACTURER_NAME_STRING);
        arrayMap.put("00002a50-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_PNP_ID);
        arrayMap.put("00002a4a-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_HID_INFORMATION);
        arrayMap.put("00002a4b-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_REPORT_MAP);
        arrayMap.put("00002a4d-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_REPORT);
        arrayMap.put("00002a4c-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_HID_CONTROL_POINT);
        arrayMap.put("00001101-d102-11e1-9b23-00025b00a5a5", CHARACTERISTIC_CSR_GAIA_COMMAND_ENDPOINT);
        arrayMap.put("00001102-d102-11e1-9b23-00025b00a5a5", CHARACTERISTIC_CSR_GAIA_RESPONSE_ENDPOINT);
        arrayMap.put("00001103-d102-11e1-9b23-00025b00a5a5", CHARACTERISTIC_CSR_GAIA_DATA_ENDPOINT);
        arrayMap.put("00002a43-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_ALERT_CATEGORY_ID);
        arrayMap.put("00002a42-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_ALERT_CATEGORY_ID_BIT_MASK);
        arrayMap.put("00002a06-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_ALERT_LEVEL);
        arrayMap.put("00002a44-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_ALERT_NOTIFICATION_CONTROL_POINT);
        arrayMap.put("00002a3f-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_ALERT_STATUS);
        arrayMap.put("00002a49-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_BLOOD_PRESSURE_FEATURE);
        arrayMap.put("00002a35-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_BLOOD_PRESSURE_MEASUREMENT);
        arrayMap.put("00002a38-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_BODY_SENSOR_LOCATION);
        arrayMap.put("00002a22-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_BOOT_KEYBOARD_INPUT_REPORT);
        arrayMap.put("00002a32-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_BOOT_KEYBOARD_OUTPUT_REPORT);
        arrayMap.put("00002a33-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_BOOT_MOUSE_INPUT_REPORT);
        arrayMap.put("00002a5c-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_CSC_FEATURE);
        arrayMap.put("00002a5b-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_CSC_MEASUREMENT);
        arrayMap.put("00002a2b-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_CURRENT_TIME);
        arrayMap.put("00002a66-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_CYCLING_POWER_CONTROL_POINT);
        arrayMap.put("00002a65-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_CYCLING_POWER_FEATURE);
        arrayMap.put("00002a63-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_CYCLING_POWER_MEASUREMENT);
        arrayMap.put("00002a64-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_CYCLING_POWER_VECTOR);
        arrayMap.put("00002a08-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_DATE_TIME);
        arrayMap.put("00002a0a-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_DAY_DATE_TIME);
        arrayMap.put("00002a09-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_DAY_OF_WEEK);
        arrayMap.put("00002a0d-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_DST_OFFSET);
        arrayMap.put("00002a0c-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_EXACT_TIME_256);
        arrayMap.put("00002a51-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_GLUCOSE_FEATURE);
        arrayMap.put("00002a18-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_GLUCOSE_MEASUREMENT);
        arrayMap.put("00002a34-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_GLUCOSE_MEASUREMENT_CONTEXT);
        arrayMap.put("00002a39-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_HEART_RATE_CONTROL_POINT);
        arrayMap.put("00002a37-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_HEART_RATE_MEASUREMENT);
        arrayMap.put("00002a2a-0000-1000-8000-00805f9b34fb", f295xf873077f);
        arrayMap.put("00002a36-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_INTERMEDIATE_CUFF_PRESSURE);
        arrayMap.put("00002a1e-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_INTERMEDIATE_TEMPERATURE);
        arrayMap.put("00002a6b-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_LN_CONTROL_POINT);
        arrayMap.put("00002a6a-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_LN_FEATURE);
        arrayMap.put("00002a0f-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_LOCAL_TIME_INFORMATION);
        arrayMap.put("00002a67-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_LOCATION_AND_SPEED);
        arrayMap.put("00002a21-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_MEASUREMENT_INTERVAL);
        arrayMap.put("00002a24-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_MODEL_NUMBER_STRING);
        arrayMap.put("00002a68-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_NAVIGATION);
        arrayMap.put("00002a46-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_NEW_ALERT);
        arrayMap.put("00002a04-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_PERIPHERAL_PREFERRED_CONNECTION_PARAMETERS);
        arrayMap.put("00002a02-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_PERIPHERAL_PRIVACY_FLAG);
        arrayMap.put("00002a69-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_POSITION_QUALITY);
        arrayMap.put("00002a4e-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_PROTOCOL_MODE);
        arrayMap.put("00002a03-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_RECONNECTION_ADDRESS);
        arrayMap.put("00002a52-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_RECORD_ACCESS_CONTROL_POINT);
        arrayMap.put("00002a14-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_REFERENCE_TIME_INFORMATION);
        arrayMap.put("00002a40-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_RINGER_CONTROL_POINT);
        arrayMap.put("00002a41-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_RINGER_SETTING);
        arrayMap.put("00002a54-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_RSC_FEATURE);
        arrayMap.put("00002a53-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_RSC_MEASUREMENT);
        arrayMap.put("00002a55-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_SC_CONTROL_POINT);
        arrayMap.put("00002a4f-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_SCAN_INTERVAL_WINDOW);
        arrayMap.put("00002a31-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_SCAN_REFRESH);
        arrayMap.put("00002a5d-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_SENSOR_LOCATION);
        arrayMap.put("00002a47-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_SUPPORTED_NEW_ALERT_CATEGORY);
        arrayMap.put("00002a48-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_SUPPORTED_UNREAD_ALERT_CATEGORY);
        arrayMap.put("00002a23-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_SYSTEM_ID);
        arrayMap.put("00002a1c-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_TEMPERATURE_MEASUREMENT);
        arrayMap.put("00002a1d-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_TEMPERATURE_TYPE);
        arrayMap.put("00002a12-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_TIME_ACCURACY);
        arrayMap.put("00002a13-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_TIME_SOURCE);
        arrayMap.put("00002a16-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_TIME_UPDATE_CONTROL_POINT);
        arrayMap.put("00002a17-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_TIME_UPDATE_STATE);
        arrayMap.put("00002a11-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_TIME_WITH_DST);
        arrayMap.put("00002a0e-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_TIME_ZONE);
        arrayMap.put("00002a07-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_TX_POWER_LEVEL);
        arrayMap.put("00002a45-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_UNREAD_ALERT_STATUS);
        arrayMap.put("00002a7d-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_DESCRIPTOR_VALUE_CHANGED);
        arrayMap.put("00002a73-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_APPARENT_WIND_DIRECTION);
        arrayMap.put("00002a72-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_APPARENT_WIND_SPEED);
        arrayMap.put("0000aaa1-d102-11e1-9b23-00025b00a5a5", CHARACTERISTIC_CSR_ACCELERATION);
        arrayMap.put("0000aaa2-d102-11e1-9b23-00025b00a5a5", CHARACTERISTIC_CSR_ANGULAR_RATE);
        arrayMap.put("0000aaa4-d102-11e1-9b23-00025b00a5a5", CHARACTERISTIC_CSR_MAGNETOMETER_CALIBRATION);
        arrayMap.put("00002a6c-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_ELEVATION);
        arrayMap.put("00002a7a-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_GUST_FACTOR);
        arrayMap.put("00002a7a-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_HEAT_INDEX);
        arrayMap.put("00002a6f-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_HUMIDITY);
        arrayMap.put("00002a77-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_IRRADIANCE);
        arrayMap.put("00002a2c-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_MAGNETIC_DECLINATION);
        arrayMap.put("00002aa0-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_MAGNETIC_FLUX_DENSITY_2D);
        arrayMap.put("00002aa1-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_MAGNETIC_FLUX_DENSITY_3D);
        arrayMap.put("00002a75-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_POLLEN_CONCENTRATION);
        arrayMap.put("00002a6d-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_PRESSURE);
        arrayMap.put("00002a78-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_RAINFALL);
        arrayMap.put("00002a6e-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_TEMPERATURE);
        arrayMap.put("00002a71-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_TRUE_WIND_DIRECTION);
        arrayMap.put("00002a70-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_TRUE_WIND_SPEED);
        arrayMap.put("00002a76-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_UV_INDEX);
        arrayMap.put("00002a79-0000-1000-8000-00805f9b34fb", CHARACTERISTIC_WIND_CHILL);
    }

    public static UUID getCharacteristicUUID(String str) {
        if (!mCharacteristics.containsValue(str)) {
            return null;
        }
        int i = 0;
        while (true) {
            ArrayMap<String, String> arrayMap = mCharacteristics;
            if (i >= arrayMap.size()) {
                return null;
            }
            if (arrayMap.valueAt(i).equals(str)) {
                return UUID.fromString(arrayMap.keyAt(i));
            }
            i++;
        }
    }

    public static String getCharacteristicName(String str) {
        String str2 = mCharacteristics.get(str);
        return str2 == null ? "Unknown Characteristic" : str2;
    }

    public static boolean isCharacteristic(String str) {
        return mCharacteristics.containsKey(str);
    }
}
