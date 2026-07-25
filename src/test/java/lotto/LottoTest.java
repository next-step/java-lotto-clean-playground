package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTest {

    @Test
    @DisplayName("중복 없는 로또 번호 6개를 가진 로또를 생성한다")
    void createLotto() {
        assertThatCode(() -> new Lotto(Arrays.asList(
                number(1), number(2), number(3),
                number(4), number(5), number(6))))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("로또 번호가 6개가 아니면 생성할 수 없다")
    void rejectInvalidSize() {
        assertThatThrownBy(() -> new Lotto(Arrays.asList(number(1), number(2))))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("중복된 번호가 있으면 로또를 생성할 수 없다")
    void rejectDuplicateNumber() {
        assertThatThrownBy(() -> new Lotto(Arrays.asList(
                number(1), number(1), number(2),
                number(3), number(4), number(5))))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("당첨 로또와 일치하는 번호 개수를 계산한다")
    void countMatches() {
        Lotto lotto = new Lotto(Arrays.asList(
                number(1), number(2), number(3), number(7), number(9), number(11)));
        Lotto winningLotto = new Lotto(Arrays.asList(
                number(1), number(2), number(3), number(4), number(5), number(6)));

        assertThat(lotto.countMatches(winningLotto)).isEqualTo(3);
    }

    @Test
    @DisplayName("일치하는 번호 개수로 당첨 등수를 판정한다")
    void determineRank() {
        Lotto lotto = new Lotto(Arrays.asList(
                number(1), number(2), number(3), number(7), number(9), number(11)));
        Lotto winningLotto = new Lotto(Arrays.asList(
                number(1), number(2), number(3), number(4), number(5), number(6)));

        assertThat(lotto.determineRank(winningLotto)).isEqualTo(LottoRank.FOURTH);
    }

    private LottoNumber number(int value) {
        return new LottoNumber(value);
    }
}
