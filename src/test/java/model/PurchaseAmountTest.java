package model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PurchaseAmountTest {

    @Test
    void 수동로또를_구매하고_남은_돈은_자동로또를_구매해야_한다() {
        long purchasePrice = 14000;
        int manualPurchaseAmount = 4;
        PurchaseAmount purchaseAmount = PurchaseAmount.of(purchasePrice, manualPurchaseAmount);
        assertThat(purchaseAmount.getAutoPurchaseAmount()).isEqualTo(10);
    }

    @Test
    void 구매금액보다_구매하려는_수동로또_개수가_많으면_예외가_발생해야_한다() {
        long purchasePrice = 1000;
        int manualPurchaseAmount = 4;
        assertThatThrownBy(() -> PurchaseAmount.of(purchasePrice, manualPurchaseAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("요청한 수동 구매 개수가 현재 로또 구입 예산으로는 구매할 수 없습니다!");
    }

    @Test
    void 수동로또의_구매_매수가_음수이면_예외가_발생해야_한다() {
        long purchasePrice = 14000;
        int manualPurchaseAmount = -1;
        assertThatThrownBy(() -> PurchaseAmount.of(purchasePrice, manualPurchaseAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수동 복권 구매 매수은 음수이면 안됩니다!");
    }

    @Test
    void 계산한_구매_로또_개수가_정수_자료형을_초과하면_예외가_발생해야_한다() {
        long purchasePrice = Long.MAX_VALUE;
        int manualPurchaseAmount = 4;
        assertThatThrownBy(() -> PurchaseAmount.of(purchasePrice, manualPurchaseAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 구매 매수는 정수 자료형보다 많이 구매할 수 없습니다!");
    }
}
