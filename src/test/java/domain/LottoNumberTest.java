package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class LottoNumberTest {

    @Test
    @DisplayName("1부터 45 사이 값으로 생성할 수 있다")
    void createWithValidValue() {
        LottoNumber number1 = new LottoNumber(1);
        LottoNumber number45 = new LottoNumber(45);

        assertThat(number1.value()).isEqualTo(1);
        assertThat(number45.value()).isEqualTo(45);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    @DisplayName("1 미만 또는 45 초과 값은 예외가 발생한다")
    void outOfRangeThrowsException(int invalidValue) {
        assertThatThrownBy(() -> new LottoNumber(invalidValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 번호는 1부터 45 사이여야 합니다.");
    }
}
