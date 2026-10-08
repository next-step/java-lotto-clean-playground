package domain.purchase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class PurchaseCountTest {
    @ParameterizedTest
    @DisplayName("자동 구매 수는 전체 장수에서 수동 구매 수를 뺀 값이다.")
    @CsvSource({
            "14, 3, 11",
            "14, 0, 14",
            "14, 14, 0"
    })
    void autoCountIsTotalMinusManual(int totalCount, int manualCount, int expectedAutoCount) {
        // when
        PurchaseCount purchaseCount = new PurchaseCount(totalCount, manualCount);

        // then
        assertEquals(manualCount, purchaseCount.getManualCount());
        assertEquals(expectedAutoCount, purchaseCount.getAutoCount());
    }

    @ParameterizedTest
    @DisplayName("수동 구매 수가 0보다 작거나 전체 장수보다 크면 오류가 발생한다.")
    @ValueSource(ints = {-1, 15})
    void errorWhenManualCountOutOfRange(int manualCount) {
        // when & then
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new PurchaseCount(14, manualCount));
        assertEquals("수동 구매 수는 0 이상, 구매 가능한 장수 이하여야 합니다.", exception.getMessage());
    }
}
