package view;

import java.util.List;

public class ResultView {
    public static void printUserLotto(List<List<Integer>> AllLotto, int manualLottoCount) {
        int allLottoCount = AllLotto.size();

        System.out.printf("\n수동으로 %d장, 자동으로 %d개를 구매했습니다.\n", manualLottoCount, allLottoCount - manualLottoCount);
        for (List<Integer> lotto : AllLotto) {
            System.out.println(lotto);
        }
    }

    public static void printStatistics(List<List<Integer>> rankResults, Double prizeMoneyRate) {
        System.out.println("\n당첨 통계\n---------");

        for (List<Integer> rankResult : rankResults) {
            printRankResult(rankResult);
        }

        System.out.printf("총 수익률은 %.2f입니다.\n", prizeMoneyRate);
    }

    private static void printRankResult(List<Integer> rankResult) {
        if (rankResult.get(2) == 0) return;

        if (rankResult.get(1) == 1) {
            System.out.printf("%d개 일치, 보너스 볼 일치(%d원) - %d개\n",
                    rankResult.get(0), rankResult.get(3), rankResult.get(4));
            return;
        }

        System.out.printf("%d개 일치 (%d원)- %d개\n",
                rankResult.get(0), rankResult.get(3), rankResult.get(4));
    }
}
