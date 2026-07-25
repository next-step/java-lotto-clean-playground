package domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import domain.lotto.BonusBall;
import domain.lotto.LottoTicket;
import domain.lotto.PurchasedLottos;
import domain.lotto.WinningLotto;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoStatisticsCalculatorTest {

    @Test
    @DisplayName("로또 번호와 당첨 번호를 비교해 당첨 통계를 계산한다")
    void calculateLottoStatistics() {
        PurchasedLottos lottos = new PurchasedLottos(createLottos());
        WinningLotto winningLotto = WinningLotto.from(List.of(1, 2, 3, 4, 5, 6));
        LottoStatisticsCalculator calculator = new LottoStatisticsCalculator();

        LottoStatistics statistics = calculator.calculate(lottos, winningLotto);

        assertThat(statistics.countOf(LottoRank.THREE_MATCHES)).isEqualTo(1);
        assertThat(statistics.countOf(LottoRank.FOUR_MATCHES)).isEqualTo(1);
        assertThat(statistics.countOf(LottoRank.FIVE_MATCHES)).isEqualTo(1);
        assertThat(statistics.countOf(LottoRank.SIX_MATCHES)).isEqualTo(1);
    }

    @Test
    @DisplayName("5개 일치하고 보너스 볼이 일치하면 2등으로 집계한다")
    void calculateSecondPlace() {
        PurchasedLottos lottos = new PurchasedLottos(List.of(new LottoTicket(List.of(1, 2, 3, 4, 5, 7))));
        WinningLotto winningLotto = WinningLotto.of(
                List.of(1, 2, 3, 4, 5, 6),
                BonusBall.from(7)
        );
        LottoStatisticsCalculator calculator = new LottoStatisticsCalculator();

        LottoStatistics statistics = calculator.calculate(lottos, winningLotto);

        assertThat(statistics.countOf(LottoRank.BONUS_BALL_MATCHES)).isEqualTo(1);
        assertThat(statistics.countOf(LottoRank.FIVE_MATCHES)).isZero();
    }

    private List<LottoTicket> createLottos() {
        return List.of(
                new LottoTicket(List.of(1, 2, 3, 10, 11, 12)),
                new LottoTicket(List.of(1, 2, 3, 4, 11, 12)),
                new LottoTicket(List.of(1, 2, 3, 4, 5, 12)),
                new LottoTicket(List.of(1, 2, 3, 4, 5, 6))
        );
    }
}
