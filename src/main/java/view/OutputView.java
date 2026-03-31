package view;

import domain.Lotto;
import domain.LottoTicket;

import java.util.List;

public class OutputView {
    public void showNumberOfTickets(int numberOfTickets) {
        System.out.println(numberOfTickets + "개를 구매했습니다.");
    }

    public void showLottoTickets(Lotto lotto) {
        for (LottoTicket lottoTicket: lotto.getTickets()) {
            showLottoTicket(lottoTicket);
        }
    }

    private void showLottoTicket(LottoTicket lottoTicket) {
        List<Integer> ticket = lottoTicket.getTicket();
        System.out.println(ticket);
    }
}
