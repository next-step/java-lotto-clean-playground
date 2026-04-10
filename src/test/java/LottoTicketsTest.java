import model.LottoTicket;
import model.LottoTickets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LottoTicketsTest {
    @Test
    @DisplayName("자동 로또 개수를 올바르게 받고 있는지 확인하기")
    public void buyAutoTicketsTest() {
        //given: 자동 로또 개수를 3으로 설정
        int quantity = 3;
        //when: quantity에 맞는 자동 로또 생성
        LottoTickets lottoTickets = new LottoTickets();
        lottoTickets.buyAutoTickets(quantity);
        //then: 자동 로또 개수를 제대로 만들었는지 확인 (개수가 3개인지 확인)
        Assertions.assertEquals(lottoTickets.getAutoTickets().size(), quantity);
    }

    @Test
    @DisplayName("수동로또 get에서 제대로 된 lottonumber들을 받고 있는지 확인")
    public void buyManualTicketsTest() {
        //given: 2개의 로또 넘버를 수동적으로 받을때
        LottoTicket ticket1 = new LottoTicket(List.of(1, 2, 3, 4, 5, 6));
        LottoTicket ticket2 = new LottoTicket(List.of(2, 3, 4, 5, 6, 7));
        //when: 수동 ticket1,ticket2를 list로 묶어서 입력받음
        List<LottoTicket> manualTickets = List.of(ticket1, ticket2);
        LottoTickets lottoTickets = new LottoTickets();
        lottoTickets.buyManualTickets(manualTickets);
        //리스트의 내용이 동일한지 test
        Assertions.assertEquals(lottoTickets.getManualTickets(), manualTickets);
    }
}
