package domain;

import java.util.Map;

public record LottoResult(Map<Rank, Integer> resultMap,
                          long totalReward,
                          double rewardRate) {
    public Map<Rank, Integer> getResultMap() {
        return resultMap;
    }

    public String getRewardRate() {
        return String.format("%.2f", rewardRate);
    }
}