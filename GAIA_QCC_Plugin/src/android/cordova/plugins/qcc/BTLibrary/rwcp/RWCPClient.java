package cordova.plugins.qcc.BTLibrary.rwcp;

import android.os.Handler;
import android.util.Log;
import cordova.plugins.qcc.BTLibrary.Utils;
import java.util.Iterator;
import java.util.LinkedList;

/* loaded from: classes2.dex */
public class RWCPClient {
    private final RWCPListener mListener;
    private final String TAG = "RWCPClient";
    private int mLastAckSequence = -1;
    private int mNextSequence = 0;
    private int mInitialWindow = 15;
    private int mMaximumWindow = 32;
    private int mWindow = 15;
    private int mCredits = 15;
    private boolean mIsResendingSegments = false;
    private int mState = 0;
    private final LinkedList<byte[]> mPendingData = new LinkedList<>();
    private final LinkedList<Segment> mUnacknowledgedSegments = new LinkedList<>();
    private final TimeOutRunnable mTimeOutRunnable = new TimeOutRunnable();
    private boolean isTimeOutRunning = false;
    private final Handler mHandler = new Handler();
    private int mDataTimeOutMs = 100;
    private boolean mShowDebugLogs = false;
    private int mAcknowledgedSegments = 0;

    /* loaded from: classes2.dex */
    public interface RWCPListener {
        void onTransferFailed();

        void onTransferFinished();

        void onTransferProgress(int i);

        boolean sendRWCPSegment(byte[] bArr);
    }

    public RWCPClient(RWCPListener rWCPListener) {
        this.mListener = rWCPListener;
    }

    public boolean isRunningASession() {
        return this.mState != 0;
    }

    public void showDebugLogs(boolean z) {
        this.mShowDebugLogs = z;
        Log.i("RWCPClient", "Debug logs are now " + (z ? "activated" : "deactivated") + ".");
    }

    public boolean sendData(byte[] bArr) {
        this.mPendingData.add(bArr);
        int i = this.mState;
        if (i == 0) {
            return startSession();
        }
        if (i == 2 && !this.isTimeOutRunning) {
            sendDataSegment();
        }
        return true;
    }

    public void cancelTransfer() {
        logState("cancelTransfer");
        if (this.mState == 0) {
            Log.i("RWCPClient", "cancelTransfer: no ongoing transfer to cancel.");
            return;
        }
        reset(true);
        if (sendRSTSegment()) {
            return;
        }
        Log.w("RWCPClient", "Sending of RST segment has failed, terminating session.");
        terminateSession();
    }

    public boolean onReceiveRWCPSegment(byte[] bArr) {
        if (bArr == null) {
            Log.w("RWCPClient", "onReceiveRWCPSegment called with a null bytes array.");
            return false;
        }
        if (bArr.length < 1) {
            Log.w("RWCPClient", this.mShowDebugLogs ? "Analyse of RWCP Segment failed: the byte array does not contain the minimum required information.\n\tbytes=" + Utils.getStringFromBytes(bArr) : "Analyse of RWCP Segment failed: the byte array does not contain the minimum required information.");
            return false;
        }
        Segment segment = new Segment(bArr);
        int operationCode = segment.getOperationCode();
        if (operationCode == -1) {
            Log.w("RWCPClient", "onReceivedRWCPSegment failed to get a RWCP segment from given bytes: " + Utils.getStringFromBytes(bArr));
            return false;
        }
        if (operationCode == 0) {
            return receiveDataAck(segment);
        }
        if (operationCode == 1) {
            return receiveSynAck(segment);
        }
        if (operationCode == 2) {
            return receiveRST(segment);
        }
        if (operationCode == 3) {
            return receiveGAP(segment);
        }
        Log.w("RWCPClient", "Received unknown operation code: " + operationCode);
        return false;
    }

    public int getInitialWindowSize() {
        return this.mInitialWindow;
    }

    public boolean setInitialWindowSize(int i) {
        logState("set initial window size to " + i);
        if (this.mState != 0) {
            Log.w("RWCPClient", "FAIL to set initial window size to " + i + ": not possible when there is an ongoing session.");
            return false;
        }
        if (i <= 0 || i > this.mMaximumWindow) {
            Log.w("RWCPClient", "FAIL to set initial window to " + i + ": size is out of range.");
            return false;
        }
        this.mInitialWindow = i;
        this.mWindow = i;
        return true;
    }

