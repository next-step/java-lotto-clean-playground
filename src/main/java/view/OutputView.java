package view;

import model.Lotto;
import model.Lottos;
import model.Rank;

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
        System.out.printf("3개 일치 (5,000원) - %d개\n", matchResult.getOrDefault(Rank.THREE, 0));
        System.out.printf("4개 일치 (50,000원) - %d개\n", matchResult.getOrDefault(Rank.FOUR, 0));
        System.out.printf("5개 일치 (1,500,000원) - %d개\n", matchResult.getOrDefault(Rank.FIVE, 0));
        System.out.printf("6개 일치 (2,000,000,000원) - %d개\n", matchResult.getOrDefault(Rank.SIX, 0));
    }

    private static void printRateOfReturn(double rateOfReturn) {
        System.out.printf("총 수익률은 %.2f입니다.\n", rateOfReturn);
    }
}
