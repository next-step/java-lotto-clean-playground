package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class LottoWinningNumbersTest {

    LottoWinningNumbers lottoWinningNumbers;

    @Test
    @DisplayName("LottoWinningNumbers가_로또의_맞는개수를_정확히_판단한다")
    void LottoWinningNumbers가_로또의_맞는개수를_정확히_판단한다() {
        //given
        List<Integer> numbers1 = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
        lottoWinningNumbers = LottoWinningNumbers.from(numbers1, 7);

        List<Integer> numbers2 = new ArrayList<>(List.of(1, 2, 3, 8, 9, 10));
        Lotto lotto = Lotto.from(numbers2);

        //then
        assertThat(lottoWinningNumbers.matchCount(lotto)).isEqualTo(3);
    }

    @Test
    @DisplayName("LottoWinningNumbers가_보너스의_일치여부를_정확히_판단한다")
    void LottoWinningNumbers가_보너스의_일치여부를_정확히_판단한다() {
        //given
        List<Integer> numbers1 = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
        lottoWinningNumbers = LottoWinningNumbers.from(numbers1, 7);

        List<Integer> numbers2 = new ArrayList<>(List.of(1, 2, 3, 4, 5, 7));
        Lotto lotto = Lotto.from(numbers2);

        //then
        assertThat(lottoWinningNumbers.bonusMatch(lotto)).isTrue();
    }

    @Test
    @DisplayName("보너스번호는 당첨번호와 중복될 수 없다")
    void 보너스번호는_당첨번호와_중복될_수_없다() {
        //given
        List<Integer> numbers1 = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
        assertThatThrownBy(() -> LottoWinningNumbers.from(numbers1, 6))
                .isInstanceOf(IllegalArgumentException.class);

    }
}
