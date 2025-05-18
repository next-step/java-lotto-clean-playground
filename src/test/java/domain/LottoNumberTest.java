package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class LottoNumberTest {

    @Test
    @DisplayName("1부터 45 사이 값으로 생성할 수 있다")
    void createWithValidValue() {
        assertThatCode(() -> new LottoNumber(1)).doesNotThrowAnyException();
        assertThatCode(() -> new LottoNumber(45)).doesNotThrowAnyException();
        assertThat(new LottoNumber(10).value()).isEqualTo(10);
    }

    @Test
    @DisplayName("1 미만 값은 예외가 발생한다")
    void lessThanMinThrowsException() {
        assertThatThrownBy(() -> new LottoNumber(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 번호는 1부터 45 사이여야 합니다.");
    }

    @Test
    @DisplayName("45 초과 값은 예외가 발생한다")
    void greaterThanMaxThrowsException() {
        assertThatThrownBy(() -> new LottoNumber(46))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 번호는 1부터 45 사이여야 합니다.");
    }
}
