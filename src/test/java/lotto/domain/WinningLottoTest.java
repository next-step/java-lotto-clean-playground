package lotto.domain;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class WinningLottoTest {

    @Test
    void 번호_5개와_보너스_볼이_일치하면_2등이다() {
        // given
        Lotto winningNumbers = Lotto.from(Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = LottoNumber.valueOf(7);
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);

        Lotto userLotto = Lotto.from(Arrays.asList(1, 2, 3, 4, 5, 7)); // 1~5 일치 + 보너스 7 일치

        // when
        Rank rank = winningLotto.judge(userLotto);

        // then
        assertThat(rank).isEqualTo(Rank.SECOND);
    }

    @Test
    void 번호_5개만_일치하고_보너스_볼이_다르면_3등이다() {
        // given
        Lotto winningNumbers = Lotto.from(Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = LottoNumber.valueOf(7);
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);

        Lotto userLotto = Lotto.from(Arrays.asList(1, 2, 3, 4, 5, 8)); // 1~5 일치, 8은 보너스 아님

        // when
        Rank rank = winningLotto.judge(userLotto);

        // then
        assertThat(rank).isEqualTo(Rank.THIRD);
    }
}
