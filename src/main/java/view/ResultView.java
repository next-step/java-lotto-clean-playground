package view;

import java.util.List;
import java.util.Map;

public class ResultView {

    public static void printOrderTickets(int manualCount, int autoCount) {
        System.out.printf("\n수동으로 %d장, 자동으로 %d개를 구매했습니다.%n", manualCount, autoCount);
    }

    public static void printPurchasedLottoTickets(List<List<Integer>> lottoTickets) {
        for (List<Integer> ticket : lottoTickets) {
            System.out.println(ticket);
        }
    }

    public static void printWinningStatistics(Map<String, Integer> winningDetails, double profitRate) {
        System.out.println("\n당첨 통계");
        System.out.println("---------");

        printWinningDetails(winningDetails);
        printProfitRate(profitRate);
    }

    private static void printWinningDetails(Map<String, Integer> winningDetails) {
        for (Map.Entry<String, Integer> entry : winningDetails.entrySet()) {
            printRankDetails(entry.getKey(), entry.getValue());
        }
    }

    private static void printRankDetails(String rankDescription, int count) {
        System.out.printf("%s- %d개%n", rankDescription, count);
    }

    private static void printProfitRate(double profitRate) {
        String profitResult = getProfitResult(profitRate);

        System.out.printf("총 수익률은 %.2f입니다. (기준이 1이기 때문에 결과적으로 %s)%n",
                profitRate, profitResult);
    }

    private static String getProfitResult(double profitRate) {
        if (profitRate >= 1) {
            return "이득이라는 의미임";
        } else {
            return "손해라는 의미임";
        }
    }
}
