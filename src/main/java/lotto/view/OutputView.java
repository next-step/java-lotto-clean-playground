package lotto.view;

import java.util.List;

import lotto.domain.Lotto;
import lotto.message.ConsoleMessage;

public class OutputView {

    private OutputView() {
    }

    public static void printLottoes(List<Lotto> lottoList) {
        System.out.printf(ConsoleMessage.COUNT_LOTTO.getMessage(), lottoList.size());

        for (Lotto lotto : lottoList) {
            System.out.println(lotto.getLottoNums());
        }
    }
}
