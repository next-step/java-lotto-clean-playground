package domain.money;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @Test
    @DisplayName("유효한 숫자 문자열로 생성할 수 있다.")
    void shouldCreateWithValidGetAmount() {
        // given
        Money money = new Money("1000");

        // when & then
        assertThat(money.getAmount())
                .isEqualTo(new BigDecimal("1000"));
    }

    @Test
    @DisplayName("음수이면 예외가 발생한다.")
    void shouldThrowException_whenNegativeGetAmount() {
        // given & when & then
        assertThatThrownBy(() -> new Money("-1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("금액은 음수일 수 없습니다.");
    }

    @Test
    @DisplayName("숫자가 아닌 값이면 예외가 발생한다.")
    void shouldThrowException_whenNotNumber() {
        // given & when & then
        assertThatThrownBy(() -> new Money("abc"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효한 숫자 형식이 아닙니다.");
    }

    @Test
    @DisplayName("두 Money 객체를 더할 수 있다.")
    void shouldAddMoney() {
        // given
        Money m1 = new Money("1000");
        Money m2 = new Money("500");

        // when
        Money result = m1.add(m2);

        // then
        assertThat(result.getAmount())
                .isEqualTo("1500");
    }

    @Test
    @DisplayName("정수 곱셈이 가능하다.")
    void shouldMultiplyMoney() {
        // given
        Money money = new Money("1000");

        // when
        Money result = money.multiply(3);

        // then
        assertThat(result.getAmount())
                .isEqualTo("3000");
    }

    @Test
    @DisplayName("0으로 나누면 예외가 발생한다")
    void shouldThrowException_whenDivideByZero() {
        // given
        Money dividend = new Money("1000");

        // when & then
        assertThatThrownBy(() -> dividend.divideBy(new Money("0")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("분모는 0보다 커야 합니다.");
    }

    @Test
    @DisplayName("같은 금액이면 equals는 true를 반환한다")
    void shouldReturnTrue_whenEqualsSameAmount() {
        // given
        Money m1 = new Money("1000");
        Money m2 = new Money("1000.00");

        // when & then
        assertThat(m1).isEqualTo(m2);
    }
}
