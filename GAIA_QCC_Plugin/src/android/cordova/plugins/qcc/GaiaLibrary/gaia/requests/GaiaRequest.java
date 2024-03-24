package cordova.plugins.qcc.GaiaLibrary.gaia.requests;

import cordova.plugins.qcc.GaiaLibrary.gaia.packets.GaiaPacket;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes2.dex */
public class GaiaRequest {
    public GaiaPacket packet;
    public final int type;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface Type {
        public static final int ACKNOWLEDGEMENT = 2;
        public static final int SINGLE_REQUEST = 1;
        public static final int UNACKNOWLEDGED_REQUEST = 3;
    }

    public GaiaRequest(int i) {
        this.type = i;
    }
}
