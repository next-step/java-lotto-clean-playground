package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class PurchaseAmountTest {

    @ParameterizedTest
    @CsvSource({"2000, 1, 1", "14000, 1, 13", "14000, 0, 14", "14000, 14, 0"})
    void calculatesPurchaseCount(int amount, int manualCount, int expectedRandomCount) {
        // 준비
        PurchaseAmount purchaseAmount = new PurchaseAmount(amount, manualCount);
        // 실행
        int randomCount = purchaseAmount.getRandomCount();
        // 검증
        assertThat(randomCount).isEqualTo(expectedRandomCount);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 999, -1000})
    void throwsExceptionWhenAmountIsLessThanPrice(int amount) {
        assertThatThrownBy(() -> new PurchaseAmount(amount, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 4, 100})
    void throwsExceptionWhenManualCountIsOutOfRange(int manualCount) {
        assertThatThrownBy(() -> new PurchaseAmount(3000, manualCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

}
