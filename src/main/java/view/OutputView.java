package view;

import model.Lotto;
import model.Lottos;
import model.Rank;

import java.util.Arrays;
import java.util.Map;

public class OutputView {
    public static void printPurchaseCount(int count) {
        System.out.printf("%d개를 구매했습니다.\n", count);
    }

    public static void printLottos(Lottos lottos) {
        for (Lotto lotto : lottos.getLottos()) {
            System.out.println(lotto);
        }
    }

    public static void printWinningResult(Map<Rank, Integer> matchResult, double rateOfReturn) {
        printResultMessage();
        printMatchDetails(matchResult);
        printRateOfReturn(rateOfReturn);
    }

    private static void printResultMessage() {
        System.out.println("당첨통계");
        System.out.println("-----------");
    }

    private static void printMatchDetails(Map<Rank, Integer> matchResult) {
        Arrays.stream(Rank.values())
                .filter(rank -> rank != Rank.NONE)
                .forEach(rank -> System.out.printf(
                        "%d개 일치 (%,d원) - %d개\n",
                        rank.getMatchCount(),
                        rank.getPrize(),
                        matchResult.getOrDefault(rank, 0)
                ));
    }

    private static void printRateOfReturn(double rateOfReturn) {
        System.out.printf("총 수익률은 %.2f입니다.\n", rateOfReturn);
    }
}
