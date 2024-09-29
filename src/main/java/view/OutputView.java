package view;

import java.util.List;

public class OutputView {

    public static void printCompleteBuyingLotto(final int lottoCount) {
        System.out.println(lottoCount + "개를 구매했습니다.");
    }

    public static void printBuyingLotto(List<Integer> lotto) {
        System.out.println(lotto);
    }

    public static void printTotalWinning() {
        System.out.println("당첨 통계");
        System.out.println("---------");
    }

    public static void printWinningLottoResult(final int correctCount, final int prizeMoney, final int correctLottoCount) {
        System.out.println(correctCount + "개 일치 (" + prizeMoney + "원)- " + correctLottoCount + "개");
    }

    public static void printRateOfReturn(final double rateOfReturn) {
        System.out.printf("총 수익률은 %.2f입니다.", rateOfReturn);
    }
}
