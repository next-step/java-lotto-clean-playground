import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class LottoTest {
    @Test
    void 로또는_6개의_수로_이루어져야_한다() {
        var numbers = List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3));

        assertThatThrownBy(() -> new Lotto(numbers)).isInstanceOf(IllegalArgumentException.class).hasMessage("로또는 6개의 수로 이루어져야 한다. ");
    }

    // fail
    @Test
    void 로또_숫자는_중복이_없어야_한다() {
        var numbers = List.of(new LottoNumber(1), new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4), new LottoNumber(5));

        assertThatThrownBy(() -> new Lotto(numbers)).isInstanceOf(IllegalArgumentException.class).hasMessage("로또 숫자는 중복이 없어야 한다. ");
    }

    // fail
    @Test
    void 로또_숫자는_오름차순_정렬되어있다() {
        var unsortedNumbers = List.of(new LottoNumber(6), new LottoNumber(5), new LottoNumber(4), new LottoNumber(3), new LottoNumber(2), new LottoNumber(1));
        // var sortedNumbers = List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4), new LottoNumber(5), new LottoNumber(6));

        var unsortedLotto = new Lotto(unsortedNumbers);
        // var sortedLotto = new Lotto(sortedNumbers);

        //assertThat(unsortedLotto.numbers().equals(sortedLotto.numbers()));
        assertThat(unsortedLotto.numbers()).isSorted();
    }
}
