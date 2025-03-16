package view;

import model.Lotto;
import model.LottoResult;
import model.LottoTickets;
import model.Rank;
import java.util.Map;

public class ResultView {

    public static void printOrderTickets(int manualCount, int autoCount) {
        System.out.printf("\n수동으로 %d장, 자동으로 %d개를 구매했습니다.%n", manualCount, autoCount);
    }

    public static void printPurchasedLottoTickets(LottoTickets lottoTickets) {
        for (Lotto ticket : lottoTickets.getTickets()) {
            System.out.println(ticket.getSortedNumbers());
        }
    }


    public static void printWinningStatistics(LottoResult lottoResult, int totalCost) {
        System.out.println("\n당첨 통계");
        System.out.println("---------");

        printWinningDetails(lottoResult);
        printProfitRate(lottoResult, totalCost);
    }

    private static void printWinningDetails(LottoResult lottoResult) {
        Map<Rank, Integer> matchCountMap = lottoResult.getMatchCountMap();
        Rank[] orderedRanks = {Rank.FIFTH, Rank.FOURTH, Rank.THIRD, Rank.SECOND, Rank.FIRST};

        for (Rank rank : orderedRanks) {
            String bonusText = getBonusText(rank);
            System.out.printf("%d개 일치%s (%d원)- %d개%n",
                    rank.getMatchCount(),
                    bonusText,
                    rank.getPrizeMoney(),
                    matchCountMap.getOrDefault(rank, 0));
        }
    }

    private static void printProfitRate(LottoResult lottoResult, int totalCost) {
        double profitRate = lottoResult.calculateProfitRate(totalCost);
        String profitResult = getProfitResult(profitRate);

        System.out.printf("총 수익률은 %.2f입니다. (기준이 1이기 때문에 결과적으로 %s)\n",
                profitRate, profitResult);
    }

    private static String getBonusText(Rank rank) {
        if (rank == Rank.SECOND) {
            return ", 보너스 볼 일치";
        }
        return "";
    }

    private static String getProfitResult(double profitRate) {
        if (profitRate >= 1) {
            return "이득";
        }
        return "손해";
    }
}
