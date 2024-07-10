package dto;

import java.util.List;

public class LottoResultDto {
    private List<Integer> result;
    private long totalReward;
    private double rewardRate;

    public LottoResultDto(List<Integer> result, long totalReward, double rewardRate) {
        this.result = result;
        this.totalReward = totalReward;
        this.rewardRate = rewardRate;
    }

    public List<Integer> getResult() {
        return result;
    }

    public long getTotalReward() {
        return totalReward;
    }

    public double getRewardRate() {
        return rewardRate;
    }
}
