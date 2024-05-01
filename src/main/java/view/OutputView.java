package view;

import domain.Lotto;
import domain.LottoMaker;

import java.util.List;

public class OutputView {

    public void printLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            printLotto(lotto);
        }
    }

    private void printLotto(Lotto lotto) {
        System.out.println(lotto.getLottoNumber());
    }

    public void printLottoQuantity(LottoMaker lottoMaker) {
        System.out.println(lottoMaker.getLottoQuantity() + "개를 구매했습니다.");
    }
}
