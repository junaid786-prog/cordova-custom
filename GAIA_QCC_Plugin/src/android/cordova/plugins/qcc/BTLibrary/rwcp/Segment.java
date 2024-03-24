package cordova.plugins.qcc.BTLibrary.rwcp;

import android.util.Log;
import cordova.plugins.qcc.BTLibrary.Utils;
import cordova.plugins.qcc.BTLibrary.libraries.vmupgrade.VMUUtils;

/* loaded from: classes2.dex */
class Segment {
    private final String TAG;
    private byte[] mBytes;
    private final byte mHeader;
    private final int mOperationCode;
    private final byte[] mPayload;
    private final int mSequenceNumber;

    private static int getBits(byte b, int i, int i2) {
        return (b & (((1 << i2) - 1) << i)) >>> i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Segment(int i, int i2, byte[] bArr) {
        this.TAG = "Segment";
        this.mOperationCode = i;
        this.mSequenceNumber = i2;
        this.mPayload = bArr;
        this.mHeader = (byte) ((i << 6) | i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Segment(int i, int i2) {
        this(i, i2, new byte[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Segment(byte[] bArr) {
        this.TAG = "Segment";
        this.mBytes = bArr;
        if (bArr == null || bArr.length < 1) {
            Log.w("Segment", "Building of RWCP Segment failed: the byte array does not contain the minimum required information.\nbytes: " + (bArr != null ? VMUUtils.getHexadecimalStringFromBytes(bArr) : "null"));
            this.mOperationCode = -1;
            this.mSequenceNumber = -1;
            this.mHeader = (byte) -1;
            this.mPayload = bArr;
            return;
        }
        byte b = bArr[0];
        this.mHeader = b;
        this.mOperationCode = getBits(b, 6, 2);
        this.mSequenceNumber = getBits(b, 0, 6);
        byte[] bArr2 = new byte[bArr.length - 1];
        this.mPayload = bArr2;
        System.arraycopy(bArr, 1, bArr2, 0, bArr2.length);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getOperationCode() {
        return this.mOperationCode;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getSequenceNumber() {
        return this.mSequenceNumber;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] getPayload() {
        return this.mPayload;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] getBytes() {
        if (this.mBytes == null) {
            byte[] bArr = this.mPayload;
            int length = bArr == null ? 0 : bArr.length;
            byte[] bArr2 = new byte[length + 1];
            this.mBytes = bArr2;
            bArr2[0] = this.mHeader;
            if (length > 0) {
                System.arraycopy(bArr, 0, bArr2, 1, bArr.length);
            }
        }
        return this.mBytes;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte getHeader() {
        return this.mHeader;
    }

    public String toString() {
        return toString(false);
    }

    String toString(boolean z) {
        StringBuilder sb = new StringBuilder("[code=");
        sb.append(this.mOperationCode).append(", sequence=").append(this.mSequenceNumber);
        if (z) {
            sb.append(", payload=").append(Utils.getStringFromBytes(this.mPayload));
        }
        sb.append("]");
        return sb.toString();
    }
}
