package lotto.view;

import lotto.domain.model.Lotto;
import lotto.domain.model.Lottos;

public class OutputView {

    private OutputView() {
    }

    public static void print(int amount) {
        System.out.println(amount + "개를 구매했습니다");
    }

    public static void printLottos(Lottos lottos) {
        for (Lotto lotto : lottos.getValues()) {
            System.out.println(lotto.getNumbers()); // [1, 2, 3, 4, 5, 6] 형태
        }
    }

}
