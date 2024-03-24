package cordova.plugins.qcc.BTLibrary.libraries.vmupgrade.packet;

import cordova.plugins.qcc.BTLibrary.libraries.vmupgrade.VMUUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes2.dex */
public class VMUException extends Exception {
    private final byte[] mBytes;
    private final String mMessage;
    private final int mType;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface Type {
        public static final int DATA_LENGTH_ERROR = 1;
        public static final int DATA_TOO_SHORT = 0;
        public static final int FILE_TOO_BIG = 2;
        public static final int GET_BYTES_FILE_FAILED = 3;
    }

    public VMUException(int i) {
        this.mType = i;
        this.mMessage = "";
        this.mBytes = new byte[0];
    }

    public VMUException(int i, String str) {
        this.mType = i;
        this.mMessage = str;
        this.mBytes = new byte[0];
    }

    public VMUException(int i, byte[] bArr) {
        this.mType = i;
        this.mMessage = "";
        this.mBytes = bArr;
    }

    public int getType() {
        return this.mType;
    }

    @Override // java.lang.Throwable
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = this.mType;
        if (i == 0) {
            sb.append("Build of a VMUPacket failed: the byte array does not contain the minimum required information\nReceived bytes: ");
            sb.append(VMUUtils.getHexadecimalStringFromBytes(this.mBytes));
        } else if (i == 2) {
            sb.append("Get file failed: The given file size is >= 2GB");
        } else if (i == 3) {
            sb.append("Get file failed");
            if (this.mMessage.length() > 0) {
                sb.append(": ").append(this.mMessage);
            }
        } else {
            sb.append("VMU Exception occurs");
        }
        return sb.toString();
    }
}
