package cordova.plugins.qcc.BTLibrary.libraries.vmupgrade.packet;

import android.util.Log;
import cordova.plugins.qcc.BTLibrary.libraries.vmupgrade.VMUUtils;
import cordova.plugins.qcc.BTLibrary.libraries.vmupgrade.codes.OpCodes;

/* loaded from: classes2.dex */
public class VMUPacket {
    private static final int DATA_OFFSET = 3;
    private static final int LENGTH_LENGTH = 2;
    private static final int LENGTH_OFFSET = 1;
    private static final int OPCODE_LENGTH = 1;
    private static final int OPCODE_OFFSET = 0;
    public static final int REQUIRED_INFORMATION_LENGTH = 3;
    private final String TAG;
    private final byte[] mData;
    private final int mOpCode;

    public VMUPacket(int i, byte[] bArr) {
        this.TAG = "VMUPacket";
        this.mOpCode = i;
        if (bArr != null) {
            this.mData = bArr;
        } else {
            this.mData = new byte[0];
        }
    }

    public VMUPacket(int i) {
        this.TAG = "VMUPacket";
        this.mOpCode = i;
        this.mData = new byte[0];
    }

    public VMUPacket(byte[] bArr) throws VMUException {
        this.TAG = "VMUPacket";
        if (bArr.length >= 3) {
            this.mOpCode = OpCodes.getOpCode(bArr[0]);
            int extractIntFromByteArray = VMUUtils.extractIntFromByteArray(bArr, 1, 2, false);
            int length = bArr.length - 3;
            if (extractIntFromByteArray > length) {
                Log.w("VMUPacket", "Building packet: the LENGTH (" + extractIntFromByteArray + ") is bigger than the DATA length(" + length + ").");
            } else if (extractIntFromByteArray < length) {
                Log.w("VMUPacket", "Building packet: the LENGTH (" + extractIntFromByteArray + ") is smaller than the DATA length(" + length + ").");
            }
            byte[] bArr2 = new byte[length];
            this.mData = bArr2;
            if (length > 0) {
                System.arraycopy(bArr, 3, bArr2, 0, length);
                return;
            }
            return;
        }
        throw new VMUException(0, bArr);
    }

    public byte[] getBytes() {
        byte[] bArr = this.mData;
        byte[] bArr2 = new byte[bArr.length + 3];
        bArr2[0] = (byte) this.mOpCode;
        VMUUtils.copyIntIntoByteArray(bArr.length, bArr2, 1, 2, false);
        byte[] bArr3 = this.mData;
        if (bArr3.length > 0) {
            System.arraycopy(bArr3, 0, bArr2, 3, bArr3.length);
        }
        return bArr2;
    }

    public int getOpCode() {
        return this.mOpCode;
    }

    public int getLength() {
        return this.mData.length;
    }

    public byte[] getData() {
        return this.mData;
    }
}
