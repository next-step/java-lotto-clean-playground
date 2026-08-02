package domain.lotto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class LottoTest {
    private Lotto createLotto(int... numbers) {
        return new Lotto(
                Arrays.stream(numbers)
                        .mapToObj(LottoNumber::from)
                        .toList()
        );
    }
    @Test
    void 번호가_6개이면_생성된다() {
        assertDoesNotThrow(() -> createLotto(1, 2, 3, 4, 5, 6));
    }

    @ParameterizedTest
    @MethodSource("invalidSizeNumbers")
    void 번호_개수가_6개보다_많거나_적으면_생성되지_않는다(int[] numbers) {
        assertThatThrownBy(() -> createLotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 6개여야 합니다.");
    }

    private static Stream<int[]> invalidSizeNumbers() {
        return Stream.of(
                new int[]{1, 2, 3, 4, 5},
                new int[]{1, 2, 3, 4, 5, 6, 7}
        );
    }

    @Test
    void 번호가_정상범위인_경우_생성된다() {
        assertDoesNotThrow(() -> createLotto(1, 7, 16, 24, 32, 45));
    }

    @Test
    void 중복된_숫자가_존재하면_생성되지_않는다() {
        assertThatThrownBy(() -> createLotto(1, 2, 3, 4, 5, 5))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 중복될 수 없습니다.");
    }
    @ParameterizedTest
    @MethodSource("matchedCountCases")
    void 일치한_번호의_개수를_반환한다(int[] otherNumbers, int expectedCount) {
        Lotto lotto1 = createLotto(1, 2, 3, 4, 5, 6);
        Lotto lotto2 = createLotto(otherNumbers);

        int result = lotto1.matchCount(lotto2);

        assertEquals(expectedCount, result);
    }
    private static Stream<Arguments> matchedCountCases() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3, 4, 5, 6}, 6),
                Arguments.of(new int[]{1, 2, 3, 7, 8, 9}, 3),
                Arguments.of(new int[]{7, 8, 9, 10, 11, 12}, 0)
        );
    }

    @ParameterizedTest
    @CsvSource({"1, true", "7, false"})
    void 번호가_포함되었는지_여부를_반환한다(int number, boolean expected) {
        Lotto lotto = createLotto(1, 2, 3, 4, 5, 6);

        boolean result = lotto.contains(LottoNumber.from(number));

        assertEquals(expected, result);
    }
}
