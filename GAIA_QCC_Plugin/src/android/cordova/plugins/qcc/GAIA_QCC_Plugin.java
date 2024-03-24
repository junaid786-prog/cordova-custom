package cordova.plugins.qcc;

import android.bluetooth.BluetoothDevice;
import android.os.Build;
import android.util.Log;
import cordova.plugins.qcc.BTLibrary.OnBluetoothConnectionListener;
import cordova.plugins.qcc.BTLibrary.Utils;
import cordova.plugins.qcc.BTLibrary.models.DSPConfig;
import cordova.plugins.qcc.BTLibrary.models.SensorReport;
import cordova.plugins.qcc.BTLibrary.models.equalizer.parameters.Filter;
import cordova.plugins.qcc.GaiaLibrary.OnGaiaUpgradeEventListener;
import cordova.plugins.qcc.GaiaLibrary.gaia.ActionCallbackContext;
import cordova.plugins.qcc.GaiaLibrary.gaia.CommandQueues;
import cordova.plugins.qcc.GaiaLibrary.gaia.CommandSpecification;
import cordova.plugins.qcc.GaiaLibrary.gaia.GAIA;
import cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener;
import cordova.plugins.qcc.GaiaLibrary.gaia.TouchFirmware;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class GAIA_QCC_Plugin extends CordovaPlugin {
    private HashMap<String, BiFunction<JSONArray, CallbackContext, PluginResult>> commandMap;
    private BluetoothDevice mCurrentConnectedDevice;
    private SolosManager mSolosManager;
    private final String ACTION_GET_SERIAL_NUM = "getSerialNum";
    private final String ACTION_READ_AUTO_POWER_OFF_TIMEOUT = "readAutoPowerOffTimeout";
    private final String ACTION_READ_AUTO_POWER_OFF_MODE = "readAutoPowerOffMode";
    private final String ACTION_READ_AUTO_POWER_ON_UPON_UNPLUG_CHARGER = "readAutoPowerOnUponUnplugCharger";
    private final String ACTION_GET_MULTIPOINT = "getMultipoint";
    private final String ACTION_READ_ON_HEAD_VOICE_PROMPT_VOLUME = "readOnHeadVoicePromptVolume";
    private final String ACTION_READ_OFF_HEAD_VOICE_PROMPT_VOLUME = "readOffHeadVoicePromptVolume";
    private final String ACTION_READ_LOST_LINK_ALERT_DURATION = "readLostLinkAlertDuration";
    private final String ACTION_READ_LOST_LINK_ALERT_VOLUME = "readLostLinkAlertVolume";
    private final String ACTION_READ_LOST_LINK_MULTIPLE_DEVICES_ALERT_MODE = "readLostLinkMultipleDevicesAlertMode";
    private final String ACTION_READ_AMBIENT_NOISE_DETECTION_ENABLE = "readAmbientNoiseDetectionEnable";
    private final String ACTION_READ_HAR_DETECTION_ENABLE = "readHarDetectionEnable";
    private final String ACTION_GET_SMART_POWER_OFF_ENABLE = "getSmartPowerOffEnable";
    private final String ACTION_INIT_SENSORS = "initSensors";
    private final String ACTION_GET_TAP_SENSITIVITY = "getTapSensitivity";
    private final String ACTION_READ_POSTURE_REMINDER_VOLUME = "readPostureReminderVolume";
    private final String ACTION_GET_INPUT_CONFIG = "getInputConfig";
    private final String ACTION_GET_WHISPER_DATA_RT_OUT_GAIN = "getWhisperDataRtOutGain";
    private final String ACTION_GET_WHISPER_DATA_RT_OUT_FLAG = "getWhisperDataRtOutFlag";
    private boolean isFirmwareUpgradeStarted = false;
    private final int EQ_FACTOR_FREQUENCY = 3;
    private final int EQ_FACTOR_QUALITY = 4096;
    private final int EQ_FACTOR_GAIN = 60;
    private final int TOUCH_UPDATE_ERROR = 768;
    private final int TOUCH_VERIFY_ERROR = GAIA.COMMAND_GET_CURRENT_RSSI;
    private String TAG = "GAIA_QCC";
    private String mFWVersion = "";
    private String touchFwVer = "";
    private boolean mUnplugChargerPowerOn = false;
    private boolean mMultipoint = false;
    private boolean mLostLinkMultipleDeviceAlert = false;
    private boolean mReadHarEnable = false;
    private boolean mAmbientNoiseDetectionEnable = false;
    private boolean mSmartPowerOff = false;
    private DSPConfig dspConfig = new DSPConfig();
    private CommandQueues commandQueue = new CommandQueues();

    /* renamed from: cd */
    private CalibrationData f297cd = new CalibrationData();
    private long refDate = LocalDateTime.of(2020, 1, 1, 0, 0).atZone(ZoneId.systemDefault()).toEpochSecond();
    private OnBluetoothConnectionListener btDevicesListener = new OnBluetoothConnectionListener() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin.1
        @Override // cordova.plugins.qcc.BTLibrary.OnBluetoothConnectionListener
        public void onBondedDevicesReady(ArrayList<BluetoothDevice> arrayList) {
        }

        @Override // cordova.plugins.qcc.BTLibrary.OnBluetoothConnectionListener
        public void onDeviceErrorConnected() {
        }

        @Override // cordova.plugins.qcc.BTLibrary.OnBluetoothConnectionListener
        public void onScannedDevicesReady(ArrayList<BluetoothDevice> arrayList) {
        }

        @Override // cordova.plugins.qcc.BTLibrary.OnBluetoothConnectionListener
        public void onScannedDeviceScanned(BluetoothDevice bluetoothDevice) {
            if (bluetoothDevice == null || bluetoothDevice.getName() == null) {
                return;
            }
            GAIA_QCC_Plugin.this.onDeviceScanned(bluetoothDevice.getName(), GAIA_QCC_Plugin.this.macAddressToUUID(bluetoothDevice.getAddress()));
        }

        @Override // cordova.plugins.qcc.BTLibrary.OnBluetoothConnectionListener
        public void onBondedDeviceScanned(BluetoothDevice bluetoothDevice) {
            if (bluetoothDevice == null || bluetoothDevice.getName() == null) {
                return;
            }
            GAIA_QCC_Plugin.this.onDeviceScanned(bluetoothDevice.getName(), GAIA_QCC_Plugin.this.macAddressToUUID(bluetoothDevice.getAddress()));
        }

        @Override // cordova.plugins.qcc.BTLibrary.OnBluetoothConnectionListener
        public void onDisconnectedMessage() {
            if (GAIA_QCC_Plugin.this.mCurrentConnectedDevice != null) {
                SolosManager solosManager = GAIA_QCC_Plugin.this.mSolosManager;
                GAIA_QCC_Plugin gAIA_QCC_Plugin = GAIA_QCC_Plugin.this;
                solosManager.disconnect(gAIA_QCC_Plugin.macAddressToUUID(gAIA_QCC_Plugin.mCurrentConnectedDevice.getAddress()));
            }
        }

        @Override // cordova.plugins.qcc.BTLibrary.OnBluetoothConnectionListener
        public void onDeviceDisconnected() {
            GAIA_QCC_Plugin.this.throwUnacknowledgedCallbackContext();
            if (GAIA_QCC_Plugin.this.mCurrentConnectedDevice != null) {
                GAIA_QCC_Plugin.this.mSolosManager.onDeviceDisconnected();
                SolosManager solosManager = GAIA_QCC_Plugin.this.mSolosManager;
                GAIA_QCC_Plugin gAIA_QCC_Plugin = GAIA_QCC_Plugin.this;
                solosManager.disconnect(gAIA_QCC_Plugin.macAddressToUUID(gAIA_QCC_Plugin.mCurrentConnectedDevice.getAddress()));
                GAIA_QCC_Plugin gAIA_QCC_Plugin2 = GAIA_QCC_Plugin.this;
                String name = gAIA_QCC_Plugin2.mCurrentConnectedDevice.getName();
                GAIA_QCC_Plugin gAIA_QCC_Plugin3 = GAIA_QCC_Plugin.this;
                gAIA_QCC_Plugin2.onDisconnected(name, gAIA_QCC_Plugin3.macAddressToUUID(gAIA_QCC_Plugin3.mCurrentConnectedDevice.getAddress()));
                return;
            }
            GAIA_QCC_Plugin.this.onDisconnected();
        }

        @Override // cordova.plugins.qcc.BTLibrary.OnBluetoothConnectionListener
        public void onBluetoothDeviceOn() {
            GAIA_QCC_Plugin.this.onBluetoothOn();
        }

        @Override // cordova.plugins.qcc.BTLibrary.OnBluetoothConnectionListener
        public void onBluetoothDeviceOff() {
            GAIA_QCC_Plugin.this.onBluetoothOff();
        }

        @Override // cordova.plugins.qcc.BTLibrary.OnBluetoothConnectionListener
        public void onDeviceSuccessConnected(BluetoothDevice bluetoothDevice) {
            if (bluetoothDevice == null || bluetoothDevice.getName() == null) {
                return;
            }
            GAIA_QCC_Plugin.this.mCurrentConnectedDevice = bluetoothDevice;
            if (GAIA_QCC_Plugin.this.mCurrentConnectedDevice != null) {
                GAIA_QCC_Plugin.this.commandQueue.getUnacknowledgedCallbackContext();
                GAIA_QCC_Plugin.this.f297cd.reset();
                GAIA_QCC_Plugin gAIA_QCC_Plugin = GAIA_QCC_Plugin.this;
                String name = bluetoothDevice.getName();
                GAIA_QCC_Plugin gAIA_QCC_Plugin2 = GAIA_QCC_Plugin.this;
                gAIA_QCC_Plugin.onConnected(name, gAIA_QCC_Plugin2.macAddressToUUID(gAIA_QCC_Plugin2.mCurrentConnectedDevice.getAddress()));
            }
        }
    };
    private OnSolosGaiaCommandResultListener mCommandResultListener = new OnSolosGaiaCommandResultListener() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin.2
        private boolean isKthBitSet(int i, int i2) {
            return (i & (1 << ((i2 + 1) - 1))) != 0;
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onControlNotSupported() {
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onGetPreset(int i) {
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onI2CRead(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, int i2, byte[] bArr, boolean z) {
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onI2CWrite(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, int i2, boolean z) {
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onIncorrectState(int i) {
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onRead9Axis(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, SensorReport sensorReport) {
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onReadDeviceVoicePromptLang(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i) {
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onReadMotionEvent(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, boolean[] zArr) {
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onReadSensorReportInterval(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, int i2) {
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onReadUARTDataEvent(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, byte[] bArr) {
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onSensorReset(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, boolean[] zArr) {
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onSetLED(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus) {
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onWriteUART(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus) {
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public boolean sendGAIAPacket(byte[] bArr) {
            return false;
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onReadFwVersion(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, int i2, int i3, int i4) {
            if (GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("fwControlQueue").isEmpty()) {
                return;
            }
            ActionCallbackContext poll = GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("fwControlQueue").poll();
            if (gaiaSolosResponseStatus == CommandSpecification.GaiaSolosResponseStatus.SUCCESS) {
                GAIA_QCC_Plugin.this.mFWVersion = "" + i + "." + i2 + "." + i4;
                poll.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, GAIA_QCC_Plugin.this.mFWVersion));
                return;
            }
            poll.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, "Failed to read Firmware Version"));
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onWriteDeviceVoicePromptLang(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus) {
            if (GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("setLanguageEventQueue").isEmpty()) {
                return;
            }
            GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("setLanguageEventQueue").poll().callbackContext.sendPluginResult(gaiaSolosResponseStatus == CommandSpecification.GaiaSolosResponseStatus.SUCCESS ? new PluginResult(PluginResult.Status.OK) : new PluginResult(PluginResult.Status.ERROR, "Failed to set voice prompt language"));
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onReadProximity(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i) {
            if (GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("readProximityEventQueue").isEmpty() || GAIA_QCC_Plugin.this.mCurrentConnectedDevice == null) {
                return;
            }
            GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("readProximityEventQueue").poll().callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
            if (gaiaSolosResponseStatus == CommandSpecification.GaiaSolosResponseStatus.SUCCESS) {
                GAIA_QCC_Plugin gAIA_QCC_Plugin = GAIA_QCC_Plugin.this;
                gAIA_QCC_Plugin.onProximityValueAvailable(gAIA_QCC_Plugin.macAddressToUUID(gAIA_QCC_Plugin.mCurrentConnectedDevice.getAddress()), i);
            }
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onSetASRMode(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, byte b) {
            PluginResult pluginResult;
            if (GAIA_QCC_Plugin.this.mCurrentConnectedDevice == null || GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("setASRModeQueue").isEmpty()) {
                return;
            }
            ActionCallbackContext poll = GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("setASRModeQueue").poll();
            if (gaiaSolosResponseStatus == CommandSpecification.GaiaSolosResponseStatus.SUCCESS) {
                pluginResult = new PluginResult(PluginResult.Status.OK);
            } else {
                pluginResult = new PluginResult(PluginResult.Status.ERROR, "Failed to enable sensor");
            }
            poll.callbackContext.sendPluginResult(pluginResult);
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onReadSensorsReportEnable(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus) {
            if (GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("enableSensorEventQueue").isEmpty()) {
                return;
            }
            GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("enableSensorEventQueue").poll().callbackContext.sendPluginResult(gaiaSolosResponseStatus == CommandSpecification.GaiaSolosResponseStatus.SUCCESS ? new PluginResult(PluginResult.Status.OK) : new PluginResult(PluginResult.Status.ERROR, "Failed to enable sensor"));
            GAIA_QCC_Plugin.this.mSolosManager.getSensorsReportEventStatus();
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onReadSensorsReportEventStatus(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, SensorReport sensorReport) {
            String str = "[";
            for (boolean z : sensorReport.reportEnable1) {
                str = str + z + ", ";
            }
            String str2 = (str + "]\n") + "[";
            for (boolean z2 : sensorReport.reportEnable2) {
                str2 = str2 + z2 + ", ";
            }
            String str3 = str2 + "],  ";
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onSetSensorReportInterval(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i) {
            if (GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("setSensorEventIntervalQueue").isEmpty()) {
                return;
            }
            GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("setSensorEventIntervalQueue").poll().callbackContext.sendPluginResult(gaiaSolosResponseStatus == CommandSpecification.GaiaSolosResponseStatus.SUCCESS ? new PluginResult(PluginResult.Status.OK) : new PluginResult(PluginResult.Status.ERROR, "Failed to set sensor interval"));
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onRead9AxisEvent(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, SensorReport sensorReport) {
            if (GAIA_QCC_Plugin.this.mCurrentConnectedDevice != null && gaiaSolosResponseStatus == CommandSpecification.GaiaSolosResponseStatus.SUCCESS) {
                GAIA_QCC_Plugin gAIA_QCC_Plugin = GAIA_QCC_Plugin.this;
                gAIA_QCC_Plugin.onGAMDataAvailable(gAIA_QCC_Plugin.macAddressToUUID(gAIA_QCC_Plugin.mCurrentConnectedDevice.getAddress()), i, sensorReport.gyro[0], sensorReport.gyro[1], sensorReport.gyro[2], sensorReport.acc[0], sensorReport.acc[1], sensorReport.acc[2], sensorReport.magnet[0], sensorReport.magnet[1], sensorReport.magnet[2], sensorReport.vad[0]);
            }
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onReadStepCountsEvent(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, int i2) {
            if (GAIA_QCC_Plugin.this.mCurrentConnectedDevice != null && gaiaSolosResponseStatus == CommandSpecification.GaiaSolosResponseStatus.SUCCESS) {
                GAIA_QCC_Plugin gAIA_QCC_Plugin = GAIA_QCC_Plugin.this;
                gAIA_QCC_Plugin.onStepCountAvailable(gAIA_QCC_Plugin.macAddressToUUID(gAIA_QCC_Plugin.mCurrentConnectedDevice.getAddress()), i, i2);
            }
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onReadGestureEvent(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, boolean[] zArr, int i2) {
            int i3;
            if (GAIA_QCC_Plugin.this.mCurrentConnectedDevice != null && gaiaSolosResponseStatus == CommandSpecification.GaiaSolosResponseStatus.SUCCESS) {
                if (zArr[0]) {
                    switch (i2) {
                        case 1:
                            i3 = 2;
                            break;
                        case 2:
                            i3 = 3;
                            break;
                        case 3:
                            i3 = 6;
                            break;
                        case 4:
                            i3 = 7;
                            break;
                        case 5:
                            i3 = 8;
                            break;
                        case 6:
                            i3 = 9;
                            break;
                        default:
                            i3 = 1000;
                            break;
                    }
                    GAIA_QCC_Plugin gAIA_QCC_Plugin = GAIA_QCC_Plugin.this;
                    gAIA_QCC_Plugin.onGestureChanged(gAIA_QCC_Plugin.macAddressToUUID(gAIA_QCC_Plugin.mCurrentConnectedDevice.getAddress()), i3);
                }
                if (zArr[1]) {
                    GAIA_QCC_Plugin gAIA_QCC_Plugin2 = GAIA_QCC_Plugin.this;
                    gAIA_QCC_Plugin2.onGestureChanged(gAIA_QCC_Plugin2.macAddressToUUID(gAIA_QCC_Plugin2.mCurrentConnectedDevice.getAddress()), 1000);
                }
                if (zArr[2]) {
                    GAIA_QCC_Plugin gAIA_QCC_Plugin3 = GAIA_QCC_Plugin.this;
                    gAIA_QCC_Plugin3.onGestureChanged(gAIA_QCC_Plugin3.macAddressToUUID(gAIA_QCC_Plugin3.mCurrentConnectedDevice.getAddress()), i2 != 1 ? 1 : 0);
                }
                if (zArr[3]) {
                    GAIA_QCC_Plugin gAIA_QCC_Plugin4 = GAIA_QCC_Plugin.this;
                    gAIA_QCC_Plugin4.onGestureChanged(gAIA_QCC_Plugin4.macAddressToUUID(gAIA_QCC_Plugin4.mCurrentConnectedDevice.getAddress()), i2 == 1 ? 4 : 5);
                }
            }
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onFactoryReset(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus) {
            if (GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("fwControlQueue").isEmpty()) {
                return;
            }
            ActionCallbackContext poll = GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("fwControlQueue").poll();
            if (gaiaSolosResponseStatus == CommandSpecification.GaiaSolosResponseStatus.SUCCESS) {
                poll.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
            } else {
                poll.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, "Failed to do factory reset"));
            }
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onReadConfigParam(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, int i2, int i3, int i4, SensorReport sensorReport) {
            PluginResult pluginResult;
            if (CommandQueues.readConfigRegEventQueue.isEmpty()) {
                return;
            }
            ActionCallbackContext poll = CommandQueues.readConfigRegEventQueue.poll();
            if (gaiaSolosResponseStatus != CommandSpecification.GaiaSolosResponseStatus.SUCCESS) {
                pluginResult = new PluginResult(PluginResult.Status.ERROR, "Failed to read config register");
            } else if (i2 == 16 || i2 == 17) {
                pluginResult = new PluginResult(PluginResult.Status.OK, i4);
            } else if (i3 == 2) {
                int i5 = i4 & 255;
                boolean z = (i4 & 8192) == 0;
                boolean z2 = (i4 & 16384) == 0;
                boolean z3 = (i4 & 32768) == 0;
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("value", i5);
                    jSONObject.put("x", z);
                    jSONObject.put("y", z2);
                    jSONObject.put("z", z3);
                    pluginResult = new PluginResult(PluginResult.Status.OK, jSONObject);
                } catch (Exception e) {
                    pluginResult = new PluginResult(PluginResult.Status.ERROR, e.getMessage());
                }
            } else {
                if (i3 == 0) {
                    GAIA_QCC_Plugin.this.mSolosManager.setSensorReport(sensorReport);
                }
                pluginResult = new PluginResult(PluginResult.Status.OK, i4);
            }
            poll.callbackContext.sendPluginResult(pluginResult);
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onReadConfigParam(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, int i2, int i3, int i4) {
            PluginResult pluginResult;
            if (CommandQueues.readConfigRegEventQueue.isEmpty()) {
                return;
            }
            ActionCallbackContext poll = CommandQueues.readConfigRegEventQueue.poll();
            String str = poll.action;
            if (gaiaSolosResponseStatus == CommandSpecification.GaiaSolosResponseStatus.SUCCESS) {
                pluginResult = null;
                if (i2 == 2) {
                    if (i3 == 1) {
                        if (str.equals("readAutoPowerOffMode")) {
                            pluginResult = new PluginResult(PluginResult.Status.OK, i4 == 1);
                        }
                    } else if (i3 == 5) {
                        GAIA_QCC_Plugin.this.mUnplugChargerPowerOn = isKthBitSet(i4, 0);
                        GAIA_QCC_Plugin.this.mMultipoint = isKthBitSet(i4, 1);
                        GAIA_QCC_Plugin.this.mLostLinkMultipleDeviceAlert = isKthBitSet(i4, 2);
                        GAIA_QCC_Plugin.this.mReadHarEnable = isKthBitSet(i4, 4);
                        GAIA_QCC_Plugin.this.mAmbientNoiseDetectionEnable = isKthBitSet(i4, 5);
                        GAIA_QCC_Plugin.this.mSmartPowerOff = isKthBitSet(i4, 7);
                        if (str.equals("readAutoPowerOnUponUnplugCharger")) {
                            pluginResult = new PluginResult(PluginResult.Status.OK, GAIA_QCC_Plugin.this.mUnplugChargerPowerOn);
                        } else if (str.equals("getMultipoint")) {
                            pluginResult = new PluginResult(PluginResult.Status.OK, GAIA_QCC_Plugin.this.mMultipoint);
                        } else if (str.equals("readLostLinkMultipleDevicesAlertMode")) {
                            pluginResult = new PluginResult(PluginResult.Status.OK, GAIA_QCC_Plugin.this.mLostLinkMultipleDeviceAlert);
                        } else if (str.equals("readHarDetectionEnable")) {
                            pluginResult = new PluginResult(PluginResult.Status.OK, GAIA_QCC_Plugin.this.mReadHarEnable);
                        } else if (str.equals("readAmbientNoiseDetectionEnable")) {
                            pluginResult = new PluginResult(PluginResult.Status.OK, GAIA_QCC_Plugin.this.mAmbientNoiseDetectionEnable);
                        } else if (str.equals("getSmartPowerOffEnable")) {
                            pluginResult = new PluginResult(PluginResult.Status.OK, GAIA_QCC_Plugin.this.mSmartPowerOff);
                        }
                    }
                } else if (i2 == 4) {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("inputLo", i4 & 255);
                        jSONObject.put("inputHi", (i4 >> 8) & 255);
                        if (str.equals("getInputConfig")) {
                            pluginResult = new PluginResult(PluginResult.Status.OK, jSONObject);
                        }
                    } catch (Exception e) {
                        pluginResult = new PluginResult(PluginResult.Status.ERROR, e.getMessage());
                    }
                }
                if (pluginResult == null) {
                    pluginResult = new PluginResult(PluginResult.Status.OK, i4);
                }
            } else {
                pluginResult = new PluginResult(PluginResult.Status.ERROR, "Failed to read config register");
            }
            poll.callbackContext.sendPluginResult(pluginResult);
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onReadConfigParam(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, int i2, int i3, byte[] bArr) {
            PluginResult pluginResult;
            if (CommandQueues.readConfigRegEventQueue.isEmpty()) {
                return;
            }
            ActionCallbackContext poll = CommandQueues.readConfigRegEventQueue.poll();
            if (gaiaSolosResponseStatus == CommandSpecification.GaiaSolosResponseStatus.SUCCESS) {
                pluginResult = new PluginResult(PluginResult.Status.OK, bArr);
                if (i2 == 5 && (i3 == 1 || i3 == 0)) {
                    pluginResult = new PluginResult(PluginResult.Status.OK, new String(bArr).trim());
                }
            } else {
                pluginResult = new PluginResult(PluginResult.Status.ERROR, "Failed to read config register");
            }
            poll.callbackContext.sendPluginResult(pluginResult);
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onWriteConfigParam(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, int i2, int i3) {
            PluginResult pluginResult;
            if (GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("writeConfigRegEventQueue").isEmpty()) {
                return;
            }
            ActionCallbackContext poll = GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("writeConfigRegEventQueue").poll();
            if (gaiaSolosResponseStatus == CommandSpecification.GaiaSolosResponseStatus.SUCCESS) {
                if (poll.action.equals("setTranslationModeRt")) {
                    GAIA_QCC_Plugin.this.onWriteConfigParam4SetTranslationModeRt(poll, i, i2, i3);
                    return;
                }
                pluginResult = new PluginResult(PluginResult.Status.OK);
            } else {
                pluginResult = new PluginResult(PluginResult.Status.ERROR, "Failed to write config register");
            }
            poll.callbackContext.sendPluginResult(pluginResult);
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onSetVolatileConfigTimeout(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, byte b) {
            PluginResult pluginResult;
            if (GAIA_QCC_Plugin.this.mCurrentConnectedDevice == null || GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("setVolatileConfigTimeoutQueue").isEmpty()) {
                return;
            }
            ActionCallbackContext poll = GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("setVolatileConfigTimeoutQueue").poll();
            if (gaiaSolosResponseStatus == CommandSpecification.GaiaSolosResponseStatus.SUCCESS) {
                pluginResult = new PluginResult(PluginResult.Status.OK);
            } else {
                pluginResult = new PluginResult(PluginResult.Status.ERROR, "Failed to set volatile config timeout");
            }
            poll.callbackContext.sendPluginResult(pluginResult);
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onReadBatteryLevel(int i) {
            if (GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("readBatteryEventQueue").isEmpty() || GAIA_QCC_Plugin.this.mCurrentConnectedDevice == null) {
                return;
            }
            GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("readBatteryEventQueue").poll().callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
            GAIA_QCC_Plugin gAIA_QCC_Plugin = GAIA_QCC_Plugin.this;
            gAIA_QCC_Plugin.onBatteryValueAvailable(gAIA_QCC_Plugin.macAddressToUUID(gAIA_QCC_Plugin.mCurrentConnectedDevice.getAddress()), i, 0);
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onWriteDSPConfigParam(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, byte[] bArr) {
            String str;
            int i = (bArr[2] & 255) | ((bArr[0] & 255) << 16) | ((bArr[1] & 255) << 8);
            if (i == 6291588) {
                GAIA_QCC_Plugin.this.mSolosManager.getVoiceEqualizerGain();
                return;
            }
            if (i == 6291590) {
                GAIA_QCC_Plugin.this.mSolosManager.getVoiceProEqualizerGain();
                return;
            }
            if (i == 6291592) {
                str = "setWhisperFeature";
            } else if (i == 6291650) {
                str = "setGyroAccelMagnetCalibrationQueue";
            } else if (i != 6291586) {
                return;
            } else {
                str = "setWhisperOn";
            }
            if (GAIA_QCC_Plugin.this.commandQueue.str2Queue.get(str).isEmpty()) {
                return;
            }
            ActionCallbackContext poll = GAIA_QCC_Plugin.this.commandQueue.str2Queue.get(str).poll();
            if (gaiaSolosResponseStatus == CommandSpecification.GaiaSolosResponseStatus.SUCCESS) {
                poll.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
            } else {
                poll.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR));
            }
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onReadDSPConfigParam(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, byte[] bArr) {
            if (!GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("readDSPConfigParam").isEmpty()) {
                GAIA_QCC_Plugin.this.combineCalibrationData(gaiaSolosResponseStatus, bArr);
                GAIA_QCC_Plugin.this.combineWhisperDownlinkData(gaiaSolosResponseStatus, bArr);
                GAIA_QCC_Plugin.this.combineWhisperEQData(gaiaSolosResponseStatus, bArr);
            } else {
                if (!GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("getVoiceEqualizerEnable").isEmpty()) {
                    GAIA_QCC_Plugin.this.checkVoiceDownlinkEnable(gaiaSolosResponseStatus, bArr);
                    return;
                }
                if (!GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("getVoiceProEqualizerEnable").isEmpty()) {
                    GAIA_QCC_Plugin.this.checkVoiceEQEnable(gaiaSolosResponseStatus, bArr);
                } else if (!GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("getWhisperFeature").isEmpty()) {
                    GAIA_QCC_Plugin.this.checkWhisperFeature(gaiaSolosResponseStatus, bArr);
                } else {
                    if (GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("getWhisperOn").isEmpty()) {
                        return;
                    }
                    GAIA_QCC_Plugin.this.checkWhisperOn(gaiaSolosResponseStatus, bArr);
                }
            }
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onGetControlActivationState(int i, boolean z) {
            if (GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("getUserEqualizerOnQueue").isEmpty()) {
                return;
            }
            ActionCallbackContext poll = GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("getUserEqualizerOnQueue").poll();
            if (i == 3) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("band", 0);
                    jSONObject.put("data", z);
                    poll.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, jSONObject));
                    return;
                } catch (Exception e) {
                    poll.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, e.getMessage()));
                    return;
                }
            }
            poll.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, "Control is not custom EQ"));
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onControlNotSupported(int i) {
            PluginResult pluginResult = new PluginResult(PluginResult.Status.ERROR, "Incorrect State");
            while (!GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("setUserEqualizerOnQueue").isEmpty()) {
                GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("setUserEqualizerOnQueue").poll().callbackContext.sendPluginResult(pluginResult);
            }
            while (!GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("getUserEqualizerOnQueue").isEmpty()) {
                GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("getUserEqualizerOnQueue").poll().callbackContext.sendPluginResult(pluginResult);
            }
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onSetEQParameter() {
            PluginResult pluginResult = new PluginResult(PluginResult.Status.OK);
            while (!GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("setMusicEqualizerFilterTypeQueue").isEmpty()) {
                GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("setMusicEqualizerFilterTypeQueue").poll().callbackContext.sendPluginResult(pluginResult);
            }
            while (!GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("setMusicEqualizerFrequencyQueue").isEmpty()) {
                GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("setMusicEqualizerFrequencyQueue").poll().callbackContext.sendPluginResult(pluginResult);
            }
            while (!GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("setMusicEqualizerGainQueue").isEmpty()) {
                GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("setMusicEqualizerGainQueue").poll().callbackContext.sendPluginResult(pluginResult);
            }
            while (!GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("setMusicEqualizerQFactorQueue").isEmpty()) {
                GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("setMusicEqualizerQFactorQueue").poll().callbackContext.sendPluginResult(pluginResult);
            }
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onSetUserEQControl() {
            PluginResult pluginResult = new PluginResult(PluginResult.Status.OK);
            while (!GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("setUserEqualizerOnQueue").isEmpty()) {
                GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("setUserEqualizerOnQueue").poll().callbackContext.sendPluginResult(pluginResult);
            }
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onGetMasterGain(int i) {
            if (GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("getMusicEqualizerMasterGainQueue").isEmpty()) {
                return;
            }
            ActionCallbackContext poll = GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("getMusicEqualizerMasterGainQueue").poll();
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("band", 0);
                jSONObject.put("data", Math.round((i * 1.0d) / 60.0d));
                poll.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, jSONObject));
            } catch (Exception e) {
                poll.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, e.getMessage()));
            }
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onGetFrequency(int i, int i2) {
            if (GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("getMusicEqualizerFrequencyQueue").isEmpty()) {
                return;
            }
            ActionCallbackContext poll = GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("getMusicEqualizerFrequencyQueue").poll();
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("band", i);
                jSONObject.put("data", Math.round((i2 * 1.0d) / 3.0d));
                poll.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, jSONObject));
            } catch (Exception e) {
                poll.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, e.getMessage()));
            }
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onGetGain(int i, int i2) {
            if (GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("getMusicEqualizerGainQueue").isEmpty()) {
                return;
            }
            short s = (short) i2;
            ActionCallbackContext poll = GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("getMusicEqualizerGainQueue").poll();
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("band", i);
                jSONObject.put("data", Math.round((s * 1.0d) / 60.0d));
                poll.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, jSONObject));
            } catch (Exception e) {
                poll.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, e.getMessage()));
            }
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onGetFilter(int i, Filter filter) {
            if (GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("getMusicEqualizerFilterTypeQueue").isEmpty()) {
                return;
            }
            ActionCallbackContext poll = GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("getMusicEqualizerFilterTypeQueue").poll();
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("band", i);
                jSONObject.put("data", filter.ordinal());
                poll.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, jSONObject));
            } catch (Exception e) {
                poll.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, e.getMessage()));
            }
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onGetQuality(int i, int i2) {
            if (GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("getMusicEqualizerQFactorQueue").isEmpty()) {
                return;
            }
            ActionCallbackContext poll = GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("getMusicEqualizerQFactorQueue").poll();
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("band", i);
                jSONObject.put("data", (i2 * 1.0d) / 4096.0d);
                poll.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, jSONObject));
            } catch (Exception e) {
                poll.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, e.getMessage()));
            }
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onIncorrectState() {
            PluginResult pluginResult = new PluginResult(PluginResult.Status.ERROR, "Incorrect State");
            while (!GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("setMusicEqualizerFilterTypeQueue").isEmpty()) {
                GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("setMusicEqualizerFilterTypeQueue").poll().callbackContext.sendPluginResult(pluginResult);
            }
            while (!GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("setMusicEqualizerFrequencyQueue").isEmpty()) {
                GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("setMusicEqualizerFrequencyQueue").poll().callbackContext.sendPluginResult(pluginResult);
            }
            while (!GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("setMusicEqualizerGainQueue").isEmpty()) {
                GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("setMusicEqualizerGainQueue").poll().callbackContext.sendPluginResult(pluginResult);
            }
            while (!GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("setMusicEqualizerQFactorQueue").isEmpty()) {
                GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("setMusicEqualizerQFactorQueue").poll().callbackContext.sendPluginResult(pluginResult);
            }
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onResetDevice() {
            if (GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("resetDeviceEventQueue").isEmpty()) {
                return;
            }
            GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("resetDeviceEventQueue").poll().callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onUARTEnable(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, boolean z) {
            if (GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("enableUartQueue").isEmpty()) {
                return;
            }
            ActionCallbackContext poll = GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("enableUartQueue").poll();
            if (gaiaSolosResponseStatus == CommandSpecification.GaiaSolosResponseStatus.SUCCESS) {
                poll.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, z));
            } else {
                poll.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, false));
            }
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onSetRTC(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus) {
            if (GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("setRTCQueue").isEmpty()) {
                return;
            }
            GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("setRTCQueue").poll().callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onSet247Monitor(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus) {
            if (GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("set247MonitorQueue").isEmpty()) {
                return;
            }
            GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("set247MonitorQueue").poll().callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onGet247Monitor(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, boolean z, boolean z2) {
            if (GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("get247MonitorQueue").isEmpty()) {
                return;
            }
            ActionCallbackContext poll = GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("get247MonitorQueue").poll();
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("stepCount", z);
                jSONObject.put("posture", z2);
                poll.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, jSONObject));
            } catch (Exception e) {
                poll.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, e.getMessage()));
            }
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onSet247Reporting(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus) {
            if (GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("set247ReportingQueue").isEmpty()) {
                return;
            }
            GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("set247ReportingQueue").poll().callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onGet247Reporting(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, boolean z, boolean z2, boolean z3, int i) {
            if (GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("get247ReportingQueue").isEmpty()) {
                return;
            }
            ActionCallbackContext poll = GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("get247ReportingQueue").poll();
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("stepCount", z);
                jSONObject.put("posture3030", z2);
                jSONObject.put("posture0060", z3);
                jSONObject.put("startTime", i + ((int) GAIA_QCC_Plugin.this.refDate));
                poll.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, jSONObject));
            } catch (Exception e) {
                poll.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, e.getMessage()));
            }
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onSet247PostureConfig(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus) {
            if (GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("set247PostureConfigQueue").isEmpty()) {
                return;
            }
            GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("set247PostureConfigQueue").poll().callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onGet247PostureConfig(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, int i2, int i3, int i4) {
            if (GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("get247PostureConfigQueue").isEmpty()) {
                return;
            }
            ActionCallbackContext poll = GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("get247PostureConfigQueue").poll();
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("pitchRef", i);
                jSONObject.put("pitchMax", i2);
                jSONObject.put("maxDur", i3);
                if (i4 == CommandSpecification.AlertMethod.NONE.ordinal()) {
                    jSONObject.put("alertMethod", "none");
                } else if (i4 == CommandSpecification.AlertMethod.BEEP.ordinal()) {
                    jSONObject.put("alertMethod", "beep");
                } else {
                    jSONObject.put("alertMethod", "voice");
                }
                poll.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, jSONObject));
            } catch (Exception e) {
                poll.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, e.getMessage()));
            }
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onGet247StepCount(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, int i2, int i3) {
            if (GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("get247StepCountQueue").isEmpty()) {
                return;
            }
            ActionCallbackContext poll = GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("get247StepCountQueue").poll();
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("startTime", i + ((int) GAIA_QCC_Plugin.this.refDate));
                jSONObject.put("dur", i2);
                jSONObject.put("stepCount", i3);
                poll.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, jSONObject));
            } catch (Exception e) {
                poll.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, e.getMessage()));
            }
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onDelete247StepCount(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, int i2) {
            if (GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("delete247StepCountQueue").isEmpty()) {
                return;
            }
            ActionCallbackContext poll = GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("delete247StepCountQueue").poll();
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("startTime", i + ((int) GAIA_QCC_Plugin.this.refDate));
                jSONObject.put("dur", i2);
                poll.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, jSONObject));
            } catch (Exception e) {
                poll.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, e.getMessage()));
            }
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onGet247Posture(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, int i2, int[] iArr, int[] iArr2) {
            if (GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("get247PostureQueue").isEmpty()) {
                return;
            }
            ActionCallbackContext poll = GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("get247PostureQueue").poll();
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("startTime", i + ((int) GAIA_QCC_Plugin.this.refDate));
                jSONObject.put("dur", i2);
                jSONObject.put("tiltUp0_5", iArr[0]);
                jSONObject.put("tiltUp5_10", iArr[1]);
                jSONObject.put("tiltUp10_15", iArr[2]);
                jSONObject.put("tiltUp15_20", iArr[3]);
                jSONObject.put("tiltUp20_25", iArr[4]);
                jSONObject.put("tiltUp25_30", iArr[5]);
                jSONObject.put("tiltUp30_", iArr[6]);
                jSONObject.put("tiltDn0_5", iArr2[0]);
                jSONObject.put("tiltDn5_10", iArr2[1]);
                jSONObject.put("tiltDn10_15", iArr2[2]);
                jSONObject.put("tiltDn15_20", iArr2[3]);
                jSONObject.put("tiltDn20_25", iArr2[4]);
                jSONObject.put("tiltDn25_30", iArr2[5]);
                jSONObject.put("tiltDn30_", iArr2[6]);
                poll.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, jSONObject));
            } catch (Exception e) {
                poll.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, e.getMessage()));
            }
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onGet247Posture(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16) {
            if (GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("get247PostureQueue").isEmpty()) {
                return;
            }
            ActionCallbackContext poll = GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("get247PostureQueue").poll();
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("startTime", ((int) GAIA_QCC_Plugin.this.refDate) + i);
                jSONObject.put("dur", i2);
                jSONObject.put("tiltUp0_", i3);
                jSONObject.put("tiltDn0_5", i4);
                jSONObject.put("tiltDn5_10", i5);
                jSONObject.put("tiltDn10_15", i6);
                jSONObject.put("tiltDn15_20", i7);
                jSONObject.put("tiltDn20_25", i8);
                jSONObject.put("tiltDn25_30", i9);
                jSONObject.put("tiltDn30_35", i10);
                jSONObject.put("tiltDn35_40", i11);
                jSONObject.put("tiltDn40_45", i12);
                jSONObject.put("tiltDn45_50", i13);
                jSONObject.put("tiltDn50_55", i14);
                jSONObject.put("tiltDn55_60", i15);
                jSONObject.put("tiltDn60_", i16);
                poll.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, jSONObject));
            } catch (Exception e) {
                poll.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, e.getMessage()));
            }
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onDelete247Posture(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, int i2) {
            if (GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("delete247PostureQueue").isEmpty()) {
                return;
            }
            ActionCallbackContext poll = GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("delete247PostureQueue").poll();
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("startTime", i + ((int) GAIA_QCC_Plugin.this.refDate));
                jSONObject.put("dur", i2);
                poll.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, jSONObject));
            } catch (Exception e) {
                poll.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, e.getMessage()));
            }
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onRead247PostureMonitorEvent(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, int[] iArr, int[] iArr2) {
            if (GAIA_QCC_Plugin.this.mCurrentConnectedDevice != null) {
                GAIA_QCC_Plugin gAIA_QCC_Plugin = GAIA_QCC_Plugin.this;
                gAIA_QCC_Plugin.on247PostureAvailable(gAIA_QCC_Plugin.macAddressToUUID(gAIA_QCC_Plugin.mCurrentConnectedDevice.getAddress()), i, iArr, iArr2);
            }
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onRead247PostureMonitorEvent(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15) {
            if (GAIA_QCC_Plugin.this.mCurrentConnectedDevice != null) {
                GAIA_QCC_Plugin gAIA_QCC_Plugin = GAIA_QCC_Plugin.this;
                gAIA_QCC_Plugin.on247PostureAvailable(gAIA_QCC_Plugin.macAddressToUUID(gAIA_QCC_Plugin.mCurrentConnectedDevice.getAddress()), i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15);
            }
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onRead247StepCountEvent(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, int i2) {
            if (GAIA_QCC_Plugin.this.mCurrentConnectedDevice != null) {
                GAIA_QCC_Plugin gAIA_QCC_Plugin = GAIA_QCC_Plugin.this;
                gAIA_QCC_Plugin.on247StepCountAvailable(gAIA_QCC_Plugin.macAddressToUUID(gAIA_QCC_Plugin.mCurrentConnectedDevice.getAddress()), i, i2);
            }
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onRebootDevice(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, byte[] bArr) {
            if (GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("rebootDeviceEventQueue").isEmpty()) {
                return;
            }
            GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("rebootDeviceEventQueue").poll().callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onSetAvEvents(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
            if (GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("avRemoteControlQueue").isEmpty()) {
                return;
            }
            ActionCallbackContext poll = GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("avRemoteControlQueue").poll();
            if (gaiaSolosResponseStatus == CommandSpecification.GaiaSolosResponseStatus.SUCCESS) {
                poll.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, true));
            } else {
                poll.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, "Failed to set av events"));
            }
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onGetAvEvents(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
            if (GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("avRemoteControlQueue").isEmpty()) {
                return;
            }
            ActionCallbackContext poll = GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("avRemoteControlQueue").poll();
            if (gaiaSolosResponseStatus == CommandSpecification.GaiaSolosResponseStatus.SUCCESS) {
                poll.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, true));
            } else {
                poll.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, "Failed to get av events"));
            }
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onGetAvInfo(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus) {
            if (GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("avRemoteControlQueue").isEmpty()) {
                return;
            }
            ActionCallbackContext poll = GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("avRemoteControlQueue").poll();
            if (gaiaSolosResponseStatus == CommandSpecification.GaiaSolosResponseStatus.SUCCESS) {
                poll.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
            } else {
                poll.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, "Failed to enable get av info"));
            }
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onSetAvControl(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus) {
            if (GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("avRemoteControlQueue").isEmpty()) {
                return;
            }
            ActionCallbackContext poll = GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("avRemoteControlQueue").poll();
            if (gaiaSolosResponseStatus == CommandSpecification.GaiaSolosResponseStatus.SUCCESS) {
                poll.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
            } else {
                poll.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, "Invalid parameter"));
            }
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
        public void onReadAvInfoEvents(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, byte b, byte[] bArr) {
            if (GAIA_QCC_Plugin.this.mCurrentConnectedDevice != null) {
                if (b == Byte.MIN_VALUE) {
                    int i = bArr[0] & 255;
                    GAIA_QCC_Plugin gAIA_QCC_Plugin = GAIA_QCC_Plugin.this;
                    gAIA_QCC_Plugin.onAvEventPlayState(gAIA_QCC_Plugin.macAddressToUUID(gAIA_QCC_Plugin.mCurrentConnectedDevice.getAddress()), i);
                    return;
                }
                if (b == 4) {
                    int i2 = ((bArr[0] & 255) << 24) | ((bArr[1] & 255) << 16) | ((bArr[2] & 255) << 8) | (bArr[3] & 255);
                    GAIA_QCC_Plugin gAIA_QCC_Plugin2 = GAIA_QCC_Plugin.this;
                    gAIA_QCC_Plugin2.onAvEventTotalPlayTime(gAIA_QCC_Plugin2.macAddressToUUID(gAIA_QCC_Plugin2.mCurrentConnectedDevice.getAddress()), i2);
                    return;
                }
                if (b == 8) {
                    int i3 = ((bArr[0] & 255) << 24) | ((bArr[1] & 255) << 16) | ((bArr[2] & 255) << 8) | (bArr[3] & 255);
                    GAIA_QCC_Plugin gAIA_QCC_Plugin3 = GAIA_QCC_Plugin.this;
                    gAIA_QCC_Plugin3.onAvEventCurrentPlayTime(gAIA_QCC_Plugin3.macAddressToUUID(gAIA_QCC_Plugin3.mCurrentConnectedDevice.getAddress()), i3);
                } else if (b == 1) {
                    String str = new String(bArr, StandardCharsets.UTF_8);
                    GAIA_QCC_Plugin gAIA_QCC_Plugin4 = GAIA_QCC_Plugin.this;
                    gAIA_QCC_Plugin4.onAvEventTitle(gAIA_QCC_Plugin4.macAddressToUUID(gAIA_QCC_Plugin4.mCurrentConnectedDevice.getAddress()), str);
                } else {
                    if (b == 2) {
                        String str2 = new String(bArr, StandardCharsets.UTF_8);
                        GAIA_QCC_Plugin gAIA_QCC_Plugin5 = GAIA_QCC_Plugin.this;
                        gAIA_QCC_Plugin5.onAvEventArtist(gAIA_QCC_Plugin5.macAddressToUUID(gAIA_QCC_Plugin5.mCurrentConnectedDevice.getAddress()), str2);
                        return;
                    }
                    Log.e(GAIA_QCC_Plugin.this.TAG, "Unknown info type = " + ((int) b));
                }
            }
        }
    };
    private TouchFirmware.TouchFirmwareListener mTouchUpdateEventListener = new TouchFirmware.TouchFirmwareListener() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin.3
        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.TouchFirmware.TouchFirmwareListener
        public void onOtaUpdateComplete() {
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.TouchFirmware.TouchFirmwareListener
        public void onOtaVerifyStart() {
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.TouchFirmware.TouchFirmwareListener
        public void onFirmwareVerRead(byte b, byte b2, byte b3) {
            String str;
            if (GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("touchFwQueue").isEmpty()) {
                return;
            }
            ActionCallbackContext poll = GAIA_QCC_Plugin.this.commandQueue.str2Queue.get("touchFwQueue").poll();
            int i = b & 255;
            GAIA_QCC_Plugin gAIA_QCC_Plugin = GAIA_QCC_Plugin.this;
            if (b == 0) {
                str = "" + ((int) b2) + "." + ((int) b3);
            } else {
                str = "" + i + "." + ((int) b2) + "." + ((int) b3);
            }
            gAIA_QCC_Plugin.touchFwVer = str;
            poll.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, GAIA_QCC_Plugin.this.touchFwVer));
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.TouchFirmware.TouchFirmwareListener
        public void onOtaUpdateStart() {
            if (GAIA_QCC_Plugin.this.mCurrentConnectedDevice == null) {
                return;
            }
            GAIA_QCC_Plugin gAIA_QCC_Plugin = GAIA_QCC_Plugin.this;
            gAIA_QCC_Plugin.onTouchUpdateStartCallback(gAIA_QCC_Plugin.macAddressToUUID(gAIA_QCC_Plugin.mCurrentConnectedDevice.getAddress()));
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.TouchFirmware.TouchFirmwareListener
        public void onOtaUpdateProgress(int i) {
            if (GAIA_QCC_Plugin.this.mCurrentConnectedDevice == null) {
                return;
            }
            GAIA_QCC_Plugin gAIA_QCC_Plugin = GAIA_QCC_Plugin.this;
            gAIA_QCC_Plugin.onTouchUpdateProgressCallback(gAIA_QCC_Plugin.macAddressToUUID(gAIA_QCC_Plugin.mCurrentConnectedDevice.getAddress()), i);
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.TouchFirmware.TouchFirmwareListener
        public void onOtaUpdateAbort() {
            if (GAIA_QCC_Plugin.this.mCurrentConnectedDevice == null) {
                return;
            }
            GAIA_QCC_Plugin gAIA_QCC_Plugin = GAIA_QCC_Plugin.this;
            gAIA_QCC_Plugin.onTouchUpdateAbortCallback(gAIA_QCC_Plugin.macAddressToUUID(gAIA_QCC_Plugin.mCurrentConnectedDevice.getAddress()));
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.TouchFirmware.TouchFirmwareListener
        public void onOtaVerifyProgress(int i) {
            if (GAIA_QCC_Plugin.this.mCurrentConnectedDevice == null) {
                return;
            }
            GAIA_QCC_Plugin gAIA_QCC_Plugin = GAIA_QCC_Plugin.this;
            gAIA_QCC_Plugin.onTouchVerifyProgressCallback(gAIA_QCC_Plugin.macAddressToUUID(gAIA_QCC_Plugin.mCurrentConnectedDevice.getAddress()), i);
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.TouchFirmware.TouchFirmwareListener
        public void onOtaVerifyComplete() {
            if (GAIA_QCC_Plugin.this.mCurrentConnectedDevice == null) {
                return;
            }
            GAIA_QCC_Plugin gAIA_QCC_Plugin = GAIA_QCC_Plugin.this;
            gAIA_QCC_Plugin.onTouchVerifyCompleteCallback(gAIA_QCC_Plugin.macAddressToUUID(gAIA_QCC_Plugin.mCurrentConnectedDevice.getAddress()));
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.TouchFirmware.TouchFirmwareListener
        public void onOtaVerifyAbort() {
            if (GAIA_QCC_Plugin.this.mCurrentConnectedDevice == null) {
                return;
            }
            GAIA_QCC_Plugin gAIA_QCC_Plugin = GAIA_QCC_Plugin.this;
            gAIA_QCC_Plugin.onTouchVerifyAbortCallback(gAIA_QCC_Plugin.macAddressToUUID(gAIA_QCC_Plugin.mCurrentConnectedDevice.getAddress()));
        }
    };
    private OnGaiaUpgradeEventListener mGaiaUpgradeEventListener = new OnGaiaUpgradeEventListener() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin.4
        @Override // cordova.plugins.qcc.GaiaLibrary.OnGaiaUpgradeEventListener
        public void onFWUpgradeProgressAvailable(double d) {
            if (GAIA_QCC_Plugin.this.mCurrentConnectedDevice == null) {
                return;
            }
            GAIA_QCC_Plugin gAIA_QCC_Plugin = GAIA_QCC_Plugin.this;
            gAIA_QCC_Plugin.onUpgradeProgressAvailable(gAIA_QCC_Plugin.macAddressToUUID(gAIA_QCC_Plugin.mCurrentConnectedDevice.getAddress()), d);
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.OnGaiaUpgradeEventListener
        public void onFWUpgradeStateChange(int i) {
            if (GAIA_QCC_Plugin.this.mCurrentConnectedDevice == null) {
                return;
            }
            if (i == 4) {
                SolosManager solosManager = GAIA_QCC_Plugin.this.mSolosManager;
                GAIA_QCC_Plugin gAIA_QCC_Plugin = GAIA_QCC_Plugin.this;
                solosManager.connect(gAIA_QCC_Plugin.macAddressToUUID(gAIA_QCC_Plugin.mCurrentConnectedDevice.getAddress()));
            }
            GAIA_QCC_Plugin gAIA_QCC_Plugin2 = GAIA_QCC_Plugin.this;
            gAIA_QCC_Plugin2.onUpgradeStateChange(gAIA_QCC_Plugin2.macAddressToUUID(gAIA_QCC_Plugin2.mCurrentConnectedDevice.getAddress()), i);
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.OnGaiaUpgradeEventListener
        public void onFWUpgradeError(int i) {
            if (GAIA_QCC_Plugin.this.mCurrentConnectedDevice == null) {
                return;
            }
            GAIA_QCC_Plugin gAIA_QCC_Plugin = GAIA_QCC_Plugin.this;
            gAIA_QCC_Plugin.onUpgradeErrorOccur(gAIA_QCC_Plugin.macAddressToUUID(gAIA_QCC_Plugin.mCurrentConnectedDevice.getAddress()), i);
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void throwUnacknowledgedCallbackContext() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            this.commandQueue.getUnacknowledgedCallbackContext().stream().forEach(new Consumer() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((ActionCallbackContext) obj).callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, "Command Unacknowledged"));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void combineCalibrationData(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, byte[] bArr) {
        int i = ((bArr[0] & 255) << 16) | ((bArr[1] & 255) << 8) | (bArr[2] & 255);
        int r12 = 0;
        if (i == 6291648 || i == 6291650) {
            if (i == 6291648) {
                this.f297cd.magnetDataRx = true;
                if (gaiaSolosResponseStatus == CommandSpecification.GaiaSolosResponseStatus.SUCCESS) {
                    this.f297cd.magnetDataOk = true;
                    ArrayList arrayList = new ArrayList();
                    int i2 = 0;
                    int i3 = 3;
                    while (i2 < 6) {
                        arrayList.add(Long.valueOf(((bArr[i3] & 255) << 8) | (bArr[r12] & 255)));
                        i2++;
                        i3 = i3 + 1 + 1;
                    }
                    this.f297cd.magnetMaxX = ((Long) arrayList.get(0)).shortValue();
                    this.f297cd.magnetMaxY = ((Long) arrayList.get(1)).shortValue();
                    this.f297cd.magnetMaxZ = ((Long) arrayList.get(2)).shortValue();
                    this.f297cd.magnetMinX = ((Long) arrayList.get(3)).shortValue();
                    this.f297cd.magnetMinY = ((Long) arrayList.get(4)).shortValue();
                    this.f297cd.magnetMinZ = ((Long) arrayList.get(5)).shortValue();
                } else {
                    this.f297cd.magnetDataOk = false;
                }
            } else {
                this.f297cd.gyroAccelDataRx = true;
                if (gaiaSolosResponseStatus == CommandSpecification.GaiaSolosResponseStatus.SUCCESS) {
                    this.f297cd.gyroAccelDataOk = true;
                    ArrayList arrayList2 = new ArrayList();
                    int i4 = 0;
                    int i5 = 3;
                    while (i4 < 12) {
                        arrayList2.add(Long.valueOf((((byte) (bArr[i5] & 255)) << 8) | (bArr[r12] & 255)));
                        i4++;
                        i5 = i5 + 1 + 1;
                    }
                    this.f297cd.gyroShiftX = ((Long) arrayList2.get(0)).shortValue();
                    this.f297cd.gyroShiftY = ((Long) arrayList2.get(1)).shortValue();
                    this.f297cd.gyroShiftZ = ((Long) arrayList2.get(2)).shortValue();
                    this.f297cd.accelShiftX = ((Long) arrayList2.get(3)).shortValue();
                    this.f297cd.accelShiftY = ((Long) arrayList2.get(4)).shortValue();
                    this.f297cd.accelShiftZ = ((Long) arrayList2.get(5)).shortValue();
                    this.f297cd.magnetBiasX = ((Long) arrayList2.get(6)).shortValue();
                    this.f297cd.magnetBiasY = ((Long) arrayList2.get(7)).shortValue();
                    this.f297cd.magnetBiasZ = ((Long) arrayList2.get(8)).shortValue();
                    this.f297cd.magnetScaleX = ((Long) arrayList2.get(9)).shortValue();
                    this.f297cd.magnetScaleY = ((Long) arrayList2.get(10)).shortValue();
                    this.f297cd.magnetScaleZ = ((Long) arrayList2.get(11)).shortValue();
                } else {
                    this.f297cd.gyroAccelDataOk = false;
                }
            }
            if (this.f297cd.magnetDataRx && this.f297cd.gyroAccelDataRx) {
                this.f297cd.magnetDataRx = false;
                this.f297cd.gyroAccelDataRx = false;
                ActionCallbackContext poll = this.commandQueue.str2Queue.get("readDSPConfigParam").poll();
                if (this.f297cd.magnetDataOk && this.f297cd.gyroAccelDataOk) {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("min_Magn_X", this.f297cd.magnetMinX & 65535);
                        jSONObject.put("max_Magn_X", this.f297cd.magnetMaxX & 65535);
                        jSONObject.put("min_Magn_Y", this.f297cd.magnetMinY & 65535);
                        jSONObject.put("max_Magn_Y", this.f297cd.magnetMaxY & 65535);
                        jSONObject.put("min_Magn_Z", this.f297cd.magnetMinZ & 65535);
                        jSONObject.put("max_Magn_Z", this.f297cd.magnetMaxZ & 65535);
                        jSONObject.put("mag_Scale_X", this.f297cd.magnetScaleX & 65535);
                        jSONObject.put("mag_Scale_Y", this.f297cd.magnetScaleY & 65535);
                        jSONObject.put("mag_Scale_Z", this.f297cd.magnetScaleZ & 65535);
                        jSONObject.put("mag_Bias_X", this.f297cd.magnetBiasX & 65535);
                        jSONObject.put("mag_Bias_Y", this.f297cd.magnetBiasY & 65535);
                        jSONObject.put("mag_Bias_Z", this.f297cd.magnetBiasZ & 65535);
                        jSONObject.put("accel_Shift_X", this.f297cd.accelShiftX & 65535);
                        jSONObject.put("accel_Shift_Y", this.f297cd.accelShiftY & 65535);
                        jSONObject.put("accel_Shift_Z", this.f297cd.accelShiftZ & 65535);
                        jSONObject.put("gyro_Shift_X", this.f297cd.gyroShiftX & 65535);
                        jSONObject.put("gyro_Shift_Y", this.f297cd.gyroShiftY & 65535);
                        jSONObject.put("gyro_Shift_Z", this.f297cd.gyroShiftZ & 65535);
                        poll.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, jSONObject));
                        return;
                    } catch (JSONException unused) {
                        poll.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, "sensor calibration failed"));
                        return;
                    }
                }
                poll.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, "sensor calibration failed"));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void combineWhisperDownlinkData(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, byte[] bArr) {
        int i = 0;
        int i2 = 1;
        if ((((bArr[0] & 255) << 16) | ((bArr[1] & 255) << 8) | (bArr[2] & 255)) != 6291588) {
            return;
        }
        ActionCallbackContext poll = this.commandQueue.str2Queue.get("readDSPConfigParam").poll();
        if (poll.userData == null) {
            return;
        }
        if (gaiaSolosResponseStatus != CommandSpecification.GaiaSolosResponseStatus.SUCCESS) {
            poll.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, "whisper downlink error"));
            return;
        }
        try {
            int i3 = poll.userData.getInt(1);
            int i4 = 3;
            System.arraycopy(Arrays.copyOfRange(bArr, 3, 23), 0, this.dspConfig.eqDownlinkBandGain, 0, 20);
            while (i < 10) {
                int i5 = i4 + 1;
                int i6 = i5 + 1;
                this.dspConfig.setDownlinkVoiceEQGain(i2, (short) (bArr[i5] | (bArr[i4] << 8)));
                i2++;
                i++;
                i4 = i6;
            }
            int i7 = i4 + 2;
            this.dspConfig.eqDownlinkMasterGain = Utils.bytesToShort(Arrays.copyOfRange(bArr, i4, i7));
            int i8 = i7 + 2;
            this.dspConfig.eqDownlinkAGCEnable = Utils.bytesToShort(Arrays.copyOfRange(bArr, i7, i8));
            int i9 = i8 + 2;
            this.dspConfig.eqDownlinkAGCPowerThreshold = Utils.bytesToShort(Arrays.copyOfRange(bArr, i8, i9));
            int i10 = i9 + 2;
            this.dspConfig.eqDownlinkOutFlag = Utils.bytesToShort(Arrays.copyOfRange(bArr, i9, i10));
            this.dspConfig.eqDownlinkDLGain = Utils.bytesToShort(Arrays.copyOfRange(bArr, i10, i10 + 2));
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("band", i3);
            int downlinkVoiceEQGain = this.dspConfig.getDownlinkVoiceEQGain(i3);
            if (downlinkVoiceEQGain > 6) {
                downlinkVoiceEQGain = 6 - downlinkVoiceEQGain;
            }
            jSONObject.put("data", downlinkVoiceEQGain);
            poll.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, jSONObject));
        } catch (JSONException unused) {
            poll.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, "whisper downlink error"));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void combineWhisperEQData(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, byte[] bArr) {
        int i = 1;
        if ((((bArr[0] & 255) << 16) | ((bArr[1] & 255) << 8) | (bArr[2] & 255)) != 6291590) {
            return;
        }
        ActionCallbackContext poll = this.commandQueue.str2Queue.get("readDSPConfigParam").poll();
        if (poll.userData == null) {
            return;
        }
        if (gaiaSolosResponseStatus != CommandSpecification.GaiaSolosResponseStatus.SUCCESS) {
            poll.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, "whisper eq error"));
            return;
        }
        try {
            int i2 = poll.userData.getInt(1);
            int i3 = 5;
            this.dspConfig.whisperEqOutFlag = Utils.bytesToShort(Arrays.copyOfRange(bArr, 3, 5));
            System.arraycopy(Arrays.copyOfRange(bArr, 5, 25), 0, this.dspConfig.eqDownlinkBandGain, 0, 20);
            for (int i4 = 0; i4 < 10; i4++) {
                int i5 = i3 + 1;
                int i6 = bArr[i3] << 8;
                i3 = i5 + 1;
                this.dspConfig.setDownlinkVoiceEQGain(i, (short) (bArr[i5] | i6));
                i++;
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("band", i2);
            int downlinkVoiceEQGain = this.dspConfig.getDownlinkVoiceEQGain(i2);
            if (downlinkVoiceEQGain > 6) {
                downlinkVoiceEQGain = 6 - downlinkVoiceEQGain;
            }
            jSONObject.put("data", downlinkVoiceEQGain);
            poll.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, jSONObject));
        } catch (JSONException unused) {
            poll.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, "whisper downlink error"));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkVoiceDownlinkEnable(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, byte[] bArr) {
        boolean z = false;
        if ((((bArr[0] & 255) << 16) | ((bArr[1] & 255) << 8) | (bArr[2] & 255)) != 6291588) {
            return;
        }
        ActionCallbackContext poll = this.commandQueue.str2Queue.get("getVoiceEqualizerEnable").poll();
        this.dspConfig.eqDownlinkAGCEnable = Utils.bytesToShort(Arrays.copyOfRange(bArr, 25, 27));
        this.dspConfig.eqDownlinkOutFlag = Utils.bytesToShort(Arrays.copyOfRange(bArr, 29, 31));
        if (this.dspConfig.eqDownlinkAGCEnable == 2 && this.dspConfig.eqDownlinkOutFlag == Short.MIN_VALUE) {
            z = true;
        }
        poll.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkVoiceEQEnable(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, byte[] bArr) {
        if ((((bArr[0] & 255) << 16) | ((bArr[1] & 255) << 8) | (bArr[2] & 255)) != 6291590) {
            return;
        }
        ActionCallbackContext poll = this.commandQueue.str2Queue.get("getVoiceProEqualizerEnable").poll();
        this.dspConfig.eqDownlinkOutFlag = Utils.bytesToShort(Arrays.copyOfRange(bArr, 3, 5));
        poll.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, this.dspConfig.eqDownlinkOutFlag == Short.MIN_VALUE));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00b0, code lost:
    
        if (r8.equals("getAgcNoiseLevelUpAlpha") == false) goto L15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void checkWhisperFeature(cordova.plugins.qcc.GaiaLibrary.gaia.CommandSpecification.GaiaSolosResponseStatus r7, byte[] r8) {
        /*
            Method dump skipped, instructions count: 284
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cordova.plugins.qcc.GAIA_QCC_Plugin.checkWhisperFeature(cordova.plugins.qcc.GaiaLibrary.gaia.CommandSpecification$GaiaSolosResponseStatus, byte[]):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkWhisperOn(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, byte[] bArr) {
        if ((((bArr[0] & 255) << 16) | ((bArr[1] & 255) << 8) | (bArr[2] & 255)) != 6291586) {
            return;
        }
        this.dspConfig.inGain = Utils.bytesToShort(Arrays.copyOfRange(bArr, 3, 5));
        this.dspConfig.outGain = Utils.bytesToShort(Arrays.copyOfRange(bArr, 5, 7));
        this.dspConfig.outFlag = Utils.bytesToShort(Arrays.copyOfRange(bArr, 7, 9));
        this.dspConfig.selectedChannel = Utils.bytesToShort(Arrays.copyOfRange(bArr, 9, 11));
        this.dspConfig.mainGain = Utils.bytesToInt(Arrays.copyOfRange(bArr, 11, 15));
        this.dspConfig.refGain = Utils.bytesToInt(Arrays.copyOfRange(bArr, 15, 19));
        this.dspConfig.vad = Utils.bytesToInt(Arrays.copyOfRange(bArr, 19, 23));
        this.dspConfig.scvad = Utils.bytesToInt(Arrays.copyOfRange(bArr, 23, 27));
        this.dspConfig.hangOver = Utils.bytesToShort(Arrays.copyOfRange(bArr, 27, 29));
        this.dspConfig.scHangOver = Utils.bytesToShort(Arrays.copyOfRange(bArr, 29, 31));
        this.dspConfig.aecRefGain = Utils.bytesToShort(Arrays.copyOfRange(bArr, 31, 33));
        this.commandQueue.str2Queue.get("getWhisperOn").poll().callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, this.dspConfig.outFlag == 0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String macAddressToUUID(String str) {
        return UUID.nameUUIDFromBytes(str.getBytes()).toString();
    }

    private void initSolosManager() {
        SolosManager solosManager = SolosManager.getInstance(this.cordova.getActivity().getApplicationContext());
        this.mSolosManager = solosManager;
        solosManager.setOnBluetoothConnectionListener(this.btDevicesListener);
        this.mSolosManager.setOnSolosGaiaCommandResultListener(this.mCommandResultListener);
        this.mSolosManager.setOnGaiaUpgradeEventListener(this.mGaiaUpgradeEventListener);
        this.mSolosManager.setTouchFirmwareListener(this.mTouchUpdateEventListener);
    }

    private void initCommandMap() {
        HashMap<String, BiFunction<JSONArray, CallbackContext, PluginResult>> hashMap = new HashMap<>();
        this.commandMap = hashMap;
        hashMap.put("init", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda28
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult init;
                init = GAIA_QCC_Plugin.this.init((JSONArray) obj, (CallbackContext) obj2);
                return init;
            }
        });
        this.commandMap.put("startScan", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda37
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult startScan;
                startScan = GAIA_QCC_Plugin.this.startScan((JSONArray) obj, (CallbackContext) obj2);
                return startScan;
            }
        });
        this.commandMap.put("stopScan", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda49
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult stopScan;
                stopScan = GAIA_QCC_Plugin.this.stopScan((JSONArray) obj, (CallbackContext) obj2);
                return stopScan;
            }
        });
        this.commandMap.put("connect", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda62
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult connect;
                connect = GAIA_QCC_Plugin.this.connect((JSONArray) obj, (CallbackContext) obj2);
                return connect;
            }
        });
        this.commandMap.put("disconnect", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda74
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult disconnect;
                disconnect = GAIA_QCC_Plugin.this.disconnect((JSONArray) obj, (CallbackContext) obj2);
                return disconnect;
            }
        });
        this.commandMap.put("startFirmwareUpgrade", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda86
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult startFirmwareUpgrade;
                startFirmwareUpgrade = GAIA_QCC_Plugin.this.startFirmwareUpgrade((JSONArray) obj, (CallbackContext) obj2);
                return startFirmwareUpgrade;
            }
        });
        this.commandMap.put("abortFirmwareUpgrade", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda98
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult abortFirmwareUpgrade;
                abortFirmwareUpgrade = GAIA_QCC_Plugin.this.abortFirmwareUpgrade((JSONArray) obj, (CallbackContext) obj2);
                return abortFirmwareUpgrade;
            }
        });
        this.commandMap.put("switchFirmware", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda110
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult switchFirmware;
                switchFirmware = GAIA_QCC_Plugin.this.switchFirmware((JSONArray) obj, (CallbackContext) obj2);
                return switchFirmware;
            }
        });
        this.commandMap.put("getFirmwareVer", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda6
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult firmwareVer;
                firmwareVer = GAIA_QCC_Plugin.this.getFirmwareVer((JSONArray) obj, (CallbackContext) obj2);
                return firmwareVer;
            }
        });
        this.commandMap.put("factoryReset", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda18
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult factoryReset;
                factoryReset = GAIA_QCC_Plugin.this.factoryReset((JSONArray) obj, (CallbackContext) obj2);
                return factoryReset;
            }
        });
        this.commandMap.put("getSerialNum", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda20
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult serialNum;
                serialNum = GAIA_QCC_Plugin.this.getSerialNum((JSONArray) obj, (CallbackContext) obj2);
                return serialNum;
            }
        });
        this.commandMap.put("getTouchFirmwareVer", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda27
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult touchFirmwareVer;
                touchFirmwareVer = GAIA_QCC_Plugin.this.getTouchFirmwareVer((JSONArray) obj, (CallbackContext) obj2);
                return touchFirmwareVer;
            }
        });
        this.commandMap.put("startTouchFirmwareUpdate", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda29
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult startTouchFirmwareUpdate;
                startTouchFirmwareUpdate = GAIA_QCC_Plugin.this.startTouchFirmwareUpdate((JSONArray) obj, (CallbackContext) obj2);
                return startTouchFirmwareUpdate;
            }
        });
        this.commandMap.put("enableUart", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda30
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult enableUart;
                enableUart = GAIA_QCC_Plugin.this.enableUart((JSONArray) obj, (CallbackContext) obj2);
                return enableUart;
            }
        });
        this.commandMap.put("initSensors", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda31
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult initSensors;
                initSensors = GAIA_QCC_Plugin.this.initSensors((JSONArray) obj, (CallbackContext) obj2);
                return initSensors;
            }
        });
        this.commandMap.put("enableGestureEvent", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda32
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult enableGestureEvent;
                enableGestureEvent = GAIA_QCC_Plugin.this.enableGestureEvent((JSONArray) obj, (CallbackContext) obj2);
                return enableGestureEvent;
            }
        });
        this.commandMap.put("enableGAMSensors", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda33
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult enableGAMSensors;
                enableGAMSensors = GAIA_QCC_Plugin.this.enableGAMSensors((JSONArray) obj, (CallbackContext) obj2);
                return enableGAMSensors;
            }
        });
        this.commandMap.put("enableProximitySensor", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda34
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult enableProximitySensor;
                enableProximitySensor = GAIA_QCC_Plugin.this.enableProximitySensor((JSONArray) obj, (CallbackContext) obj2);
                return enableProximitySensor;
            }
        });
        this.commandMap.put("resetStepCountSensor", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda35
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult resetStepCountSensor;
                resetStepCountSensor = GAIA_QCC_Plugin.this.resetStepCountSensor((JSONArray) obj, (CallbackContext) obj2);
                return resetStepCountSensor;
            }
        });
        this.commandMap.put("enableStepCountSensor", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda36
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult enableStepCountSensor;
                enableStepCountSensor = GAIA_QCC_Plugin.this.enableStepCountSensor((JSONArray) obj, (CallbackContext) obj2);
                return enableStepCountSensor;
            }
        });
        this.commandMap.put("enableStepCountEvent", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda38
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult enableStepCountEvent;
                enableStepCountEvent = GAIA_QCC_Plugin.this.enableStepCountEvent((JSONArray) obj, (CallbackContext) obj2);
                return enableStepCountEvent;
            }
        });
        this.commandMap.put("enableGAMEvents", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda40
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult enableGAMEvents;
                enableGAMEvents = GAIA_QCC_Plugin.this.enableGAMEvents((JSONArray) obj, (CallbackContext) obj2);
                return enableGAMEvents;
            }
        });
        this.commandMap.put("setGAMEventInterval", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda41
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult gAMEventInterval;
                gAMEventInterval = GAIA_QCC_Plugin.this.setGAMEventInterval((JSONArray) obj, (CallbackContext) obj2);
                return gAMEventInterval;
            }
        });
        this.commandMap.put("getCalibrationData", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda42
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult calibrationData;
                calibrationData = GAIA_QCC_Plugin.this.getCalibrationData((JSONArray) obj, (CallbackContext) obj2);
                return calibrationData;
            }
        });
        this.commandMap.put("setMagnetCalibrationData", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda43
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult magnetCalibrationData;
                magnetCalibrationData = GAIA_QCC_Plugin.this.setMagnetCalibrationData((JSONArray) obj, (CallbackContext) obj2);
                return magnetCalibrationData;
            }
        });
        this.commandMap.put("setGyroAccelMagnetCalibrationData", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda44
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult gyroAccelMagnetCalibrationData;
                gyroAccelMagnetCalibrationData = GAIA_QCC_Plugin.this.setGyroAccelMagnetCalibrationData((JSONArray) obj, (CallbackContext) obj2);
                return gyroAccelMagnetCalibrationData;
            }
        });
        this.commandMap.put("setLanguage", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda45
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult language;
                language = GAIA_QCC_Plugin.this.setLanguage((JSONArray) obj, (CallbackContext) obj2);
                return language;
            }
        });
        this.commandMap.put("setTapSensitivity", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda46
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult tapSensitivity;
                tapSensitivity = GAIA_QCC_Plugin.this.setTapSensitivity((JSONArray) obj, (CallbackContext) obj2);
                return tapSensitivity;
            }
        });
        this.commandMap.put("getTapSensitivity", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda47
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult tapSensitivity;
                tapSensitivity = GAIA_QCC_Plugin.this.getTapSensitivity((JSONArray) obj, (CallbackContext) obj2);
                return tapSensitivity;
            }
        });
        this.commandMap.put("readBattery", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda48
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult readBattery;
                readBattery = GAIA_QCC_Plugin.this.readBattery((JSONArray) obj, (CallbackContext) obj2);
                return readBattery;
            }
        });
        this.commandMap.put("setPowerOn", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda51
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult powerOn;
                powerOn = GAIA_QCC_Plugin.this.setPowerOn((JSONArray) obj, (CallbackContext) obj2);
                return powerOn;
            }
        });
        this.commandMap.put("readI2C", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda52
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult readI2C;
                readI2C = GAIA_QCC_Plugin.this.readI2C((JSONArray) obj, (CallbackContext) obj2);
                return readI2C;
            }
        });
        this.commandMap.put("writeI2C", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda53
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult writeI2C;
                writeI2C = GAIA_QCC_Plugin.this.writeI2C((JSONArray) obj, (CallbackContext) obj2);
                return writeI2C;
            }
        });
        this.commandMap.put("readProximity", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda54
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult readProximity;
                readProximity = GAIA_QCC_Plugin.this.readProximity((JSONArray) obj, (CallbackContext) obj2);
                return readProximity;
            }
        });
        this.commandMap.put("setASRMode", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda55
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult aSRMode;
                aSRMode = GAIA_QCC_Plugin.this.setASRMode((JSONArray) obj, (CallbackContext) obj2);
                return aSRMode;
            }
        });
        this.commandMap.put("setOrientationDetectionEnable", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda56
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult orientationDetectionEnable;
                orientationDetectionEnable = GAIA_QCC_Plugin.this.setOrientationDetectionEnable((JSONArray) obj, (CallbackContext) obj2);
                return orientationDetectionEnable;
            }
        });
        this.commandMap.put("getOrientationDetectionEnable", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda57
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult orientationDetectionEnable;
                orientationDetectionEnable = GAIA_QCC_Plugin.this.getOrientationDetectionEnable((JSONArray) obj, (CallbackContext) obj2);
                return orientationDetectionEnable;
            }
        });
        this.commandMap.put("setInputConfig", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda58
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult inputConfig;
                inputConfig = GAIA_QCC_Plugin.this.setInputConfig((JSONArray) obj, (CallbackContext) obj2);
                return inputConfig;
            }
        });
        this.commandMap.put("getInputConfig", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda59
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult inputConfig;
                inputConfig = GAIA_QCC_Plugin.this.getInputConfig((JSONArray) obj, (CallbackContext) obj2);
                return inputConfig;
            }
        });
        this.commandMap.put("readAutoPowerOffTimeout", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda60
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult readAutoPowerOffTimeout;
                readAutoPowerOffTimeout = GAIA_QCC_Plugin.this.readAutoPowerOffTimeout((JSONArray) obj, (CallbackContext) obj2);
                return readAutoPowerOffTimeout;
            }
        });
        this.commandMap.put("writeAutoPowerOffTimeout", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda63
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult writeAutoPowerOffTimeout;
                writeAutoPowerOffTimeout = GAIA_QCC_Plugin.this.writeAutoPowerOffTimeout((JSONArray) obj, (CallbackContext) obj2);
                return writeAutoPowerOffTimeout;
            }
        });
        this.commandMap.put("readAutoPowerOffMode", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda64
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult readAutoPowerOffMode;
                readAutoPowerOffMode = GAIA_QCC_Plugin.this.readAutoPowerOffMode((JSONArray) obj, (CallbackContext) obj2);
                return readAutoPowerOffMode;
            }
        });
        this.commandMap.put("writeAutoPowerOffMode", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda65
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult writeAutoPowerOffMode;
                writeAutoPowerOffMode = GAIA_QCC_Plugin.this.writeAutoPowerOffMode((JSONArray) obj, (CallbackContext) obj2);
                return writeAutoPowerOffMode;
            }
        });
        this.commandMap.put("readAutoPowerOnUponUnplugCharger", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda66
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult readAutoPowerOnUponUnplugCharger;
                readAutoPowerOnUponUnplugCharger = GAIA_QCC_Plugin.this.readAutoPowerOnUponUnplugCharger((JSONArray) obj, (CallbackContext) obj2);
                return readAutoPowerOnUponUnplugCharger;
            }
        });
        this.commandMap.put("writeAutoPowerOnUponUnplugCharger", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda67
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult writeAutoPowerOnUponUnplugCharger;
                writeAutoPowerOnUponUnplugCharger = GAIA_QCC_Plugin.this.writeAutoPowerOnUponUnplugCharger((JSONArray) obj, (CallbackContext) obj2);
                return writeAutoPowerOnUponUnplugCharger;
            }
        });
        this.commandMap.put("getMultipoint", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda68
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult multipoint;
                multipoint = GAIA_QCC_Plugin.this.getMultipoint((JSONArray) obj, (CallbackContext) obj2);
                return multipoint;
            }
        });
        this.commandMap.put("setMultipoint", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda69
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult multipoint;
                multipoint = GAIA_QCC_Plugin.this.setMultipoint((JSONArray) obj, (CallbackContext) obj2);
                return multipoint;
            }
        });
        this.commandMap.put("readOffHeadVoicePromptVolume", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda70
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult readOffHeadVoicePromptVolume;
                readOffHeadVoicePromptVolume = GAIA_QCC_Plugin.this.readOffHeadVoicePromptVolume((JSONArray) obj, (CallbackContext) obj2);
                return readOffHeadVoicePromptVolume;
            }
        });
        this.commandMap.put("writeOffHeadVoicePromptVolume", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda71
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult writeOffHeadVoicePromptVolume;
                writeOffHeadVoicePromptVolume = GAIA_QCC_Plugin.this.writeOffHeadVoicePromptVolume((JSONArray) obj, (CallbackContext) obj2);
                return writeOffHeadVoicePromptVolume;
            }
        });
        this.commandMap.put("readOnHeadVoicePromptVolume", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda73
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult readOnHeadVoicePromptVolume;
                readOnHeadVoicePromptVolume = GAIA_QCC_Plugin.this.readOnHeadVoicePromptVolume((JSONArray) obj, (CallbackContext) obj2);
                return readOnHeadVoicePromptVolume;
            }
        });
        this.commandMap.put("writeOnHeadVoicePromptVolume", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda75
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult writeOnHeadVoicePromptVolume;
                writeOnHeadVoicePromptVolume = GAIA_QCC_Plugin.this.writeOnHeadVoicePromptVolume((JSONArray) obj, (CallbackContext) obj2);
                return writeOnHeadVoicePromptVolume;
            }
        });
        this.commandMap.put("readLostLinkAlertDuration", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda76
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult readLostLinkAlertDuration;
                readLostLinkAlertDuration = GAIA_QCC_Plugin.this.readLostLinkAlertDuration((JSONArray) obj, (CallbackContext) obj2);
                return readLostLinkAlertDuration;
            }
        });
        this.commandMap.put("writeLostLinkAlertDuration", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda77
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult writeLostLinkAlertDuration;
                writeLostLinkAlertDuration = GAIA_QCC_Plugin.this.writeLostLinkAlertDuration((JSONArray) obj, (CallbackContext) obj2);
                return writeLostLinkAlertDuration;
            }
        });
        this.commandMap.put("getLostLinkVolume", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda78
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult readLostLinkAlertVolume;
                readLostLinkAlertVolume = GAIA_QCC_Plugin.this.readLostLinkAlertVolume((JSONArray) obj, (CallbackContext) obj2);
                return readLostLinkAlertVolume;
            }
        });
        this.commandMap.put("setLostLinkVolume", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda79
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult writeLostLinkAlertVolume;
                writeLostLinkAlertVolume = GAIA_QCC_Plugin.this.writeLostLinkAlertVolume((JSONArray) obj, (CallbackContext) obj2);
                return writeLostLinkAlertVolume;
            }
        });
        this.commandMap.put("getMultipointLostLinkAlertFor2Phones", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda80
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult readLostLinkMultipleDevicesAlertMode;
                readLostLinkMultipleDevicesAlertMode = GAIA_QCC_Plugin.this.readLostLinkMultipleDevicesAlertMode((JSONArray) obj, (CallbackContext) obj2);
                return readLostLinkMultipleDevicesAlertMode;
            }
        });
        this.commandMap.put("setMultipointLostLinkAlertFor2Phones", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda81
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult writeLostLinkMultipleDevicesAlertMode;
                writeLostLinkMultipleDevicesAlertMode = GAIA_QCC_Plugin.this.writeLostLinkMultipleDevicesAlertMode((JSONArray) obj, (CallbackContext) obj2);
                return writeLostLinkMultipleDevicesAlertMode;
            }
        });
        this.commandMap.put("getAmbientNoiseDetectionEnable", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda82
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult ambientNoiseDetectionEnable;
                ambientNoiseDetectionEnable = GAIA_QCC_Plugin.this.getAmbientNoiseDetectionEnable((JSONArray) obj, (CallbackContext) obj2);
                return ambientNoiseDetectionEnable;
            }
        });
        this.commandMap.put("setAmbientNoiseDetectionEnable", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda84
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult ambientNoiseDetectionEnable;
                ambientNoiseDetectionEnable = GAIA_QCC_Plugin.this.setAmbientNoiseDetectionEnable((JSONArray) obj, (CallbackContext) obj2);
                return ambientNoiseDetectionEnable;
            }
        });
        this.commandMap.put("getHarDetectionEnable", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda85
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult harDetectionEnable;
                harDetectionEnable = GAIA_QCC_Plugin.this.getHarDetectionEnable((JSONArray) obj, (CallbackContext) obj2);
                return harDetectionEnable;
            }
        });
        this.commandMap.put("setHarDetectionEnable", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda87
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult harDetectionEnable;
                harDetectionEnable = GAIA_QCC_Plugin.this.setHarDetectionEnable((JSONArray) obj, (CallbackContext) obj2);
                return harDetectionEnable;
            }
        });
        this.commandMap.put("getSmartPowerOffEnable", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda88
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult smartPowerOffEnable;
                smartPowerOffEnable = GAIA_QCC_Plugin.this.getSmartPowerOffEnable((JSONArray) obj, (CallbackContext) obj2);
                return smartPowerOffEnable;
            }
        });
        this.commandMap.put("setSmartPowerOffEnable", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda89
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult smartPowerOffEnable;
                smartPowerOffEnable = GAIA_QCC_Plugin.this.setSmartPowerOffEnable((JSONArray) obj, (CallbackContext) obj2);
                return smartPowerOffEnable;
            }
        });
        this.commandMap.put("getUserEqualizerOn", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda90
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult userEqualizerOn;
                userEqualizerOn = GAIA_QCC_Plugin.this.getUserEqualizerOn((JSONArray) obj, (CallbackContext) obj2);
                return userEqualizerOn;
            }
        });
        this.commandMap.put("setUserEqualizerOn", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda91
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult userEqualizerOn;
                userEqualizerOn = GAIA_QCC_Plugin.this.setUserEqualizerOn((JSONArray) obj, (CallbackContext) obj2);
                return userEqualizerOn;
            }
        });
        this.commandMap.put("getMusicEqualizerMasterGain", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda92
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult musicEqualizerMasterGain;
                musicEqualizerMasterGain = GAIA_QCC_Plugin.this.getMusicEqualizerMasterGain((JSONArray) obj, (CallbackContext) obj2);
                return musicEqualizerMasterGain;
            }
        });
        this.commandMap.put("setMusicEqualizerMasterGain", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda93
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult musicEqualizerMasterGain;
                musicEqualizerMasterGain = GAIA_QCC_Plugin.this.setMusicEqualizerMasterGain((JSONArray) obj, (CallbackContext) obj2);
                return musicEqualizerMasterGain;
            }
        });
        this.commandMap.put("getMusicEqualizerGain", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda95
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult musicEqualizerGain;
                musicEqualizerGain = GAIA_QCC_Plugin.this.getMusicEqualizerGain((JSONArray) obj, (CallbackContext) obj2);
                return musicEqualizerGain;
            }
        });
        this.commandMap.put("setMusicEqualizerGain", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda96
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult musicEqualizerGain;
                musicEqualizerGain = GAIA_QCC_Plugin.this.setMusicEqualizerGain((JSONArray) obj, (CallbackContext) obj2);
                return musicEqualizerGain;
            }
        });
        this.commandMap.put("getVoiceEqualizerGain", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda97
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult voiceEqualizerGain;
                voiceEqualizerGain = GAIA_QCC_Plugin.this.getVoiceEqualizerGain((JSONArray) obj, (CallbackContext) obj2);
                return voiceEqualizerGain;
            }
        });
        this.commandMap.put("setVoiceEqualizerGain", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda99
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult voiceEqualizerGain;
                voiceEqualizerGain = GAIA_QCC_Plugin.this.setVoiceEqualizerGain((JSONArray) obj, (CallbackContext) obj2);
                return voiceEqualizerGain;
            }
        });
        this.commandMap.put("getMusicEqualizerFrequency", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda100
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult musicEqualizerFrequency;
                musicEqualizerFrequency = GAIA_QCC_Plugin.this.getMusicEqualizerFrequency((JSONArray) obj, (CallbackContext) obj2);
                return musicEqualizerFrequency;
            }
        });
        this.commandMap.put("setMusicEqualizerFrequency", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda101
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult musicEqualizerFrequency;
                musicEqualizerFrequency = GAIA_QCC_Plugin.this.setMusicEqualizerFrequency((JSONArray) obj, (CallbackContext) obj2);
                return musicEqualizerFrequency;
            }
        });
        this.commandMap.put("getMusicEqualizerQFactor", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda102
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult musicEqualizerQFactor;
                musicEqualizerQFactor = GAIA_QCC_Plugin.this.getMusicEqualizerQFactor((JSONArray) obj, (CallbackContext) obj2);
                return musicEqualizerQFactor;
            }
        });
        this.commandMap.put("setMusicEqualizerQFactor", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda103
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult musicEqualizerQFactor;
                musicEqualizerQFactor = GAIA_QCC_Plugin.this.setMusicEqualizerQFactor((JSONArray) obj, (CallbackContext) obj2);
                return musicEqualizerQFactor;
            }
        });
        this.commandMap.put("getMusicEqualizerFilterType", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda104
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult musicEqualizerFilterType;
                musicEqualizerFilterType = GAIA_QCC_Plugin.this.getMusicEqualizerFilterType((JSONArray) obj, (CallbackContext) obj2);
                return musicEqualizerFilterType;
            }
        });
        this.commandMap.put("setMusicEqualizerFilterType", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda106
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult musicEqualizerFilterType;
                musicEqualizerFilterType = GAIA_QCC_Plugin.this.setMusicEqualizerFilterType((JSONArray) obj, (CallbackContext) obj2);
                return musicEqualizerFilterType;
            }
        });
        this.commandMap.put("setVoiceEqualizerEnable", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda107
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult voiceEqualizerEnable;
                voiceEqualizerEnable = GAIA_QCC_Plugin.this.setVoiceEqualizerEnable((JSONArray) obj, (CallbackContext) obj2);
                return voiceEqualizerEnable;
            }
        });
        this.commandMap.put("getVoiceEqualizerEnable", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda108
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult voiceEqualizerEnable;
                voiceEqualizerEnable = GAIA_QCC_Plugin.this.getVoiceEqualizerEnable((JSONArray) obj, (CallbackContext) obj2);
                return voiceEqualizerEnable;
            }
        });
        this.commandMap.put("getVoiceProEqualizerGain", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda109
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult voiceProEqualizerGain;
                voiceProEqualizerGain = GAIA_QCC_Plugin.this.getVoiceProEqualizerGain((JSONArray) obj, (CallbackContext) obj2);
                return voiceProEqualizerGain;
            }
        });
        this.commandMap.put("setVoiceProEqualizerGain", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda111
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult voiceProEqualizerGain;
                voiceProEqualizerGain = GAIA_QCC_Plugin.this.setVoiceProEqualizerGain((JSONArray) obj, (CallbackContext) obj2);
                return voiceProEqualizerGain;
            }
        });
        this.commandMap.put("getVoiceProEqualizerEnable", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda112
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult voiceProEqualizerEnable;
                voiceProEqualizerEnable = GAIA_QCC_Plugin.this.getVoiceProEqualizerEnable((JSONArray) obj, (CallbackContext) obj2);
                return voiceProEqualizerEnable;
            }
        });
        this.commandMap.put("setVoiceProEqualizerEnable", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda113
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult voiceProEqualizerEnable;
                voiceProEqualizerEnable = GAIA_QCC_Plugin.this.setVoiceProEqualizerEnable((JSONArray) obj, (CallbackContext) obj2);
                return voiceProEqualizerEnable;
            }
        });
        this.commandMap.put("resetDevice", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda114
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult resetDevice;
                resetDevice = GAIA_QCC_Plugin.this.resetDevice((JSONArray) obj, (CallbackContext) obj2);
                return resetDevice;
            }
        });
        this.commandMap.put("setRTC", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda115
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult rtc;
                rtc = GAIA_QCC_Plugin.this.setRTC((JSONArray) obj, (CallbackContext) obj2);
                return rtc;
            }
        });
        this.commandMap.put("set247Monitor", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda1
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult pluginResult;
                pluginResult = GAIA_QCC_Plugin.this.set247Monitor((JSONArray) obj, (CallbackContext) obj2);
                return pluginResult;
            }
        });
        this.commandMap.put("get247Monitor", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda2
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult pluginResult;
                pluginResult = GAIA_QCC_Plugin.this.get247Monitor((JSONArray) obj, (CallbackContext) obj2);
                return pluginResult;
            }
        });
        this.commandMap.put("set247Reporting", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda3
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult pluginResult;
                pluginResult = GAIA_QCC_Plugin.this.set247Reporting((JSONArray) obj, (CallbackContext) obj2);
                return pluginResult;
            }
        });
        this.commandMap.put("get247Reporting", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda4
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult pluginResult;
                pluginResult = GAIA_QCC_Plugin.this.get247Reporting((JSONArray) obj, (CallbackContext) obj2);
                return pluginResult;
            }
        });
        this.commandMap.put("get247StepCount", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda5
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult pluginResult;
                pluginResult = GAIA_QCC_Plugin.this.get247StepCount((JSONArray) obj, (CallbackContext) obj2);
                return pluginResult;
            }
        });
        this.commandMap.put("delete247StepCount", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda7
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult delete247StepCount;
                delete247StepCount = GAIA_QCC_Plugin.this.delete247StepCount((JSONArray) obj, (CallbackContext) obj2);
                return delete247StepCount;
            }
        });
        this.commandMap.put("set247PostureConfig", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda8
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult pluginResult;
                pluginResult = GAIA_QCC_Plugin.this.set247PostureConfig((JSONArray) obj, (CallbackContext) obj2);
                return pluginResult;
            }
        });
        this.commandMap.put("get247PostureConfig", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda9
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult pluginResult;
                pluginResult = GAIA_QCC_Plugin.this.get247PostureConfig((JSONArray) obj, (CallbackContext) obj2);
                return pluginResult;
            }
        });
        this.commandMap.put("get247Posture", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda10
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult pluginResult;
                pluginResult = GAIA_QCC_Plugin.this.get247Posture((JSONArray) obj, (CallbackContext) obj2);
                return pluginResult;
            }
        });
        this.commandMap.put("get247Posture2", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda12
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult pluginResult;
                pluginResult = GAIA_QCC_Plugin.this.get247Posture2((JSONArray) obj, (CallbackContext) obj2);
                return pluginResult;
            }
        });
        this.commandMap.put("delete247Posture", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda13
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult delete247Posture;
                delete247Posture = GAIA_QCC_Plugin.this.delete247Posture((JSONArray) obj, (CallbackContext) obj2);
                return delete247Posture;
            }
        });
        this.commandMap.put("get247PostureAlertVolume", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda14
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult readPostureReminderVolume;
                readPostureReminderVolume = GAIA_QCC_Plugin.this.readPostureReminderVolume((JSONArray) obj, (CallbackContext) obj2);
                return readPostureReminderVolume;
            }
        });
        this.commandMap.put("set247PostureAlertVolume", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda15
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult writePostureReminderVolume;
                writePostureReminderVolume = GAIA_QCC_Plugin.this.writePostureReminderVolume((JSONArray) obj, (CallbackContext) obj2);
                return writePostureReminderVolume;
            }
        });
        this.commandMap.put("setAvEvents", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda16
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult avEvents;
                avEvents = GAIA_QCC_Plugin.this.setAvEvents((JSONArray) obj, (CallbackContext) obj2);
                return avEvents;
            }
        });
        this.commandMap.put("getAvEvents", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda17
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult avEvents;
                avEvents = GAIA_QCC_Plugin.this.getAvEvents((JSONArray) obj, (CallbackContext) obj2);
                return avEvents;
            }
        });
        this.commandMap.put("getAvInfo", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda39
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult avInfo;
                avInfo = GAIA_QCC_Plugin.this.getAvInfo((JSONArray) obj, (CallbackContext) obj2);
                return avInfo;
            }
        });
        this.commandMap.put("setAvControl", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda50
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult avControl;
                avControl = GAIA_QCC_Plugin.this.setAvControl((JSONArray) obj, (CallbackContext) obj2);
                return avControl;
            }
        });
        this.commandMap.put("rebootDevice", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda61
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult rebootDevice;
                rebootDevice = GAIA_QCC_Plugin.this.rebootDevice((JSONArray) obj, (CallbackContext) obj2);
                return rebootDevice;
            }
        });
        this.commandMap.put("setWindFilterOn", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda72
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult windFilterOn;
                windFilterOn = GAIA_QCC_Plugin.this.setWindFilterOn((JSONArray) obj, (CallbackContext) obj2);
                return windFilterOn;
            }
        });
        this.commandMap.put("getWindFilterOn", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda83
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult windFilterOn;
                windFilterOn = GAIA_QCC_Plugin.this.getWindFilterOn((JSONArray) obj, (CallbackContext) obj2);
                return windFilterOn;
            }
        });
        this.commandMap.put("setAGCNoiseLevelUpAlpha", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda94
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult agcNoiseLevelUpAlpha;
                agcNoiseLevelUpAlpha = GAIA_QCC_Plugin.this.setAgcNoiseLevelUpAlpha((JSONArray) obj, (CallbackContext) obj2);
                return agcNoiseLevelUpAlpha;
            }
        });
        this.commandMap.put("getAGCNoiseLevelUpAlpha", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda105
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult agcNoiseLevelUpAlpha;
                agcNoiseLevelUpAlpha = GAIA_QCC_Plugin.this.getAgcNoiseLevelUpAlpha((JSONArray) obj, (CallbackContext) obj2);
                return agcNoiseLevelUpAlpha;
            }
        });
        this.commandMap.put("setAGCNoiseLevelDnAlpha", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda116
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult agcNoiseLevelDnAlpha;
                agcNoiseLevelDnAlpha = GAIA_QCC_Plugin.this.setAgcNoiseLevelDnAlpha((JSONArray) obj, (CallbackContext) obj2);
                return agcNoiseLevelDnAlpha;
            }
        });
        this.commandMap.put("getAGCNoiseLevelDnAlpha", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda11
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult agcNoiseLevelDnAlpha;
                agcNoiseLevelDnAlpha = GAIA_QCC_Plugin.this.getAgcNoiseLevelDnAlpha((JSONArray) obj, (CallbackContext) obj2);
                return agcNoiseLevelDnAlpha;
            }
        });
        this.commandMap.put("setWhisperOn", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda19
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult whisperOn;
                whisperOn = GAIA_QCC_Plugin.this.setWhisperOn((JSONArray) obj, (CallbackContext) obj2);
                return whisperOn;
            }
        });
        this.commandMap.put("getWhisperOn", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda21
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult whisperOn;
                whisperOn = GAIA_QCC_Plugin.this.getWhisperOn((JSONArray) obj, (CallbackContext) obj2);
                return whisperOn;
            }
        });
        this.commandMap.put("getWhisperDataRtOutGain", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda22
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult whisperDataRtOutGain;
                whisperDataRtOutGain = GAIA_QCC_Plugin.this.getWhisperDataRtOutGain((JSONArray) obj, (CallbackContext) obj2);
                return whisperDataRtOutGain;
            }
        });
        this.commandMap.put("getWhisperDataRtOutFlag", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda23
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult whisperDataRtOutFlag;
                whisperDataRtOutFlag = GAIA_QCC_Plugin.this.getWhisperDataRtOutFlag((JSONArray) obj, (CallbackContext) obj2);
                return whisperDataRtOutFlag;
            }
        });
        this.commandMap.put("setTranslationMode", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda24
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult translationMode;
                translationMode = GAIA_QCC_Plugin.this.setTranslationMode((JSONArray) obj, (CallbackContext) obj2);
                return translationMode;
            }
        });
        this.commandMap.put("setTranslationModeRt", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda25
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult translationModeRt;
                translationModeRt = GAIA_QCC_Plugin.this.setTranslationModeRt((JSONArray) obj, (CallbackContext) obj2);
                return translationModeRt;
            }
        });
        this.commandMap.put("setVolatileInputConfigTimeout", new BiFunction() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin$$ExternalSyntheticLambda26
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                PluginResult volatileInputConfigTimeout;
                volatileInputConfigTimeout = GAIA_QCC_Plugin.this.setVolatileInputConfigTimeout((JSONArray) obj, (CallbackContext) obj2);
                return volatileInputConfigTimeout;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.cordova.CordovaPlugin
    public void pluginInitialize() {
        super.pluginInitialize();
        initSolosManager();
        initCommandMap();
    }

    @Override // org.apache.cordova.CordovaPlugin
    public void onStart() {
        super.onStart();
    }

    @Override // org.apache.cordova.CordovaPlugin
    public void onStop() {
        super.onStop();
    }

    @Override // org.apache.cordova.CordovaPlugin
    public void onResume(boolean z) {
        super.onResume(z);
        SolosManager solosManager = this.mSolosManager;
        if (solosManager != null) {
            solosManager.registerBondStateReceiver();
        }
    }

    @Override // org.apache.cordova.CordovaPlugin
    public void onPause(boolean z) {
        super.onPause(z);
        SolosManager solosManager = this.mSolosManager;
        if (solosManager != null) {
            solosManager.unregisterBondStateReceiver();
        }
    }

    @Override // org.apache.cordova.CordovaPlugin
    public boolean execute(String str, JSONArray jSONArray, CallbackContext callbackContext) throws JSONException {
        BiFunction<JSONArray, CallbackContext, PluginResult> biFunction = this.commandMap.get(str);
        if (biFunction == null) {
            return true;
        }
        callbackContext.sendPluginResult(biFunction.apply(jSONArray, callbackContext));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult init(JSONArray jSONArray, CallbackContext callbackContext) {
        if (this.mSolosManager.isBluetoothEnable()) {
            onBluetoothOn();
        } else {
            onBluetoothOff();
        }
        return new PluginResult(PluginResult.Status.OK);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult startScan(JSONArray jSONArray, CallbackContext callbackContext) {
        this.mSolosManager.startScan();
        return new PluginResult(PluginResult.Status.OK);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult stopScan(JSONArray jSONArray, CallbackContext callbackContext) {
        this.mSolosManager.stopScan();
        return new PluginResult(PluginResult.Status.OK);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult connect(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            if (jSONArray.length() > 0) {
                this.mSolosManager.connect(jSONArray.getString(0));
                return new PluginResult(PluginResult.Status.OK);
            }
            return new PluginResult(PluginResult.Status.ERROR);
        } catch (JSONException e) {
            return new PluginResult(PluginResult.Status.ERROR, e.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult disconnect(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            if (jSONArray.length() > 0 && this.mCurrentConnectedDevice != null) {
                if (!jSONArray.getString(0).equals(macAddressToUUID(this.mCurrentConnectedDevice.getAddress()))) {
                    return new PluginResult(PluginResult.Status.ERROR);
                }
                this.mSolosManager.disconnect(jSONArray.getString(0));
                return new PluginResult(PluginResult.Status.OK);
            }
            return new PluginResult(PluginResult.Status.ERROR);
        } catch (JSONException e) {
            return new PluginResult(PluginResult.Status.ERROR, e.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult startFirmwareUpgrade(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 2);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            if (this.isFirmwareUpgradeStarted) {
                throw new Exception("The firmware upgrade has been started");
            }
            this.mSolosManager.startFirmwareUpgrade(new File(jSONArray.getString(1).replace("file://", "")));
            return new PluginResult(PluginResult.Status.OK);
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult abortFirmwareUpgrade(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 1);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            if (this.isFirmwareUpgradeStarted) {
                throw new Exception("The firmware upgrade has been started");
            }
            this.mSolosManager.abortFirmwareUpgrade();
            return new PluginResult(PluginResult.Status.OK);
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult switchFirmware(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 1);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            if (this.isFirmwareUpgradeStarted) {
                throw new Exception("The firmware upgrade has been started");
            }
            this.mSolosManager.switchFirmware();
            return new PluginResult(PluginResult.Status.OK);
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult getFirmwareVer(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 1);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("fwControlQueue").add(new ActionCallbackContext(callbackContext, ""));
            this.mSolosManager.readFWVersion();
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult enableUart(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 2);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("enableUartQueue").add(new ActionCallbackContext(callbackContext, ""));
            this.mSolosManager.enableUart(jSONArray.getBoolean(1));
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult getTouchFirmwareVer(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 1);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("touchFwQueue").add(new ActionCallbackContext(callbackContext, ""));
            this.mSolosManager.readTouchFWVersion();
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult startTouchFirmwareUpdate(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 2);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            if (this.isFirmwareUpgradeStarted) {
                throw new Exception("The firmware upgrade has been started");
            }
            this.mSolosManager.startTouchFirmwareUpdate(new File(jSONArray.getString(1).replace("file://", "")));
            return new PluginResult(PluginResult.Status.OK);
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult factoryReset(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 1);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("fwControlQueue").add(new ActionCallbackContext(callbackContext, ""));
            this.mSolosManager.factoryReset();
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult getSerialNum(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 1);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            CommandQueues.readConfigRegEventQueue.add(new ActionCallbackContext(callbackContext, "getSerialNum"));
            this.mSolosManager.getSerialNum();
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult readAutoPowerOffTimeout(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 1);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            CommandQueues.readConfigRegEventQueue.add(new ActionCallbackContext(callbackContext, "readAutoPowerOffTimeout"));
            this.mSolosManager.readAutoPowerOffTimeout();
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult writeAutoPowerOffTimeout(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 2);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("writeConfigRegEventQueue").add(new ActionCallbackContext(callbackContext, ""));
            this.mSolosManager.writeAutoPowerOffTimeout(jSONArray.getInt(1));
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult readAutoPowerOffMode(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 1);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            CommandQueues.readConfigRegEventQueue.add(new ActionCallbackContext(callbackContext, "readAutoPowerOffMode"));
            this.mSolosManager.readAutoPowerOffMode();
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult writeAutoPowerOffMode(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 2);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("writeConfigRegEventQueue").add(new ActionCallbackContext(callbackContext, ""));
            this.mSolosManager.writeAutoPowerOffMode(jSONArray.getBoolean(1));
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult readAutoPowerOnUponUnplugCharger(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 1);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            CommandQueues.readConfigRegEventQueue.add(new ActionCallbackContext(callbackContext, "readAutoPowerOnUponUnplugCharger"));
            this.mSolosManager.readSystemConfig();
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult writeAutoPowerOnUponUnplugCharger(JSONArray jSONArray, CallbackContext callbackContext) {
        int i = 2;
        try {
            PluginResult isVaild = isVaild(jSONArray, 2);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("writeConfigRegEventQueue").add(new ActionCallbackContext(callbackContext, ""));
            boolean z = jSONArray.getBoolean(1);
            int i2 = (z ? 1 : 0) | 0;
            if (!this.mMultipoint) {
                i = 0;
            }
            int i3 = i | i2 | (this.mLostLinkMultipleDeviceAlert ? 4 : 0) | (this.mReadHarEnable ? 16 : 0) | (this.mAmbientNoiseDetectionEnable ? 32 : 0);
            int i4 = this.mSmartPowerOff ? 128 : 0;
            this.mUnplugChargerPowerOn = z;
            this.mSolosManager.writeSystemConfig(i3 | i4);
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult getMultipoint(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 1);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            CommandQueues.readConfigRegEventQueue.add(new ActionCallbackContext(callbackContext, "getMultipoint"));
            this.mSolosManager.readSystemConfig();
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult setMultipoint(JSONArray jSONArray, CallbackContext callbackContext) {
        int i = 2;
        try {
            PluginResult isVaild = isVaild(jSONArray, 2);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("writeConfigRegEventQueue").add(new ActionCallbackContext(callbackContext, ""));
            boolean z = jSONArray.getBoolean(1);
            int i2 = (this.mUnplugChargerPowerOn ? 1 : 0) | 0;
            if (!z) {
                i = 0;
            }
            int i3 = i | i2 | (this.mLostLinkMultipleDeviceAlert ? 4 : 0) | (this.mReadHarEnable ? 16 : 0) | (this.mAmbientNoiseDetectionEnable ? 32 : 0);
            int i4 = this.mSmartPowerOff ? 128 : 0;
            this.mMultipoint = z;
            this.mSolosManager.writeSystemConfig(i3 | i4);
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult readOnHeadVoicePromptVolume(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 1);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            CommandQueues.readConfigRegEventQueue.add(new ActionCallbackContext(callbackContext, "readOnHeadVoicePromptVolume"));
            this.mSolosManager.readOnHeadVoicePromptVolume();
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult writeOnHeadVoicePromptVolume(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 2);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("writeConfigRegEventQueue").add(new ActionCallbackContext(callbackContext, ""));
            this.mSolosManager.writeOnHeadVoicePromptVolume(jSONArray.getInt(1));
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult readOffHeadVoicePromptVolume(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 1);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            CommandQueues.readConfigRegEventQueue.add(new ActionCallbackContext(callbackContext, "readOffHeadVoicePromptVolume"));
            this.mSolosManager.readOffHeadVoicePromptVolume();
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult writeOffHeadVoicePromptVolume(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 2);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("writeConfigRegEventQueue").add(new ActionCallbackContext(callbackContext, ""));
            this.mSolosManager.writeOffHeadVoicePromptVolume(jSONArray.getInt(1));
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult readLostLinkAlertDuration(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 1);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            CommandQueues.readConfigRegEventQueue.add(new ActionCallbackContext(callbackContext, "readLostLinkAlertDuration"));
            this.mSolosManager.readLostLinkAlertDuration();
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult writeLostLinkAlertDuration(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 2);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("writeConfigRegEventQueue").add(new ActionCallbackContext(callbackContext, ""));
            this.mSolosManager.writeLostLinkAlertDuration(jSONArray.getInt(1));
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult readLostLinkAlertVolume(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 1);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            CommandQueues.readConfigRegEventQueue.add(new ActionCallbackContext(callbackContext, "readLostLinkAlertVolume"));
            this.mSolosManager.readLostLinkAlertVolume();
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult writeLostLinkAlertVolume(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 2);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("writeLostLinkAlertVolume").add(new ActionCallbackContext(callbackContext, ""));
            this.mSolosManager.writeLostLinkAlertVolume(jSONArray.getInt(1));
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult readLostLinkMultipleDevicesAlertMode(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 1);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            CommandQueues.readConfigRegEventQueue.add(new ActionCallbackContext(callbackContext, "readLostLinkMultipleDevicesAlertMode"));
            this.mSolosManager.readSystemConfig();
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult writeLostLinkMultipleDevicesAlertMode(JSONArray jSONArray, CallbackContext callbackContext) {
        int i = 2;
        try {
            PluginResult isVaild = isVaild(jSONArray, 2);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("writeConfigRegEventQueue").add(new ActionCallbackContext(callbackContext, ""));
            boolean z = jSONArray.getBoolean(1);
            int i2 = (this.mUnplugChargerPowerOn ? 1 : 0) | 0;
            if (!this.mMultipoint) {
                i = 0;
            }
            int i3 = i | i2 | (z ? 4 : 0) | (this.mReadHarEnable ? 16 : 0) | (this.mAmbientNoiseDetectionEnable ? 32 : 0);
            int i4 = this.mSmartPowerOff ? 128 : 0;
            this.mLostLinkMultipleDeviceAlert = z;
            this.mSolosManager.writeSystemConfig(i3 | i4);
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult setAmbientNoiseDetectionEnable(JSONArray jSONArray, CallbackContext callbackContext) {
        int i = 2;
        try {
            PluginResult isVaild = isVaild(jSONArray, 2);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("writeConfigRegEventQueue").add(new ActionCallbackContext(callbackContext, ""));
            boolean z = jSONArray.getBoolean(1);
            int i2 = (this.mUnplugChargerPowerOn ? 1 : 0) | 0;
            if (!this.mMultipoint) {
                i = 0;
            }
            int i3 = i | i2 | (this.mLostLinkMultipleDeviceAlert ? 4 : 0) | (this.mReadHarEnable ? 16 : 0) | (z ? 32 : 0);
            int i4 = this.mSmartPowerOff ? 128 : 0;
            this.mAmbientNoiseDetectionEnable = z;
            this.mSolosManager.writeSystemConfig(i3 | i4);
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult getAmbientNoiseDetectionEnable(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 1);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            CommandQueues.readConfigRegEventQueue.add(new ActionCallbackContext(callbackContext, "readAmbientNoiseDetectionEnable"));
            this.mSolosManager.readSystemConfig();
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult setHarDetectionEnable(JSONArray jSONArray, CallbackContext callbackContext) {
        int i = 2;
        try {
            PluginResult isVaild = isVaild(jSONArray, 2);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("writeConfigRegEventQueue").add(new ActionCallbackContext(callbackContext, ""));
            boolean z = jSONArray.getBoolean(1);
            int i2 = (this.mUnplugChargerPowerOn ? 1 : 0) | 0;
            if (!this.mMultipoint) {
                i = 0;
            }
            int i3 = i | i2 | (this.mLostLinkMultipleDeviceAlert ? 4 : 0) | (z ? 16 : 0) | (this.mAmbientNoiseDetectionEnable ? 32 : 0);
            int i4 = this.mSmartPowerOff ? 128 : 0;
            this.mReadHarEnable = z;
            this.mSolosManager.writeSystemConfig(i3 | i4);
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult getHarDetectionEnable(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 1);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            CommandQueues.readConfigRegEventQueue.add(new ActionCallbackContext(callbackContext, "readHarDetectionEnable"));
            this.mSolosManager.readSystemConfig();
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult setSmartPowerOffEnable(JSONArray jSONArray, CallbackContext callbackContext) {
        int i = 2;
        try {
            PluginResult isVaild = isVaild(jSONArray, 2);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("writeConfigRegEventQueue").add(new ActionCallbackContext(callbackContext, ""));
            boolean z = jSONArray.getBoolean(1);
            int i2 = (this.mUnplugChargerPowerOn ? 1 : 0) | 0;
            if (!this.mMultipoint) {
                i = 0;
            }
            int i3 = i | i2 | (this.mLostLinkMultipleDeviceAlert ? 4 : 0) | (this.mReadHarEnable ? 16 : 0) | (this.mAmbientNoiseDetectionEnable ? 32 : 0);
            int i4 = z ? 128 : 0;
            this.mSmartPowerOff = z;
            this.mSolosManager.writeSystemConfig(i3 | i4);
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult getSmartPowerOffEnable(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 1);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            CommandQueues.readConfigRegEventQueue.add(new ActionCallbackContext(callbackContext, "getSmartPowerOffEnable"));
            this.mSolosManager.readSystemConfig();
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult initSensors(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 1);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            CommandQueues.readConfigRegEventQueue.add(new ActionCallbackContext(callbackContext, "initSensors"));
            this.mSolosManager.readSensorStatus();
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult enableGestureEvent(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 2);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("enableSensorEventQueue").add(new ActionCallbackContext(callbackContext, ""));
            this.mSolosManager.enableGestureEvent(jSONArray.getBoolean(1));
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult enableGAMSensors(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 2);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("writeConfigRegEventQueue").add(new ActionCallbackContext(callbackContext, ""));
            this.mSolosManager.enableGAMSensors(jSONArray.getBoolean(1));
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult enableProximitySensor(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 2);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("writeConfigRegEventQueue").add(new ActionCallbackContext(callbackContext, ""));
            this.mSolosManager.enableProximity(jSONArray.getBoolean(1));
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult resetStepCountSensor(JSONArray jSONArray, CallbackContext callbackContext) {
        this.mSolosManager.resetStepCountSensor();
        return new PluginResult(PluginResult.Status.OK);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult enableStepCountSensor(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 2);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("writeConfigRegEventQueue").add(new ActionCallbackContext(callbackContext, ""));
            this.mSolosManager.enableStepCountSensor(jSONArray.getBoolean(1));
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult enableStepCountEvent(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 2);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("enableSensorEventQueue").add(new ActionCallbackContext(callbackContext, ""));
            this.mSolosManager.enableStepCountEvent(jSONArray.getBoolean(1));
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult enableGAMEvents(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 4);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("enableSensorEventQueue").add(new ActionCallbackContext(callbackContext, ""));
            this.mSolosManager.enableGAMEvents(jSONArray.getBoolean(1), jSONArray.getBoolean(2), jSONArray.getBoolean(3), jSONArray.getBoolean(4));
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult setGAMEventInterval(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 2);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            if (jSONArray.getInt(1) > 65535) {
                return new PluginResult(PluginResult.Status.ERROR, "Interval out of range");
            }
            this.commandQueue.str2Queue.get("setSensorEventIntervalQueue").add(new ActionCallbackContext(callbackContext, ""));
            Log.d(this.TAG, "GAIA_QCC_PLUGIN: setGAMEventInterval");
            this.mSolosManager.setGAMEventInterval(jSONArray.getInt(1));
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult setTapSensitivity(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 4);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            int i = (jSONArray.getInt(1) & 255) +
                    ((jSONArray.getBoolean(2) ? 0 : 1) << 13) +
                    ((jSONArray.getBoolean(3) ? 0 : 1) << 14) +
                    ((jSONArray.getBoolean(4) ? 0 : 1) << 15);

            this.commandQueue.str2Queue.get("writeConfigRegEventQueue").add(new ActionCallbackContext(callbackContext, ""));
            this.mSolosManager.setTapSensitivity(i);
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult getTapSensitivity(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 1);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            CommandQueues.readConfigRegEventQueue.add(new ActionCallbackContext(callbackContext, "getTapSensitivity"));
            this.mSolosManager.getTapSensitivity();
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult getCalibrationData(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 1);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("readDSPConfigParam").add(new ActionCallbackContext(callbackContext, ""));
            this.mSolosManager.getCalibrationData();
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult setMagnetCalibrationData(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 7);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.mSolosManager.setMagnetCalibrationData(jSONArray.getInt(1), jSONArray.getInt(2), jSONArray.getInt(3), jSONArray.getInt(4), jSONArray.getInt(5), jSONArray.getInt(6));
            return new PluginResult(PluginResult.Status.OK);
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult setGyroAccelMagnetCalibrationData(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 13);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            int i = jSONArray.getInt(1);
            int i2 = jSONArray.getInt(2);
            int i3 = jSONArray.getInt(3);
            int i4 = jSONArray.getInt(4);
            int i5 = jSONArray.getInt(5);
            int i6 = jSONArray.getInt(6);
            int i7 = jSONArray.getInt(7);
            int i8 = jSONArray.getInt(8);
            int i9 = jSONArray.getInt(9);
            int i10 = jSONArray.getInt(10);
            int i11 = jSONArray.getInt(11);
            int i12 = jSONArray.getInt(12);
            this.commandQueue.str2Queue.get("setGyroAccelMagnetCalibrationQueue").add(new ActionCallbackContext(callbackContext, ""));
            this.mSolosManager.setGyroAccelMagnetCalibrationData(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12);
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult setLanguage(JSONArray jSONArray, CallbackContext callbackContext) {
        byte b = 2;
        try {
            PluginResult isVaild = isVaild(jSONArray, 2);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("setLanguageEventQueue").add(new ActionCallbackContext(callbackContext, ""));
            if (!jSONArray.getString(1).equals("zh-hans") && !jSONArray.getString(1).equals("zh-hant")) {
                if (!jSONArray.getString(1).equals("zh-hant-hk")) {
                    b = 0;
                }
                this.mSolosManager.setLanguage(b);
                PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
                pluginResult.setKeepCallback(true);
                return pluginResult;
            }
            b = 1;
            this.mSolosManager.setLanguage(b);
            PluginResult pluginResult2 = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult2.setKeepCallback(true);
            return pluginResult2;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult getUserEqualizerOn(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 1);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("getUserEqualizerOnQueue").add(new ActionCallbackContext(callbackContext, ""));
            this.mSolosManager.getUserEqualizerOn();
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult setUserEqualizerOn(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 2);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("setUserEqualizerOnQueue").add(new ActionCallbackContext(callbackContext, ""));
            this.mSolosManager.setUserEqualizerOn(jSONArray.getBoolean(1));
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult getMusicEqualizerMasterGain(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 1);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("getMusicEqualizerMasterGainQueue").add(new ActionCallbackContext(callbackContext, ""));
            this.mSolosManager.getMusicEqualizerMasterGain();
            return new PluginResult(PluginResult.Status.OK);
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult setMusicEqualizerMasterGain(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 2);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("setMusicEqualizerMasterGainQueue").add(new ActionCallbackContext(callbackContext, ""));
            this.mSolosManager.setMusicEqualizerMasterGain(jSONArray.getInt(1) * 60);
            return new PluginResult(PluginResult.Status.OK);
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult getMusicEqualizerGain(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 2);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            if (jSONArray.getInt(1) >= 1 && jSONArray.getInt(1) <= 5) {
                this.commandQueue.str2Queue.get("getMusicEqualizerGainQueue").add(new ActionCallbackContext(callbackContext, ""));
                this.mSolosManager.getMusicEqualizerGain(jSONArray.getInt(1));
                PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
                pluginResult.setKeepCallback(true);
                return pluginResult;
            }
            return new PluginResult(PluginResult.Status.ERROR, "Invalid band number");
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult setMusicEqualizerGain(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 3);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            if (jSONArray.getInt(1) >= 1 && jSONArray.getInt(1) <= 5) {
                this.commandQueue.str2Queue.get("setMusicEqualizerGainQueue").add(new ActionCallbackContext(callbackContext, ""));
                this.mSolosManager.setMusicEqualizerGain(jSONArray.getInt(1), jSONArray.getInt(2) * 60);
                PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
                pluginResult.setKeepCallback(true);
                return pluginResult;
            }
            return new PluginResult(PluginResult.Status.ERROR, "Invalid band number");
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult getVoiceEqualizerGain(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 2);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            if (jSONArray.getInt(1) >= 1 && jSONArray.getInt(1) <= 5) {
                ActionCallbackContext actionCallbackContext = new ActionCallbackContext(callbackContext, "");
                actionCallbackContext.userData = jSONArray;
                this.commandQueue.str2Queue.get("readDSPConfigParam").add(actionCallbackContext);
                this.mSolosManager.getVoiceEqualizerGain();
                PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
                pluginResult.setKeepCallback(true);
                return pluginResult;
            }
            return new PluginResult(PluginResult.Status.ERROR, "Invalid band number");
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult setVoiceEqualizerGain(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 3);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            if (jSONArray.getInt(1) >= 1 && jSONArray.getInt(1) <= 5) {
                int i = jSONArray.getInt(2);
                if (i < 0) {
                    i = 6 - i;
                }
                short s = (short) i;
                this.dspConfig.setDownlinkVoiceEQGain(jSONArray.getInt(1), s);
                this.dspConfig.eqDownlinkAGCEnable = (short) 2;
                this.dspConfig.eqDownlinkOutFlag = Short.MIN_VALUE;
                this.mSolosManager.setVoiceEqualizerGain(this.dspConfig);
                this.mSolosManager.setWhisperDownlinkRTEnable(this.dspConfig.eqDownlinkAGCEnable);
                this.mSolosManager.setWhisperDownlinkRTOutFlag(this.dspConfig.eqDownlinkOutFlag);
                this.mSolosManager.setWhisperDownlinkRTBandGain(jSONArray.getInt(1), s);
                return new PluginResult(PluginResult.Status.OK);
            }
            return new PluginResult(PluginResult.Status.ERROR, "Invalid band number");
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult getMusicEqualizerFrequency(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 2);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            if (jSONArray.getInt(1) >= 1 && jSONArray.getInt(1) <= 5) {
                this.commandQueue.str2Queue.get("getMusicEqualizerFrequencyQueue").add(new ActionCallbackContext(callbackContext, ""));
                this.mSolosManager.getMusicEqualizerFrequency(jSONArray.getInt(1));
                PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
                pluginResult.setKeepCallback(true);
                return pluginResult;
            }
            return new PluginResult(PluginResult.Status.ERROR, "Invalid band number");
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult setMusicEqualizerFrequency(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 3);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            if (jSONArray.getInt(1) >= 1 && jSONArray.getInt(1) <= 5) {
                this.commandQueue.str2Queue.get("setMusicEqualizerFrequencyQueue").add(new ActionCallbackContext(callbackContext, ""));
                this.mSolosManager.setMusicEqualizerFrequency(jSONArray.getInt(1), jSONArray.getInt(2) * 3);
                PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
                pluginResult.setKeepCallback(true);
                return pluginResult;
            }
            return new PluginResult(PluginResult.Status.ERROR, "Invalid band number");
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult getMusicEqualizerQFactor(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 2);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            if (jSONArray.getInt(1) >= 1 && jSONArray.getInt(1) <= 5) {
                this.commandQueue.str2Queue.get("getMusicEqualizerQFactorQueue").add(new ActionCallbackContext(callbackContext, ""));
                this.mSolosManager.getMusicEqualizerQFactor(jSONArray.getInt(1));
                PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
                pluginResult.setKeepCallback(true);
                return pluginResult;
            }
            return new PluginResult(PluginResult.Status.ERROR, "Invalid band number");
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult setMusicEqualizerQFactor(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 3);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            if (jSONArray.getInt(1) >= 1 && jSONArray.getInt(1) <= 5) {
                this.commandQueue.str2Queue.get("setMusicEqualizerQFactorQueue").add(new ActionCallbackContext(callbackContext, ""));
                this.mSolosManager.setMusicEqualizerQFactor(jSONArray.getInt(1), (int) (jSONArray.getDouble(2) * 4096.0d));
                PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
                pluginResult.setKeepCallback(true);
                return pluginResult;
            }
            return new PluginResult(PluginResult.Status.ERROR, "Invalid band number");
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult getMusicEqualizerFilterType(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 2);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            if (jSONArray.getInt(1) >= 1 && jSONArray.getInt(1) <= 5) {
                this.commandQueue.str2Queue.get("getMusicEqualizerFilterTypeQueue").add(new ActionCallbackContext(callbackContext, ""));
                this.mSolosManager.getMusicEqualizerFilterType(jSONArray.getInt(1));
                PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
                pluginResult.setKeepCallback(true);
                return pluginResult;
            }
            return new PluginResult(PluginResult.Status.ERROR, "Invalid band number");
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult setVoiceEqualizerEnable(JSONArray jSONArray, CallbackContext callbackContext) {
        int i = 2;
        try {
            PluginResult isVaild = isVaild(jSONArray, 2);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            DSPConfig dSPConfig = this.dspConfig;
            if (!jSONArray.getBoolean(1)) {
                i = 0;
            }
            dSPConfig.eqDownlinkAGCEnable = (short) i;
            this.dspConfig.eqDownlinkOutFlag = Short.MIN_VALUE;
            this.mSolosManager.setVoiceEqualizerGain(this.dspConfig);
            this.mSolosManager.setWhisperDownlinkRTEnable(this.dspConfig.eqDownlinkAGCEnable);
            this.mSolosManager.setWhisperDownlinkRTOutFlag(this.dspConfig.eqDownlinkOutFlag);
            return new PluginResult(PluginResult.Status.OK);
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult getVoiceEqualizerEnable(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 1);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("getVoiceEqualizerEnable").add(new ActionCallbackContext(callbackContext, ""));
            this.mSolosManager.getVoiceEqualizerGain();
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult setMusicEqualizerFilterType(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 3);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            if (jSONArray.getInt(1) >= 1 && jSONArray.getInt(1) <= 5) {
                this.commandQueue.str2Queue.get("setMusicEqualizerFilterTypeQueue").add(new ActionCallbackContext(callbackContext, ""));
                this.mSolosManager.setMusicEqualizerFilterType(jSONArray.getInt(1), jSONArray.getInt(2));
                PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
                pluginResult.setKeepCallback(true);
                return pluginResult;
            }
            return new PluginResult(PluginResult.Status.ERROR, "Invalid band number");
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult getVoiceProEqualizerGain(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 2);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            int i = jSONArray.getInt(1);
            if (i >= 1 && i <= 10) {
                ActionCallbackContext actionCallbackContext = new ActionCallbackContext(callbackContext, "");
                actionCallbackContext.userData = jSONArray;
                this.commandQueue.str2Queue.get("readDSPConfigParam").add(actionCallbackContext);
                this.mSolosManager.getVoiceProEqualizerGain();
                PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
                pluginResult.setKeepCallback(true);
                return pluginResult;
            }
            return new PluginResult(PluginResult.Status.ERROR, "Invalid band number");
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult setVoiceProEqualizerGain(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 3);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            int i = jSONArray.getInt(1);
            if (i >= 1 && i <= 10) {
                int i2 = jSONArray.getInt(2);
                if (i2 < 0) {
                    i2 = 6 - i2;
                }
                short s = (short) i2;
                this.dspConfig.setDownlinkVoiceEQGain(i, s);
                this.dspConfig.whisperEqOutFlag = Short.MIN_VALUE;
                this.mSolosManager.setVoiceProEqualizerGain(this.dspConfig);
                this.mSolosManager.setWhisperEqRTOutFlag(this.dspConfig.whisperEqOutFlag);
                this.mSolosManager.setWhisperEqRTBandGain(i, s);
                return new PluginResult(PluginResult.Status.OK);
            }
            return new PluginResult(PluginResult.Status.ERROR, "Invalid band number");
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult getVoiceProEqualizerEnable(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 1);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("getVoiceProEqualizerEnable").add(new ActionCallbackContext(callbackContext, ""));
            this.mSolosManager.getVoiceProEqualizerGain();
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult setVoiceProEqualizerEnable(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 2);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.dspConfig.whisperEqOutFlag = jSONArray.getBoolean(1) ? Short.MIN_VALUE : (short) 0;
            this.mSolosManager.setVoiceProEqualizerGain(this.dspConfig);
            this.mSolosManager.setWhisperEqRTOutFlag(this.dspConfig.whisperEqOutFlag);
            return new PluginResult(PluginResult.Status.OK);
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult setRTC(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 2);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("setRTCQueue").add(new ActionCallbackContext(callbackContext, ""));
            this.mSolosManager.setRTC(jSONArray.getInt(1) - ((int) this.refDate));
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult set247Monitor(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 3);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("set247MonitorQueue").add(new ActionCallbackContext(callbackContext, ""));
            this.mSolosManager.set247Monitor(jSONArray.getBoolean(1), jSONArray.getBoolean(2));
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult get247Monitor(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 1);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("get247MonitorQueue").add(new ActionCallbackContext(callbackContext, ""));
            this.mSolosManager.get247Monitor();
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult set247Reporting(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 5);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("set247ReportingQueue").add(new ActionCallbackContext(callbackContext, ""));
            this.mSolosManager.set247Reporting(jSONArray.getBoolean(1), jSONArray.getBoolean(2), jSONArray.getBoolean(3), jSONArray.getInt(4) - ((int) this.refDate));
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult get247Reporting(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 3);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("get247ReportingQueue").add(new ActionCallbackContext(callbackContext, ""));
            this.mSolosManager.get247Reporting();
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult get247StepCount(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 3);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("get247StepCountQueue").add(new ActionCallbackContext(callbackContext, ""));
            int i = jSONArray.getInt(1);
            this.mSolosManager.get247StepCount(i - ((int) this.refDate), jSONArray.getInt(2));
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult delete247StepCount(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 3);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("delete247StepCountQueue").add(new ActionCallbackContext(callbackContext, ""));
            int i = jSONArray.getInt(1);
            this.mSolosManager.delete247StepCount(i - ((int) this.refDate), jSONArray.getInt(2));
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult set247PostureConfig(JSONArray jSONArray, CallbackContext callbackContext) {
        int ordinal;
        try {
            PluginResult isVaild = isVaild(jSONArray, 5);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("set247PostureConfigQueue").add(new ActionCallbackContext(callbackContext, ""));
            int i = jSONArray.getInt(1);
            int i2 = jSONArray.getInt(2);
            int i3 = jSONArray.getInt(3);
            String string = jSONArray.getString(4);
            if (string.equals("none")) {
                ordinal = CommandSpecification.AlertMethod.NONE.ordinal();
            } else if (string.equals("beep")) {
                ordinal = CommandSpecification.AlertMethod.BEEP.ordinal();
            } else {
                ordinal = CommandSpecification.AlertMethod.VOICE.ordinal();
            }
            this.mSolosManager.set247PostureConfig(i, i2, i3, ordinal);
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult get247PostureConfig(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 1);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("get247PostureConfigQueue").add(new ActionCallbackContext(callbackContext, ""));
            this.mSolosManager.get247PostureConfig();
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult get247Posture(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 1);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("get247PostureQueue").add(new ActionCallbackContext(callbackContext, ""));
            int i = jSONArray.getInt(1);
            this.mSolosManager.get247Posture(i - ((int) this.refDate), jSONArray.getInt(2));
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult get247Posture2(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 1);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("get247PostureQueue").add(new ActionCallbackContext(callbackContext, ""));
            int i = jSONArray.getInt(1);
            this.mSolosManager.get247Posture2(i - ((int) this.refDate), jSONArray.getInt(2));
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult delete247Posture(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 3);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("delete247PostureQueue").add(new ActionCallbackContext(callbackContext, ""));
            int i = jSONArray.getInt(1);
            this.mSolosManager.delete247Posture(i - ((int) this.refDate), jSONArray.getInt(2));
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult readPostureReminderVolume(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 1);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            CommandQueues.readConfigRegEventQueue.add(new ActionCallbackContext(callbackContext, "readPostureReminderVolume"));
            this.mSolosManager.readPostureReminderVolume();
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult writePostureReminderVolume(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 2);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("writeConfigRegEventQueue").add(new ActionCallbackContext(callbackContext, ""));
            this.mSolosManager.writePostureReminderVolume(jSONArray.getInt(1));
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult readProximity(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 1);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("readProximityEventQueue").add(new ActionCallbackContext(callbackContext, ""));
            this.mSolosManager.readProximity();
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onProximityValueAvailable(String str, long j) {
        callback(String.format("onProximityValueAvailable({uuid:\"%s\", value:%d})", str, Long.valueOf(j)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult setASRMode(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 2);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            byte b = (byte) jSONArray.getInt(1);
            this.commandQueue.str2Queue.get("setASRModeQueue").add(new ActionCallbackContext(callbackContext, ""));
            this.mSolosManager.setASRMode(b);
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult setOrientationDetectionEnable(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 2);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("writeConfigRegEventQueue").add(new ActionCallbackContext(callbackContext, ""));
            this.mSolosManager.setOrientationDetectionEnable(jSONArray.getBoolean(1));
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult getOrientationDetectionEnable(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 1);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            return new PluginResult(PluginResult.Status.OK, this.mSolosManager.getOrientationDetectionEnable());
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult setInputConfig(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 5);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("writeConfigRegEventQueue").add(new ActionCallbackContext(callbackContext, ""));
            this.mSolosManager.setInputConfig(jSONArray.getInt(1), jSONArray.getInt(2), jSONArray.getInt(3), jSONArray.getBoolean(4));
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult getInputConfig(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 3);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            CommandQueues.readConfigRegEventQueue.add(new ActionCallbackContext(callbackContext, "getInputConfig"));
            this.mSolosManager.getInputConfig(jSONArray.getInt(1), jSONArray.getBoolean(2));
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult setVolatileInputConfigTimeout(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 2);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            int i = jSONArray.getInt(1);
            this.commandQueue.str2Queue.get("setVolatileConfigTimeoutQueue").add(new ActionCallbackContext(callbackContext, ""));
            this.mSolosManager.setVolatileInputConfigTimeout(i);
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult readBattery(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            if (this.mCurrentConnectedDevice == null) {
                return new PluginResult(PluginResult.Status.ERROR, "The device is not connected");
            }
            if (!jSONArray.getString(0).equals(macAddressToUUID(this.mCurrentConnectedDevice.getAddress()))) {
                return new PluginResult(PluginResult.Status.ERROR, "UUID does not match the connected device");
            }
            if (jSONArray.length() < 1) {
                return new PluginResult(PluginResult.Status.ERROR, "Wrong number of data argument");
            }
            this.commandQueue.str2Queue.get("readBatteryEventQueue").add(new ActionCallbackContext(callbackContext, ""));
            this.mSolosManager.readBatteryLevel();
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult resetDevice(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            if (this.mCurrentConnectedDevice == null) {
                return new PluginResult(PluginResult.Status.ERROR, "The device is not connected");
            }
            if (!jSONArray.getString(0).equals(macAddressToUUID(this.mCurrentConnectedDevice.getAddress()))) {
                return new PluginResult(PluginResult.Status.ERROR, "UUID does not match the connected device");
            }
            if (jSONArray.length() < 1) {
                return new PluginResult(PluginResult.Status.ERROR, "Wrong number of data argument");
            }
            this.commandQueue.str2Queue.get("resetDeviceEventQueue").add(new ActionCallbackContext(callbackContext, ""));
            this.mSolosManager.resetDevice();
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult rebootDevice(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            if (this.mCurrentConnectedDevice == null) {
                return new PluginResult(PluginResult.Status.ERROR, "This device is not connected");
            }
            if (!jSONArray.getString(0).equals(macAddressToUUID(this.mCurrentConnectedDevice.getAddress()))) {
                return new PluginResult(PluginResult.Status.ERROR, "UUID does not match the connected device");
            }
            if (jSONArray.length() < 1) {
                return new PluginResult(PluginResult.Status.ERROR, "Wrong number of data argument");
            }
            this.commandQueue.str2Queue.get("rebootDeviceEventQueue").add(new ActionCallbackContext(callbackContext, ""));
            this.mSolosManager.rebootDevice(jSONArray.getInt(1));
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult setAvEvents(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 2);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("avRemoteControlQueue").add(new ActionCallbackContext(callbackContext, ""));
            boolean z = jSONArray.getBoolean(1);
            this.mSolosManager.setAvEvents(z, z, z, z, z);
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult getAvEvents(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 1);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("avRemoteControlQueue").add(new ActionCallbackContext(callbackContext, ""));
            this.mSolosManager.getAvEvents();
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult getAvInfo(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 1);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("avRemoteControlQueue").add(new ActionCallbackContext(callbackContext, ""));
            this.mSolosManager.getAvInfo();
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult setAvControl(JSONArray jSONArray, CallbackContext callbackContext) {
        char c;
        try {
            PluginResult isVaild = isVaild(jSONArray, 2);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("avRemoteControlQueue").add(new ActionCallbackContext(callbackContext, ""));
            String string = jSONArray.getString(1);
            byte b = 0;
            switch (string.hashCode()) {
                case 2392819:
                    if (string.equals("NEXT")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 2458420:
                    if (string.equals("PLAY")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case 2464307:
                    if (string.equals("PREV")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case 2640344:
                    if (string.equals("VOL+")) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case 2640346:
                    if (string.equals("VOL-")) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                case 75902422:
                    if (string.equals("PAUSE")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            if (c == 0) {
                b = CommandSpecification.MEDIA_PLAY;
            } else if (c == 1) {
                b = CommandSpecification.MEDIA_PAUSE;
            } else if (c == 2) {
                b = CommandSpecification.MEDIA_NEXT;
            } else if (c == 3) {
                b = CommandSpecification.MEDIA_PREV;
            } else if (c == 4) {
                b = CommandSpecification.MEDIA_VOL_UP;
            } else if (c != 5) {
                Log.e(this.TAG, "Unknown code = " + string);
            } else {
                b = CommandSpecification.MEDIA_VOL_DOWN;
            }
            this.mSolosManager.setAvControl(b);
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult setWindFilterOn(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 2);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("setWhisperFeature").add(new ActionCallbackContext(callbackContext, "setWindFilterOn"));
            boolean z = jSONArray.getBoolean(1);
            DSPConfig dSPConfig = this.dspConfig;
            dSPConfig.whisperFeature = z ? dSPConfig.whisperFeature | 1 : dSPConfig.whisperFeature & 65534;
            this.dspConfig.windNoiseThreshold = 996;
            this.mSolosManager.setWhisperFeature(this.dspConfig);
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult getWindFilterOn(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 1);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("getWhisperFeature").add(new ActionCallbackContext(callbackContext, "getWindFilterOn"));
            this.mSolosManager.getWhisperFeature();
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult setAgcNoiseLevelUpAlpha(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 2);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("setWhisperFeature").add(new ActionCallbackContext(callbackContext, "setAgcNoiseLevelUpAlpha"));
            this.dspConfig.agcNoiseLevelUpAlpha = jSONArray.getInt(1);
            this.mSolosManager.setWhisperFeature(this.dspConfig);
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult getAgcNoiseLevelUpAlpha(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 1);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("getWhisperFeature").add(new ActionCallbackContext(callbackContext, "getAgcNoiseLevelUpAlpha"));
            this.mSolosManager.getWhisperFeature();
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult setAgcNoiseLevelDnAlpha(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 2);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("setWhisperFeature").add(new ActionCallbackContext(callbackContext, "setAgcNoiseLevelDnAlpha"));
            this.dspConfig.agcNoiseLevelDnAlpha = jSONArray.getInt(1);
            this.mSolosManager.setWhisperFeature(this.dspConfig);
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult getAgcNoiseLevelDnAlpha(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 1);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("getWhisperFeature").add(new ActionCallbackContext(callbackContext, "getAgcNoiseLevelDnAlpha"));
            this.mSolosManager.getWhisperFeature();
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult setWhisperOn(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 2);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("setWhisperOn").add(new ActionCallbackContext(callbackContext, ""));
            boolean z = jSONArray.getBoolean(1);
            this.dspConfig.outFlag = z ? 0 : 256;
            this.mSolosManager.setWhisper(this.dspConfig);
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult getWhisperOn(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 1);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("getWhisperOn").add(new ActionCallbackContext(callbackContext, ""));
            this.mSolosManager.getWhisper();
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult getWhisperDataRtOutGain(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 1);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            CommandQueues.readConfigRegEventQueue.add(new ActionCallbackContext(callbackContext, "getWhisperDataRtOutGain"));
            this.mSolosManager.getWhisperDataRtOutGain();
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult getWhisperDataRtOutFlag(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 1);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            CommandQueues.readConfigRegEventQueue.add(new ActionCallbackContext(callbackContext, "getWhisperDataRtOutFlag"));
            this.mSolosManager.getWhisperDataRtOutFlag();
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult setTranslationMode(JSONArray jSONArray, CallbackContext callbackContext) {
        try {
            PluginResult isVaild = isVaild(jSONArray, 2);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            this.commandQueue.str2Queue.get("setWhisperOn").add(new ActionCallbackContext(callbackContext, ""));
            int i = jSONArray.getInt(1);
            this.dspConfig.outFlag = 0;
            this.dspConfig.outGain = 17;
            if (i == 1) {
                this.dspConfig.outFlag = 0;
                this.dspConfig.outGain = 0;
            } else if (i == 2) {
                this.dspConfig.outFlag = 32775;
                this.dspConfig.outGain = 19;
            } else if (i == 3) {
                this.dspConfig.outFlag = 32775;
                this.dspConfig.outGain = 0;
            }
            this.mSolosManager.setWhisper(this.dspConfig);
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult setTranslationModeRt(JSONArray jSONArray, CallbackContext callbackContext) {
        long j;
        try {
            PluginResult isVaild = isVaild(jSONArray, 2);
            if (isVaild.getStatus() == PluginResult.Status.ERROR.ordinal()) {
                return isVaild;
            }
            int i = jSONArray.getInt(1);
            if (i == 2) {
                j = 19;
            } else {
                if (i != 1 && i != 3) {
                    j = 17;
                }
                j = 0;
            }
            if (jSONArray.length() >= 4) {
                j = jSONArray.getInt(2);
            }
            this.commandQueue.str2Queue.get("writeConfigRegEventQueue").add(new ActionCallbackContext(callbackContext, "setTranslationModeRt", jSONArray));
            this.mSolosManager.setWhisperDataRtOutGain(j);
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            return pluginResult;
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0023 A[Catch: Exception -> 0x0046, TryCatch #0 {Exception -> 0x0046, blocks: (B:7:0x000a, B:12:0x001c, B:14:0x0023, B:15:0x0028), top: B:6:0x000a }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onWriteConfigParam4SetTranslationModeRt(cordova.plugins.qcc.GaiaLibrary.gaia.ActionCallbackContext r4, int r5, int r6, int r7) {
        /*
            r3 = this;
            if (r5 != 0) goto L58
            r5 = 16
            if (r6 != r5) goto L58
            r5 = 2
            if (r7 == r5) goto La
            goto L58
        La:
            org.json.JSONArray r6 = r4.userData     // Catch: java.lang.Exception -> L46
            r7 = 1
            int r7 = r6.getInt(r7)     // Catch: java.lang.Exception -> L46
            r0 = 3
            if (r7 == r5) goto L1a
            if (r7 != r0) goto L17
            goto L1a
        L17:
            r1 = 0
            goto L1c
        L1a:
            r1 = 7
        L1c:
            int r5 = r6.length()     // Catch: java.lang.Exception -> L46
            r7 = 4
            if (r5 < r7) goto L28
            int r5 = r6.getInt(r0)     // Catch: java.lang.Exception -> L46
            long r1 = (long) r5     // Catch: java.lang.Exception -> L46
        L28:
            cordova.plugins.qcc.GaiaLibrary.gaia.CommandQueues r5 = r3.commandQueue     // Catch: java.lang.Exception -> L46
            java.util.HashMap<java.lang.String, java.util.Queue<cordova.plugins.qcc.GaiaLibrary.gaia.ActionCallbackContext>> r5 = r5.str2Queue     // Catch: java.lang.Exception -> L46
            java.lang.String r6 = "writeConfigRegEventQueue"
            java.lang.Object r5 = r5.get(r6)     // Catch: java.lang.Exception -> L46
            java.util.Queue r5 = (java.util.Queue) r5     // Catch: java.lang.Exception -> L46
            cordova.plugins.qcc.GaiaLibrary.gaia.ActionCallbackContext r6 = new cordova.plugins.qcc.GaiaLibrary.gaia.ActionCallbackContext     // Catch: java.lang.Exception -> L46
            org.apache.cordova.CallbackContext r7 = r4.callbackContext     // Catch: java.lang.Exception -> L46
            java.lang.String r0 = ""
            r6.<init>(r7, r0)     // Catch: java.lang.Exception -> L46
            r5.add(r6)     // Catch: java.lang.Exception -> L46
            cordova.plugins.qcc.SolosManager r5 = r3.mSolosManager     // Catch: java.lang.Exception -> L46
            r5.setWhisperDataRtOutFlag(r1)     // Catch: java.lang.Exception -> L46
            goto L57
        L46:
            r5 = move-exception
            org.apache.cordova.CallbackContext r4 = r4.callbackContext
            org.apache.cordova.PluginResult r6 = new org.apache.cordova.PluginResult
            org.apache.cordova.PluginResult$Status r7 = org.apache.cordova.PluginResult.Status.ERROR
            java.lang.String r5 = r5.getMessage()
            r6.<init>(r7, r5)
            r4.sendPluginResult(r6)
        L57:
            return
        L58:
            org.apache.cordova.CallbackContext r4 = r4.callbackContext
            org.apache.cordova.PluginResult r5 = new org.apache.cordova.PluginResult
            org.apache.cordova.PluginResult$Status r6 = org.apache.cordova.PluginResult.Status.OK
            r5.<init>(r6)
            r4.sendPluginResult(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cordova.plugins.qcc.GAIA_QCC_Plugin.onWriteConfigParam4SetTranslationModeRt(cordova.plugins.qcc.GaiaLibrary.gaia.ActionCallbackContext, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult setPowerOn(JSONArray jSONArray, CallbackContext callbackContext) {
        return new PluginResult(PluginResult.Status.OK);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult readI2C(JSONArray jSONArray, CallbackContext callbackContext) {
        return new PluginResult(PluginResult.Status.OK);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PluginResult writeI2C(JSONArray jSONArray, CallbackContext callbackContext) {
        return new PluginResult(PluginResult.Status.OK);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onBluetoothOn() {
        callback("onBluetoothOn()");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onBluetoothOff() {
        callback("onBluetoothOff()");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDeviceScanned(String str, String str2) {
        callback(String.format("onDeviceScanned({name:\"%s\",uuid:\"%s\"})", str, str2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onConnected(String str, String str2) {
        String format = String.format("onDeviceConnected({name:\"%s\",uuid:\"%s\"})", str, str2);
        Log.i(this.TAG, format);
        callback(format);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDisconnected(String str, String str2) {
        callback(String.format("onDeviceDisconnected({name:\"%s\",uuid:\"%s\"})", str, str2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDisconnected() {
        callback(String.format("onDeviceDisconnected({})", new Object[0]));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onGestureChanged(String str, int i) {
        callback(String.format("onGestureChanged({uuid:\"%s\",gestureType:%d})", str, Integer.valueOf(i)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onGAMDataAvailable(String str, long j, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
        callback(String.format("onGAMDataAvailable({uuid:\"%s\",timestampMS:%d,gyroX:%d,gyroY:%d,gyroZ:%d,acceX:%d,acceY:%d,acceZ:%d,magnX:%d,magnY:%d,magnZ:%d,vad:%d})", str, Long.valueOf(j), Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5), Integer.valueOf(i6), Integer.valueOf(i7), Integer.valueOf(i8), Integer.valueOf(i9), Integer.valueOf(i10)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onStepCountAvailable(String str, long j, long j2) {
        callback(String.format("onStepCountAvailable({uuid:\"%s\",timestampMS:%d,stepCount:%d})", str, Long.valueOf(j), Long.valueOf(j2)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onBatteryValueAvailable(String str, int i, int i2) {
        callback(String.format("onBatteryValueAvailable({uuid:\"%s\",mV:%d,chargingState:%d})", str, Integer.valueOf(i), Integer.valueOf(i2)));
    }

    private void callback(final String str) {
        this.cordova.getActivity().runOnUiThread(new Runnable() { // from class: cordova.plugins.qcc.GAIA_QCC_Plugin.5
            @Override // java.lang.Runnable
            public void run() {
                GAIA_QCC_Plugin.this.webView.sendJavascript("cordova.plugins.GAIA_QCC_Plugin.Events." + str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void on247StepCountAvailable(String str, int i, int i2) {
        callback(String.format("on247StepCountAvailable({uuid:\"%s\",startTime:%d,stepCount:%d})", str, Integer.valueOf(i + ((int) this.refDate)), Integer.valueOf(i2)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void on247PostureAvailable(String str, int i, int[] iArr, int[] iArr2) {
        callback(String.format("on247PostureAvailable({uuid:\"%s\",startTime:%d,tiltUp0_5:%d,tiltUp5_10:%d,tiltUp10_15:%d,tiltUp15_20:%d,tiltUp20_25:%d,tiltUp25_30:%d,tiltUp30_:%d,tiltDn0_5:%d,tiltDn5_10:%d,tiltDn10_15:%d,tiltDn15_20:%d,tiltDn20_25:%d,tiltDn25_30:%d,tiltDn30_:%d})", str, Integer.valueOf(i + ((int) this.refDate)), Integer.valueOf(iArr[0]), Integer.valueOf(iArr[1]), Integer.valueOf(iArr[2]), Integer.valueOf(iArr[3]), Integer.valueOf(iArr[4]), Integer.valueOf(iArr[5]), Integer.valueOf(iArr[6]), Integer.valueOf(iArr2[0]), Integer.valueOf(iArr2[1]), Integer.valueOf(iArr2[2]), Integer.valueOf(iArr2[3]), Integer.valueOf(iArr2[4]), Integer.valueOf(iArr2[5]), Integer.valueOf(iArr2[6])));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void on247PostureAvailable(String str, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15) {
        callback(String.format("on247PostureAvailable2({uuid:\"%s\",startTime:%d,tiltUp0_:%d,tiltDn0_5:%d,tiltDn5_10:%d,tiltDn10_15:%d,tiltDn15_20:%d,tiltDn20_25:%d,tiltDn25_30:%d,tiltDn30_35:%d,tiltDn35_40:%d,tiltDn40_45:%d,tiltDn45_50:%d,tiltDn50_55:%d,tiltDn55_60:%d,tiltDn60_:%d})", str, Integer.valueOf(((int) this.refDate) + i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5), Integer.valueOf(i6), Integer.valueOf(i7), Integer.valueOf(i8), Integer.valueOf(i9), Integer.valueOf(i10), Integer.valueOf(i11), Integer.valueOf(i12), Integer.valueOf(i13), Integer.valueOf(i14), Integer.valueOf(i15)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onUpgradeProgressAvailable(String str, double d) {
        callback(String.format("onUpgradeProgressAvailable({uuid:\"%s\",progress:%f,})", str, Double.valueOf(d)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onUpgradeStateChange(String str, int i) {
        callback(String.format("onUpgradeStateChange({uuid:\"%s\",stateCode:%d,})", str, Integer.valueOf(i)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onUpgradeErrorOccur(String str, int i) {
        callback(String.format("onUpgradeErrorOccur({uuid:\"%s\",errorCode:%d,})", str, Integer.valueOf(i)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onTouchUpdateStartCallback(String str) {
        callback(String.format("onTouchOtaUpdateStart({uuid:\"%s\",})", str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onTouchUpdateProgressCallback(String str, int i) {
        callback(String.format("onTouchOtaUpdateProgress({uuid: \"%s\",progress:%d,})", str, Integer.valueOf(i)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onTouchUpdateAbortCallback(String str) {
        callback(String.format("onTouchOtaUpdateAbort({uuid:\"%s\",})", str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onTouchVerifyProgressCallback(String str, int i) {
        callback(String.format("onTouchOtaVerifyProgress({uuid:\"%s\",progress:%d,})", str, Integer.valueOf(i)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onTouchVerifyAbortCallback(String str) {
        callback(String.format("onTouchOtaVerifyAbort({uuid:\"%s\",})", str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onTouchVerifyCompleteCallback(String str) {
        callback(String.format("onTouchOtaVerifyComplete({uuid:\"%s\",})", str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAvEventTitle(String str, String str2) {
        callback(String.format("onAvEventTitle({uuid:\"%s\",title:\"%s\",})", str, str2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAvEventArtist(String str, String str2) {
        callback(String.format("onAvEventArtist({uuid:\"%s\",artist:\"%s\",})", str, str2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAvEventTotalPlayTime(String str, int i) {
        callback(String.format("onAvEventTotalPlayTime({uuid:\"%s\",totalTime:%d,})", str, Integer.valueOf(i)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAvEventCurrentPlayTime(String str, int i) {
        callback(String.format("onAvEventCurrentPlayTime({uuid:\"%s\",currentTime:%d,})", str, Integer.valueOf(i)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAvEventPlayState(String str, int i) {
        callback(String.format("onAvEventPlayState({uuid:\"%s\",state:%d,})", str, Integer.valueOf(i)));
    }

    private PluginResult isVaild(JSONArray jSONArray, int i) {
        try {
            if (this.mCurrentConnectedDevice == null) {
                return new PluginResult(PluginResult.Status.ERROR, "The device is not connected");
            }
            if (jSONArray.length() < i) {
                return new PluginResult(PluginResult.Status.ERROR, "Wrong number of data argument");
            }
            if (!jSONArray.getString(0).equals(macAddressToUUID(this.mCurrentConnectedDevice.getAddress()))) {
                return new PluginResult(PluginResult.Status.ERROR, "UUID does not match the connected device");
            }
            return new PluginResult(PluginResult.Status.OK);
        } catch (Exception e) {
            return new PluginResult(PluginResult.Status.ERROR, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class CalibrationData {
        public int accelShiftX;
        public int accelShiftY;
        public int accelShiftZ;
        public boolean gyroAccelDataOk;
        public boolean gyroAccelDataRx;
        public int gyroShiftX;
        public int gyroShiftY;
        public int gyroShiftZ;
        public int magnetBiasX;
        public int magnetBiasY;
        public int magnetBiasZ;
        public boolean magnetDataOk;
        public boolean magnetDataRx;
        public int magnetMaxX;
        public int magnetMaxY;
        public int magnetMaxZ;
        public int magnetMinX;
        public int magnetMinY;
        public int magnetMinZ;
        public int magnetScaleX;
        public int magnetScaleY;
        public int magnetScaleZ;

        private CalibrationData() {
        }

        public void reset() {
            this.magnetDataRx = false;
            this.gyroAccelDataRx = false;
            this.magnetDataOk = false;
            this.gyroAccelDataOk = false;
        }
    }
}
