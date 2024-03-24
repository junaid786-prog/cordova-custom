package cordova.plugins.qcc;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import cordova.plugins.qcc.BTLibrary.OnBluetoothConnectionListener;
import cordova.plugins.qcc.BTLibrary.SolosBluetoothConnector;
import cordova.plugins.qcc.BTLibrary.models.DSPConfig;
import cordova.plugins.qcc.BTLibrary.models.SensorReport;
import cordova.plugins.qcc.BTLibrary.models.equalizer.parameters.Filter;
import cordova.plugins.qcc.GaiaLibrary.OnGaiaUpgradeEventListener;
import cordova.plugins.qcc.GaiaLibrary.SolosGaiaController;
import cordova.plugins.qcc.GaiaLibrary.gaia.CommandSpecification;
import cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener;
import cordova.plugins.qcc.GaiaLibrary.gaia.TouchFirmware;
import java.io.File;

/* loaded from: classes2.dex */
public class SolosManager implements OnSolosGaiaCommandResultListener {
    private static SolosManager self;
    private SolosBluetoothConnector mBtConnector;
    private OnSolosGaiaCommandResultListener mCommandResultListener;
    private SolosGaiaController mGaiaController;

    public void getWhisperRTBand() {
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onSetLED(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus) {
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onWriteUART(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus) {
    }

    public static SolosManager getInstance(Context context) {
        if (self == null) {
            self = new SolosManager(context);
        }
        return self;
    }

    private SolosManager() {
    }

    private SolosManager(Context context) {
        this.mBtConnector = new SolosBluetoothConnector(context);
        SolosGaiaController solosGaiaController = new SolosGaiaController(context);
        this.mGaiaController = solosGaiaController;
        this.mBtConnector.setOnGaiaMessageReceivedListener(solosGaiaController);
        this.mGaiaController.setOnSolosGaiaCommandResultListener(this);
        this.mGaiaController.setBtConnector(this.mBtConnector);
    }

    public void startScan() {
        SolosBluetoothConnector solosBluetoothConnector = this.mBtConnector;
        if (solosBluetoothConnector != null) {
            solosBluetoothConnector.startScan();
        }
    }

    public void stopScan() {
        SolosBluetoothConnector solosBluetoothConnector = this.mBtConnector;
        if (solosBluetoothConnector != null) {
            solosBluetoothConnector.stopScan();
        }
    }

    public void connect(String str) {
        SolosBluetoothConnector solosBluetoothConnector = this.mBtConnector;
        if (solosBluetoothConnector != null) {
            solosBluetoothConnector.startConnectByUUID(str);
        }
    }

    public boolean isConnected() {
        SolosBluetoothConnector solosBluetoothConnector = this.mBtConnector;
        return (solosBluetoothConnector == null || solosBluetoothConnector.mService == null || this.mBtConnector.mService.getConnectionState() != 2) ? false : true;
    }

    public BluetoothDevice getConnectedDevice() {
        SolosBluetoothConnector solosBluetoothConnector = this.mBtConnector;
        if (solosBluetoothConnector == null || solosBluetoothConnector.mService == null) {
            return null;
        }
        return this.mBtConnector.mService.getDevice();
    }

    public void disconnect(String str) {
        SolosBluetoothConnector solosBluetoothConnector = this.mBtConnector;
        if (solosBluetoothConnector != null) {
            solosBluetoothConnector.startDisconnectByUUID(str);
        }
    }

    public boolean isBluetoothEnable() {
        SolosBluetoothConnector solosBluetoothConnector = this.mBtConnector;
        if (solosBluetoothConnector != null) {
            return solosBluetoothConnector.isBluetoothEnable();
        }
        return false;
    }

    public void registerBondStateReceiver() {
        SolosBluetoothConnector solosBluetoothConnector = this.mBtConnector;
        if (solosBluetoothConnector != null) {
            solosBluetoothConnector.registerReceiver();
        }
    }

    public void unregisterBondStateReceiver() {
        SolosBluetoothConnector solosBluetoothConnector = this.mBtConnector;
        if (solosBluetoothConnector != null) {
            solosBluetoothConnector.unregisterReceiver();
        }
    }

    public void setOnBluetoothConnectionListener(OnBluetoothConnectionListener onBluetoothConnectionListener) {
        this.mBtConnector.setOnBluetoothConnectionListener(onBluetoothConnectionListener);
        this.mGaiaController.setOnBluetoothConnectionListener(onBluetoothConnectionListener);
    }

    public void setOnSolosGaiaCommandResultListener(OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener) {
        this.mCommandResultListener = onSolosGaiaCommandResultListener;
    }

    public void setOnGaiaUpgradeEventListener(OnGaiaUpgradeEventListener onGaiaUpgradeEventListener) {
        this.mGaiaController.setOnGaiaUpgradeEventListener(onGaiaUpgradeEventListener);
    }

    public void setTouchFirmwareListener(TouchFirmware.TouchFirmwareListener touchFirmwareListener) {
        this.mGaiaController.setTouchFirmwareListener(touchFirmwareListener);
    }

    public void enableGestureEvent(boolean z) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.enableGestureEvent(z);
        }
    }