    public int getMaximumWindowSize() {
        return this.mMaximumWindow;
    }

    public boolean setMaximumWindowSize(int i) {
        logState("set maximum window size to " + i);
        if (this.mState != 0) {
            Log.w("RWCPClient", "FAIL to set maximum window size to " + i + ": not possible when there is an ongoing session.");
            return false;
        }
        if (i <= 0 || i > 32) {
            Log.w("RWCPClient", "FAIL to set maximum window to " + i + ": size is out of range.");
            return false;
        }
        if (this.mInitialWindow > this.mMaximumWindow) {
            Log.w("RWCPClient", "FAIL to set maximum window to " + i + ": initial window is " + this.mInitialWindow + ".");
            return false;
        }
        this.mMaximumWindow = i;
        if (this.mWindow <= i) {
            return true;
        }
        Log.i("RWCPClient", "window is updated to be less than the maximum window size (" + this.mMaximumWindow + ").");
        this.mWindow = this.mMaximumWindow;
        return true;
    }

    private boolean startSession() {
        logState("startSession");
        if (this.mState != 0) {
            Log.w("RWCPClient", "Start RWCP session failed: already an ongoing session.");
            return false;
        }
        if (sendRSTSegment()) {
            return true;
        }
        Log.w("RWCPClient", "Start RWCP session failed: sending of RST segment failed.");
        terminateSession();
        return false;
    }

    private void terminateSession() {
        logState("terminateSession");
        reset(true);
    }

    private boolean receiveSynAck(Segment segment) {
        if (this.mShowDebugLogs) {
            Log.d("RWCPClient", "Receive SYN_ACK for sequence " + segment.getSequenceNumber());
        }
        int i = this.mState;
        if (i != 1) {
            if (i == 2) {
                cancelTimeOut();
                if (this.mUnacknowledgedSegments.size() > 0) {
                    resendDataSegment();
                }
                return true;
            }
            Log.w("RWCPClient", "Received unexpected SYN_ACK segment with header " + ((int) segment.getHeader()) + " while in state " + RWCP.getStateLabel(this.mState));
            return false;
        }
        cancelTimeOut();
        if (validateAckSequence(1, segment.getSequenceNumber()) >= 0) {
            this.mState = 2;
            if (this.mPendingData.size() > 0) {
                sendDataSegment();
            }
        } else {
            Log.w("RWCPClient", "Receive SYN_ACK with unexpected sequence number: " + segment.getSequenceNumber());
            terminateSession();
            this.mListener.onTransferFailed();
            sendRSTSegment();
        }
        return true;
    }

    private boolean receiveDataAck(Segment segment) {
        if (this.mShowDebugLogs) {
            Log.d("RWCPClient", "Receive DATA_ACK for sequence " + segment.getSequenceNumber());
        }
        int i = this.mState;
        if (i != 2) {
            if (i == 3) {
                if (this.mShowDebugLogs) {
                    Log.i("RWCPClient", "Received DATA_ACK(" + segment.getSequenceNumber() + ") segment while in state CLOSING: segment discarded.");
                }
                return true;
            }
            Log.w("RWCPClient", "Received unexpected DATA_ACK segment with sequence " + segment.getSequenceNumber() + " while in state " + RWCP.getStateLabel(this.mState));
            return false;
        }
        cancelTimeOut();
        int validateAckSequence = validateAckSequence(0, segment.getSequenceNumber());
        if (validateAckSequence >= 0) {
            if (this.mCredits > 0 && !this.mPendingData.isEmpty()) {
                sendDataSegment();
            } else if (this.mPendingData.isEmpty() && this.mUnacknowledgedSegments.isEmpty()) {
                sendRSTSegment();
            } else if (this.mPendingData.isEmpty() || this.mCredits == 0) {
                startTimeOut(this.mDataTimeOutMs);
            }
            this.mListener.onTransferProgress(validateAckSequence);
        }
        return true;
    }

