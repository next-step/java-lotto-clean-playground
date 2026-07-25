package domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {

    @Test
    @DisplayName("로또 번호 목록을 조회한다")
    void returnLottoNumbers() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        List<Integer> result = lotto.values();

        assertThat(result).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @Test
    @DisplayName("당첨 번호와 일치하는 번호 개수를 센다")
    void countMatchingNumbers() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 10, 11, 12));
        WinningLotto winningLotto = WinningLotto.from(List.of(1, 2, 3, 4, 5, 6));

        boolean result = lotto.countMatching(winningLotto).isSame(3);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("로또 번호가 6개가 아니면 예외가 발생한다")
    void throwExceptionWhenNumberCountIsNotSix() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 6개여야 합니다.");
    }

    @Test
    @DisplayName("로또 번호가 중복되면 예외가 발생한다")
    void throwExceptionWhenNumberIsDuplicated() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 중복될 수 없습니다.");
    }
}
