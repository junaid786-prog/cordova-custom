package cordova.plugins.qcc.GaiaLibrary;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Message;
import android.util.Log;
import cordova.plugins.qcc.BTLibrary.OnBluetoothConnectionListener;
import cordova.plugins.qcc.BTLibrary.SolosBluetoothConnector;
import cordova.plugins.qcc.BTLibrary.models.DSPConfig;
import cordova.plugins.qcc.BTLibrary.models.SensorReport;
import cordova.plugins.qcc.GaiaLibrary.gaia.CommandSpecification;
import cordova.plugins.qcc.GaiaLibrary.gaia.CustomEqualizerGaiaManager;
import cordova.plugins.qcc.GaiaLibrary.gaia.EqualizerGaiaManager;
import cordova.plugins.qcc.GaiaLibrary.gaia.GaiaManager;
import cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener;
import cordova.plugins.qcc.GaiaLibrary.gaia.SolosGaiaPacketManager;
import cordova.plugins.qcc.GaiaLibrary.gaia.TouchFirmware;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class SolosGaiaController implements OnGaiaMessageReceivedListener {
    private static final byte paramCategory = 3;

    /* renamed from: sr */
    private static final SensorReport f298sr = new SensorReport();
    private SolosBluetoothConnector btConnector;
    private OnGaiaUpgradeEventListener gaiaUpgradeEventListener;
    private OnBluetoothConnectionListener mBtConnectionListener;
    private OnSolosGaiaCommandResultListener mCommandResultListener;
    private BluetoothDevice mConnectedDevice;
    private Context mContext;
    private CustomEqualizerGaiaManager mCustomEqualizerGaiaManager;
    private EqualizerGaiaManager mEqualizerGaiaManager;
    private SolosFirmwareUpdater mFirmwareSoftwareUpdater;
    private SolosGaiaPacketManager mPacketManager;
    private TouchFirmware.TouchFirmwareListener mTouchFwListener;
    protected ArrayList<GaiaManager> mManagers = new ArrayList<>();
    private DSPConfig dspc = new DSPConfig();
    private String TAG = "SolosGaiaController";
    private BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: cordova.plugins.qcc.GaiaLibrary.SolosGaiaController.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE);
                if (intExtra == 10) {
                    SolosGaiaController.this.mBtConnectionListener.onBluetoothDeviceOff();
                } else {
                    if (intExtra != 12) {
                        return;
                    }
                    SolosGaiaController.this.mBtConnectionListener.onBluetoothDeviceOn();
                }
            }
        }
    };

    public SolosGaiaController(Context context) {
        SensorReport sensorReport = f298sr;
        sensorReport.setAcceleration(true);
        sensorReport.setGyro(true);
        sensorReport.setMagnet(true);
        sensorReport.setTouch(true);
        sensorReport.setProximity(true);
        sensorReport.setSingleTap(true);
        sensorReport.setDoubleTap(true);
        sensorReport.setButton(true);
        this.mContext = context;
        this.mContext.registerReceiver(this.mReceiver, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
    }

    public void setOnBluetoothConnectionListener(OnBluetoothConnectionListener onBluetoothConnectionListener) {
        this.mBtConnectionListener = onBluetoothConnectionListener;
    }

    public void setOnSolosGaiaCommandResultListener(OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener) {
        this.mCommandResultListener = onSolosGaiaCommandResultListener;
    }

    public void setOnGaiaUpgradeEventListener(OnGaiaUpgradeEventListener onGaiaUpgradeEventListener) {
        this.gaiaUpgradeEventListener = onGaiaUpgradeEventListener;
    }

    public void setTouchFirmwareListener(TouchFirmware.TouchFirmwareListener touchFirmwareListener) {
        this.mTouchFwListener = touchFirmwareListener;
    }

    public void setBtConnector(SolosBluetoothConnector solosBluetoothConnector) {
        this.btConnector = solosBluetoothConnector;
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.OnGaiaMessageReceivedListener
    public void handleMessageFromService(Message message) {
        int i = message.what;
        if (i == 0) {
            int intValue = ((Integer) message.obj).intValue();
            String str = intValue == 2 ? "CONNECTED" : intValue == 1 ? "CONNECTING" : intValue == 3 ? "DISCONNECTING" : intValue == 0 ? "DISCONNECTED" : "UNKNOWN";
            if (str.equals("DISCONNECTED")) {
                this.mBtConnectionListener.onDisconnectedMessage();
                this.mBtConnectionListener.onDeviceDisconnected();
            } else {
                str.equals("CONNECTED");
            }
            Log.d(this.TAG, "Handle a message from BLE service: CONNECTION_STATE_HAS_CHANGED: ".concat(str));
            return;
        }
        if (i == 1) {
            int intValue2 = ((Integer) message.obj).intValue();
            Log.d(this.TAG, "Handle a message from BLE service: DEVICE_BOND_STATE_HAS_CHANGED: ".concat(intValue2 == 12 ? "BONDED" : intValue2 == 11 ? "BONDING" : "BOND NONE"));
            return;
        }
        if (i == 2) {
            Log.d(this.TAG, "Handle a message from BLE service: GATT_SUPPORT");
            return;
        }
        if (i == 3) {
            byte[] bArr = (byte[]) message.obj;
            boolean z = false;
            for (int i2 = 0; i2 < this.mManagers.size() && !z; i2++) {
                z = this.mManagers.get(i2).onReceiveGAIAPacket(bArr);
            }
            if (z) {
                return;
            }
            Log.d(this.TAG, "the packet cannot be handle by any manager");
            return;
        }
        if (i == 4) {
            Log.d(this.TAG, "Handle a message from BLE service: GAIA_READY");
            SolosFirmwareUpdater solosFirmwareUpdater = new SolosFirmwareUpdater();
            this.mFirmwareSoftwareUpdater = solosFirmwareUpdater;
            solosFirmwareUpdater.setBluetoothService(this.btConnector.mService);
            this.mFirmwareSoftwareUpdater.setOnGaiaUpgradeEventListener(this.gaiaUpgradeEventListener);
            this.mBtConnectionListener.onDeviceSuccessConnected(this.mConnectedDevice);
            return;
        }
        if (i == 6) {
            int i3 = message.arg1;
            Object obj = message.obj;
            SolosFirmwareUpdater solosFirmwareUpdater2 = this.mFirmwareSoftwareUpdater;
            if (solosFirmwareUpdater2 != null) {
                solosFirmwareUpdater2.onReceiveGattMessage(i3, obj);
            }
            Log.d(this.TAG, "Handle a message from BLE service: GATT_MESSAGE");
            return;
        }
        if (i == 7) {
            int i4 = message.arg1;
            Object obj2 = message.obj;
            SolosFirmwareUpdater solosFirmwareUpdater3 = this.mFirmwareSoftwareUpdater;
            if (solosFirmwareUpdater3 != null) {
                solosFirmwareUpdater3.onReceiveUpgradeMessage(i4, obj2);
                return;
            }
            return;
        }
        Log.d(this.TAG, "Handle a message from BLE service: UNKNOWN MESSAGE: " + message.what);
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.OnGaiaMessageReceivedListener
    public void onServiceConnected(int i) {
        this.mPacketManager = new SolosGaiaPacketManager(this.mCommandResultListener, i);
        this.mEqualizerGaiaManager = new EqualizerGaiaManager(this.mCommandResultListener, i);
        this.mCustomEqualizerGaiaManager = new CustomEqualizerGaiaManager(this.mCommandResultListener, i);
        this.mEqualizerGaiaManager.setActivationState(3, true);
        this.mCustomEqualizerGaiaManager.getPreset();
        this.mManagers.add(this.mPacketManager);
        this.mManagers.add(this.mEqualizerGaiaManager);
        this.mManagers.add(this.mCustomEqualizerGaiaManager);
        this.mPacketManager.touchFirmwareAddListener(this.mTouchFwListener);
        Log.d(this.TAG, "service is connected");
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.OnGaiaMessageReceivedListener
    public void onServiceDisconnected() {
        Log.d(this.TAG, "service is disconnected");
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.OnGaiaMessageReceivedListener
    public void onDeviceConnected(BluetoothDevice bluetoothDevice) {
        this.mConnectedDevice = bluetoothDevice;
    }

    public void enableGestureEvent(boolean z) {
        SensorReport sensorReport = f298sr;
        sensorReport.setTouch(z);
        sensorReport.setSingleTap(z);
        sensorReport.setDoubleTap(z);
        sensorReport.setButton(z);
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.setSensorsReportEventEnable(sensorReport);
        }
    }

    public void onReadFWVersion() {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.readFWVersion();
        }
    }

    public void onReadTouchFWVersion() {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.touchFirmwareReadVersion();
        }
    }

    public void enableUart(boolean z) {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.EnableUART(z);
        }
    }

    public void onDeviceDisconnected() {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.onDeviceDisconnected();
        }
    }

    public void factoryReset() {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.factoryReset();
        }
    }

    public void getSerialNum() {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.readConfigParam(2, (byte) 5, 0);
        }
    }

    public void readSensorStatus() {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.readConfigParam(1, (byte) 3, 0);
        }
    }

    public void setSensorReport(SensorReport sensorReport) {
        f298sr.setSensorReport(sensorReport);
    }

    public void enableProximity(boolean z) {
        SensorReport sensorReport = f298sr;
        sensorReport.setProximity(z);
        int packedData = sensorReport.getPackedData();
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.writeConfigParam(1, (byte) 3, (byte) 0, packedData);
        }
    }

    public void readProximity() {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.onReadProximity();
        }
    }

    public void setASRMode(byte b) {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.setASRMode(b);
        }
    }

    public void setOrientationDetectionEnable(boolean z) {
        SensorReport sensorReport = f298sr;
        sensorReport.setOrientationDetection(z);
        int packedData = sensorReport.getPackedData();
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.writeConfigParam(1, (byte) 3, (byte) 0, packedData);
        }
    }

    public boolean getOrientationDetectionEnable() {
        return f298sr.isOrientationDetectionAvail();
    }

    public void enableGAMSensors(boolean z) {
        SensorReport sensorReport = f298sr;
        sensorReport.setAcceleration(z);
        sensorReport.setGyro(z);
        sensorReport.setMagnet(z);
        sensorReport.setVad(z);
        int packedData = sensorReport.getPackedData();
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.writeConfigParam(1, (byte) 3, (byte) 0, packedData);
        }
    }

    public void enableGAMEvents(boolean z, boolean z2, boolean z3, boolean z4) {
        SensorReport sensorReport = f298sr;
        sensorReport.setAcceleration(z);
        sensorReport.setGyro(z2);
        sensorReport.setMagnet(z3);
        sensorReport.setVad(z4);
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.setSensorsReportEventEnable(sensorReport);
        }
    }

    public void setGAMEventInterval(int i) {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.setSensorsReportInterval(i);
        }
    }

    public void resetStepCountSensor() {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.sensorReset();
        }
    }

    public void enableStepCountSensor(boolean z) {
        SensorReport sensorReport = f298sr;
        sensorReport.setStepCount(z);
        int packedData = sensorReport.getPackedData();
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.writeConfigParam(1, (byte) 3, (byte) 0, packedData);
        }
    }

    public void enableStepCountEvent(boolean z) {
        SensorReport sensorReport = f298sr;
        sensorReport.setStepCount(z);
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.setSensorsReportEventEnable(sensorReport);
        }
    }

    public void setLanguage(byte b) {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.setVoicePromptLang(b);
        }
    }

    public void readLanguage() {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.readVoicePromptLang();
        }
    }

    public void setTapSensitivity(int i) {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.writeConfigParam(1, (byte) 3, (byte) 2, i);
        }
    }

    public void getTapSensitivity() {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.readConfigParam(1, (byte) 3, 2);
        }
    }

    public void readBatteryLevel() {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.readBatteryLevel();
        }
    }

    public void getSensorsReportEventStatus() {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.getSensorsReportEventStatus();
        }
    }

    public void getCalibrationData() {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.readDSPConfigParam(CommandSpecification.DSP_REG_SENSOR_CALIBRATION_MAGNET, 6);
            this.mPacketManager.readDSPConfigParam(CommandSpecification.DSP_REG_SENSOR_CALIBRATION_GA, 12);
        }
    }

    public void setMagnetCalibrationData(int i, int i2, int i3, int i4, int i5, int i6) {
        if (this.mPacketManager != null) {
            this.dspc.magnetMaxX = i;
            this.dspc.magnetMinX = i2;
            this.dspc.magnetMaxY = i3;
            this.dspc.magnetMinY = i4;
            this.dspc.magnetMaxZ = i5;
            this.dspc.magnetMinZ = i6;
            this.mPacketManager.writeDSPConfigParam(CommandSpecification.DSP_REG_SENSOR_CALIBRATION_MAGNET, 6, this.dspc);
        }
    }

    public void setGyroAccelMagnetCalibrationData(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12) {
        if (this.mPacketManager != null) {
            this.dspc.gyroShiftX = i;
            this.dspc.gyroShiftY = i2;
            this.dspc.gyroShiftZ = i3;
            this.dspc.accelShiftX = i4;
            this.dspc.accelShiftY = i5;
            this.dspc.accelShiftZ = i6;
            this.dspc.magnetShiftX = i7;
            this.dspc.magnetShiftY = i8;
            this.dspc.magnetShiftZ = i9;
            this.dspc.magnetScaleX = i10;
            this.dspc.magnetScaleY = i11;
            this.dspc.magnetScaleZ = i12;
            this.mPacketManager.writeDSPConfigParam(CommandSpecification.DSP_REG_SENSOR_CALIBRATION_GA, 12, this.dspc);
        }
    }

    public void readAutoPowerOffTimeout() {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.readConfigParam(1, (byte) 2, 0);
        }
    }

    public void writeAutoPowerOffTimeout(int i) {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.writeConfigParam(1, (byte) 2, (byte) 0, i);
        }
    }

    public void readAutoPowerOffMode() {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.readConfigParam(1, (byte) 2, 1);
        }
    }

    public void writeAutoPowerOffMode(boolean z) {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.writeConfigParam(1, (byte) 2, (byte) 1, z ? 1 : 0);
        }
    }

    public void readSystemConfig() {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.readConfigParam(1, (byte) 2, 5);
        }
    }

    public void writeSystemConfig(int i) {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.writeConfigParam(1, (byte) 2, (byte) 5, i);
        }
    }

    public void readOnHeadVoicePromptVolume() {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.readConfigParam(1, (byte) 2, 3);
        }
    }

    public void writeOnHeadVoicePromptVolume(int i) {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.writeConfigParam(1, (byte) 2, (byte) 3, i);
        }
    }

    public void readOffHeadVoicePromptVolume() {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.readConfigParam(1, (byte) 2, 4);
        }
    }

    public void writeOffHeadVoicePromptVolume(int i) {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.writeConfigParam(1, (byte) 2, (byte) 4, i);
        }
    }

    public void readLostLinkAlertDuration() {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.readConfigParam(1, (byte) 2, 6);
        }
    }

    public void writeLostLinkAlertDuration(int i) {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.writeConfigParam(1, (byte) 2, (byte) 6, i);
        }
    }

    public void setUserEqualizerOn(boolean z) {
        if (this.mCustomEqualizerGaiaManager != null) {
            this.mEqualizerGaiaManager.setActivationState(3, z);
            if (z) {
                this.mEqualizerGaiaManager.setPreset(1);
            }
        }
    }

    public void getUserEqualizerOn() {
        if (this.mCustomEqualizerGaiaManager != null) {
            this.mEqualizerGaiaManager.getActivationState(3);
        }
    }

    public void getMusicEqualizerMasterGain() {
        CustomEqualizerGaiaManager customEqualizerGaiaManager = this.mCustomEqualizerGaiaManager;
        if (customEqualizerGaiaManager != null) {
            customEqualizerGaiaManager.getEQParameter(0, 1);
        }
    }

    public void setMusicEqualizerMasterGain(int i) {
        CustomEqualizerGaiaManager customEqualizerGaiaManager = this.mCustomEqualizerGaiaManager;
        if (customEqualizerGaiaManager != null) {
            customEqualizerGaiaManager.setEQParameter(0, 1, i);
        }
    }

    public void getMusicEqualizerGain(int i) {
        CustomEqualizerGaiaManager customEqualizerGaiaManager = this.mCustomEqualizerGaiaManager;
        if (customEqualizerGaiaManager != null) {
            customEqualizerGaiaManager.getEQParameter(i, 2);
        }
    }

    public void setMusicEqualizerGain(int i, int i2) {
        CustomEqualizerGaiaManager customEqualizerGaiaManager = this.mCustomEqualizerGaiaManager;
        if (customEqualizerGaiaManager != null) {
            customEqualizerGaiaManager.setEQParameter(i, 2, i2);
        }
    }

    public void getVoiceEqualizerGain() {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.readDSPConfigParam(CommandSpecification.DSP_REG_WHISPER_DOWNLINK, 15);
        }
    }

    public void setVoiceEqualizerGain(DSPConfig dSPConfig) {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.writeDSPConfigParam(CommandSpecification.DSP_REG_WHISPER_DOWNLINK, 15, dSPConfig);
        }
    }

    public void getVoiceProEqualizerGain() {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.readDSPConfigParam(CommandSpecification.DSP_REG_WHISPER_EQ, 11);
        }
    }

    public void setVoiceProEqualizerGain(DSPConfig dSPConfig) {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.writeDSPConfigParam(CommandSpecification.DSP_REG_WHISPER_EQ, 11, dSPConfig);
        }
    }

    public void setWhisperEqRTOutFlag(short s) {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.setWhisperEqRTOutFlag(s);
        }
    }

    public void setWhisperEqRTBandGain(int i, short s) {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.setWhisperEqRTBandGain(i, s);
        }
    }

    public void setWhisperDownlinkRTBandGain(int i, short s) {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.setWhisperDownlinkRTBandGain(i, s);
        }
    }

    public void setWhisperDownlinkRTOutFlag(short s) {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.setWhisperDownlinkRTOutFlag(s);
        }
    }

    public void setWhisperDownlinkRTEnable(short s) {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.setWhisperDownlinkRTEnable(s);
        }
    }

    public void getMusicEqualizerFrequency(int i) {
        CustomEqualizerGaiaManager customEqualizerGaiaManager = this.mCustomEqualizerGaiaManager;
        if (customEqualizerGaiaManager != null) {
            customEqualizerGaiaManager.getEQParameter(i, 1);
        }
    }

    public void setMusicEqualizerFrequency(int i, int i2) {
        CustomEqualizerGaiaManager customEqualizerGaiaManager = this.mCustomEqualizerGaiaManager;
        if (customEqualizerGaiaManager != null) {
            customEqualizerGaiaManager.setEQParameter(i, 1, i2);
        }
    }

    public void getMusicEqualizerQFactor(int i) {
        CustomEqualizerGaiaManager customEqualizerGaiaManager = this.mCustomEqualizerGaiaManager;
        if (customEqualizerGaiaManager != null) {
            customEqualizerGaiaManager.getEQParameter(i, 3);
        }
    }

    public void setMusicEqualizerQFactor(int i, int i2) {
        CustomEqualizerGaiaManager customEqualizerGaiaManager = this.mCustomEqualizerGaiaManager;
        if (customEqualizerGaiaManager != null) {
            customEqualizerGaiaManager.setEQParameter(i, 3, i2);
        }
    }

    public void getMusicEqualizerFilterType(int i) {
        CustomEqualizerGaiaManager customEqualizerGaiaManager = this.mCustomEqualizerGaiaManager;
        if (customEqualizerGaiaManager != null) {
            customEqualizerGaiaManager.getEQParameter(i, 0);
        }
    }

    public void setMusicEqualizerFilterType(int i, int i2) {
        CustomEqualizerGaiaManager customEqualizerGaiaManager = this.mCustomEqualizerGaiaManager;
        if (customEqualizerGaiaManager != null) {
            customEqualizerGaiaManager.setEQParameter(i, 0, i2);
        }
    }

    public void setWhisperFeature(DSPConfig dSPConfig) {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.writeDSPConfigParam(CommandSpecification.DSP_REG_WHISPER_FEATURE, 15, dSPConfig);
        }
    }

    public void getWhisperFeature() {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.readDSPConfigParam(CommandSpecification.DSP_REG_WHISPER_FEATURE, 15);
        }
    }

    public void setWhisper(DSPConfig dSPConfig) {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.writeDSPConfigParam(CommandSpecification.DSP_REG_WHISPER, 15, dSPConfig);
        }
    }

    public void getWhisper() {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.readDSPConfigParam(CommandSpecification.DSP_REG_WHISPER, 15);
        }
    }

    public void resetDevice() {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.resetDevice();
        }
    }

    public void setRTC(int i) {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.setRTC(i);
        }
    }

    public void set247Monitor(boolean z, boolean z2) {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.set247Monitor(z, z2);
        }
    }

    public void get247Monitor() {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.get247Monitor();
        }
    }

    public void set247Reporting(boolean z, boolean z2, boolean z3, int i) {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.set247Reporting(z, z2, z3, i);
        }
    }

    public void get247Reporting() {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.get247Reporting();
        }
    }

    public void get247StepCount(int i, int i2) {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.get247StepCount(i, (short) i2);
        }
    }

    public void delete247StepCount(int i, int i2) {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.delete247StepCount(i, (short) i2);
        }
    }

    public void set247PostureConfig(int i, int i2, int i3, int i4) {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.set247PostureConfig(i, i2, i3, i4);
        }
    }

    public void get247PostureConfig() {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.get247PostureConfig();
        }
    }

    public void get247Posture(int i, int i2) {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.get247Posture(i, (short) i2);
        }
    }

    public void get247Posture2(int i, int i2) {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.get247Posture2(i, (short) i2);
        }
    }

    public void delete247Posture(int i, int i2) {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.delete247Posture(i, (short) i2);
        }
    }

    public void startFirmwareUpgrade(File file) {
        SolosFirmwareUpdater solosFirmwareUpdater = this.mFirmwareSoftwareUpdater;
        if (solosFirmwareUpdater != null) {
            solosFirmwareUpdater.startFirmwareUpgrade(file);
        }
    }

    public void startTouchFirmwareUpdate(File file) {
        if (this.mPacketManager != null) {
            try {
                this.mPacketManager.touchFirmwareStartOtaUpdate(Files.readAllBytes(file.toPath()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void switchFirmware() {
        SolosFirmwareUpdater solosFirmwareUpdater = this.mFirmwareSoftwareUpdater;
        if (solosFirmwareUpdater != null) {
            solosFirmwareUpdater.switchFirmware();
        }
    }

    public void abortFirmwareUpgrade() {
        SolosFirmwareUpdater solosFirmwareUpdater = this.mFirmwareSoftwareUpdater;
        if (solosFirmwareUpdater != null) {
            solosFirmwareUpdater.abortFirmwareUpgrade();
        }
    }

    public void readLostLinkAlertVolume() {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.readConfigParam(1, (byte) 2, 7);
        }
    }

    public void writeLostLinkAlertVolume(int i) {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.writeConfigParam(1, (byte) 2, (byte) 7, i);
        }
    }

    public void readPostureReminderVolume() {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.readConfigParam(1, (byte) 2, 8);
        }
    }

    public void writePostureReminderVolume(int i) {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.writeConfigParam(1, (byte) 2, (byte) 8, i);
        }
    }

    public void setInputConfig(int i, int i2, int i3, boolean z) {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.writeConfigParam(!z ? 1 : 0, (byte) 4, (byte) i, i2 | (i3 << 8));
        }
    }

    public void getInputConfig(int i, boolean z) {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.readConfigParam(!z ? 1 : 0, (byte) 4, (byte) i);
        }
    }

    public void setVolatileInputConfigTimeout(int i) {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.setVolatileConfigTimeout((byte) 4, (byte) i);
        }
    }

    public void setAvEvents(boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.setAvEvents(z, z2, z3, z4, z5);
        }
    }

    public void getAvEvents() {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.getAvEvents();
        }
    }

    public void getAvInfo() {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.getAvInfo();
        }
    }

    public void setAvControl(byte b) {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.setAvControl(b);
        }
    }

    public void getWhisperDataRtOutGain() {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.getWhisperDataRtOutGain();
        }
    }

    public void getWhisperDataRtOutFlag() {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.getWhisperDataRtOutFlag();
        }
    }

    public void setWhisperDataRtOutGain(long j) {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.setWhisperDataRtOutGain(j);
        }
    }

    public void setWhisperDataRtOutFlag(long j) {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.setWhisperDataRtOutFlag(j);
        }
    }

    public void rebootDevice(int i) {
        SolosGaiaPacketManager solosGaiaPacketManager = this.mPacketManager;
        if (solosGaiaPacketManager != null) {
            solosGaiaPacketManager.rebootDevice(i);
        }
    }
}
