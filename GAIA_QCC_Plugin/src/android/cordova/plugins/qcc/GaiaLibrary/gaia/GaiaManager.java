package cordova.plugins.qcc.GaiaLibrary.gaia;

import android.os.Handler;
import android.util.Log;
//import com.google.firebase.messaging.ServiceStarter;
import cordova.plugins.qcc.GaiaLibrary.gaia.packets.GaiaPacket;
import cordova.plugins.qcc.GaiaLibrary.gaia.packets.GaiaPacketBLE;
import cordova.plugins.qcc.GaiaLibrary.gaia.packets.GaiaPacketBREDR;
import cordova.plugins.qcc.GaiaLibrary.gaia.requests.GaiaAcknowledgementRequest;
import cordova.plugins.qcc.GaiaLibrary.gaia.requests.GaiaRequest;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes2.dex */
public abstract class GaiaManager {
    protected static final int ACKNOWLEDGEMENT_RUNNABLE_DEFAULT_DELAY_MILLIS = 5000;
    private final int mTransportType;
    private final String TAG = "GaiaManager";
    private final ArrayList<TimeOutRequestRunnable> mTimeOutRequestRunnableList = new ArrayList<>();
    private int mTimeOutRequestDelay = 5000;
    private final Handler mHandler = new Handler();
    private boolean mShowDebugLogs = false;
    private final ArrayList<DeferSendRequestRunnable> mDeferSendRequestRunnableList = new ArrayList<>();
    private final ArrayList<GaiaRequest> mGaiaRequestList = new ArrayList<>();

    protected abstract void hasNotReceivedAcknowledgementPacket(GaiaPacket gaiaPacket);

    protected abstract boolean manageReceivedPacket(GaiaPacket gaiaPacket);

    protected abstract void onSendingFailed(GaiaPacket gaiaPacket);

    protected abstract void receiveSuccessfulAcknowledgement(GaiaPacket gaiaPacket);

    protected abstract void receiveUnsuccessfulAcknowledgement(GaiaPacket gaiaPacket);

    protected abstract boolean sendGAIAPacket(byte[] bArr);

    /* JADX INFO: Access modifiers changed from: protected */
    public GaiaManager(int i) {
        this.mTransportType = i;
    }

    public void reset() {
        if (this.mShowDebugLogs) {
            Log.d("GaiaManager", "Request received to reset the manager.");
        }
        synchronized (this) {
            this.mGaiaRequestList.clear();
            Iterator<TimeOutRequestRunnable> it = this.mTimeOutRequestRunnableList.iterator();
            while (it.hasNext()) {
                this.mHandler.removeCallbacks(it.next());
            }
            this.mTimeOutRequestRunnableList.clear();
            Iterator<DeferSendRequestRunnable> it2 = this.mDeferSendRequestRunnableList.iterator();
            while (it2.hasNext()) {
                this.mHandler.removeCallbacks(it2.next());
            }
            this.mDeferSendRequestRunnableList.clear();
        }
    }

    public synchronized void setRequestTimeOut(int i) {
        if (this.mShowDebugLogs) {
            Log.d("GaiaManager", "Time out set up to " + i + ", previous time out was " + this.mTimeOutRequestDelay);
        }
        this.mTimeOutRequestDelay = i;
    }

