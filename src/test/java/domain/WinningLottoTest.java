package domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WinningLottoTest {
    @Test
    void 당첨_번호와_구매_로또의_일치_개수를_계산한다() {
        WinningLotto winningLotto = WinningLotto.from(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto = Lotto.from(List.of(1, 2, 3, 7, 8, 9));

        int matchCount = winningLotto.countMatches(lotto);

        assertThat(matchCount).isEqualTo(3);
    }
}
