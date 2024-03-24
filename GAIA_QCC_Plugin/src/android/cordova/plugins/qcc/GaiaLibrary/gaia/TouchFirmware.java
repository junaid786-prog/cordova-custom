package cordova.plugins.qcc.GaiaLibrary.gaia;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

/* loaded from: classes2.dex */
public class TouchFirmware implements UartProtocolStack.UartProtocolStackListener {
    private static final short PAGE = 64;
    private static final String TAG = "TouchFirmware";
    private static final byte TOUCH_BOOTLOADER_DISABLE_RESPONSE = -1;
    private static final byte TOUCH_BOOTLOADER_ENABLE_RESPONSE = 11;
    private static final byte TOUCH_DEBUG_CMD_RESPONSE = 2;
    private static final byte TOUCH_FW_VERSION_RESPONSE = 86;
    private static final byte TOUCH_READ_CAP_RESPONSE = 7;
    private static final byte TOUCH_READ_DELTA_RESPONSE = 6;
    private static final byte TOUCH_READ_REF_RESPONSE = 5;
    private static final byte TOUCH_READ_SIGNAL_RESPONSE = 4;
    private static final byte TOUCH_SET_PAGE_NUM_RESPONSE = Byte.MIN_VALUE;
    private static final byte TOUCH_SYSTEM_RESET_RESPONSE = 3;
    private byte[][] binFile;
    UartProtocolStack mUart;
    private int pageNum;
    private int pageTotal;
    private static final byte[] TOUCH_FW_VERSION = {86};
    private static final byte[] TOUCH_BOOTLOADER_ENABLE = {CommandSpecification.MEDIA_PLAY, 2, 11};
    private static final byte[] TOUCH_SYSTEM_RESET = {CommandSpecification.MEDIA_PLAY, 3, 0};
    private static final byte[] TOUCH_BOOTLOADER_DISABLE = {CommandSpecification.MEDIA_PLAY, 2, -1};
    private static final byte[] TOUCH_SET_PAGE_NUM = {CommandSpecification.MEDIA_PLAY, Byte.MIN_VALUE};
    private static final byte TOUCH_SEND_DATA_TO_PAGE_RESPONSE = -127;
    private static final byte[] TOUCH_SEND_DATA = {CommandSpecification.MEDIA_PLAY, TOUCH_SEND_DATA_TO_PAGE_RESPONSE};
    private static final byte TOUCH_WRITE_TO_PAGE_RESPONSE = -126;
    private static final byte[] TOUCH_WRITE_TO_PAGE = {CommandSpecification.MEDIA_PLAY, TOUCH_WRITE_TO_PAGE_RESPONSE};
    private static final byte TOUCH_EXEC_APP_RESPONSE = -113;
    private static final byte[] TOUCH_EXEC_APP = {CommandSpecification.MEDIA_PLAY, TOUCH_EXEC_APP_RESPONSE};
    private static final byte TOUCH_GET_PAGE_CHECKSUM_RESPONSE = -124;
    private static final byte[] TOUCH_GET_PAGE_CHECKSUM = {CommandSpecification.MEDIA_PLAY, TOUCH_GET_PAGE_CHECKSUM_RESPONSE};
    private static final byte[] TOUCH_READ_SIGNAL = {CommandSpecification.MEDIA_PLAY, 4, 0};
    private static final byte[] TOUCH_READ_REF = {CommandSpecification.MEDIA_PLAY, 5, 0};
    private static final byte[] TOUCH_READ_DELTA = {CommandSpecification.MEDIA_PLAY, 6, 0};
    private static final byte[] TOUCH_READ_CAP = {CommandSpecification.MEDIA_PLAY, 7, 0};
    private ArrayList<TouchFirmwareListener> mListeners = new ArrayList<>();
    private LinkedList<Command> commandFifo = new LinkedList<>();
    private byte pageOffset = 0;
    private boolean bootloaderEnable = false;
    private boolean isVerifying = false;
    private boolean isUpdateStart = false;
    private int otaRetryCnt = 0;

    /* loaded from: classes2.dex */
    public interface Callback {
        void exec();
    }

