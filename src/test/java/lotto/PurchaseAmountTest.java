package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PurchaseAmountTest {

    @Test
    @DisplayName("구입 금액으로 구매할 로또 개수를 계산한다")
    void calculateLottoCount() {
        PurchaseAmount purchaseAmount = new PurchaseAmount(14_000);

        assertThat(purchaseAmount.calculateLottoCount()).isEqualTo(14);
    }

    @Test
    void 구입금액은_1장가격_이상이어야한다() {
        assertThatThrownBy(() -> {
            new PurchaseAmount(999);
        })
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 구입금액은_단위에_맞게_입력해야한다() {
        assertThatThrownBy(() -> {
            new PurchaseAmount(1_500);
        })
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("전체 구매 수에서 수동 구매 수를 제외한 자동 구매 수를 계산한다")
    void calculateAutomaticLottoCount() {
        PurchaseAmount purchaseAmount = new PurchaseAmount(14_000);

        assertThat(purchaseAmount.calculateAutomaticLottoCount(3)).isEqualTo(11);
    }

    @Test
    @DisplayName("수동 구매 수는 전체 구매 수를 넘을 수 없다")
    void rejectInvalidManualLottoCount() {
        PurchaseAmount purchaseAmount = new PurchaseAmount(14_000);

        assertThatThrownBy(() -> purchaseAmount.calculateAutomaticLottoCount(15))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 수동_구매_수는_음수일_수_없다() {
        PurchaseAmount purchaseAmount = new PurchaseAmount(14_000);

        assertThatThrownBy(() -> purchaseAmount.calculateAutomaticLottoCount(-1))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
