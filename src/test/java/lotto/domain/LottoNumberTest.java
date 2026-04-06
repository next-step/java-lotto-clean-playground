package lotto.domain;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LottoNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 45})
    void 로또_번호는_1에서_45_사이의_숫자여야_한다(int value) {
        // given & when
        LottoNumber lottoNumber = new LottoNumber(value);

        // then
        assertThat(lottoNumber).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    void 로또_번호_범위를_벗어나면_예외가_발생한다(int value) {
        assertThatThrownBy(() -> new LottoNumber(value))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 숫자가_같으면_같은_객체로_인식한다() {
        // given
        LottoNumber number1 = new LottoNumber(10);
        LottoNumber number2 = new LottoNumber(10);

        // then
        assertThat(number1).isEqualTo(number2);
        assertThat(number1.hashCode()).isEqualTo(number2.hashCode());
    }
}
