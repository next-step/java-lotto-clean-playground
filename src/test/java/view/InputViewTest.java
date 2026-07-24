package view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InputViewTest {
    private final InputStream standardInput = System.in;

    @AfterEach
    void restoreInput() {
        System.setIn(standardInput);
    }

    @Test
    @DisplayName("구입 금액이 숫자가 아니면 예외가 발생한다")
    void throwExceptionWhenPurchaseAmountIsNotNumber() {
        System.setIn(new ByteArrayInputStream("abc".getBytes()));
        InputView inputView = new InputView();

        assertThatThrownBy(inputView::readPurchaseAmount)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구입 금액은 숫자여야 합니다.");
    }

    @Test
    @DisplayName("당첨 번호가 숫자 형식이 아니면 예외가 발생한다")
    void throwExceptionWhenWinningNumbersAreNotNumbers() {
        System.setIn(new ByteArrayInputStream("1 4 8 33 42 45".getBytes()));
        InputView inputView = new InputView();

        assertThatThrownBy(inputView::readWinningLotto)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("당첨 번호는 쉼표(,)로 구분한 숫자여야 합니다.");
    }
}
