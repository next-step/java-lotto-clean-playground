package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PrizeMoneyTest {

    @Test
    @DisplayName("우승 상금은 음수일 수 없다")
    void 우승_상금은_음수일_수_없다() {
        assertThatThrownBy(() -> new PrizeMoney(-1))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
