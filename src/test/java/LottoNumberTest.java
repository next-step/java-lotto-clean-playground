import exception.CustomException;
import exception.ErrorMessage;
import model.LottoNumber;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoNumberTest {

    @Test
    public void 정상적인_번호_범위에서_객체_생성시_예외가_발생하지_않는다() {
        assertThatCode(() -> new LottoNumber(1))
            .doesNotThrowAnyException();

        assertThatCode(() -> new LottoNumber("1"))
            .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {"-100", "-1", "46", "100"})
    void 문자열_잘못된_범위로_객체_생성시_예외가_발생한다(String strNumber) {
        assertThatThrownBy(() -> new LottoNumber(strNumber))
            .isInstanceOf(CustomException.class)
            .hasMessage(ErrorMessage.INVALID_NUMBER_RANGE.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {-100, -1, 46, 100})
    void 정수_잘못된_범위로_객체_생성시_예외가_발생한다(int number) {
        assertThatThrownBy(() -> new LottoNumber(number))
            .isInstanceOf(CustomException.class)
            .hasMessage(ErrorMessage.INVALID_NUMBER_RANGE.getMessage());
    }

    @Test
    void 두_객체_내부의_값이_일치하다면_true를_반환한다() {
        LottoNumber a = new LottoNumber(1);
        LottoNumber b = new LottoNumber(1);

        Assertions.assertThat(a).isEqualTo(b);
    }

    @Test
    void 두_객체_내부의_값이_불일치하다면_false를_반환한다() {
        LottoNumber a = new LottoNumber(1);
        LottoNumber b = new LottoNumber(2);

        Assertions.assertThat(a).isNotEqualTo(b);
    }
}
