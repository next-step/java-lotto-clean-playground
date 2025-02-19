package view;

import model.Lotto;
import java.util.List;

public class ResultView {

    public static void printLottoResult(List<Lotto> tickets) {
        System.out.println(tickets.size() + "개를 구매했습니다.");
        for (Lotto ticket : tickets) {
            System.out.println(ticket.getSortedLottoNumbers());
        }
    }
}
