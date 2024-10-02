package view;

import domain.Lotto;
import domain.Lottos;

import java.util.Map;

public class OutputView {

    public static void printLottos(Lottos lottos) {
        for (Lotto lotto : lottos.getLottos()) {
            System.out.println(lotto.getLotto());
        }
    }

    public static void printWinningResult(Map<Integer, Integer> resultMap) {
        System.out.println("당첨 통계");
        System.out.println("_________");
        System.out.println("3개 일치 (5000원) - " + resultMap.get(3) + "개");
        System.out.println("4개 일치 (50000원) - " + resultMap.get(4) + "개");
        System.out.println("5개 일치 (1500000원) - " + resultMap.get(5) + "개");
        System.out.println("6개 일치 (2000000000원) - " + resultMap.get(6) + "개");

    }
}
