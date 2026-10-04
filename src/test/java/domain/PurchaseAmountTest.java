package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class PurchaseAmountTest {

    @ParameterizedTest
    @CsvSource({"2000, 1", "14000, 13"})
    void calculatesPurchaseCount(int amount, int expectedCount) {
        // 준비
        PurchaseAmount purchaseAmount = new PurchaseAmount(amount, 1);
        // 실행
        int randomCount = purchaseAmount.getRandomCount();
        // 검증
        assertThat(randomCount).isEqualTo(expectedCount);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 999, -1000})
    void throwsExceptionWhenAmountIsLessThanPrice(int amount) {
        assertThatThrownBy(() -> new PurchaseAmount(amount, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

}
