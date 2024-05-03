package model;

import config.ResultType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class WinningStat {

    private final Map<ResultType, Integer> statics;

    public WinningStat() {
        this.statics = new HashMap<>();
        Arrays.stream(ResultType.values())
                .filter(type -> !type.equals(ResultType.MATCH_ZERO))
                .forEach(type -> statics.put(type, 0));
    }

    public void updateWinningStat(ResultType resultType) {
        statics.put(resultType, statics.getOrDefault(resultType, 0) + 1);
    }

    public Map<ResultType, Integer> getStatics() {
        Map<ResultType, Integer> copy = new HashMap<>();
        for (ResultType type : statics.keySet()) {
            copy.put(type, statics.get(type));
        }
        return copy;
    }

}
