package cordova.plugins.qcc.BTLibrary.models.equalizer.parameters;

/* loaded from: classes2.dex */
public enum ParameterType {
    FILTER,
    FREQUENCY,
    GAIN,
    QUALITY;

    private static final ParameterType[] values = values();

    public static ParameterType valueOf(int i) {
        if (i < 0) {
            return null;
        }
        ParameterType[] parameterTypeArr = values;
        if (i >= parameterTypeArr.length) {
            return null;
        }
        return parameterTypeArr[i];
    }

    public static int getSize() {
        return values.length;
    }
}
