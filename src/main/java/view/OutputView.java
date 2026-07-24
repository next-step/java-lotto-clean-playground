package view;

import domain.lotto.Lotto;

import java.util.List;

public class OutputView {

    public static void printPaymentNotice() {
        System.out.println("구입금액을 입력해주세요.");
    }

    public static void printLottoAmount(int amount) {
        System.out.printf("\n%d개를 구매했습니다.\n", amount);
    }

    public static void printLottoTickets(List<Lotto> tickets) {
        for (Lotto lotto : tickets) {
            System.out.println(lotto.toString());
        }
    }
}
