import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class LottoTest {

    @Test
    void 로또는_중복되지_않는_6개의_숫자로_이루어져있다() {
        // given
        var numbers = List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        );

        // when & then
        assertThatCode(() -> new Lotto(numbers)).doesNotThrowAnyException();
    }

    @Test
    void 로또는_6개의_숫자로_이루어져야_한다() {
        // given
        var numbers = List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5)
        );

        // when & then
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또는 6개의 숫자로 이루어져야 합니다.");
    }

    @Test
    void 로또는_중복되지_않는_6개의_숫자로_이루어져야_한다() {
        // given
        var numbers = List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(5)
        );

        // when & then
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또는 중복되지 않는 6개의 숫자로 이루어져야 합니다.");
    }

    @Test
    void 로또의_숫자들은_오름차순으로_정렬되어_있다() {
        // given
        var numbers = List.of(
                new LottoNumber(6),
                new LottoNumber(5),
                new LottoNumber(4),
                new LottoNumber(3),
                new LottoNumber(2),
                new LottoNumber(1)
        );

        // when
        var lotto = new Lotto(numbers);

        // then
        assertThat(lotto.numbers()).isSorted();
    }
}
