package inputView;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PriceTest {

    @Test
    void 올바른_금액이면_로또개수계산() {
        // given
        Price price = new Price(2000);

        // when
        int count = price.howManyLottos();

        // then
        assertEquals(2, count);
    }

    @Test
    void _0원_미만이면_예외가_발생한다() {
        // given
        int invalidMoney = -2000;

        // when & then
        assertThrows(IllegalArgumentException.class, () -> new Price(invalidMoney));
    }

    @Test
    void 같은금액이면_동일객체() {
        // given
        Price price1 = new Price(6000);
        Price price2 = new Price(6000);

        // when & then
        assertEquals(price1, price2);
        assertEquals(price1.hashCode(), price2.hashCode());
    }
}
