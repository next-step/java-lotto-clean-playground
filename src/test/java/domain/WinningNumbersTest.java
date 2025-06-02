package domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningNumbersTest {

    @Test
    @DisplayName("보너스 번호가 당첨 번호에 포함되면 예외가 발생한다")
    void throw_exception_when_bonusNumber_in_winningNumbers() {
        // Given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        int bonus = 6;

        // When & Then
        assertThatThrownBy(() -> new WinningNumbers(numbers, bonus))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("보너스 번호가 당첨 번호와 겹치지 않으면 예외가 발생하지 않는다")
    void no_exception_when_bonusNumber_not_in_winningNumbers() {
        // Given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        int bonus = 7;

        // When & Then
        assertThatCode(() -> new WinningNumbers(numbers, bonus))
            .doesNotThrowAnyException();
    }
}
