package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PrizeTest {

    @Test
    @DisplayName("상금 객체를 생성하면 amount 값을 보존한다")
    void create_prize_successfully() {
        Prize prize = Prize.from(5000L);
        assertThat(prize.getAmount()).isEqualTo(5000L);
    }

    @Test
    @DisplayName("음수 상금을 생성하려고 하면 예외를 던진다")
    void throw_exception_when_negative_amount() {
        assertThatThrownBy(() -> Prize.from(-1000L))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("상금은 음수일 수 없습니다.");
    }

    @Test
    @DisplayName("상금에 개수를 곱한 결과를 반환한다")
    void multiply_prize() {
        Prize prize = Prize.from(1000L);
        Prize multiplied = prize.multiply(3);

        assertThat(multiplied.getAmount()).isEqualTo(3000L);
    }

    @Test
    @DisplayName("음수 개수를 곱하면 예외를 던진다")
    void throw_exception_when_negative_count_in_multiply() {
        Prize prize = Prize.from(1000L);

        assertThatThrownBy(() -> prize.multiply(-1))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("개수는 음수일 수 없습니다.");
    }
}
