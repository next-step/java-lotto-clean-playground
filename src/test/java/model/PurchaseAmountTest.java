package model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PurchaseAmountTest {

    @Test
    void 수동로또를_구매하고_남은_돈은_자동로또를_구매해야_한다() {
        int purchasePrice = 14000;
        int manualPurchaseAmount = 4;
        PurchaseAmount purchaseAmount = PurchaseAmount.of(purchasePrice, manualPurchaseAmount);
        assertThat(purchaseAmount.getAutoPurchaseAmount()).isEqualTo(10);
    }

    @Test
    public void 구매금액보다_구매하려는_수동로또_개수가_많으면_예외가_발생해야_한다() {
        int purchasePrice = 1000;
        int manualPurchaseAmount = 4;
        assertThatThrownBy(() -> PurchaseAmount.of(purchasePrice, manualPurchaseAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("요청한 수동 구매 개수가 현재 로또 구입 예산으로는 구매할 수 없습니다!");
    }

    @Test
    void 구매액이_로또_1장_가격보다_작으면_예외가_발생해야_한다() {
        int purchasePrice = 900;
        int manualPurchaseAmount = 0;
        assertThatThrownBy(() -> PurchaseAmount.of(purchasePrice, manualPurchaseAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("복권 구매의 최소 금액은 1000원 입니다!");
    }

}
