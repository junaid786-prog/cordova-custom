package cordova.plugins.qcc.BTLibrary.models.equalizer.parameters;

/* loaded from: classes2.dex */
public abstract class Parameter {
    private static final int MAX_BOUND_OFFSET = 1;
    private static final int MIN_BOUND_OFFSET = 0;
    private int mRawValue;
    private final ParameterType mType;
    private final int[] mRawBounds = new int[2];
    private final String[] mLabelBounds = new String[2];
    private boolean isUpToDate = false;
    boolean isConfigurable = false;
    private final int mFactor = getFactor();

    abstract int getFactor();

    abstract String getLabel(double d);

    /* JADX INFO: Access modifiers changed from: package-private */
    public Parameter(ParameterType parameterType) {
        this.mType = parameterType;
    }

    public ParameterType getParameterType() {
        return this.mType;
    }

    public int getValue() {
        return this.mRawValue;
    }

    public int getPositionValue() {
        return this.mRawValue - this.mRawBounds[0];
    }

    public int getBoundsLength() {
        int[] iArr = this.mRawBounds;
        return iArr[1] - iArr[0];
    }

    public boolean isConfigurable() {
        return this.isConfigurable;
    }

    public boolean isUpToDate() {
        return this.isUpToDate;
    }

    public String getLabelMinBound() {
        return this.isConfigurable ? this.mLabelBounds[0] : "";
    }

    public String getLabelMaxBound() {
        return this.isConfigurable ? this.mLabelBounds[1] : "";
    }

    public String getLabelValue() {
        return getLabel(this.mRawValue / this.mFactor);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getMinBound() {
        return this.mRawBounds[0];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getMaxBound() {
        return this.mRawBounds[1];
    }

    public void setValue(int i) {
        this.isUpToDate = true;
        this.mRawValue = i;
    }

    public void setValueFromProportion(int i) {
        this.mRawValue = i + this.mRawBounds[0];
    }

    public void setConfigurable(double d, double d2) {
        this.isConfigurable = true;
        setBound(0, d);
        setBound(1, d2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setNotConfigurable() {
        this.isConfigurable = false;
    }

    public void hasTobeUpdated() {
        this.isUpToDate = false;
    }

    private void setBound(int i, double d) {
        this.mLabelBounds[i] = getLabel(d);
        this.mRawBounds[i] = (int) (d * this.mFactor);
    }
}
