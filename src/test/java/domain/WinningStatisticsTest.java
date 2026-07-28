package domain;

import domain.enums.LotteryPrize;
import domain.lotto.Lotto;
import domain.lotto.WinningLotto;
import domain.lotto.collection.LottoTickets;
import domain.lotto.collection.WinningStatistics;
import domain.lotto.wrap.LottoNumber;
import domain.lotto.wrap.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static helper.TestHelperMethod.toLottoNumbers;
import static org.assertj.core.api.Assertions.assertThat;

public class WinningStatisticsTest {

    private final WinningLotto winningLotto = new WinningLotto(
            new Lotto(toLottoNumbers(1, 2, 3, 4, 5, 6)), new LottoNumber(7));

    private final Money paid = new Money(1_000);

    private WinningStatistics statisticsOf(int... ticketNumbers) {
        LottoTickets tickets = new LottoTickets(List.of(new Lotto(toLottoNumbers(ticketNumbers))));
        return tickets.match(winningLotto);
    }

    @Test
    @DisplayName("3개 일치하면 5등, 수익률은 5.0")
    void fifthPrize() {
        // when
        WinningStatistics statistics = statisticsOf(1, 2, 3, 43, 44, 45);

        // then
        assertThat(statistics.countOf(LotteryPrize.FIFTH)).isEqualTo(1);
        assertThat(statistics.returnRate(paid)).isEqualTo(5.0);
    }

    @Test
    @DisplayName("4개 일치하면 4등, 수익률은 50.0")
    void fourthPrize() {
        // when
        WinningStatistics statistics = statisticsOf(1, 2, 3, 4, 44, 45);

        // then
        assertThat(statistics.countOf(LotteryPrize.FOURTH)).isEqualTo(1);
        assertThat(statistics.returnRate(paid)).isEqualTo(50.0);
    }

    @Test
    @DisplayName("5개 일치하면 3등, 수익률은 1500.0")
    void thirdPrize() {
        // when
        WinningStatistics statistics = statisticsOf(1, 2, 3, 4, 5, 45);

        // then
        assertThat(statistics.countOf(LotteryPrize.THIRD)).isEqualTo(1);
        assertThat(statistics.countOf(LotteryPrize.SECOND)).isEqualTo(0);
        assertThat(statistics.returnRate(paid)).isEqualTo(1_500.0);
    }

    @Test
    @DisplayName("5개 일치 + 보너스 볼 일치하면 2등, 수익률은 30000.0이다")
    void secondPrize() {
        // when
        WinningStatistics statistics = statisticsOf(1, 2, 3, 4, 5, 7);

        // then
        assertThat(statistics.countOf(LotteryPrize.SECOND)).isEqualTo(1);
        assertThat(statistics.countOf(LotteryPrize.THIRD)).isEqualTo(0);
        assertThat(statistics.returnRate(paid)).isEqualTo(30_000.0);
    }

    @Test
    @DisplayName("6개 일치하면 1등, 수익률은 2000000.0이다")
    void firstPrize() {
        // when
        WinningStatistics statistics = statisticsOf(1, 2, 3, 4, 5, 6);

        // then
        assertThat(statistics.countOf(LotteryPrize.FIRST)).isEqualTo(1);
        assertThat(statistics.returnRate(paid)).isEqualTo(2_000_000.0);
    }

    @Test
    @DisplayName("수익률이 1 이상이면 이득, 미만이면 손해다")
    void profitOrLoss() {
        // when
        WinningStatistics win = statisticsOf(1, 2, 3, 43, 44, 45);
        WinningStatistics lose = statisticsOf(40, 41, 42, 43, 44, 45);

        // then
        assertThat(win.isProfit(paid)).isEqualTo(true);
        assertThat(lose.isProfit(paid)).isEqualTo(false);
    }
}
