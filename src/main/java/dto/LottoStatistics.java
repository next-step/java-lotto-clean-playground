package dto;

import domain.LottoRank;

import java.math.BigDecimal;
import java.util.Map;

public record LottoStatistics(Map<LottoRank, Integer> matchedCount, BigDecimal profitRate) {
    public static LottoStatistics of(Map<LottoRank, Integer> matchedCount, BigDecimal profitRate) {
        return new LottoStatistics(matchedCount, profitRate);
    }
}