    /* loaded from: classes2.dex */
    public interface ResponseValidation {
        boolean validate(byte[] bArr, byte[] bArr2);
    }

    /* loaded from: classes2.dex */
    public interface TouchFirmwareListener {
        void onFirmwareVerRead(byte b, byte b2, byte b3);

        void onOtaUpdateAbort();

        void onOtaUpdateComplete();

        void onOtaUpdateProgress(int i);

        void onOtaUpdateStart();

        void onOtaVerifyAbort();

        void onOtaVerifyComplete();

        void onOtaVerifyProgress(int i);

        void onOtaVerifyStart();
    }

    public TouchFirmware(UartProtocolStack uartProtocolStack) {
        this.mUart = uartProtocolStack;
        uartProtocolStack.addListener(this);
    }

    private void appendCommandToFifo(Command command) {
        this.commandFifo.addLast(command);
        if (this.commandFifo.size() == 1) {
            runFirstCommandInFifo();
        }
    }

    private void runFirstCommandInFifo() {
        if (this.commandFifo.size() > 0) {
            this.commandFifo.getFirst().exec();
        }
    }

    public void clearCommand() {
        if (this.commandFifo.size() > 0) {
            this.commandFifo.removeFirst().cleanup();
        }
    }

    private void onCommandResponse(byte[] bArr) {
        if (this.commandFifo.size() == 0) {
            Log.e(TAG, "commandFifo is empty, onCommandResponse: " + Arrays.toString(bArr));
        } else if (this.commandFifo.getFirst().validResponse(bArr)) {
            this.commandFifo.removeFirst().cleanup();
            runFirstCommandInFifo();
        }
    }

    public void addListener(TouchFirmwareListener touchFirmwareListener) {
        if (touchFirmwareListener != null) {
            this.mListeners.add(touchFirmwareListener);
        }
    }

    public void delListener(TouchFirmwareListener touchFirmwareListener) {
        if (touchFirmwareListener != null) {
            this.mListeners.remove(touchFirmwareListener);
        }
    }

    public void storeBinFileData(byte[] bArr) {
        int length = bArr.length;
        int i = length % 64;
        int i2 = length / 64;
        if (i != 0) {
            i2++;
        }
        this.pageTotal = i2;
        this.binFile = (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, i2, 64);
        int i3 = 0;
        for (int i4 = 0; i4 < this.pageTotal; i4++) {
            int i5 = 0;
            while (i5 < 64 && i3 < bArr.length) {
                this.binFile[i4][i5] = bArr[i3];
                i5++;
                i3++;
            }
        }
    }

    private short getPageChecksum() {
        short s = 0;
        for (int i = 0; i < 64; i++) {
            s = (short) (s + (this.binFile[this.pageNum][i] & 255));
        }
        return s;
    }

    private byte getOffsetChecksum() {
        byte[] bArr = new byte[16];
        System.arraycopy(this.binFile[this.pageNum], this.pageOffset, bArr, 0, 16);
        short s = 0;
        for (int i = 0; i < 16; i++) {
            s = (short) (s + bArr[i]);
        }
        return (byte) (s * (-1));
    }

    private byte[] getBinFilePageOffsetData(int i, byte b) {
        byte[] bArr = new byte[16];
        System.arraycopy(this.binFile[i], b, bArr, 0, 16);
        return bArr;
    }

    private void sendCommand(byte[] bArr, int i, Callback callback, byte[] bArr2, ResponseValidation responseValidation) {
        appendCommandToFifo(new RetryCommand(bArr, i, callback, bArr2, responseValidation));
    }

    private void sendCommand(byte[] bArr, int i, Callback callback, byte[] bArr2) {
        appendCommandToFifo(new RetryCommand(bArr, i, callback, bArr2));
    }

    private void sendCommand(byte[] bArr, int i, ResponseValidation responseValidation) {
        appendCommandToFifo(new DelayCommand(bArr, i, responseValidation));
    }

    private void sendCommand(byte[] bArr, int i) {
        appendCommandToFifo(new DelayCommand(bArr, i));
    }

    private void sendCommand(byte[] bArr) {
        appendCommandToFifo(new NoRetryCommand(bArr));
    }

