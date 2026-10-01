package domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @Test
    void 천원_미만이면_예외가_발생한다() {
        assertThatThrownBy(() -> new PurchaseAmount(0))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
