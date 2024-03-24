package cordova.plugins.qcc.BTLibrary.models.equalizer.parameters;

import java.text.DecimalFormat;

/* loaded from: classes2.dex */
public class Quality extends Parameter {
    private static final int FACTOR = 4096;
    private final DecimalFormat mDecimalFormat;

    @Override // cordova.plugins.qcc.BTLibrary.models.equalizer.parameters.Parameter
    public int getFactor() {
        return 4096;
    }

    public Quality() {
        super(ParameterType.QUALITY);
        this.mDecimalFormat = new DecimalFormat();
    }

    @Override // cordova.plugins.qcc.BTLibrary.models.equalizer.parameters.Parameter
    String getLabel(double d) {
        if (!this.isConfigurable) {
            return "-";
        }
        this.mDecimalFormat.setMaximumFractionDigits(2);
        return this.mDecimalFormat.format(d);
    }
}
