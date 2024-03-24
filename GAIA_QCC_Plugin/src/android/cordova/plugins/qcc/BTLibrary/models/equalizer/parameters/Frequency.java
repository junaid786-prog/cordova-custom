package cordova.plugins.qcc.BTLibrary.models.equalizer.parameters;

import java.text.DecimalFormat;

/* loaded from: classes2.dex */
public class Frequency extends Parameter {
    private static final int FACTOR = 3;
    private final DecimalFormat mDecimalFormat;
    private final LogValues mLogValues;

    @Override // cordova.plugins.qcc.BTLibrary.models.equalizer.parameters.Parameter
    public int getFactor() {
        return 3;
    }

    public Frequency() {
        super(ParameterType.FREQUENCY);
        this.mDecimalFormat = new DecimalFormat();
        this.mLogValues = new LogValues();
    }

    @Override // cordova.plugins.qcc.BTLibrary.models.equalizer.parameters.Parameter
    String getLabel(double d) {
        if (!this.isConfigurable) {
            return "- Hz";
        }
        if (d < 50.0d) {
            this.mDecimalFormat.setMaximumFractionDigits(1);
            return this.mDecimalFormat.format(d) + " Hz";
        }
        if (d < 1000.0d) {
            this.mDecimalFormat.setMaximumFractionDigits(0);
            return this.mDecimalFormat.format(d) + " Hz";
        }
        this.mDecimalFormat.setMaximumFractionDigits(1);
        return this.mDecimalFormat.format(d / 1000.0d) + " kHz";
    }

    @Override // cordova.plugins.qcc.BTLibrary.models.equalizer.parameters.Parameter
    public int getPositionValue() {
        return (int) Math.round((this.mLogValues.rangeLength * (Math.log(getValue()) - this.mLogValues.logMin)) / this.mLogValues.logRange);
    }

    @Override // cordova.plugins.qcc.BTLibrary.models.equalizer.parameters.Parameter
    public void setConfigurable(double d, double d2) {
        super.setConfigurable(d, d2);
        this.mLogValues.rangeLength = getMaxBound() - getMinBound();
        this.mLogValues.logMax = Math.log(getMaxBound());
        this.mLogValues.logMin = Math.log(getMinBound());
        LogValues logValues = this.mLogValues;
        logValues.logRange = logValues.logMax - this.mLogValues.logMin;
    }

    @Override // cordova.plugins.qcc.BTLibrary.models.equalizer.parameters.Parameter
    public void setValueFromProportion(int i) {
        setValue((int) Math.round(Math.exp(this.mLogValues.logMin + ((i * this.mLogValues.logRange) / this.mLogValues.rangeLength))));
    }

    /* loaded from: classes2.dex */
    private class LogValues {
        double logMax;
        double logMin;
        double logRange;
        int rangeLength;

        private LogValues() {
        }
    }
}
