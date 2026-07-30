package domain.lotto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

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


    @Test
    void 보너스_번호와_당첨_번호가_중복되면_오류가_발생한다() {
        Lotto lotto = createLotto(1, 2, 3, 4, 5, 6);

        assertThatThrownBy(() -> lotto.validateBonusNumber(new LottoNumber(1)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
    }

    @ParameterizedTest
    @CsvSource({"1, true", "7, false"})
    void 번호가_포함되었는지_여부를_반환한다(int number, boolean expected) {
        Lotto lotto = createLotto(1, 2, 3, 4, 5, 6);

        boolean result = lotto.contains(new LottoNumber(number));

        assertEquals(expected, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5,6", " 1, 2, 3, 4, 5, 6"})
    void from으로_문자열을_파싱해_로또를_생성한다(String input) {
        Lotto lotto = Lotto.from(input);

        assertEquals(6, lotto.getLottoNumbers().size());
    }

    @Test
    void from에_숫자가_아닌_값이_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> Lotto.from("1,2,3,4,5,가"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("숫자만 입력해주세요.");
    }

    @Test
    void from에_범위를_벗어난_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> Lotto.from("1,2,3,4,5,50"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 1~45 사이여야 합니다.");
    }
}
