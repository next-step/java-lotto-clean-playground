package view.input;

import domain.lotto.Lotto;
import domain.lotto.WinningLotto;
import domain.lotto.wrap.LottoNumber;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import view.InputView;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

import static helper.TestHelperMethod.inputViewOf;
import static helper.TestHelperMethod.toLottoNumbers;
import static org.assertj.core.api.Assertions.assertThat;

public class BonusBallInputViewTest {

    @Test
    @DisplayName("문자열을 입력한 경우 예외가 발생한다")
    void ifStringInput() {
        // given
        InputView inputView = inputViewOf("칠\n");

        // then
        Assertions.assertThrows(
                InputMismatchException.class,
                // when
                inputView::bonusNumber
        );
    }

    @Test
    @DisplayName("실수를 입력한 경우 예외 발생")
    void ifFloatInput() {
        // given
        InputView inputView = inputViewOf("7.5\n");

        // then
        Assertions.assertThrows(
                InputMismatchException.class,
                // when
                inputView::bonusNumber
        );
    }

    @Test
    @DisplayName("음의 정수를 입력한 경우 예외 발생")
    void ifNegativeInput() {
        // given
        InputView inputView = inputViewOf("-7\n");

        // then
        Assertions.assertThrows(
                IllegalArgumentException.class,
                // when
                inputView::bonusNumber
        );
    }

    @Test
    @DisplayName("보너스 볼이 정상적으로 처리된 경우")
    void validBonusNumber() {
        // given
        InputView inputView = inputViewOf("7\n");

        // when
        LottoNumber bonus = inputView.bonusNumber();

        // then
        assertThat(bonus).isEqualTo(new LottoNumber(7));
    }

    @Test
    @DisplayName("보너스 볼이 당첨 번호와 중복되면 예외가 발생한다")
    void ifBonusDuplicatesWinningNumbers() {
        // given
        Lotto winningNumbers = new Lotto(toLottoNumbers(1, 2, 3, 4, 5, 6));

        // then
        Assertions.assertThrows(
                IllegalArgumentException.class,
                // when
                () -> new WinningLotto(winningNumbers, new LottoNumber(6))
        );
    }
}
