package view;

import domain.Lotto;
import domain.Lottos;

public class OutputView {
    public void printPurchasedCount(Lottos lottos) {
        System.out.println(lottos.getLottoCount() + "개를 구매했습니다.");
    }

    public void printLottos(Lottos lottos) {
        for (Lotto lotto : lottos.getLottos()) {
            System.out.println(lotto.getNumbers());
        }
    }
}
