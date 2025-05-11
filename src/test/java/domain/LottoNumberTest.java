package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class LottoNumberTest {

    @Nested
    @DisplayName("성공 케이스")
    class SuccessCases {

        @Test
        @DisplayName("1부터 45 사이 값으로 생성할 수 있다")
        void createWithValidValue() {
            assertThatCode(() -> new LottoNumber(1)).doesNotThrowAnyException();
            assertThatCode(() -> new LottoNumber(45)).doesNotThrowAnyException();
            assertThat(new LottoNumber(10).value()).isEqualTo(10);
        }
    }

    @Nested
    @DisplayName("실패 케이스")
    class FailureCases {

        @Test
        @DisplayName("1 미만 값은 예외가 발생한다")
        void lessThanMinThrowsException() {
            assertThatThrownBy(() -> new LottoNumber(0))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 로또 번호는 1부터 45 사이여야 합니다.");
        }

        @Test
        @DisplayName("45 초과 값은 예외가 발생한다")
        void greaterThanMaxThrowsException() {
            assertThatThrownBy(() -> new LottoNumber(46))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 로또 번호는 1부터 45 사이여야 합니다.");
        }
    }
}
