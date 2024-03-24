package cordova.plugins.qcc.GaiaLibrary.gaia.requests;

/* loaded from: classes2.dex */
public class GaiaAcknowledgementRequest extends GaiaRequest {
    public final byte[] data;
    public final int status;

    public GaiaAcknowledgementRequest(int i, byte[] bArr) {
        super(2);
        this.status = i;
        this.data = bArr;
    }
}
