package dto;

import domain.LottoResult;
import domain.Rank;
import java.util.Map;

public class LottoResultDto {
    private Map<Rank, Integer> resultMap;
    private long totalReward;
    private double rewardRate;

    public LottoResultDto(LottoResult lottoResult) {
        this.resultMap = lottoResult.resultMap();
        this.totalReward = lottoResult.totalReward();
        this.rewardRate = lottoResult.rewardRate();
    }

    public Map<Rank, Integer> getResultMap() {
        return resultMap;
    }

    public long getTotalReward() {
        return totalReward;
    }

    public double getRewardRate() {
        return rewardRate;
    }
}
