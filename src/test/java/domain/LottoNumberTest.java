package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoNumberTest {

    @DisplayName("1~45 범위를 벗어나는 숫자로 로또 번호를 생성하면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 46, -5, 100})
    void invalidNumberTest(int invalidNumber) {
        assertThatThrownBy(() -> new LottoNumber(invalidNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("범위를 벗어났습니다");
    }

    @DisplayName("숫자가 같으면 동등한 객체로 취급한다. (equals & hashCode 재정의 확인)")
    @Test
    void equalsTest() {
        LottoNumber number1 = new LottoNumber(10);
        LottoNumber number2 = new LottoNumber(10);

        assertThat(number1).isEqualTo(number2);
        assertThat(number1.hashCode()).isEqualTo(number2.hashCode());
    }
}