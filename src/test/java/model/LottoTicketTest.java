package model;

import lotto.model.LottoTicket;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class LottoTicketTest {
    @Test
    void 번호_리스트를_스트링으로_바꾸기(){
        //Given
        List<Integer> ticket = Arrays.asList(2, 3, 5, 6, 7, 8);
        String ticketStr = null;
        String tmp = "[2, 3, 5, 6, 7, 8]";
        LottoTicket lottoTicket = new LottoTicket(ticket,ticketStr);

        //When
        lottoTicket.changeNumListToStr();

        //Then
        Assertions.assertThat(tmp).isEqualTo(lottoTicket.getTicketStr());
    }
}
