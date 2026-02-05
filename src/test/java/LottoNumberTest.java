import domain.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("LottoNumber 예외처리")
public class LottoNumberTest {

    @Test
    @DisplayName("1~45 범위면 생성된다")
    void createsNormally() {
        assertThatCode(() -> LottoNumber.of(1))
                .doesNotThrowAnyException();
        assertThatCode(() -> LottoNumber.of(45))
                .doesNotThrowAnyException();
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
