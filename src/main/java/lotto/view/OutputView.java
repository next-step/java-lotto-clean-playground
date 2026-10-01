package lotto.view;

import lotto.domain.Lottos;
import lotto.domain.Rank;
import lotto.domain.WinningStatistics;

public class OutputView {

    public static void printLottos(Lottos lottos) {
        System.out.println(lottos.size() + "개를 구매했습니다.");
        for (int i = 0; i < lottos.size(); i++) {
            System.out.println(lottos.getLottoList().get(i));
        }
    }

    public static void printWinningStatistics(WinningStatistics statistics) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");
        printRankResult(statistics, Rank.FIFTH);
        printRankResult(statistics, Rank.FOURTH);
        printRankResult(statistics, Rank.THIRD);
        printRankResult(statistics, Rank.SECOND);
        printRankResult(statistics, Rank.FIRST);
        printProfitRate(statistics);
    }

    private static void printRankResult(WinningStatistics statistics, Rank rank) {
        int count = statistics.countRank(rank); //이 등수가 몇개있는가를 갖고옴
        System.out.println(createResultLine(rank, count));
    }

    private static String createResultLine(Rank rank, int count) {
        if (rank == Rank.SECOND) {
            return rank.getMatchCount() + "개 일치, 보너스 볼 일치(" + rank.getPrize() + "원) - " + count + "개";
        }
        return rank.getMatchCount() + "개 일치 (" + rank.getPrize() + "원)- " + count + "개";
    }

    private static void printProfitRate(WinningStatistics statistics) {
        double profitRateValue = statistics.getProfitRate();
        System.out.printf("총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 %s라는 의미임)%n", profitRateValue,
                profitLossToggle(profitRateValue));
    }

    private static String profitLossToggle(double profitRate) {
        if (profitRate >= 1) {
            return "이익";
        }
        return "손해";
    }

}
