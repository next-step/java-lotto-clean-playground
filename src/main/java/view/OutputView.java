package view;

import model.Lotto;
import model.Lottos;


public class OutputView {

    public static void printPurchaseMessage() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public static void printLottos(Lottos lottos) {
        for (Lotto lotto : lottos.getLottos()) {
            System.out.println(lotto.getNumbers());
        }
    }

    public static void printPurchaseAmount(int amount) {
        System.out.println(amount + "개를 구매했습니다.");
    }

    public static void printLastWeekLottoInputMessage() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }
}
