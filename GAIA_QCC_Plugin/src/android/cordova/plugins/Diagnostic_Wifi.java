package cordova.plugins;

import android.content.Intent;
import android.net.wifi.WifiManager;
import android.util.Log;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes2.dex */
public class Diagnostic_Wifi extends CordovaPlugin {
    public static final String TAG = "Diagnostic_Wifi";
    public static Diagnostic_Wifi instance;
    protected CallbackContext currentContext;
    private Diagnostic diagnostic;

    @Override // org.apache.cordova.CordovaPlugin
    public void initialize(CordovaInterface cordovaInterface, CordovaWebView cordovaWebView) {
        Log.d(TAG, "initialize()");
        instance = this;
        this.diagnostic = Diagnostic.getInstance();
        super.initialize(cordovaInterface, cordovaWebView);
    }

    @Override // org.apache.cordova.CordovaPlugin
    public boolean execute(String str, JSONArray jSONArray, CallbackContext callbackContext) throws JSONException {
        Diagnostic diagnostic = Diagnostic.instance;
        this.currentContext = callbackContext;
        diagnostic.currentContext = callbackContext;
        try {
            if (str.equals("switchToWifiSettings")) {
                switchToWifiSettings();
                callbackContext.success();
            } else if (str.equals("isWifiAvailable")) {
                callbackContext.success(isWifiAvailable() ? 1 : 0);
            } else if (str.equals("setWifiState")) {
                setWifiState(jSONArray.getBoolean(0));
                callbackContext.success();
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

    public boolean isWifiAvailable() {
        return ((WifiManager) this.cordova.getActivity().getApplicationContext().getSystemService("wifi")).isWifiEnabled();
    }

    public void switchToWifiSettings() {
        this.diagnostic.logDebug("Switch to Wifi Settings");
        this.cordova.getActivity().startActivity(new Intent("android.settings.WIFI_SETTINGS"));
    }

    public void setWifiState(boolean z) {
        WifiManager wifiManager = (WifiManager) this.cordova.getActivity().getApplicationContext().getSystemService("wifi");
        if (z && !wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        } else {
            if (z || !wifiManager.isWifiEnabled()) {
                return;
            }
            wifiManager.setWifiEnabled(false);
        }
    }
}
