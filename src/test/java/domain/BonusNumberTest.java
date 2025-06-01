package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BonusNumberTest {

    @Test
    @DisplayName("보너스 번호가 1~45 사이면 객체 생성에 성공한다")
    void create_bonus_number_successfully() {
        // Given
        int number = 23;

        // When
        BonusNumber bonusNumber = new BonusNumber(number);

        // Then
        assertThat(bonusNumber.getNumber()).isEqualTo(23);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, 46, 100})
    @DisplayName("보너스 번호가 1~45 범위를 벗어나면 예외를 던진다")
    void throw_exception_when_bonus_number_is_out_of_range(int invalidNumber) {
        // When & Then
        assertThatThrownBy(() -> new BonusNumber(invalidNumber))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("보너스 번호는 1~45 사이여야 합니다.");
    }

    @Test
    @DisplayName("같은 숫자의 보너스 번호는 동등하게 판단된다")
    void bonus_number_equality_test() {
        // Given
        BonusNumber a = new BonusNumber(7);
        BonusNumber b = new BonusNumber(7);

        // Then
        assertThat(a).isEqualTo(b);
        assertThat(a.hashCode()).isEqualTo(b.hashCode());
    }
}
