package cordova.plugins;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.util.Log;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes2.dex */
public class Diagnostic_NFC extends CordovaPlugin {
    public static final String NFC_STATE_OFF = "powered_off";
    public static final String NFC_STATE_ON = "powered_on";
    public static final String NFC_STATE_TURNING_OFF = "powering_off";
    public static final String NFC_STATE_TURNING_ON = "powering_on";
    public static final String NFC_STATE_UNKNOWN = "unknown";
    public static final int NFC_STATE_VALUE_OFF = 1;
    public static final int NFC_STATE_VALUE_ON = 3;
    public static final int NFC_STATE_VALUE_TURNING_OFF = 4;
    public static final int NFC_STATE_VALUE_TURNING_ON = 2;
    public static final int NFC_STATE_VALUE_UNKNOWN = 0;
    public static final String TAG = "Diagnostic_NFC";
    public static Diagnostic_NFC instance;
    public static NfcManager nfcManager;
    protected CallbackContext currentContext;
    private Diagnostic diagnostic;
    protected String currentNFCState = "unknown";
    protected final BroadcastReceiver NFCStateChangedReceiver = new BroadcastReceiver() { // from class: cordova.plugins.Diagnostic_NFC.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            try {
                String action = intent.getAction();
                if (Diagnostic_NFC.instance == null || !action.equals("android.nfc.action.ADAPTER_STATE_CHANGED")) {
                    return;
                }
                Log.v(Diagnostic_NFC.TAG, "onReceiveNFCStateChange");
                Diagnostic_NFC.instance.notifyNFCStateChange(intent.getIntExtra("android.nfc.extra.ADAPTER_STATE", -1));
            } catch (Exception e) {
                Diagnostic_NFC.this.diagnostic.logError("Error receiving NFC state change: " + e.toString());
            }
        }
    };

    public String getNFCState(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? "unknown" : NFC_STATE_TURNING_OFF : NFC_STATE_ON : NFC_STATE_TURNING_ON : NFC_STATE_OFF;
    }

    @Override // org.apache.cordova.CordovaPlugin
    public void initialize(CordovaInterface cordovaInterface, CordovaWebView cordovaWebView) {
        Log.d(TAG, "initialize()");
        instance = this;
        Diagnostic diagnostic = Diagnostic.getInstance();
        this.diagnostic = diagnostic;
        try {
            diagnostic.applicationContext.registerReceiver(this.NFCStateChangedReceiver, new IntentFilter("android.nfc.action.ADAPTER_STATE_CHANGED"));
            nfcManager = (NfcManager) this.diagnostic.applicationContext.getSystemService("nfc");
        } catch (Exception e) {
            this.diagnostic.logWarning("Unable to register NFC state change receiver: " + e.getMessage());
        }
        try {
            this.currentNFCState = isNFCAvailable() ? NFC_STATE_ON : NFC_STATE_OFF;
        } catch (Exception e2) {
            this.diagnostic.logWarning("Unable to get initial NFC state: " + e2.getMessage());
        }
        super.initialize(cordovaInterface, cordovaWebView);
    }

    @Override // org.apache.cordova.CordovaPlugin
    public void onDestroy() {
        try {
            this.diagnostic.applicationContext.unregisterReceiver(this.NFCStateChangedReceiver);
        } catch (Exception e) {
            this.diagnostic.logWarning("Unable to unregister NFC state change receiver: " + e.getMessage());
        }
    }

    @Override // org.apache.cordova.CordovaPlugin
    public boolean execute(String str, JSONArray jSONArray, CallbackContext callbackContext) throws JSONException {
        Diagnostic diagnostic = Diagnostic.instance;
        this.currentContext = callbackContext;
        diagnostic.currentContext = callbackContext;
        try {
            if (str.equals("switchToNFCSettings")) {
                switchToNFCSettings();
                callbackContext.success();
            } else if (str.equals("isNFCPresent")) {
                callbackContext.success(isNFCPresent() ? 1 : 0);
            } else if (str.equals("isNFCEnabled")) {
                callbackContext.success(isNFCEnabled() ? 1 : 0);
            } else if (str.equals("isNFCAvailable")) {
                callbackContext.success(isNFCAvailable() ? 1 : 0);
            } else {
                this.diagnostic.handleError("Invalid action");
                return false;
            }
            return true;
        } catch (Exception e) {
            this.diagnostic.handleError("Exception occurred: ".concat(e.getMessage()));
            return false;
        }
    }

    public void switchToNFCSettings() {
        this.diagnostic.logDebug("Switch to NFC Settings");
        new Intent("android.settings.WIRELESS_SETTINGS");
        this.cordova.getActivity().startActivity(new Intent("android.settings.NFC_SETTINGS"));
    }

    public boolean isNFCPresent() {
        try {
            return nfcManager.getDefaultAdapter() != null;
        } catch (Exception e) {
            this.diagnostic.logError(e.getMessage());
            return false;
        }
    }

    public boolean isNFCEnabled() {
        try {
            NfcAdapter defaultAdapter = nfcManager.getDefaultAdapter();
            if (defaultAdapter != null) {
                return defaultAdapter.isEnabled();
            }
            return false;
        } catch (Exception e) {
            this.diagnostic.logError(e.getMessage());
            return false;
        }
    }

    public boolean isNFCAvailable() {
        return isNFCPresent() && isNFCEnabled();
    }

    public void notifyNFCStateChange(int i) {
        String nFCState = getNFCState(i);
        try {
            if (nFCState != this.currentNFCState) {
                this.diagnostic.logDebug("NFC state changed to: " + nFCState);
                this.diagnostic.executePluginJavascript("nfc._onNFCStateChange(\"" + nFCState + "\");");
                this.currentNFCState = nFCState;
            }
        } catch (Exception e) {
            this.diagnostic.logError("Error retrieving current NFC state on state change: " + e.toString());
        }
    }
}
