package cordova.plugins.qcc.GaiaLibrary.gaia;

import android.util.Log;
import cordova.plugins.qcc.BTLibrary.Utils;
import cordova.plugins.qcc.BTLibrary.models.equalizer.parameters.Filter;
import cordova.plugins.qcc.BTLibrary.models.equalizer.parameters.ParameterType;
import cordova.plugins.qcc.GaiaLibrary.gaia.packets.GaiaPacket;

/* loaded from: classes2.dex */
public class CustomEqualizerGaiaManager extends AGaiaManager {
    private static final boolean DEBUG = false;
    private static final int EQ_PARAMETER_FIRST_BYTE = 1;
    public static final int GENERAL_BAND = 0;
    private static final int GET_EQ_PARAMETER_PAYLOAD_LENGTH = 5;
    public static final int PARAMETER_MASTER_GAIN = 1;
    private final String TAG;
    private boolean isBank1Selected;
    private final OnSolosGaiaCommandResultListener mListener;

    private int buildParameterIDLowByte(int i, int i2) {
        return (i << 4) | i2;
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

    public CustomEqualizerGaiaManager(OnSolosGaiaCommandResultListener onSolosGaiaCommandResultListener, int i) {
        super(i);
        this.TAG = "CustomEQGaiaManager";
        this.isBank1Selected = false;
        this.mListener = onSolosGaiaCommandResultListener;
    }

    public void getMasterGain() {
        getEQParameter(0, 1);
    }

    public void getEQParameter(int i, int i2) {
        createRequest(createPacket(GAIA.COMMAND_GET_EQ_PARAMETER, new byte[]{1, (byte) buildParameterIDLowByte(i, i2)}));
    }

    public void setEQParameter(int i, int i2, int i3) {
        byte[] var4 = new byte[]{1, (byte)this.buildParameterIDLowByte(i, i2), 0, 0, 0};
        Utils.copyIntIntoByteArray(i3, var4, 2, 2, false);
        var4[4] =  (byte) (this.isBank1Selected ? 1 : 0 );
        this.createRequest(this.createPacket(538, var4));
    }

    public void getPreset() {
        createRequest(createPacket(GAIA.COMMAND_GET_EQ_CONTROL));
    }

    public void onSetEQParameter(byte[] bArr) {
        if (bArr.length == 1) {
            this.mListener.onSetEQParameter();
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.GaiaManager
    protected void receiveSuccessfulAcknowledgement(GaiaPacket gaiaPacket) {
        int command = gaiaPacket.getCommand();
        if (command == 538) {
            onSetEQParameter(gaiaPacket.getPayload());
        } else if (command == 660) {
            receiveGetEQControlACK(gaiaPacket);
        } else {
            if (command != 666) {
                return;
            }
            receiveGetEQParameterACK(gaiaPacket);
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.GaiaManager
    protected void receiveUnsuccessfulAcknowledgement(GaiaPacket gaiaPacket) {
        if (gaiaPacket.getStatus() == 6) {
            this.mListener.onIncorrectState();
        } else {
            this.mListener.onControlNotSupported();
        }
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.GaiaManager
    protected boolean sendGAIAPacket(byte[] bArr) {
        return this.mListener.sendGAIAPacket(bArr);
    }

    private void receiveGetEQParameterACK(GaiaPacket gaiaPacket) {
        byte[] payload = gaiaPacket.getPayload();
        if (payload.length < 5) {
            Log.w("CustomEQGaiaManager", "Received \"COMMAND_GET_EQ_PARAMETER\" packet with missing arguments.");
            return;
        }
        byte b = payload[2];
        int i = (b & 240) >>> 4;
        int i2 = b & 15;
        if (i == 0 && i2 == 1) {
            this.mListener.onGetMasterGain(Utils.extractShortFromByteArray(payload, 3, 2, false));
            return;
        }
        ParameterType valueOf = ParameterType.valueOf(i2);
        if (valueOf == null) {
            Log.w("CustomEQGaiaManager", "Received \"COMMAND_GET_EQ_PARAMETER\" packet with an unknown parameter type: " + i2);
            return;
        }
        int i3 = C16371.f299x71ddde3b[valueOf.ordinal()];
        if (i3 == 1) {
            int extractIntFromByteArray = Utils.extractIntFromByteArray(payload, 3, 2, false);
            Filter valueOf2 = Filter.valueOf(extractIntFromByteArray);
            if (valueOf2 == null) {
                Log.w("CustomEQGaiaManager", "Received \"COMMAND_GET_EQ_PARAMETER\" packet with an unknown filter type: " + extractIntFromByteArray);
                return;
            } else {
                this.mListener.onGetFilter(i, valueOf2);
                return;
            }
        }
        if (i3 == 2) {
            this.mListener.onGetFrequency(i, Utils.extractIntFromByteArray(payload, 3, 2, false));
        } else if (i3 == 3) {
            this.mListener.onGetGain(i, Utils.extractIntFromByteArray(payload, 3, 2, false));
        } else {
            if (i3 != 4) {
                return;
            }
            this.mListener.onGetQuality(i, Utils.extractIntFromByteArray(payload, 3, 2, false));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: cordova.plugins.qcc.GaiaLibrary.gaia.CustomEqualizerGaiaManager$1 */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class C16371 {

        /* renamed from: $SwitchMap$cordova$plugins$qcc$BTLibrary$models$equalizer$parameters$ParameterType */
        static final /* synthetic */ int[] f299x71ddde3b;

        static {
            int[] iArr = new int[ParameterType.values().length];
            f299x71ddde3b = iArr;
            try {
                iArr[ParameterType.FILTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f299x71ddde3b[ParameterType.FREQUENCY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f299x71ddde3b[ParameterType.GAIN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f299x71ddde3b[ParameterType.QUALITY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private void receiveGetEQControlACK(GaiaPacket gaiaPacket) {
        byte[] payload = gaiaPacket.getPayload();
        if (payload.length >= 2) {
            this.isBank1Selected = payload[1] == 1;
        }
    }
}
