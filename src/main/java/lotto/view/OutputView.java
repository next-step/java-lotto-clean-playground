package lotto.view;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lotto.domain.Rank;
import lotto.domain.WinningStatistics;

public class OutputView {

    public static void printLottos(List<List<Integer>> lottos, int manualLottoCount) {
        System.out.println("수동으로 " + manualLottoCount + "장, 자동으로 " +
                (lottos.size() - manualLottoCount) + "개를 구매했습니다.");
        for (List<Integer> values : lottos) {
            System.out.println(values);
        }
    }

    public static void printWinningStatistics(WinningStatistics statistics, List<Rank> ranks) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");

        List<Rank> sortedRanks = new ArrayList<>(ranks);
        sortedRanks.sort(Comparator.comparingInt(Rank::getPrize));

        for (Rank rank : sortedRanks) {
            printRankResult(statistics, rank);
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
        float profitRate = statistics.getProfitRate();
        String result = getResultText(profitRate);

        System.out.println("총 수익률은 " + String.format("%.2f", profitRate)
                + "입니다. (" + result + ")");
    }

    private static String getResultText(float profitRate) {
        if (profitRate < 1) {
            return "기준이 1이기 때문에 결과적으로 손해라는 의미임";
        }
        if (profitRate > 1) {
            return "기준이 1이기 때문에 결과적으로 이득이라는 의미임";
        }
        return "기준이 1이기 때문에 결과적으로 본전이라는 의미임";
    }

}
