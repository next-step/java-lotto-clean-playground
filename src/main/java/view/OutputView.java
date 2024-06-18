package view;

import domain.lotto.LottoTicket;

import java.util.List;

public class OutputView {
    
    public static void printResult(List<LottoTicket> lottoTickets) {
        System.out.println();
        System.out.println(lottoTickets.size() + "개를 구매했습니다.");
        for (LottoTicket lottoTicket : lottoTickets) {
            System.out.println(lottoTicket);
        }
    }
}
