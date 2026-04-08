package model;

import constants.LottoSettingsConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoFinanceStatsCalculatorTest {
    private LottoBatch lottoBatch;
    private WinCondition winCondition;
    private LottoFinanceStatsCalculator calculator;

    @BeforeEach
    void setUp() {
        lottoBatch = new LottoBatch();
        winCondition = new WinCondition(List.of(1, 2, 3, 4, 5, 6), 7);
    }

    @Test
    @DisplayName("로또 결과 리스트를 올바르게 반환한다")
    void testGetLottoResults() {
        // given
        Lotto lotto1 = new Lotto(List.of(1, 2, 3, 10, 11, 12));
        Lotto lotto2 = new Lotto(List.of(1, 2, 3, 4, 5, 12));
        lottoBatch.add(lotto1);
        lottoBatch.add(lotto2);
        calculator = new LottoFinanceStatsCalculator(lottoBatch);

        // when
        List<LottoResult> results = calculator.getLottoResults(winCondition);

        // then
        assertThat(results).containsExactlyInAnyOrder(LottoResult.THREE, LottoResult.FIVE);
    }

    @Test
    @DisplayName("수익률을 올바르게 계산한다")
    void testGetReturnRatio() {
        // given
        lottoBatch.add(new Lotto(List.of(1, 2, 3, 4, 5, 7)));
        lottoBatch.add(new Lotto(List.of(10, 11, 12, 13, 14, 15)));
        calculator = new LottoFinanceStatsCalculator(lottoBatch);

        // when
        double ratio = calculator.getReturnRatio(winCondition);

        // then
        double expectedRatio = ((double) LottoResult.FIVE_WITH_BONUS.getReward() + LottoResult.NO_MATCH.getReward()) / (LottoSettingsConstants.LOTTO_PRICE * lottoBatch.getLottoCount());
        Assertions.assertEquals(expectedRatio, ratio);
    }
}
