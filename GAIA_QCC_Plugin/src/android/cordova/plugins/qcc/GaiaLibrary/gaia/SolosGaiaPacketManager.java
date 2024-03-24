package cordova.plugins.qcc.GaiaLibrary.gaia;

import android.util.Log;
import cordova.plugins.qcc.BTLibrary.Utils;
import cordova.plugins.qcc.BTLibrary.models.DSPConfig;
import cordova.plugins.qcc.BTLibrary.models.SensorReport;
import cordova.plugins.qcc.GaiaLibrary.gaia.CommandSpecification;
import cordova.plugins.qcc.GaiaLibrary.gaia.TouchFirmware;
import cordova.plugins.qcc.GaiaLibrary.gaia.UartProtocolStack;
import cordova.plugins.qcc.GaiaLibrary.gaia.packets.GaiaPacket;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class SolosGaiaPacketManager extends AGaiaManager implements UartProtocolStack.UartWriteImp {
    private OnSolosGaiaCommandResultListener mListener;
    private TouchFirmware mTouchFw;
    private UartProtocolStack mUart;

    public SolosGaiaPacketManager(OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener, int i) {
        super(i);
        this.mListener = onSolosGaiaCommandResultListener;
        this.mUart = new UartProtocolStack(this);
        this.mTouchFw = new TouchFirmware(this.mUart);
    }

    public void touchFirmwareAddListener(TouchFirmware.TouchFirmwareListener touchFirmwareListener) {
        this.mTouchFw.addListener(touchFirmwareListener);
    }

    public void touchFirmwareDelListener(TouchFirmware.TouchFirmwareListener touchFirmwareListener) {
        this.mTouchFw.delListener(touchFirmwareListener);
    }

    public void touchFirmwareStartOtaUpdate(byte[] bArr) {
        this.mTouchFw.storeBinFileData(bArr);
        this.mTouchFw.startOtaUpdate();
    }

    public void touchFirmwareReadVersion() {
        this.mTouchFw.readFirmwareVer();
    }

    private void sendResponse(byte b, byte b2, byte[] bArr) {
        if (b == 22) {
            onSetASRMode(b2, bArr);
            return;
        }
        if (b != 64) {
            switch (b) {
                case 0:
                    readFWVersionResponse(b2, bArr);
                    return;
                case 1:
                    onI2CWriteResponse(b2, bArr);
                    return;
                case 2:
                    onI2CReadResponse(b2, bArr);
                    return;
                case 3:
                    setVoicePromptLangResponse(b2, bArr);
                    return;
                case 4:
                    readVoicePromptLangResponse(b2, bArr);
                    return;
                case 5:
                    onSetRTC(b2, bArr);
                    return;
                case 6:
                    onWriteUART(b2, bArr);
                    return;
                case 7:
                    onSetLED(b2, bArr);
                    return;
                default:
                    switch (b) {
                        case 16:
                            onSensorResetResponse(b2, bArr);
                            return;
                        case 17:
                            onReadProximityResponse(b2, bArr);
                            return;
                        case 18:
                            onRead9AxisResponse(b2, bArr);
                            return;
                        default:
                            switch (b) {
                                case 32:
                                    onSetSensorReportEnableResponse(b2, bArr);
                                    return;
                                case 33:
                                    onGetSensorsReportEventStatusResponse(b2, bArr);
                                    return;
                                case 34:
                                    onSetReportIntervalResponse(b2, bArr);
                                    return;
                                case 35:
                                    onGetReportIntervalResponse(b2, bArr);
                                    return;
                                case 36:
                                    onUARTEnable(b2, bArr);
                                    return;
                                default:
                                    switch (b) {
                                        case 39:
                                            onSetAvEvents(b2, bArr);
                                            return;
                                        case 40:
                                            onGetAvEvents(b2, bArr);
                                            return;
                                        case 41:
                                            onGetAvInfo(b2, bArr);
                                            return;
                                        default:
                                            switch (b) {
                                                case 48:
                                                    onWriteConfigParam(b2, bArr);
                                                    return;
                                                case 49:
                                                    onReadConfigParamResponse(b2, bArr);
                                                    return;
                                                case 50:
                                                    onWriteDSPConfigParam(b2, bArr);
                                                    return;
                                                case 51:
                                                    onReadDSPConfigParam(b2, bArr);
                                                    return;
                                                case 52:
                                                    onSetVolatileConfigTimeout(b2, bArr);
                                                    return;
                                                default:
                                                    switch (b) {
                                                        case 80:
                                                            onSet247Monitor(b2, bArr);
                                                            return;
                                                        case 81:
                                                            onGet247Monitor(b2, bArr);
                                                            return;
                                                        case 82:
                                                            onSet247Reporting(b2, bArr);
                                                            return;
                                                        case 83:
                                                            onGet247Reporting(b2, bArr);
                                                            return;
                                                        case 84:
                                                            onSet247PostureConfig(b2, bArr);
                                                            return;
                                                        case 85:
                                                            onGet247PostureConfig(b2, bArr);
                                                            return;
                                                        case 86:
                                                            onGet247StepCount(b2, bArr);
                                                            return;
                                                        case 87:
                                                            onDelete247StepCount(b2, bArr);
                                                            return;
                                                        case 88:
                                                            onGet247Posture(b2, bArr);
                                                            return;
                                                        case 89:
                                                            onDelete247Posture(b2, bArr);
                                                            return;
                                                        case 90:
                                                            onGet247Posture2(b2, bArr);
                                                            return;
                                                        default:
                                                            return;
                                                    }
                                            }
                                    }
                            }
                    }
            }
        }
        onFactoryResetResponse(b2, bArr);
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.GaiaManager
    protected void receiveSuccessfulAcknowledgement(GaiaPacket gaiaPacket) {
        if (gaiaPacket.getCommand() == 648) {
            byte[] payload = gaiaPacket.getPayload();
            if (payload.length >= 2) {
                byte b = payload[0];
                byte b2 = payload[1];
                byte[] bArr = new byte[payload.length - 2];
                System.arraycopy(payload, 2, bArr, 0, payload.length - 2);
                if ((b2 & 128) == 128) {
                    sendResponse((byte) (b2 & 128), b, bArr);
                    return;
                }
                return;
            }
            return;
        }
        if (gaiaPacket.getCommand() == 770) {
            onReadBatteryLevel(gaiaPacket);
        } else if (gaiaPacket.getCommand() == 514) {
            onResetDevice(gaiaPacket);
        } else if (gaiaPacket.getCommand() == 543) {
            onSetAvControl(gaiaPacket);
        }
    }

    private void handleUnsuccessfulAcknowledgement(GaiaPacket gaiaPacket) {
        if (gaiaPacket.getCommand() == 648) {
            byte[] payload = gaiaPacket.getPayload();
            if (payload.length >= 1) {
                byte b = payload[0];
                byte[] bArr = new byte[payload.length - 1];
                if (payload.length - 1 > 0) {
                    System.arraycopy(payload, 1, bArr, 0, payload.length - 1);
                }
                sendResponse(b, (byte) -1, bArr);
            }
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.GaiaManager
    protected void receiveUnsuccessfulAcknowledgement(GaiaPacket gaiaPacket) {
        Log.w("SolosGalaManager", "receiveUnsuccessfulAcknowledgement " + gaiaPacket.getCommand());
        handleUnsuccessfulAcknowledgement(gaiaPacket);
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.GaiaManager
    protected void hasNotReceivedAcknowledgementPacket(GaiaPacket gaiaPacket) {
        Log.w("SolosGalaManager", "hasNotReceivedAcknowledgementPacket " + gaiaPacket.getCommand());
        handleUnsuccessfulAcknowledgement(gaiaPacket);
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.GaiaManager
    protected boolean manageReceivedPacket(GaiaPacket gaiaPacket) {
        if (gaiaPacket.getCommandId() != 49800) {
            return false;
        }
        byte[] payload = gaiaPacket.getPayload();
        if (payload.length > 0) {
            byte b = payload[0];
            byte b2 = payload[1];
            byte[] bArr = new byte[payload.length - 2];
            System.arraycopy(payload, 2, bArr, 0, payload.length - 2);
            if (b2 == 37) {
                onReadAvInfoEvents(b, bArr);
            } else if (b2 != 48) {
                switch (b2) {
                    case 16:
                        onRead9AxisEventResponse(b, bArr);
                        break;
                    case 17:
                        onReadStepCountsResponse(b, bArr);
                        break;
                    case 18:
                        onReadGestureEventResponse(b, bArr);
                        break;
                    case 19:
                        onReadMotionEventResponse(b, bArr);
                        break;
                    default:
                        switch (b2) {
                            case 32:
                                onRead247StepCountEvent(b, bArr);
                                break;
                            case 33:
                                onRead247PostureMonitorEvent(b, bArr);
                                break;
                            case 34:
                                onRead247PostureMonitorEvent2(b, bArr);
                                break;
                        }
                }
            } else {
                onReadUARTDataEvent(b, bArr);
            }
        }
        return true;
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.GaiaManager
    protected void onSendingFailed(GaiaPacket gaiaPacket) {
        Log.w("SolosGalaManager", "onSendingFailed " + gaiaPacket.getCommand());
        handleUnsuccessfulAcknowledgement(gaiaPacket);
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.GaiaManager
    protected boolean sendGAIAPacket(byte[] bArr) {
        return this.mListener.sendGAIAPacket(bArr);
    }

    public void readFWVersion() {
        createRequest(createPacket(CommandSpecification.GAIA_SOLOS_COMMANDS, new byte[]{0}));
    }

    public void readFWVersionResponse(byte b, byte[] bArr) {
        int i;
        CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus;
        int i2;
        int i3;
        int i4;
        CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus2;
        CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus3 = CommandSpecification.GaiaSolosResponseStatus.SUCCESS;
        if (b != 0) {
            gaiaSolosResponseStatus2 = CommandSpecification.GaiaSolosResponseStatus.FAILED;
        } else if (bArr.length < 4) {
            gaiaSolosResponseStatus2 = CommandSpecification.GaiaSolosResponseStatus.INVALID_PAYLOAD_LENGTH;
        } else {
            int i5 = (bArr[0] << 8) + bArr[1];
            int i6 = bArr[2] & 255;
            int i7 = bArr[3] & 255;
            i = i5;
            gaiaSolosResponseStatus = gaiaSolosResponseStatus3;
            gaiaSolosResponseStatus2 = gaiaSolosResponseStatus3;
            i2 = bArr.length == 5 ? bArr[4] & 255 : 0;
            i3 = i6;
            i4 = i7;
            this.mListener.onReadFwVersion(gaiaSolosResponseStatus, i, i3, i4, i2);
        }
        gaiaSolosResponseStatus = gaiaSolosResponseStatus2;
        i = 0;
        i3 = 0;
        i4 = 0;
        i2 = 0;
        this.mListener.onReadFwVersion(gaiaSolosResponseStatus, i, i3, i4, i2);
    }

    public void readVoicePromptLang() {
        createRequest(createPacket(CommandSpecification.GAIA_SOLOS_COMMANDS, new byte[]{4}));
    }

    public void setVoicePromptLang(byte b) {
        createRequest(createPacket(CommandSpecification.GAIA_SOLOS_COMMANDS, new byte[]{3, b}));
    }

    public void readVoicePromptLangResponse(byte b, byte[] bArr) {
        CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.SUCCESS;
        byte b2 = -1;
        if (b != 0) {
            gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.FAILED;
        } else if (bArr.length < 1) {
            gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.INVALID_PAYLOAD_LENGTH;
        } else {
            b2 = bArr[0];
        }
        this.mListener.onReadDeviceVoicePromptLang(gaiaSolosResponseStatus, b2);
    }

    public void setVoicePromptLangResponse(byte b, byte[] bArr) {
        CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.SUCCESS;
        if (b != 0) {
            gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.FAILED;
        }
        this.mListener.onWriteDeviceVoicePromptLang(gaiaSolosResponseStatus);
    }

    public void onReadProximity() {
        createRequest(createPacket(CommandSpecification.GAIA_SOLOS_COMMANDS, new byte[]{17}));
    }

    public void onReadProximityResponse(byte b, byte[] bArr) {
        CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.SUCCESS;
        int i = -1;
        if (b != 0) {
            gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.FAILED;
        } else if (bArr.length < 2) {
            gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.INVALID_PAYLOAD_LENGTH;
        } else {
            i = ((bArr[0] & 255) << 8) | (bArr[1] & 255);
        }
        this.mListener.onReadProximity(gaiaSolosResponseStatus, i);
    }

    public void onRead9Axis(SensorReport sensorReport) {
        byte[] bArr = {18, 0};
        if (sensorReport.isAccelerationAvail()) {
            bArr[1] = (byte) (bArr[1] | 1);
        }
        if (sensorReport.isGyroAvail()) {
            bArr[1] = (byte) (2 | bArr[1]);
        }
        if (sensorReport.isMagnetAvail()) {
            bArr[1] = (byte) (bArr[1] | 4);
        }
        createRequest(createPacket(CommandSpecification.GAIA_SOLOS_COMMANDS, bArr));
    }

    void onRead9AxisResponse(byte b, byte[] bArr) {
        int i;
        CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.SUCCESS;
        SensorReport sensorReport = new SensorReport();
        if (b != 0) {
            gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.FAILED;
        } else if (bArr.length < 1) {
            gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.INVALID_PAYLOAD_LENGTH;
        } else {
            int i2 = 7;
            if ((bArr[0] & 1) == 1) {
                sensorReport.setAcceleration(true);
                i = 7;
            } else {
                i = 1;
            }
            if ((bArr[0] & 2) == 2) {
                sensorReport.setGyro(true);
                i += 6;
            }
            if ((bArr[0] & 4) == 4) {
                sensorReport.setMagnet(true);
                i += 6;
            }
            if (bArr.length < i) {
                gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.INVALID_PAYLOAD_LENGTH;
            } else {
                if (sensorReport.isAccelerationAvail()) {
                    sensorReport.acc[0] = (bArr[1] & 65280) | (bArr[2] & 255);
                    sensorReport.acc[1] = (bArr[4] & 255) | (bArr[3] & 65280);
                    sensorReport.acc[2] = (bArr[5] & 65280) | (bArr[6] & 255);
                } else {
                    i2 = 1;
                }
                if (sensorReport.isGyroAvail()) {
                    int i3 = i2 + 1;
                    int i4 = i3 + 1;
                    sensorReport.gyro[0] = (bArr[i2] & 65280) | (bArr[i3] & 255);
                    int[] iArr = sensorReport.gyro;
                    int i5 = i4 + 1;
                    int i6 = bArr[i4] & 65280;
                    int i7 = i5 + 1;
                    iArr[1] = (bArr[i5] & 255) | i6;
                    int i8 = i7 + 1;
                    sensorReport.gyro[2] = (bArr[i8] & 255) | (bArr[i7] & 65280);
                    i2 = i8 + 1;
                }
                if (sensorReport.isMagnetAvail()) {
                    int i9 = i2 + 1;
                    int i10 = i9 + 1;
                    sensorReport.magnet[0] = (bArr[i2] & 65280) | (bArr[i9] & 255);
                    int i11 = i10 + 1;
                    int i12 = i11 + 1;
                    sensorReport.magnet[1] = (bArr[i11] & 255) | (bArr[i10] & 65280);
                    sensorReport.magnet[2] = (bArr[i12 + 1] & 255) | (bArr[i12] & 65280);
                }
            }
        }
        this.mListener.onRead9Axis(gaiaSolosResponseStatus, sensorReport);
    }

    public void setASRMode(byte b) {
        createRequest(createPacket(CommandSpecification.GAIA_SOLOS_COMMANDS, new byte[]{22, b}));
    }

    void onSetASRMode(byte b, byte[] bArr) {
        CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.SUCCESS;
        byte b2 = 0;
        if (b != 0) {
            gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.FAILED;
        } else if (bArr.length < 1) {
            gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.INVALID_PAYLOAD_LENGTH;
        } else {
            b2 = bArr[0];
        }
        this.mListener.onSetASRMode(gaiaSolosResponseStatus, b2);
    }

    public void setSensorsReportEventEnable(SensorReport sensorReport) {
        byte[] bArr = new byte[3];
        bArr[0] = 32;
        int i = 1;
        int i2 = 1;
        for (int i3 = 0; i3 < 8; i3++) {
            if (sensorReport.reportEnable1[i3]) {
                bArr[1] = (byte) (bArr[1] | i2);
            }
            i2 <<= 1;
        }
        for (int i4 = 0; i4 < 6; i4++) {
            if (sensorReport.reportEnable2[i4]) {
                bArr[2] = (byte) (bArr[2] | i);
            }
            i <<= 1;
        }
        createRequest(createPacket(CommandSpecification.GAIA_SOLOS_COMMANDS, bArr));
    }

    public void onSetSensorReportEnableResponse(byte b, byte[] bArr) {
        CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.SUCCESS;
        if (b != 0) {
            gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.FAILED;
        }
        this.mListener.onReadSensorsReportEnable(gaiaSolosResponseStatus);
    }

    public void getSensorsReportEventStatus() {
        createRequest(createPacket(CommandSpecification.GAIA_SOLOS_COMMANDS, new byte[]{33}));
    }

    public void onGetSensorsReportEventStatusResponse(byte b, byte[] bArr) {
        CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.SUCCESS;
        SensorReport sensorReport = new SensorReport();
        if (b != 0) {
            gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.FAILED;
        } else if (bArr.length < 2) {
            gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.INVALID_PAYLOAD_LENGTH;
        } else {
            for (int i = 0; i < 8; i++) {
                sensorReport.reportEnable1[i] = (bArr[0] & 1) == 1;
                bArr[0] = (byte) (bArr[0] >> 1);
            }
            for (int i2 = 0; i2 < 6; i2++) {
                sensorReport.reportEnable2[i2] = (bArr[1] & 1) == 1;
                bArr[1] = (byte) (bArr[1] >> 1);
            }
        }
        this.mListener.onReadSensorsReportEventStatus(gaiaSolosResponseStatus, sensorReport);
    }

    public void setSensorsReportInterval(int i) {
        createRequest(createPacket(CommandSpecification.GAIA_SOLOS_COMMANDS, new byte[]{34, 1, (byte) ((i >> 8) & 255), (byte) (i & 255)}));
    }

    public void onSetReportIntervalResponse(byte b, byte[] bArr) {
        CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.SUCCESS;
        byte b2 = 0;
        if (b != 0) {
            gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.FAILED;
        } else if (bArr.length < 1) {
            gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.INVALID_PAYLOAD_LENGTH;
        } else {
            b2 = bArr[0];
        }
        this.mListener.onSetSensorReportInterval(gaiaSolosResponseStatus, b2);
    }

    public void getSensorReportInterval() {
        createRequest(createPacket(CommandSpecification.GAIA_SOLOS_COMMANDS, new byte[]{CommandSpecification.GAIA_SOLOS_COMMANDS_GET_REPORT_EVENT_INTERVAL, 1}));
    }

    public void onGetReportIntervalResponse(byte b, byte[] bArr) {
        int i;
        CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.SUCCESS;
        byte b2 = 0;
        if (b != 0) {
            gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.FAILED;
        } else if (bArr.length < 3) {
            gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.INVALID_PAYLOAD_LENGTH;
        } else {
            b2 = bArr[0];
            i = ((bArr[1] & 255) << 8) | (bArr[2] & 255);
            this.mListener.onReadSensorReportInterval(gaiaSolosResponseStatus, b2, i);
        }
        i = 0;
        this.mListener.onReadSensorReportInterval(gaiaSolosResponseStatus, b2, i);
    }

    public void onRead9AxisEventResponse(byte b, byte[] bArr) {
        int i;
        CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.SUCCESS;
        SensorReport sensorReport = new SensorReport();
        int i2 = 0;
        int i3 = 5;
        if (bArr.length < 5) {
            gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.INVALID_PAYLOAD_LENGTH;
        } else {
            if ((bArr[0] & 1) == 1) {
                sensorReport.setAcceleration(true);
                i = 11;
            } else {
                i = 5;
            }
            if ((bArr[0] & 2) == 2) {
                sensorReport.setGyro(true);
                i += 6;
            }
            if ((bArr[0] & 4) == 4) {
                sensorReport.setMagnet(true);
                i += 6;
            }
            if ((bArr[0] & 8) == 8) {
                sensorReport.setVad(true);
                i++;
            }
            if (bArr.length < i) {
                gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.INVALID_PAYLOAD_LENGTH;
            } else {
                int i4 = ((bArr[1] & 255) << 24) | ((bArr[2] & 255) << 16) | ((bArr[3] & 255) << 8) | (bArr[4] & 255);
                if (sensorReport.isAccelerationAvail()) {
                    sensorReport.acc[0] = ((bArr[5] & 255) << 8) | (bArr[6] & 255);
                    sensorReport.acc[1] = ((bArr[7] & 255) << 8) | (bArr[8] & 255);
                    sensorReport.acc[2] = ((bArr[9] & 255) << 8) | (bArr[10] & 255);
                    i3 = 11;
                }
                if (sensorReport.isGyroAvail()) {
                    int i5 = i3 + 1;
                    int i6 = i5 + 1;
                    sensorReport.gyro[0] = ((bArr[i3] & 255) << 8) | (bArr[i5] & 255);
                    int[] iArr = sensorReport.gyro;
                    int i7 = i6 + 1;
                    int i8 = (bArr[i6] & 255) << 8;
                    int i9 = i7 + 1;
                    iArr[1] = (bArr[i7] & 255) | i8;
                    int i10 = i9 + 1;
                    sensorReport.gyro[2] = (bArr[i10] & 255) | ((bArr[i9] & 255) << 8);
                    i3 = i10 + 1;
                }
                if (sensorReport.isMagnetAvail()) {
                    int i11 = i3 + 1;
                    int i12 = i11 + 1;
                    sensorReport.magnet[0] = ((bArr[i3] & 255) << 8) | (bArr[i11] & 255);
                    int[] iArr2 = sensorReport.magnet;
                    int i13 = i12 + 1;
                    int i14 = (bArr[i12] & 255) << 8;
                    int i15 = i13 + 1;
                    iArr2[1] = (bArr[i13] & 255) | i14;
                    int i16 = i15 + 1;
                    sensorReport.magnet[2] = (bArr[i16] & 255) | ((bArr[i15] & 255) << 8);
                    i3 = i16 + 1;
                }
                if (sensorReport.isVadAvail()) {
                    sensorReport.vad[0] = bArr[i3] & 255;
                }
                i2 = i4;
            }
        }
        this.mListener.onRead9AxisEvent(gaiaSolosResponseStatus, i2, sensorReport);
    }

    public void onReadStepCountsResponse(byte b, byte[] bArr) {
        int i;
        CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.SUCCESS;
        int i2 = 0;
        if (bArr.length < 6) {
            gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.INVALID_PAYLOAD_LENGTH;
            i = 0;
        } else {
            i2 = ((bArr[0] & 255) << 24) | ((bArr[1] & 255) << 16) | ((bArr[2] & 255) << 8) | (bArr[3] & 255);
            i = (bArr[5] & 255) | ((bArr[4] & 255) << 8);
        }
        this.mListener.onReadStepCountsEvent(gaiaSolosResponseStatus, i2, i);
    }

    public void onReadGestureEventResponse(byte b, byte[] bArr) {
        boolean[] zArr;
        byte b2;
        CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.SUCCESS;
        int i = 0;
        if (bArr.length < 6) {
            zArr = new boolean[0];
            gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.INVALID_PAYLOAD_LENGTH;
            b2 = 0;
        } else {
            int i2 = ((bArr[0] & 255) << 24) | ((bArr[1] & 255) << 16) | ((bArr[2] & 255) << 8) | (bArr[3] & 255);
            zArr = new boolean[4];
            byte b3 = bArr[4];
            if ((b3 & 1) == 1) {
                zArr[0] = true;
            }
            if ((b3 & 2) == 2) {
                zArr[1] = true;
            }
            if ((b3 & 4) == 4) {
                zArr[2] = true;
            }
            if ((b3 & 8) == 8) {
                zArr[3] = true;
            }
            b2 = bArr[5];
            i = i2;
        }
        this.mListener.onReadGestureEvent(gaiaSolosResponseStatus, i, zArr, b2);
    }

    public void onReadMotionEventResponse(byte b, byte[] bArr) {
        boolean[] zArr;
        CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.SUCCESS;
        int i = 0;
        if (bArr.length < 5) {
            zArr = new boolean[0];
            gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.INVALID_PAYLOAD_LENGTH;
        } else {
            int i2 = ((bArr[0] & 255) << 24) | ((bArr[1] & 255) << 16) | ((bArr[2] & 255) << 8) | (bArr[3] & 255);
            zArr = new boolean[4];
            byte b2 = bArr[4];
            if ((b2 & 1) == 1) {
                zArr[0] = true;
            }
            if ((b2 & 2) == 2) {
                zArr[1] = true;
            }
            if ((b2 & 4) == 4) {
                zArr[2] = true;
            }
            i = i2;
        }
        this.mListener.onReadMotionEvent(gaiaSolosResponseStatus, i, zArr);
    }

    public void factoryReset() {
        createRequest(createPacket(CommandSpecification.GAIA_SOLOS_COMMANDS, new byte[]{CommandSpecification.GAIA_SOLOS_COMMANDS_FACTORY_RESET, -86}));
    }

    public void onFactoryResetResponse(byte b, byte[] bArr) {
        CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.SUCCESS;
        if (b != 0) {
            gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.FAILED;
        }
        this.mListener.onFactoryReset(gaiaSolosResponseStatus);
    }

    public void sensorReset() {
        createRequest(createPacket(CommandSpecification.GAIA_SOLOS_COMMANDS, new byte[]{16}));
    }

    public void onSensorResetResponse(byte b, byte[] bArr) {
        CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.SUCCESS;
        boolean[] zArr = new boolean[3];
        if (b != 0) {
            gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.FAILED;
        } else if (bArr.length < 1) {
            gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.INVALID_PAYLOAD_LENGTH;
        }
        this.mListener.onSensorReset(gaiaSolosResponseStatus, zArr);
    }

    public void i2cRead(byte b, byte b2, byte b3) {
        if (b3 <= 16) {
            CommandSpecification.I2C_WRITE_DEVICE_ADDR = b;
            CommandSpecification.I2C_READ_REG_ADDR = b2;
            CommandSpecification.I2C_READ_DATA_SIZE = b3;
            createRequest(createPacket(CommandSpecification.GAIA_SOLOS_COMMANDS, new byte[]{2, b, b2, b3}));
        }
    }

    public void onI2CReadResponse(byte b, byte[] bArr) {
        CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus;
        byte b2;
        byte b3;
        CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus2;
        CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus3 = CommandSpecification.GaiaSolosResponseStatus.SUCCESS;
        byte[] bArr2 = new byte[CommandSpecification.I2C_READ_DATA_SIZE];
        if (b != 0) {
            gaiaSolosResponseStatus2 = CommandSpecification.GaiaSolosResponseStatus.FAILED;
        } else {
            if (bArr.length >= 2) {
                byte b4 = bArr[0];
                byte b5 = bArr[1];
                System.arraycopy(bArr, 2, bArr2, 0, CommandSpecification.I2C_READ_DATA_SIZE);
                gaiaSolosResponseStatus = gaiaSolosResponseStatus3;
                b2 = b5;
                b3 = b4;
                this.mListener.onI2CRead(gaiaSolosResponseStatus, b3, b2, bArr2, b3 != CommandSpecification.I2C_READ_DEVICE_ADDR && b2 == CommandSpecification.I2C_READ_REG_ADDR);
            }
            gaiaSolosResponseStatus2 = CommandSpecification.GaiaSolosResponseStatus.INVALID_PAYLOAD_LENGTH;
        }
        gaiaSolosResponseStatus = gaiaSolosResponseStatus2;
        b3 = 0;
        b2 = 0;
        this.mListener.onI2CRead(gaiaSolosResponseStatus, b3, b2, bArr2, b3 != CommandSpecification.I2C_READ_DEVICE_ADDR && b2 == CommandSpecification.I2C_READ_REG_ADDR);
    }

    public void i2cWrite(byte b, byte b2, byte b3, byte[] bArr) {
        if (b3 > 16 || b3 != bArr.length) {
            return;
        }
        byte[] bArr2 = new byte[b3 + 4];
        int i = 0;
        bArr2[0] = 1;
        bArr2[1] = b;
        bArr2[2] = (byte) (b3 + 1);
        bArr2[3] = b2;
        int i2 = 4;
        while (i < b3) {
            bArr2[i2] = bArr[i];
            i++;
            i2++;
        }
        CommandSpecification.I2C_WRITE_DEVICE_ADDR = b;
        CommandSpecification.I2C_WRITE_REG_ADDR = b2;
        CommandSpecification.I2C_WRITE_DATA_SIZE = b3;
        createRequest(createPacket(CommandSpecification.GAIA_SOLOS_COMMANDS, bArr2));
    }

    public void onI2CWriteResponse(byte b, byte[] bArr) {
        byte b2;
        byte b3;
        CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.SUCCESS;
        if (b != 0) {
            gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.FAILED;
        } else {
            if (bArr.length >= 2) {
                b2 = bArr[0];
                b3 = bArr[1];
                this.mListener.onI2CWrite(gaiaSolosResponseStatus, b2, b3, b2 != CommandSpecification.I2C_WRITE_DEVICE_ADDR && b3 == CommandSpecification.I2C_WRITE_REG_ADDR);
            }
            gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.INVALID_PAYLOAD_LENGTH;
        }
        b2 = 0;
        b3 = 0;
        this.mListener.onI2CWrite(gaiaSolosResponseStatus, b2, b3, b2 != CommandSpecification.I2C_WRITE_DEVICE_ADDR && b3 == CommandSpecification.I2C_WRITE_REG_ADDR);
    }

    public void readConfigParam(int i, byte b, int i2) {
        createRequest(createPacket(CommandSpecification.GAIA_SOLOS_COMMANDS, new byte[]{CommandSpecification.GAIA_SOLOS_COMMANDS_READ_CONFIG_PARAM, (byte) i, b, (byte) i2}));
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x009a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onReadConfigParamResponse(byte r23, byte[] r24) {
        /*
            r22 = this;
            r0 = r22
            r1 = r24
            cordova.plugins.qcc.GaiaLibrary.gaia.CommandSpecification$GaiaSolosResponseStatus r2 = cordova.plugins.qcc.GaiaLibrary.gaia.CommandSpecification.GaiaSolosResponseStatus.SUCCESS
            cordova.plugins.qcc.BTLibrary.models.SensorReport r9 = new cordova.plugins.qcc.BTLibrary.models.SensorReport
            r9.<init>()
            r3 = 14
            byte[] r15 = new byte[r3]
            r4 = 5
            r5 = 3
            r6 = 0
            if (r23 == 0) goto L21
            cordova.plugins.qcc.GaiaLibrary.gaia.CommandSpecification$GaiaSolosResponseStatus r2 = cordova.plugins.qcc.GaiaLibrary.gaia.CommandSpecification.GaiaSolosResponseStatus.FAILED
        L16:
            r17 = r2
            r8 = r6
            r18 = r8
            r20 = r18
            r21 = r20
            goto L89
        L21:
            int r7 = r1.length
            if (r7 >= r5) goto L27
            cordova.plugins.qcc.GaiaLibrary.gaia.CommandSpecification$GaiaSolosResponseStatus r2 = cordova.plugins.qcc.GaiaLibrary.gaia.CommandSpecification.GaiaSolosResponseStatus.INVALID_PAYLOAD_LENGTH
            goto L16
        L27:
            r7 = r1[r6]
            r8 = 1
            r8 = r1[r8]
            r10 = 2
            r10 = r1[r10]
            r11 = 16
            r12 = 4
            if (r8 == r11) goto L68
            r13 = 17
            if (r8 != r13) goto L39
            goto L68
        L39:
            if (r8 != r5) goto L4c
            r3 = r1[r5]
            r3 = r3 & 255(0xff, float:3.57E-43)
            int r3 = r3 << 8
            r1 = r1[r12]
            r1 = r1 & 255(0xff, float:3.57E-43)
            r1 = r1 | r3
            if (r10 != 0) goto L81
            r9.setSensorsEnable16BitsUnsigned(r1)
            goto L81
        L4c:
            if (r8 != r4) goto L5f
            r11 = r6
        L4f:
            if (r11 >= r3) goto L5a
            int r12 = r11 + 3
            r12 = r1[r12]
            r15[r11] = r12
            int r11 = r11 + 1
            goto L4f
        L5a:
            r17 = r2
            r21 = r6
            goto L85
        L5f:
            r3 = r1[r5]
            r3 = r3 & 255(0xff, float:3.57E-43)
            int r3 = r3 << 8
            r1 = r1[r12]
            goto L7e
        L68:
            r3 = r1[r5]
            r3 = r3 & 255(0xff, float:3.57E-43)
            int r3 = r3 << 24
            r6 = r1[r12]
            r6 = r6 & 255(0xff, float:3.57E-43)
            int r6 = r6 << r11
            r3 = r3 | r6
            r6 = r1[r4]
            r6 = r6 & 255(0xff, float:3.57E-43)
            int r6 = r6 << 8
            r3 = r3 | r6
            r6 = 6
            r1 = r1[r6]
        L7e:
            r1 = r1 & 255(0xff, float:3.57E-43)
            r1 = r1 | r3
        L81:
            r21 = r1
            r17 = r2
        L85:
            r18 = r7
            r20 = r10
        L89:
            if (r8 != r5) goto L9a
            cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener r3 = r0.mListener
            r4 = r17
            r5 = r18
            r6 = r8
            r7 = r20
            r8 = r21
            r3.onReadConfigParam(r4, r5, r6, r7, r8, r9)
            goto Lb2
        L9a:
            if (r8 != r4) goto La9
            cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener r10 = r0.mListener
            r11 = r17
            r12 = r18
            r13 = r8
            r14 = r20
            r10.onReadConfigParam(r11, r12, r13, r14, r15)
            goto Lb2
        La9:
            cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener r1 = r0.mListener
            r16 = r1
            r19 = r8
            r16.onReadConfigParam(r17, r18, r19, r20, r21)
        Lb2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cordova.plugins.qcc.GaiaLibrary.gaia.SolosGaiaPacketManager.onReadConfigParamResponse(byte, byte[]):void");
    }

    public void writeConfigParam(int i, byte b, byte b2, int i2) {
        createRequest(createPacket(CommandSpecification.GAIA_SOLOS_COMMANDS, new byte[]{48, (byte) i, b, b2, (byte) ((i2 >> 8) & 255), (byte) (i2 & 255)}));
    }

    public void writeConfigParam32Bits(int i, byte b, byte b2, long j) {
        createRequest(createPacket(CommandSpecification.GAIA_SOLOS_COMMANDS, new byte[]{48, (byte) i, b, b2, (byte) ((j >> 24) & 255), (byte) ((j >> 16) & 255), (byte) ((j >> 8) & 255), (byte) (j & 255)}));
    }

    public void onWriteConfigParam(byte b, byte[] bArr) {
        byte b2;
        byte b3;
        CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.SUCCESS;
        byte b4 = 0;
        if (b != 0) {
            gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.FAILED;
        } else if (bArr.length < 3) {
            gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.INVALID_PAYLOAD_LENGTH;
        } else {
            b4 = bArr[0];
            b2 = bArr[1];
            b3 = bArr[2];
            this.mListener.onWriteConfigParam(gaiaSolosResponseStatus, b4, b2, b3);
        }
        b2 = 0;
        b3 = 0;
        this.mListener.onWriteConfigParam(gaiaSolosResponseStatus, b4, b2, b3);
    }

    public void setVolatileConfigTimeout(byte b, byte b2) {
        createRequest(createPacket(CommandSpecification.GAIA_SOLOS_COMMANDS, new byte[]{CommandSpecification.GAIA_SOLOS_COMMANDS_SET_VOLATILE_CONFIG_TIMEOUT, b, b2}));
    }

    public void onSetVolatileConfigTimeout(byte b, byte[] bArr) {
        CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.SUCCESS;
        byte b2 = 0;
        if (b != 0) {
            gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.FAILED;
        } else if (bArr.length < 1) {
            gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.INVALID_PAYLOAD_LENGTH;
        } else {
            b2 = bArr[0];
        }
        this.mListener.onSetVolatileConfigTimeout(gaiaSolosResponseStatus, b2);
    }

    public void readBatteryLevel() {
        createRequest(createPacket(GAIA.COMMAND_GET_CURRENT_BATTERY_LEVEL));
    }

    public void onReadBatteryLevel(GaiaPacket gaiaPacket) {
        if (gaiaPacket.getPayload().length >= 3) {
            this.mListener.onReadBatteryLevel(GaiaUtils.extractIntFromByteArray(gaiaPacket.getPayload(), 1, 2, false));
        }
    }

    public void readDSPConfigParam(int i, int i2) {
        createRequest(createPacket(CommandSpecification.GAIA_SOLOS_COMMANDS, new byte[]{CommandSpecification.GAIA_SOLOS_COMMANDS_GET_DSP_CONFIG, (byte) ((i >> 16) & 255), (byte) ((i >> 8) & 255), (byte) (i & 255), (byte) i2}));
    }

    public void writeDSPConfigParam(int i, int i2, DSPConfig dSPConfig) {
        byte[] packedGetGAData;
        byte[] bArr = new byte[(i2 * 2) + 5];
        bArr[0] = CommandSpecification.GAIA_SOLOS_COMMANDS_SET_DSP_CONFIG;
        bArr[3] = (byte) (i & 255);
        bArr[2] = (byte) ((i >> 8) & 255);
        bArr[1] = (byte) ((i >> 16) & 255);
        bArr[4] = (byte) i2;
        if (i == 6291586) {
            packedGetGAData = dSPConfig.getPackedWhisperData();
        } else if (i == 6291588) {
            packedGetGAData = dSPConfig.getPackedWhisperDownlinkData();
        } else if (i == 6291590) {
            packedGetGAData = dSPConfig.getPackedWhisperEqData();
        } else if (i == 6291592) {
            packedGetGAData = dSPConfig.getPackedWhisperFeature();
        } else if (i == 6292482) {
            packedGetGAData = dSPConfig.getPackedWhisperHearableData();
        } else if (i == 6291648) {
            packedGetGAData = dSPConfig.getPackedMagnetData();
        } else if (i != 6291650) {
            return;
        } else {
            packedGetGAData = dSPConfig.getPackedGetGAData();
        }
        for (int i3 = 0; i3 < packedGetGAData.length; i3++) {
            bArr[5 + i3] = packedGetGAData[i3];
        }
        createRequest(createPacket(CommandSpecification.GAIA_SOLOS_COMMANDS, bArr));
    }

    public void setWhisperDownlinkRTBandGain(int i, short s) {
        if (i > 10) {
            return;
        }
        byte b = 0;
        switch (i) {
            case 2:
                b = 1;
                break;
            case 3:
                b = 2;
                break;
            case 4:
                b = 3;
                break;
            case 5:
                b = 4;
                break;
            case 6:
                b = 5;
                break;
            case 7:
                b = 6;
                break;
            case 8:
                b = 7;
                break;
            case 9:
                b = 8;
                break;
            case 10:
                b = 9;
                break;
        }
        writeConfigParam32Bits(0, (byte) 17, b, s << 16);
    }

    public void setWhisperDownlinkRTOutFlag(short s) {
        writeConfigParam32Bits(0, (byte) 17, (byte) 12, s);
    }

    public void setWhisperDownlinkRTEnable(short s) {
        writeConfigParam32Bits(0, (byte) 17, (byte) 11, s);
    }

    public void setWhisperEqRTOutFlag(short s) {
        writeConfigParam32Bits(0, (byte) 16, (byte) 13, s);
    }

    public void setWhisperEqRTBandGain(int i, short s) {
        if (i > 10) {
            return;
        }
        byte b = 14;
        switch (i) {
            case 2:
                b = 15;
                break;
            case 3:
                b = 16;
                break;
            case 4:
                b = 17;
                break;
            case 5:
                b = 18;
                break;
            case 6:
                b = 19;
                break;
            case 7:
                b = 20;
                break;
            case 8:
                b = 21;
                break;
            case 9:
                b = 22;
                break;
            case 10:
                b = 23;
                break;
        }
        writeConfigParam32Bits(0, (byte) 16, b, s);
    }

    public void onWriteDSPConfigParam(byte b, byte[] bArr) {
        if (b == 0) {
            this.mListener.onWriteDSPConfigParam(CommandSpecification.GaiaSolosResponseStatus.SUCCESS, bArr);
        } else {
            this.mListener.onWriteDSPConfigParam(CommandSpecification.GaiaSolosResponseStatus.FAILED, bArr);
        }
    }

    public void onReadDSPConfigParam(byte b, byte[] bArr) {
        if (b == 0) {
            this.mListener.onReadDSPConfigParam(CommandSpecification.GaiaSolosResponseStatus.SUCCESS, bArr);
        } else {
            this.mListener.onReadDSPConfigParam(CommandSpecification.GaiaSolosResponseStatus.FAILED, bArr);
        }
    }

    public void resetDevice() {
        createRequest(createPacket(GAIA.COMMAND_DEVICE_RESET));
    }

    public void onResetDevice(GaiaPacket gaiaPacket) {
        this.mListener.onResetDevice();
    }

    public void setRTC(int i) {
        byte[] bArr = new byte[5];
        byte[] intToBytes = Utils.intToBytes(i);
        bArr[0] = 5;
        System.arraycopy(intToBytes, 0, bArr, 1, intToBytes.length);
        createRequest(createPacket(CommandSpecification.GAIA_SOLOS_COMMANDS, bArr));
    }

    public void onSetRTC(byte b, byte[] bArr) {
        CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.SUCCESS;
        if (b != 0) {
            gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.FAILED;
        }
        this.mListener.onSetRTC(gaiaSolosResponseStatus);
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.UartProtocolStack.UartWriteImp
    public void UARTWrite(int i, byte[] bArr) {
        byte[] bArr2 = new byte[i + 2];
        bArr2[0] = 6;
        bArr2[1] = (byte) i;
        System.arraycopy(bArr, 0, bArr2, 2, i);
        createRequest(createPacket(CommandSpecification.GAIA_SOLOS_COMMANDS, bArr2));
    }

    public void onWriteUART(byte b, byte[] bArr) {
        CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.SUCCESS;
        if (b != 0) {
            gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.FAILED;
        }
        this.mListener.onWriteUART(gaiaSolosResponseStatus);
    }

    public void setLED(int i) {
        createRequest(createPacket(CommandSpecification.GAIA_SOLOS_COMMANDS, new byte[]{7, (byte) i}));
    }

    public void onSetLED(byte b, byte[] bArr) {
        CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.SUCCESS;
        if (b != 0) {
            gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.FAILED;
        }
        this.mListener.onSetLED(gaiaSolosResponseStatus);
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.UartProtocolStack.UartWriteImp
    public void EnableUART(boolean z) {
        createRequest(createPacket(CommandSpecification.GAIA_SOLOS_COMMANDS, new byte[]{CommandSpecification.GAIA_SOLOS_COMMANDS_UART_ENABLE, z ? (byte) 1 : (byte) 0}));
    }

    public void onUARTEnable(byte b, byte[] bArr) {
        CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.SUCCESS;
        boolean z = false;
        if (b != 0) {
            gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.FAILED;
        } else if (bArr.length < 1) {
            gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.INVALID_PAYLOAD_LENGTH;
        } else if ((bArr[0] & 1) == 1) {
            z = true;
        }
        this.mListener.onUARTEnable(gaiaSolosResponseStatus, z);
    }

    public void set247Monitor(boolean z, boolean z2) {
        byte[] bArr = new byte[2];
        byte b = z ? (byte) 1 : (byte) 0;
        int i = z2 ? 2 : 0;
        bArr[0] = CommandSpecification.GAIA_SOLOS_COMMANDS_SET_WHOLE_DAY_MONITOR;
        bArr[1] = (byte) (b | ((byte) i));
        createRequest(createPacket(CommandSpecification.GAIA_SOLOS_COMMANDS, bArr));
    }

    public void onSet247Monitor(byte b, byte[] bArr) {
        CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.SUCCESS;
        if (b != 0) {
            gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.FAILED;
        }
        this.mListener.onSet247Monitor(gaiaSolosResponseStatus);
    }

    public void get247Monitor() {
        createRequest(createPacket(CommandSpecification.GAIA_SOLOS_COMMANDS, new byte[]{CommandSpecification.GAIA_SOLOS_COMMANDS_GET_WHOLE_DAY_MONITOR}));
    }

    public void onGet247Monitor(byte b, byte[] bArr) {
        boolean z;
        boolean r1 = false;
        CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.SUCCESS;
        if (b != 0) {
            gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.FAILED;
        } else if (bArr.length < 1) {
            gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.INVALID_PAYLOAD_LENGTH;
        } else {
            byte b2 = bArr[0];
            boolean z2 = (b2 & 1) == 1;
            z = ((b2 & 2) >> 1) == 1;
            r1 = z2;
            this.mListener.onGet247Monitor(gaiaSolosResponseStatus, r1, z);
        }
        z = false;
        this.mListener.onGet247Monitor(gaiaSolosResponseStatus, r1, z);
    }

    public void set247Reporting(boolean z, boolean z2, boolean z3, int i) {
        byte[] bArr = new byte[6];
        byte b = z ? (byte) 1 : (byte) 0;
        byte b2 = (byte) (z2 ? 2 : 0);
        int i2 = z3 ? 4 : 0;
        bArr[0] = CommandSpecification.GAIA_SOLOS_COMMANDS_SET_WHOLE_DAY_MONITOR_REPORT_EVENT;
        bArr[1] = (byte) (b | b2 | ((byte) i2));
        byte[] intToBytes = Utils.intToBytes(i);
        System.arraycopy(intToBytes, 0, bArr, 2, intToBytes.length);
        createRequest(createPacket(CommandSpecification.GAIA_SOLOS_COMMANDS, bArr));
    }

    public void onSet247Reporting(byte b, byte[] bArr) {
        CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.SUCCESS;
        if (b != 0) {
            gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.FAILED;
        }
        this.mListener.onSet247Reporting(gaiaSolosResponseStatus);
    }

    public void get247Reporting() {
        createRequest(createPacket(CommandSpecification.GAIA_SOLOS_COMMANDS, new byte[]{CommandSpecification.GAIA_SOLOS_COMMANDS_GET_WHOLE_DAY_MONITOR_REPORT}));
    }

    public void onGet247Reporting(byte b, byte[] bArr) {
        int bytesToInt;
        CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus;
        boolean z;
        boolean z2;
        boolean z3;
        CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus2 = CommandSpecification.GaiaSolosResponseStatus.SUCCESS;
        CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus3 = CommandSpecification.GaiaSolosResponseStatus.SUCCESS;
        if (b != 0) {
            gaiaSolosResponseStatus2 = CommandSpecification.GaiaSolosResponseStatus.FAILED;
        } else if (bArr.length < 5) {
            gaiaSolosResponseStatus2 = CommandSpecification.GaiaSolosResponseStatus.INVALID_PAYLOAD_LENGTH;
        } else {
            byte b2 = bArr[0];
            boolean z4 = (b2 & 1) == 1;
            boolean z5 = ((b2 & 2) >> 1) == 1;
            boolean z6 = ((b2 & 4) >> 2) == 1;
            bytesToInt = Utils.bytesToInt(Arrays.copyOfRange(bArr, 1, 5));
            gaiaSolosResponseStatus = gaiaSolosResponseStatus3;
            z = z6;
            z2 = z4;
            z3 = z5;
            this.mListener.onGet247Reporting(gaiaSolosResponseStatus, z2, z3, z, bytesToInt);
        }
        gaiaSolosResponseStatus = gaiaSolosResponseStatus2;
        z2 = false;
        z3 = false;
        z = false;
        bytesToInt = 0;
        this.mListener.onGet247Reporting(gaiaSolosResponseStatus, z2, z3, z, bytesToInt);
    }

    public void set247PostureConfig(int i, int i2, int i3, int i4) {
        byte[] shortToBytes = Utils.shortToBytes((short) i3);
        byte[] r0 = new byte[shortToBytes.length - 2];
        System.arraycopy(shortToBytes, 0, r0, 3, shortToBytes.length);
        byte[] bArr = {CommandSpecification.GAIA_SOLOS_COMMANDS_SET_WHOLE_DAY_POSTURE_MONITOR_CONFIG, (byte) i, (byte) i2, 0, 0, (byte) i4};
        createRequest(createPacket(CommandSpecification.GAIA_SOLOS_COMMANDS, bArr));
    }

    public void onSet247PostureConfig(byte b, byte[] bArr) {
        CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.SUCCESS;
        if (b != 0) {
            gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.FAILED;
        }
        this.mListener.onSet247PostureConfig(gaiaSolosResponseStatus);
    }

    public void get247PostureConfig() {
        createRequest(createPacket(CommandSpecification.GAIA_SOLOS_COMMANDS, new byte[]{CommandSpecification.GAIA_SOLOS_COMMANDS_GET_WHOLE_DAY_POSTURE_MONITOR_CONFIG}));
    }

    public void onGet247PostureConfig(byte b, byte[] bArr) {
        byte b2;
        byte b3;
        CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus;
        byte b4;
        short s;
        CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus2 = CommandSpecification.GaiaSolosResponseStatus.SUCCESS;
        CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus3 = CommandSpecification.GaiaSolosResponseStatus.SUCCESS;
        if (b != 0) {
            gaiaSolosResponseStatus2 = CommandSpecification.GaiaSolosResponseStatus.FAILED;
        } else if (bArr.length < 5) {
            gaiaSolosResponseStatus2 = CommandSpecification.GaiaSolosResponseStatus.INVALID_PAYLOAD_LENGTH;
        } else {
            byte b5 = bArr[0];
            byte b6 = bArr[1];
            short bytesToShort = Utils.bytesToShort(Arrays.copyOfRange(bArr, 2, 4));
            b2 = b6;
            b3 = bArr[4];
            gaiaSolosResponseStatus = gaiaSolosResponseStatus3;
            b4 = b5;
            s = bytesToShort;
            this.mListener.onGet247PostureConfig(gaiaSolosResponseStatus, b4, b2, s, b3);
        }
        gaiaSolosResponseStatus = gaiaSolosResponseStatus2;
        b4 = 0;
        b2 = 0;
        s = 0;
        b3 = 0;
        this.mListener.onGet247PostureConfig(gaiaSolosResponseStatus, b4, b2, s, b3);
    }

    public void get247StepCount(int i, short s) {
        byte[] bArr = new byte[7];
        bArr[0] = CommandSpecification.GAIA_SOLOS_COMMANDS_DOWNLOAD_WHOLE_DAY_STEP_COUNT_REPORT;
        byte[] intToBytes = Utils.intToBytes(i);
        byte[] shortToBytes = Utils.shortToBytes(s);
        System.arraycopy(intToBytes, 0, bArr, 1, intToBytes.length);
        System.arraycopy(shortToBytes, 0, bArr, intToBytes.length + 1, shortToBytes.length);
        createRequest(createPacket(CommandSpecification.GAIA_SOLOS_COMMANDS, bArr));
    }

    private void onGet247StepCount(byte b, byte[] bArr) {
        short bytesToShort;
        short bytesToShort2;
        CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.SUCCESS;
        int i = 0;
        if (b != 0) {
            gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.FAILED;
        } else if (bArr.length < 8) {
            gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.INVALID_PAYLOAD_LENGTH;
        } else {
            i = Utils.bytesToInt(Arrays.copyOfRange(bArr, 0, 4));
            bytesToShort = Utils.bytesToShort(Arrays.copyOfRange(bArr, 4, 6));
            bytesToShort2 = Utils.bytesToShort(Arrays.copyOfRange(bArr, 6, 8));
            this.mListener.onGet247StepCount(gaiaSolosResponseStatus, i, bytesToShort, bytesToShort2);
        }
        bytesToShort = 0;
        bytesToShort2 = 0;
        this.mListener.onGet247StepCount(gaiaSolosResponseStatus, i, bytesToShort, bytesToShort2);
    }

    public void delete247StepCount(int i, short s) {
        byte[] bArr = new byte[7];
        bArr[0] = CommandSpecification.GAIA_SOLOS_COMMANDS_DELETE_WHOLE_DAY_STEP_COUNT_REPORT;
        byte[] intToBytes = Utils.intToBytes(i);
        byte[] shortToBytes = Utils.shortToBytes(s);
        System.arraycopy(intToBytes, 0, bArr, 1, intToBytes.length);
        System.arraycopy(shortToBytes, 0, bArr, intToBytes.length + 1, shortToBytes.length);
        createRequest(createPacket(CommandSpecification.GAIA_SOLOS_COMMANDS, bArr));
    }

    private void onDelete247StepCount(byte b, byte[] bArr) {
        short bytesToShort;
        CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.SUCCESS;
        int i = 0;
        if (b != 0) {
            gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.FAILED;
        } else if (bArr.length < 6) {
            gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.INVALID_PAYLOAD_LENGTH;
        } else {
            i = Utils.bytesToInt(Arrays.copyOfRange(bArr, 0, 4));
            bytesToShort = Utils.bytesToShort(Arrays.copyOfRange(bArr, 4, 6));
            this.mListener.onDelete247StepCount(gaiaSolosResponseStatus, i, bytesToShort);
        }
        bytesToShort = 0;
        this.mListener.onDelete247StepCount(gaiaSolosResponseStatus, i, bytesToShort);
    }

    public void get247Posture(int i, short s) {
        byte[] bArr = new byte[7];
        bArr[0] = CommandSpecification.GAIA_SOLOS_COMMANDS_DOWNLOAD_WHOLE_DAY_POSTURE_MONITOR_REPORT;
        byte[] intToBytes = Utils.intToBytes(i);
        byte[] shortToBytes = Utils.shortToBytes(s);
        System.arraycopy(intToBytes, 0, bArr, 1, intToBytes.length);
        System.arraycopy(shortToBytes, 0, bArr, intToBytes.length + 1, shortToBytes.length);
        createRequest(createPacket(CommandSpecification.GAIA_SOLOS_COMMANDS, bArr));
    }

    private void onGet247Posture(byte b, byte[] bArr) {
        int bytesToInt;
        SolosGaiaPacketManager solosGaiaPacketManager;
        CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus;
        short s;
        CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus2 = CommandSpecification.GaiaSolosResponseStatus.SUCCESS;
        CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus3 = CommandSpecification.GaiaSolosResponseStatus.SUCCESS;
        int[] iArr = new int[7];
        int[] iArr2 = new int[7];
        if (b != 0) {
            gaiaSolosResponseStatus2 = CommandSpecification.GaiaSolosResponseStatus.FAILED;
        } else if (bArr.length < 48) {
            gaiaSolosResponseStatus2 = CommandSpecification.GaiaSolosResponseStatus.INVALID_PAYLOAD_LENGTH;
        } else {
            bytesToInt = Utils.bytesToInt(Arrays.copyOfRange(bArr, 0, 4));
            short bytesToShort = Utils.bytesToShort(Arrays.copyOfRange(bArr, 4, 6));
            iArr[0] = Utils.bytesToInt(Arrays.copyOfRange(bArr, 6, 9));
            iArr[1] = Utils.bytesToInt(Arrays.copyOfRange(bArr, 9, 12));
            iArr[2] = Utils.bytesToInt(Arrays.copyOfRange(bArr, 12, 15));
            iArr[3] = Utils.bytesToInt(Arrays.copyOfRange(bArr, 15, 18));
            iArr[4] = Utils.bytesToInt(Arrays.copyOfRange(bArr, 18, 21));
            iArr[5] = Utils.bytesToInt(Arrays.copyOfRange(bArr, 21, 24));
            iArr[6] = Utils.bytesToInt(Arrays.copyOfRange(bArr, 24, 27));
            iArr2[0] = Utils.bytesToInt(Arrays.copyOfRange(bArr, 27, 30));
            iArr2[1] = Utils.bytesToInt(Arrays.copyOfRange(bArr, 30, 33));
            iArr2[2] = Utils.bytesToInt(Arrays.copyOfRange(bArr, 33, 36));
            iArr2[3] = Utils.bytesToInt(Arrays.copyOfRange(bArr, 36, 39));
            iArr2[4] = Utils.bytesToInt(Arrays.copyOfRange(bArr, 39, 42));
            iArr2[5] = Utils.bytesToInt(Arrays.copyOfRange(bArr, 42, 45));
            iArr2[6] = Utils.bytesToInt(Arrays.copyOfRange(bArr, 45, 48));
            solosGaiaPacketManager = this;
            gaiaSolosResponseStatus = gaiaSolosResponseStatus3;
            s = bytesToShort;
            solosGaiaPacketManager.mListener.onGet247Posture(gaiaSolosResponseStatus, bytesToInt, s, iArr, iArr2);
        }
        solosGaiaPacketManager = this;
        gaiaSolosResponseStatus = gaiaSolosResponseStatus2;
        bytesToInt = 0;
        s = 0;
        solosGaiaPacketManager.mListener.onGet247Posture(gaiaSolosResponseStatus, bytesToInt, s, iArr, iArr2);
    }

    public void get247Posture2(int i, short s) {
        byte[] bArr = new byte[7];
        bArr[0] = CommandSpecification.GAIA_SOLOS_COMMANDS_DOWNLOAD_WHOLE_DAY_POSTURE_MONITOR_REPORT_2;
        byte[] intToBytes = Utils.intToBytes(i);
        byte[] shortToBytes = Utils.shortToBytes(s);
        System.arraycopy(intToBytes, 0, bArr, 1, intToBytes.length);
        System.arraycopy(shortToBytes, 0, bArr, intToBytes.length + 1, shortToBytes.length);
        createRequest(createPacket(CommandSpecification.GAIA_SOLOS_COMMANDS, bArr));
    }

    private void onGet247Posture2(byte b, byte[] bArr) {
        SolosGaiaPacketManager solosGaiaPacketManager;
        int bytesToInt;
        short s;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus;
        int i13;
        int i14;
        CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus2 = CommandSpecification.GaiaSolosResponseStatus.SUCCESS;
        CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus3 = CommandSpecification.GaiaSolosResponseStatus.SUCCESS;
        if (b != 0) {
            gaiaSolosResponseStatus2 = CommandSpecification.GaiaSolosResponseStatus.FAILED;
        } else if (bArr.length < 48) {
            gaiaSolosResponseStatus2 = CommandSpecification.GaiaSolosResponseStatus.INVALID_PAYLOAD_LENGTH;
        } else {
            int bytesToInt2 = Utils.bytesToInt(Arrays.copyOfRange(bArr, 0, 4));
            short bytesToShort = Utils.bytesToShort(Arrays.copyOfRange(bArr, 4, 6));
            int bytesToInt3 = Utils.bytesToInt(Arrays.copyOfRange(bArr, 6, 9));
            int bytesToInt4 = Utils.bytesToInt(Arrays.copyOfRange(bArr, 9, 12));
            int bytesToInt5 = Utils.bytesToInt(Arrays.copyOfRange(bArr, 12, 15));
            int bytesToInt6 = Utils.bytesToInt(Arrays.copyOfRange(bArr, 15, 18));
            int bytesToInt7 = Utils.bytesToInt(Arrays.copyOfRange(bArr, 18, 21));
            int bytesToInt8 = Utils.bytesToInt(Arrays.copyOfRange(bArr, 21, 24));
            int bytesToInt9 = Utils.bytesToInt(Arrays.copyOfRange(bArr, 24, 27));
            int bytesToInt10 = Utils.bytesToInt(Arrays.copyOfRange(bArr, 27, 30));
            int bytesToInt11 = Utils.bytesToInt(Arrays.copyOfRange(bArr, 30, 33));
            int bytesToInt12 = Utils.bytesToInt(Arrays.copyOfRange(bArr, 33, 36));
            int bytesToInt13 = Utils.bytesToInt(Arrays.copyOfRange(bArr, 36, 39));
            int bytesToInt14 = Utils.bytesToInt(Arrays.copyOfRange(bArr, 39, 42));
            int bytesToInt15 = Utils.bytesToInt(Arrays.copyOfRange(bArr, 42, 45));
            solosGaiaPacketManager = this;
            bytesToInt = Utils.bytesToInt(Arrays.copyOfRange(bArr, 45, 48));
            s = bytesToShort;
            i = bytesToInt14;
            i2 = bytesToInt3;
            i3 = bytesToInt4;
            i4 = bytesToInt5;
            i5 = bytesToInt6;
            i6 = bytesToInt7;
            i7 = bytesToInt8;
            i8 = bytesToInt9;
            i9 = bytesToInt10;
            i10 = bytesToInt11;
            i11 = bytesToInt12;
            i12 = bytesToInt13;
            gaiaSolosResponseStatus = gaiaSolosResponseStatus3;
            i13 = bytesToInt2;
            i14 = bytesToInt15;
            solosGaiaPacketManager.mListener.onGet247Posture(gaiaSolosResponseStatus, i13, s, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i, i14, bytesToInt);
        }
        solosGaiaPacketManager = this;
        gaiaSolosResponseStatus = gaiaSolosResponseStatus2;
        i13 = 0;
        s = 0;
        i2 = 0;
        i3 = 0;
        i4 = 0;
        i5 = 0;
        i6 = 0;
        i7 = 0;
        i8 = 0;
        i9 = 0;
        i10 = 0;
        i11 = 0;
        i12 = 0;
        i = 0;
        i14 = 0;
        bytesToInt = 0;
        solosGaiaPacketManager.mListener.onGet247Posture(gaiaSolosResponseStatus, i13, s, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i, i14, bytesToInt);
    }

    public void delete247Posture(int i, short s) {
        byte[] bArr = new byte[7];
        bArr[0] = CommandSpecification.GAIA_SOLOS_COMMANDS_DELETE_WHOLE_DAY_POSTURE_MONITOR_REPORT;
        byte[] intToBytes = Utils.intToBytes(i);
        byte[] shortToBytes = Utils.shortToBytes(s);
        System.arraycopy(intToBytes, 0, bArr, 1, intToBytes.length);
        System.arraycopy(shortToBytes, 0, bArr, intToBytes.length + 1, shortToBytes.length);
        createRequest(createPacket(CommandSpecification.GAIA_SOLOS_COMMANDS, bArr));
    }

    private void onDelete247Posture(byte b, byte[] bArr) {
        short bytesToShort;
        CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.SUCCESS;
        int i = 0;
        if (b != 0) {
            gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.FAILED;
        } else if (bArr.length < 6) {
            gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.INVALID_PAYLOAD_LENGTH;
        } else {
            i = Utils.bytesToInt(Arrays.copyOfRange(bArr, 0, 4));
            bytesToShort = Utils.bytesToShort(Arrays.copyOfRange(bArr, 4, 6));
            this.mListener.onDelete247Posture(gaiaSolosResponseStatus, i, bytesToShort);
        }
        bytesToShort = 0;
        this.mListener.onDelete247Posture(gaiaSolosResponseStatus, i, bytesToShort);
    }

    public void onReadUARTDataEvent(byte b, byte[] bArr) {
        if (b == 0) {
            CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.SUCCESS;
            if (bArr[0] == bArr.length - 1) {
                this.mListener.onReadUARTDataEvent(gaiaSolosResponseStatus, Arrays.copyOfRange(bArr, 2, bArr.length));
                this.mUart.onPacketReceive(bArr);
            }
        }
    }

    public void onRead247PostureMonitorEvent(byte b, byte[] bArr) {
        if (b == 0) {
            this.mListener.onRead247PostureMonitorEvent(CommandSpecification.GaiaSolosResponseStatus.SUCCESS, Utils.bytesToInt(Arrays.copyOfRange(bArr, 0, 4)), new int[]{Utils.bytesToInt(Arrays.copyOfRange(bArr, 4, 7)), Utils.bytesToInt(Arrays.copyOfRange(bArr, 7, 10)), Utils.bytesToInt(Arrays.copyOfRange(bArr, 10, 13)), Utils.bytesToInt(Arrays.copyOfRange(bArr, 13, 16)), Utils.bytesToInt(Arrays.copyOfRange(bArr, 16, 19)), Utils.bytesToInt(Arrays.copyOfRange(bArr, 19, 22)), Utils.bytesToInt(Arrays.copyOfRange(bArr, 22, 25))}, new int[]{Utils.bytesToInt(Arrays.copyOfRange(bArr, 25, 28)), Utils.bytesToInt(Arrays.copyOfRange(bArr, 28, 31)), Utils.bytesToInt(Arrays.copyOfRange(bArr, 31, 34)), Utils.bytesToInt(Arrays.copyOfRange(bArr, 34, 37)), Utils.bytesToInt(Arrays.copyOfRange(bArr, 37, 40)), Utils.bytesToInt(Arrays.copyOfRange(bArr, 40, 43)), Utils.bytesToInt(Arrays.copyOfRange(bArr, 43, bArr.length))});
        }
    }

    public void onRead247PostureMonitorEvent2(byte b, byte[] bArr) {
        if (b == 0) {
            this.mListener.onRead247PostureMonitorEvent(CommandSpecification.GaiaSolosResponseStatus.SUCCESS, Utils.bytesToInt(Arrays.copyOfRange(bArr, 0, 4)), Utils.bytesToInt(Arrays.copyOfRange(bArr, 4, 7)), Utils.bytesToInt(Arrays.copyOfRange(bArr, 7, 10)), Utils.bytesToInt(Arrays.copyOfRange(bArr, 10, 13)), Utils.bytesToInt(Arrays.copyOfRange(bArr, 13, 16)), Utils.bytesToInt(Arrays.copyOfRange(bArr, 16, 19)), Utils.bytesToInt(Arrays.copyOfRange(bArr, 19, 22)), Utils.bytesToInt(Arrays.copyOfRange(bArr, 22, 25)), Utils.bytesToInt(Arrays.copyOfRange(bArr, 25, 28)), Utils.bytesToInt(Arrays.copyOfRange(bArr, 28, 31)), Utils.bytesToInt(Arrays.copyOfRange(bArr, 31, 34)), Utils.bytesToInt(Arrays.copyOfRange(bArr, 34, 37)), Utils.bytesToInt(Arrays.copyOfRange(bArr, 37, 40)), Utils.bytesToInt(Arrays.copyOfRange(bArr, 40, 43)), Utils.bytesToInt(Arrays.copyOfRange(bArr, 43, bArr.length)));
        }
    }

    public void onRead247StepCountEvent(byte b, byte[] bArr) {
        if (b == 0) {
            this.mListener.onRead247StepCountEvent(CommandSpecification.GaiaSolosResponseStatus.SUCCESS, Utils.bytesToInt(Arrays.copyOfRange(bArr, 0, 4)), Utils.bytesToShort(Arrays.copyOfRange(bArr, 4, bArr.length)));
        }
    }

    public void rebootDevice(int i) {
        byte[] bArr = new byte[2];
        bArr[0] = 8;
        bArr[1] = (byte) (i == 1 ? 1 : 0);
        createRequest(createPacket(CommandSpecification.GAIA_SOLOS_COMMANDS, bArr));
    }

    public void onRebootDevice(byte b, byte[] bArr) {
        if (b == 0) {
            this.mListener.onRebootDevice(CommandSpecification.GaiaSolosResponseStatus.SUCCESS, bArr);
        }
    }

    public void setAvEvents(boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        byte[] bArr = new byte[2];
        bArr[0] = CommandSpecification.GAIA_SOLOS_COMMANDS_SET_AV_EVENTS;
        if (z) {
            bArr[1] = (byte) (bArr[1] | 1);
        }
        if (z2) {
            bArr[1] = (byte) (bArr[1] | 2);
        }
        if (z3) {
            bArr[1] = (byte) (bArr[1] | 4);
        }
        if (z4) {
            bArr[1] = (byte) (bArr[1] | 8);
        }
        if (z5) {
            bArr[1] = (byte) (bArr[1] | Byte.MIN_VALUE);
        }
        createRequest(createPacket(CommandSpecification.GAIA_SOLOS_COMMANDS, bArr));
    }

    public void onSetAvEvents(byte b, byte[] bArr) {
        SolosGaiaPacketManager solosGaiaPacketManager;
        CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus2 = CommandSpecification.GaiaSolosResponseStatus.SUCCESS;
        CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus3 = CommandSpecification.GaiaSolosResponseStatus.SUCCESS;
        if (b != 0) {
            gaiaSolosResponseStatus2 = CommandSpecification.GaiaSolosResponseStatus.FAILED;
        } else if (bArr.length < 1) {
            gaiaSolosResponseStatus2 = CommandSpecification.GaiaSolosResponseStatus.INVALID_PAYLOAD_LENGTH;
        } else {
            byte b2 = bArr[0];
            boolean z6 = (b2 & 1) == 1;
            boolean z7 = (b2 & 2) == 2;
            boolean z8 = (b2 & 4) == 4;
            boolean z9 = (b2 & 8) == 8;
            if ((b2 & Byte.MIN_VALUE) == -128) {
                solosGaiaPacketManager = this;
                gaiaSolosResponseStatus = gaiaSolosResponseStatus3;
                z2 = z6;
                z = true;
            } else {
                solosGaiaPacketManager = this;
                gaiaSolosResponseStatus = gaiaSolosResponseStatus3;
                z = false;
                z2 = z6;
            }
            z3 = z7;
            z4 = z8;
            z5 = z9;
            solosGaiaPacketManager.mListener.onSetAvEvents(gaiaSolosResponseStatus, z2, z3, z4, z5, z);
        }
        solosGaiaPacketManager = this;
        gaiaSolosResponseStatus = gaiaSolosResponseStatus2;
        z2 = false;
        z3 = false;
        z4 = false;
        z5 = false;
        z = false;
        solosGaiaPacketManager.mListener.onSetAvEvents(gaiaSolosResponseStatus, z2, z3, z4, z5, z);
    }

    public void getAvEvents() {
        createRequest(createPacket(CommandSpecification.GAIA_SOLOS_COMMANDS, new byte[]{CommandSpecification.GAIA_SOLOS_COMMANDS_GET_AV_EVENTS}));
    }

    public void onGetAvEvents(byte b, byte[] bArr) {
        SolosGaiaPacketManager solosGaiaPacketManager;
        CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus2 = CommandSpecification.GaiaSolosResponseStatus.SUCCESS;
        CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus3 = CommandSpecification.GaiaSolosResponseStatus.SUCCESS;
        if (b != 0) {
            gaiaSolosResponseStatus2 = CommandSpecification.GaiaSolosResponseStatus.FAILED;
        } else if (bArr.length < 1) {
            gaiaSolosResponseStatus2 = CommandSpecification.GaiaSolosResponseStatus.INVALID_PAYLOAD_LENGTH;
        } else {
            byte b2 = bArr[0];
            boolean z6 = (b2 & 1) == 1;
            boolean z7 = (b2 & 2) == 2;
            boolean z8 = (b2 & 4) == 4;
            boolean z9 = (b2 & 8) == 8;
            if ((b2 & Byte.MIN_VALUE) == -128) {
                solosGaiaPacketManager = this;
                gaiaSolosResponseStatus = gaiaSolosResponseStatus3;
                z2 = z6;
                z = true;
            } else {
                solosGaiaPacketManager = this;
                gaiaSolosResponseStatus = gaiaSolosResponseStatus3;
                z = false;
                z2 = z6;
            }
            z3 = z7;
            z4 = z8;
            z5 = z9;
            solosGaiaPacketManager.mListener.onGetAvEvents(gaiaSolosResponseStatus, z2, z3, z4, z5, z);
        }
        solosGaiaPacketManager = this;
        gaiaSolosResponseStatus = gaiaSolosResponseStatus2;
        z2 = false;
        z3 = false;
        z4 = false;
        z5 = false;
        z = false;
        solosGaiaPacketManager.mListener.onGetAvEvents(gaiaSolosResponseStatus, z2, z3, z4, z5, z);
    }

    public void getAvInfo() {
        createRequest(createPacket(CommandSpecification.GAIA_SOLOS_COMMANDS, new byte[]{CommandSpecification.GAIA_SOLOS_COMMANDS_GET_AV_INFO}));
    }

    public void onGetAvInfo(byte b, byte[] bArr) {
        CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus;
        if (b == 0) {
            gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.SUCCESS;
        } else {
            gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.FAILED;
        }
        this.mListener.onGetAvInfo(gaiaSolosResponseStatus);
    }

    public void setAvControl(byte b) {
        createRequest(createPacket(GAIA.COMMAND_AV_REMOTE_CONTROL, new byte[]{b}));
    }

    public void onSetAvControl(GaiaPacket gaiaPacket) {
        CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus;
        byte[] payload = gaiaPacket.getPayload();
        if (payload.length >= 1) {
            if (payload[0] == 0) {
                gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.SUCCESS;
            } else {
                gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.FAILED;
            }
            this.mListener.onSetAvControl(gaiaSolosResponseStatus);
        }
    }

    public void onReadAvInfoEvents(byte b, byte[] bArr) {
        if (b != 0 || bArr.length < 2 || bArr[1] <= 0) {
            return;
        }
        CommandSpecification.GaiaSolosResponseStatus gaiaSolosResponseStatus = CommandSpecification.GaiaSolosResponseStatus.SUCCESS;
        byte b2 = bArr[0];
        byte[] bArr2 = new byte[bArr[1]];
        System.arraycopy(bArr, 2, bArr2, 0, bArr.length - 2);
        this.mListener.onReadAvInfoEvents(gaiaSolosResponseStatus, b2, bArr2);
    }

    public void getWhisperDataRtOutGain() {
        readConfigParam(0, (byte) 16, 2);
    }

    public void getWhisperDataRtOutFlag() {
        readConfigParam(0, (byte) 16, 3);
    }

    public void setWhisperDataRtOutGain(long j) {
        writeConfigParam32Bits(0, (byte) 16, (byte) 2, j);
    }

    public void setWhisperDataRtOutFlag(long j) {
        writeConfigParam32Bits(0, (byte) 16, (byte) 3, j);
    }

    public void onDeviceDisconnected() {
        reset();
        this.mTouchFw.onSolosDeviceDisconnected();
    }
}
