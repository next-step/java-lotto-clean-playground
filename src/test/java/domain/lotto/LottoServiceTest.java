package domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;

import domain.lottoTicket.LottoTicket;
import domain.lottoWinningStatistics.LottoWinningStatistics;
import domain.lottoWinningStatistics.LottoWinningStatisticsService;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import view.InputView;
import view.OutputView;

public class LottoServiceTest {
    @Test
    @DisplayName("로또 티켓 수 구하는 메서드 테스트")
    public void countLottoTicketsTest() {
        //given
        int money = 3000;
        int expectedLottoTicketCount = (int) 3000 / 1000;

        LottoService lottoService = new LottoService(new InputView(), new OutputView());

        //when
        int lottoTicketCount = lottoService.countLottoTickets(money);

        //then
        assertThat(expectedLottoTicketCount).isEqualTo(lottoTicketCount);
    }

    @Test
    @DisplayName("로또 번호로 가능한 리스트를 구한 후 출력")
    public void generateLottoTicketListTest() {
        //given
        int money = 6000;
        LottoService lottoService = new LottoService(new InputView(), new OutputView());
        Lotto lotto = new Lotto(money, lottoService.countLottoTickets(money));

        //when
        List<LottoTicket> lottoTickets =lottoService.generateLottoTicketList(lotto);

        //then
        lottoTickets.forEach(lottoTicket -> System.out.println(lottoTicket.getLottoNumber()));
        lottoService.printLottoTickets(lotto, lottoTickets);
    }

    @Test
    @DisplayName("로또 당첨 번호를 맞춘 개수 반환 테스트 불가(?)...")
    public void countLottoWinningNumberTest() {
        //given
        int money = 6000;
        LottoService lottoService = new LottoService(new InputView(), new OutputView());
        Lotto lotto = new Lotto(money, lottoService.countLottoTickets(money));

        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        LottoWinningStatisticsService lottoWinningStatisticsService = new LottoWinningStatisticsService();
        List<LottoTicket> lottoTickets =lottoService.generateLottoTicketList(lotto);

        List<Integer> lottoWinnerCount = lottoWinningStatisticsService.countLottoWinning(lottoTickets, winningNumbers);

        int winnings = lottoWinningStatisticsService.caculateWinnings(lottoWinnerCount);

        double returnOfInvestment = lottoWinningStatisticsService.caculateReturnOfInvestment(lotto, winnings);

        LottoWinningStatistics lottoWinningStatistics = new LottoWinningStatistics(winningNumbers, lottoWinnerCount, returnOfInvestment);

        //when

        //then
        new OutputView().writeLottoWinningStatistics(lottoWinningStatistics);
    }

    @Test
    @DisplayName("로또 당첨 번호 통계")
    public void makeLottoWinningStatistic() {
        //given

        //when
        //then
    }
}
