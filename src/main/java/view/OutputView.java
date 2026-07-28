package view;

import domain.enums.LotteryPrize;
import domain.lotto.Lotto;
import domain.lotto.collection.LottoTickets;
import domain.lotto.collection.WinningStatistics;
import domain.lotto.wrap.Money;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static domain.enums.LotteryPrize.MISS;

public class OutputView {

    public static void newLine() {
        System.out.println();
    }

    public static void printPaymentNotice() {
        System.out.println("구입금액을 입력해주세요.");
    }

    public static void printManualCountNotice() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
    }

    public static void printManualNumbersNotice() {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
    }

    public static void printLottoAmount(int manualCount, int autoCount) {
        System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다.\n", manualCount, autoCount);
    }

    public static void printLottoTickets(LottoTickets tickets) {
        for (Lotto lotto : tickets.getTickets()) {
            System.out.println(lotto.toString());
        }
    }

    public static void printLastWeekWinedNumbersNotice() {
        System.out.println("지난 주 당첨 번호를 입력해주세요.");
    }

    public static void printWinningStatics(WinningStatistics statistics, Money payment) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        for (LotteryPrize prize : prizesInDisplayOrder()) {
            printPrizeLine(prize, statistics.countOf(prize));
        }

        System.out.printf("총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 %s라는 의미임)",
                statistics.returnRate(payment),
                statistics.isProfit(payment));
    }

    public static void printBonusBallNotice() {
        System.out.println("보너스 볼을 입력해 주세요.");
    }

    private static void printPrizeLine(LotteryPrize prize, int count) {
        if (prize == LotteryPrize.SECOND) {
            System.out.printf("%d개 일치, 보너스 볼 일치(%d원) - %d개\n",
                    prize.getMatchCount(), prize.getPrize().getAmount(), count);
            return;
        }

        System.out.printf("%d개 일치(%d원) - %d개\n",
                prize.getMatchCount(), prize.getPrize().getAmount(), count);
    }

    private static List<LotteryPrize> prizesInDisplayOrder() {
        List<LotteryPrize> prizes = new ArrayList<>(List.of(LotteryPrize.values()));
        prizes.remove(MISS);
        return prizes;
    }
}
