package lotto.view;

import lotto.domain.*;
import java.util.Map;

public class OutputView {
    public static void printPurchaseSummary(int manual, int auto) {
        System.out.printf("\n수동으로 %d장, 자동으로 %d개를 구매했습니다.\n", manual, auto);
    }

    public static void printTickets(LottoTickets tickets) {
        tickets.getTickets().forEach(System.out::println);
    }

    public static void printStatistics(Map<Rank, Long> result, double yield) {
        System.out.println("\n당첨 통계\n---------");
        for (Rank rank : new Rank[]{Rank.FIFTH, Rank.FOURTH, Rank.THIRD, Rank.SECOND, Rank.FIRST}) {
            String bonusMsg = (rank == Rank.SECOND) ? ", 보너스 볼 일치" : "";
            System.out.printf("%d개 일치%s (%d원)- %d개\n",
                    rank.getMatchCount(), bonusMsg, rank.getWinningMoney(), result.getOrDefault(rank, 0L));
        }
        System.out.printf("총 수익률은 %.2f입니다.\n", yield);
    }
}
