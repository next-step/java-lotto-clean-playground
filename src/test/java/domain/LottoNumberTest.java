package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class LottoNumberTest {
    @Test
    public void 로또번호는_1부터_45사이여야_한다() {
        // when & then
        assertThatCode(() -> new LottoNumber(1))
                .doesNotThrowAnyException();

        assertThatCode(() -> new LottoNumber(45))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, 46, 100})
    public void 로또번호가_1부터_45사이가_아니면_예외가_발생한다(int number) {
        // when && then
        assertThatThrownBy(() -> new LottoNumber(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또의 범위는 1~45 사이여야 합니다.");
    }

    @Test
    public void 같은_값을_가진_로또번호는_같은_객체로_판단된다() {
        // given
        LottoNumber firstLottoNumber = new LottoNumber(1);
        LottoNumber secondLottoNumber = new LottoNumber(1);

        // then
        assertThat(firstLottoNumber).isEqualTo(secondLottoNumber);
    }
}
