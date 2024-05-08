package domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;

import domain.lottoTicket.LottoTicket;
import domain.lottoTicket.LottoTicketService;
import domain.lottoWinningStatistics.LottoWinningStatistics;
import domain.lottoWinningStatistics.LottoWinningStatisticsService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import view.InputView;
import view.OutputView;

public class LottoServiceTest {
    private final LottoService lottoService = new LottoService(new InputView(), new OutputView());
    private final LottoTicketService lottoTicketService = new LottoTicketService();
    private final LottoWinningStatisticsService lottoWinningStatisticsService = new LottoWinningStatisticsService();
    private final int MONEY = 14000;
    private final int LOTTO_PRICE = 1000;

    @Test
    @DisplayName("로또 티켓 세기")
    public void countLottoTicketsTest() {
        //given
        int expectedLottoTicketCount = MONEY / LOTTO_PRICE;
        //when

        //then
        assertThat(expectedLottoTicketCount).isEqualTo(lottoService.countLottoTickets(MONEY));
    }

    @Test
    @DisplayName("로또 티켓 묶음 생성 - 비교 불가 따라서 출력 대체")
    public void generateLottoTicketListTest() {
        //given
        Lotto lotto = new Lotto(MONEY, lottoService.countLottoTickets(MONEY));

        List<LottoTicket> expectedLottoTickets = new ArrayList<>();

        for(int i = 0; i < lotto.getLottoTicketCount(); i++) {
            LottoTicket lottoTicket = lottoTicketService.generateLottoTicket();
            expectedLottoTickets.add(lottoTicket);
        }

        //when
        List<LottoTicket> lottoTickets = lottoService.generateLottoTicketList(lotto);

        //then
        System.out.println("수동 생성한 로또 티켓 묶음");
        lottoService.printLottoTickets(lotto, expectedLottoTickets);
        System.out.println("\n로또 서비스로 만든 로또 티켓 묶음");
        lottoService.printLottoTickets(lotto, lottoTickets);
    }

    @Test
    @DisplayName("로또 우승 통계 계산 및 출력 입력값을 받기 때문에 진행 불가")
    public void drawLottoWinningTest() {
        //given
        //when
        //then
    }
}
