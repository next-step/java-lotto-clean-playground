package domain.lotto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


class MoneyTest {

    @Test
    void 더했을때_올바른_값을_반환한다() {
        Money money1 = Money.from(1000);
        Money money2 = Money.from(2000);

        Money result = money1.add(money2);

        assertEquals(Money.from(3000), result);
    }

    @Test
    void 곱했을때_올바른_값을_반환한다() {
        Money money = Money.from(1000);

        Money result = money.multiply(2);

        assertEquals(Money.from(2000), result);
    }

    @Test
    void 나누었을때_소숫점을_포함한_올바른_값을_반환한다() {
        Money totalPrize = Money.from(2000);
        Money purchasePrice = Money.from(11000);

        double result = totalPrize.divide(purchasePrice);

        assertEquals(0.18181818181818182, result);
    }

    @Test
    void 구매_가능한_개수를_반환한다() {
        Money purchasePrice = Money.from(3000);
        Money lottoPrice = Money.from(1000);

        long result = purchasePrice.divideBy(lottoPrice);

        assertEquals(3, result);
    }

    @Test
    void 나머지가_존재하면_나머지를_버리고_몫을_반환한다() {
        Money purchasePrice = Money.from(13000);
        Money lottoPrice = Money.from(2000);

        long result = purchasePrice.divideBy(lottoPrice);

        assertEquals(6, result);
    }

    @Test
    void 내부_값이_같으면_같다() {
        Money money1 = Money.from(1000);
        Money money2 = Money.from(1000);

        assertEquals(money1, money2);
    }

    @Test
    void 내부_값이_다르면_다르다() {
        Money money1 = Money.from(1000);
        Money money2 = Money.from(2000);

        assertNotEquals(money1, money2);
    }

    @Test
    void 값이_음수이면_예외가_발생한다() {
        assertThatThrownBy(() -> Money.from(-1000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("금액은 0 이상이어야 합니다.");
    }

    @Test
    void 값이_1000원_단위가_아니면_예외가_발생한다() {
        assertThatThrownBy(() -> Money.from(1500))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("금액은 1000원 단위여야 합니다.");
    }

    @Test
    void 구매_가능한_로또_개수를_반환한다() {
        Money purchasePrice = Money.from(5000);

        int result = purchasePrice.countPurchasable(Money.from(1000));

        assertEquals(5, result);
    }

    @Test
    void 금액이_로또_가격보다_적으면_예외가_발생한다() {
        assertThatThrownBy(() -> Money.from(0).countPurchasable(Money.from(1000)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("금액은 1000원 이상이어야 합니다.");
    }
}
