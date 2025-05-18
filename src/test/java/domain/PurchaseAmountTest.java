package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PurchaseAmountTest {

    @Test
    @DisplayName("입력된 금액이 1000원보다 작을 경우 예외가 발생한다.")
    void shouldThrowException_whenPurchaseAmountIsLessThanMinAmount() {
        // given
        int invalidAmount = 500;

        // when & then
        assertThatThrownBy(() -> new PurchaseAmount(invalidAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구입 금액은 최소 1000원 이상이어야 합니다.");
    }

    @Test
    @DisplayName("입력된 금액이 1000원 단위가 아닐 경우 예외가 발생한다.")
    void shouldThrowException_whenPurchaseAmountInvalidUnit() {
        // given
        int invalidAmount = 15500;

        // when & then
        assertThatThrownBy(() -> new PurchaseAmount(invalidAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구입 금액은 1000원 단위여야 합니다.");
    }
}
