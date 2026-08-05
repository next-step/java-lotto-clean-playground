package domain;

import java.util.EnumMap;
import java.util.Map;

public class LottoStatistics {
    private final Map<LottoRank, Integer> winningCounts = new EnumMap<>(LottoRank.class);

    public LottoStatistics(Lottos lottos, WinningLotto winningLotto) {
        initializeWinningCounts();

        calculateWinningCounts(lottos, winningLotto);
    }

    public int getWinningCount(LottoRank rank) {
        return winningCounts.get(rank);
    }

    public double calculateProfitRate(PurchaseAmount purchaseAmount) {
        return (double)calculateTotalPrize() / purchaseAmount.getAmount();
    }

    private long calculateTotalPrize() {
        long total = 0;

        for (LottoRank rank : LottoRank.values()) {
            total += getWinningCount(rank) * rank.getPrize();
        }

        return total;
    }

    private void initializeWinningCounts() {
        for (LottoRank rank : LottoRank.values()) {
            winningCounts.put(rank, 0);
        }
    }

    private void calculateWinningCounts(Lottos lottos, WinningLotto winningLotto) {
        for (Lotto lotto : lottos.getLottos()) {
            processLottoResult(lotto, winningLotto);
        }
    }

    private void processLottoResult(Lotto lotto, WinningLotto winningLotto) {
        LottoRank rank = winningLotto.determineRank(lotto);

        increaseWinningCount(rank);
    }

    private void increaseWinningCount(LottoRank rank) {
        winningCounts.put(rank, winningCounts.get(rank) + 1);
    }
}
