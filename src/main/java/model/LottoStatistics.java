package model;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LottoStatistics {
    private final Map<Rank, Long> result;
    private final double profitRate;

    public LottoStatistics(List<Lotto> purchasedLottos, Lotto winningNumbers, LottoNumber bonusNumber,
                           int purchasedAmount) {
        this.result = purchasedLottos.stream()
                .map(lotto -> {
                    int matchCount = lotto.countMatching(winningNumbers);
                    boolean matchBonus = lotto.contains(bonusNumber);
                    return Rank.of(matchCount, matchBonus);
                })
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        long totalPrize = result.entrySet().stream()
                .mapToLong(e -> e.getKey().getPrize() * e.getValue())
                .sum();

        this.profitRate = (double) totalPrize / purchasedAmount;
    }

    public long getCount(Rank rank) {
        return result.getOrDefault(rank, 0L);
    }

    public double getProfitRate() {
        return profitRate;
    }

}
