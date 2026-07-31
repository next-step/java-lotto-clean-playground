package view;

import domain.Lotto;
import domain.Lottos;
import domain.PurchaseAmount;

public class OutputView {
    public void printPurchasedCount(PurchaseAmount purchaseAmount) {
        System.out.println(purchaseAmount.calculateLottoCount() + "개를 구매했습니다.");
    }

    public void printLottos(Lottos lottos) {
        for (Lotto lotto : lottos.getLottos()) {
            System.out.println(lotto.getNumbers());
        }
    }
}
