package view;

import domain.Lotto;
import domain.Lottos;
import utils.WinningResult;
import static utils.WinningResult.resultMap;

public class OutputView {

    public static void printLottos(Lottos lottos) {
        for (Lotto lotto : lottos.getLottos()) {
            System.out.println(lotto.getLotto());
        }
    }

    public static void printWinningResult() {
        System.out.println("당첨 통계");
        System.out.println("_________");
        System.out.println("3개 일치 (5000원) - " + resultMap.get(WinningResult.MATCH_THREE) + "개");
        System.out.println("4개 일치 (50000원) - " + resultMap.get(WinningResult.MATCH_FOUR) + "개");
        System.out.println("5개 일치 (1500000원) - " + resultMap.get(WinningResult.MATCH_FIVE) + "개");
        System.out.println("6개 일치 (2000000000원) - " + resultMap.get(WinningResult.MATCH_SIX) + "개");
    }

    public static void printProfitability(int inputMoney){
        String profitability = WinningResult.calculateProfitability(inputMoney);
        System.out.println("총 수익률은 "+profitability+"입니다.");
    }
}
