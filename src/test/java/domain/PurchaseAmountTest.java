package domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class PurchaseAmountTest {

    @ParameterizedTest
    @CsvSource({"1000, 1", "14000, 14"})
    void calculatesPurchaseCount(int amount, int expectedCount) {
        // 준비
        PurchaseAmount purchaseAmount = new PurchaseAmount(amount);
        // 실행
        int actualCount = purchaseAmount.getLottosCount();
        // 검증
        assertThat(actualCount).isEqualTo(expectedCount);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 999, -1000})
    void throwsExceptionWhenAmountIsLessThanPrice(int amount) {
        assertThatThrownBy(() -> new PurchaseAmount(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

}
