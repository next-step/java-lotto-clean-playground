package lotto.domain.service;

import java.util.EnumMap;
import java.util.Map;
import lotto.domain.model.LottoRank;
import lotto.domain.model.Lottos;
import lotto.domain.model.Money;
import lotto.domain.model.WinningLotto;

public class WinningStatistics {

    private final Map<LottoRank, Integer> statistics;

    private final Money purchaseMoney;

    public WinningStatistics(Money purchaseMoney) {
        this.statistics = new EnumMap<>(LottoRank.class);
        initDefaultValues();
        this.purchaseMoney = purchaseMoney;
    }

    public void addResult(LottoRank rank) {
        this.statistics.put(rank,  this.statistics.get(rank) + 1);
    }

    public int getCount(LottoRank rank) {
        return statistics.get(rank);
    }

    public static void calculateResults(Lottos lottos, WinningLotto winningLotto, WinningStatistics statistics) {
        lottos.getValues().forEach(lotto -> {
            LottoRank rank = winningLotto.judge(lotto);
            statistics.addResult(rank);
        });
    }

    public double calculateProfitRate() {
        long totalPrize = 0;
        for (LottoRank rank : statistics.keySet()) {
            totalPrize += (long) rank.getPrizeMoney() * statistics.get(rank);
        }
        return (double) totalPrize / purchaseMoney.getAmount();
    }
    private void initDefaultValues() {
        for (LottoRank rank : LottoRank.values()) {
            statistics.put(rank, 0);
        }
    }
}
