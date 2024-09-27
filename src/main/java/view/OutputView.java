package view;

import java.util.List;

public class OutputView {

    public static void printCompleteBuyingLotto(final int lottoCount) {
        System.out.println(lottoCount + "개를 구매했습니다.");
    }

    public static void printBuyingLotto(List<Integer> lotto) {
        System.out.println(lotto);
    }
}
