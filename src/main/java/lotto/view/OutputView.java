package lotto.view;

import lotto.model.Lotto;
import lotto.model.Lottos;

public class OutputView {
    public void printAskInputMoney() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public void printLottosHistory(final Lottos lottos) {
        printLottoSize(lottos.size());
        lottos.getLottos()
            .forEach(this::printLottoNumbers);
    }

    private void printLottoSize(final int size) {
        System.out.printf("%n%s개를 구매했습니다.%n", size);
    }

    private void printLottoNumbers(final Lotto lotto) {
        System.out.println(lotto);
    }
}
