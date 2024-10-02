package model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

public class LottoTicketsTest {
    @Test
    @DisplayName("빈 티켓을 추가하면 예외가 발생한다.")
    void testThrowExceptionIfAddNullTicket(){
        LottoTickets lottoTickets = new LottoTickets();

        assertThatThrownBy(()->lottoTickets.addTicket(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 티켓은 비어있을 수 없습니다.");
    }

    @Test
    @DisplayName("티켓의 수를 정확히 반환해야 한다.")
    void testReturnCorrectTicketCount(){
        LottoTickets lottoTickets = new LottoTickets();
        LottoTicket lottoTicket1 = new LottoTicket(Arrays.asList(
                new LottoNumber(1),new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
        ));
        LottoTicket lottoTicket2 = new LottoTicket(Arrays.asList(
                new LottoNumber(7),new LottoNumber(8), new LottoNumber(9),
                new LottoNumber(10), new LottoNumber(11), new LottoNumber(12)
        ));

        lottoTickets.addTicket(lottoTicket1);
        lottoTickets.addTicket(lottoTicket2);

        assertThat(lottoTickets.ticketsCount()).isEqualTo(2);
    }

    @DisplayName("toString 메서드 테스트")
    @Test
    void testToStringTickets() {
        LottoTickets lottoTickets = new LottoTickets();
        LottoTicket ticket1 = new LottoTicket(Arrays.asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)));
        LottoTicket ticket2 = new LottoTicket(Arrays.asList(new LottoNumber(7), new LottoNumber(8), new LottoNumber(9), new LottoNumber(10), new LottoNumber(11), new LottoNumber(12)));

        lottoTickets.addTicket(ticket1);
        lottoTickets.addTicket(ticket2);

        String expected = "[1, 2, 3, 4, 5, 6]\n" +
                          "[7, 8, 9, 10, 11, 12]\n";

        assertThat(lottoTickets.toString()).isEqualTo(expected);
    }

}
