package view;

import domain.*;

public class ResultView {
    public static void showNum(Lottos lottos) {
        System.out.printf("%d개를 구매했습니다.", lottos.size());
        System.out.println();
        for (Lotto lotto : lottos.getLottos()) {
            System.out.println(lotto.getLottoNumbers());
        }
    }

    public static void showStatistics(ProfitRate profitRate, WinningStatistics winningStatistics) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        System.out.println("3개 일치 (5000원)-" + winningStatistics.getWinningStatistics().get(Rank.FOURTH_PLACE));
        System.out.println("4개 일치 (50000원)-" +winningStatistics.getWinningStatistics().get(Rank.THIRD_PLACE));
        System.out.println("5개 일치 (1500000원)-" + winningStatistics.getWinningStatistics().get(Rank.SECOND_PLACE));
        System.out.println("5개 일치, 보너스 볼 일치(30000000원)-" + winningStatistics.getWinningStatistics().get(Rank.SECOND_PLACE_BONUS));
        System.out.println("6개 일치 (2000000000원)-" + winningStatistics.getWinningStatistics().get(Rank.FIRST_PLACE));
        System.out.println("총 수익률은 " + profitRate.getProfitRate() + "%입니다.");
    }
}
