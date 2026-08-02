package domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoNumberTest {
    @Test
    @DisplayName("create lotto number successfully")
    void createLottoNumberSuccessfully() {
        assertThatCode(() -> new LottoNumber(7))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("number is below minimum")
    void exceptionBelowMinimum() {
        assertThatThrownBy(() -> new LottoNumber(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 1부터 45 사이의 숫자여야 합니다.");
    }

    @Test
    @DisplayName("number exceeds maximum")
    void exceptionAboveMaximum() {
        assertThatThrownBy(() -> new LottoNumber(46))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 1부터 45 사이의 숫자여야 합니다.");
    }

    @Test
    @DisplayName("same number lotto numbers are equal")
    void sameNumberLottoNumbersAreEqual() {
        assertThat(new LottoNumber(7)).isEqualTo(new LottoNumber(7));
    }
}
