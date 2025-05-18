package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static support.LottoTestHelper.lotto;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProfitRateCalculatorTest {

    @Test
    @DisplayName("정상적으로 수익률을 계산한다.")
    void shouldReturnCalculateProfitRate() {
        // given
        WinningLotto winningLotto = new WinningLotto("1,2,3,4,5,6");

        Lottos purchasedLottos = new Lottos(List.of(
                lotto(1, 2, 3, 4, 5, 6),
                lotto(1, 2, 3, 4, 5, 7)
        ));

        WinningStatistics statistics = new WinningStatistics(winningLotto, purchasedLottos);

        // when
        long purchaseAmount = 2_000;
        double result = ProfitRateCalculator.calculateProfitRate(statistics, purchaseAmount);

        // then
        long expectedTotalPrize = Rank.FIRST.getPrize() + Rank.SECOND.getPrize();
        double expectedRate = (double) expectedTotalPrize / purchaseAmount;

        assertThat(result).isEqualTo(expectedRate);
    }
}
