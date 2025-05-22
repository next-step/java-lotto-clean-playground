package domain;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class YieldCalculatorTest {

    @Test
    void testCalculatingYield() {
        Map<LottoRank, Integer> WinningResultMap = Map.of(
                LottoRank.FIRST, 1,
                LottoRank.SECOND, 0,
                LottoRank.THIRD, 1,
                LottoRank.FOURTH, 1,
                LottoRank.FIFTH, 1,
                LottoRank.NONE, 2
        );

        Amount amount = new Amount(6000);
        WinningResult winningResult = new WinningResult(WinningResultMap);

        double expected = 333_592.5;
        assertThat(YieldCalculator.calculateYield(winningResult, amount))
                .isEqualTo(expected);

    }

    @Test
    void whenYieldIsLessThenOne_thenReturnTrue() {
        assertAll(
                () -> assertThat(YieldCalculator.isLoss(0.99)).isTrue(),
                () -> assertThat(YieldCalculator.isLoss(1.00)).isFalse()
        );
    }

}
