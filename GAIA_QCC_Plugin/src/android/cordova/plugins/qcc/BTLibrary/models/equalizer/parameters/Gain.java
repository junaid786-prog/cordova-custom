package cordova.plugins.qcc.BTLibrary.models.equalizer.parameters;

import java.text.DecimalFormat;

/* loaded from: classes2.dex */
public class Gain extends Parameter {
    private static final int FACTOR = 60;
    private final DecimalFormat mDecimalFormat;

    @Override // cordova.plugins.qcc.BTLibrary.models.equalizer.parameters.Parameter
    public int getFactor() {
        return 60;
    }

    public Gain() {
        super(ParameterType.GAIN);
        this.mDecimalFormat = new DecimalFormat();
    }

    @Override // cordova.plugins.qcc.BTLibrary.models.equalizer.parameters.Parameter
    String getLabel(double d) {
        if (!this.isConfigurable) {
            return "- dB";
        }
        this.mDecimalFormat.setMaximumFractionDigits(1);
        return this.mDecimalFormat.format(d) + " dB";
    }
}
