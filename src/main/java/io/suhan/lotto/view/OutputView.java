package io.suhan.lotto.view;

import io.suhan.lotto.model.Rank;
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

        Rank[] ranks = Rank.values();

        // print in reverse order
        for (int i = ranks.length - 1; i >= 0; i--) {
            Rank rank = ranks[i];

            if (rank == Rank.NONE) {
                continue;
            }

            long count = statistics.getCountMap().getOrDefault(rank, 0L);
            System.out.printf("%s (%d원)- %d개\n", rank.getDescription(), rank.getPrize(), count);
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
