package domain.money;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PurchaseAmountTest {

    @Test
    @DisplayName("구입 금액이 0원 이하면 예외가 발생한다")
    void throwExceptionWhenPurchaseAmountIsNotPositive() {
        assertThatThrownBy(() -> PurchaseAmount.from(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구입 금액은 0원보다 커야 합니다.");
        assertThatThrownBy(() -> PurchaseAmount.from(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구입 금액은 0원보다 커야 합니다.");
    }

    @Test
    @DisplayName("구입 금액이 1000원 단위가 아니면 예외가 발생한다")
    void throwExceptionWhenPurchaseAmountIsNotDivisibleByThousand() {
        assertThatThrownBy(() -> PurchaseAmount.from(9_500))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구입 금액은 1000원 단위여야 합니다.");
    }
}
