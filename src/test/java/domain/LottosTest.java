package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LottosTest {

    @Test
    void 모든_로또의_당첨_결과를_받는다() {
        // given
        WinningLotto winningLotto = new WinningLotto(
                List.of(1, 2, 3, 4, 5, 6),
                7
        );
        Lotto fiveAndBonusMatch = Lotto.from(List.of(1, 2, 3, 4, 5, 7));
        Lotto fiveMatch = Lotto.from(List.of(1, 2, 3, 4, 5, 30));
        Lotto twoMatch = Lotto.from(List.of(1, 2, 10, 20, 30, 40));
        Lottos lottos = new Lottos(
                List.of(fiveAndBonusMatch, fiveMatch, twoMatch)
        );

        // when
        LottoResult result = lottos.calculateResult(winningLotto);

        // then
        assertThat(result.getResults())
                .containsEntry(Rank.FIRST, 0)
                .containsEntry(Rank.SECOND, 1)
                .containsEntry(Rank.THIRD, 1)
                .containsEntry(Rank.FOURTH, 0)
                .containsEntry(Rank.FIFTH, 0);
    }
}