    private boolean receiveRST(Segment segment) {
        if (this.mShowDebugLogs) {
            Log.d("RWCPClient", "Receive RST or RST_ACK for sequence " + segment.getSequenceNumber());
        }
        int i = this.mState;
        if (i == 1) {
            Log.i("RWCPClient", "Received RST (sequence " + segment.getSequenceNumber() + ") in SYN_SENT state, ignoring segment.");
            return true;
        }
        if (i == 2) {
            Log.w("RWCPClient", "Received RST (sequence " + segment.getSequenceNumber() + ") in ESTABLISHED state, terminating session, transfer failed.");
            terminateSession();
            this.mListener.onTransferFailed();
            return true;
        }
        if (i == 3) {
            cancelTimeOut();
            validateAckSequence(2, segment.getSequenceNumber());
            reset(false);
            if (!this.mPendingData.isEmpty()) {
                if (!sendSYNSegment()) {
                    Log.w("RWCPClient", "Start session of RWCP data transfer failed: sending of SYN failed.");
                    terminateSession();
                    this.mListener.onTransferFailed();
                }
            } else {
                this.mListener.onTransferFinished();
            }
            return true;
        }
        Log.w("RWCPClient", "Received unexpected RST segment with sequence=" + segment.getSequenceNumber() + " while in state " + RWCP.getStateLabel(this.mState));
        return false;
    }

