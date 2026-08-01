package view.input;

import domain.lotto.Lotto;
import domain.lotto.WinningLotto;
import domain.lotto.wrap.LottoNumber;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import view.InputView;

import static helper.TestHelperMethod.inputViewOf;
import static helper.TestHelperMethod.toLottoNumbers;
import static org.assertj.core.api.Assertions.assertThat;

public class BonusBallInputViewTest {

    @Test
    @DisplayName("문자열을 입력하고 정상적으로 입력한 경우 성공한다.")
    void ifStringInput() {
        // given
        InputView inputView = inputViewOf("칠\n7\n");
        int expected = 7;

        // when
        LottoNumber actual = inputView.bonusNumber();

        // then
        assertThat(actual.getValue()).isEqualTo(expected);
    }

    @Test
    @DisplayName("실수를 입력하고 정상적으로 입력한 경우 성공한다.")
    void ifFloatInput() {
        // given
        InputView inputView = inputViewOf("7.5\n7\n");
        int expected = 7;

        // when
        LottoNumber actual = inputView.bonusNumber();

        // then
        assertThat(actual.getValue()).isEqualTo(expected);
    }

    @Test
    @DisplayName("음의 정수를 입력하고 정상적으로 입력한 경우 성공한다.")
    void ifNegativeInput() {
        // given
        InputView inputView = inputViewOf("-7\n7\n");
        int expected = 7;

         // when
        LottoNumber actual = inputView.bonusNumber();

        // then
        assertThat(actual.getValue()).isEqualTo(expected);
    }

    @Test
    @DisplayName("로또 범위 외의 숫자를 입력한 경우")
    void inputOverRangeNumberInBonusBallInitialize() {
        // given
        InputView underInputView = inputViewOf("0\n1\n");
        InputView overInputView = inputViewOf("46\n1\n");
        int expected = 1;

        // when
        LottoNumber under = underInputView.bonusNumber();
        LottoNumber over = overInputView.bonusNumber();

        // then
        assertThat(under.getValue()).isEqualTo(expected);
        assertThat(over.getValue()).isEqualTo(expected);
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
