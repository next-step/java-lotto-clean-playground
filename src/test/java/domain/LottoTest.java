package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoTest {

    private static NumberGenerator numberGenerator;
    private static Lotto lotto;

    static class TestNumberGenerator implements NumberGenerator {

        private final List<Integer> numbers;
        private int index;

        TestNumberGenerator(List<Integer> numbers) {
            this.numbers = numbers;
            this.index = 0;
        }

        @Override
        public int generate() {
            return numbers.get(index++);
        }

    }

    @Nested
    @DisplayName("로또 생성 테스트")
    class createLotto {

        private static Stream<Arguments> methodSourceOfCreateLotto() {
            return Stream.of(
                Arguments.arguments(List.of(1, 4, 1, 30, 31, 32, 33), List.of(1, 4, 30, 31, 32, 33)),
                Arguments.arguments(List.of(10, 10, 11, 11, 12, 12, 13, 13, 14, 14, 15, 15),
                                    List.of(10, 11, 12, 13, 14, 15))
            );
        }

        @ParameterizedTest(name = "랜덤으로 {0} 의 숫자가 생성된다면, 생성되는 로또 번호는 중복 값을 제거한 {1}이다.")
        @MethodSource("methodSourceOfCreateLotto")
        @DisplayName("로또는 중복값을 포함하지 않는 숫자들로 구성된다.")
        void createLottoNumberTest(List<Integer> inputNumbers, List<Integer> expectedLottoNumbers) {
            // given
            numberGenerator = new TestNumberGenerator(inputNumbers);
            // when
            lotto = new Lotto(numberGenerator);
            // then
            String stringOfLotto = lotto.toString();
            List<Integer> createdLottoNumber = parseStringToIntegerList(stringOfLotto);
            assertThat(createdLottoNumber)
                .containsExactlyInAnyOrderElementsOf(expectedLottoNumbers);
        }

        @Test
        @DisplayName("생성되는 로또의 사이즈는 6이다.")
        void createLottoSizeTest() {
            // given
            numberGenerator = new RandomLottoNumberGenerator();
            // when
            lotto = new Lotto(numberGenerator);
            // then
            String stringOfLotto = lotto.toString();
            List<Integer> createdLottoNumber = parseStringToIntegerList(stringOfLotto);
            assertThat(createdLottoNumber.size())
                .isEqualTo(6);
        }
    }

    private List<Integer> parseStringToIntegerList(String input) {
        return Arrays.stream(input.substring(1, input.length()-1).split(","))
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(Collectors.toList());
    }
}
