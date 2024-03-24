package cordova.plugins.qcc.GaiaLibrary.gaia;

import cordova.plugins.qcc.GaiaLibrary.gaia.packets.GaiaPacket;
import cordova.plugins.qcc.GaiaLibrary.gaia.packets.GaiaPacketBLE;
import cordova.plugins.qcc.GaiaLibrary.gaia.packets.GaiaPacketBREDR;

/* loaded from: classes2.dex */
abstract class AGaiaManager extends GaiaManager {
    private final boolean hasChecksum;
    private final int mVendor;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AGaiaManager(int i) {
        super(i);
        this.hasChecksum = false;
        this.mVendor = 10;
        showDebugLogs(false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GaiaPacket createPacket(int i) {
        if (getTransportType() == 0) {
            return new GaiaPacketBLE(10, i);
        }
        return new GaiaPacketBREDR(10, i, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GaiaPacket createPacket(int i, byte[] bArr) {
        if (getTransportType() == 0) {
            return new GaiaPacketBLE(10, i, bArr);
        }
        return new GaiaPacketBREDR(10, i, bArr, false);
    }
}
