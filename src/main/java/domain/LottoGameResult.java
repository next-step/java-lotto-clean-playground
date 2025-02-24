package domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoGameResult {
    private static final int LOTTO_PRICE = 1000;

    private final List<Lotto> lottos;
    private final WinningNumbers winningNumbers;

    public LottoGameResult(List<Lotto> lottos, WinningNumbers winningNumbers) {
        this.lottos = lottos;
        this.winningNumbers = winningNumbers;
    }

    public void printWinningStatistics() {
        Map<WinningRank, Integer> matchCount = new EnumMap<>(WinningRank.class);
        for (WinningRank rank : WinningRank.values()) {
            matchCount.put(rank, 0);
        }

        for (Lotto lotto : lottos) {
            WinningRank rank = determineRank(lotto);
            matchCount.put(rank, matchCount.get(rank) + 1);
        }
        printStatistics(matchCount);
    }

    private WinningRank determineRank(Lotto lotto) {
        int match = winningNumbers.countMatchingNumbers(lotto);
        boolean containsBonus = winningNumbers.containsBonusNumber(lotto);
        return WinningRank.valueOf(match, containsBonus);
    }

    private void printStatistics(Map<WinningRank, Integer> matchCount) {
        System.out.println("당첨 통계");
        System.out.println("---------");

        for (WinningRank rank : WinningRank.values()) {
            if (rank != WinningRank.NONE) {
                System.out.println(rank.getMessage() + " - " + matchCount.get(rank) + "개");
            }
        }

        double profitRate = calculateProfitRate(matchCount);
        System.out.printf("총 수익률은 %.4f입니다.%n", profitRate);
    }

    private double calculateProfitRate(Map<WinningRank, Integer> matchCount) {
        int totalPrize = 0;
        for (WinningRank rank : WinningRank.values()) {
            totalPrize += matchCount.get(rank) * rank.getPrize();
        }
        int totalSpent = LOTTO_PRICE * lottos.size();
        return (double) totalPrize / totalSpent;
    }
}
