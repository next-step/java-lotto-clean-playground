package domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StatisticTest {
    @DisplayName("로또 결과 채점 테스트")
    @Test
    void testWinningResult() {
        // given
        List<Integer> sequence = List.of(1, 5, 3, 2, 6, 7);

        LottoGenerator lottoGenerator = new FixedLottoGenerator(sequence);
        LottoService lottoService = new LottoService(lottoGenerator);
        Statistic statistic = new Statistic();

        // when
        List<LottoTicket> tickets = lottoService.buyTickets(2000, List.of());
        Map<WinningRank, Integer> result = statistic.getWinningResult(
                tickets,
                new WinningNumbers(
                        toLottoNumbers(List.of(1, 5, 3, 11, 12, 13)),
                        new LottoNumber(14)
                )
        );

        // then
        assertThat(result.get(WinningRank.THREE)).isEqualTo(2);
        assertThat(result.get(WinningRank.FOUR)).isEqualTo(0);
        assertThat(result.get(WinningRank.FIVE)).isEqualTo(0);
        assertThat(result.get(WinningRank.SIX)).isEqualTo(0);
    }

    @DisplayName("수익률 계산 테스트")
    @Test
    void testRevenue() {
        // given
        Statistic statistic = new Statistic();
        Map<WinningRank, Integer> result = initializeResult();
        result.put(WinningRank.THREE, 2);
        result.put(WinningRank.FOUR, 1);

        // when
        double revenue = statistic.getRevenue(3000, result);

        // then
        double expected = (WinningRank.THREE.getPrize() * 2
                + WinningRank.FOUR.getPrize()) / 3000.0;

        assertThat(revenue).isEqualTo(expected);
    }

    @DisplayName("3개, 4개, 5개, 6개 당첨 개수를 각각 집계한다")
    @Test
    void testWinningResultCounts() {
        // given
        Statistic statistic = new Statistic();
        List<LottoTicket> tickets = getLottoTickets();
        WinningNumbers winningNumbers = new WinningNumbers(
                toLottoNumbers(List.of(1, 2, 3, 4, 5, 6)),
                new LottoNumber(7)
        );

        // when
        Map<WinningRank, Integer> result = statistic.getWinningResult(tickets, winningNumbers);
        // then
        assertThat(result.get(WinningRank.THREE)).isEqualTo(1);
        assertThat(result.get(WinningRank.FOUR)).isEqualTo(1);
        assertThat(result.get(WinningRank.FIVE)).isEqualTo(1);
        assertThat(result.get(WinningRank.SIX)).isEqualTo(1);
        assertThat(result.get(WinningRank.FIVE_BONUS)).isEqualTo(0);
    }

    private static Map<WinningRank, Integer> initializeResult() {
        Map<WinningRank, Integer> result = new EnumMap<>(WinningRank.class);

        for (WinningRank rank : WinningRank.values()) {
            result.put(rank, 0);
        }

        return result;
    }

    private static List<LottoTicket> getLottoTickets() {
        LottoTicket threeMatchTicket = new LottoTicket(toLottoNumbers(List.of(1, 2, 3, 40, 41, 42)));
        LottoTicket fourMatchTicket = new LottoTicket(toLottoNumbers(List.of(1, 2, 3, 4, 41, 42)));
        LottoTicket fiveMatchTicket = new LottoTicket(toLottoNumbers(List.of(1, 2, 3, 4, 5, 42)));
        LottoTicket sixMatchTicket = new LottoTicket(toLottoNumbers(List.of(1, 2, 3, 4, 5, 6)));

        return List.of(
                threeMatchTicket,
                fourMatchTicket,
                fiveMatchTicket,
                sixMatchTicket
        );
    }

    private static List<LottoNumber> toLottoNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::new)
                .toList();
    }

}
