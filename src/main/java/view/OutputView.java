package view;

import domain.Lotto;
import java.util.List;

public class OutputView {

    public void printPurchasedLottoTickets(List<Lotto> purchasedLottoTickets) {
        System.out.println();
        System.out.println(purchasedLottoTickets.size() + "개를 구매했습니다.");
        purchasedLottoTickets.forEach(System.out::println);
    }
}
