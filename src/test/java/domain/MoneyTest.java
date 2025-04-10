package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {

    @Test
    @DisplayName("구매 금액이 음수일 경우 예외가 발생해야 한다.")
    void testNegativeMoneyException() {
        assertThatThrownBy(() -> Money.from(-1000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("구매 금액으로 음수는 입력이 불가능하합니다.");
    }

    @Test
    @DisplayName("3000에 2000을 더하면 5000이 되어야 한다")
    void testMoneyAddition() {
        Money money1 = Money.from(3000);
        Money money2 = money1.add(2000);
        long expected = 5000;

        long actual = money2.getPrice();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("5000에서 2000을 빼면 3000이 되어야 한다")
    void testMoneySubtraction() {
        Money money1 = Money.from(5000);
        Money money2 = money1.sub(2000);
        long expected = 3000;

        long actual = money2.getPrice();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("5000에서 2500을 나누면 2가 되어야 한다")
    void testMoneyDivide() {
        Money money1 = Money.from(5000);
        long expected = 2;

        long actual = money1.divideBy(2500);

        assertThat(actual).isEqualTo(expected);
    }
}
