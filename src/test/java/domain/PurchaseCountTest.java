package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PurchaseCountTest {

    @DisplayName("수동 구매 개수가 총 구매 개수보다 많으면 예외가 발생한다.")
    @Test
    void overManualCountTest() {
        assertThatThrownBy(() -> new PurchaseCount(14, 15))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("수동 구매 개수가 음수면 예외가 발생한다.")
    @Test
    void negativeManualCountTest() {
        assertThatThrownBy(() -> new PurchaseCount(14, -1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("수동 구매 개수를 제외한 나머지 개수를 자동 구매 개수로 계산한다.")
    @Test
    void calculateAutoCountTest() {
        PurchaseCount count = new PurchaseCount(14, 3);
        assertThat(count.getManual()).isEqualTo(3);
        assertThat(count.getAuto()).isEqualTo(11);
    }
}