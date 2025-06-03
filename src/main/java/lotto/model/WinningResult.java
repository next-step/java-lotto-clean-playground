package lotto.model;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WinningResult {

    private final Map<Rank, Long> result;

    public WinningResult(WinningNumbers winningNumbers, List<LottoNumbers> lottoNumbers) {
        List<MatchCount> matchCounts = calculateWinningCount(winningNumbers, lottoNumbers);
        this.result = calculateResults(matchCounts);
    }

    public Map<Rank, Long> getWinningStatistics() {
        return new EnumMap<>(result);
    }

    public Prize calculateTotalPrize() {
        return result.entrySet().stream()
                .map(entry -> entry.getKey().getPrize().multiply(entry.getValue()))
                .reduce(new Prize(0), (prize1, prize2) ->
                    new Prize(new Money(prize1.getAmount().getAmount() +
                                      prize2.getAmount().getAmount())));
    }

    public double calculateProfitRate(Money purchaseAmount) {
        return (double) calculateTotalPrize().getAmount().getAmount() /
               purchaseAmount.getAmount() * 100;
    }

    private List<MatchCount> calculateWinningCount(WinningNumbers winningNumbers,
        List<LottoNumbers> lottoNumbers) {
        return lottoNumbers.stream()
            .map(lottoNumber -> lottoNumber.match(winningNumbers))
            .collect(Collectors.toList());
    }

    private Map<Rank, Long> calculateResults(List<MatchCount> matchCounts) {
        return matchCounts.stream()
            .map(this::findRankByMatchCountAndBonus)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    private Rank findRankByMatchCountAndBonus(MatchCount matchCount) {
        return Rank.from(matchCount.getCount(), matchCount.isMatchBonus());
    }
}
