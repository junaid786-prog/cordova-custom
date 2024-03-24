package cordova.plugins.qcc.BTLibrary.models.equalizer.parameters;

/* loaded from: classes2.dex */
public enum Filter {
    BYPASS,
    LOW_PASS_1,
    HIGH_PASS_1,
    ALL_PASS_1,
    LOW_SHELF_1,
    HIGH_SHELF_1,
    TILT_1,
    LOW_PASS_2,
    HIGH_PASS_2,
    ALL_PASS_2,
    LOW_SHELF_2,
    HIGH_SHELF_2,
    TILT_2,
    PARAMETRIC_EQUALIZER;

    private static final Filter[] values = values();

    public static Filter valueOf(int i) {
        if (i < 0) {
            return null;
        }
        Filter[] filterArr = values;
        if (i >= filterArr.length) {
            return null;
        }
        return filterArr[i];
    }

    public static int getSize() {
        return values.length;
    }

    /* renamed from: cordova.plugins.qcc.BTLibrary.models.equalizer.parameters.Filter$1 */
    /* loaded from: classes2.dex */
    static /* synthetic */ class C16251 {

        /* renamed from: $SwitchMap$cordova$plugins$qcc$BTLibrary$models$equalizer$parameters$Filter */
        static final /* synthetic */ int[] f296x14fde6c0;

        static {
            int[] iArr = new int[Filter.values().length];
            f296x14fde6c0 = iArr;
            try {
                iArr[Filter.HIGH_PASS_1.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f296x14fde6c0[Filter.ALL_PASS_1.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f296x14fde6c0[Filter.LOW_PASS_1.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f296x14fde6c0[Filter.HIGH_PASS_2.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f296x14fde6c0[Filter.ALL_PASS_2.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f296x14fde6c0[Filter.LOW_PASS_2.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f296x14fde6c0[Filter.LOW_SHELF_1.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f296x14fde6c0[Filter.HIGH_SHELF_1.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f296x14fde6c0[Filter.TILT_1.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f296x14fde6c0[Filter.LOW_SHELF_2.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f296x14fde6c0[Filter.HIGH_SHELF_2.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f296x14fde6c0[Filter.TILT_2.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f296x14fde6c0[Filter.BYPASS.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f296x14fde6c0[Filter.PARAMETRIC_EQUALIZER.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
        }
    }

    public static void defineParameters(Filter filter, Parameter parameter, Parameter parameter2, Parameter parameter3) {
        switch (C16251.f296x14fde6c0[filter.ordinal()]) {
            case 1:
            case 2:
            case 3:
                parameter.setConfigurable(0.333d, 20000.0d);
                parameter2.setNotConfigurable();
                parameter3.setNotConfigurable();
                return;
            case 4:
            case 5:
            case 6:
                parameter.setConfigurable(40.0d, 20000.0d);
                parameter2.setNotConfigurable();
                parameter3.setConfigurable(0.25d, 2.0d);
                return;
            case 7:
            case 8:
            case 9:
                parameter.setConfigurable(20.0d, 20000.0d);
                parameter2.setConfigurable(-12.0d, 12.0d);
                parameter3.setNotConfigurable();
                return;
            case 10:
            case 11:
            case 12:
                parameter.setConfigurable(40.0d, 20000.0d);
                parameter2.setConfigurable(-12.0d, 12.0d);
                parameter3.setConfigurable(0.25d, 2.0d);
                return;
            case 13:
                parameter.setNotConfigurable();
                parameter2.setNotConfigurable();
                parameter3.setNotConfigurable();
                return;
            case 14:
                parameter.setConfigurable(20.0d, 20000.0d);
                parameter2.setConfigurable(-36.0d, 12.0d);
                parameter3.setConfigurable(0.25d, 8.0d);
                return;
            default:
                return;
        }
    }
}
