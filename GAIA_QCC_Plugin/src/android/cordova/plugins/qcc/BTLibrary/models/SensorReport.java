package cordova.plugins.qcc.BTLibrary.models;

/* loaded from: classes2.dex */
public class SensorReport {
    public static final int EVENT_TYPE_DOUBLE_PRESS = 5;
    public static final int EVENT_TYPE_DOUBLE_TAP = 1;
    public static final int EVENT_TYPE_FORWARD_SLIDE = 2;
    public static final int EVENT_TYPE_REVERSE_SLIDE = 3;
    public static final int EVENT_TYPE_SINGLE_PRESS = 4;
    public static final int EVENT_TYPE_SINGLE_TAP = 0;
    public static final int EVENT_TYPE_SLIDE_PRESS = 6;
    public static final int EVENT_TYPE_SLIDE_RELEASE = 7;
    public static final int EVENT_TYPE_UNKNOWN = 1000;
    public static final int EVENT_TYPE_VB_PRESS = 8;
    public static final int EVENT_TYPE_VB_RELEASE = 9;
    public static final byte paramCategory = 3;
    public boolean[] reportEnable1 = new boolean[8];
    public boolean[] reportEnable2 = new boolean[6];
    public int[] acc = new int[3];
    public int[] gyro = new int[3];
    public int[] magnet = new int[3];
    public int[] vad = new int[1];

    public SensorReport() {
        for (int i = 0; i < 8; i++) {
            this.reportEnable1[i] = false;
        }
        for (int i2 = 0; i2 < 5; i2++) {
            this.reportEnable2[i2] = false;
        }
        for (int i3 = 0; i3 < 3; i3++) {
            this.acc[i3] = 0;
        }
        for (int i4 = 0; i4 < 3; i4++) {
            this.gyro[i4] = 0;
        }
        for (int i5 = 0; i5 < 3; i5++) {
            this.magnet[i5] = 0;
        }
    }

    public void setAcceleration(boolean z) {
        this.reportEnable1[0] = z;
    }

    public void setGyro(boolean z) {
        this.reportEnable1[1] = z;
    }

    public void setMagnet(boolean z) {
        this.reportEnable1[2] = z;
    }

    public void setTouch(boolean z) {
        this.reportEnable1[3] = z;
    }

    public void setProximity(boolean z) {
        this.reportEnable1[4] = z;
    }

    public void setSingleTap(boolean z) {
        this.reportEnable1[5] = z;
    }

    public void setDoubleTap(boolean z) {
        this.reportEnable1[6] = z;
    }

    public void setButton(boolean z) {
        this.reportEnable1[7] = z;
    }

    public void setStepCount(boolean z) {
        this.reportEnable2[0] = z;
    }

    public void setFallDetection(boolean z) {
        this.reportEnable2[1] = z;
    }

    public void setActivityDetection(boolean z) {
        this.reportEnable2[2] = z;
    }

    public void setSignificantMotionDetection(boolean z) {
        this.reportEnable2[3] = z;
    }

    public void setOrientationDetection(boolean z) {
        this.reportEnable2[4] = z;
    }

    public void setVad(boolean z) {
        this.reportEnable2[5] = z;
    }

    public void setSensorsEnable16BitsUnsigned(int i) {
        int i2 = 0;
        while (true) {
            boolean z = true;
            if (i2 >= 8) {
                break;
            }
            boolean[] zArr = this.reportEnable1;
            if ((i & 1) != 1) {
                z = false;
            }
            zArr[i2] = z;
            i >>= 1;
            i2++;
        }
        for (int i3 = 0; i3 < 5; i3++) {
            this.reportEnable2[i3] = (i & 1) == 1;
            i >>= 1;
        }
    }

    public boolean isAccelerationAvail() {
        return this.reportEnable1[0];
    }

    public boolean isGyroAvail() {
        return this.reportEnable1[1];
    }

    public boolean isMagnetAvail() {
        return this.reportEnable1[2];
    }

    public boolean isTouchAvail() {
        return this.reportEnable1[3];
    }

    public boolean isProximityAvail() {
        return this.reportEnable1[4];
    }

    public boolean isSingleTapAvail() {
        return this.reportEnable1[5];
    }

    public boolean isDoubleTapAvail() {
        return this.reportEnable1[6];
    }

    public boolean isButtonAvail() {
        return this.reportEnable1[7];
    }

    public boolean isStepCountAvail() {
        return this.reportEnable2[0];
    }

    public boolean isFallDetectionAvail() {
        return this.reportEnable2[1];
    }

    public boolean isActivityDetectionAvail() {
        return this.reportEnable2[2];
    }

    public boolean isSignificantMotionDetectionAvail() {
        return this.reportEnable2[3];
    }

    public boolean isOrientationDetectionAvail() {
        return this.reportEnable2[4];
    }

    public boolean isVadAvail() {
        return this.reportEnable2[5];
    }

    public int getPackedData() {
        int i = 1;
        int i2 = 0;
        for (int i3 = 0; i3 < 8; i3++) {
            if (this.reportEnable1[i3]) {
                i2 |= i;
            }
            i <<= 1;
        }
        for (int i4 = 0; i4 < 6; i4++) {
            if (this.reportEnable2[i4]) {
                i2 |= i;
            }
            i <<= 1;
        }
        return i2;
    }

    public void setSensorReport(SensorReport sensorReport) {
        setAcceleration(sensorReport.isAccelerationAvail());
        setGyro(sensorReport.isGyroAvail());
        setMagnet(sensorReport.isMagnetAvail());
        setTouch(sensorReport.isTouchAvail());
        setProximity(sensorReport.isProximityAvail());
        setSingleTap(sensorReport.isSingleTapAvail());
        setDoubleTap(sensorReport.isDoubleTapAvail());
        setButton(sensorReport.isButtonAvail());
        setStepCount(sensorReport.isStepCountAvail());
        setFallDetection(sensorReport.isFallDetectionAvail());
        setActivityDetection(sensorReport.isActivityDetectionAvail());
        setSignificantMotionDetection(sensorReport.isSignificantMotionDetectionAvail());
        setOrientationDetection(sensorReport.isOrientationDetectionAvail());
    }
}
