package cordova.plugins.qcc.GaiaLibrary.gaia;

import cordova.plugins.qcc.BTLibrary.models.SensorReport;
import cordova.plugins.qcc.BTLibrary.models.equalizer.parameters.Filter;
import cordova.plugins.qcc.GaiaLibrary.gaia.CommandSpecification;

/* loaded from: classes2.dex */
public interface OnSolosGaiaCommandResultListener {
    void onControlNotSupported();

    void onControlNotSupported(int i);

    void onDelete247Posture(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, int i2);

    void onDelete247StepCount(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, int i2);

    void onFactoryReset(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus);

    void onGet247Monitor(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, boolean z, boolean z2);

    void onGet247Posture(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16);

    void onGet247Posture(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, int i2, int[] iArr, int[] iArr2);

    void onGet247PostureConfig(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, int i2, int i3, int i4);

    void onGet247Reporting(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, boolean z, boolean z2, boolean z3, int i);

    void onGet247StepCount(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, int i2, int i3);

    void onGetAvEvents(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, boolean z, boolean z2, boolean z3, boolean z4, boolean z5);

    void onGetAvInfo(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus);

    void onGetControlActivationState(int i, boolean z);

    void onGetFilter(int i, Filter filter);

    void onGetFrequency(int i, int i2);

    void onGetGain(int i, int i2);

    void onGetMasterGain(int i);

    void onGetPreset(int i);

    void onGetQuality(int i, int i2);

    void onI2CRead(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, int i2, byte[] bArr, boolean z);

    void onI2CWrite(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, int i2, boolean z);

    void onIncorrectState();

    void onIncorrectState(int i);

    void onRead247PostureMonitorEvent(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15);

    void onRead247PostureMonitorEvent(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, int[] iArr, int[] iArr2);

    void onRead247StepCountEvent(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, int i2);

    void onRead9Axis(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, SensorReport sensorReport);

    void onRead9AxisEvent(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, SensorReport sensorReport);

    void onReadAvInfoEvents(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, byte b, byte[] bArr);

    void onReadBatteryLevel(int i);

    void onReadConfigParam(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, int i2, int i3, int i4);

    void onReadConfigParam(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, int i2, int i3, int i4, SensorReport sensorReport);

    void onReadConfigParam(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, int i2, int i3, byte[] bArr);

    void onReadDSPConfigParam(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, byte[] bArr);

    void onReadDeviceVoicePromptLang(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i);

    void onReadFwVersion(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, int i2, int i3, int i4);

    void onReadGestureEvent(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, boolean[] zArr, int i2);

    void onReadMotionEvent(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, boolean[] zArr);

    void onReadProximity(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i);

    void onReadSensorReportInterval(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, int i2);

    void onReadSensorsReportEnable(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus);

    void onReadSensorsReportEventStatus(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, SensorReport sensorReport);

    void onReadStepCountsEvent(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, int i2);

    void onReadUARTDataEvent(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, byte[] bArr);

    void onRebootDevice(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, byte[] bArr);

    void onResetDevice();

    void onSensorReset(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, boolean[] zArr);

    void onSet247Monitor(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus);

    void onSet247PostureConfig(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus);

    void onSet247Reporting(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus);

    void onSetASRMode(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, byte b);

    void onSetAvControl(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus);

    void onSetAvEvents(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, boolean z, boolean z2, boolean z3, boolean z4, boolean z5);

    void onSetEQParameter();

    void onSetLED(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus);

    void onSetRTC(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus);

    void onSetSensorReportInterval(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i);

    void onSetUserEQControl();

    void onSetVolatileConfigTimeout(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, byte b);

    void onUARTEnable(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, boolean z);

    void onWriteConfigParam(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, int i, int i2, int i3);

    void onWriteDSPConfigParam(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus, byte[] bArr);

    void onWriteDeviceVoicePromptLang(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus);

    void onWriteUART(CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus);

    boolean sendGAIAPacket(byte[] bArr);
}
