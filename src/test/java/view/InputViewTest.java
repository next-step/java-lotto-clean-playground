package view;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import util.Errors;

class InputViewTest {

    private final InputView inputView = new InputView();

    private void setSetIn(String input) {
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }

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

    @Nested
    @DisplayName("로또 입력 테스트")
    class inputWinningNumbersTest {

        private static Stream<Arguments> methodSourceOfWinningNumbers() {
            return Stream.of(
                Arguments.arguments("1,2,3,4,5,6", List.of(1, 2, 3, 4, 5, 6)),
                Arguments.arguments("10, 20, 23, 25, 30, 32", List.of(10, 20, 23, 25, 30, 32))
            );
        }

        @ParameterizedTest(name = "사용자가 로또 숫자로 {0}을 입력하면 쉼표를 구분자로 해당 6개의 숫자가 담긴 리스트가 생성된다.")
        @MethodSource("methodSourceOfWinningNumbers")
        @DisplayName("쉼표를 기준으로 숫자를 분리해 리스트를 만들어낸다")
        void getWinningNumbersTest(String userInput, List<Integer> expectedWinningNumbers) {
            // given
            setSetIn(userInput);
            // when
            final List<Integer> winningNumbers = inputView.getLottoNumber();
            // then
            assertThat(winningNumbers)
                .isEqualTo(expectedWinningNumbers);
        }

        @ParameterizedTest(name = "사용자가 로또 숫자로 {0}을 입력하면 숫자가 아니기 때문에 에러가 발생한다.")
        @ValueSource(strings = {"1,2,ㅠㅠ,3,4,5", "a,b,c,d,e,f", ".,?,*,a,ㅁ,A"})
        @DisplayName("시도 횟수로 숫자가 아닌 다른 것이 입력되면 예외를 반환한다.")
        void getWinningNumbersExceptionTest(String userInput) {
            // given
            setSetIn(userInput);
            // when then
            assertThatThrownBy(inputView::getLottoNumber)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Errors.INPUT_NUMBER_IS_NOT_INTEGER);
        }
    }
}
