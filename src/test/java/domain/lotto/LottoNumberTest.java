package domain.lotto;

import exception.LottoNumberRangeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoNumberTest {

    @DisplayName("로또 번호는 1부터 45 사이의 값을 가진다")
    @ParameterizedTest
    @ValueSource(ints = {1, 23, 45})
    void createLottoNumber(int number) {
        // given & when
        LottoNumber lottoNumber = new LottoNumber(number);

        // then
        assertThat(lottoNumber.getValue()).isEqualTo(number);
    }

    @DisplayName("로또 번호가 1보다 작으면 예외가 발생한다")
    @Test
    void throwExceptionWhenNumberLessThanMin() {
        // given
        int invalidNumber = 0;

        // when & then
        assertThatThrownBy(() -> new LottoNumber(invalidNumber))
                .isInstanceOf(LottoNumberRangeException.class);
    }

    @DisplayName("로또 번호가 45보다 크면 예외가 발생한다")
    @Test
    void throwExceptionWhenNumberGreaterThanMax() {
        // given
        int invalidNumber = 46;

        // when & then
        assertThatThrownBy(() -> new LottoNumber(invalidNumber))
                .isInstanceOf(LottoNumberRangeException.class);
    }

    @DisplayName("같은 값을 가진 로또 번호는 동등하다")
    @Test
    void equalLottoNumbers() {
        // given
        LottoNumber number1 = new LottoNumber(7);
        LottoNumber number2 = new LottoNumber(7);

        // when & then
        assertThat(number1).isEqualTo(number2);
    }
}
