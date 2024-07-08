package domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class WinningsCalculator {

    private Map<Winnings, Integer> winningsResult = new HashMap<>();
    private int winningPrize;
    private double rateOfReturn;

    public WinningsCalculator() {
        for (Winnings winning : Winnings.values()) {
            winningsResult.put(winning, 0);
        }
    }

    public void updateWinningsResult(List<Integer> winningNumber, LottoList lottoList) {
        List<Lotto> lottos = lottoList.getLottoList();
        for (Lotto lotto : lottos) {
            int matchCount = calculateMatchCount(winningNumber, lotto.getLottoNumber());
            updateWiningState(matchCount);
        }
        calculateRateOfResult(lottoList.getNumberOfLotto());
    }

    private int calculateMatchCount(List<Integer> winningNumber, List<Integer> lottoNumber) {
        return (int)lottoNumber.stream()
            .filter(winningNumber::contains)
            .count();
    }

    private void updateWiningState(int matchCount) {
        try {
            Winnings winnings = Winnings.of(matchCount);
            this.winningsResult.merge(winnings, 1, Integer::sum);
            winningPrize += winnings.getWinningPrize();
        } catch (NoSuchElementException ignore) {

        }
    }

    private void calculateRateOfResult(int numberOfLotto) {
        rateOfReturn = (double) winningPrize / (numberOfLotto * 1000);
    }

    public Map<Winnings, Integer> getWinningsResult() {
        return this.winningsResult;
    }

    public double getRateOfReturn() {
        return this.rateOfReturn;
    }
}
