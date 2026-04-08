package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.LottoNumberGenerator;
import util.FixedLottoNumberGenerator;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StatisticTest {
    @DisplayName("로또 결과 채점 테스트")
    @Test
    void testWinningResult() {
        // given
        List<Integer> sequence = List.of(1, 5, 3, 2, 6, 7);

        LottoNumberGenerator lottoNumberGenerator = new FixedLottoNumberGenerator(sequence);
        LottoService lottoService = new LottoService(lottoNumberGenerator);
        Statistic statistic = new Statistic();

        // when
        List<LottoTicket> tickets = lottoService.buyTickets(2000);
        WinningResultDto result = statistic.getWinningResult(
                tickets,
                List.of(1, 5, 3, 11, 12, 13)
        );

        // then
        assertThat(result.getThreeMatchCount()).isEqualTo(2);
        assertThat(result.getFourMatchCount()).isEqualTo(0);
        assertThat(result.getFiveMatchCount()).isEqualTo(0);
        assertThat(result.getSixMatchCount()).isEqualTo(0);
    }

    @DisplayName("수익률 계산 테스트")
    @Test
    void testRevenue() {
        // given
        Statistic statistic = new Statistic();
        WinningResultDto result = new WinningResultDto();
        result.addMatchCount(3);
        result.addMatchCount(3);
        result.addMatchCount(4);

        // when
        double revenue = statistic.getRevenue(3000, result);

        // then
        double expected = (WinningPrizeDto.THREE_MATCH_PRIZE * 2
                + WinningPrizeDto.FOUR_MATCH_PRIZE) / 3000.0;

        assertThat(revenue).isEqualTo(expected);
    }

    @DisplayName("3개, 4개, 5개, 6개 당첨 개수를 각각 집계한다")
    @Test
    void testWinningResultCounts() {
        // given
        Statistic statistic = new Statistic();
        List<LottoTicket> tickets = getLottoTickets();
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);

        // when
        WinningResultDto result = statistic.getWinningResult(tickets, winningNumbers);
        // then
        assertThat(result.getThreeMatchCount()).isEqualTo(1);
        assertThat(result.getFourMatchCount()).isEqualTo(1);
        assertThat(result.getFiveMatchCount()).isEqualTo(1);
        assertThat(result.getSixMatchCount()).isEqualTo(1);
    }

    private static List<LottoTicket> getLottoTickets() {
        LottoTicket threeMatchTicket = new LottoTicket(List.of(1, 2, 3, 40, 41, 42));
        LottoTicket fourMatchTicket = new LottoTicket(List.of(1, 2, 3, 4, 41, 42));
        LottoTicket fiveMatchTicket = new LottoTicket(List.of(1, 2, 3, 4, 5, 42));
        LottoTicket sixMatchTicket = new LottoTicket(List.of(1, 2, 3, 4, 5, 6));

        return List.of(
                threeMatchTicket,
                fourMatchTicket,
                fiveMatchTicket,
                sixMatchTicket
        );
    }
}
