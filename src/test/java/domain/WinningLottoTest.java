package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class WinningLottoTest {
    @Test
    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외가 발생한다")
    void throwsExceptionWhenBonusNumberIsDuplicatedWithWinningNumbers() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        LottoNumber bonusNumber = new LottoNumber(3);
        Lotto winningNumbers = new Lotto(numbers);

        assertThrows(
                IllegalArgumentException.class,
                () -> new WinningLotto(winningNumbers, bonusNumber)
        );
    }
}
