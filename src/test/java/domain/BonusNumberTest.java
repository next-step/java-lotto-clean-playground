package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BonusNumberTest {

  @Test
  @DisplayName("보너스 번호는 로또 번호와 중복되지 않아야 한다")
  void bonusNumberShouldNotDuplicateWithWinningNumbers() {
    // Given
    WinningNumbers winningNumbers = new WinningNumbers(
        Arrays.asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
            new LottoNumber(5), new LottoNumber(6)));
    LottoNumber bonusNumber = new LottoNumber(5);

    // When & Then
    assertThatThrownBy(() -> new BonusNumber(bonusNumber, winningNumbers))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("보너스 번호는 로또 번호와 중복될 수 없습니다.");
  }
}
