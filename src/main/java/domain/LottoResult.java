package domain;

import java.util.Map;

public record LottoResult(Map<Rank, Integer> resultMap,
                          long totalReward,
                          double rewardRate) { }
