package domain;

import domain.lotto.Lotto;
import domain.lotto.LottoNumber;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;


class LottoTest {
    private Lotto createLotto(int... numbers) {
        return new Lotto(
                Arrays.stream(numbers)
                        .mapToObj(LottoNumber::new)
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
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> createLotto(numbers)
        );
        assertEquals("로또 번호는 6개여야 합니다.", exception.getMessage());
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
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> createLotto(1, 2, 3, 4, 5, 5)
        );
        assertEquals("로또 번호는 중복될 수 없습니다.", exception.getMessage());
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


    @Test
    void 보너스_번호와_당첨_번호가_중복되면_오류가_발생한다() {
        Lotto lotto = createLotto(1, 2, 3, 4, 5, 6);

        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class,
                        () -> lotto.validateBonusNumber(new LottoNumber(1))
                );

        assertThat(exception.getMessage())
                .isEqualTo("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
    }

    @ParameterizedTest
    @CsvSource({"1, true", "7, false"})
    void 번호가_포함되었는지_여부를_반환한다(int number, boolean expected) {
        Lotto lotto = createLotto(1, 2, 3, 4, 5, 6);

        boolean result = lotto.contains(new LottoNumber(number));

        assertEquals(expected, result);
    }
}
