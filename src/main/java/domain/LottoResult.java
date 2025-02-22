package domain;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import enumerate.LottoRateEnum;

public class LottoResult {

    private final Map<LottoRateEnum, Integer> matchCounts;
    private final double rateOfReturn;

    public LottoResult(Lottos lottos, LottoDrawResult lottoDrawResult, Money money) {
        this.matchCounts = calculateMatchCounts(lottos, lottoDrawResult);
        this.rateOfReturn = calculateRateOfReturn(matchCounts, money);
    }

    private Map<LottoRateEnum, Integer> calculateMatchCounts(Lottos lottos, LottoDrawResult lottoDrawResult) {
        return lottos.getLottos().stream()
            .map(lotto -> calculateRank(lotto.getLottoNumbers(), lottoDrawResult))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(Collectors.groupingBy(rank -> rank, Collectors.summingInt(rank -> 1)));
    }

    private Optional<LottoRateEnum> calculateRank(LottoNumbers lottoNumbers, LottoDrawResult lottoDrawResult) {
        int matchCount = countMatchingNumbers(lottoNumbers, lottoDrawResult);
        boolean isBonusMatched = isBonusNumberMatched(lottoNumbers, lottoDrawResult);
        return LottoRateEnum.getLottoRate(matchCount, isBonusMatched);
    }

    private int countMatchingNumbers(LottoNumbers lottoNumbers, LottoDrawResult lottoDrawResult) {
        return (int)lottoNumbers.getLottoNumbers().stream()
            .filter(lottoDrawResult.getWinningNumbers().getLottoNumbers()::contains)
            .count();
    }

    private boolean isBonusNumberMatched(LottoNumbers lottoNumbers, LottoDrawResult lottoDrawResult) {
        return lottoNumbers.getLottoNumbers().contains(lottoDrawResult.getBonusNumber());
    }

    private double calculateRateOfReturn(Map<LottoRateEnum, Integer> matchCounts, Money money) {
        long totalPrize = calculateTotalPrize(matchCounts);
        return (double)totalPrize / money.getAmount();
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
