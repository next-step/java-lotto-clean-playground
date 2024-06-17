package model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MoneyTest {

    @DisplayName("구매금액이 음수이면 예외를 발생한다.")
    @Test
    void money_not_under_zero() {
        // given
        int value = -1;
        // when
        // then
        assertThatThrownBy(() -> new Money(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구입금액은 음수가 될 수 없습니다.");
    }

    @DisplayName("구매금액이 1000단위가 아니면 예외를 발생한다.")
    @ValueSource(ints = {1100, 1010, 1001})
    @ParameterizedTest
    void moeny_divide_with_1000(int value) {
        // given
        // when
        // then
        assertThatThrownBy(() -> new Money(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구입금액은 1000단위이어야 합니다.");
    }
}
