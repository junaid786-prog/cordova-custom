package cordova.plugins;

import android.os.Environment;
import android.os.StatFs;
import android.util.Log;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class Diagnostic_External_Storage extends CordovaPlugin {
    public static final String TAG = "Diagnostic_External_Storage";
    protected static String externalStoragePermission = "READ_EXTERNAL_STORAGE";
    public static Diagnostic_External_Storage instance;
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
            if (str.equals("getExternalSdCardDetails")) {
                getExternalSdCardDetails();
                return true;
            }
            this.diagnostic.handleError("Invalid action");
            return false;
        } catch (Exception e) {
            this.diagnostic.handleError("Exception occurred: ".concat(e.getMessage()));
            return false;
        }
    }

    public static void onReceivePermissionResult() throws JSONException {
        instance._getExternalSdCardDetails();
    }

    protected void getExternalSdCardDetails() throws Exception {
        String str = Diagnostic.permissionsMap.get(externalStoragePermission);
        if (this.diagnostic.hasRuntimePermission(str)) {
            _getExternalSdCardDetails();
        } else {
            this.diagnostic.requestRuntimePermission(str, Diagnostic.GET_EXTERNAL_SD_CARD_DETAILS_PERMISSION_REQUEST.intValue());
        }
    }

    protected void _getExternalSdCardDetails() throws JSONException {
        String[] storageDirectories = getStorageDirectories();
        JSONArray jSONArray = new JSONArray();
        for (String str : storageDirectories) {
            File file = new File(str);
            JSONObject jSONObject = new JSONObject();
            if (file.canRead()) {
                jSONObject.put("path", str);
                jSONObject.put("filePath", "file://" + str);
                jSONObject.put("canWrite", file.canWrite());
                jSONObject.put("freeSpace", getFreeSpaceInBytes(str));
                if (str.contains("Android")) {
                    jSONObject.put("type", "application");
                } else {
                    jSONObject.put("type", "root");
                }
                jSONArray.put(jSONObject);
            }
        }
        this.currentContext.success(jSONArray);
    }

    protected long getFreeSpaceInBytes(String str) {
        try {
            StatFs statFs = new StatFs(str);
            return statFs.getAvailableBlocks() * statFs.getBlockSize();
        } catch (IllegalArgumentException unused) {
            return 0L;
        }
    }

    protected String[] getStorageDirectories() {
        ArrayList arrayList = new ArrayList();
        for (File file : this.cordova.getActivity().getApplicationContext().getExternalFilesDirs(null)) {
            if (file != null) {
                String path = file.getPath();
                String str = path.split("/Android")[0];
                if (Environment.isExternalStorageRemovable(file)) {
                    arrayList.add(str);
                    arrayList.add(path);
                }
            }
        }
        if (arrayList.isEmpty()) {
            String str2 = "";
            try {
                Process start = new ProcessBuilder(new String[0]).command("mount | grep /dev/block/vold").redirectErrorStream(true).start();
                start.waitFor();
                InputStream inputStream = start.getInputStream();
                byte[] bArr = new byte[1024];
                while (inputStream.read(bArr) != -1) {
                    str2 = str2 + new String(bArr);
                }
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!str2.trim().isEmpty()) {
                for (String str3 : str2.split("\n")) {
                    arrayList.add(str3.split(" ")[2]);
                }
            }
        }
        int i = 0;
        while (i < arrayList.size()) {
            if (!((String) arrayList.get(i)).toLowerCase().matches(".*[0-9a-f]{4}[-][0-9a-f]{4}.*")) {
                this.diagnostic.logDebug(((String) arrayList.get(i)) + " might not be extSDcard");
                arrayList.remove(i);
                i--;
            }
            i++;
        }
        String[] strArr = new String[arrayList.size()];
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            strArr[i2] = (String) arrayList.get(i2);
        }
        return strArr;
    }
}
