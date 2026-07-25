package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningLottoTest {

    @Test
    @DisplayName("5개와 보너스 번호가 일치하면 2등으로 판정한다")
    void determineSecondRank() {
        Lotto lotto = lotto(1, 2, 3, 4, 5, 7);
        WinningLotto winningLotto = new WinningLotto(lotto(1, 2, 3, 4, 5, 6), number(7));

        assertThat(winningLotto.determineRank(lotto)).isEqualTo(LottoRank.SECOND);
    }

    @Test
    @DisplayName("보너스 번호는 당첨 번호와 중복될 수 없다")
    void rejectDuplicateBonusNumber() {
        Lotto winningNumbers = lotto(1, 2, 3, 4, 5, 6);

        assertThatThrownBy(() -> new WinningLotto(winningNumbers, number(6)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private Lotto lotto(int first, int second, int third, int fourth, int fifth, int sixth) {
        return new Lotto(Arrays.asList(
                number(first), number(second), number(third),
                number(fourth), number(fifth), number(sixth)));
    }

    private LottoNumber number(int value) {
        return new LottoNumber(value);
    }
}
