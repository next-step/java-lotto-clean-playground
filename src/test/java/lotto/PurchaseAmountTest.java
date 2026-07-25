package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PurchaseAmountTest {

    @Test
    @DisplayName("구입 금액으로 구매할 로또 개수를 계산한다")
    void calculateLottoCount() {
        PurchaseAmount purchaseAmount = new PurchaseAmount(14_000);

        assertThat(purchaseAmount.calculateLottoCount()).isEqualTo(14);
    }
}
