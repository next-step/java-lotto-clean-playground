package view;

import domain.Lotto;
import java.util.List;

public class OutputView {
    public void printPurchasePrice(){
        System.out.println("구입금액을 입력해 주세요.");
    }

    public void printPurchasedLottos(List<Lotto> lottos) {
        System.out.println("\n"+lottos.size() + "개를 구매했습니다.");
        for (Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers());
        }
    }
}
