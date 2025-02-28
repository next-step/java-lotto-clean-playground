package domain;

import java.util.*;
import java.util.stream.Collectors;

public class LottoStatistics {
    private final Map<WinningRank, Integer> statistics;

    public LottoStatistics(Lottos lottos, WinningLottoNumbers winningNumbers) {
        this.statistics = calculateStatistics(lottos, winningNumbers);
    }

    private Map<WinningRank, Integer> calculateStatistics(Lottos lottos, WinningLottoNumbers winningNumbers) {
        return lottos.getLottos().stream()
                .map(lotto -> countMatchingNumbers(lotto, winningNumbers))
                .map(WinningRank::valueOf)
                .flatMap(Optional::stream)
                .collect(Collectors.groupingBy(rank -> rank, Collectors.summingInt(count -> 1)));
    }

    private int countMatchingNumbers(Lotto lotto, WinningLottoNumbers winningNumbers) {
        return (int) lotto.getLottoNumbers().stream()
                .filter(winningNumbers.getNumbers()::contains)
                .count();
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
