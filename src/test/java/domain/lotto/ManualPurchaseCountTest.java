package domain.lotto;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.money.PurchaseAmount;
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

    @Test
    @DisplayName("수동 구매 수가 전체 구매 수보다 많으면 예외가 발생한다")
    void throwExceptionWhenManualPurchaseCountExceedsTotalPurchaseCount() {
        ManualPurchaseCount manualPurchaseCount = ManualPurchaseCount.from(2);
        PurchaseAmount purchaseAmount = PurchaseAmount.from(1_000);

        assertThatThrownBy(() -> manualPurchaseCount.validateNotGreaterThan(purchaseAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수동 구매 수는 전체 구매 수를 넘을 수 없습니다.");
    }
}
