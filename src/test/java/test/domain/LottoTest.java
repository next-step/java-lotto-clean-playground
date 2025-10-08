package test.domain;

import domain.Lotto;
import domain.value.BonusNumber;
import domain.value.WinningNumbers;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LottoTest {

    @Nested
    class 생성자_테스트 {

        @Test
        void 숫자_6개_중복없고_범위내면_생성된다() {
            List<Integer> numbers = Arrays.asList(1, 7, 15, 23, 34, 45);
            assertDoesNotThrow(() -> new Lotto(numbers));
        }

        @Test
        void 숫자_개수가_6개가_아니면_예외가_발생한다() {
            List<Integer> fiveList = Arrays.asList(1, 2, 3, 4, 5);
            List<Integer> sevenList = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
            assertThrows(IllegalArgumentException.class, () -> new Lotto(fiveList));
            assertThrows(IllegalArgumentException.class, () -> new Lotto(sevenList));
        }

        @Test
        void 중복된_숫자가_있으면_예외가_발생한다() {
            List<Integer> duplication = Arrays.asList(1, 2, 3, 4, 5, 5);
            assertThrows(IllegalArgumentException.class, () -> new Lotto(duplication));
        }

        @Test
        void 숫자에_0이_포함되면_예외가_발생한다() {
            List<Integer> numbers = Arrays.asList(0, 2, 3, 4, 5, 6);
            assertThrows(IllegalArgumentException.class, () -> new Lotto(numbers));
        }

        @Test
        void 숫자에_46이_포함되면_예외가_발생한다() {
            List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 46);
            assertThrows(IllegalArgumentException.class, () -> new Lotto(numbers));
        }
    }

    @Nested
    class isBonusNumberMatched_테스트 {

        @Test
        void 보너스번호가_포함되면_true를_반환한다() {
            Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
            BonusNumber bonus = new BonusNumber(6);
            assertTrue(lotto.isBonusNumberMatched(bonus));
        }

        @Test
        void 보너스번호가_포함되지않으면_false를_반환한다() {
            Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
            BonusNumber bonus = new BonusNumber(7);
            assertFalse(lotto.isBonusNumberMatched(bonus));
        }
    }

    @Nested
    class countMatches_테스트 {

        @Test
        void 당첨번호와_3개_일치하면_count는_3이다() {
            Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 10, 20, 30));
            WinningNumbers winning = new WinningNumbers(Arrays.asList(1, 2, 3, 40, 41, 42));
            assertEquals(3, lotto.countMatches(winning));
        }

        @Test
        void 당첨번호와_전혀_일치하지않으면_count는_0이다() {
            Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 10, 20, 30));
            WinningNumbers winning = new WinningNumbers(Arrays.asList(40, 41, 42, 43, 44, 45));
            assertEquals(0, lotto.countMatches(winning));
        }
    }

}
