package cordova.plugins.qcc.BTLibrary.models;

import cordova.plugins.qcc.BTLibrary.Utils;

/* loaded from: classes2.dex */
public class DSPConfig {
    public int accelShiftX;
    public int accelShiftY;
    public int accelShiftZ;
    public int aecRefGain;
    public int agcNoiseLevelDnAlpha;
    public int agcNoiseLevelUpAlpha;
    public int bfOutGain;
    public long eqBand1Gain;
    public int eqBand2Gain;
    public int eqBand3Gain;
    public short eqDownlinkAGCEnable;
    public short eqDownlinkAGCPowerThreshold;
    public byte[] eqDownlinkBandGain = new byte[20];
    public short eqDownlinkDLGain;
    public short eqDownlinkMasterGain;
    public short eqDownlinkOutFlag;
    public int gyroShiftX;
    public int gyroShiftY;
    public int gyroShiftZ;
    public int hangOver;
    public int inGain;
    public int magnetMaxX;
    public int magnetMaxY;
    public int magnetMaxZ;
    public int magnetMinX;
    public int magnetMinY;
    public int magnetMinZ;
    public int magnetScaleX;
    public int magnetScaleY;
    public int magnetScaleZ;
    public int magnetShiftX;
    public int magnetShiftY;
    public int magnetShiftZ;
    public long mainGain;
    public int nearEndThreshold;
    public int outFlag;
    public int outGain;
    public long refGain;
    public int scHangOver;
    public long scvad;
    public int selectedChannel;
    public long vad;
    public short whisperEqOutFlag;
    public int whisperFeature;
    public int windNoiseThreshold;

    public byte[] getPackedWhisperData() {
        int i = this.inGain;
        int i2 = this.outGain;
        int i3 = this.outFlag;
        int i4 = this.selectedChannel;
        long j = this.mainGain;
        long j2 = this.refGain;
        long j3 = this.vad;
        long j4 = this.scvad;
        int i5 = this.hangOver;
        int i6 = this.scHangOver;
        int i7 = this.aecRefGain;
        return new byte[]{(byte) ((i >> 8) & 255), (byte) (i & 255), (byte) ((i2 >> 8) & 255), (byte) (i2 & 255), (byte) ((i3 >> 8) & 255), (byte) (i3 & 255), (byte) ((i4 >> 8) & 255), (byte) (i4 & 255), (byte) ((j >> 24) & 255), (byte) ((j >> 16) & 255), (byte) ((j >> 8) & 255), (byte) (j & 255), (byte) ((j2 >> 24) & 255), (byte) ((j2 >> 16) & 255), (byte) ((j2 >> 8) & 255), (byte) (j2 & 255), (byte) ((j3 >> 24) & 255), (byte) ((j3 >> 16) & 255), (byte) ((j3 >> 8) & 255), (byte) (j3 & 255), (byte) ((j4 >> 24) & 255), (byte) ((j4 >> 16) & 255), (byte) ((j4 >> 8) & 255), (byte) (j4 & 255), (byte) ((i5 >> 8) & 255), (byte) (i5 & 255), (byte) ((i6 >> 8) & 255), (byte) (i6 & 255), (byte) ((i7 >> 8) & 255), (byte) (i7 & 255)};
    }

    public byte[] getPackedWhisperHearableData() {
        int i = this.inGain;
        int i2 = this.outGain;
        int i3 = this.outFlag;
        int i4 = this.selectedChannel;
        long j = this.mainGain;
        long j2 = this.refGain;
        int i5 = this.nearEndThreshold;
        long j3 = this.eqBand1Gain;
        int i6 = this.eqBand2Gain;
        int i7 = this.eqBand3Gain;
        int i8 = this.bfOutGain;
        return new byte[]{(byte) ((i >> 8) & 255), (byte) (i & 255), (byte) ((i2 >> 8) & 255), (byte) (i2 & 255), (byte) ((i3 >> 8) & 255), (byte) (i3 & 255), (byte) ((i4 >> 8) & 255), (byte) (i4 & 255), (byte) ((j >> 24) & 255), (byte) ((j >> 16) & 255), (byte) ((j >> 8) & 255), (byte) (j & 255), (byte) ((j2 >> 24) & 255), (byte) ((j2 >> 16) & 255), (byte) ((j2 >> 8) & 255), (byte) (j2 & 255), (byte) ((i5 >> 8) & 255), (byte) (i5 & 255), (byte) ((j3 >> 8) & 255), (byte) (j3 & 255), (byte) ((i6 >> 8) & 255), (byte) (i6 & 255), (byte) ((i7 >> 8) & 255), (byte) (i7 & 255), (byte) ((i8 >> 8) & 255), (byte) (i8 & 255)};
    }

