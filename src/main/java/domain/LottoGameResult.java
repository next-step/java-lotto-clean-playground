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
        if (match == 6) {
            return WinningRank.SIX;
        }
        if (match == 5 && winningNumbers.containsBonusNumber(lotto)) {
            return WinningRank.FIVE_WITH_BONUS;
        }
        if (match == 5) {
            return WinningRank.FIVE;
        }
        if (match == 4) {
            return WinningRank.FOUR;
        }
        if (match == 3) {
            return WinningRank.THREE;
        }
        return WinningRank.NONE;
    }

    private void printStatistics(Map<WinningRank, Integer> matchCount) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        System.out.println("3개 일치 (5000원) - " + matchCount.get(WinningRank.THREE) + "개");
        System.out.println("4개 일치 (50000원) - " + matchCount.get(WinningRank.FOUR) + "개");
        System.out.println("5개 일치 (1500000원) - " + matchCount.get(WinningRank.FIVE) + "개");
        System.out.println("5개 일치, 보너스 볼 일치 (30000000원) - " + matchCount.get(WinningRank.FIVE_WITH_BONUS) + "개");
        System.out.println("6개 일치 (2000000000원) - " + matchCount.get(WinningRank.SIX) + "개");
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
