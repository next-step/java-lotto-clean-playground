package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("model.Lotto 클래스 테스트")
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class LottoTest {
    private static Stream<Arguments> provideLottoNumbersAndExpectedMatchCount() {
        return Stream.of(Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6), Arrays.asList(1, 2, 3, 4, 5, 6), 6), Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 7), Arrays.asList(1, 2, 3, 4, 5, 6), 5), Arguments.of(Arrays.asList(1, 2, 3, 4, 7, 8), Arrays.asList(1, 2, 3, 4, 5, 6), 4), Arguments.of(Arrays.asList(1, 2, 3, 7, 8, 9), Arrays.asList(1, 2, 3, 4, 5, 6), 3), Arguments.of(Arrays.asList(10, 11, 12, 13, 14, 15), Arrays.asList(1, 2, 3, 4, 5, 6), 0));
    }

    @Test
    void Lotto_생성_시_숫자가_6개가_아니면_예외가_발생한다() {
        List<Integer> numbersWithFive = List.of(1, 2, 3, 4, 5);
        assertThrows(IllegalArgumentException.class, () -> new Lotto(numbersWithFive));
    }

    @Test
    void Lotto_생성_시_중복된_숫자가_있으면_예외가_발생한다() {
        List<Integer> numbersWithDuplicates = List.of(1, 2, 3, 4, 5, 5);
        assertThrows(IllegalArgumentException.class, () -> new Lotto(numbersWithDuplicates));
    }

    @ParameterizedTest
    @MethodSource("provideLottoNumbersAndExpectedMatchCount")
    void countMatches_호출_시_일치하는_번호의_개수를_정확히_반환한다(
            List<Integer> userNumbers,
            List<Integer> winningNumbers,
            int expectedCount
    ) {
        Lotto userLotto = new Lotto(userNumbers);
        List<LottoNumber> winningLottoNumbers = winningNumbers.stream().map(LottoNumber::new).collect(Collectors.toList());
        WinningNumbers winningNumbersObject = new WinningNumbers(winningLottoNumbers);

        int matchCount = userLotto.countMatches(winningNumbersObject);

        assertEquals(expectedCount, matchCount);
    }
}
