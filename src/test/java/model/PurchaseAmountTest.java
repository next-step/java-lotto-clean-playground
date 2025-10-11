package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PurchaseAmountTest {

    @Test
    void 올바른_금액이면_로또개수계산() {
        // given
        PurchaseAmount purchaseAmount = new PurchaseAmount(2000);

        // when
        int count = purchaseAmount.calculateLottoCount();

        // then
        assertEquals(2, count);
    }

    @Test
    void _0원_미만이면_예외가_발생한다() {
        // given
        int invalidMoney = -2000;

        // when & then
        assertThrows(IllegalArgumentException.class, () -> new PurchaseAmount(invalidMoney));
    }

    @Test
    void 같은금액이면_동일객체() {
        // given
        PurchaseAmount purchaseAmount1 = new PurchaseAmount(6000);
        PurchaseAmount purchaseAmount2 = new PurchaseAmount(6000);

        // when & then
        assertEquals(purchaseAmount1, purchaseAmount2);
        assertEquals(purchaseAmount1.hashCode(), purchaseAmount2.hashCode());
    }
}
