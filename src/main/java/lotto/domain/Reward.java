package lotto.domain;

import java.util.Map;

import lotto.Rank;

public class Reward {
    private final int inputMoney;
    private int totalReward;
    private final Map<Rank, Integer> resultMap;

    public Reward(Result result, int inputMoney) {
        this.inputMoney = inputMoney;
        this.resultMap = result.getResult();
    }

    private void caculateTotalReward() {
        resultMap.forEach((rank, value) ->
            totalReward += rank.getReward() * value);
    }

    private String calculate() {
        caculateTotalReward();

        if (totalReward == 0) {
            return "0";
        }
        double rewardRate = (double)totalReward / inputMoney;
        return String.format("%.2f", rewardRate);
    }

    public String getRate() {
        return calculate();
    }
}
