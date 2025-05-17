package domain;

import static domain.WinningLotto.ERROR_DUPLICATE_BONUS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningLottoTest {

    @Test
    @DisplayName("보너스 번호가 당첨 번호에 포함되면 예외가 발생한다")
    void duplicateBonusNumberTest() {
        List<LottoNumber> winningNumbers = Stream.of(1, 2, 3, 4, 5, 6)
                .map(LottoNumber::new)
                .toList();
        Lotto lotto = new Lotto(winningNumbers);
        LottoNumber bonusNumber = winningNumbers.get(0);
        assertThatThrownBy(() -> new WinningLotto(lotto, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_DUPLICATE_BONUS);
    }

    @Test
    void countMatch() {
        List<LottoNumber> numbers = Stream.of(1, 2, 3, 4, 5, 6)
                .map(LottoNumber::new)
                .toList();
        WinningLotto winningLotto = new WinningLotto(new Lotto(numbers), new LottoNumber(7));
        Lotto userLotto = new Lotto(numbers);

        assertThat(winningLotto.countMatch(userLotto)).isEqualTo(6);
    }

    @Test
    void matchBonus() {
        List<LottoNumber> numbers = Stream.of(1, 2, 3, 4, 5, 6)
                .map(LottoNumber::new)
                .toList();
        WinningLotto winningLotto = new WinningLotto(new Lotto(numbers), new LottoNumber(7));
        Lotto userLotto = new Lotto(numbers);

        assertThat(winningLotto.matchBonus(userLotto)).isFalse();
    }
}