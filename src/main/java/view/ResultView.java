package view;

import java.util.List;

public class ResultView {
    public static void printPurchaseAmount(int count) {
        System.out.println(count + "개를 구매했습니다.");
    }

    public static void printLotto(List<Integer> lotto) {
        System.out.println(lotto);
    }
}
