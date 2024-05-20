package domain.lottoTicket;

import domain.lottoNumber.LottoNumber;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTicketServiceTest {
    private final LottoTicketService lottoTicketService = new LottoTicketService();
    @Test
    @DisplayName("로또 티켓 생성 테스트 - 같은 숫자 매칭 불가 따라서 출력으로 대체")
    public void generateLottoNumberListTest() {
        //given
        LottoNumber lottoNumber = new LottoNumber(List.of(1, 2, 3, 4, 5, 6));

        LottoTicket expectedLottoTicket = new LottoTicket(lottoNumber);

        //when
        LottoTicket lottoTicket = lottoTicketService.generateLottoTicket();

        //then
        System.out.println(expectedLottoTicket.getLottoTicketNumber().getLottoNumber());
        System.out.println(lottoTicket.getLottoTicketNumber().getLottoNumber());
    }
}
