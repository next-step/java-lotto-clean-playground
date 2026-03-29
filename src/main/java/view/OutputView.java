package view;

import domain.Lotto;
import domain.Lottos;

public class OutputView {

    public void printLottos(Lottos lottos) {
        printResultHeader(lottos.size());

        for (Lotto lotto : lottos.getLottos()) {
            System.out.println(lotto.getNumbers());
        }
    }

    private void printResultHeader(int count) {
        System.out.println(count + "개를 구매했습니다.");
    }
}
