package view;

import lotto.Lotto;
import lotto.Lottos;

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
}
