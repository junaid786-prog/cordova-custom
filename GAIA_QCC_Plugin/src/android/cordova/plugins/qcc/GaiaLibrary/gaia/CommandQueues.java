package cordova.plugins.qcc.GaiaLibrary.gaia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* loaded from: classes2.dex */
public class CommandQueues {
    public static Queue<ActionCallbackContext> readConfigRegEventQueue;
    public HashMap<String, Queue<ActionCallbackContext>> str2Queue = new HashMap<>();

    public CommandQueues() {
        readConfigRegEventQueue = new LinkedList();
        this.str2Queue.put("writeConfigRegEventQueue", new LinkedList());
        this.str2Queue.put("enableSensorEventQueue", new LinkedList());
        this.str2Queue.put("setSensorEventIntervalQueue", new LinkedList());
        this.str2Queue.put("fwControlQueue", new LinkedList());
        this.str2Queue.put("touchFwQueue", new LinkedList());
        this.str2Queue.put("readBatteryEventQueue", new LinkedList());
        this.str2Queue.put("readProximityEventQueue", new LinkedList());
        this.str2Queue.put("setLanguageEventQueue", new LinkedList());
        this.str2Queue.put("readDSPConfigParam", new LinkedList());
        this.str2Queue.put("setMagnetCalibrationDataQueue", new LinkedList());
        this.str2Queue.put("setMusicEqualizerMasterGainQueue", new LinkedList());
        this.str2Queue.put("getMusicEqualizerMasterGainQueue", new LinkedList());
        this.str2Queue.put("getMusicEqualizerGainQueue", new LinkedList());
        this.str2Queue.put("getMusicEqualizerFrequencyQueue", new LinkedList());
        this.str2Queue.put("getMusicEqualizerQFactorQueue", new LinkedList());
        this.str2Queue.put("getMusicEqualizerFilterTypeQueue", new LinkedList());
        this.str2Queue.put("setMusicEqualizerGainQueue", new LinkedList());
        this.str2Queue.put("setMusicEqualizerFrequencyQueue", new LinkedList());
        this.str2Queue.put("setMusicEqualizerQFactorQueue", new LinkedList());
        this.str2Queue.put("setMusicEqualizerFilterTypeQueue", new LinkedList());
        this.str2Queue.put("getUserEqualizerOnQueue", new LinkedList());
        this.str2Queue.put("setUserEqualizerOnQueue", new LinkedList());
        this.str2Queue.put("resetDeviceEventQueue", new LinkedList());
        this.str2Queue.put("setRTCQueue", new LinkedList());
        this.str2Queue.put("set247MonitorQueue", new LinkedList());
        this.str2Queue.put("get247MonitorQueue", new LinkedList());
        this.str2Queue.put("set247ReportingQueue", new LinkedList());
        this.str2Queue.put("get247ReportingQueue", new LinkedList());
        this.str2Queue.put("get247StepCountQueue", new LinkedList());
        this.str2Queue.put("delete247StepCountQueue", new LinkedList());
        this.str2Queue.put("set247PostureConfigQueue", new LinkedList());
        this.str2Queue.put("get247PostureConfigQueue", new LinkedList());
        this.str2Queue.put("get247PostureQueue", new LinkedList());
        this.str2Queue.put("delete247PostureQueue", new LinkedList());
        this.str2Queue.put("fwUpgradeRequestQueue", new LinkedList());
        this.str2Queue.put("writeLostLinkAlertVolume", new LinkedList());
        this.str2Queue.put("rebootDeviceEventQueue", new LinkedList());
        this.str2Queue.put("enableUartQueue", new LinkedList());
        this.str2Queue.put("avRemoteControlQueue", new LinkedList());
        this.str2Queue.put("getVoiceEqualizerEnable", new LinkedList());
        this.str2Queue.put("getVoiceProEqualizerEnable", new LinkedList());
        this.str2Queue.put("setWhisperFeature", new LinkedList());
        this.str2Queue.put("getWhisperFeature", new LinkedList());
        this.str2Queue.put("setWhisperOn", new LinkedList());
        this.str2Queue.put("getWhisperOn", new LinkedList());
        this.str2Queue.put("setGyroAccelMagnetCalibrationQueue", new LinkedList());
        this.str2Queue.put("setASRModeQueue", new LinkedList());
        this.str2Queue.put("setVolatileConfigTimeoutQueue", new LinkedList());
    }

    public ArrayList<ActionCallbackContext> getUnacknowledgedCallbackContext() {
        final ArrayList<ActionCallbackContext> arrayList = new ArrayList<>();
        readConfigRegEventQueue.forEach(new Consumer() { // from class: cordova.plugins.qcc.GaiaLibrary.gaia.CommandQueues$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                arrayList.add((ActionCallbackContext) obj);
            }
        });
        readConfigRegEventQueue.clear();
        this.str2Queue.forEach(new BiConsumer() { // from class: cordova.plugins.qcc.GaiaLibrary.gaia.CommandQueues$$ExternalSyntheticLambda1
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                CommandQueues.lambda$getUnacknowledgedCallbackContext$2(arrayList, (String) obj, (Queue) obj2);
            }
        });
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getUnacknowledgedCallbackContext$2(final ArrayList arrayList, String str, Queue queue) {
        queue.forEach(new Consumer() { // from class: cordova.plugins.qcc.GaiaLibrary.gaia.CommandQueues$$ExternalSyntheticLambda2
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                arrayList.add((ActionCallbackContext) obj);
            }
        });
        queue.clear();
    }
}
