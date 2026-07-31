package view;

import domain.Lotto;
import domain.LottoNumber;
import domain.Lottos;
import domain.PurchaseAmount;

import java.util.ArrayList;
import java.util.List;

public class OutputView {
    public void printPurchasedCount(PurchaseAmount purchaseAmount) {
        System.out.println(purchaseAmount.calculateLottoCount() + "개를 구매했습니다.");
    }

    public void printLottos(Lottos lottos) {
        for (Lotto lotto : lottos.getLottos()) {
            printLotto(lotto);
        }
    }

    private void printLotto(Lotto lotto) {
        List<Integer> numberValues = new ArrayList<>();

        for (LottoNumber lottoNumber : lotto.getNumbers()) {
            numberValues.add(lottoNumber.getNumber());
        }

        System.out.println(numberValues);
    }
}
