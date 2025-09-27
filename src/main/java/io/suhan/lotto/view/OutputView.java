package io.suhan.lotto.view;

import io.suhan.lotto.model.lotto.Lotto;
import io.suhan.lotto.model.lotto.LottoStatistics;
import java.util.List;

public class OutputView {
    public static void printPurchaseResult(List<Lotto> lottos) {
        System.out.println("\n" + lottos.size() + "개를 구매했습니다.");
        printLottos(lottos);
    }

    public static void printStatistics(LottoStatistics statistics, int totalSpent) {

        System.out.println("\n당첨 통계");
        System.out.println("---------");

        // 3개 ~ 6개 일치
        for (int i = 3; i <= 6; i++) {
            long count = statistics.getCountMap().getOrDefault(i, 0L);
            int winnings = statistics.getWinningsMap().get(i);

            System.out.printf("%d개 일치 (%d원)- %d개\n", i, winnings, count);
        }

        double revenue = statistics.calculateRevenue(totalSpent);

        System.out.printf("총 수익률은 %.2f입니다.", revenue);
    }

    private static void printLottos(List<Lotto> lottos) {
        lottos.forEach(OutputView::printLotto);
    }

    private static void printLotto(Lotto lotto) {
        System.out.println(lotto);
    }
}
