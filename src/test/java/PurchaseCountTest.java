import domain.PurchaseCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PurchaseCountTest {
    @Test
    @DisplayName("수동 구매 수가 0이다.")
    void manualCountZero() {
        assertDoesNotThrow(() -> PurchaseCount.from(5, 0));
    }

    @Test
    @DisplayName("수동 구매 수와 전체 구매 수가 같다.")
    void manualSameTotal() {
        PurchaseCount purchaseCount = PurchaseCount.from(5, 5);
        assertEquals(0, purchaseCount.calculateAutoCount());
    }

    @Test
    @DisplayName("수동 구매 수가 전체 구매 수보다 크면 안 된다.")
    void manualSmallerThanTotal() {
        assertThrows(IllegalArgumentException.class, () -> PurchaseCount.from(1, 2));
    }

    @Test
    @DisplayName("수동 구매 수가 음수이면 안 된다.")
    void manualNegative() {
        assertThrows(IllegalArgumentException.class, () -> PurchaseCount.from(1, -1));
    }
}
