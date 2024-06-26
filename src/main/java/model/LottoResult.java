package model;

import java.util.Map;

public class LottoResult {

    private final Map<Rank, Integer> result;

    public LottoResult(final Map<Rank, Integer> result) {
        this.result = result;
    }

    public double getRateOfReturn(final int purchaseMoney) {
        int reward = getReward();
        return reward / (double) purchaseMoney;
    }

    private int getReward() {
        return result.keySet().stream()
                .mapToInt(rank -> rank.getReward() * result.get(rank))
                .sum();
    }

    public Map<Rank, Integer> getResult() {
        return result;
    }
}
