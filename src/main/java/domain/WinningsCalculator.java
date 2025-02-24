package domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class WinningsCalculator {

    private final Map<Winnings, Integer> winningsResult = new HashMap<>();
    private int prize;
    private double profit;

    public WinningsCalculator() {
        Arrays.stream(Winnings.values())
            .forEach(winnings -> winningsResult.put(winnings, 0));
    }

    public void updateWinningsResult(LottoStatus lottoStatus) {
        List<Lotto> lottoList = lottoStatus.getLottoList();
        Lotto winningNumber = lottoStatus.getWinningNumber();
        LottoNumber bonusNumber = lottoStatus.getBonusNumber();
        for (Lotto lotto : lottoList) {
            int matchCount = calculateMatchCount(winningNumber.getLottoNumber(), lotto.getLottoNumber());
            boolean bonusCount = calculateBonusCount(bonusNumber, lotto.getLottoNumber());
            updateWinningsResult(matchCount, bonusCount);
        }
        calculateProfit(lottoStatus.getLottoList().size());
    }

    private int calculateMatchCount(List<LottoNumber> winningNumber, List<LottoNumber> lottoNumber) {
        return (int)lottoNumber.stream()
            .filter(winningNumber::contains)
            .count();
    }

    private boolean calculateBonusCount(LottoNumber bonusNumber, List<LottoNumber> lottoNumber) {
        return lottoNumber.contains(bonusNumber);
    }

    private void updateWinningsResult(int matchCount, boolean bonusNumber) {
        try {
            Winnings winnings = Winnings.of(matchCount, bonusNumber);
            this.winningsResult.merge(winnings, 1, Integer::sum);
            prize += winnings.getWinningPrize();
        } catch (NoSuchElementException ignore) {

        }
    }

    private void calculateProfit(int lottoCount) {
        profit = (double)prize / (lottoCount * 1000);
    }

    public Map<Winnings, Integer> getWinningsResult() {
        return this.winningsResult;
    }

    public double getProfit() {
        return this.profit;
    }
}
