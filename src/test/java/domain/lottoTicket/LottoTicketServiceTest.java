package domain.lottoTicket;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTicketServiceTest {

    @Test
    @DisplayName("생성 가능한 번호 생성 테스트")
    public void generateLottoNumberListTest() {
        //given
        List<Integer> expectedLottoNumberList = new ArrayList<>();
        for(int i = 1; i <= 45; i++)
            expectedLottoNumberList.add(i);

        //when
        LottoTicketService lottoTicketService = new LottoTicketService();
        List<Integer> lottoNumberList = lottoTicketService.generateLottoNumberList();

        //then
        assertThat(expectedLottoNumberList).isEqualTo(lottoNumberList);
    }

    @Test
    @DisplayName("로또 티켓 생성 테스트")
    public void generateLottoTicketTest() {
        //given
        LottoTicketService lottoTicketService = new LottoTicketService();
        List<Integer> expectedLottoTicket = lottoTicketService.generateLottoTicket();

        //when
        LottoTicket lottoTicket = new LottoTicket(expectedLottoTicket);

        //then
        assertThat(expectedLottoTicket).isEqualTo(lottoTicket.getLottoNumber());
    }
}
