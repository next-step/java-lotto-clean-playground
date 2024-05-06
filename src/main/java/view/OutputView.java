package view;

import domain.Lotto;
import domain.LottoGame;
import domain.WinningNumbers;

import java.util.List;

public class OutputView {

    public static void printStartMessage() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public static void printAmountMessage(int lottoAmount) {
        System.out.println(lottoAmount + "개를 구매했습니다.");
    }

    public static void printLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            System.out.println(lotto);
        }
    }

    public static void printWinningNumbers(List<Integer> numbers) {
        if (numbers.isEmpty()) {
            System.out.println("당첨 번호가 입력되지 않았습니다.");
        } else {
            System.out.println("지난 주 당첨 번호를 입력해 주세요.");
            System.out.println(numbers);
        }
    }



}
