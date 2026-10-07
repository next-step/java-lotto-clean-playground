package domain.purchase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PurchasePriceTest {
    @ParameterizedTest
    @DisplayName("구입 금액에 따라 실제 구매 금액을 반납한다.")
    @CsvSource({
            "1000, 1000",
            "1001, 1000",
            "2500, 2000"
    })
    void returnRealPurchasePrice(int purchasePrice, int spentAmount) {
        PurchasePrice purchasePrice1 = new PurchasePrice(purchasePrice);
        assertEquals(spentAmount, purchasePrice1.calculateSpentAmount());
    }

    @Test
    @DisplayName("구입 금액이 999원일 때 오류가 발생한다.")
    void errorWhenPurchasedPriceIs999() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new PurchasePrice(999)
        );
        assertEquals("로또 최소 구매 금액은 1000원입니다.",  exception.getMessage());
    }

    @ParameterizedTest
    @DisplayName("구매 가격에 따른 로또 장수를 반납한다.")
    @CsvSource({
            "1000, 1",
            "2000, 2",
            "3000, 3",
            "10000, 10"
    })
    void returnLottoCountBasedOnPurchasePrice(int amount, int expected) {
        PurchasePrice purchasePrice = new PurchasePrice(amount);
        assertEquals(expected, purchasePrice.calculateLottoCount());
    }
}