    public void startOtaUpdate() {
        clearCommand();
        this.isUpdateStart = true;
        this.isVerifying = false;
        this.pageNum = 0;
        this.otaRetryCnt = 0;
        ArrayList<TouchFirmwareListener> arrayList = this.mListeners;
        if (arrayList != null) {
            Iterator<TouchFirmwareListener> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().onOtaUpdateStart();
            }
        }
        byte[] bArr = TOUCH_FW_VERSION;
        sendCommand(bArr, 3000, new TouchFirmware$$ExternalSyntheticLambda0(this), bArr);
    }

    private void retryOtaUpdate() {
        int i = this.otaRetryCnt;
        if (i > 2) {
            if (this.isUpdateStart) {
                onOtaUpdateAbort();
                return;
            } else {
                if (this.isVerifying) {
                    onOtaVerifyAbort();
                    return;
                }
                return;
            }
        }
        this.isUpdateStart = true;
        this.isVerifying = false;
        this.pageNum = 0;
        this.otaRetryCnt = i + 1;
        byte[] bArr = TOUCH_FW_VERSION;
        sendCommand(bArr, 3000, new TouchFirmware$$ExternalSyntheticLambda0(this), bArr);
    }

    private void setTouchPageNum() {
        short s = (short) (((this.pageNum * 64) + 1024) >> 6);
        byte[] bArr = TOUCH_SET_PAGE_NUM;
        System.arraycopy(bArr, 0, bArr, 0, bArr.length);
        byte[] bArr2 = {0, 0, (byte) (s >> 8), (byte) s};
        sendCommand(bArr2, 3000, new Callback() { // from class: cordova.plugins.qcc.GaiaLibrary.gaia.TouchFirmware$$ExternalSyntheticLambda2
            @Override // cordova.plugins.qcc.GaiaLibrary.gaia.TouchFirmware.Callback
            public final void exec() {
                TouchFirmware.this.lambda$setTouchPageNum$0();
            }
        }, bArr2);
    }

    public /* synthetic */ void lambda$setTouchPageNum$0() {
        if (this.isUpdateStart) {
            onOtaUpdateAbort();
        } else {
            onOtaVerifyAbort();
        }
    }

    public void readFirmwareVer() {
        byte[] bArr = TOUCH_FW_VERSION;
        sendCommand(bArr, 0, new Callback() { // from class: cordova.plugins.qcc.GaiaLibrary.gaia.TouchFirmware$$ExternalSyntheticLambda1
            @Override // cordova.plugins.qcc.GaiaLibrary.gaia.TouchFirmware.Callback
            public final void exec() {
                TouchFirmware.this.lambda$readFirmwareVer$1();
            }
        }, bArr);
    }

    public /* synthetic */ void lambda$readFirmwareVer$1() {
        ArrayList<TouchFirmwareListener> arrayList = this.mListeners;
        if (arrayList != null) {
            Iterator<TouchFirmwareListener> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().onFirmwareVerRead((byte) 0, (byte) 0, (byte) 0);
            }
        }
    }

    private void onUpdateProgress() {
        int i = (this.pageNum * 100) / this.pageTotal;
        ArrayList<TouchFirmwareListener> arrayList = this.mListeners;
        if (arrayList != null) {
            Iterator<TouchFirmwareListener> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().onOtaUpdateProgress(i);
            }
        }
    }

    private void onReadFirmwareVer(byte[] bArr) {
        byte[] bArr2 = new byte[3];
        System.arraycopy(bArr, 1, bArr2, 0, 3);
        this.bootloaderEnable = bArr2[0] == Byte.MIN_VALUE && bArr2[1] == 0 && bArr2[2] == 2;
        ArrayList<TouchFirmwareListener> arrayList = this.mListeners;
        if (arrayList != null) {
            Iterator<TouchFirmwareListener> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().onFirmwareVerRead(bArr2[0], bArr2[1], bArr2[2]);
            }
        }
    }

    private void onOtaUpdateComplete() {
        ArrayList<TouchFirmwareListener> arrayList;
        if (this.pageNum != this.pageTotal || (arrayList = this.mListeners) == null) {
            return;
        }
        Iterator<TouchFirmwareListener> it = arrayList.iterator();
        while (it.hasNext()) {
            it.next().onOtaUpdateComplete();
        }
    }

    public void onOtaUpdateAbort() {
        this.isUpdateStart = false;
        ArrayList<TouchFirmwareListener> arrayList = this.mListeners;
        if (arrayList != null) {
            Iterator<TouchFirmwareListener> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().onOtaUpdateAbort();
            }
        }
    }

    public void onOtaVerifyAbort() {
        this.isVerifying = false;
        ArrayList<TouchFirmwareListener> arrayList = this.mListeners;
        if (arrayList != null) {
            Iterator<TouchFirmwareListener> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().onOtaVerifyAbort();
            }
        }
    }

    private void onOtaVerifyProgress() {
        int i = (this.pageNum * 100) / this.pageTotal;
        ArrayList<TouchFirmwareListener> arrayList = this.mListeners;
        if (arrayList != null) {
            Iterator<TouchFirmwareListener> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().onOtaVerifyProgress(i);
            }
        }
    }

    private void onOtaVerifyStart() {
        this.isUpdateStart = false;
        this.isVerifying = true;
        this.pageNum = 0;
        ArrayList<TouchFirmwareListener> arrayList = this.mListeners;
        if (arrayList != null) {
            Iterator<TouchFirmwareListener> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().onOtaVerifyStart();
            }
        }
    }

    private void onOtaVerifyComplete() {
        this.isVerifying = false;
        ArrayList<TouchFirmwareListener> arrayList = this.mListeners;
        if (arrayList != null) {
            Iterator<TouchFirmwareListener> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().onOtaVerifyComplete();
            }
        }
    }

    private void setDataToPage() {
        byte[] binFilePageOffsetData = getBinFilePageOffsetData(this.pageNum, this.pageOffset);
        byte[] bArr = new byte[19];
        bArr[0] = CommandSpecification.MEDIA_PLAY;
        bArr[1] = TOUCH_SEND_DATA_TO_PAGE_RESPONSE;
        bArr[2] = this.pageOffset;
        System.arraycopy(binFilePageOffsetData, 0, bArr, 3, binFilePageOffsetData.length);
        sendCommand(bArr, 3000, new TouchFirmware$$ExternalSyntheticLambda0(this), bArr);
    }

    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.UartProtocolStack.UartProtocolStackListener
    public void onCommandReceive(byte[] bArr) {
        int length = bArr.length;
        if (length > 0) {
            onCommandResponse(bArr);
            byte b = bArr[0];
            if (b == 2) {
                byte b2 = bArr[1];
                if (b2 == -1 && this.isVerifying) {
                    onOtaVerifyComplete();
                    return;
                } else {
                    if (b2 == 11) {
                        byte[] bArr2 = TOUCH_SYSTEM_RESET;
                        sendCommand(bArr2, 3000, new TouchFirmware$$ExternalSyntheticLambda0(this), bArr2, new ResponseValidation() { // from class: cordova.plugins.qcc.GaiaLibrary.gaia.TouchFirmware$$ExternalSyntheticLambda6
                            @Override // cordova.plugins.qcc.GaiaLibrary.gaia.TouchFirmware.ResponseValidation
                            public final boolean validate(byte[] bArr3, byte[] bArr4) {
                                return TouchFirmware.lambda$onCommandReceive$3(bArr3, bArr4);
                            }
                        });
                        return;
                    }
                    return;
                }
            }
            if (b == 3) {
                byte[] bArr3 = TOUCH_FW_VERSION;
                sendCommand(bArr3, 3000, new TouchFirmware$$ExternalSyntheticLambda0(this), bArr3);
                return;
            }
            if (b != 68) {
                if (b != 86) {
                    return;
                }
                onReadFirmwareVer(bArr);
                if (this.isUpdateStart) {
                    if (this.bootloaderEnable) {
                        setTouchPageNum();
                        return;
                    } else {
                        byte[] bArr4 = TOUCH_BOOTLOADER_ENABLE;
                        sendCommand(bArr4, 3000, new TouchFirmware$$ExternalSyntheticLambda0(this), bArr4, new ResponseValidation() { // from class: cordova.plugins.qcc.GaiaLibrary.gaia.TouchFirmware$$ExternalSyntheticLambda7
                            @Override // cordova.plugins.qcc.GaiaLibrary.gaia.TouchFirmware.ResponseValidation
                            public final boolean validate(byte[] bArr5, byte[] bArr6) {
                                return TouchFirmware.lambda$onCommandReceive$4(bArr5, bArr6);
                            }
                        });
                        return;
                    }
                }
                if (!this.isVerifying || this.bootloaderEnable) {
                    return;
                }
                byte[] bArr5 = TOUCH_BOOTLOADER_DISABLE;
                sendCommand(bArr5, 3000, new Callback() { // from class: cordova.plugins.qcc.GaiaLibrary.gaia.TouchFirmware$$ExternalSyntheticLambda3
                    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.TouchFirmware.Callback
                    public final void exec() {
                        TouchFirmware.this.onOtaVerifyAbort();
                    }
                }, bArr5, new ResponseValidation() { // from class: cordova.plugins.qcc.GaiaLibrary.gaia.TouchFirmware$$ExternalSyntheticLambda4
                    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.TouchFirmware.ResponseValidation
                    public final boolean validate(byte[] bArr6, byte[] bArr7) {
                        return TouchFirmware.lambda$onCommandReceive$5(bArr6, bArr7);
                    }
                });
                return;
            }
            if (length > 1) {
                byte b3 = bArr[1];
                if (b3 == -124) {
                    if (this.isVerifying) {
                        short pageChecksum = getPageChecksum();
                        if (bArr[2] == ((byte) (pageChecksum >> 8)) && bArr[3] == ((byte) pageChecksum)) {
                            this.pageNum++;
                            onOtaVerifyProgress();
                            if (this.pageNum == this.pageTotal) {
                                sendCommand(TOUCH_EXEC_APP, 1000, new Callback() { // from class: cordova.plugins.qcc.GaiaLibrary.gaia.TouchFirmware$$ExternalSyntheticLambda3
                                    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.TouchFirmware.Callback
                                    public final void exec() {
                                        TouchFirmware.this.onOtaVerifyAbort();
                                    }
                                }, TOUCH_FW_VERSION, new ResponseValidation() { // from class: cordova.plugins.qcc.GaiaLibrary.gaia.TouchFirmware$$ExternalSyntheticLambda5
                                    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.TouchFirmware.ResponseValidation
                                    public final boolean validate(byte[] bArr6, byte[] bArr7) {
                                        return TouchFirmware.lambda$onCommandReceive$2(bArr6, bArr7);
                                    }
                                });
                                return;
                            } else {
                                setTouchPageNum();
                                return;
                            }
                        }
                        retryOtaUpdate();
                        return;
                    }
                    return;
                }
                if (b3 != -113) {
                    switch (b3) {
                        case Byte.MIN_VALUE:
                            this.pageOffset = (byte) 0;
                            if (this.isVerifying) {
                                byte[] bArr6 = TOUCH_GET_PAGE_CHECKSUM;
                                sendCommand(bArr6, 3000, new Callback() { // from class: cordova.plugins.qcc.GaiaLibrary.gaia.TouchFirmware$$ExternalSyntheticLambda3
                                    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.TouchFirmware.Callback
                                    public final void exec() {
                                        TouchFirmware.this.onOtaVerifyAbort();
                                    }
                                }, bArr6);
                                return;
                            } else {
                                setDataToPage();
                                return;
                            }
                        case -127:
                            byte offsetChecksum = getOffsetChecksum();
                            if (this.isUpdateStart) {
                                if (bArr[2] == offsetChecksum) {
                                    byte b4 = (byte) (this.pageOffset + 16);
                                    this.pageOffset = b4;
                                    if (b4 < 64) {
                                        setDataToPage();
                                        return;
                                    }
                                    this.pageOffset = (byte) 0;
                                    byte[] bArr7 = TOUCH_WRITE_TO_PAGE;
                                    sendCommand(bArr7, 3000, new TouchFirmware$$ExternalSyntheticLambda0(this), bArr7);
                                    return;
                                }
                                retryOtaUpdate();
                                return;
                            }
                            return;
                        case -126:
                            this.pageNum++;
                            onUpdateProgress();
                            if (this.pageNum == this.pageTotal) {
                                onOtaUpdateComplete();
                                onOtaVerifyStart();
                            }
                            setTouchPageNum();
                            return;
                        default:
                            return;
                    }
                }
                byte[] bArr8 = TOUCH_FW_VERSION;
                sendCommand(bArr8, 3000, new Callback() { // from class: cordova.plugins.qcc.GaiaLibrary.gaia.TouchFirmware$$ExternalSyntheticLambda3
                    @Override // cordova.plugins.qcc.GaiaLibrary.gaia.TouchFirmware.Callback
                    public final void exec() {
                        TouchFirmware.this.onOtaVerifyAbort();
                    }
                }, bArr8);
            }
        }
    }

    public static /* synthetic */ boolean lambda$onCommandReceive$2(byte[] bArr, byte[] bArr2) {
        return bArr2[0] == 86;
    }

    public static /* synthetic */ boolean lambda$onCommandReceive$3(byte[] bArr, byte[] bArr2) {
        return bArr2[0] == bArr[1];
    }

    public static /* synthetic */ boolean lambda$onCommandReceive$4(byte[] bArr, byte[] bArr2) {
        return bArr2[0] == bArr[1];
    }

    public static /* synthetic */ boolean lambda$onCommandReceive$5(byte[] bArr, byte[] bArr2) {
        return bArr2[0] == bArr[1];
    }

    public void onSolosDeviceDisconnected() {
        clearCommand();
        this.commandFifo.clear();
        this.isUpdateStart = false;
        this.isVerifying = false;
    }

    /* loaded from: classes2.dex */
    public abstract class Command {
        protected static final int RETRY_LIMIT = 3;
        protected byte[] command;
        Handler timer;
        protected int sendCmdRetryCnt = 0;
        protected ResponseValidation validateFun = new ResponseValidation() { // from class: cordova.plugins.qcc.GaiaLibrary.gaia.TouchFirmware$Command$$ExternalSyntheticLambda0
            @Override // cordova.plugins.qcc.GaiaLibrary.gaia.TouchFirmware.ResponseValidation
            public final boolean validate(byte[] bArr, byte[] bArr2) {
                return bArr2[0] == bArr[0];
            }
        };

        public abstract void exec();

        Command() {
        }

        public boolean lambda$new$0(byte[] bArr, byte[] bArr2) {
            return bArr2[0] == bArr[0];
        }

        public void cleanup() {
            this.sendCmdRetryCnt = 0;
        }

        public boolean validResponse(byte[] bArr) {
            return this.validateFun.validate(this.command, bArr);
        }

        protected void beginTimer(int i, Callback callback) {
            endTimer();
            Handler handler = new Handler(Looper.getMainLooper());
            this.timer = handler;
            handler.postDelayed(new Runnable() { // from class: cordova.plugins.qcc.GaiaLibrary.gaia.TouchFirmware.Command.1
                Callback r2;
                int r3;

                void RunnableC16381(Callback callback2, int i2) {
                    r2 = callback2;
                    r3 = i2;
                }

                @Override // java.lang.Runnable
                public void run() {
                    r2.exec();
                    if (Command.this.timer != null) {
                        Command.this.timer.postDelayed(this, r3);
                    }
                }
            }, 1000);
        }

        /* renamed from: cordova.plugins.qcc.GaiaLibrary.gaia.TouchFirmware$Command$1 */
        /* loaded from: classes2.dex */
        public class RunnableC16381 implements Runnable {
            final /* synthetic */ Callback r2;
            final /* synthetic */ int r3;

            RunnableC16381(Callback callback2, int i2) {
                r2 = callback2;
                r3 = i2;
            }

            @Override // java.lang.Runnable
            public void run() {
                r2.exec();
                if (Command.this.timer != null) {
                    Command.this.timer.postDelayed(this, r3);
                }
            }
        }

        protected void endTimer() {
            Handler handler = this.timer;
            if (handler != null) {
                handler.removeCallbacksAndMessages(null);
                this.timer = null;
            }
        }

        protected void sendCmd(byte[] bArr, int i, Callback callback) {
            beginTimer(i, callback);
            TouchFirmware.this.mUart.sendCommand(bArr);
        }

        protected void sendCmd(byte[] bArr) {
            TouchFirmware.this.mUart.sendCommand(bArr);
        }
    }

    /* loaded from: classes2.dex */
    class NoRetryCommand extends Command {
        NoRetryCommand(byte[] bArr) {
            super();
            this.command = bArr;
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.TouchFirmware.Command
        public void exec() {
            sendCmd(this.command);
        }
    }

    /* loaded from: classes2.dex */
    public class RetryCommand extends Command {
        private Callback callback;

        /* renamed from: ms */
        private int f301ms;

        RetryCommand(byte[] bArr, int i, Callback callback, byte[] bArr2) {
            super();
            this.command = bArr;
            this.f301ms = i;
            initRetryCallBack(callback, bArr2);
        }

        RetryCommand(byte[] bArr, int i, Callback callback, byte[] bArr2, ResponseValidation responseValidation) {
            super();
            this.command = bArr;
            this.f301ms = i;
            this.validateFun = responseValidation;
            initRetryCallBack(callback, bArr2);
        }

        private void initRetryCallBack(final Callback callback, final byte[] bArr) {
            this.callback = new Callback() { // from class: cordova.plugins.qcc.GaiaLibrary.gaia.TouchFirmware$RetryCommand$$ExternalSyntheticLambda0
                @Override // cordova.plugins.qcc.GaiaLibrary.gaia.TouchFirmware.Callback
                public final void exec() {
                    TouchFirmware.RetryCommand.this.lambda$initRetryCallBack$0(bArr, callback);
                }
            };
        }

        public /* synthetic */ void lambda$initRetryCallBack$0(byte[] bArr, Callback callback) {
            if (this.sendCmdRetryCnt < 3) {
                this.sendCmdRetryCnt++;
                sendCmd(bArr);
            } else {
                TouchFirmware.this.clearCommand();
                callback.exec();
            }
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.TouchFirmware.Command
        public void exec() {
            sendCmd(this.command, this.f301ms, this.callback);
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.TouchFirmware.Command
        public void cleanup() {
            super.cleanup();
            endTimer();
        }
    }

    /* loaded from: classes2.dex */
    public class DelayCommand extends Command {
        private Callback callback;

        /* renamed from: ms */
        private int f300ms;

        DelayCommand(final byte[] bArr, int i) {
            super();
            this.callback = new Callback() { // from class: cordova.plugins.qcc.GaiaLibrary.gaia.TouchFirmware$DelayCommand$$ExternalSyntheticLambda1
                @Override // cordova.plugins.qcc.GaiaLibrary.gaia.TouchFirmware.Callback
                public final void exec() {
                    TouchFirmware.DelayCommand.this.lambda$new$0(bArr);
                }
            };
            this.f300ms = i;
        }

        public /* synthetic */ void lambda$new$0(byte[] bArr) {
            sendCmd(bArr);
        }

        DelayCommand(final byte[] bArr, int i, ResponseValidation responseValidation) {
            super();
            this.callback = new Callback() { // from class: cordova.plugins.qcc.GaiaLibrary.gaia.TouchFirmware$DelayCommand$$ExternalSyntheticLambda0
                @Override // cordova.plugins.qcc.GaiaLibrary.gaia.TouchFirmware.Callback
                public final void exec() {
                    TouchFirmware.DelayCommand.this.lambda$new$1(bArr);
                }
            };
            this.f300ms = i;
            this.validateFun = responseValidation;
        }

        public /* synthetic */ void lambda$new$1(byte[] bArr) {
            sendCmd(bArr);
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.TouchFirmware.Command
        public void exec() {
            beginTimer(this.f300ms, this.callback);
        }

        @Override // cordova.plugins.qcc.GaiaLibrary.gaia.TouchFirmware.Command
        public void cleanup() {
            super.cleanup();
            endTimer();
        }
    }
}
