package model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PurchaseAmountTest {

    @Test
    void 구매액에_맞추어_로또를_발급해야_한다() {
        int purchasePrice = 14000;
        PurchaseAmount purchaseAmount = PurchaseAmount.create(purchasePrice);
        assertThat(purchaseAmount.getAmount()).isEqualTo(14);
    }

    @Test
    void 구매액이_로또_1장_가격보다_작으면_예외가_발생해야_한다() {
        int purchasePrice = 900;
        assertThatThrownBy(() -> PurchaseAmount.create(purchasePrice))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("복권 구매의 최소 금액은 1000원 입니다!");
    }

}
