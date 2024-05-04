package model;

import config.ResultType;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

public class LottoStat {

    private final Map<ResultType, Integer> statics;
    private long totalWinningPrice;

    public LottoStat() {
        this.statics = new LinkedHashMap<>();
        Arrays.stream(ResultType.values())
                .filter(type -> !type.equals(ResultType.MATCH_ZERO))
                .sorted(Comparator.comparingLong(ResultType::getWinningPrice))
                .forEach(type -> statics.put(type, 0));
    }

    public void updateWinningStat(ResultType resultType) {
        if (!resultType.equals(ResultType.MATCH_ZERO)) {
            statics.put(resultType, statics.getOrDefault(resultType, 0) + 1);
        }
    }

    public Map<ResultType, Integer> getStatics() {
        Map<ResultType, Integer> copy = new LinkedHashMap<>();
        for (ResultType type : statics.keySet()) {
            copy.put(type, statics.get(type));
        }
        return copy;
    }

    public double getTotalReturnRate(int inputPrice) {
        for (ResultType key : statics.keySet()) {
            this.totalWinningPrice += key.getWinningPrice() * statics.get(key);
        }
        return (double) this.totalWinningPrice / inputPrice;
    }

}
