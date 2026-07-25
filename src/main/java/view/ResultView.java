package view;

import lotto.Lotto;
import lotto.LottoRank;
import lotto.LottoResult;
import lotto.Lottos;
import lotto.PurchaseAmount;

public class ResultView {

    public static void printLottoCount(int count) {
        System.out.println();
        System.out.println(count + "개를 구매했습니다.");
    }

    public static void printLottos(Lottos lottos) {
        for (Lotto lotto : lottos) {
            System.out.println(lotto);
        }
    }

    public static void printResult(LottoResult result, PurchaseAmount purchaseAmount) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        printRank(result, LottoRank.FOURTH);
        printRank(result, LottoRank.THIRD);
        printRank(result, LottoRank.SECOND);
        printRank(result, LottoRank.FIRST);
        printProfitRate(result, purchaseAmount);
    }

    private static void printProfitRate(LottoResult result, PurchaseAmount purchaseAmount) {
        System.out.println("총 수익률은 " + result.calculateProfitRate(purchaseAmount)
                + "입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)");
    }

    private static void printRank(LottoResult result, LottoRank rank) {
        System.out.println(rank.matchCount() + "개 일치 (" + rank.prizeMoney()
                + "원)- " + result.countOf(rank) + "개");
    }
}
