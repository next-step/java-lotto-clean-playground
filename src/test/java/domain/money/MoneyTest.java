package domain.money;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @Test
    @DisplayName("유효한 숫자 문자열로 생성할 수 있다.")
    void shouldCreateWithValidGetAmount() {
        // given
        Money money = Money.from("1000");

        // when & then
        assertThat(money.amount())
                .isEqualTo(new BigDecimal("1000"));
    }

    @Test
    @DisplayName("음수이면 예외가 발생한다.")
    void shouldThrowException_whenNegativeGetAmount() {
        // given & when & then
        assertThatThrownBy(() -> Money.from("-1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("금액은 음수일 수 없습니다.");
    }

    @Test
    @DisplayName("숫자가 아닌 값이면 예외가 발생한다.")
    void shouldThrowException_whenNotNumber() {
        // given & when & then
        assertThatThrownBy(() -> Money.from("abc"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효한 숫자 형식이 아닙니다.");
    }

    @Test
    @DisplayName("두 Money 객체를 더할 수 있다.")
    void shouldAddMoney() {
        // given
        Money m1 = Money.from("1000");
        Money m2 = Money.from("500");

        // when
        Money result = m1.add(m2);

        // then
        assertThat(result.amount())
                .isEqualTo("1500");
    }

    @Test
    @DisplayName("정수 곱셈이 가능하다.")
    void shouldMultiplyMoney() {
        // given
        Money money = Money.from("1000");

        // when
        Money result = money.multiply(3);

        // then
        assertThat(result.amount())
                .isEqualTo("3000");
    }

    @Test
    @DisplayName("0으로 나누면 0을 반환한다.")
    void shouldThrowException_whenDivideZero() {
        // given
        Money dividend = Money.from("1000");

        // when
        Money result = dividend.divide(Money.zero());

        // then
        assertThat(result.amount())
                .isEqualTo("0");
    }

    @Test
    @DisplayName("나눗셈 결과는 DIVIDE SCALE 자릿수까지 반올림된다.")
    void shouldResultRoundingHalf_whenDivide() {
        // given
        Money dividend = Money.from("100");
        Money divisor = Money.from("3");

        // when
        Money result = dividend.divide(divisor);

        // then
        BigDecimal expected = new BigDecimal("33.33").setScale(Money.DIVIDE_SCALE, RoundingMode.HALF_UP);
        assertThat(result.amount())
                .isEqualByComparingTo(expected);
    }

    @Test
    @DisplayName("금액 포맷이 달라도 나눗셈 연산이 정상적으로 수행된다")
    void shouldReturnDivideResult_withDifferentFormats() {
        // given
        Money m1 = Money.from("1000.00");
        Money m2 = Money.from("4");

        // when
        Money result = m1.divide(m2);

        // then
        assertThat(result.amount()).isEqualTo("250.00");
    }
}
