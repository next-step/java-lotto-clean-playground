package domain;

import domain.lotto.Lotto;
import domain.lotto.WinningLotto;
import domain.result.MatchResult;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class WinningLottoTest {
    @Test
    void 보너스볼이_당첨번호와_중복이면_예외가_발생한다() {
        // given
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 1;

        // when & then
        assertThatThrownBy(() -> new WinningLotto(winningNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("보너스 볼은 당첨 번호와 중복될 수 없습니다.");
    }

    @Test
    void 로또와_당첨번호의_일치_개수를_계산한다() {
        // given
        WinningLotto winningLotto = new WinningLotto(
                List.of(1, 2, 3, 4, 5, 6),
                7
        );
        Lotto lotto = Lotto.from(List.of(1, 2, 3, 10, 11, 12));

        // when
        MatchResult result = winningLotto.createMatchResult(lotto);

        // then
        assertThat(result.getMatchCount()).isEqualTo(3);
    }

    @Test
    void 로또가_보너스볼을_포함하면_보너스볼_일치로_판단한다() {
        // given
        WinningLotto winningLotto = new WinningLotto(
                List.of(1, 2, 3, 4, 5, 6),
                7
        );
        Lotto lotto = Lotto.from(List.of(1, 2, 3, 4, 5, 7));

        // when
        MatchResult result = winningLotto.createMatchResult(lotto);

        // then
        assertThat(result.isBonusMatched()).isTrue();
    }

    @Test
    void 로또가_보너스볼을_포함하지_않으면_보너스볼_불일치로_판단한다() {
        // given
        WinningLotto winningLotto = new WinningLotto(
                List.of(1, 2, 3, 4, 5, 6),
                7
        );
        Lotto lotto = Lotto.from(List.of(1, 2, 3, 4, 5, 8));

        // when
        MatchResult result = winningLotto.createMatchResult(lotto);

        // then
        assertThat(result.isBonusMatched()).isFalse();
    }
}
