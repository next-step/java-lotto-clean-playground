package domain;

import static domain.LottoNumber.MAX_NUMBER;
import static domain.LottoNumber.MIN_NUMBER;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {
    @DisplayName("MIN_NUMBER~MAX_NUMBER의 숫자를 입력하면 예외가 발생하지 않는다.")
    @ParameterizedTest
    @ValueSource(ints = {MIN_NUMBER, MAX_NUMBER})
    public void testValueOf_ValidNumber(int number) {
        // when & then
        assertThatCode(() -> LottoNumber.valueOf(number)).doesNotThrowAnyException();
    }

    @DisplayName("MIN_NUMBER~MAX_NUMBER 이외의 숫자를 입력하면 IllegalArgumentException을 발생시킨다.")
    @ParameterizedTest
    @ValueSource(ints = {MIN_NUMBER - 1, MAX_NUMBER + 1})
    public void testValueOf_InvalidNumber(int number) {
        // when & then
        assertThatThrownBy(() -> LottoNumber.valueOf(number)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 숫자가 범위를 벗어났습니다.");
    }
}
