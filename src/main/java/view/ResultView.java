package view;

import domain.Lotto;

public class ResultView {

    public static void printPurchase(int price) {
        Lotto lotto = new Lotto();

        System.out.println(price + "개를 구매했습니다.");
        for (int i = 0; i < price; i++) {
            lotto.run();
        }
    }
}
