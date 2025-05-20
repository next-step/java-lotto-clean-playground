package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PrizeTest {

    @Test
    @DisplayName("Prize가 정상적으로 생성되고 금액은 지정한 소수점 자리로 반올림된다.")
    void shouldCreatePrizeAndRoundAmountToDecimalPlaces_whenValidAmount() {
        // given
        Prize prize = Prize.from(123456);

        // when & then
        assertThat(prize.getAmount())
                .isEqualTo("123456.00");
    }

    @Test
    @DisplayName("음수 금액으로 생성 시 예외가 발생한다.")
    void shouldThrowException_whenNegativeValue() {
        // given & when & then
        assertThatThrownBy(() -> Prize.from(-100))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("금액은 음수일 수 없습니다.");
    }

    @Test
    @DisplayName("덧셈 연산이 정상적으로 수행된다.")
    void shouldReturnSum_whenAddTwoPrizes() {
        // given
        Prize p1 = Prize.from(1000);
        Prize p2 = Prize.from(2000);

        // when
        Prize result = p1.add(p2);

        // then
        assertThat(result.getAmount())
                .isEqualTo("3000.00");
    }

    @Test
    @DisplayName("곱셈 연산이 정상적으로 수행된다.")
    void shouldReturnProduct_whenMultiplyPrizeByCount() {
        // given
        Prize prize = Prize.from(1500);

        // when
        Prize result = prize.multiply(3);

        // then
        assertThat(result.getAmount())
                .isEqualTo("4500.00");
    }

    @Test
    @DisplayName("나눗셈 연산이 정상적으로 수행된다.")
    void shouldReturnResult_whenDividePrizeByValidPrize() {
        // given
        Prize p1 = Prize.from(1000);
        Prize p2 = Prize.from(4);

        // when
        Prize result = p1.divideBy(p2);

        // then
        assertThat(result.getAmount()).isEqualByComparingTo("250.00");
    }

    @Test
    @DisplayName("분모가 0 이하일 때 나눗셈 시 예외가 발생한다.")
    void shouldThrowException_whenDivideByZero() {
        // given
        Prize prize = Prize.from(1000);
        Prize invalid = Prize.from(0);

        // when & then
        assertThatThrownBy(() -> prize.divideBy(invalid))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("분모는 0보다 커야 합니다.");
    }
}
