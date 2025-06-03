package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BonusNumberTest {

    private LottoNumber winningNumbers() {
        return new LottoNumber(List.of(1, 2, 3, 4, 5, 6));
    }

    @Test
    @DisplayName("보너스 번호가 1~45 사이고, 당첨 번호와 중복되지 않으면 객체 생성에 성공한다")
    void create_bonus_number_successfully() {
        // Given
        int number = 23;
        LottoNumber winningNumbers = winningNumbers();

        // When
        BonusNumber bonusNumber = new BonusNumber(number, winningNumbers);

        // Then
        assertThat(bonusNumber.getNumber()).isEqualTo(23);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, 46, 100})
    @DisplayName("보너스 번호가 1~45 범위를 벗어나면 예외를 던진다")
    void throw_exception_when_bonus_number_is_out_of_range(int invalidNumber) {
        // Given
        LottoNumber winningNumbers = winningNumbers();

        // When & Then
        assertThatThrownBy(() -> new BonusNumber(invalidNumber, winningNumbers))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("보너스 번호는 1~45 사이여야 합니다.");
    }
}
