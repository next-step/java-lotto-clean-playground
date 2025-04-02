package model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PurchasePriceTest {

    @Test
    public void 구매_비용이_로또_가격보다_낮으면_예외가_발생해야_한다() throws Exception {
        int purchasePrice = 900;
        assertThatThrownBy(() -> PurchasePrice.from(purchasePrice))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("복권 구매의 최소 금액은 1000원 입니다!");
    }
}
