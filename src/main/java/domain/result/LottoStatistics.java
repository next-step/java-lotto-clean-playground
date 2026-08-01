package domain.result;

import domain.money.PrizeAmount;
import domain.money.PurchaseAmount;
import java.util.EnumMap;
import java.util.Map;

public class LottoStatistics {
    private static final int INITIAL_COUNT = 0;

    private final Map<LottoRank, Integer> rankCounts;

    private LottoStatistics(Map<LottoRank, Integer> rankCounts) {
        this.rankCounts = rankCounts;
    }

    public static LottoStatistics empty() {
        return new LottoStatistics(initialRankCounts());
    }

    private static Map<LottoRank, Integer> initialRankCounts() {
        Map<LottoRank, Integer> rankCounts = new EnumMap<>(LottoRank.class);
        LottoRank.valuesForResult().forEach(rank -> rankCounts.put(rank, INITIAL_COUNT));
        return rankCounts;
    }

    public void record(LottoResult lottoResult) {
        LottoRank.findBy(lottoResult).ifPresent(this::increaseCount);
    }

    private void increaseCount(LottoRank lottoRank) {
        rankCounts.put(lottoRank, countOf(lottoRank) + 1);
    }

    public int countOf(LottoRank lottoRank) {
        return rankCounts.getOrDefault(lottoRank, INITIAL_COUNT);
    }

    public double profitRate(PurchaseAmount purchaseAmount) {
        return purchaseAmount.profitRate(totalPrizeAmount());
    }

    private PrizeAmount totalPrizeAmount() {
        return rankCounts.entrySet().stream()
                .map(entry -> entry.getKey().totalPrize(entry.getValue()))
                .reduce(PrizeAmount.from(0), PrizeAmount::plus);
    }
}
