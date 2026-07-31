package domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningLottoTest {
    @Test
    void 당첨_번호와_구매_로또의_일치_개수를_계산한다() {
        // given
        WinningLotto winningLotto = WinningLotto.from(List.of(1, 2, 3, 4, 5, 6), 7);
        Lotto lotto = Lotto.from(List.of(1, 2, 3, 7, 8, 9));

        // when
        int matchCount = winningLotto.countMatchingNumbersOf(lotto);

        // then
        assertThat(matchCount).isEqualTo(3);
    }

    @Test
    void 구매_로또가_보너스_번호를_포함하는지_확인한다() {
        // given
        WinningLotto winningLotto = WinningLotto.from(List.of(1, 2, 3, 4, 5, 6), 7);
        Lotto lotto = Lotto.from(List.of(1, 2, 3, 4, 5, 7));

        // when
        boolean bonusMatch = winningLotto.matchesBonus(lotto);

        // then
        assertThat(bonusMatch).isTrue();
    }

    @Test
    void 보너스_번호가_당첨_번호와_중복되면_예외를_던진다() {
        assertThatThrownBy(() -> WinningLotto.from(List.of(1, 2, 3, 4, 5, 6), 6))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