    public int getTransportType() {
        return this.mTransportType;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void showDebugLogs(boolean z) {
        this.mShowDebugLogs = z;
        Log.i("GaiaManager", "Debug logs are now " + (z ? "activated" : "deactivated") + ".");
    }

    private void sendGAIAAcknowledgement(GaiaPacket gaiaPacket, int i, byte[] bArr) {
        if (this.mShowDebugLogs) {
            Log.d("GaiaManager", "Request to send acknowledgement for packet with command " + GaiaUtils.getHexadecimalStringFromInt(gaiaPacket.getCommand()));
        }
        if (gaiaPacket.isAcknowledgement()) {
            return;
        }
        try {
            sendGAIAPacket(gaiaPacket.getAcknowledgementPacketBytes(i, bArr));
        } catch (GaiaException unused) {
        }
    }

    public boolean onReceiveGAIAPacket(byte[] bArr) {
        if (this.mShowDebugLogs) {
            Log.d("GaiaManager", "Received potential GAIA packet: " + GaiaUtils.getHexadecimalStringFromBytes(bArr));
        }
        try {
            GaiaPacket gaiaPacketBLE = this.mTransportType == 0 ? new GaiaPacketBLE(bArr) : new GaiaPacketBREDR(bArr);
            if (this.mShowDebugLogs) {
                Log.d("GaiaManager", "Manager could retrieve a packet from the given data with command: " + GaiaUtils.getGAIACommandToString(gaiaPacketBLE.getCommand()));
            }
            if (gaiaPacketBLE.isAcknowledgement() && gaiaPacketBLE.getCommandId() != 49800) {
                boolean cancelTimeOutRequestRunnable = cancelTimeOutRequestRunnable(gaiaPacketBLE.getCommand());
                sendFirstPendingRequest(0);
                if (cancelTimeOutRequestRunnable) {
                    int status = gaiaPacketBLE.getStatus();
                    if (this.mShowDebugLogs) {
                        Log.d("GaiaManager", "Received GAIA ACK packet for command" + GaiaUtils.getGAIACommandToString(gaiaPacketBLE.getCommand()) + " with status: " + GAIA.getStatusToString(status));
                    }
                    if (status == 0) {
                        receiveSuccessfulAcknowledgement(gaiaPacketBLE);
                    } else {
                        receiveUnsuccessfulAcknowledgement(gaiaPacketBLE);
                    }
                }
                return cancelTimeOutRequestRunnable;
            }
            if (manageReceivedPacket(gaiaPacketBLE)) {
                return true;
            }
            createAcknowledgmentRequest(gaiaPacketBLE, 1, null);
            return false;
        } catch (GaiaException unused) {
            return false;
        }
    }

    private void appendRequestList(GaiaRequest gaiaRequest) {
        synchronized (this) {
            this.mGaiaRequestList.add(gaiaRequest);
            if (this.mShowDebugLogs) {
                Log.d("GaiaManager", "appendRequestList: gaia request list " + this.mGaiaRequestList.size());
            }
        }
    }

    private void prependRequestList(GaiaRequest gaiaRequest) {
        synchronized (this) {
            this.mGaiaRequestList.add(0, gaiaRequest);
            if (this.mShowDebugLogs) {
                Log.d("GaiaManager", "prependRequestList: gaia request list " + this.mGaiaRequestList.size());
            }
        }
    }

    private void sendFirstPendingRequest(int i) {
        synchronized (this) {
            if (!this.mDeferSendRequestRunnableList.isEmpty()) {
                if (this.mShowDebugLogs) {
                    Log.d("GaiaManager", "defer send request runnable list not empty " + this.mDeferSendRequestRunnableList.size());
                }
            } else {
                if (this.mGaiaRequestList.isEmpty()) {
                    if (this.mShowDebugLogs) {
                        Log.d("GaiaManager", "gaia request list empty ");
                    }
                    return;
                }
                GaiaRequest remove = this.mGaiaRequestList.remove(0);
                if (this.mShowDebugLogs) {
                    Log.d("GaiaManager", "sendFirstPendingRequest: gaia request list " + this.mGaiaRequestList.size());
                }
                DeferSendRequestRunnable deferSendRequestRunnable = new DeferSendRequestRunnable(remove);
                this.mDeferSendRequestRunnableList.add(deferSendRequestRunnable);
                this.mHandler.postDelayed(deferSendRequestRunnable, i);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void createRequest(GaiaPacket gaiaPacket) {
        if (this.mShowDebugLogs) {
            Log.d("GaiaManager", "Received request to send a packet with expected acknowledgement for command: " + GaiaUtils.getGAIACommandToString(gaiaPacket.getCommand()));
        }
        GaiaRequest gaiaRequest = new GaiaRequest(1);
        gaiaRequest.packet = gaiaPacket;
        appendRequestList(gaiaRequest);
        sendFirstPendingRequest(0);
    }

    protected void createUnacknowledgedRequest(GaiaPacket gaiaPacket) {
        if (this.mShowDebugLogs) {
            Log.d("GaiaManager", "Received request to send a packet with no acknowledgement for command: " + GaiaUtils.getGAIACommandToString(gaiaPacket.getCommand()));
        }
        GaiaRequest gaiaRequest = new GaiaRequest(3);
        gaiaRequest.packet = gaiaPacket;
        processRequest(gaiaRequest);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void createAcknowledgmentRequest(GaiaPacket gaiaPacket, int i, byte[] bArr) {
        if (this.mShowDebugLogs) {
            Log.d("GaiaManager", "Received request to send an acknowledgement packet for command: " + GaiaUtils.getGAIACommandToString(gaiaPacket.getCommand()) + " with status: " + GAIA.getStatusToString(i));
        }
        GaiaAcknowledgementRequest gaiaAcknowledgementRequest = new GaiaAcknowledgementRequest(i, bArr);
        gaiaAcknowledgementRequest.packet = gaiaPacket;
        processRequest(gaiaAcknowledgementRequest);
    }

    private boolean startTimeOutRequestRunnable(GaiaRequest gaiaRequest) {
        synchronized (this) {
            if (!this.mTimeOutRequestRunnableList.isEmpty()) {
                if (this.mShowDebugLogs) {
                    Log.d("GaiaManager", "startTimeOutRequestRunnable timeout request runnable list not empty");
                }
                return false;
            }
            TimeOutRequestRunnable timeOutRequestRunnable = new TimeOutRequestRunnable(gaiaRequest);
            this.mTimeOutRequestRunnableList.add(timeOutRequestRunnable);
            this.mHandler.postDelayed(timeOutRequestRunnable, this.mTimeOutRequestDelay);
            return true;
        }
    }

    protected boolean cancelTimeOutRequestRunnable(int i) {
        synchronized (this) {
            if (this.mTimeOutRequestRunnableList.isEmpty()) {
                if (this.mShowDebugLogs) {
                    Log.d("GaiaManager", "cancelTimeOutRequestRunnable timeout request runnable list empty");
                }
                return false;
            }
            TimeOutRequestRunnable timeOutRequestRunnable = this.mTimeOutRequestRunnableList.get(0);
            if (timeOutRequestRunnable.getKey() != i) {
                if (this.mShowDebugLogs) {
                    Log.d("GaiaManager", "cancelTimeOutRequestRunnable key not match " + timeOutRequestRunnable.getKey() + ", " + i);
                }
                return false;
            }
            this.mHandler.removeCallbacks(this.mTimeOutRequestRunnableList.remove(0));
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processRequest(GaiaRequest gaiaRequest) {
        if (this.mShowDebugLogs) {
            Log.d("GaiaManager", "Processing request of type " + gaiaRequest.type);
        }
        int i = gaiaRequest.type;
        if (i != 1) {
            if (i == 2) {
                GaiaAcknowledgementRequest gaiaAcknowledgementRequest = (GaiaAcknowledgementRequest) gaiaRequest;
                sendGAIAAcknowledgement(gaiaAcknowledgementRequest.packet, gaiaAcknowledgementRequest.status, gaiaAcknowledgementRequest.data);
                return;
            } else {
                if (i != 3) {
                    return;
                }
                try {
                    if (sendGAIAPacket(gaiaRequest.packet.getBytes())) {
                        return;
                    }
                    onSendingFailed(gaiaRequest.packet);
                    return;
                } catch (GaiaException unused) {
                    return;
                }
            }
        }
        try {
            if (!startTimeOutRequestRunnable(gaiaRequest)) {
                prependRequestList(gaiaRequest);
            } else {
                if (sendGAIAPacket(gaiaRequest.packet.getBytes())) {
                    return;
                }
                cancelTimeOutRequestRunnable(gaiaRequest.packet.getCommand());
                //sendFirstPendingRequest(ServiceStarter.ERROR_UNKNOWN);
                Log.w("GaiaManager", "Fail to send GAIA packet for GAIA command: " + GaiaUtils.getGAIACommandToString(gaiaRequest.packet.getCommandId()));
                onSendingFailed(gaiaRequest.packet);
            }
        } catch (GaiaException e) {
            Log.w("GaiaManager", "Exception when attempting to create GAIA packet: " + e.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class TimeOutRequestRunnable implements Runnable {
        private final GaiaRequest request;

        TimeOutRequestRunnable(GaiaRequest gaiaRequest) {
            this.request = gaiaRequest;
        }

        public int getKey() {
            return this.request.packet.getCommand();
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (this) {
                int command = this.request.packet.getCommand();
                if (GaiaManager.this.mShowDebugLogs) {
                    Log.d("GaiaManager", "A request is timed out for command: " + GaiaUtils.getGAIACommandToString(command));
                }
                GaiaManager.this.mTimeOutRequestRunnableList.remove(this);
            }
            Log.w("GaiaManager", "No ACK packet for command: " + GaiaUtils.getGAIACommandToString(this.request.packet.getCommand()));
            GaiaManager.this.hasNotReceivedAcknowledgementPacket(this.request.packet);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class DeferSendRequestRunnable implements Runnable {
        private final GaiaRequest request;

        DeferSendRequestRunnable(GaiaRequest gaiaRequest) {
            this.request = gaiaRequest;
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (this) {
                GaiaManager.this.mDeferSendRequestRunnableList.remove(this);
            }
            GaiaManager.this.processRequest(this.request);
        }
    }
}
