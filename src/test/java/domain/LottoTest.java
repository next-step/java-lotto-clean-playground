package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

public class LottoTest {
    @Test
    void 로또번호가_6개가_아니면_예외가_발생한다() {
        List<LottoNumber> numbers = List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5)
        );

        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 6개여야 합니다.");
    }

    @Test
    void 로또번호가_중복되면_예외가_발생한다() {
        List<LottoNumber> numbers = List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(5)
        );

        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 중복일 수 없습니다.");
    }

    @Test
    void 당첨로또와_일치하는_번호의_개수를_계산한다() {
        Lotto lotto = Lotto.from(List.of(1, 2, 3, 10, 20, 30));
        Lotto winningLotto = Lotto.from(List.of(1, 2, 3, 4, 5, 6));

        int matchCount = lotto.calculateMatchCount(winningLotto);

        assertThat(matchCount).isEqualTo(3);
    }

    @Test
    void 로또번호에_보너스볼이_포함되는_것을_확인한다() {
        Lotto lotto = Lotto.from(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = new LottoNumber(1);

        assertThat(lotto.contains(bonusNumber)).isTrue();
    }
}
