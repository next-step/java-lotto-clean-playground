package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoStatistics {
    private static final long THREE_MATCH_PRIZE = 5000;
    private static final long FOUR_MATCH_PRIZE = 50000;
    private static final long FIVE_MATCH_PRIZE = 1500000;
    private static final long SIX_MATCH_PRIZE = 2000000000;
    private static final int NUMBER_BOUND = 6;

    private final List<Integer> winningCounts = new ArrayList<>(7);

    public LottoStatistics(Lottos lottos, WinningNumbers winningNumbers) {
        initializeWinningCounts();

        calculateWinningCounts(lottos, winningNumbers);
    }

    public int getWinningCount(int matchCount) {
        return winningCounts.get(matchCount);
    }

    public long getPrizeAmount(int matchCount) {
        if (matchCount == 3) return THREE_MATCH_PRIZE;
        if (matchCount == 4) return FOUR_MATCH_PRIZE;
        if (matchCount == 5) return FIVE_MATCH_PRIZE;
        if (matchCount == 6) return SIX_MATCH_PRIZE;
        return 0;
    }

    public double calculateProfitRate(PurchaseAmount purchaseAmount) {
        return (double)calculateTotalPrize() / purchaseAmount.getAmount();
    }

    private long calculateTotalPrize() {
        long total = 0;

        total += getWinningCount(3) * THREE_MATCH_PRIZE;
        total += getWinningCount(4) * FOUR_MATCH_PRIZE;
        total += getWinningCount(5) * FIVE_MATCH_PRIZE;
        total += getWinningCount(6) * SIX_MATCH_PRIZE;

        return total;
    }

    private void initializeWinningCounts() {
        for (int i = 0; i <= NUMBER_BOUND; i++) {
            winningCounts.add(0);
        }
    }

    private void calculateWinningCounts(Lottos lottos, WinningNumbers winningNumbers) {
        for (Lotto lotto : lottos.getLottos()) {
            int countMatchingNumbers = winningNumbers.countMatchingNumbers(lotto);
            increaseWinningCount(countMatchingNumbers);
        }
    }

    private void increaseWinningCount(int countMatchingNumbers) {
        winningCounts.set(countMatchingNumbers, winningCounts.get(countMatchingNumbers) + 1);
    }
}
