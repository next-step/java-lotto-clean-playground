package domain.lottoWiningsStatistics;

import static org.assertj.core.api.Assertions.assertThat;

import domain.lotto.Lotto;
import domain.lottoNumber.LottoNumber;
import domain.lottoTicket.LottoTicket;
import domain.lottoWinningStatistics.LottoWinningStatisticsService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoWiningStatisticsServiceTest {
    @Test
    @DisplayName("로또 당첨 번호와 로또 번호 매칭 횟수 반환 테스트")
    public void matchLottoWinningNumberTest() {
        //given
        LottoTicket lottoTicket = new LottoTicket(new LottoNumber(List.of(1, 2, 3, 4, 5, 6)));
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);

        LottoWinningStatisticsService lottoWinningStatisticsService = new LottoWinningStatisticsService();

        //when
        int expectedMatchingCount = 6;

        int matchingCount = lottoWinningStatisticsService.matchLottoWinningNumber(lottoTicket, winningNumbers);
        //then
        assertThat(expectedMatchingCount).isEqualTo(matchingCount);

    }

    @Test
    @DisplayName("로또 티켓 별 매칭 숫자 카운트 테스트")
    public void countLottoWinnerTest() {
        //given
        LottoWinningStatisticsService lottoWinningStatisticsService = new LottoWinningStatisticsService();

        List<Integer> winningNumber = List.of(1, 2, 3, 4, 5, 6);

        LottoTicket lottoTicket1 = new LottoTicket(new LottoNumber(List.of(1, 2, 3, 4, 5, 6)));
        LottoTicket lottoTicket2 = new LottoTicket(new LottoNumber(List.of(1, 2, 3, 4, 5, 7)));
        LottoTicket lottoTicket3 = new LottoTicket(new LottoNumber(List.of(1, 2, 3, 4, 7, 8)));

        List<LottoTicket> lottoTickets = new ArrayList<>();

        lottoTickets.add(lottoTicket1);
        lottoTickets.add(lottoTicket2);
        lottoTickets.add(lottoTicket3);

        //when
        List<Integer> expectedMatchingCount = List.of(6, 5, 4);

        List<Integer> matchingCount = lottoWinningStatisticsService.countLottoWinning(lottoTickets, winningNumber);

        //then
        assertThat(expectedMatchingCount).isEqualTo(matchingCount);
    }

    @Test
    @DisplayName("로또로 번 돈 계산 후 반환 테스트")
    public void caculateWinningsTest() {
        //given
        LottoWinningStatisticsService lottoWinningStatisticsService = new LottoWinningStatisticsService();

        List<Integer> lottoWinnerCount = List.of(1, 2, 3, 4);

        //when
        int expectedWinnings = 55000;

        int winnings = lottoWinningStatisticsService.caculateWinnings(lottoWinnerCount);

        //then
        assertThat(expectedWinnings).isEqualTo(winnings);
    }

    @Test
    @DisplayName("로또 구매로 얻은 이윤 계산 후 반환 테스트")
    public void caculateReturnOfInvestment() {
        //given
        LottoWinningStatisticsService lottoWinningStatisticsService = new LottoWinningStatisticsService();

        int money = 14000;
        Lotto lotto = new Lotto(14000, 14);

        int winnings = 3000;
        //when
        double expectedReturnOfInvestment = (double) winnings / money;

        double returnOfInvestment = lottoWinningStatisticsService.caculateReturnOfInvestment(lotto, winnings);

        //then
        assertThat(expectedReturnOfInvestment).isEqualTo(returnOfInvestment);
    }
}