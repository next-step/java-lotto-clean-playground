package view.input;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import view.InputView;

import static helper.TestHelperMethod.inputViewOf;
import static org.assertj.core.api.Assertions.assertThat;

public class ManualLottoInputViewTest {

    @Test
    @DisplayName("문자열을 입력하고 정상적인 값을 입력한 경우")
    void ifStringInput() {
        // given
        InputView inputView = inputViewOf("셋\n3\n");
        int expected = 3;

        // when
        int actual = inputView.manualCount();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("음의 정수를 입력하고 정상적인 값을 입력한 경우")
    void ifNegativeInput() {
        // given
        InputView inputView = inputViewOf("-3\n3\n");
        int expected = 3;

        // when
        int actual = inputView.manualCount();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("실수를 입력하고 정상적인 값을 입력한 경우")
    void ifFloatInput() {
        // given
        InputView inputView = inputViewOf("3.5\n3\n");
        int expected = 3;

        // when
        int actual = inputView.manualCount();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("수동 구매 수가 정상적으로 처리된 경우")
    void validManualCount() {
        // given
        InputView inputView = inputViewOf("3\n");

        // when
        int count = inputView.manualCount();

        // then
        assertThat(count).isEqualTo(3);
    }

    @Test
    @DisplayName("0을 입력하면 전부 자동으로 구매한다")
    void ifZeroInput() {
        // given
        InputView inputView = inputViewOf("0\n");

        // when
        int count = inputView.manualCount();

        // then
        assertThat(count).isEqualTo(0);
    }
}
