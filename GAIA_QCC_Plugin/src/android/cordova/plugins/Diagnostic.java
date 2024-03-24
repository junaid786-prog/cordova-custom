package cordova.plugins;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import kotlin.time.DurationKt;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class Diagnostic extends CordovaPlugin {
    public static final String CPU_ARCH_ARMv6 = "ARMv6";
    public static final String CPU_ARCH_ARMv7 = "ARMv7";
    public static final String CPU_ARCH_ARMv8 = "ARMv8";
    public static final String CPU_ARCH_MIPS = "MIPS";
    public static final String CPU_ARCH_MIPS_64 = "MIPS_64";
    public static final String CPU_ARCH_UNKNOWN = "unknown";
    public static final String CPU_ARCH_X86 = "X86";
    public static final String CPU_ARCH_X86_64 = "X86_64";
    protected static final Integer GET_EXTERNAL_SD_CARD_DETAILS_PERMISSION_REQUEST;
    protected static final String STATUS_DENIED_ALWAYS = "DENIED_ALWAYS";
    protected static final String STATUS_DENIED_ONCE = "DENIED_ONCE";
    protected static final String STATUS_GRANTED = "GRANTED";
    protected static final String STATUS_NOT_REQUESTED = "NOT_REQUESTED";
    public static final String TAG = "Diagnostic";
    protected static final String externalStorageClassName = "cordova.plugins.Diagnostic_External_Storage";
    public static Diagnostic instance;
    protected static final Map<String, String> permissionsMap;
    protected Context applicationContext;
    protected CallbackContext currentContext;
    protected SharedPreferences.Editor editor;
    protected SharedPreferences sharedPref;
    protected HashMap<String, CallbackContext> callbackContexts = new HashMap<>();
    protected HashMap<String, JSONObject> permissionStatuses = new HashMap<>();
    boolean debugEnabled = false;

    static {
        HashMap hashMap = new HashMap();
        addBiDirMapEntry(hashMap, "ACCESS_COARSE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION");
        addBiDirMapEntry(hashMap, "ACCESS_FINE_LOCATION", "android.permission.ACCESS_FINE_LOCATION");
        addBiDirMapEntry(hashMap, "ADD_VOICEMAIL", "android.permission.ADD_VOICEMAIL");
        addBiDirMapEntry(hashMap, "BODY_SENSORS", "android.permission.BODY_SENSORS");
        addBiDirMapEntry(hashMap, "CALL_PHONE", "android.permission.CALL_PHONE");
        addBiDirMapEntry(hashMap, "CAMERA", "android.permission.CAMERA");
        addBiDirMapEntry(hashMap, "GET_ACCOUNTS", "android.permission.GET_ACCOUNTS");
        addBiDirMapEntry(hashMap, "PROCESS_OUTGOING_CALLS", "android.permission.PROCESS_OUTGOING_CALLS");
        addBiDirMapEntry(hashMap, "READ_CALENDAR", "android.permission.READ_CALENDAR");
        addBiDirMapEntry(hashMap, "READ_CALL_LOG", "android.permission.READ_CALL_LOG");
        addBiDirMapEntry(hashMap, "READ_CONTACTS", "android.permission.READ_CONTACTS");
        addBiDirMapEntry(hashMap, "READ_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE");
        addBiDirMapEntry(hashMap, "READ_PHONE_STATE", "android.permission.READ_PHONE_STATE");
        addBiDirMapEntry(hashMap, "READ_SMS", "android.permission.READ_SMS");
        addBiDirMapEntry(hashMap, "RECEIVE_MMS", "android.permission.RECEIVE_MMS");
        addBiDirMapEntry(hashMap, "RECEIVE_SMS", "android.permission.RECEIVE_SMS");
        addBiDirMapEntry(hashMap, "RECEIVE_WAP_PUSH", "android.permission.RECEIVE_WAP_PUSH");
        addBiDirMapEntry(hashMap, "RECORD_AUDIO", "android.permission.RECORD_AUDIO");
        addBiDirMapEntry(hashMap, "SEND_SMS", "android.permission.SEND_SMS");
        addBiDirMapEntry(hashMap, "USE_SIP", "android.permission.USE_SIP");
        addBiDirMapEntry(hashMap, "WRITE_CALENDAR", "android.permission.WRITE_CALENDAR");
        addBiDirMapEntry(hashMap, "WRITE_CALL_LOG", "android.permission.WRITE_CALL_LOG");
        addBiDirMapEntry(hashMap, "WRITE_CONTACTS", "android.permission.WRITE_CONTACTS");
        addBiDirMapEntry(hashMap, "WRITE_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE");
        addBiDirMapEntry(hashMap, "ANSWER_PHONE_CALLS", "android.permission.ANSWER_PHONE_CALLS");
        addBiDirMapEntry(hashMap, "READ_PHONE_NUMBERS", "android.permission.READ_PHONE_NUMBERS");
        addBiDirMapEntry(hashMap, "ACCEPT_HANDOVER", "android.permission.ACCEPT_HANDOVER");
        addBiDirMapEntry(hashMap, "ACCESS_BACKGROUND_LOCATION", "android.permission.ACCESS_BACKGROUND_LOCATION");
        addBiDirMapEntry(hashMap, "ACCESS_MEDIA_LOCATION", "android.permission.ACCESS_MEDIA_LOCATION");
        addBiDirMapEntry(hashMap, "ACTIVITY_RECOGNITION", "android.permission.ACTIVITY_RECOGNITION");
        addBiDirMapEntry(hashMap, "BLUETOOTH_ADVERTISE", "android.permission.BLUETOOTH_ADVERTISE");
        addBiDirMapEntry(hashMap, "BLUETOOTH_CONNECT", "android.permission.BLUETOOTH_CONNECT");
        addBiDirMapEntry(hashMap, "BLUETOOTH_SCAN", "android.permission.BLUETOOTH_SCAN");
        addBiDirMapEntry(hashMap, "UWB_RANGING", "android.permission.UWB_RANGING");
        addBiDirMapEntry(hashMap, "BODY_SENSORS_BACKGROUND", "android.permission.BODY_SENSORS_BACKGROUND");
        addBiDirMapEntry(hashMap, "NEARBY_WIFI_DEVICES", "android.permission.NEARBY_WIFI_DEVICES");
        addBiDirMapEntry(hashMap, "POST_NOTIFICATIONS", "android.permission.POST_NOTIFICATIONS");
        addBiDirMapEntry(hashMap, "READ_MEDIA_AUDIO", "android.permission.READ_MEDIA_AUDIO");
        addBiDirMapEntry(hashMap, "READ_MEDIA_IMAGES", "android.permission.READ_MEDIA_IMAGES");
        addBiDirMapEntry(hashMap, "READ_MEDIA_VIDEO", "android.permission.READ_MEDIA_VIDEO");
        permissionsMap = Collections.unmodifiableMap(hashMap);
        GET_EXTERNAL_SD_CARD_DETAILS_PERMISSION_REQUEST = 1000;
        instance = null;
    }

    public static Diagnostic getInstance() {
        return instance;
    }

    @Override // org.apache.cordova.CordovaPlugin
    public void initialize(CordovaInterface cordovaInterface, CordovaWebView cordovaWebView) {
        Log.d(TAG, "initialize()");
        instance = this;
        this.applicationContext = this.cordova.getActivity().getApplicationContext();
        SharedPreferences sharedPreferences = cordovaInterface.getActivity().getSharedPreferences(TAG, 0);
        this.sharedPref = sharedPreferences;
        this.editor = sharedPreferences.edit();
        super.initialize(cordovaInterface, cordovaWebView);
    }

    @Override // org.apache.cordova.CordovaPlugin
    public boolean execute(String str, JSONArray jSONArray, CallbackContext callbackContext) throws JSONException {
        this.currentContext = callbackContext;
        try {
            if (str.equals("enableDebug")) {
                this.debugEnabled = true;
                logDebug("Debug enabled");
                callbackContext.success();
            } else if (str.equals("switchToSettings")) {
                switchToAppSettings();
                callbackContext.success();
            } else if (str.equals("switchToMobileDataSettings")) {
                switchToMobileDataSettings();
                callbackContext.success();
            } else if (str.equals("switchToWirelessSettings")) {
                switchToWirelessSettings();
                callbackContext.success();
            } else if (str.equals("isDataRoamingEnabled")) {
                if (Build.VERSION.SDK_INT <= 32) {
                    callbackContext.success(isDataRoamingEnabled() ? 1 : 0);
                } else {
                    callbackContext.error("Data roaming setting not available on Android 12L / API32+");
                }
                callbackContext.success(isDataRoamingEnabled() ? 1 : 0);
            } else if (str.equals("getPermissionAuthorizationStatus")) {
                getPermissionAuthorizationStatus(jSONArray);
            } else if (str.equals("getPermissionsAuthorizationStatus")) {
                getPermissionsAuthorizationStatus(jSONArray);
            } else if (str.equals("requestRuntimePermission")) {
                requestRuntimePermission(jSONArray);
            } else if (str.equals("requestRuntimePermissions")) {
                requestRuntimePermissions(jSONArray);
            } else if (str.equals("isADBModeEnabled")) {
                callbackContext.success(isADBModeEnabled() ? 1 : 0);
            } else if (str.equals("isDeviceRooted")) {
                callbackContext.success(isDeviceRooted() ? 1 : 0);
            } else if (str.equals("isMobileDataEnabled")) {
                callbackContext.success(isMobileDataEnabled() ? 1 : 0);
            } else if (str.equals("restart")) {
                restart(jSONArray);
            } else if (str.equals("getArchitecture")) {
                callbackContext.success(getCPUArchitecture());
            } else if (str.equals("getCurrentBatteryLevel")) {
                callbackContext.success(getCurrentBatteryLevel());
            } else if (str.equals("isAirplaneModeEnabled")) {
                callbackContext.success(isAirplaneModeEnabled() ? 1 : 0);
            } else if (str.equals("getDeviceOSVersion")) {
                callbackContext.success(getDeviceOSVersion());
            } else if (str.equals("getBuildOSVersion")) {
                callbackContext.success(getBuildOSVersion());
            } else {
                handleError("Invalid action");
                return false;
            }
            return true;
        } catch (Exception e) {
            handleError("Exception occurred: ".concat(e.getMessage()));
            return false;
        }
    }

    public void restart(JSONArray jSONArray) throws Exception {
        if (jSONArray.getBoolean(0)) {
            doColdRestart();
        } else {
            doWarmRestart();
        }
    }

    public boolean isDataRoamingEnabled() throws Exception {
        return Settings.Global.getInt(this.cordova.getActivity().getContentResolver(), "data_roaming", 0) == 1;
    }

    public void switchToAppSettings() {
        logDebug("Switch to App Settings");
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", this.cordova.getActivity().getPackageName(), null));
        this.cordova.getActivity().startActivity(intent);
    }

    public void switchToMobileDataSettings() {
        logDebug("Switch to Mobile Data Settings");
        this.cordova.getActivity().startActivity(new Intent("android.settings.DATA_ROAMING_SETTINGS"));
    }

    public void switchToWirelessSettings() {
        logDebug("Switch to wireless Settings");
        this.cordova.getActivity().startActivity(new Intent("android.settings.WIRELESS_SETTINGS"));
    }

    public void getPermissionsAuthorizationStatus(JSONArray jSONArray) throws Exception {
        this.currentContext.success(_getPermissionsAuthorizationStatus(jsonArrayToStringArray(jSONArray.getJSONArray(0))));
    }

    public void getPermissionAuthorizationStatus(JSONArray jSONArray) throws Exception {
        String string = jSONArray.getString(0);
        JSONArray jSONArray2 = new JSONArray();
        jSONArray2.put(string);
        this.currentContext.success(_getPermissionsAuthorizationStatus(jsonArrayToStringArray(jSONArray2)).getString(string));
    }

    public void requestRuntimePermissions(JSONArray jSONArray) throws Exception {
        _requestRuntimePermissions(jSONArray.getJSONArray(0), storeCurrentContextByRequestId());
    }

    public void requestRuntimePermission(JSONArray jSONArray) throws Exception {
        requestRuntimePermission(jSONArray.getString(0));
    }

    public void requestRuntimePermission(String str) throws Exception {
        requestRuntimePermission(str, storeCurrentContextByRequestId());
    }

    public void requestRuntimePermission(String str, int i) throws Exception {
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(str);
        _requestRuntimePermissions(jSONArray, i);
    }

    public int getADBMode() {
        return Settings.Global.getInt(this.applicationContext.getContentResolver(), "adb_enabled", 0);
    }

    public boolean isADBModeEnabled() {
        boolean z = false;
        try {
            if (getADBMode() == 1) {
                z = true;
            }
        } catch (Exception e) {
            logError(e.getMessage());
        }
        logDebug("ADB mode enabled: " + z);
        return z;
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x006e, code lost:
    
        if (r2 != null) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0080, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x007d, code lost:
    
        r2.destroy();
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x007b, code lost:
    
        if (r2 == null) goto L31;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean isDeviceRooted() {
        /*
            r11 = this;
            java.lang.String r0 = android.os.Build.TAGS
            r1 = 1
            if (r0 == 0) goto Le
            java.lang.String r2 = "test-keys"
            boolean r0 = r0.contains(r2)
            if (r0 == 0) goto Le
            return r1
        Le:
            r0 = 0
            java.lang.String r2 = "/system/app/Superuser.apk"
            java.lang.String r3 = "/sbin/su"
            java.lang.String r4 = "/system/bin/su"
            java.lang.String r5 = "/system/xbin/su"
            java.lang.String r6 = "/data/local/xbin/su"
            java.lang.String r7 = "/data/local/bin/su"
            java.lang.String r8 = "/system/sd/xbin/su"
            java.lang.String r9 = "/system/bin/failsafe/su"
            java.lang.String r10 = "/data/local/su"
            java.lang.String[] r2 = new java.lang.String[]{r2, r3, r4, r5, r6, r7, r8, r9, r10}     // Catch: java.lang.Exception -> L3b
            r3 = r0
        L26:
            r4 = 9
            if (r3 >= r4) goto L43
            r4 = r2[r3]     // Catch: java.lang.Exception -> L3b
            java.io.File r5 = new java.io.File     // Catch: java.lang.Exception -> L3b
            r5.<init>(r4)     // Catch: java.lang.Exception -> L3b
            boolean r4 = r5.exists()     // Catch: java.lang.Exception -> L3b
            if (r4 == 0) goto L38
            return r1
        L38:
            int r3 = r3 + 1
            goto L26
        L3b:
            r2 = move-exception
            java.lang.String r2 = r2.getMessage()
            r11.logDebug(r2)
        L43:
            r2 = 0
            java.lang.Runtime r3 = java.lang.Runtime.getRuntime()     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L73
            java.lang.String r4 = "/system/xbin/which"
            java.lang.String r5 = "su"
            java.lang.String[] r4 = new java.lang.String[]{r4, r5}     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L73
            java.lang.Process r2 = r3.exec(r4)     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L73
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L73
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L73
            java.io.InputStream r5 = r2.getInputStream()     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L73
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L73
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L73
            java.lang.String r3 = r3.readLine()     // Catch: java.lang.Throwable -> L71 java.lang.Exception -> L73
            if (r3 == 0) goto L6e
            if (r2 == 0) goto L6d
            r2.destroy()
        L6d:
            return r1
        L6e:
            if (r2 == 0) goto L80
            goto L7d
        L71:
            r0 = move-exception
            goto L81
        L73:
            r1 = move-exception
            java.lang.String r1 = r1.getMessage()     // Catch: java.lang.Throwable -> L71
            r11.logDebug(r1)     // Catch: java.lang.Throwable -> L71
            if (r2 == 0) goto L80
        L7d:
            r2.destroy()
        L80:
            return r0
        L81:
            if (r2 == 0) goto L86
            r2.destroy()
        L86:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cordova.plugins.Diagnostic.isDeviceRooted():boolean");
    }

    public boolean isMobileDataEnabled() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.cordova.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        try {
            Method declaredMethod = Class.forName(connectivityManager.getClass().getName()).getDeclaredMethod("getMobileDataEnabled", new Class[0]);
            declaredMethod.setAccessible(true);
            return ((Boolean) declaredMethod.invoke(connectivityManager, new Object[0])).booleanValue();
        } catch (Exception e) {
            logDebug(e.getMessage());
            return false;
        }
    }

    public void logDebug(String str) {
        if (str != null && this.debugEnabled) {
            Log.d(TAG, str);
            executeGlobalJavascript("console.log(\"Diagnostic[native]: " + escapeDoubleQuotes(str) + "\")");
        }
    }

    public void logInfo(String str) {
        if (str == null) {
            return;
        }
        Log.i(TAG, str);
        if (this.debugEnabled) {
            executeGlobalJavascript("console.info(\"Diagnostic[native]: " + escapeDoubleQuotes(str) + "\")");
        }
    }

    public void logWarning(String str) {
        if (str == null) {
            return;
        }
        Log.w(TAG, str);
        if (this.debugEnabled) {
            executeGlobalJavascript("console.warn(\"Diagnostic[native]: " + escapeDoubleQuotes(str) + "\")");
        }
    }

    public void logError(String str) {
        if (str == null) {
            return;
        }
        Log.e(TAG, str);
        if (this.debugEnabled) {
            executeGlobalJavascript("console.error(\"Diagnostic[native]: " + escapeDoubleQuotes(str) + "\")");
        }
    }

    public String escapeDoubleQuotes(String str) {
        return str.replace("\"", "\\\"").replace("%22", "\\%22");
    }

    public void handleError(String str, CallbackContext callbackContext) {
        try {
            logError(str);
            callbackContext.error(str);
        } catch (Exception e) {
            logError(e.toString());
        }
    }

    public void handleError(String str) {
        handleError(str, this.currentContext);
    }

    public void handleError(String str, int i) {
        CallbackContext callbackContext;
        String valueOf = String.valueOf(i);
        if (this.callbackContexts.containsKey(valueOf)) {
            callbackContext = this.callbackContexts.get(valueOf);
        } else {
            callbackContext = this.currentContext;
        }
        handleError(str, callbackContext);
        clearRequest(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JSONObject _getPermissionsAuthorizationStatus(String[] strArr) throws Exception {
        JSONObject jSONObject = new JSONObject();
        for (int i = 0; i < strArr.length; i++) {
            String str = strArr[i];
            Map<String, String> map = permissionsMap;
            if (!map.containsKey(str)) {
                throw new Exception("Permission name '" + str + "' is not a valid permission");
            }
            if (Build.VERSION.SDK_INT < 29 && str.equals("ACCESS_BACKGROUND_LOCATION")) {
                str = "ACCESS_COARSE_LOCATION";
            }
            if (Build.VERSION.SDK_INT < 29 && str.equals("ACTIVITY_RECOGNITION")) {
                str = "BODY_SENSORS";
            }
            String str2 = map.get(str);
            Log.v(TAG, "Get authorisation status for " + str2);
            if (hasRuntimePermission(str2)) {
                jSONObject.put(str, STATUS_GRANTED);
            } else if (!shouldShowRequestPermissionRationale(this.cordova.getActivity(), str2)) {
                if (isPermissionRequested(str)) {
                    jSONObject.put(str, STATUS_DENIED_ALWAYS);
                } else {
                    jSONObject.put(str, STATUS_NOT_REQUESTED);
                }
            } else {
                jSONObject.put(str, STATUS_DENIED_ONCE);
            }
        }
        return jSONObject;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void _requestRuntimePermissions(JSONArray jSONArray, int i) throws Exception {
        JSONObject _getPermissionsAuthorizationStatus = _getPermissionsAuthorizationStatus(jsonArrayToStringArray(jSONArray));
        JSONArray jSONArray2 = new JSONArray();
        for (int i2 = 0; i2 < _getPermissionsAuthorizationStatus.names().length(); i2++) {
            String string = _getPermissionsAuthorizationStatus.names().getString(i2);
            if (_getPermissionsAuthorizationStatus.getString(string) == STATUS_GRANTED) {
                Log.d(TAG, "Permission already granted for " + string);
                JSONObject jSONObject = this.permissionStatuses.get(String.valueOf(i));
                jSONObject.put(string, STATUS_GRANTED);
                this.permissionStatuses.put(String.valueOf(i), jSONObject);
            } else {
                String str = permissionsMap.get(string);
                Log.d(TAG, "Requesting permission for " + str);
                jSONArray2.put(str);
            }
        }
        if (jSONArray2.length() > 0) {
            Log.v(TAG, "Requesting permissions");
            requestPermissions(this, i, jsonArrayToStringArray(jSONArray2));
        } else {
            Log.d(TAG, "No permissions to request: returning result");
            sendRuntimeRequestResult(i);
        }
    }

    protected void sendRuntimeRequestResult(int i) {
        String valueOf = String.valueOf(i);
        CallbackContext callbackContext = this.callbackContexts.get(valueOf);
        JSONObject jSONObject = this.permissionStatuses.get(valueOf);
        Log.v(TAG, "Sending runtime request result for id=" + valueOf);
        callbackContext.success(jSONObject);
    }

    protected int storeCurrentContextByRequestId() {
        return storeContextByRequestId(this.currentContext);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int storeContextByRequestId(CallbackContext callbackContext) {
        String generateRandomRequestId = generateRandomRequestId();
        this.callbackContexts.put(generateRandomRequestId, callbackContext);
        this.permissionStatuses.put(generateRandomRequestId, new JSONObject());
        return Integer.valueOf(generateRandomRequestId).intValue();
    }

    protected String generateRandomRequestId() {
        while (true) {
            String str = null;
            while (str == null) {
                str = generateRandom();
                if (this.callbackContexts.containsKey(str)) {
                    break;
                }
            }
            return str;
        }
    }

    protected String generateRandom() {
        return Integer.toString(new Random().nextInt() + 1);
    }

    protected String[] jsonArrayToStringArray(JSONArray jSONArray) throws JSONException {
        if (jSONArray == null) {
            return null;
        }
        int length = jSONArray.length();
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            strArr[i] = jSONArray.optString(i);
        }
        return strArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JSONArray stringArrayToJsonArray(String[] strArr) throws JSONException {
        if (strArr == null) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < strArr.length; i++) {
            jSONArray.put(i, strArr[i]);
        }
        return jSONArray;
    }

    protected CallbackContext getContextById(String str) throws Exception {
        if (!this.callbackContexts.containsKey(str)) {
            throw new Exception("No context found for request id=" + str);
        }
        return this.callbackContexts.get(str);
    }

    protected void clearRequest(int i) {
        String valueOf = String.valueOf(i);
        if (this.callbackContexts.containsKey(valueOf)) {
            this.callbackContexts.remove(valueOf);
            this.permissionStatuses.remove(valueOf);
        }
    }

    protected static void addBiDirMapEntry(Map map, Object obj, Object obj2) {
        map.put(obj, obj2);
        map.put(obj2, obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean hasRuntimePermission(String str) throws Exception {
        try {
            return ((Boolean) this.cordova.getClass().getMethod("hasPermission", str.getClass()).invoke(this.cordova, str)).booleanValue();
        } catch (NoSuchMethodException unused) {
            logWarning("Cordova v10.1.1 does not support runtime permissions so defaulting to GRANTED for " + str);
            return true;
        }
    }

    protected void requestPermissions(CordovaPlugin cordovaPlugin, int i, String[] strArr) throws Exception {
        try {
            this.cordova.getClass().getMethod("requestPermissions", CordovaPlugin.class, Integer.TYPE, String[].class).invoke(this.cordova, cordovaPlugin, Integer.valueOf(i), strArr);
            for (String str : strArr) {
                setPermissionRequested(permissionsMap.get(str));
            }
        } catch (NoSuchMethodException unused) {
            throw new Exception("requestPermissions() method not found in CordovaInterface implementation of Cordova v10.1.1");
        }
    }

    protected boolean shouldShowRequestPermissionRationale(Activity activity, String str) throws Exception {
        try {
            return ((Boolean) ActivityCompat.class.getMethod("shouldShowRequestPermissionRationale", Activity.class, String.class).invoke(null, activity, str)).booleanValue();
        } catch (NoSuchMethodException unused) {
            throw new Exception("shouldShowRequestPermissionRationale() method not found in ActivityCompat class.");
        }
    }

    public void executeGlobalJavascript(final String str) {
        this.cordova.getActivity().runOnUiThread(new Runnable() { // from class: cordova.plugins.Diagnostic.1
            @Override // java.lang.Runnable
            public void run() {
                Diagnostic.this.webView.loadUrl("javascript:" + str);
            }
        });
    }

    public void executePluginJavascript(String str) {
        executeGlobalJavascript("cordova.plugins.diagnostic." + str);
    }

    protected void doWarmRestart() {
        this.cordova.getActivity().runOnUiThread(new Runnable() { // from class: cordova.plugins.Diagnostic.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Diagnostic.this.logInfo("Warm restarting main activity");
                    Diagnostic.instance.cordova.getActivity().recreate();
                } catch (Exception e) {
                    Diagnostic.this.handleError("Unable to warm restart main activity: " + e.getMessage());
                }
            }
        });
    }

    protected void doColdRestart() {
        try {
            logInfo("Cold restarting application");
            AppCompatActivity activity = instance.cordova.getActivity();
            if (activity != null) {
                Intent launchIntentForPackage = activity.getPackageManager().getLaunchIntentForPackage(activity.getPackageName());
                activity.finishAffinity();
                activity.startActivity(launchIntentForPackage);
                System.exit(0);
            } else {
                handleError("Unable to cold restart application: Activity is null");
            }
        } catch (Exception e) {
            handleError("Unable to cold restart application: " + e.getMessage());
        }
    }

    protected String getCPUArchitecture() {
        String str = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            str = Build.SUPPORTED_ABIS[0];
        }
        return str == "armeabi" ? CPU_ARCH_ARMv6 : str.equals("armeabi-v7a") ? CPU_ARCH_ARMv7 : str.equals("arm64-v8a") ? CPU_ARCH_ARMv8 : str.equals("x86") ? CPU_ARCH_X86 : str.equals("x86_64") ? CPU_ARCH_X86_64 : str.equals("mips") ? CPU_ARCH_MIPS : str.equals("mips64") ? CPU_ARCH_MIPS_64 : "unknown";
    }

    protected void setPermissionRequested(String str) {
        this.editor.putBoolean(str, true);
        if (this.editor.commit()) {
            return;
        }
        handleError("Failed to set permission requested flag for " + str);
    }

    protected boolean isPermissionRequested(String str) {
        return this.sharedPref.getBoolean(str, false);
    }

    protected int getCurrentBatteryLevel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return ((BatteryManager) this.cordova.getContext().getApplicationContext().getSystemService(Context.BATTERY_SERVICE)).getIntProperty(4);
        } else
            return 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean hasBuildPermission(String str) {
        try {
            PackageInfo packageInfo = this.cordova.getActivity().getPackageManager().getPackageInfo(this.cordova.getContext().getPackageName(), 4096);
            if (packageInfo.requestedPermissions != null) {
                for (String str2 : packageInfo.requestedPermissions) {
                    if (str2.equals("android.permission." + str)) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isAirplaneModeEnabled() {
        return Settings.Global.getInt(this.cordova.getActivity().getContentResolver(), "airplane_mode_on", 0) != 0;
    }

    public JSONObject getDeviceOSVersion() throws Exception {
        JSONObject jSONObject = new JSONObject();
        //jSONObject.put(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION, Build.VERSION.RELEASE);
        jSONObject.put("apiLevel", Build.VERSION.SDK_INT);
        jSONObject.put("apiName", getNameForApiLevel(Build.VERSION.SDK_INT));
        return jSONObject;
    }

    public JSONObject getBuildOSVersion() throws Exception {
        int i = 0;
        JSONObject jSONObject = new JSONObject();
        AppCompatActivity activity = instance.cordova.getActivity();
        int i2 = 0;
        ApplicationInfo applicationInfo = activity.getPackageManager().getApplicationInfo(activity.getPackageName(), 0);
        if (applicationInfo != null) {
            i2 = applicationInfo.targetSdkVersion;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                i = applicationInfo.minSdkVersion;
            }
        } else {
            i = 0;
        }
        jSONObject.put("targetApiLevel", i2);
        jSONObject.put("targetApiName", getNameForApiLevel(i2));
        jSONObject.put("minApiLevel", i);
        jSONObject.put("minApiName", getNameForApiLevel(i));
        return jSONObject;
    }

    protected String getNameForApiLevel(int i) throws Exception {
        String str = "UNKNOWN";
        for (Field field : Build.VERSION_CODES.class.getFields()) {
            if (field.getInt(Build.VERSION_CODES.class) == i) {
                str = field.getName();
            }
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String[] concatStrings(String[] strArr, String[] strArr2) {
        int length = strArr.length;
        int length2 = strArr2.length;
        String[] strArr3 = new String[length + length2];
        System.arraycopy(strArr, 0, strArr3, 0, length);
        System.arraycopy(strArr2, 0, strArr3, length, length2);
        return strArr3;
    }

    @Override // org.apache.cordova.CordovaPlugin
    public void onRequestPermissionResult(int i, String[] strArr, int[] iArr) throws JSONException {
        Class<?> cls;
        String str;
        String valueOf = String.valueOf(i);
        Log.v(TAG, "Received result for permissions request id=" + valueOf);
        try {
            CallbackContext contextById = getContextById(valueOf);
            JSONObject jSONObject = this.permissionStatuses.get(valueOf);
            int length = strArr.length;
            for (int i2 = 0; i2 < length; i2++) {
                String str2 = strArr[i2];
                String str3 = permissionsMap.get(str2);
                if (Build.VERSION.SDK_INT < 29 && str3.equals("ACCESS_BACKGROUND_LOCATION")) {
                    str3 = "ACCESS_COARSE_LOCATION";
                }
                if (Build.VERSION.SDK_INT < 29 && str3.equals("ACTIVITY_RECOGNITION")) {
                    str3 = "BODY_SENSORS";
                }
                if (iArr[i2] != -1) {
                    str = STATUS_GRANTED;
                } else if (shouldShowRequestPermissionRationale(this.cordova.getActivity(), str2)) {
                    str = STATUS_DENIED_ONCE;
                } else {
                    str = isPermissionRequested(str3) ? STATUS_DENIED_ALWAYS : STATUS_NOT_REQUESTED;
                }
                jSONObject.put(str3, str);
                Log.v(TAG, "Authorisation for " + str3 + " is " + jSONObject.get(str3));
                clearRequest(i);
            }
            try {
                cls = Class.forName(externalStorageClassName);
            } catch (ClassNotFoundException unused) {
                cls = null;
            }
            if (i == GET_EXTERNAL_SD_CARD_DETAILS_PERMISSION_REQUEST.intValue() && cls != null) {
                cls.getMethod("onReceivePermissionResult", new Class[0]).invoke(null, new Object[0]);
            } else {
                contextById.success(jSONObject);
            }
        } catch (Exception e) {
            handleError("Exception occurred onRequestPermissionsResult: ".concat(e.getMessage()), i);
        }
    }
}
