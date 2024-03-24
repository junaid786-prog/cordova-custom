package cordova.plugins.settings;

import android.telephony.TelephonyManager;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes2.dex */
public class DeviceSettings extends CordovaPlugin {
    private String TAG = "DeviceSettings";

    @Override // org.apache.cordova.CordovaPlugin
    public void onStart() {
    }

    @Override // org.apache.cordova.CordovaPlugin
    public void onStop() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.cordova.CordovaPlugin
    public void pluginInitialize() {
    }

    @Override // org.apache.cordova.CordovaPlugin
    public boolean execute(String str, JSONArray jSONArray, CallbackContext callbackContext) throws JSONException {
        if (!str.equals("getCountryCode")) {
            return false;
        }
        getCountryCode(jSONArray, callbackContext);
        return true;
    }

    private void getCountryCode(JSONArray jSONArray, CallbackContext callbackContext) {
        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, ((TelephonyManager) this.webView.getContext().getSystemService("phone")).getNetworkCountryIso()));
    }
}
