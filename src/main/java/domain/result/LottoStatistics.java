package domain.result;

import domain.money.PrizeAmount;
import domain.money.PurchaseAmount;
import domain.number.MatchCount;
import java.util.LinkedHashMap;
import java.util.Map;

public class LottoStatistics {
    private final Map<LottoRank, Integer> results;

    private LottoStatistics(Map<LottoRank, Integer> results) {
        this.results = results;
    }

    public static LottoStatistics empty() {
        return new LottoStatistics(initialResults());
    }

    private static Map<LottoRank, Integer> initialResults() {
        Map<LottoRank, Integer> results = new LinkedHashMap<>();
        LottoRank.valuesForResult().forEach(rank -> results.put(rank, 0));
        return results;
    }

    public void record(MatchCount matchCount) {
        LottoRank.findBy(matchCount).ifPresent(this::increase);
    }

    private void increase(LottoRank lottoRank) {
        results.put(lottoRank, countOf(lottoRank) + 1);
    }

    public int countOf(LottoRank lottoRank) {
        return results.get(lottoRank);
    }

    public double profitRate(PurchaseAmount purchaseAmount) {
        return purchaseAmount.profitRate(totalPrizeAmount());
    }

    private PrizeAmount totalPrizeAmount() {
        return results.keySet().stream()
                .map(rank -> rank.totalPrize(countOf(rank)))
                .reduce(PrizeAmount.from(0), PrizeAmount::plus);
    }
}
