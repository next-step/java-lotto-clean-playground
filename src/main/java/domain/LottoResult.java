package domain;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import enumerate.LottoRateEnum;

public class LottoResult {

    private final Map<LottoRateEnum, Integer> matchCounts;
    private final double rateOfReturn;

    public LottoResult(Lottos lottos, WinningNumbers winningNumbers, long purchaseMoney) {
        this.matchCounts = calculateMatchCounts(lottos, winningNumbers);
        this.rateOfReturn = calculateRateOfReturn(matchCounts, purchaseMoney);
    }

    private Map<LottoRateEnum, Integer> calculateMatchCounts(Lottos lottos, WinningNumbers winningNumbers) {
        return lottos.getLottos().stream()
            .map(lotto -> calculateRank(lotto.getLottoNumbers(), winningNumbers))
            .filter(Objects::nonNull)
            .collect(Collectors.groupingBy(rank -> rank, Collectors.summingInt(rank -> 1)));
    }

    private LottoRateEnum calculateRank(LottoNumbers lottoNumbers, WinningNumbers winningNumbers) {
        int matchCount = countMatchingNumbers(lottoNumbers, winningNumbers);
        boolean isBonusMatched = isBonusNumberMatched(lottoNumbers, winningNumbers);
        return LottoRateEnum.getLottoRate(matchCount, isBonusMatched);
    }

    private int countMatchingNumbers(LottoNumbers lottoNumbers, WinningNumbers winningNumbers) {
        return (int) lottoNumbers.getLottoNumbers().stream()
            .filter(winningNumbers.getWinningNumbers().getLottoNumbers()::contains)
            .count();
    }

    private boolean isBonusNumberMatched(LottoNumbers lottoNumbers, WinningNumbers winningNumbers) {
        return lottoNumbers.getLottoNumbers().contains(winningNumbers.getBonusNumber());
    }

    private double calculateRateOfReturn(Map<LottoRateEnum, Integer> matchCounts, long purchaseMoney) {
        long totalPrize = calculateTotalPrize(matchCounts);
        return (double)totalPrize / purchaseMoney;
    }

    private long calculateTotalPrize(Map<LottoRateEnum, Integer> matchCounts) {
        return matchCounts.entrySet().stream()
            .mapToLong(entry -> entry.getKey().price * entry.getValue())
            .sum();
    }

    public Map<LottoRateEnum, Integer> getMatchCounts() {
        return matchCounts;
    }

    public double getRateOfReturn() {
        return rateOfReturn;
    }
}
