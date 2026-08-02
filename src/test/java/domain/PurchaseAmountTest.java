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

    @Test
    @DisplayName("수동 구매 개수가 음수이면 예외가 발생한다")
    void throwsExceptionWhenManualLottoCountIsNegative() {
        PurchaseAmount purchaseAmount = new PurchaseAmount(14000);
        int manualLottoCount = -2;

        assertThrows(
                IllegalArgumentException.class,
                () -> purchaseAmount.calculateAutoLottoCount(manualLottoCount)
        );
    }

    @Test
    @DisplayName("수동 구매 개수가 전체 구매 가능 개수를 초과하면 예외가 발생한다")
    void throwsExceptionWhenManualLottoCountExceedsTotalLottoCount() {
        PurchaseAmount purchaseAmount = new PurchaseAmount(14000);
        int manualLottoCount = 15;

        assertThrows(
                IllegalArgumentException.class,
                () -> purchaseAmount.calculateAutoLottoCount(manualLottoCount)
        );
    }

    @Test
    @DisplayName("수동 구매 개수를 제외한 자동 구매 개수를 계산한다")
    void calculatesAutoLottoCountWithValidManualLottoCount() {
        PurchaseAmount purchaseAmount = new PurchaseAmount(14000);
        int manualLottoCount = 3;

        assertEquals(11, purchaseAmount.calculateAutoLottoCount(manualLottoCount));
    }
}
