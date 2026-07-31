package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PurchaseAmountTest {
    @Test
    @DisplayName("구입 금액에 따라 구매 가능한 로또 개수를 계산한다")
    void calculatesLottoCountBasedOnPurchaseAmount() {
        PurchaseAmount purchaseAmount = new PurchaseAmount(5000);

        assertEquals(5, purchaseAmount.calculateLottoCount());
    }

    @Test
    @DisplayName("최소 구입 금액으로 구매 가능한 로또 개수는 1개이다")
    void calculatesOneLottoForMinimumPurchaseAmount() {
        PurchaseAmount purchaseAmount = new PurchaseAmount(1000);

        assertEquals(1, purchaseAmount.calculateLottoCount());
    }

    @ParameterizedTest
    @ValueSource(ints = {999, 0, -5000})
    @DisplayName("구입 금액이 최소 구입 금액보다 작으면 예외가 발생한다")
    void throwsExceptionWhenPurchaseAmountIsLessThanLottoPrice(int amount) {
        assertThrows(
                IllegalArgumentException.class,
                () -> new PurchaseAmount(amount)
        );
    }
}
