package service;

import domain.*;

import java.util.*;
import java.util.stream.Collectors;

public class LottoStatisticsService {
    private final Map<WinningRank, Integer> statistics;

    public LottoStatisticsService(Lottos lottos, WinningLottoNumbers winningNumbers) {
        this.statistics = calculateStatistics(lottos, winningNumbers);
    }

    private Map<WinningRank, Integer> calculateStatistics(Lottos lottos, WinningLottoNumbers winningNumbers) {
        return lottos.getLottos().stream()
                .map(lotto -> determineRank(lotto, winningNumbers))
                .flatMap(Optional::stream)
                .collect(Collectors.groupingBy(rank -> rank, Collectors.summingInt(count -> 1)));
    }

    private Optional<WinningRank> determineRank(Lotto lotto, WinningLottoNumbers winningNumbers) {
        int matchCount = countMatchingNumbers(lotto, winningNumbers);
        boolean bonusMatch = isBonusMatched(lotto, winningNumbers);
        return WinningRank.valueOf(matchCount, bonusMatch);
    }

    private int countMatchingNumbers(Lotto lotto, WinningLottoNumbers winningNumbers) {
        return (int) lotto.getLottoNumbers().stream()
                .filter(winningNumbers.getNumbers()::contains)
                .count();
    }

    private boolean isBonusMatched(Lotto lotto, WinningLottoNumbers winningNumbers) {
        LottoNumber bonusLottoNumber = LottoNumber.of(winningNumbers.getBonusBall().getBonusBall());
        return lotto.getLottoNumbers().contains(bonusLottoNumber);
    }

    public Map<WinningRank, Integer> getStatistics() {
        return statistics;
    }

    public double calculateProfitRate(Amount purchaseAmount) {
        int resultPrice = statistics.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
        return (double) resultPrice / purchaseAmount.getAmount();
    }
}
