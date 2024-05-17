package org.duckstudy.view;

import java.util.List;
import org.duckstudy.model.lotto.Lotto;

public class OutputView {

    public void printInputPrice() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public void printException(Exception e) {
        System.out.println(e.getMessage());
    }

    public void printLottos(List<Lotto> lottos) {
        System.out.printf("\n%d개를 구매했습니다.\n", lottos.size());

        for (Lotto lotto : lottos) {
            System.out.println(lotto.getLotto());
        }
    }
}
