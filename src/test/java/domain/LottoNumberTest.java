package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class LottoNumberTest {
    @Test
    void 로또_번호가_1보다_작으면_예외를_던진다() {
        assertThatThrownBy(() -> new LottoNumber(0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또_번호가_45보다_크면_예외를_던진다() {
        assertThatThrownBy(() -> new LottoNumber(46))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또_번호가_1이상_45이하라면_정상적으로_생성된다() {
        assertThatCode(() -> new LottoNumber(1))
                .doesNotThrowAnyException();

        assertThatCode(() -> new LottoNumber(45))
                .doesNotThrowAnyException();
    }

    @Test
    void 같은_값의_로또번호는_같은_로또번호로_판단한다() {
        LottoNumber lottoNumber1 = new LottoNumber(1);
        LottoNumber lottoNumber2 = new LottoNumber(1);

        assertThat(lottoNumber1).isEqualTo(lottoNumber2);
    }

    @Test
    void 서로다른_값의_로또번호는_다른_로또번호로_판단한다() {
        LottoNumber lottoNumber1 = new LottoNumber(1);
        LottoNumber lottoNumber2 = new LottoNumber(2);

        assertThat(lottoNumber1).isNotEqualTo(lottoNumber2);
    }
}
