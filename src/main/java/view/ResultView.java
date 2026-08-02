package view;

import domain.lotto.Lotto;
import domain.winning.Rank;
import domain.winning.WinningStatistics;
import java.util.Arrays;
import java.util.Map;

public class ResultView {
    public static void printPurchaseAmount(int manualCount, int autoCount) {
        System.out.println();
        System.out.println("수동으로 " + manualCount + "장, 자동으로 " + autoCount + "개를 구매했습니다.");
    }

    public static void printLotto(Lotto lotto) {
        System.out.println(lotto.getNumbers());
    }

    public static void printStatistics(WinningStatistics statistics) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");

        Map<Rank, Integer> statisticsMap = statistics.getStatistics();
        Arrays.stream(Rank.values())
                .filter(rank -> rank != Rank.NONE)
                .forEach(rank -> printRank(rank, statisticsMap));
    }

    private static void printRank(Rank rank, Map<Rank, Integer> statisticsMap) {
        int count = statisticsMap.getOrDefault(rank, 0);
        if (rank.isBonusMatch()) {
            System.out.println(rank.getMatchCount() + "개 일치, 보너스 볼 일치(" + rank.getPrize() + "원) - " + count + "개");
            return;
        }
        System.out.println(rank.getMatchCount() + "개 일치 (" + rank.getPrize() + "원)- " + count + "개");
    }

    public static void printProfitRate(double profitRate) {
        System.out.println("총 수익률은 " + profitRate + "입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)");
    }
}