    private boolean receiveGAP(Segment segment) {
        if (this.mShowDebugLogs) {
            Log.d("RWCPClient", "Receive GAP for sequence " + segment.getSequenceNumber());
        }
        int i = this.mState;
        if (i != 2) {
            if (i == 3) {
                if (this.mShowDebugLogs) {
                    Log.i("RWCPClient", "Received GAP(" + segment.getSequenceNumber() + ") segment while in state CLOSING: segment discarded.");
                }
                return true;
            }
            Log.w("RWCPClient", "Received unexpected GAP segment with header " + ((int) segment.getHeader()) + " while in state " + RWCP.getStateLabel(this.mState));
            return false;
        }
        if (this.mLastAckSequence > segment.getSequenceNumber()) {
            Log.i("RWCPClient", "Ignoring GAP (" + segment.getSequenceNumber() + ") as last ack sequence is " + this.mLastAckSequence + ".");
            return true;
        }
        if (this.mLastAckSequence <= segment.getSequenceNumber()) {
            decreaseWindow();
            validateAckSequence(0, segment.getSequenceNumber());
        }
        cancelTimeOut();
        resendDataSegment();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onTimeOut() {
        if (this.isTimeOutRunning) {
            this.isTimeOutRunning = false;
            this.mIsResendingSegments = true;
            this.mAcknowledgedSegments = 0;
            if (this.mShowDebugLogs) {
                Log.i("RWCPClient", "TIME OUT > re sending segments");
            }
            if (this.mState == 2) {
                int i = this.mDataTimeOutMs * 2;
                this.mDataTimeOutMs = i;
                if (i > 2000) {
                    this.mDataTimeOutMs = 2000;
                }
                resendDataSegment();
                return;
            }
            resendSegment();
        }
    }

    private int validateAckSequence(int i, int i2) {
        int i3;
        if (i2 < 0) {
            Log.w("RWCPClient", "Received ACK sequence (" + i2 + ") is less than 0.");
            return -1;
        }
        if (i2 > 63) {
            Log.w("RWCPClient", "Received ACK sequence (" + i2 + ") is bigger than its maximum value (63).");
            return -1;
        }
        int i4 = this.mLastAckSequence;
        int i5 = this.mNextSequence;
        if (i4 < i5 && (i2 < i4 || i2 > i5)) {
            Log.w("RWCPClient", "Received ACK sequence (" + i2 + ") is out of interval: last received is " + this.mLastAckSequence + " and next will be " + this.mNextSequence);
            return -1;
        }
        if (i4 > i5 && i2 < i4 && i2 > i5) {
            Log.w("RWCPClient", "Received ACK sequence (" + i2 + ") is out of interval: last received is " + this.mLastAckSequence + " and next will be " + this.mNextSequence);
            return -1;
        }
        synchronized (this.mUnacknowledgedSegments) {
            i3 = 0;
            while (i4 != i2) {
                i4 = increaseSequenceNumber(i4);
                if (removeSegmentFromQueue(i, i4)) {
                    this.mLastAckSequence = i4;
                    int i6 = this.mCredits;
                    if (i6 < this.mWindow) {
                        this.mCredits = i6 + 1;
                    }
                    i3++;
                } else {
                    Log.w("RWCPClient", "Error validating sequence " + i4 + ": no corresponding segment in pending segments.");
                }
            }
        }
        logState(i3 + " segment(s) validated with ACK sequence(code=" + i + ", seq=" + i2 + ")");
        increaseWindow(i3);
        return i3;
    }

    private boolean sendRSTSegment() {
        boolean sendSegment;
        if (this.mState == 3) {
            return true;
        }
        reset(false);
        synchronized (this.mUnacknowledgedSegments) {
            this.mState = 3;
            Segment segment = new Segment(2, this.mNextSequence);
            sendSegment = sendSegment(segment, 1000);
            if (sendSegment) {
                this.mUnacknowledgedSegments.add(segment);
                this.mNextSequence = increaseSequenceNumber(this.mNextSequence);
                this.mCredits--;
                logState("send RST segment");
            }
        }
        return sendSegment;
    }

    private void logState(String str) {
        if (this.mShowDebugLogs) {
            Log.d("RWCPClient", str + "\t\t\tstate=" + RWCP.getStateLabel(this.mState) + "\n\tWindow: \tcurrent = " + this.mWindow + " \t\tdefault = " + this.mInitialWindow + " \t\tcredits = " + this.mCredits + "\n\tSequence: \tlast = " + this.mLastAckSequence + " \t\tnext = " + this.mNextSequence + "\n\tPending: \tPSegments = " + this.mUnacknowledgedSegments.size() + " \t\tPData = " + this.mPendingData.size());
        }
    }

    private boolean sendSYNSegment() {
        boolean sendSegment;
        synchronized (this.mUnacknowledgedSegments) {
            this.mState = 1;
            Segment segment = new Segment(1, this.mNextSequence);
            sendSegment = sendSegment(segment, 1000);
            if (sendSegment) {
                this.mUnacknowledgedSegments.add(segment);
                this.mNextSequence = increaseSequenceNumber(this.mNextSequence);
                this.mCredits--;
                logState("send SYN segment");
            }
        }
        return sendSegment;
    }

    private void sendDataSegment() {
        while (this.mCredits > 0 && !this.mPendingData.isEmpty() && !this.mIsResendingSegments && this.mState == 2) {
            synchronized (this.mUnacknowledgedSegments) {
                Segment segment = new Segment(0, this.mNextSequence, this.mPendingData.poll());
                sendSegment(segment, this.mDataTimeOutMs);
                this.mUnacknowledgedSegments.add(segment);
                this.mNextSequence = increaseSequenceNumber(this.mNextSequence);
                this.mCredits--;
            }
        }
        logState("send DATA segments");
    }

    private int increaseSequenceNumber(int i) {
        return (i + 1) % 64;
    }

    private int decreaseSequenceNumber(int i, int i2) {
        return (((i - i2) + 63) + 1) % 64;
    }

    private void resendSegment() {
        if (this.mState == 2) {
            Log.w("RWCPClient", "Trying to resend non data segment while in ESTABLISHED state.");
            return;
        }
        this.mIsResendingSegments = true;
        this.mCredits = this.mWindow;
        synchronized (this.mUnacknowledgedSegments) {
            Iterator<Segment> it = this.mUnacknowledgedSegments.iterator();
            while (it.hasNext()) {
                Segment next = it.next();
                int i = 1000;
                if (next.getOperationCode() != 1 && next.getOperationCode() != 2) {
                    i = this.mDataTimeOutMs;
                }
                sendSegment(next, i);
                this.mCredits--;
            }
        }
        logState("resend segments");
        this.mIsResendingSegments = false;
    }

    private void resendDataSegment() {
        if (this.mState != 2) {
            Log.w("RWCPClient", "Trying to resend data segment while not in ESTABLISHED state.");
            return;
        }
        this.mIsResendingSegments = true;
        this.mCredits = this.mWindow;
        logState("reset credits");
        synchronized (this.mUnacknowledgedSegments) {
            int i = 0;
            while (true) {
                if (this.mUnacknowledgedSegments.size() <= this.mCredits) {
                    break;
                }
                Segment last = this.mUnacknowledgedSegments.getLast();
                if (last.getOperationCode() == 0) {
                    removeSegmentFromQueue(last);
                    this.mPendingData.addFirst(last.getPayload());
                    i++;
                } else {
                    Log.w("RWCPClient", "Segment " + last.toString() + " in pending segments but not a DATA segment.");
                    break;
                }
            }
            this.mNextSequence = decreaseSequenceNumber(this.mNextSequence, i);
            Iterator<Segment> it = this.mUnacknowledgedSegments.iterator();
            while (it.hasNext()) {
                sendSegment(it.next(), this.mDataTimeOutMs);
                this.mCredits--;
            }
        }
        logState("Resend DATA segments");
        this.mIsResendingSegments = false;
        if (this.mCredits > 0) {
            sendDataSegment();
        }
    }

    private boolean sendSegment(Segment segment, int i) {
        if (!this.mListener.sendRWCPSegment(segment.getBytes())) {
            return false;
        }
        startTimeOut(i);
        return true;
    }

    private boolean removeSegmentFromQueue(int i, int i2) {
        synchronized (this.mUnacknowledgedSegments) {
            Iterator<Segment> it = this.mUnacknowledgedSegments.iterator();
            while (it.hasNext()) {
                Segment next = it.next();
                if (next.getOperationCode() == i && next.getSequenceNumber() == i2) {
                    this.mUnacknowledgedSegments.remove(next);
                    return true;
                }
            }
            Log.w("RWCPClient", "Pending segments does not contain acknowledged segment: code=" + i + " \tsequence=" + i2);
            return false;
        }
    }

    private boolean removeSegmentFromQueue(Segment segment) {
        synchronized (this.mUnacknowledgedSegments) {
            if (this.mUnacknowledgedSegments.remove(segment)) {
                return true;
            }
            Log.w("RWCPClient", "Pending unack segments does not contain segment (code=" + segment.getOperationCode() + ", seq=" + segment.getSequenceNumber() + ")");
            return false;
        }
    }

    private void reset(boolean z) {
        synchronized (this.mUnacknowledgedSegments) {
            this.mLastAckSequence = -1;
            this.mNextSequence = 0;
            this.mState = 0;
            this.mUnacknowledgedSegments.clear();
            int i = this.mInitialWindow;
            this.mWindow = i;
            this.mAcknowledgedSegments = 0;
            this.mCredits = i;
            cancelTimeOut();
        }
        if (z) {
            this.mPendingData.clear();
        }
        logState("reset");
    }

    private void increaseWindow(int i) {
        int i2 = this.mAcknowledgedSegments + i;
        this.mAcknowledgedSegments = i2;
        int i3 = this.mWindow;
        if (i2 <= i3 || i3 >= this.mMaximumWindow) {
            return;
        }
        this.mAcknowledgedSegments = 0;
        this.mWindow = i3 + 1;
        this.mCredits++;
        logState("increase window to " + this.mWindow);
    }

    private void decreaseWindow() {
        int i = ((this.mWindow - 1) / 2) + 1;
        this.mWindow = i;
        if (i > this.mMaximumWindow || i < 1) {
            this.mWindow = 1;
        }
        this.mAcknowledgedSegments = 0;
        this.mCredits = this.mWindow;
        logState("decrease window to " + this.mWindow);
    }

    private void startTimeOut(long j) {
        if (this.isTimeOutRunning) {
            this.mHandler.removeCallbacks(this.mTimeOutRunnable);
        }
        this.isTimeOutRunning = true;
        this.mHandler.postDelayed(this.mTimeOutRunnable, j);
    }

    private void cancelTimeOut() {
        if (this.isTimeOutRunning) {
            this.mHandler.removeCallbacks(this.mTimeOutRunnable);
            this.isTimeOutRunning = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class TimeOutRunnable implements Runnable {
        private TimeOutRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            RWCPClient.this.onTimeOut();
        }
    }
}
