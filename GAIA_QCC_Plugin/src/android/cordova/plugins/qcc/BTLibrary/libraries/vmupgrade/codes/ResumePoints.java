package cordova.plugins.qcc.BTLibrary.libraries.vmupgrade.codes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes2.dex */
public final class ResumePoints {
    private static final int RESUME_POINTS_COUNT = 5;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface Enum {
        public static final byte COMMIT = 4;
        public static final byte DATA_TRANSFER = 0;
        public static final byte IN_PROGRESS = 3;
        public static final byte TRANSFER_COMPLETE = 2;
        public static final byte VALIDATION = 1;
    }

    public static String getLabel(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? "Initialisation" : "Upgrade commit" : "Upgrade in progress" : "Data transfer complete" : "Data validation" : "Data transfer";
    }

    public static int getLength() {
        return 5;
    }

    public static int getResumePoint(byte b) {
        int i = 1;
        if (b != 1) {
            i = 2;
            if (b != 2) {
                i = 3;
                if (b != 3) {
                    i = 4;
                    if (b != 4) {
                        return 0;
                    }
                }
            }
        }
        return i;
    }
}
