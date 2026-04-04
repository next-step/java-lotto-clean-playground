package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class LottoNumberTest {
    @DisplayName("1~45의 숫자를 입력하면 예외가 발생하지 않는다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 5, 30, 45})
    public void testValueOf_ValidNumber(int number) {
        // when & then
        assertThatCode(() -> LottoNumber.valueOf(number)).doesNotThrowAnyException();
    }

    @DisplayName("1~45 이외의 숫자를 입력하면 IllegalArgumentException을 발생시킨다.")
    @ParameterizedTest
    @ValueSource(ints = {-10, 0, 46, 100})
    public void testValueOf_InvalidNumber(int number) {
        // when & then
        assertThatThrownBy(() -> LottoNumber.valueOf(number)).isInstanceOf(IllegalArgumentException.class).hasMessage("로또 숫자가 범위를 벗어났습니다.");
    }
}
