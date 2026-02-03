package domain.lotto;

import exception.BonusNumberDuplicatedException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningLottoTest {

    @DisplayName("당첨 번호와 보너스 번호로 WinningLotto를 생성한다")
    @Test
    void createWinningLotto() {
        // given
        Lotto winningNumbers = new Lotto(LottoNumbers.from("1, 2, 3, 4, 5, 6"));
        LottoNumber bonusNumber = new LottoNumber(7);

        // when
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);

        // then
        assertThat(winningLotto).isNotNull();
    }

    @DisplayName("보너스 번호가 당첨 번호에 포함되어 있으면 예외가 발생한다")
    @Test
    void throwExceptionWhenBonusNumberIsDuplicated() {
        // given
        Lotto winningNumbers = new Lotto(LottoNumbers.from("1, 2, 3, 4, 5, 6"));
        LottoNumber bonusNumber = new LottoNumber(6);

        // when & then
        assertThatThrownBy(() -> new WinningLotto(winningNumbers, bonusNumber))
                .isInstanceOf(BonusNumberDuplicatedException.class);
    }

    @DisplayName("6개 번호가 모두 일치하면 1등이다")
    @Test
    void matchFirst() {
        // given
        Lotto winningNumbers = new Lotto(LottoNumbers.from("1, 2, 3, 4, 5, 6"));
        LottoNumber bonusNumber = new LottoNumber(7);
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);
        Lotto userLotto = new Lotto(LottoNumbers.from("1, 2, 3, 4, 5, 6"));

        // when
        Rank rank = winningLotto.match(userLotto);

        // then
        assertThat(rank).isEqualTo(Rank.FIRST);
    }

    @DisplayName("5개 번호가 일치하고 보너스 번호가 일치하면 2등이다")
    @Test
    void matchSecond() {
        // given
        Lotto winningNumbers = new Lotto(LottoNumbers.from("1, 2, 3, 4, 5, 6"));
        LottoNumber bonusNumber = new LottoNumber(7);
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);
        Lotto userLotto = new Lotto(LottoNumbers.from("1, 2, 3, 4, 5, 7"));

        // when
        Rank rank = winningLotto.match(userLotto);

        // then
        assertThat(rank).isEqualTo(Rank.SECOND);
    }

    @DisplayName("5개 번호가 일치하고 보너스 번호가 일치하지 않으면 3등이다")
    @Test
    void matchThird() {
        // given
        Lotto winningNumbers = new Lotto(LottoNumbers.from("1, 2, 3, 4, 5, 6"));
        LottoNumber bonusNumber = new LottoNumber(7);
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);
        Lotto userLotto = new Lotto(LottoNumbers.from("1, 2, 3, 4, 5, 45"));

        // when
        Rank rank = winningLotto.match(userLotto);

        // then
        assertThat(rank).isEqualTo(Rank.THIRD);
    }

    @DisplayName("4개 번호가 일치하면 4등이다")
    @Test
    void matchFourth() {
        // given
        Lotto winningNumbers = new Lotto(LottoNumbers.from("1, 2, 3, 4, 5, 6"));
        LottoNumber bonusNumber = new LottoNumber(7);
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);
        Lotto userLotto = new Lotto(LottoNumbers.from("1, 2, 3, 4, 44, 45"));

        // when
        Rank rank = winningLotto.match(userLotto);

        // then
        assertThat(rank).isEqualTo(Rank.FOURTH);
    }

    @DisplayName("3개 번호가 일치하면 5등이다")
    @Test
    void matchFifth() {
        // given
        Lotto winningNumbers = new Lotto(LottoNumbers.from("1, 2, 3, 4, 5, 6"));
        LottoNumber bonusNumber = new LottoNumber(7);
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);
        Lotto userLotto = new Lotto(LottoNumbers.from("1, 2, 3, 43, 44, 45"));

        // when
        Rank rank = winningLotto.match(userLotto);

        // then
        assertThat(rank).isEqualTo(Rank.FIFTH);
    }

    @DisplayName("2개 이하로 일치하면 낙첨이다")
    @Test
    void matchMiss() {
        // given
        Lotto winningNumbers = new Lotto(LottoNumbers.from("1, 2, 3, 4, 5, 6"));
        LottoNumber bonusNumber = new LottoNumber(7);
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);
        Lotto userLotto = new Lotto(LottoNumbers.from("1, 2, 40, 41, 44, 45"));

        // when
        Rank rank = winningLotto.match(userLotto);

        // then
        assertThat(rank).isEqualTo(Rank.MISS);
    }
}
