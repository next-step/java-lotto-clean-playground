package view;

import domain.LottoTicket;
import java.util.List;
import java.util.Map;

public class OutputView {

    private OutputView() {
        throw new AssertionError("OutputView는 인스턴스화 할 수 없습니다.");
    }

    public static void printTicketCount(int count) {
        System.out.println();
        System.out.println(count + "개를 구매했습니다.");
    }

    public static void printTickets(List<LottoTicket> tickets) {
        for (LottoTicket ticket : tickets) {
            System.out.println(ticket);
        }
        System.out.println();
    }

    public static void printResult(Map<Integer, Integer> matchResults) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");

        System.out.println("3개 일치 (5000원) - " + matchResults.getOrDefault(3, 0) + "개");
        System.out.println("4개 일치 (50000원) - " + matchResults.getOrDefault(4, 0) + "개");
        System.out.println("5개 일치 (1500000원) - " + matchResults.getOrDefault(5, 0) + "개");
        System.out.println("6개 일치 (2000000000원) - " + matchResults.getOrDefault(6, 0) + "개");
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
