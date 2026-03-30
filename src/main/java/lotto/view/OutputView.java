package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoTickets;
import lotto.domain.Rank;
import java.util.List;
import java.util.Map;

public class OutputView {
    public static void printTicketCount(int count) {
        System.out.println("\n" + count + "개를 구매했습니다.");
    }

    public static void printTickets(LottoTickets tickets) {
        for (Lotto ticket : tickets.getTickets()) {
            System.out.println(ticket);
        }
        System.out.println();
    }

    public static void printStatistics(Map<Rank, Long> result, double yield) {
        System.out.println("\n당첨 통계\n---------");
        printRank(Rank.FIFTH, result.getOrDefault(Rank.FIFTH, 0L));
        printRank(Rank.FOURTH, result.getOrDefault(Rank.FOURTH, 0L));
        printRank(Rank.THIRD, result.getOrDefault(Rank.THIRD, 0L));
        printRank(Rank.FIRST, result.getOrDefault(Rank.FIRST, 0L));
        System.out.printf("총 수익률은 %.2f입니다.\n", yield);
    }

    private static void printRank(Rank rank, long count) {
        System.out.printf("%d개 일치 (%d원)- %d개\n",
                rank.getMatchCount(), rank.getWinningMoney(), count);
    }
}
