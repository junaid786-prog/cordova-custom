package cordova.plugins.qcc.BTLibrary.models.equalizer;

import cordova.plugins.qcc.BTLibrary.models.equalizer.parameters.Filter;
import cordova.plugins.qcc.BTLibrary.models.equalizer.parameters.Frequency;
import cordova.plugins.qcc.BTLibrary.models.equalizer.parameters.Gain;
import cordova.plugins.qcc.BTLibrary.models.equalizer.parameters.Parameter;
import cordova.plugins.qcc.BTLibrary.models.equalizer.parameters.ParameterType;
import cordova.plugins.qcc.BTLibrary.models.equalizer.parameters.Quality;

/* loaded from: classes2.dex */
public class Band {
    private final Parameter[] mParameters;
    private Filter mFilter = Filter.BYPASS;
    private boolean isFilterUpToDate = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Band() {
        Parameter[] parameterArr = new Parameter[ParameterType.getSize()];
        this.mParameters = parameterArr;
        parameterArr[ParameterType.FREQUENCY.ordinal()] = new Frequency();
        parameterArr[ParameterType.GAIN.ordinal()] = new Gain();
        parameterArr[ParameterType.QUALITY.ordinal()] = new Quality();
    }

    public void setFilter(Filter filter, boolean z) {
        this.mFilter = filter;
        Filter.defineParameters(filter, this.mParameters[ParameterType.FREQUENCY.ordinal()], this.mParameters[ParameterType.GAIN.ordinal()], this.mParameters[ParameterType.QUALITY.ordinal()]);
        if (z) {
            return;
        }
        this.isFilterUpToDate = true;
    }

    public Filter getFilter() {
        return this.mFilter;
    }

    public Parameter getFrequency() {
        return this.mParameters[ParameterType.FREQUENCY.ordinal()];
    }

    public Parameter getGain() {
        return this.mParameters[ParameterType.GAIN.ordinal()];
    }

    public Parameter getQuality() {
        return this.mParameters[ParameterType.QUALITY.ordinal()];
    }

    public boolean isUpToDate() {
        int i = 1;
        while (true) {
            Parameter[] parameterArr = this.mParameters;
            if (i < parameterArr.length) {
                if (parameterArr[i].isConfigurable() && !this.mParameters[i].isUpToDate()) {
                    return false;
                }
                i++;
            } else {
                return this.isFilterUpToDate;
            }
        }
    }

    public void hasToBeUpdated() {
        this.isFilterUpToDate = false;
        int i = 1;
        while (true) {
            Parameter[] parameterArr = this.mParameters;
            if (i >= parameterArr.length) {
                return;
            }
            parameterArr[i].hasTobeUpdated();
            i++;
        }
    }
}
