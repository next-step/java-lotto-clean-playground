package model;

import java.util.Map;

public class LottoResult {

    private final Map<LottoRank, Integer> winningResult;
    private final double earningRate;

    public LottoResult(Map<LottoRank, Integer> winningResult, double earningRate) {
        this.winningResult = winningResult;
        this.earningRate = earningRate;
    }

    public Map<LottoRank, Integer> getWinningResult() {
        return winningResult;
    }

    public double getEarningRate() {
        return earningRate;
    }
}
