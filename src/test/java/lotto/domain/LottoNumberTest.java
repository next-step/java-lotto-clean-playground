package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
public class LottoNumberTest {
    @Test
    void 숫자를_생성할_수_있다() {
        assertThatCode(() -> new LottoNumber(1))
                .doesNotThrowAnyException();

        assertThatCode(() -> new LottoNumber(23))
                .doesNotThrowAnyException();

        assertThatCode(() -> new LottoNumber(45))
                .doesNotThrowAnyException();
    }

    @Test
    void 숫자가_범위를_벗어나면_안된다() {
        assertThatThrownBy(() -> new LottoNumber(0))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> new LottoNumber(46))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또_번호는_비교할_수_있다() {
        assertThat(new LottoNumber(6).compareTo(new LottoNumber(8)))
                .isEqualTo(-1);

        assertThat(new LottoNumber(25).compareTo(new LottoNumber(14)))
                .isEqualTo(1);

        assertThat(new LottoNumber(36).compareTo(new LottoNumber(36)))
                .isEqualTo(0);
    }

    @Test
    void 로또_번호는_문자열로_변환할_수_있다() {
        assertThat(new LottoNumber(43).format())
                .isEqualTo("43");
    }
}
