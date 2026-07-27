package view.input;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import view.InputView;

import java.io.ByteArrayInputStream;
import java.util.NoSuchElementException;

import static helper.TestHelperMethod.inputViewOf;
import static org.assertj.core.api.Assertions.assertThat;

public class ManualLottoInputViewTest {

    @Test
    @DisplayName("문자열을 입력한 경우 재입력 요청")
    void ifStringInput() {
        // given
        InputView inputView = inputViewOf("셋\n");

        // then
        Assertions.assertThrows(
                NoSuchElementException.class,
                // when
                inputView::manualCount
        );
    }

    @Test
    @DisplayName("음의 정수를 입력한 경우 재입력 요청")
    void ifNegativeInput() {
        // given
        InputView inputView = inputViewOf("-3\n");

        // then
        Assertions.assertThrows(
                NoSuchElementException.class,
                // when
                inputView::manualCount
        );
    }

    @Test
    @DisplayName("실수를 입력한 경우 재입력 요청")
    void ifFloatInput() {
        // given
        InputView inputView = inputViewOf("3.5\n");

        // then
        Assertions.assertThrows(
                NoSuchElementException.class,
                // when
                inputView::manualCount
        );
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
