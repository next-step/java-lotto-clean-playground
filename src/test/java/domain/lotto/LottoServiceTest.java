package domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;

import domain.lottoTicket.LottoTicket;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoServiceTest {
    @Test
    @DisplayName("로또 티켓 수 구하는 메서드 테스트")
    public void countLottoTicketsTest() {
        //given
        int money = 3000;
        int expectedLottoTicketCount = (int) 3000 / 1000;
        LottoService lottoService = new LottoService();

        //when
        int lottoTicketCount = lottoService.countLottoTickets(money);

        //then
        assertThat(expectedLottoTicketCount).isEqualTo(lottoTicketCount);
    }

    @Test
    @DisplayName("로또 번호로 가능한 리스트를 구한다.")
    public void generateLottoTicketListTest() {
        //given
        int money = 6000;
        LottoService lottoService = new LottoService();
        Lotto lotto = new Lotto(lottoService.countLottoTickets(money));

        //when
        List<LottoTicket> lottoTickets =lottoService.generateLottoTicketList(lotto);

        //then
        lottoTickets.forEach(lottoTicket -> System.out.println(lottoTicket.getLottoNumber()));
    }

    @Test
    @DisplayName("로또 당첨 번호를 맞춘 개수 반환")
    public void countLottoWinningNumberTest() {
        //given
        //when
        //then
    }

    @Test
    @DisplayName("로또 당첨 번호 통계")
    public void makeLottoWinningStatistic() {
        //given

        //when
        //then
    }
}