    public byte[] getPackedMagnetData() {
        int i = this.magnetMaxX;
        int i2 = this.magnetMaxY;
        int i3 = this.magnetMaxZ;
        int i4 = this.magnetMinX;
        int i5 = this.magnetMinY;
        int i6 = this.magnetMinZ;
        return new byte[]{(byte) ((i >> 8) & 255), (byte) (i & 255), (byte) ((i2 >> 8) & 255), (byte) (i2 & 255), (byte) ((i3 >> 8) & 255), (byte) (i3 & 255), (byte) ((i4 >> 8) & 255), (byte) (i4 & 255), (byte) ((i5 >> 8) & 255), (byte) (i5 & 255), (byte) ((i6 >> 8) & 255), (byte) (i6 & 255)};
    }

    public byte[] getPackedGetGAData() {
        int i = this.gyroShiftX;
        int i2 = this.gyroShiftY;
        int i3 = this.gyroShiftZ;
        int i4 = this.accelShiftX;
        int i5 = this.accelShiftY;
        int i6 = this.accelShiftZ;
        int i7 = this.magnetShiftX;
        int i8 = this.magnetShiftY;
        int i9 = this.magnetShiftZ;
        int i10 = this.magnetScaleX;
        int i11 = this.magnetScaleY;
        int i12 = this.magnetScaleZ;
        return new byte[]{(byte) ((i >> 8) & 255), (byte) (i & 255), (byte) ((i2 >> 8) & 255), (byte) (i2 & 255), (byte) ((i3 >> 8) & 255), (byte) (i3 & 255), (byte) ((i4 >> 8) & 255), (byte) (i4 & 255), (byte) ((i5 >> 8) & 255), (byte) (i5 & 255), (byte) ((i6 >> 8) & 255), (byte) (i6 & 255), (byte) ((i7 >> 8) & 255), (byte) (i7 & 255), (byte) ((i8 >> 8) & 255), (byte) (i8 & 255), (byte) ((i9 >> 8) & 255), (byte) (i9 & 255), (byte) ((i10 >> 8) & 255), (byte) (i10 & 255), (byte) ((i11 >> 8) & 255), (byte) (i11 & 255), (byte) ((i12 >> 8) & 255), (byte) (i12 & 255)};
    }

    public byte[] getPackedWhisperDownlinkData() {
        byte[] bArr = new byte[30];
        System.arraycopy(this.eqDownlinkBandGain, 0, bArr, 0, 20);
        System.arraycopy(Utils.shortToBytes(this.eqDownlinkMasterGain), 0, bArr, 20, 2);
        System.arraycopy(Utils.shortToBytes(this.eqDownlinkAGCEnable), 0, bArr, 22, 2);
        System.arraycopy(Utils.shortToBytes(this.eqDownlinkAGCPowerThreshold), 0, bArr, 24, 2);
        System.arraycopy(Utils.shortToBytes(this.eqDownlinkOutFlag), 0, bArr, 26, 2);
        System.arraycopy(Utils.shortToBytes(this.eqDownlinkDLGain), 0, bArr, 28, 2);
        return bArr;
    }

    public byte[] getPackedWhisperEqData() {
        byte[] bArr = new byte[22];
        System.arraycopy(Utils.shortToBytes(this.whisperEqOutFlag), 0, bArr, 0, 2);
        System.arraycopy(this.eqDownlinkBandGain, 0, bArr, 2, 20);
        return bArr;
    }

    public void setDownlinkVoiceEQGain(int i, short s) {
        if (i > 10) {
            return;
        }
        byte[] bArr = this.eqDownlinkBandGain;
        int i2 = (i - 1) * 2;
        bArr[i2] = (byte) ((s >> 8) & 255);
        bArr[i2 + 1] = (byte) (s & 255);
    }

    public int getDownlinkVoiceEQGain(int i) {
        if (i > 10) {
            return 0;
        }
        byte[] bArr = this.eqDownlinkBandGain;
        int i2 = (i - 1) * 2;
        return bArr[i2 + 1] | (bArr[i2] << 8);
    }

    public byte[] getPackedWhisperFeature() {
        byte[] bArr = new byte[30];
        int i = this.whisperFeature;
        bArr[1] = (byte) (i & 255);
        bArr[0] = (byte) ((i >> 8) & 255);
        int i2 = this.windNoiseThreshold;
        bArr[3] = (byte) (i2 & 255);
        bArr[2] = (byte) ((i2 >> 8) & 255);
        int i3 = this.agcNoiseLevelUpAlpha;
        bArr[5] = (byte) (i3 & 255);
        bArr[4] = (byte) ((i3 >> 8) & 255);
        int i4 = this.agcNoiseLevelDnAlpha;
        bArr[7] = (byte) (i4 & 255);
        bArr[6] = (byte) ((i4 >> 8) & 255);
        return bArr;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
