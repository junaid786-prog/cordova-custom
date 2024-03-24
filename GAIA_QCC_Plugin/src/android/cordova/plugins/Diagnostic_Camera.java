package cordova.plugins;

import android.hardware.Camera;
import android.os.Build;
import android.util.Log;
import java.util.Objects;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes2.dex */
public class Diagnostic_Camera extends CordovaPlugin {
    public static final String TAG = "Diagnostic_Camera";
    protected static final String cameraPermission = "CAMERA";
    public static Diagnostic_Camera instance;
    protected static String[] storagePermissions;
    protected CallbackContext currentContext;
    private Diagnostic diagnostic;

    static {
        if (Build.VERSION.SDK_INT >= 33) {
            storagePermissions = new String[]{"READ_MEDIA_IMAGES", "READ_MEDIA_VIDEO"};
        } else {
            storagePermissions = new String[]{"READ_EXTERNAL_STORAGE", "WRITE_EXTERNAL_STORAGE"};
        }
        instance = null;
    }

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
            if (str.equals("isCameraPresent")) {
                callbackContext.success(isCameraPresent() ? 1 : 0);
            } else if (str.equals("requestCameraAuthorization")) {
                requestCameraAuthorization(jSONArray, callbackContext);
            } else if (str.equals("getCameraAuthorizationStatus")) {
                getCameraAuthorizationStatus(jSONArray, callbackContext);
            } else {
                this.diagnostic.handleError("Invalid action");
                return false;
            }
            return true;
        } catch (Exception e) {
            this.diagnostic.handleError("Exception occurred: ".concat((String) Objects.requireNonNull(e.getMessage())));
            return false;
        }
    }

    public boolean isCameraPresent() {
        return this.cordova.getActivity().getPackageManager().hasSystemFeature(Build.VERSION.SDK_INT >= 32 ? "android.hardware.camera.any" : "android.hardware.camera") && Camera.getNumberOfCameras() > 0;
    }

    private String[] getPermissions(boolean z) {
        String[] strArr = {cameraPermission};
        return z ? Diagnostic.instance.concatStrings(strArr, storagePermissions) : strArr;
    }

    private void requestCameraAuthorization(JSONArray jSONArray, CallbackContext callbackContext) throws Exception {
        String[] permissions = getPermissions(jSONArray.getBoolean(0));
        Diagnostic.instance._requestRuntimePermissions(Diagnostic.instance.stringArrayToJsonArray(permissions), Diagnostic.instance.storeContextByRequestId(callbackContext));
    }

    private void getCameraAuthorizationStatus(JSONArray jSONArray, CallbackContext callbackContext) throws Exception {
        callbackContext.success(Diagnostic.instance._getPermissionsAuthorizationStatus(getPermissions(jSONArray.getBoolean(0))));
    }
}
