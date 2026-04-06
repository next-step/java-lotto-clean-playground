package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ManualLottoCountTest {

    @Test
    void 수동_구매_수가_0_미만이면_예외가_발생한다() {
        PurchaseAmount purchaseAmount = new PurchaseAmount(5000);

        assertThatThrownBy(() -> new ManualLottoCount(-1, purchaseAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수동 구매 수는 0 이상이어야 합니다.");
    }

    @Test
    void 수동_구매_수가_총_구매_가능_수를_초과하면_예외가_발생한다() {
        PurchaseAmount purchaseAmount = new PurchaseAmount(4000);

        assertThatThrownBy(() -> new ManualLottoCount(5, purchaseAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수동 로또 수는 총 로또 수를 넘을 수 없습니다.");
    }
}
