import domain.PurchaseAmount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class PurchaseAmountTest {
    @Test
    @DisplayName("금액이 1000원보다 작으면 예외")
    void lessThan1000() {
        assertThrows(IllegalArgumentException.class, () -> PurchaseAmount.from(500));
    }

    @Test
    @DisplayName("금액이 1000원 단위가 아니면 예외")
    void not1000Multiple() {
        assertThrows(IllegalArgumentException.class, () -> PurchaseAmount.from(1500));
    }
}
