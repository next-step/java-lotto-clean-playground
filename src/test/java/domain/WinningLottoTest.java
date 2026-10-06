package domain;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningLottoTest {

    @Test
    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외가 발생한다")
    void rejectsBonusNumberIncludedInWinningNumbers() {
        // 준비
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        // 실행 및 검증
        assertThatThrownBy(() ->
                new WinningLotto(winningNumbers, new LottoNumber(3)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("당첨 번호 5개와 보너스 번호가 일치하면 2등이다")
    void returnsSecondWhenFiveNumbersAndBonusMatch() {
        // 준비
        WinningLotto winningLotto = new WinningLotto(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new LottoNumber(7));
        Lotto purchasedLotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));

        // 실행
        Rank result = winningLotto.match(purchasedLotto);

        // 검증
        assertThat(result).isEqualTo(Rank.SECOND);
    }

    @Test
    @DisplayName("당첨 번호 5개가 일치하고 보너스 번호가 불일치하면 3등이다")
    void returnsThirdWhenFiveNumbersMatchWithoutBonus() {
        // 준비
        WinningLotto winningLotto = new WinningLotto(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new LottoNumber(7));
        Lotto purchasedLotto = new Lotto(List.of(1, 2, 3, 4, 5, 8));

        // 실행
        Rank result = winningLotto.match(purchasedLotto);

        // 검증
        assertThat(result).isEqualTo(Rank.THIRD);
    }
}
