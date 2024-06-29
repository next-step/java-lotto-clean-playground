package view;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.io.ByteArrayInputStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import util.Errors;

class InputViewTest {

    private final InputView inputView = new InputView();

    private void setSetIn(String input) {
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }

    @Nested
    @DisplayName("로또 구매금액을 입력받는 메소드")
    class TestOfGetParticipantNames {

        @ParameterizedTest(name = "사용자가 {0}을 입력하면 숫자가 아니기 때문에 예외가 반환된다.")
        @ValueSource(strings = {"r", "가", "."})
        @DisplayName("사용자가 숫자가 아닌 값을 입력하면 예외 반환")
        void invalidTest1(String userInput) {
            // given
            setSetIn(userInput);
            // when then
            assertThatThrownBy(inputView::getUserIntegerInput)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Errors.INPUT_IS_NOT_INTEGER);
        }
    }

}
