package domain;

import exception.lotto.InvalidLottoNumberRangeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoNumberTest {
    @ParameterizedTest(name = "숫자가 {0}일때 throws InvalidLottoNumberRangeException")
    @DisplayName("로또 숫자 범위가 1~45가 아니면 예외가 발생한다.")
    @ValueSource(ints = {0, 46})
    void whenInputNumberIsNotBetween1And45_thenThrowsException(int number) {
        assertThatThrownBy(() -> new LottoNumber(number)).isInstanceOf(InvalidLottoNumberRangeException.class);
    }

    @ParameterizedTest(name = "숫자가 {0}일때 LottoNumber를 생성한다.")
    @DisplayName("로또 숫자 범위가 1~45가 이면 LottoNumber 객체가 생성된다.")
    @ValueSource(ints = {1, 45})
    void whenInputNumberIsBetween1And45_thenCreateLottoNumber(int number) {
        assertThatCode(() -> new LottoNumber(number)).doesNotThrowAnyException();
    }
}
