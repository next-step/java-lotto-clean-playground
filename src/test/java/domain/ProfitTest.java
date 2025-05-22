package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class ProfitTest {

    @Test
    @DisplayName("수익률이 1.5이면 손해가 아니다")
    void profitIsNotLossIfRateOverOne() {
        Profit profit = new Profit(new LottoStatistics(Map.of(Rank.THIRD, 1)), 1_000_000);
        assertThat(profit.rate()).isEqualTo(1.5);
        assertThat(profit.isLoss()).isFalse();
    }

    @Test
    @DisplayName("수익률이 0.5이면 손해이다")
    void profitIsLossIfRateBelowOne() {
        Profit profit = new Profit(new LottoStatistics(Map.of(Rank.FIFTH, 1)), 10_000);
        assertThat(profit.rate()).isEqualTo(0.5);
        assertThat(profit.isLoss()).isTrue();
    }

    @Test
    @DisplayName("수익률이 정확히 1이면 손해가 아니다")
    void profitIsNotLossIfRateEqualsOne() {
        Profit profit = new Profit(new LottoStatistics(Map.of(Rank.FIFTH, 2)), 10_000);
        assertThat(profit.rate()).isEqualTo(1.0);
        assertThat(profit.isLoss()).isFalse();
    }

    @Test
    @DisplayName("구입 금액이 0이면 예외가 발생한다")
    void profitThrowsIfPurchaseAmountIsZero() {
        assertThatThrownBy(() -> new Profit(new LottoStatistics(Map.of()), 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구입 금액은 0보다 커야 합니다.");
    }

    @Test
    @DisplayName("구입 금액이 음수이면 예외가 발생한다")
    void profitThrowsIfPurchaseAmountIsNegative() {
        assertThatThrownBy(() -> new Profit(new LottoStatistics(Map.of()), -1_000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구입 금액은 0보다 커야 합니다.");
    }
}

