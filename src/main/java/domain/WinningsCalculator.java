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

    public void updateWinningsResult(LottoList lottoList, List<Integer> winningNumber, int bonusNumber) {
        List<Lotto> lottos = lottoList.getLottoList();
        for (Lotto lotto : lottos) {
            int matchCount = calculateMatchCount(winningNumber, lotto.getLottoNumber());
            boolean bonusCount = calculateBonusCount(bonusNumber, lotto.getLottoNumber());
            updateWinningsResult(matchCount, bonusCount);
            System.out.println(String.format("일치숫자: %d, 보너스: %s", matchCount, bonusCount));
        }
        calculateRateOfResult(lottoList.getNumberOfLotto());
    }

    private int calculateMatchCount(List<Integer> winningNumber, List<Integer> lottoNumber) {
        return (int)lottoNumber.stream()
            .filter(winningNumber::contains)
            .count();
    }

    private boolean calculateBonusCount(int bonusNumber, List<Integer> lottoNumber) {
        if (lottoNumber.contains(bonusNumber)) {
            return true;
        }
        return false;
    }

    private void updateWinningsResult(int matchCount, boolean bonusNumber) {
        try {
            Winnings winnings = Winnings.of(matchCount, bonusNumber);
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
