package lotto.view;

import lotto.domain.model.Lotto;
import lotto.domain.model.LottoRank;
import lotto.domain.model.Lottos;
import lotto.domain.model.WinningStatistics;

public class OutputView {

    private OutputView() {
    }

    public static void print(int manualCount, int autoCount) {
        System.out.println("\n" + "수동으로 " + manualCount + "장, 자동으로 " + autoCount+ "개를 구매했습니다");
    }

    public static void printLottos(Lottos lottos) {
        for (Lotto lotto : lottos.getValues()) {
            System.out.println(lotto.getNumbers()); // [1, 2, 3, 4, 5, 6] 형태
        }
        System.out.println();
    }

    public static void printResult(WinningStatistics statistics) {
        System.out.println("당첨 통계" + "\n---------\n");

        for (LottoRank rank : LottoRank.values()) {
            if (rank == LottoRank.MISS) continue;

            System.out.printf("%s - %d개\n", rank.getDescription(), statistics.getCount(rank));
        }
        double profit = statistics.calculateProfitRate();
        System.out.printf("총 수익률은 %.1f%% 입니다.", profit);
        if (profit < 1) {
            System.out.print("(기준이 1이기 때문에 결과적으로 손해라는 의미임)");
        }
    }
}
