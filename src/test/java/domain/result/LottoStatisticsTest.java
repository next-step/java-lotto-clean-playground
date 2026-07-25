package domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import domain.lotto.BonusBall;
import domain.lotto.Lotto;
import domain.lotto.Lottos;
import domain.lotto.WinningLotto;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoStatisticsTest {

    @Test
    @DisplayName("로또 번호와 당첨 번호를 비교해 당첨 통계를 계산한다")
    void calculateLottoStatistics() {
        Lottos lottos = new Lottos(createLottos());
        WinningLotto winningLotto = WinningLotto.from(List.of(1, 2, 3, 4, 5, 6));

        LottoStatistics statistics = lottos.calculateLottoStatistics(winningLotto);

        assertThat(statistics.countOf(LottoRank.THREE_MATCHES)).isEqualTo(1);
        assertThat(statistics.countOf(LottoRank.FOUR_MATCHES)).isEqualTo(1);
        assertThat(statistics.countOf(LottoRank.FIVE_MATCHES)).isEqualTo(1);
        assertThat(statistics.countOf(LottoRank.SIX_MATCHES)).isEqualTo(1);
    }

    @Test
    @DisplayName("5개 일치하고 보너스 볼이 일치하면 2등으로 집계한다")
    void calculateSecondPlace() {
        Lottos lottos = new Lottos(List.of(new Lotto(List.of(1, 2, 3, 4, 5, 7))));
        WinningLotto winningLotto = WinningLotto.of(
                List.of(1, 2, 3, 4, 5, 6),
                BonusBall.from(7)
        );

        LottoStatistics statistics = lottos.calculateLottoStatistics(winningLotto);

        assertThat(statistics.countOf(LottoRank.BONUS_BALL_MATCHES)).isEqualTo(1);
        assertThat(statistics.countOf(LottoRank.FIVE_MATCHES)).isZero();
    }

    private List<Lotto> createLottos() {
        return List.of(
                new Lotto(List.of(1, 2, 3, 10, 11, 12)),
                new Lotto(List.of(1, 2, 3, 4, 11, 12)),
                new Lotto(List.of(1, 2, 3, 4, 5, 12)),
                new Lotto(List.of(1, 2, 3, 4, 5, 6))
        );
    }
}
