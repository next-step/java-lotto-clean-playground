package view;

import java.util.List;

public class OutputView {

    public static void printCompleteBuyingLotto(final int passiveLottoCount, final int autoLottoCount) {
        System.out.println("수동으로 " + passiveLottoCount + "장, 자동으로 " + autoLottoCount + "개를 구매했습니다.");
    }

    public static void printBuyingLotto(final List<Integer> lotto) {
        System.out.println(lotto);
    }

    public static void printTotalWinning() {
        System.out.println("당첨 통계");
        System.out.println("---------");
    }

    public static void printWinningLottoResult(final int correctCount, final int prizeMoney, final int correctLottoCount, final boolean isSecondPrize) {
        if (isSecondPrize) {
            System.out.println(correctCount + "개 일치, 보너스 볼 일치(" + prizeMoney + "원)- " + correctLottoCount + "개");
            return;
        }
        System.out.println(correctCount + "개 일치 (" + prizeMoney + "원)- " + correctLottoCount + "개");
    }

    public static void printRateOfReturn(final double rateOfReturn) {
        System.out.printf("총 수익률은 %.2f입니다.", rateOfReturn);
    }
}
