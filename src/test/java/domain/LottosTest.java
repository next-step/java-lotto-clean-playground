package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class LottosTest {

    @Test
    void 모든_로또를_당첨로또와_비교해_결과를_계산한다() {
        Lotto winningLotto =
                createLotto(1, 2, 3, 4, 5, 6);

        Lotto threeMatch =
                createLotto(1, 2, 3, 10, 20, 30);

        Lotto fourMatch =
                createLotto(1, 2, 3, 4, 20, 30);

        Lotto twoMatch =
                createLotto(1, 2, 10, 20, 30, 40);

        Lottos lottos = new Lottos(
                List.of(threeMatch, fourMatch, twoMatch)
        );

        LottoResult result = lottos.getMatchCount(winningLotto);

        assertThat(result.getWinningCount(Rank.THREE))
                .isEqualTo(1);

        assertThat(result.getWinningCount(Rank.FOUR))
                .isEqualTo(1);

        assertThat(result.getWinningCount(Rank.FIVE))
                .isZero();

        assertThat(result.getWinningCount(Rank.SIX))
                .isZero();
    }

    private Lotto createLotto(int... values) {
        List<LottoNumber> numbers = Arrays.stream(values)
                                          .mapToObj(LottoNumber::new)
                                          .toList();

        return new Lotto(numbers);
    }
}