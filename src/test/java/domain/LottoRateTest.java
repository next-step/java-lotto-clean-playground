package domain;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LottoRateTest {
    @Test
    void 로또상금_테스트() {

        // given
        List<Integer> ticketNumbers = List.of(1, 2, 3, 4, 5, 6);
        List<Integer> winNumbers = List.of(1, 2, 3, 4, 5, 6);
        List<Integer> winningMoney = List.of(2000000000, 30000000, 1500000, 50000, 5000);
        LottoTicket lottoTicket = new LottoTicket(ticketNumbers);
        WinningLotto winningLotto = new WinningLotto(winNumbers);
        List<LottoTicket> tickets = Collections.singletonList(lottoTicket);

        // when
        LottoRate lottoRate = new LottoRate(tickets, winningLotto);
        List<Integer> matchesMoney = lottoRate.matchesMoney();

        // then
        assertEquals(winningMoney, matchesMoney);
    }


    @Test
    void 수익률_테스트() {

        // given
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6));
        LottoTicket ticket1 = new LottoTicket(List.of(1, 2, 3, 4, 5, 6));
        LottoTicket ticket2 = new LottoTicket(List.of(11, 12, 13, 14, 15, 16));
        int payMoney = 5000;
        int bonusNumber = 7;

        List<LottoTicket> tickets = List.of(ticket1, ticket2);
        LottoRate lottoRate = new LottoRate(tickets, winningLotto);


        // when
        double rateOfReturn = lottoRate.calculateRateOfReturn(payMoney, bonusNumber);

        // then
        assertEquals(400000.0, rateOfReturn);
    }
}
