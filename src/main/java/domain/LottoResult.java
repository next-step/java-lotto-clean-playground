package domain;

import dto.LottoResultDto;
import java.util.ArrayList;
import java.util.List;

public class LottoResult {
    private List<Integer> result;
    private long totalReward;
    private double rewardRate;

    public LottoResult(List<Integer> result, long totalReward, double rewardRate) {
        this.result = result;
        this.totalReward = totalReward;
        this.rewardRate = rewardRate;
    }

    public List<Integer> getResult() {
        return result;
    }

    public void setResult(List<Integer> result) {
        this.result = result;
    }

    public long getTotalReward() {
        return totalReward;
    }

    public void setTotalReward(long totalReward) {
        this.totalReward = totalReward;
    }

    public double getRewardRate() {
        return rewardRate;
    }

    public void setRewardRate(double rewardRate) {
        this.rewardRate = rewardRate;
    }

    public LottoResultDto toDto() {
        List<Integer> newResult = new ArrayList<>();
        newResult.addAll(result);
        return new LottoResultDto(newResult, totalReward, rewardRate);
    }
}
