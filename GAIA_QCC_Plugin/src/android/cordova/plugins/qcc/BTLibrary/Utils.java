package cordova.plugins.qcc.BTLibrary;

import java.nio.ByteBuffer;
import java.text.DecimalFormat;

/* loaded from: classes2.dex */
public class Utils {
    private static final int BITS_IN_BYTE = 8;
    public static final int BITS_IN_HEXADECIMAL = 4;
    public static final int BYTES_IN_INT = 4;
    private static final int BYTES_IN_SHORT = 2;
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat();

    public static String getStringFromBytes(byte[] bArr) {
        if (bArr == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            sb.append(String.format("0x%02x ", Byte.valueOf(b)));
        }
        return sb.toString();
    }

    public static void copyIntIntoByteArray(int i, byte[] bArr, int i2, int i3, boolean z) {
        int i4 = 0;
        if ((i3 < 0) || (i3 > 4)) {
            throw new IndexOutOfBoundsException("Length must be between 0 and 4");
        }
        if (!z) {
            int i5 = (i3 - 1) * 8;
            while (i4 < i3) {
                bArr[i4 + i2] = (byte) (((255 << i5) & i) >> i5);
                i5 -= 8;
                i4++;
            }
            return;
        }
        int i6 = 0;
        for (int i7 = i3 - 1; i7 >= 0; i7--) {
            bArr[i6 + i2] = (byte) (((255 << i4) & i) >> i4);
            i4 += 8;
            i6++;
        }
    }

    public static short extractShortFromByteArray(byte[] bArr, int i, int i2, boolean z) {
        short s = 0;
        if ((i2 < 0) || (i2 > 2)) {
            throw new IndexOutOfBoundsException("Length must be between 0 and 2");
        }
        int i3 = (i2 - 1) * 8;
        if (z) {
            for (int i4 = (i2 + i) - 1; i4 >= i; i4--) {
                s = (short) (((bArr[i4] & 255) << i3) | s);
                i3 -= 8;
            }
        } else {
            for (int i5 = i; i5 < i + i2; i5++) {
                s = (short) (s | ((bArr[i5] & 255) << i3));
                i3 -= 8;
            }
        }
        return s;
    }

    public static int extractIntFromByteArray(byte[] bArr, int i, int i2, boolean z) {
        int i3 = 0;
        if ((i2 < 0) || (i2 > 4)) {
            throw new IndexOutOfBoundsException("Length must be between 0 and 4");
        }
        int i4 = (i2 - 1) * 8;
        if (z) {
            for (int i5 = (i2 + i) - 1; i5 >= i; i5--) {
                i3 |= (bArr[i5] & 255) << i4;
                i4 -= 8;
            }
        } else {
            for (int i6 = i; i6 < i + i2; i6++) {
                i3 |= (bArr[i6] & 255) << i4;
                i4 -= 8;
            }
        }
        return i3;
    }

    public static String getStringForPercentage(double d) {
        if (d <= 1.0d) {
            DECIMAL_FORMAT.setMaximumFractionDigits(2);
        } else {
            DECIMAL_FORMAT.setMaximumFractionDigits(1);
        }
        return DECIMAL_FORMAT.format(d) + " %";
    }

    public static byte[] intToBytes(int i) {
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.putInt(i);
        return allocate.array();
    }

    public static byte[] shortToBytes(short s) {
        ByteBuffer allocate = ByteBuffer.allocate(2);
        allocate.putShort(s);
        return allocate.array();
    }

    public static int bytesToInt(byte[] bArr) {
        if (bArr.length < 4) {
            byte[] bArr2 = new byte[4];
            System.arraycopy(bArr, 0, bArr2, 4 - bArr.length, bArr.length);
            bArr = bArr2;
        }
        return ByteBuffer.wrap(bArr).getInt();
    }

    public static short bytesToShort(byte[] bArr) {
        if (bArr.length < 2) {
            byte[] bArr2 = new byte[2];
            System.arraycopy(bArr, 0, bArr2, 2 - bArr.length, bArr.length);
            bArr = bArr2;
        }
        return ByteBuffer.wrap(bArr).getShort();
    }
}
