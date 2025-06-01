package view;

import domain.LottoTicket;
import domain.MatchResult;
import domain.Rank;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private OutputView() {
        throw new AssertionError("OutputView는 인스턴스화 할 수 없습니다.");
    }

    public static void printTicketCount(int handCount, int autoCount) {
        System.out.println();
        System.out.println("수동으로 " + handCount + "장, 자동으로 " + +autoCount + "개를 구매했습니다.");
    }

    public static void printTickets(List<LottoTicket> tickets) {
        for (LottoTicket ticket : tickets) {
            System.out.println(formatTicket(ticket));
        }
        System.out.println();
    }

    private static String formatTicket(LottoTicket ticket) {
        return ticket.getNumbers().stream()
            .map(String::valueOf)
            .collect(Collectors.joining(", ", "[", "]"));
    }

    public static void printResult(MatchResult matchResults) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");

        System.out.println("3개 일치 (5000원) - " + matchResults.getCount(Rank.FIFTH) + "개");
        System.out.println("4개 일치 (50000원) - " + matchResults.getCount(Rank.FOURTH) + "개");
        System.out.println("5개 일치 (1500000원) - " + matchResults.getCount(Rank.THIRD) + "개");
        System.out.println(
            "5개 일치, 보너스 볼 일치(30000000원) - " + matchResults.getCount(Rank.SECOND) + "개");
        System.out.println("6개 일치 (2000000000원) - " + matchResults.getCount(Rank.FIRST) + "개");
    }

    public static void printProfitRate(double profitRate) {
        String statusMessage = getProfitStatusMessage(profitRate);
        System.out.printf("총 수익률은 %.2f입니다.%s%n", profitRate, statusMessage);
    }

    private static String getProfitStatusMessage(double profitRate) {
        if (profitRate < 1.0) {
            return "(기준이 1이기 때문에 결과적으로 손해라는 의미임)";
        }
        return "(기준이 1이기 때문에 결과적으로 이익이라는 의미임)";
    }
}
