package view.input;

import domain.lotto.Lotto;
import domain.lotto.wrap.LottoNumber;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import view.InputView;

import java.util.List;
import java.util.NoSuchElementException;

import static helper.TestHelperMethod.inputViewOf;
import static helper.TestHelperMethod.toLottoNumbers;
import static org.assertj.core.api.Assertions.assertThat;

public class LastWeekWinningNumberInputViewTest {

    @Test
    @DisplayName("5개만 입력하고 정상적인 값을 입력한 경우")
    void ifFiveNumbersInput() {
        // given
        InputView inputView = inputViewOf("1,2,3,4,5\n4, 5, 6, 7, 8, 9");
        List<LottoNumber> expected = toLottoNumbers(4, 5, 6, 7, 8, 9);

        // when
        Lotto lotto = inputView.lastWeekWinningNumbers();

        // then
        assertThat(lotto.getNumbers()).isEqualTo(expected);
    }

    @Test
    @DisplayName("1 ~ 45의 범위 내의 숫자가 아닌 경우")
    void isNotValidNumber() {
        // given
        InputView inputView = inputViewOf("1,2,3,4,5, 50\n");

        // then
        Assertions.assertThrows(
                NoSuchElementException.class,
                // when
                inputView::lastWeekWinningNumbers
        );
    }

    @Test
    @DisplayName("7개를 입력하고 정상적인 값을 입력한 경우")
    void ifSevenNumbersInput() {
        // given
        InputView inputView = inputViewOf("1,2,3,4,5,6,7\n1,2,3,4,5,6\n");
        List<LottoNumber> expected = toLottoNumbers(1, 2, 3, 4, 5, 6);

        // when
        Lotto lotto = inputView.lastWeekWinningNumbers();

        // then
        assertThat(lotto.getNumbers()).isEqualTo(expected);
    }

    @Test
    @DisplayName("중복된 번호를 입력하고 정상적인 값을 입력한 경우")
    void ifDuplicateNumbersInput() {
        // given
        InputView inputView = inputViewOf("1,1,2,3,4,5\n1,2,3,4,5,6\n");
        List<LottoNumber> expected = toLottoNumbers(1, 2, 3, 4, 5, 6);

        // when
        Lotto lotto = inputView.lastWeekWinningNumbers();

        // then
        assertThat(lotto.getNumbers()).isEqualTo(expected);
    }

    @Test
    @DisplayName("콤마 없이 입력하고 정상적인 값을 입력한 경우")
    void ifNoCommaInput() {
        // given
        InputView inputView = inputViewOf("123456\n4, 5, 6, 7, 8, 9");
        List<LottoNumber> expected = toLottoNumbers(4, 5, 6, 7, 8, 9);

        // when
        inputView.lastWeekWinningNumbers();

        // then
        assertThat(expected).isEqualTo(expected);
    }

    @Test
    @DisplayName("입력한 당첨 번호가 그대로 저장된다")
    void savedExactly() {
        // given
        InputView inputView = inputViewOf("1,2,3,4,5,6\n");

        // when
        Lotto lotto = inputView.lastWeekWinningNumbers();

        // then
        assertThat(toLottoNumbers(1, 2, 3, 4, 5, 6)).allMatch(lotto::contains);
        assertThat(lotto.toString()).isEqualTo("[1, 2, 3, 4, 5, 6]");
    }
}
