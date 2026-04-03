package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PurchaseAmountTest {

    @Test
    void 구입금액이_1000원_미만이면_예외가_발생한다() {
        assertThatThrownBy(() -> new PurchaseAmount(999))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구입 금액은 1000원 이상이어야 합니다.");
    }

    @Test
    void 구입금액이_1000원_단위가_아니면_예외가_발생한다() {
        assertThatThrownBy(() -> new PurchaseAmount(1500))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구입 금액은 1000원 단위여야 합니다.");
    }

    @Test
    void 구입금액으로_구매할_로또_개수를_계산한다() {
        PurchaseAmount purchaseAmount = new PurchaseAmount(5000);

        assertThat(purchaseAmount.calculateLottoCount()).isEqualTo(5);
    }
}
