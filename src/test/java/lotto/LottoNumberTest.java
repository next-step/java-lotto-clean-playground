package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoNumberTest {

    @Test
    @DisplayName("1부터 45 사이의 로또 번호를 생성한다")
    void createLottoNumber() {
        assertThatCode(() -> new LottoNumber(1)).doesNotThrowAnyException();
        assertThatCode(() -> new LottoNumber(45)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("1부터 45 사이가 아닌 로또 번호는 생성할 수 없다")
    void rejectInvalidLottoNumber() {
        assertThatThrownBy(() -> new LottoNumber(0))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new LottoNumber(46))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("같은 값을 가진 로또 번호는 동일하다")
    void compareLottoNumber() {
        LottoNumber firstNumber = new LottoNumber(7);
        LottoNumber secondNumber = new LottoNumber(7);

        assertThat(firstNumber).isEqualTo(secondNumber);
        assertThat(firstNumber.hashCode()).isEqualTo(secondNumber.hashCode());
    }
}
