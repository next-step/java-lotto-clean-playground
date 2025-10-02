package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static model.LottoFixture.DEFAULT_WINNING_NUMBERS;
import static model.LottoFixture.LOTTO_MATCH_0;
import static model.LottoFixture.LOTTO_MATCH_3;
import static model.LottoFixture.LOTTO_MATCH_4;
import static model.LottoFixture.LOTTO_MATCH_5_MATCH_BONUS_BALL;
import static model.LottoFixture.LOTTO_MATCH_5_MISS_BONUS_BALL;
import static model.LottoFixture.LOTTO_MATCH_6;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("model.Lotto 클래스 테스트")
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class LottoTest {
    private static Stream<Arguments> provideLottoNumbersAndExpectedMatchCount() {
        WinningNumbers winningNumbers = new WinningNumbers(DEFAULT_WINNING_NUMBERS);
        return Stream.of(
                Arguments.of(LOTTO_MATCH_6, winningNumbers, 6),
                Arguments.of(LOTTO_MATCH_5_MATCH_BONUS_BALL, winningNumbers, 5),
                Arguments.of(LOTTO_MATCH_5_MISS_BONUS_BALL, winningNumbers, 5),
                Arguments.of(LOTTO_MATCH_4, winningNumbers, 4),
                Arguments.of(LOTTO_MATCH_3, winningNumbers, 3),
                Arguments.of(LOTTO_MATCH_0, winningNumbers, 0));
    }

    ;

    @Test
    void Lotto_생성_시_숫자가_6개가_아니면_예외가_발생한다() {
        List<Integer> numbersWithFive = List.of(1, 2, 3, 4, 5);

        assertThatThrownBy(() -> new Lotto(numbersWithFive))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void Lotto_생성_시_중복된_숫자가_있으면_예외가_발생한다() {
        List<Integer> numbersWithDuplicates = List.of(1, 2, 3, 4, 5, 5);

        assertThatThrownBy(() -> new Lotto(numbersWithDuplicates))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("provideLottoNumbersAndExpectedMatchCount")
    void countMatches_호출_시_일치하는_번호의_개수를_정확히_반환한다(
            Lotto userLotto,
            WinningNumbers winningNumbers,
            int expectedCount
    ) {
        int matchCount = userLotto.countMatches(winningNumbers);

        assertEquals(expectedCount, matchCount);
    }
}
