package view;

import model.DrawResults;
import model.Lotto;
import model.Lottos;
import model.Ranking;

public class OutputView {

    public static void printPurchaseMessage() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public static void printLottos(Lottos lottos) {
        printPurchaseAmount(lottos.size());
        for (Lotto lotto : lottos.getLottos()) {
            System.out.println(lotto.getNumbers());
        }
        System.out.println();
    }

    public static void printPurchaseAmount(int amount) {
        System.out.println();
        System.out.println(amount + "개를 구매했습니다.");
    }

    public static void printLastWeekLottoInputMessage() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }

    public static void printDrawResults(DrawResults drawResults) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");
        for (Ranking ranking : Ranking.values()) {
            printPrizeResult(drawResults, ranking);
        }
    }

    private static void printPrizeResult(DrawResults drawResults, Ranking ranking) {
        if (ranking != Ranking.MISS) {
            String string = getResultInfoMessage(drawResults, ranking);
            System.out.println(string);
        }
    }

    private static String getResultInfoMessage(DrawResults drawResults, Ranking ranking) {
        return ranking.getResultMessage() + drawResults.getResults().get(ranking) + "개";
    }

    public static void printProfit(double profit) {
        System.out.println("총 수익률은 " + Math.floor(profit * 100) / 100.0 + "입니다.");
    }

    public static void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }

    public static void printBonusBallInputMessage() {
        System.out.println("보너스 볼을 입력해 주세요.");
    }
}
