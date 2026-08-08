package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

class WinningLottoTest {

    @Test
    void createWinningLottoWithDuplicateBonusNumber() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        assertThatThrownBy(
                () -> new WinningLotto(lotto, new LottoNumber(6))
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void determineFirstRank() {
        WinningLotto winningLotto = createWinningLotto();
        Lotto purchasedLotto =
                new Lotto(List.of(1, 2, 3, 4, 5, 6));

        assertThat(winningLotto.determineRank(purchasedLotto))
                .isEqualTo(Rank.FIRST);
    }

    @Test
    void determineSecondRank() {
        WinningLotto winningLotto = createWinningLotto();
        Lotto purchasedLotto =
                new Lotto(List.of(1, 2, 3, 4, 5, 7));

        assertThat(winningLotto.determineRank(purchasedLotto))
                .isEqualTo(Rank.SECOND);
    }

    @Test
    void determineThirdRank() {
        WinningLotto winningLotto = createWinningLotto();
        Lotto purchasedLotto =
                new Lotto(List.of(1, 2, 3, 4, 5, 8));

        assertThat(winningLotto.determineRank(purchasedLotto))
                .isEqualTo(Rank.THIRD);
    }

    @Test
    void determineFourthRank() {
        WinningLotto winningLotto = createWinningLotto();
        Lotto purchasedLotto =
                new Lotto(List.of(1, 2, 3, 4, 8, 9));

        assertThat(winningLotto.determineRank(purchasedLotto))
                .isEqualTo(Rank.FOURTH);
    }

    @Test
    void determineFifthRank() {
        WinningLotto winningLotto = createWinningLotto();
        Lotto purchasedLotto =
                new Lotto(List.of(1, 2, 3, 8, 9, 10));

        assertThat(winningLotto.determineRank(purchasedLotto))
                .isEqualTo(Rank.FIFTH);
    }

    @Test
    void determineMiss() {
        WinningLotto winningLotto = createWinningLotto();
        Lotto purchasedLotto =
                new Lotto(List.of(1, 2, 8, 9, 10, 11));

        assertThat(winningLotto.determineRank(purchasedLotto))
                .isEqualTo(Rank.MISS);
    }

    private WinningLotto createWinningLotto() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        return new WinningLotto(lotto, new LottoNumber(7));
    }
}
