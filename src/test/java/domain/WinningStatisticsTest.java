package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static support.LottoTestHelper.lotto;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningStatisticsTest {

    @Test
    @DisplayName("구매한 로또 번호와 지난 당첨 번호의 비교해 등수별 당첨 통계를 정확히 보여준다.")
    void shouldReturnWinningStatistics_whenComparingPurchasedLottoAndLastWinningNumber() {
        // given
        WinningLotto winningLotto = new WinningLotto("1,2,3,4,5,6");

        Lottos purchasedLottos = new Lottos(List.of(
                lotto(1, 2, 3, 4, 5, 6),
                lotto(1, 2, 3, 4, 5, 7),
                lotto(1, 2, 3, 4, 8, 9),
                lotto(1, 2, 3, 8, 9, 10),
                lotto(10, 11, 12, 13, 14, 15)
        ));

        // when
        WinningStatistics statistics = new WinningStatistics(winningLotto, purchasedLottos);

        // then
        assertThat(Rank.values())
                .extracting(statistics::getCount)
                .containsExactly(1, 1, 1, 1, 1);
    }

    @Test
    @DisplayName("총 당첨 금액을 정상적으로 계산한다.")
    void shouldReturnCalculateTotalPrize() {
        // given
        WinningLotto winningLotto = new WinningLotto("1,2,3,4,5,6");

        Lottos purchasedLottos = new Lottos(List.of(
                lotto(1, 2, 3, 4, 5, 6),
                lotto(1, 2, 3, 4, 5, 7)
        ));

        WinningStatistics statistics = new WinningStatistics(winningLotto, purchasedLottos);

        // when
        long totalPrize = statistics.getTotalPrize();

        // then
        assertThat(totalPrize).isEqualTo(2_000_000_000L + 1_500_000L);
    }
}
