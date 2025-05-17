package domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import exception.InvalidPurchasePriceException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoPurchasePriceTest {
    @Test
    @DisplayName("로또 구매 금액 테스트: 정상")
    void validLottoPurchasePriceTest() {
        assertDoesNotThrow(() -> new LottoPurchasePrice(2000));
    }

    @Test
    @DisplayName("로또 구매 금액 테스트: 음수")
    void negativePurchasePriceTest() {
        assertThrows(IllegalArgumentException.class, () -> new LottoPurchasePrice(-200));
    }

    @Test
    @DisplayName("로또 구매 금액 테스트: 1000원 단위로 안떨어지는")
    void isNotThousandUnitPurchasePriceTest() {
        assertThrows(InvalidPurchasePriceException.class, () -> new LottoPurchasePrice(2500));
    }
}
