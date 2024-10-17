package model;

import java.util.Collections;
import java.util.Map;

public class LottoResult {

    private final int INITIAL_COUNT_BY_RANK = 0;
    private final Map<LottoRank, Integer> winningResult;

    public LottoResult(Map<LottoRank, Integer> winningResult) {

        for (LottoRank rank : LottoRank.values()) {
            winningResult.put(rank,INITIAL_COUNT_BY_RANK);
        }

        this.winningResult = winningResult;
    }

    public Map<LottoRank, Integer> getWinningResult() {
        return Collections.unmodifiableMap(winningResult);
    }

    public double calculateEarningRate(int purchaseAmount) {

        int totalPrize = calculateTotalPrize();
        return (double) totalPrize / purchaseAmount;

    }

    public void operateLottoCheckMachine(LottoTickets lottoTickets,
                                         LottoTicket winningNumbers, LottoNumber bonusNumber){

        lottoTickets.checkWinningTickets(winningResult,winningNumbers,bonusNumber);

    }

    private int calculateTotalPrize() {

        int  totalPrize = 0;

        for (Map.Entry<LottoRank,Integer> entry : winningResult.entrySet()) {
            totalPrize += entry.getKey().calculatePrizeForCount(entry.getValue());
        }

        return totalPrize;
    }
}
