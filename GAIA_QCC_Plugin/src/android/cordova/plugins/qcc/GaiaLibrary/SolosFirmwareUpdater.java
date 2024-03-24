package cordova.plugins.qcc.GaiaLibrary;

import android.os.SystemClock;
import android.util.Log;
import cordova.plugins.qcc.BTLibrary.libraries.vmupgrade.UpgradeError;
import cordova.plugins.qcc.BTLibrary.services.BluetoothService;
import cordova.plugins.qcc.BTLibrary.services.GAIAGATTBLEService;
import java.io.File;

/* loaded from: classes2.dex */
public class SolosFirmwareUpdater {
    private OnGaiaUpgradeEventListener mListener;
    private BluetoothService mService;
    private long mStartTime = 0;

    public void onReceiveGattMessage(int i, Object obj) {
    }

    public void onReceiveUpgradeMessage(int i, Object obj) {
        BluetoothService bluetoothService;
        if (i == 0) {
            this.mStartTime = 0L;
            this.mListener.onFWUpgradeStateChange(4);
            enableUpgrade(false);
            return;
        }
        if (i == 1) {
            askForConfirmation(((Integer) obj).intValue());
            return;
        }
        if (i == 2) {
            if (((Integer) obj).intValue() == 0 && this.mStartTime == 0) {
                this.mStartTime = SystemClock.elapsedRealtime();
                return;
            }
            return;
        }
        if (i != 3) {
            if (i != 4) {
                return;
            }
            double doubleValue = ((Double) obj).doubleValue();
            Log.d("ContentValues", "upgrade (%): " + doubleValue);
            this.mListener.onFWUpgradeProgressAvailable(doubleValue);
            return;
        }
        this.mStartTime = 0L;
        int returnCode = ((UpgradeError) obj).getReturnCode();
        if (returnCode == 35 && (bluetoothService = this.mService) != null) {
            bluetoothService.abortUpgrade();
        }
        this.mListener.onFWUpgradeError(returnCode);
        enableUpgrade(false);
    }

    public void setBluetoothService(BluetoothService bluetoothService) {
        this.mService = bluetoothService;
    }

    public void startFirmwareUpgrade(File file) {
        if (this.mService != null) {
            setFirmwareUpdaterConfig();
            this.mStartTime = 0L;
            this.mService.startUpgrade(file);
        }
    }

    public void switchFirmware() {
        BluetoothService bluetoothService = this.mService;
        if (bluetoothService != null) {
            bluetoothService.sendConfirmation(1, true);
        }
    }

    public void abortFirmwareUpgradeOnDeviceDisconnected() {
        BluetoothService bluetoothService = this.mService;
        if (bluetoothService == null || !bluetoothService.isUpgrading()) {
            return;
        }
        abortFirmwareUpgrade();
    }

    public void abortFirmwareUpgrade() {
        BluetoothService bluetoothService = this.mService;
        if (bluetoothService != null) {
            bluetoothService.abortUpgrade();
            enableUpgrade(false);
        }
    }

    public void setOnGaiaUpgradeEventListener(OnGaiaUpgradeEventListener onGaiaUpgradeEventListener) {
        this.mListener = onGaiaUpgradeEventListener;
    }

    public void setFirmwareUpdaterConfig() {
        enableUpgrade(true);
        setMTUSize(512);
        enableRWCP(true);
        setRWCPInitialWindow(32);
        setRWCPMaximumWindow(32);
    }

    private void enableUpgrade(boolean z) {
        BluetoothService bluetoothService = this.mService;
        if (bluetoothService != null) {
            bluetoothService.enableUpgrade(z);
        }
    }

    private boolean setMTUSize(int i) {
        BluetoothService bluetoothService = this.mService;
        return bluetoothService != null && bluetoothService.getTransport() == 0 && ((GAIAGATTBLEService) this.mService).setMtuSize(i);
    }

    private boolean enableRWCP(boolean z) {
        BluetoothService bluetoothService = this.mService;
        return bluetoothService != null && bluetoothService.getTransport() == 0 && ((GAIAGATTBLEService) this.mService).enableRWCP(z);
    }

    private boolean setRWCPInitialWindow(int i) {
        BluetoothService bluetoothService = this.mService;
        return bluetoothService != null && bluetoothService.getTransport() == 0 && ((GAIAGATTBLEService) this.mService).setRWCPInitialWindow(i);
    }

    private boolean setRWCPMaximumWindow(int i) {
        BluetoothService bluetoothService = this.mService;
        return bluetoothService != null && bluetoothService.getTransport() == 0 && ((GAIAGATTBLEService) this.mService).setRWCPMaximumWindow(i);
    }

    private void askForConfirmation(int i) {
        if (i == 1) {
            this.mListener.onFWUpgradeStateChange(3);
        } else if (i == 2) {
            this.mService.sendConfirmation(i, true);
        } else {
            if (i != 3) {
                return;
            }
            this.mService.sendConfirmation(i, true);
        }
    }

    private void manageError(UpgradeError upgradeError) {
        upgradeError.getError();
    }
}
