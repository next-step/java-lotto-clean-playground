package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


class MoneyTest {

    @Test
    void 더했을때_올바른_값을_반환한다() {
        // Given
        Money money1 = new Money(1000);
        Money money2 = new Money(2000);
        // When
        Money result = money1.add(money2);
        // Then
        assertEquals(new Money(3000), result);
    }

    @Test
    void 곱했을때_올바른_값을_반환한다() {
        // Given
        Money money = new Money(1000);
        // When
        Money result = money.multiply(2);
        // Then
        assertEquals(new Money(2000), result);
    }

    @Test
    void 나누었을때_소숫점을_포함한_올바른_값을_반환한다() {
        // Given
        Money totalPrize = new Money(2000);
        Money purchasePrice = new Money(11000);
        // When
        double result = totalPrize.divide(purchasePrice);
        // Then
        assertEquals(0.18181818181818182, result);
    }

    @Test
    void 구매_가능한_개수를_반환한다() {
        // Given
        Money purchasePrice = new Money(3000);
        Money lottoPrice = new Money(1000);
        // When
        int result = purchasePrice.divideBy(lottoPrice);
        // Then
        assertEquals(3, result);
    }

    @Test
    void 나머지가_존재하면_나머지를_버리고_몫을_반환한다() {
        // Given
        Money purchasePrice = new Money(13000);
        Money lottoPrice = new Money(2000);
        // When
        int result = purchasePrice.divideBy(lottoPrice);
        // Then
        assertEquals(6, result);
    }

    @Test
    void 금액이_더_작으면_true를_반환한다() {
        // Given
        Money money1 = new Money(1000);
        Money money2 = new Money(2000);
        // When
        boolean result = money1.isLessThan(money2);
        // Then
        assertTrue(result);
    }

    @Test
    void 금액이_더_작지_않으면_false를_반환한다() {
        // Given
        Money money1 = new Money(2000);
        Money money2 = new Money(1000);
        // When
        boolean result = money1.isLessThan(money2);
        // Then
        assertFalse(result);
    }

    @Test
    void 나누어_떨어지면_true를_반환한다() {
        // Given
        Money money1 = new Money(12000);
        Money money2 = new Money(2000);
        // When
        boolean result = money1.isDivisibleBy(money2);
        // Then
        assertTrue(result);
    }

    @Test
    void 나누어_떨어지지_않으면_false를_반환한다() {
        // Given
        Money money1 = new Money(13000);
        Money money2 = new Money(2000);
        // When
        boolean result = money1.isDivisibleBy(money2);
        // Then
        assertFalse(result);
    }

    @Test
    void 내부_값이_같으면_같다() {
        // Given
        Money money1 = new Money(1000);
        Money money2 = new Money(1000);
        // When & Then
        assertEquals(money1, money2);
    }

    @Test
    void 내부_값이_다르면_다르다() {
        // Given
        Money money1 = new Money(1000);
        Money money2 = new Money(2000);
        // When & Then
        assertNotEquals(money1, money2);
    }
}
