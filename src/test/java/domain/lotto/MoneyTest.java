package domain.lotto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


class MoneyTest {

    @Test
    void 더했을때_올바른_값을_반환한다() {
        // Given
        Money money1 = Money.from(1000);
        Money money2 = Money.from(2000);
        // When
        Money result = money1.add(money2);
        // Then
        assertEquals(Money.from(3000), result);
    }

    @Test
    void 곱했을때_올바른_값을_반환한다() {
        // Given
        Money money = Money.from(1000);
        // When
        Money result = money.multiply(2);
        // Then
        assertEquals(Money.from(2000), result);
    }

    @Test
    void 나누었을때_소숫점을_포함한_올바른_값을_반환한다() {
        // Given
        Money totalPrize = Money.from(2000);
        Money purchasePrice = Money.from(11000);
        // When
        double result = totalPrize.divide(purchasePrice);
        // Then
        assertEquals(0.18181818181818182, result);
    }

    @Test
    void 구매_가능한_개수를_반환한다() {
        // Given
        Money purchasePrice = Money.from(3000);
        Money lottoPrice = Money.from(1000);
        // When
        int result = purchasePrice.divideBy(lottoPrice);
        // Then
        assertEquals(3, result);
    }

    @Test
    void 나머지가_존재하면_나머지를_버리고_몫을_반환한다() {
        // Given
        Money purchasePrice = Money.from(13000);
        Money lottoPrice = Money.from(2000);
        // When
        int result = purchasePrice.divideBy(lottoPrice);
        // Then
        assertEquals(6, result);
    }

    @ParameterizedTest
    @CsvSource({"1000, 2000, true", "2000, 1000, false"})
    void 금액이_더_적으면_true를_더_많으면_false를_반환한다(int value1, int value2, boolean expected) {
        Money money1 = Money.from(value1);
        Money money2 = Money.from(value2);

        boolean result = money1.isLessThan(money2);

        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({"12000, 2000, true", "13000, 2000, false"})
    void 나머지가_없으면_true를_있으면_false를_반환한다(int value1, int value2, boolean expected) {
        Money money1 = Money.from(value1);
        Money money2 = Money.from(value2);
        boolean result = money1.isDivisibleBy(money2);

        assertEquals(expected, result);
    }

    @Test
    void 내부_값이_같으면_같다() {
        // Given
        Money money1 = Money.from(1000);
        Money money2 = Money.from(1000);
        // When & Then
        assertEquals(money1, money2);
    }

    @Test
    void 내부_값이_다르면_다르다() {
        // Given
        Money money1 = Money.from(1000);
        Money money2 = Money.from(2000);
        // When & Then
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
}
