package cordova.plugins.qcc.GaiaLibrary.gaia;

import android.util.Log;
import cordova.plugins.qcc.GaiaLibrary.gaia.packets.GaiaPacket;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes2.dex */
public class EqualizerGaiaManager extends AGaiaManager {
    public static final int CUSTOMIZABLE_PRESET = 1;
    private static final boolean DEBUG = false;
    public static final int NUMBER_OF_PRESETS = 7;
    private final String TAG;
    private final OnSolosGaiaCommandResultListener mListener;
    private static final byte[] PAYLOAD_BOOLEAN_TRUE = {1};
    private static final byte[] PAYLOAD_BOOLEAN_FALSE = {0};

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface Controls {
        public static final int BASS_BOOST = 2;
        public static final int ENHANCEMENT_3D = 1;
        public static final int PRESETS = 3;
    }

    /* loaded from: classes2.dex */
    public interface GaiaManagerListener {
        void onControlNotSupported(int i);

        void onGetControlActivationState(int i, boolean z);

        void onGetPreset(int i);

        boolean sendGAIAPacket(byte[] bArr);
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.GaiaManager
    protected void hasNotReceivedAcknowledgementPacket(GaiaPacket gaiaPacket) {
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.GaiaManager
    protected boolean manageReceivedPacket(GaiaPacket gaiaPacket) {
        return false;
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.GaiaManager
    protected void onSendingFailed(GaiaPacket gaiaPacket) {
    }

    public EqualizerGaiaManager(OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener, int i) {
        super(i);
        this.TAG = "EqualizerGaiaManager";
        this.mListener = onSolosGaiaCommandResultListener;
    }

    public void setPreset(int i) {
        if (i >= 0 && i < 7) {
            createRequest(createPacket(GAIA.COMMAND_SET_EQ_CONTROL, new byte[]{(byte) i}));
        } else {
            Log.w("EqualizerGaiaManager", "setPreset used with parameter not between 0 and 6, value: " + i);
        }
    }

    public void getPreset() {
        createRequest(createPacket(GAIA.COMMAND_GET_EQ_CONTROL));
    }

    public void getActivationState(int i) {
        if (i == 1) {
            createRequest(createPacket(GAIA.COMMAND_GET_3D_ENHANCEMENT_CONTROL));
        } else if (i == 2) {
            createRequest(createPacket(GAIA.COMMAND_GET_BASS_BOOST_CONTROL));
        } else {
            if (i != 3) {
                return;
            }
            createRequest(createPacket(GAIA.COMMAND_GET_USER_EQ_CONTROL));
        }
    }

    public void setActivationState(int i, boolean z) {
        byte[] bArr = z ? PAYLOAD_BOOLEAN_TRUE : PAYLOAD_BOOLEAN_FALSE;
        if (i == 1) {
            createRequest(createPacket(GAIA.COMMAND_SET_3D_ENHANCEMENT_CONTROL, bArr));
        } else if (i == 2) {
            createRequest(createPacket(GAIA.COMMAND_SET_BASS_BOOST_CONTROL, bArr));
        } else {
            if (i != 3) {
                return;
            }
            createRequest(createPacket(GAIA.COMMAND_SET_USER_EQ_CONTROL, bArr));
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.GaiaManager
    protected void receiveSuccessfulAcknowledgement(GaiaPacket gaiaPacket) {
        int command = gaiaPacket.getCommand();
        if (command == 544) {
            this.mListener.onSetUserEQControl();
            return;
        }
        if (command == 672) {
            receiveGetControlACK(3, gaiaPacket);
            return;
        }
        switch (command) {
            case GAIA.COMMAND_GET_EQ_CONTROL /* 660 */:
                receiveGetEQControlACK(gaiaPacket);
                return;
            case GAIA.COMMAND_GET_BASS_BOOST_CONTROL /* 661 */:
                receiveGetControlACK(2, gaiaPacket);
                return;
            case GAIA.COMMAND_GET_3D_ENHANCEMENT_CONTROL /* 662 */:
                receiveGetControlACK(1, gaiaPacket);
                return;
            default:
                return;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x000c. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x001a  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0013  */
    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.GaiaManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void receiveUnsuccessfulAcknowledgement(cordova.plugins.qcc.GaiaLibrary.gaia.packets.GaiaPacket r2) {
        /*
            r1 = this;
            int r2 = r2.getCommand()
            r0 = 544(0x220, float:7.62E-43)
            if (r2 == r0) goto L21
            r0 = 672(0x2a0, float:9.42E-43)
            if (r2 == r0) goto L21
            switch(r2) {
                case 532: goto L21;
                case 533: goto L1a;
                case 534: goto L13;
                default: goto Lf;
            }
        Lf:
            switch(r2) {
                case 660: goto L21;
                case 661: goto L1a;
                case 662: goto L13;
                default: goto L12;
            }
        L12:
            goto L27
        L13:
            cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener r2 = r1.mListener
            r0 = 1
            r2.onControlNotSupported(r0)
            goto L27
        L1a:
            cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener r2 = r1.mListener
            r0 = 2
            r2.onControlNotSupported(r0)
            goto L27
        L21:
            cordova.plugins.qcc.GaiaLibrary.gaia.OnSolosGaiaCommandResultListener r2 = r1.mListener
            r0 = 3
            r2.onControlNotSupported(r0)
        L27:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cordova.plugins.qcc.GaiaLibrary.gaia.EqualizerGaiaManager.receiveUnsuccessfulAcknowledgement(cordova.plugins.qcc.GaiaLibrary.gaia.packets.GaiaPacket):void");
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.GaiaManager
    protected boolean sendGAIAPacket(byte[] bArr) {
        return this.mListener.sendGAIAPacket(bArr);
    }

    private void receiveGetControlACK(int i, GaiaPacket gaiaPacket) {
        byte[] payload = gaiaPacket.getPayload();
        if (payload.length >= 2) {
            this.mListener.onGetControlActivationState(i, payload[1] == 1);
        }
    }

    private void receiveGetEQControlACK(GaiaPacket gaiaPacket) {
        byte[] payload = gaiaPacket.getPayload();
        if (payload.length >= 2) {
            this.mListener.onGetPreset(payload[1]);
        }
    }
}
