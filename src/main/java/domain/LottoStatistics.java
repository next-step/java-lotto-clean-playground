package domain;

import java.util.EnumMap;
import java.util.Map;

public class LottoStatistics {
    private final Map<LottoRank, Integer> winningCounts = new EnumMap<>(LottoRank.class);

    public LottoStatistics(Lottos lottos, WinningNumbers winningNumbers, LottoNumber bonusNumber) {
        initializeWinningCounts();

        calculateWinningCounts(lottos, winningNumbers, bonusNumber);
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

    private void calculateWinningCounts(Lottos lottos, WinningNumbers winningNumbers, LottoNumber bonusNumber) {
        for (Lotto lotto : lottos.getLottos()) {
            processLottoResult(lotto, winningNumbers, bonusNumber);
        }
    }

    private void increaseWinningCount(LottoRank rank) {
        winningCounts.put(rank, winningCounts.get(rank) + 1);
    }

    private boolean isBonusNumberMatched(Lotto lotto, LottoNumber bonusNumber) {
         return lotto.getNumbers().contains(bonusNumber);
    }

    private void processLottoResult(Lotto lotto, WinningNumbers winningNumbers, LottoNumber bonusNumber) {
        int matchCount = winningNumbers.countMatchingNumbers(lotto);
        boolean bonusMatched = isBonusNumberMatched(lotto, bonusNumber);

        LottoRank rank = LottoRank.from(matchCount, bonusMatched);

        if (rank == null) {
            return;
        }

        increaseWinningCount(rank);
    }
}
