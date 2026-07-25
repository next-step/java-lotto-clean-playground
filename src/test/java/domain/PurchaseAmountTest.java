package domain;

<<<<<<< HEAD
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PurchaseAmountTest {
    @Test
    @DisplayName("amount is below minimum")
    void exceptionAmountMinimum() {
        assertThatThrownBy(() -> new PurchaseAmount(500))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구입 금액은 1000원 이상이어야 합니다");
    }

    @Test
    @DisplayName("amount is not a multiple of 1000")
    void exceptionAmountMultiple() {
        assertThatThrownBy(() -> new PurchaseAmount(1500))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구입 금액은 1000원 단위어야 합니다");
    }

    @Test
    @DisplayName("calculate lotto count")
    void calculateLottoCount() {
        PurchaseAmount purchaseAmount = new PurchaseAmount(14000);

        assertThat(purchaseAmount.calculateLottoCount()).isEqualTo(14);
    }

    @Test
    @DisplayName("manual count is negative")
    void manualCountNegative() {
        PurchaseAmount purchaseAmount = new PurchaseAmount(14000);

        assertThatThrownBy(() -> purchaseAmount.calculateAutoCount(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수동 구매 개수는 0 이상이어야 합니다.");
    }

    @Test
    @DisplayName("manual count exceeds total count")
    void exceptionManualCountExceedsTotal() {
        PurchaseAmount purchaseAmount = new PurchaseAmount(14000);

        assertThatThrownBy(() -> purchaseAmount.calculateAutoCount(15))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수동 구매 개수는 전체 구매 개수보다 많을 수 없습니다.");
    }

    @Test
    @DisplayName("calculate auto count")
    void calculateAutoCount() {
        PurchaseAmount purchaseAmount = new PurchaseAmount(14000);

        assertThat(purchaseAmount.calculateAutoCount(3)).isEqualTo(11);
    }
=======
public class PurchaseAmountTest {
>>>>>>> ea80e16 (feat : 5단ãã계 리팩토ã링 및 테스트 코드 추가)
}
