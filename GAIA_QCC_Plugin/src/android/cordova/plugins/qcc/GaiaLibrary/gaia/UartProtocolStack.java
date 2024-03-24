package cordova.plugins.qcc.GaiaLibrary.gaia;

import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class UartProtocolStack {
    WeakReference<UartWriteImp> mUartWriteImp;
    private ArrayList<UartProtocolStackListener> listeners = new ArrayList<>();
    private List<Byte> decodedList = new ArrayList();
    private boolean escapeByteRead = false;
    private boolean isStartByte = false;

    /* loaded from: classes2.dex */
    public interface UartProtocolStackListener {
        void onCommandReceive(byte[] bArr);
    }

    /* loaded from: classes2.dex */
    public interface UartWriteImp {
        void EnableUART(boolean z);

        void UARTWrite(int i, byte[] bArr);
    }

    public UartProtocolStack(UartWriteImp uartWriteImp) {
        this.mUartWriteImp = new WeakReference<>(uartWriteImp);
    }

    public void encodeCmdTest() {
        byte[] bArr = {CommandSpecification.GAIA_SOLOS_COMMANDS_DOWNLOAD_WHOLE_DAY_STEP_COUNT_REPORT};
        byte[] bArr2 = {CommandSpecification.MEDIA_PAUSE, 12, 2, 12};
        byte[] bArr3 = {CommandSpecification.MEDIA_PAUSE, 1, 2, 3, 4, 5, 6, 2, 2, 2, 2, 2, 2, 2, 4, 5, 6, 7};
        byte[] encodeCommand = encodeCommand(bArr);
        byte[] encodeCommand2 = encodeCommand(bArr2);
        byte[] encodeCommand3 = encodeCommand(bArr3);
        for (byte b : encodeCommand) {
            Log.i("Encode CMD for test1", String.valueOf((int) b));
        }
        for (byte b2 : encodeCommand2) {
            Log.i("Encode CMD for test2", String.valueOf((int) b2));
        }
        for (byte b3 : encodeCommand3) {
            Log.i("Encode CMD for test3", String.valueOf((int) b3));
        }
    }

    public byte[] encodeCommand(byte[] bArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.add((byte) 12);
        int i = 0;
        for (byte b : bArr) {
            arrayList.add(Byte.valueOf(b));
            if (b == 12) {
                arrayList.add((byte) 12);
            }
        }
        arrayList.add((byte) 12);
        arrayList.add((byte) 13);
        Byte[] bArr2 = (Byte[]) arrayList.toArray(new Byte[arrayList.size()]);
        byte[] bArr3 = new byte[bArr2.length];
        int length = bArr2.length;
        int i2 = 0;
        while (i < length) {
            bArr3[i2] = bArr2[i].byteValue();
            i++;
            i2++;
        }
        return bArr3;
    }

    public void addListener(UartProtocolStackListener uartProtocolStackListener) {
        if (uartProtocolStackListener != null) {
            this.listeners.add(uartProtocolStackListener);
        }
    }

    public void delListener(UartProtocolStackListener uartProtocolStackListener) {
        if (uartProtocolStackListener != null) {
            this.listeners.remove(uartProtocolStackListener);
        }
    }

    public void decodeCmdTest() {
        byte[] onPacketReceive = onPacketReceive(new byte[]{12, 1, 2, 3, 12, 13});
        byte[] onPacketReceive2 = onPacketReceive(new byte[]{12, 1, 2, 12, 12, 4, 12, 12, 12, 13});
        byte[] onPacketReceive3 = onPacketReceive(new byte[]{12, 1, 2, 12, 3, 4, 12, 1, 12, 13});
        for (byte b : onPacketReceive) {
            Log.i("Decode CMD for test1", String.valueOf((int) b));
        }
        for (byte b2 : onPacketReceive2) {
            Log.i("Decode CMD for test2", String.valueOf((int) b2));
        }
        for (byte b3 : onPacketReceive3) {
            Log.i("Decode CMD for test3", String.valueOf((int) b3));
        }
    }

    public byte[] onPacketReceive(byte[] bArr) {
        int length = bArr.length;
        byte[] bArr2 = new byte[0];
        int i = 0;
        while (i < length) {
            int i2 = i + 1;
            byte b = bArr[i];
            if (this.escapeByteRead) {
                this.escapeByteRead = false;
                if (b == 12) {
                    this.escapeByteRead = true;
                    if (!this.isStartByte) {
                        this.isStartByte = true;
                    } else {
                        this.escapeByteRead = false;
                        this.decodedList.add(Byte.valueOf(b));
                    }
                } else if (b == 13) {
                    List<Byte> list = this.decodedList;
                    Byte[] bArr3 = (Byte[]) list.toArray(new Byte[list.size()]);
                    byte[] bArr4 = new byte[bArr3.length];
                    int length2 = bArr3.length;
                    int i3 = 0;
                    int i4 = 0;
                    while (i3 < length2) {
                        bArr4[i4] = bArr3[i3].byteValue();
                        i3++;
                        i4++;
                    }
                    ArrayList<UartProtocolStackListener> arrayList = this.listeners;
                    if (arrayList != null) {
                        Iterator<UartProtocolStackListener> it = arrayList.iterator();
                        while (it.hasNext()) {
                            it.next().onCommandReceive(bArr4);
                        }
                    }
                    this.isStartByte = false;
                    this.decodedList.clear();
                    bArr2 = bArr4;
                } else {
                    if (this.isStartByte) {
                        this.decodedList.clear();
                    }
                    this.isStartByte = true;
                    this.decodedList.add(Byte.valueOf(b));
                }
            } else if (b == 12) {
                this.escapeByteRead = true;
            } else if (this.isStartByte) {
                this.decodedList.add(Byte.valueOf(b));
            }
            i = i2;
        }
        return bArr2;
    }

    public void sendCommand(byte[] bArr) {
        byte[] encodeCommand = encodeCommand(bArr);
        int length = encodeCommand.length;
        int i = 0;
        while (i < length) {
            int i2 = 16;
            if (length <= 16) {
                i2 = length;
            } else if (length - i <= 16) {
                i2 = length % 16;
            }
            byte[] bArr2 = new byte[i2];
            System.arraycopy(encodeCommand, i, bArr2, 0, i2);
            this.mUartWriteImp.get().UARTWrite(i2, bArr2);
            i += i2;
        }
    }
}
