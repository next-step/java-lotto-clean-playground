package view;

import model.Lotto;
import model.Lottos;
import model.Rank;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

public class OutputView {
    public static void displayManualLottoPrompt() {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
    }

    public static void printPurchaseCount(int manualCount, int automaticCount) {
        System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다.\n", manualCount, automaticCount);
    }

    public static void printLottos(Lottos lottos) {
        for (Lotto lotto : lottos.getLottos()) {
            System.out.println(lotto);
        }
        System.out.println();
    }

    public static void printWinningResult(Map<Rank, Integer> matchResult, double rateOfReturn) {
        printResultMessage();
        printMatchDetails(matchResult);
        printRateOfReturn(rateOfReturn);
    }

    private static void printResultMessage() {
        System.out.println("\n당첨통계");
        System.out.println("-----------");
    }

    private static void printMatchDetails(Map<Rank, Integer> matchResult) {
        Arrays.stream(Rank.values())
                .filter(rank -> rank != Rank.NONE)
                .sorted(Comparator.comparing(Rank::getPrize))
                .forEach(rank -> System.out.printf(
                        "%s - %d개\n",
                        getRankDescription(rank),
                        matchResult.getOrDefault(rank, 0)
                ));
    }

    private static String getRankDescription(Rank rank) {
        if (rank == Rank.SECOND) {
            return String.format("5개 일치, 보너스 볼 일치 (%,d원)", rank.getPrize());
        }
        return String.format("%d개 일치 (%,d원)", rank.getMatchCount(), rank.getPrize());
    }

    private static void printRateOfReturn(double rateOfReturn) {
        System.out.printf("총 수익률은 %.2f입니다.\n", rateOfReturn);
    }

    public static void printErrorMessage(String message) {
        System.out.println("[ERROR] " + message);
    }
}
