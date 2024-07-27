package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import util.Errors;

class LottoTest {

    @Nested
    @DisplayName("로또 생성 테스트")
    class createLotto {

        private static Stream<Arguments> methodSourceOfCreateLotto() {
            return Stream.of(
                Arguments.arguments(Arrays.asList(1, 4, 2, 30, 31, 32, 33)),
                Arguments.arguments(Arrays.asList(10, 11, 12, 13, 14, 15, 16, 17, 18))
            );
        }

        @ParameterizedTest(name = "{0}은 6 이상의 사이즈를 가지므로 예외가 발생한다.")
        @MethodSource("methodSourceOfCreateLotto")
        @DisplayName("생성되는 로또의 사이즈는 6이다.")
        void createLottoSizeTest(List<Integer> inputNumbers) {
            // given
            // when
            // then
            assertThatThrownBy(() -> Lotto.from(inputNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Errors.WRONG_LOTTO_SIZE);
        }

        private static Stream<Arguments> methodSourceOfToString() {
            return Stream.of(
                Arguments.arguments(Arrays.asList(10, 9, 8, 1, 2, 3), Arrays.asList(1, 2, 3, 8, 9, 10)),
                Arguments.arguments(Arrays.asList(45, 40, 10, 30, 20, 25), Arrays.asList(10, 20, 25, 30, 40, 45))
            );
        }

        @ParameterizedTest(name = "{0}이 로또 numbers이면 해당 로또를 toString() 한 값은 {1}이다.")
        @MethodSource("methodSourceOfToString")
        @DisplayName("로또를 numbers() 하면 정렬된 숫자를 반환한다.")
        void toStringTest(List<Integer> inputNumbers, List<Integer> expectedNumbers) {
            // given
            Lotto lotto = Lotto.from(inputNumbers);
            // when
            List<Integer> lottoNumbers = lotto.getNumbers();
            // then
            assertThat(lottoNumbers)
                .containsExactlyElementsOf(expectedNumbers);
        }

        private static Stream<Arguments> methodSourceOfDuplicateTest() {
            return Stream.of(
                Arguments.arguments(Arrays.asList(1, 4, 1, 1, 1, 1)),
                Arguments.arguments(Arrays.asList(10, 10, 11, 11, 12, 12))
            );
        }

        @ParameterizedTest(name = "{0}은 중복된 숫자가 있으므로 예외가 발생한다..")
        @MethodSource("methodSourceOfDuplicateTest")
        @DisplayName("생성되는 로또는 중복되지 않은 숫자들의 구성으로 이루어져있다..")
        void duplicateNumbersTest(List<Integer> inputNumbers) {
            // given
            // when
            // then
            assertThatThrownBy(() -> Lotto.from(inputNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Errors.NUMBERS_HAS_DUPLICATE_NUMBER);
        }

        private static Stream<Arguments> methodSourceOfRangeTest() {
            return Stream.of(
                Arguments.arguments(Arrays.asList(0, 0, 0, 0, 0, 0)),
                Arguments.arguments(Arrays.asList(50, 60, 70, 80, 90, 100))
            );
        }

        @ParameterizedTest(name = "{0}은 1~45 사이가 아닌 숫자가 있으므로 예외가 발생한다..")
        @MethodSource("methodSourceOfRangeTest")
        @DisplayName("생성되는 로또는 1 ~ 45 사이의 숫자들의 구성으로 이루어져있다..")
        void numberRangeTest(List<Integer> inputNumbers) {
            // given
            // when
            // then
            assertThatThrownBy(() -> Lotto.from(inputNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Errors.NUMBER_IS_NOT_IN_VALID_RANGE);
        }

    }

    private static Stream<Arguments> methodSourceOfGetMatchingNumberCount() {
        return Stream.of(
            Arguments.arguments(Arrays.asList(45, 40, 10, 30, 20, 25), Arrays.asList(45, 40, 1, 2, 3, 4), 2),
            Arguments.arguments(Arrays.asList(45, 40, 10, 30, 20, 25), Arrays.asList(45, 40, 10, 30, 3, 4), 4)
        );
    }

    @ParameterizedTest(name = "{0}라는 로또와 {1}을 비교했을 때, 같은 숫자의 개수는 {2}개이다.")
    @MethodSource("methodSourceOfGetMatchingNumberCount")
    @DisplayName("주어진 리스트와 몇 개의 숫자가 일치하는지 계산할 수 있다.")
    void getMatchingNumberTest(List<Integer> lottoNumbers, List<Integer> comparingNumbers, int expectedCount) {
        // given
        Lotto lotto = Lotto.from(lottoNumbers);
        final Lotto comparingLotto = Lotto.from(comparingNumbers);
        // when
        final int matchingNumberCount = lotto.getMatchingNumberCount(comparingLotto);
        // then
        assertThat(matchingNumberCount)
            .isEqualTo(expectedCount);
    }

    private static Stream<Arguments> methodSourceOfContainsTest() {
        return Stream.of(
            Arguments.arguments(Arrays.asList(45, 40, 10, 30, 20, 25), 10, true),
            Arguments.arguments(Arrays.asList(45, 40, 10, 30, 20, 25), 43, false)
        );
    }

    @ParameterizedTest(name = "{0}의 로또 번호에 {1}이 포함되어있는지의 여부는 {2}이다.")
    @MethodSource("methodSourceOfContainsTest")
    @DisplayName("특정 숫자가 로또번호에 포함되어있는지 판단할 수 있다.")
    void containsTest(List<Integer> lottoNumbers, int givenNumber, boolean expectedResult) {
        // given
        final Lotto lotto = Lotto.from(lottoNumbers);
        // when
        final boolean result = lotto.isContains(givenNumber);
        // then
        assertThat(result).isEqualTo(expectedResult);
    }

}