    public void readSensorStatus() {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.readSensorStatus();
        }
    }

    public void setSensorReport(SensorReport sensorReport) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.setSensorReport(sensorReport);
        }
    }

    public void enableProximity(boolean z) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.enableProximity(z);
        }
    }

    public void readProximity() {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.readProximity();
        }
    }

    public void setASRMode(byte b) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.setASRMode(b);
        }
    }

    public void setOrientationDetectionEnable(boolean z) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.setOrientationDetectionEnable(z);
        }
    }

    public boolean getOrientationDetectionEnable() {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            return solosGaiaController.getOrientationDetectionEnable();
        }
        return false;
    }

    public void enableGAMSensors(boolean z) {
        this.mGaiaController.enableGAMSensors(z);
    }

    public void setGAMEventInterval(int i) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.setGAMEventInterval(i);
        }
    }

    public void enableGAMEvents(boolean z, boolean z2, boolean z3, boolean z4) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.enableGAMEvents(z, z2, z3, z4);
        }
    }

    public void resetStepCountSensor() {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.resetStepCountSensor();
        }
    }

    public void enableStepCountSensor(boolean z) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.enableStepCountSensor(z);
        }
    }

    public void enableStepCountEvent(boolean z) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.enableStepCountEvent(z);
        }
    }

    public void setLanguage(byte b) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.setLanguage(b);
        }
    }

    public void readLanguage() {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.readLanguage();
        }
    }

    public void setTapSensitivity(int i) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.setTapSensitivity(i);
        }
    }

    public void getTapSensitivity() {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.getTapSensitivity();
        }
    }

    public void readFWVersion() {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.onReadFWVersion();
        }
    }

    public void readTouchFWVersion() {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.onReadTouchFWVersion();
        }
    }

    public void enableUart(boolean z) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.enableUart(z);
        }
    }

    public void onDeviceDisconnected() {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.onDeviceDisconnected();
        }
    }

    public void factoryReset() {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.factoryReset();
        }
    }

    public void getSerialNum() {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.getSerialNum();
        }
    }

    public void getSensorsReportEventStatus() {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.getSensorsReportEventStatus();
        }
    }

    public void readBatteryLevel() {
        this.mGaiaController.readBatteryLevel();
    }

    public void getCalibrationData() {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.getCalibrationData();
        }
    }

    public void setMagnetCalibrationData(int i, int i2, int i3, int i4, int i5, int i6) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.setMagnetCalibrationData(i, i2, i3, i4, i5, i6);
        }
    }

    public void setGyroAccelMagnetCalibrationData(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.setGyroAccelMagnetCalibrationData(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12);
        }
    }

    public void readAutoPowerOffTimeout() {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.readAutoPowerOffTimeout();
        }
    }

    public void writeAutoPowerOffTimeout(int i) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.writeAutoPowerOffTimeout(i);
        }
    }

    public void readAutoPowerOffMode() {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.readAutoPowerOffMode();
        }
    }

    public void writeAutoPowerOffMode(boolean z) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.writeAutoPowerOffMode(z);
        }
    }

    public void readSystemConfig() {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.readSystemConfig();
        }
    }

    public void writeSystemConfig(int i) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.writeSystemConfig(i);
        }
    }

    public void readOnHeadVoicePromptVolume() {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.readOnHeadVoicePromptVolume();
        }
    }

    public void writeOnHeadVoicePromptVolume(int i) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.writeOnHeadVoicePromptVolume(i);
        }
    }

    public void readOffHeadVoicePromptVolume() {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.readOffHeadVoicePromptVolume();
        }
    }

    public void writeOffHeadVoicePromptVolume(int i) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.writeOffHeadVoicePromptVolume(i);
        }
    }

    public void readLostLinkAlertDuration() {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.readLostLinkAlertDuration();
        }
    }

    public void writeLostLinkAlertDuration(int i) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.writeLostLinkAlertDuration(i);
        }
    }

    public void getUserEqualizerOn() {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.getUserEqualizerOn();
        }
    }

    public void setUserEqualizerOn(boolean z) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.setUserEqualizerOn(z);
        }
    }

    public void getMusicEqualizerMasterGain() {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.getMusicEqualizerMasterGain();
        }
    }

    public void setMusicEqualizerMasterGain(int i) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.setMusicEqualizerMasterGain(i);
        }
    }

    public void getMusicEqualizerGain(int i) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.getMusicEqualizerGain(i);
        }
    }

    public void setMusicEqualizerGain(int i, int i2) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.setMusicEqualizerGain(i, i2);
        }
    }

    public void getVoiceEqualizerGain() {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.getVoiceEqualizerGain();
        }
    }

    public void setVoiceEqualizerGain(DSPConfig dSPConfig) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.setVoiceEqualizerGain(dSPConfig);
        }
    }

    public void getVoiceProEqualizerGain() {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.getVoiceProEqualizerGain();
        }
    }

    public void setVoiceProEqualizerGain(DSPConfig dSPConfig) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.setVoiceProEqualizerGain(dSPConfig);
        }
    }

    public void setWhisperEqRTOutFlag(short s) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.setWhisperEqRTOutFlag(s);
        }
    }

    public void setWhisperEqRTBandGain(int i, short s) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.setWhisperEqRTBandGain(i, s);
        }
    }

    public void setWhisperDownlinkRTBandGain(int i, short s) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.setWhisperDownlinkRTBandGain(i, s);
        }
    }

    public void setWhisperDownlinkRTOutFlag(short s) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.setWhisperDownlinkRTOutFlag(s);
        }
    }

    public void setWhisperDownlinkRTEnable(short s) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.setWhisperDownlinkRTEnable(s);
        }
    }

    public void getMusicEqualizerFrequency(int i) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.getMusicEqualizerFrequency(i);
        }
    }

    public void setMusicEqualizerFrequency(int i, int i2) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.setMusicEqualizerFrequency(i, i2);
        }
    }

    public void getMusicEqualizerQFactor(int i) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.getMusicEqualizerQFactor(i);
        }
    }

    public void setMusicEqualizerQFactor(int i, int i2) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.setMusicEqualizerQFactor(i, i2);
        }
    }

    public void getMusicEqualizerFilterType(int i) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.getMusicEqualizerFilterType(i);
        }
    }

    public void setMusicEqualizerFilterType(int i, int i2) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.setMusicEqualizerFilterType(i, i2);
        }
    }

    public void setWhisperFeature(DSPConfig dSPConfig) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.setWhisperFeature(dSPConfig);
        }
    }

    public void getWhisperFeature() {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.getWhisperFeature();
        }
    }

    public void setWhisper(DSPConfig dSPConfig) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.setWhisper(dSPConfig);
        }
    }

    public void getWhisper() {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.getWhisper();
        }
    }

    public void resetDevice() {
        this.mGaiaController.resetDevice();
    }

    public void setRTC(int i) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.setRTC(i);
        }
    }

    public void set247Monitor(boolean z, boolean z2) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.set247Monitor(z, z2);
        }
    }

    public void get247Monitor() {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.get247Monitor();
        }
    }

    public void set247Reporting(boolean z, boolean z2, boolean z3, int i) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.set247Reporting(z, z2, z3, i);
        }
    }

    public void get247Reporting() {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.get247Reporting();
        }
    }

    public void get247StepCount(int i, int i2) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.get247StepCount(i, i2);
        }
    }

    public void delete247StepCount(int i, int i2) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.delete247StepCount(i, i2);
        }
    }

    public void set247PostureConfig(int i, int i2, int i3, int i4) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.set247PostureConfig(i, i2, i3, i4);
        }
    }

    public void get247PostureConfig() {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.get247PostureConfig();
        }
    }

    public void get247Posture(int i, int i2) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.get247Posture(i, i2);
        }
    }

    public void get247Posture2(int i, int i2) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.get247Posture2(i, i2);
        }
    }

    public void delete247Posture(int i, int i2) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.delete247Posture(i, i2);
        }
    }

    public void startFirmwareUpgrade(File file) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.startFirmwareUpgrade(file);
        }
    }

    public void switchFirmware() {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.switchFirmware();
        }
    }

    public void abortFirmwareUpgrade() {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.abortFirmwareUpgrade();
        }
    }

    public void startTouchFirmwareUpdate(File file) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.startTouchFirmwareUpdate(file);
        }
    }

    public void readLostLinkAlertVolume() {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.readLostLinkAlertVolume();
        }
    }

    public void writeLostLinkAlertVolume(int i) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.writeLostLinkAlertVolume(i);
        }
    }

    public void readPostureReminderVolume() {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.readPostureReminderVolume();
        }
    }

    public void writePostureReminderVolume(int i) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.writePostureReminderVolume(i);
        }
    }

    public void rebootDevice(int i) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.rebootDevice(i);
        }
    }

    public void setInputConfig(int i, int i2, int i3, boolean z) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.setInputConfig(i, i2, i3, z);
        }
    }

    public void getInputConfig(int i, boolean z) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.getInputConfig(i, z);
        }
    }

    public void setVolatileInputConfigTimeout(int i) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.setVolatileInputConfigTimeout(i);
        }
    }

    public void setAvEvents(boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.setAvEvents(z, z2, z3, z4, z5);
        }
    }

    public void getAvEvents() {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.getAvEvents();
        }
    }

    public void getAvInfo() {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.getAvInfo();
        }
    }

    public void setAvControl(byte b) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.setAvControl(b);
        }
    }

    public void getWhisperDataRtOutGain() {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.getWhisperDataRtOutGain();
        }
    }

    public void getWhisperDataRtOutFlag() {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.getWhisperDataRtOutFlag();
        }
    }

    public void setWhisperDataRtOutGain(long j) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.setWhisperDataRtOutGain(j);
        }
    }

    public void setWhisperDataRtOutFlag(long j) {
        SolosGaiaController solosGaiaController = this.mGaiaController;
        if (solosGaiaController != null) {
            solosGaiaController.setWhisperDataRtOutFlag(j);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public boolean sendGAIAPacket(byte[] bArr) {
        return this.mBtConnector.sendGAIAPacket(bArr);
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onReadFwVersion(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, int i2, int i3, int i4) {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onReadFwVersion(gaiaSolosResponseStatus, i, i2, i3, i4);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onRead9Axis(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, SensorReport sensorReport) {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onRead9Axis(gaiaSolosResponseStatus, sensorReport);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onSetASRMode(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, byte b) {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onSetASRMode(gaiaSolosResponseStatus, b);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onReadDeviceVoicePromptLang(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i) {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onReadDeviceVoicePromptLang(gaiaSolosResponseStatus, i);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onWriteDeviceVoicePromptLang(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus) {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onWriteDeviceVoicePromptLang(gaiaSolosResponseStatus);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onReadProximity(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i) {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onReadProximity(gaiaSolosResponseStatus, i);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onReadSensorsReportEnable(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus) {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onReadSensorsReportEnable(gaiaSolosResponseStatus);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onReadSensorsReportEventStatus(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, SensorReport sensorReport) {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onReadSensorsReportEventStatus(gaiaSolosResponseStatus, sensorReport);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onSetSensorReportInterval(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i) {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onSetSensorReportInterval(gaiaSolosResponseStatus, i);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onReadSensorReportInterval(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, int i2) {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onReadSensorReportInterval(gaiaSolosResponseStatus, i, i2);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onRead9AxisEvent(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, SensorReport sensorReport) {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onRead9AxisEvent(gaiaSolosResponseStatus, i, sensorReport);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onReadStepCountsEvent(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, int i2) {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onReadStepCountsEvent(gaiaSolosResponseStatus, i, i2);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onReadGestureEvent(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, boolean[] zArr, int i2) {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onReadGestureEvent(gaiaSolosResponseStatus, i, zArr, i2);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onReadMotionEvent(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, boolean[] zArr) {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onReadMotionEvent(gaiaSolosResponseStatus, i, zArr);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onFactoryReset(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus) {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onFactoryReset(gaiaSolosResponseStatus);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onSensorReset(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, boolean[] zArr) {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onSensorReset(gaiaSolosResponseStatus, zArr);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onI2CRead(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, int i2, byte[] bArr, boolean z) {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onI2CRead(gaiaSolosResponseStatus, i, i2, bArr, z);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onI2CWrite(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, int i2, boolean z) {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onI2CWrite(gaiaSolosResponseStatus, i, i2, z);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onReadConfigParam(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, int i2, int i3, int i4, SensorReport sensorReport) {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onReadConfigParam(gaiaSolosResponseStatus, i, i2, i3, i4, sensorReport);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onReadConfigParam(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, int i2, int i3, int i4) {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onReadConfigParam(gaiaSolosResponseStatus, i, i2, i3, i4);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onReadConfigParam(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, int i2, int i3, byte[] bArr) {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onReadConfigParam(gaiaSolosResponseStatus, i, i2, i3, bArr);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onWriteConfigParam(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, int i2, int i3) {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onWriteConfigParam(gaiaSolosResponseStatus, i, i2, i3);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onSetVolatileConfigTimeout(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, byte b) {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onSetVolatileConfigTimeout(gaiaSolosResponseStatus, b);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onReadBatteryLevel(int i) {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onReadBatteryLevel(i);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onWriteDSPConfigParam(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, byte[] bArr) {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onWriteDSPConfigParam(gaiaSolosResponseStatus, bArr);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onReadDSPConfigParam(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, byte[] bArr) {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onReadDSPConfigParam(gaiaSolosResponseStatus, bArr);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onGetPreset(int i) {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onGetPreset(i);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onGetControlActivationState(int i, boolean z) {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onGetControlActivationState(i, z);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onControlNotSupported(int i) {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onControlNotSupported(i);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onSetEQParameter() {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onSetEQParameter();
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onSetUserEQControl() {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onSetUserEQControl();
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onControlNotSupported() {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onControlNotSupported();
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onGetMasterGain(int i) {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onGetMasterGain(i);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onGetFrequency(int i, int i2) {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onGetFrequency(i, i2);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onGetGain(int i, int i2) {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onGetGain(i, i2);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onGetFilter(int i, Filter filter) {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onGetFilter(i, filter);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onGetQuality(int i, int i2) {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onGetQuality(i, i2);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onIncorrectState() {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onIncorrectState();
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onIncorrectState(int i) {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onIncorrectState(i);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onResetDevice() {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onResetDevice();
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onUARTEnable(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, boolean z) {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onUARTEnable(gaiaSolosResponseStatus, z);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onSetRTC(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus) {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onSetRTC(gaiaSolosResponseStatus);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onSet247Monitor(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus) {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onSet247Monitor(gaiaSolosResponseStatus);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onGet247Monitor(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, boolean z, boolean z2) {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onGet247Monitor(gaiaSolosResponseStatus, z, z2);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onSet247Reporting(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus) {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onSet247Reporting(gaiaSolosResponseStatus);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onGet247Reporting(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, boolean z, boolean z2, boolean z3, int i) {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onGet247Reporting(gaiaSolosResponseStatus, z, z2, z3, i);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onSet247PostureConfig(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus) {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onSet247PostureConfig(gaiaSolosResponseStatus);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onGet247PostureConfig(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, int i2, int i3, int i4) {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onGet247PostureConfig(gaiaSolosResponseStatus, i, i2, i3, i4);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onGet247StepCount(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, int i2, int i3) {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onGet247StepCount(gaiaSolosResponseStatus, i, i2, i3);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onDelete247StepCount(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, int i2) {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onDelete247StepCount(gaiaSolosResponseStatus, i, i2);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onGet247Posture(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, int i2, int[] iArr, int[] iArr2) {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onGet247Posture(gaiaSolosResponseStatus, i, i2, iArr, iArr2);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onGet247Posture(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16) {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onGet247Posture(gaiaSolosResponseStatus, i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onDelete247Posture(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, int i2) {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onDelete247Posture(gaiaSolosResponseStatus, i, i2);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onReadUARTDataEvent(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, byte[] bArr) {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onReadUARTDataEvent(gaiaSolosResponseStatus, bArr);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onRead247PostureMonitorEvent(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, int[] iArr, int[] iArr2) {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onRead247PostureMonitorEvent(gaiaSolosResponseStatus, i, iArr, iArr2);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onRead247PostureMonitorEvent(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15) {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onRead247PostureMonitorEvent(gaiaSolosResponseStatus, i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onRead247StepCountEvent(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, int i2) {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onRead247StepCountEvent(gaiaSolosResponseStatus, i, i2);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onRebootDevice(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, byte[] bArr) {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onRebootDevice(gaiaSolosResponseStatus, bArr);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onSetAvEvents(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onSetAvEvents(gaiaSolosResponseStatus, z, z2, z3, z4, z5);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onGetAvEvents(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onGetAvEvents(gaiaSolosResponseStatus, z, z2, z3, z4, z5);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onGetAvInfo(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus) {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onGetAvInfo(gaiaSolosResponseStatus);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onReadAvInfoEvents(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, byte b, byte[] bArr) {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onReadAvInfoEvents(gaiaSolosResponseStatus, b, bArr);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener
    public void onSetAvControl(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus) {
        OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener = this.mCommandResultListener;
        if (onSolosGaiaCommandResultListener != null) {
            onSolosGaiaCommandResultListener.onSetAvControl(gaiaSolosResponseStatus);
        }
    }
}
