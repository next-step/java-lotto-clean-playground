package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import domain.lotto.Lotto;
import domain.winning.WinningLotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningLottoNumberTest {

    @Test
    @DisplayName("calculate match count")
    void calculateMatchCount() {
        WinningLotto winningLottoNumber = new WinningLotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto purchasedLotto = new Lotto(List.of(1, 2, 3, 10, 20, 30));

        int matchCount = winningLottoNumber.countMatches(purchasedLotto);

        assertThat(matchCount).isEqualTo(3);
    }

    @Test
    @DisplayName("there is no match")
    void noMatch() {
        WinningLotto winningLottoNumber = new WinningLotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto purchasedLotto = new Lotto(List.of(7, 8, 9, 10, 11, 12));

        int matchCount = winningLottoNumber.countMatches(purchasedLotto);

        assertThat(matchCount).isEqualTo(0);
    }

}
