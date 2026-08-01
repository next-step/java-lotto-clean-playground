package domain.number;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoNumberTest {

    @Test
    @DisplayName("입력된 로또 번호가 1보다 작으면 예외가 발생한다")
    void throwExceptionWhenNumberIsLessThanOne() {
        assertThatThrownBy(() -> LottoNumber.from(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 1부터 45 사이여야 합니다.");
    }

    @Test
    @DisplayName("입력된 로또 번호가 45보다 크면 예외가 발생한다")
    void throwExceptionWhenNumberIsGreaterThanFortyFive() {
        assertThatThrownBy(() -> LottoNumber.from(46))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 1부터 45 사이여야 합니다.");
    }
}
