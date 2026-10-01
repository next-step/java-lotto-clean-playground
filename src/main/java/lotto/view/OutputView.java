package lotto.view;

import java.util.List;
import lotto.domain.Rank;
import lotto.domain.WinningStatistics;

public class OutputView {

    public static void printLottos(List<List<Integer>> lottos) {
        System.out.println(lottos.size() + "개를 구매했습니다.");
        for (List<Integer> values : lottos) {
            System.out.println(values);
        }
    }

    public static void printWinningStatistics(WinningStatistics statistics, List<Rank> ranks) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");

        for (int i = ranks.size() - 1; i >= 0; i--) {
            printRankResult(statistics, ranks.get(i));
        }

        printProfitRate(statistics);
    }

    private static void printRankResult(WinningStatistics statistics, Rank rank) {
        int count = statistics.countRank(rank);
        if (rank == Rank.SECOND) {
            System.out.println(rank.getMatchCount() + "개 일치, 보너스 볼 일치 (" +
                    rank.getPrize() + "원)- " + count + "개");
            return;
        }
        System.out.println(rank.getMatchCount() + "개 일치 (" + rank.getPrize() + "원)- " + count + "개");
    }

    private static void printProfitRate(WinningStatistics statistics) {
        String profitRate = String.format("%.2f", statistics.getProfitRate());
        System.out.println("총 수익률은 " + profitRate + "입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)");
    }

}
