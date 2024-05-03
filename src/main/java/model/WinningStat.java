package model;

import config.ResultType;
import java.util.Map;

public class WinningStat {

    private final Map<ResultType, Integer> statics;

    public WinningStat(final Map<ResultType, Integer> statics) {
        this.statics = statics;
    }

    public void updateWinningStat(ResultType resultType) {
        statics.put(resultType, statics.get(resultType) + 1);
    }

}
