package view;

import domain.lotto.Lotto;

import java.util.List;
import java.util.Map;

public class OutputView {

    public static void newLine() {
        System.out.println();
    }

    public static void printPaymentNotice() {
        System.out.println("구입금액을 입력해주세요.");
    }

    public static void printLottoAmount(int amount) {
        System.out.printf("%d개를 구매했습니다.\n", amount);
    }

    public static void printLottoTickets(List<Lotto> tickets) {
        for (Lotto lotto : tickets) {
            System.out.println(lotto.toString());
        }
    }

    public static void printLastWeekWinedNumbersNotice() {
        System.out.println("지난 주 당첨 번호를 입력해주세요.");
    }

    public static void printWinningStatics(Map<String, Integer> winningStatics) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        System.out.printf("3개 일치 (5000원) - %d개\n", winningStatics.get("3"));
        System.out.printf("4개 일치 (50000원) - %d개\n", winningStatics.get("4"));
        System.out.printf("5개 일치 (1500000원) - %d개\n", winningStatics.get("5"));
        System.out.printf("6개 일치 (2000000000원) - %d개\n", winningStatics.get("6"));
        System.out.printf("총 수익률은 %.2f입니다.(기준이 1이기 결과적으로는 %s라는 의미임)\n", getReturnRate(winningStatics), getProfitOrLoss(winningStatics));
    }

    private static Double getReturnRate(Map<String, Integer> winningStatics) {
        return winningStatics.entrySet().stream()
                .mapToDouble(entry ->
                        Integer.parseInt(entry.getKey()) * entry.getValue()
                )
                .sum();
    }

    private static String getProfitOrLoss(Map<String, Integer> winningStatics) {
        Double rate = getReturnRate(winningStatics);
        if(rate >= 1) {
            return "이득";
        }
        return "손해";
    }
}
