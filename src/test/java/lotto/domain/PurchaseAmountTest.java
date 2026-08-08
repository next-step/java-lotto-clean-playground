package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class PurchaseAmountTest {

    @Test
    void calculateTotalCount() {
        PurchaseAmount purchaseAmount =
                new PurchaseAmount(14_000);

        assertThat(purchaseAmount.calculateTotalCount())
                .isEqualTo(14);
    }

    @Test
    void calculateAutomaticCount() {
        PurchaseAmount purchaseAmount =
                new PurchaseAmount(14_000);

        assertThat(purchaseAmount.calculateAutomaticCount(3))
                .isEqualTo(11);
    }

    @Test
    void createPurchaseAmountLessThanMinimum() {
        assertThatThrownBy(
                () -> new PurchaseAmount(1_500)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
