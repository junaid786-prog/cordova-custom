package cordova.plugins.qcc.GaiaLibrary.gaia;

import org.apache.cordova.CallbackContext;
import org.json.JSONArray;

/* loaded from: classes2.dex */
public class ActionCallbackContext {
    public String action;
    public CallbackContext callbackContext;
    public JSONArray userData;

    public ActionCallbackContext(CallbackContext callbackContext, String str) {
        this.callbackContext = callbackContext;
        this.action = str;
    }

    public ActionCallbackContext(CallbackContext callbackContext, String str, JSONArray jSONArray) {
        this.callbackContext = callbackContext;
        this.action = str;
        this.userData = jSONArray;
    }
}
