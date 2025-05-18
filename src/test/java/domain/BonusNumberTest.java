package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class BonusNumberTest {

    @Test
    @DisplayName("보너스 번호는 당첨 번호와 중복되지 않으면 생성된다")
    void validBonusNumber() {
        WinningLotto winningLotto = new WinningLotto(toLottoNumbers(List.of(1, 2, 3, 4, 5, 6)));
        BonusNumber bonus = new BonusNumber(7, winningLotto);

        assertThat(bonus.value()).isEqualTo(7);
    }

    @Test
    @DisplayName("보너스 번호가 0이면 예외를 던진다")
    void throwsWhenBonusIsZero() {
        WinningLotto winningLotto = new WinningLotto(toLottoNumbers(List.of(1, 2, 3, 4, 5, 6)));

        assertThatThrownBy(() -> new BonusNumber(0, winningLotto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 보너스 번호는 1부터 45 사이여야 합니다.");
    }

    @Test
    @DisplayName("보너스 번호가 46이면 예외를 던진다")
    void throwsWhenBonusIsOutOfRange() {
        WinningLotto winningLotto = new WinningLotto(toLottoNumbers(List.of(1, 2, 3, 4, 5, 6)));

        assertThatThrownBy(() -> new BonusNumber(46, winningLotto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 보너스 번호는 1부터 45 사이여야 합니다.");
    }

    @Test
    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외를 던진다")
    void throwsWhenBonusIsDuplicateWithWinningNumber() {
        WinningLotto winningLotto = new WinningLotto(toLottoNumbers(List.of(1, 2, 3, 4, 5, 6)));

        assertThatThrownBy(() -> new BonusNumber(3, winningLotto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
    }

    private List<LottoNumber> toLottoNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::new)
                .toList();
    }
}
