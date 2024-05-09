package domain.lottoWiningsStatistics;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.lotto.Lotto;
import domain.lottoNumber.LottoNumber;
import domain.lottoTicket.LottoTicket;
import domain.lottoWinningStatistics.LottoWinningStatisticsService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoWiningStatisticsServiceTest {
    private final LottoWinningStatisticsService lottoWinningStatisticsService = new LottoWinningStatisticsService();
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
        assertThat(matchingCount).isEqualTo(expectedMatchingCount);

    }

    @Test
    @DisplayName("랭크 리스트 생산 테스트")
    public void generateWinningStatisticTest() {
        //given
        List<Integer> winningNumber = List.of(1, 2, 3, 4, 5, 6);
        int bonusBall = 7;

        LottoTicket lottoTicket1 = new LottoTicket(new LottoNumber(List.of(1, 2, 3, 4, 5, 6)));
        LottoTicket lottoTicket2 = new LottoTicket(new LottoNumber(List.of(1, 2, 3, 4, 5, 7)));
        LottoTicket lottoTicket3 = new LottoTicket(new LottoNumber(List.of(1, 2, 3, 4, 7, 8)));

        List<LottoTicket> lottoTickets = new ArrayList<>();

        lottoTickets.add(lottoTicket1);
        lottoTickets.add(lottoTicket2);
        lottoTickets.add(lottoTicket3);

        //when

        //then
        assertAll(
        );
    }

    @Test
    @DisplayName("로또 구매 당첨금 계산 테스트")
    public void caculateWinningTest() {
        //given
        List<Integer> winningNumber = List.of(1, 2, 3, 4, 5, 6);
        int bonusBall = 7;

        LottoTicket lottoTicket1 = new LottoTicket(new LottoNumber(List.of(1, 2, 3, 7, 8, 9)));
        LottoTicket lottoTicket2 = new LottoTicket(new LottoNumber(List.of(1, 2, 12, 7, 8, 9)));
        LottoTicket lottoTicket3 = new LottoTicket(new LottoNumber(List.of(1, 2, 13, 7, 8, 9)));

        List<LottoTicket> lottoTickets = new ArrayList<>();

        lottoTickets.add(lottoTicket1);
        lottoTickets.add(lottoTicket2);
        lottoTickets.add(lottoTicket3);


        int expectedWinnings = 5000;

        //when

        //then
    }

    @Test
    @DisplayName("로또 구매로 얻은 수익률 반환 테스트")
    public void caculateReturnOfInvestmentTest() {
        //given
        List<Integer> winningNumber = List.of(1, 2, 3, 4, 5, 6);
        int bonusBall = 7;

        LottoTicket lottoTicket1 = new LottoTicket(new LottoNumber(List.of(1, 2, 3, 7, 8, 9)));
        LottoTicket lottoTicket2 = new LottoTicket(new LottoNumber(List.of(1, 2, 12, 7, 8, 9)));
        LottoTicket lottoTicket3 = new LottoTicket(new LottoNumber(List.of(1, 2, 13, 7, 8, 9)));

        List<LottoTicket> lottoTickets = new ArrayList<>();

        lottoTickets.add(lottoTicket1);
        lottoTickets.add(lottoTicket2);
        lottoTickets.add(lottoTicket3);


        int money = 14000;

        int winnings = 5000;

        //when
        double expectedReturnOfInvestment = (double) winnings / money;

        //then
    }
}