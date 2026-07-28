package domain.lotto;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ManualPurchaseCountTest {

    @Test
    @DisplayName("수동 구매 수가 음수이면 예외가 발생한다")
    void throwExceptionWhenManualPurchaseCountIsNegative() {
        assertThatThrownBy(() -> ManualPurchaseCount.from(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수동 구매 수는 0 이상이어야 합니다.");
    }
}
