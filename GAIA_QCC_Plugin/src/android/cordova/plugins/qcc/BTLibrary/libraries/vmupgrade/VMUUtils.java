package cordova.plugins.qcc.BTLibrary.libraries.vmupgrade;

import cordova.plugins.qcc.BTLibrary.libraries.vmupgrade.packet.VMUException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/* loaded from: classes2.dex */
public final class VMUUtils {
    private static final int BITS_IN_BYTE = 8;
    private static final int BYTES_IN_INT = 4;
    private static final int BYTES_IN_LONG = 8;
    private static final int BYTES_IN_SHORT = 2;
    private static final String TAG = "VMUUtils";

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

    public static long extractLongFromByteArray(byte[] bArr, int i, int i2, boolean z) {
        if ((i2 > 8) || (i2 < 0)) {
            throw new IndexOutOfBoundsException("Length must be between 0 and 8");
        }
        int i3 = (i2 - 1) * 8;
        long j = 0;
        if (z) {
            for (int i4 = (i2 + i) - 1; i4 >= i; i4--) {
                j |= (bArr[i4] & 255) << i3;
                i3 -= 8;
            }
        } else {
            for (int i5 = i; i5 < i + i2; i5++) {
                j |= (bArr[i5] & 255) << i3;
                i3 -= 8;
            }
        }
        return j;
    }

    public static String getHexadecimalString(int i) {
        return String.format("0x%04X", Integer.valueOf(i & 65535));
    }

    public static String getHexadecimalStringTwoBytes(int i) {
        return String.format("0x%02X", Integer.valueOf(i & 255));
    }

    public static String getHexadecimalStringFromBytes(byte[] bArr) {
        if (bArr == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            sb.append(String.format("0x%02x ", Byte.valueOf(b)));
        }
        return sb.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x00c6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] getMD5FromFile(java.io.File r10) {
        /*
            java.lang.String r0 = "Exception: "
            java.lang.String r1 = "Exception occurs when tried to get MD5 check sum for file: "
            java.lang.String r2 = "VMUUtils"
            r3 = 0
            r4 = 0
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            r5.<init>(r10)     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            r4 = 1024(0x400, float:1.435E-42)
            byte[] r4 = new byte[r4]     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L5a
            java.lang.String r6 = "MD5"
            java.security.MessageDigest r6 = java.security.MessageDigest.getInstance(r6)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L5a
            r7 = r3
        L18:
            r8 = -1
            if (r7 == r8) goto L25
            int r7 = r5.read(r4)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L5a
            if (r7 <= 0) goto L18
            r6.update(r4, r3, r7)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L5a
            goto L18
        L25:
            byte[] r3 = r6.digest()     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L5a
            r5.close()     // Catch: java.lang.Exception -> L2d
            goto L56
        L2d:
            r4 = move-exception
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>(r1)
            java.lang.String r10 = r10.getName()
            java.lang.StringBuilder r10 = r5.append(r10)
            java.lang.String r10 = r10.toString()
            android.util.Log.w(r2, r10)
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>(r0)
            java.lang.String r0 = r4.getMessage()
            java.lang.StringBuilder r10 = r10.append(r0)
            java.lang.String r10 = r10.toString()
            android.util.Log.w(r2, r10)
        L56:
            return r3
        L57:
            r3 = move-exception
            r4 = r5
            goto Lc4
        L5a:
            r4 = move-exception
            goto L62
        L5c:
            r3 = move-exception
            goto Lc4
        L5e:
            r5 = move-exception
            r9 = r5
            r5 = r4
            r4 = r9
        L62:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L57
            r6.<init>()     // Catch: java.lang.Throwable -> L57
            java.lang.StringBuilder r6 = r6.append(r1)     // Catch: java.lang.Throwable -> L57
            java.lang.String r7 = r10.getName()     // Catch: java.lang.Throwable -> L57
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch: java.lang.Throwable -> L57
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Throwable -> L57
            android.util.Log.e(r2, r6)     // Catch: java.lang.Throwable -> L57
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L57
            r6.<init>()     // Catch: java.lang.Throwable -> L57
            java.lang.StringBuilder r6 = r6.append(r0)     // Catch: java.lang.Throwable -> L57
            java.lang.String r4 = r4.getMessage()     // Catch: java.lang.Throwable -> L57
            java.lang.StringBuilder r4 = r6.append(r4)     // Catch: java.lang.Throwable -> L57
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> L57
            android.util.Log.e(r2, r4)     // Catch: java.lang.Throwable -> L57
            byte[] r3 = new byte[r3]     // Catch: java.lang.Throwable -> L57
            if (r5 == 0) goto Lc3
            r5.close()     // Catch: java.lang.Exception -> L9a
            goto Lc3
        L9a:
            r4 = move-exception
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>(r1)
            java.lang.String r10 = r10.getName()
            java.lang.StringBuilder r10 = r5.append(r10)
            java.lang.String r10 = r10.toString()
            android.util.Log.w(r2, r10)
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>(r0)
            java.lang.String r0 = r4.getMessage()
            java.lang.StringBuilder r10 = r10.append(r0)
            java.lang.String r10 = r10.toString()
            android.util.Log.w(r2, r10)
        Lc3:
            return r3
        Lc4:
            if (r4 == 0) goto Lf3
            r4.close()     // Catch: java.lang.Exception -> Lca
            goto Lf3
        Lca:
            r4 = move-exception
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>(r1)
            java.lang.String r10 = r10.getName()
            java.lang.StringBuilder r10 = r5.append(r10)
            java.lang.String r10 = r10.toString()
            android.util.Log.w(r2, r10)
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>(r0)
            java.lang.String r0 = r4.getMessage()
            java.lang.StringBuilder r10 = r10.append(r0)
            java.lang.String r10 = r10.toString()
            android.util.Log.w(r2, r10)
        Lf3:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: cordova.plugins.qcc.BTLibrary.libraries.vmupgrade.VMUUtils.getMD5FromFile(java.io.File):byte[]");
    }

    public static byte[] getBytesFromFile(File file) throws VMUException {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            long length = file.length();
            int i = (int) length;
            if (i != length) {
                throw new VMUException(2);
            }
            byte[] bArr = new byte[i];
            int read = fileInputStream.read(bArr);
            fileInputStream.close();
            if (read != i && (read != -1 || i != Integer.MAX_VALUE)) {
                throw new VMUException(3);
            }
            return bArr;
        } catch (IOException e) {
            throw new VMUException(3, e.getMessage());
        }
    }

    public static void copyIntIntoByteArray(int i, byte[] bArr, int i2, int i3, boolean z) {
        int i4 = 0;
        if ((i3 < 0) | (i3 > 4)) {
            throw new IndexOutOfBoundsException("Length must be between 0 and 4");
        }
        if (bArr.length < i2 + i3) {
            throw new IndexOutOfBoundsException("The targeted location must be contained in the target array.");
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
}
