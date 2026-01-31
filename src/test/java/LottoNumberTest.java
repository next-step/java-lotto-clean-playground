import domain.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.*;

public class LottoNumberTest {

    @Nested
    @DisplayName("예외 처리")
    class Exceptions {
        @Test
        @DisplayName("1~45 범위면 생성된다")
        void createsNormally() {
            LottoNumber one = LottoNumber.of(1);
            LottoNumber fortyFive = LottoNumber.of(45);

            assertThat(one.value()).isEqualTo(1);
            assertThat(fortyFive.value()).isEqualTo(45);
        }

        @Test
        @DisplayName("0이면 예외")
        void throwsWhenZero() {
            assertThatThrownBy(() -> LottoNumber.of(0))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("로또 번호는 1~45 범위여야 합니다.");
        }

        @Test
        @DisplayName("46이면 예외")
        void throwsWhen46() {
            assertThatThrownBy(() -> LottoNumber.of(46))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("로또 번호는 1~45 범위여야 합니다.");
        }
    }
}
