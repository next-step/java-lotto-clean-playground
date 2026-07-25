package view;

import lotto.Lotto;

public class ResultView {

    public static void printLottoCount(int count) {
        System.out.println();
        System.out.println(count + "개를 구매했습니다.");
    }

    public static void printLotto(Lotto lotto) {
        System.out.println(lotto);
    }
}
