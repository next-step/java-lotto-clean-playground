package dto;

import domain.LottoRank;

import java.math.BigDecimal;
import java.util.Map;

public record LottoStatistics(Map<LottoRank, Integer> matchCount, BigDecimal profitRate) { }
