package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningNumbersTest {
    @Test
    @DisplayName("동일한 번호로 생성된 WinningNumbers 객체가 동등한지 확인한다")
    void check_is_SameInstance_When_ConstructNewWinningNumbers() {
        // Given
        WinningNumbers first = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
        WinningNumbers second = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));

        // Then
        assertThat(first).isEqualTo(second);
        assertThat(first.hashCode()).isEqualTo(second.hashCode());
    }

    @Test
    @DisplayName("보너스 번호가 당첨 번호에 포함되면 예외가 발생한다")
    void throw_exception_when_bonusNumber_in_winningNumbers() {
        // Given
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = new BonusNumber(6);

        // When & Then
        assertThatThrownBy(() -> winningNumbers.validateBonusNumber(bonusNumber))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("보너스 번호가 당첨 번호와 겹치지 않으면 예외가 발생하지 않는다")
    void no_exception_when_bonusNumber_not_in_winningNumbers() {
        // Given
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = new BonusNumber(7);

        // When & Then
        winningNumbers.validateBonusNumber(bonusNumber);
    }
}
