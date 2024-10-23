package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoNumberTest {

    @Test
    @DisplayName("유효한 로또 번호는 성공적으로 생성되어야 한다.")
    public void generateValidLottoNumber() {
        LottoNumber lottoNumber = new LottoNumber(5);
        assertThat(lottoNumber.getNumber()).isEqualTo(5);
    }

    @Test
    @DisplayName("유효하지 않은 범위의 로또 번호를 생성할 경우 예외가 발생해야 한다.")
    public void generateInvalidLottoNumber_throwsException() {
        assertThatThrownBy(() -> new LottoNumber(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호 범위에서 벗어났습니다. (로또 번호 범위: 1~45)");

        assertThatThrownBy(() -> new LottoNumber(46))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호 범위에서 벗어났습니다. (로또 번호 범위: 1~45)");
    }

    @Test
    @DisplayName("같은 값을 가진 로또 번호는 동일해야 한다.")
    public void equalsSameNumber_returnsTrue() {
        LottoNumber lottoNumber1 = new LottoNumber(10);
        LottoNumber lottoNumber2 = new LottoNumber(10);
        assertThat(lottoNumber1).isEqualTo(lottoNumber2);
    }

    @Test
    @DisplayName("다른 값을 가진 로또 번호는 동일하지 않아야 한다.")
    public void equalsDifferentNumber_returnsFalse() {
        LottoNumber lottoNumber1 = new LottoNumber(10);
        LottoNumber lottoNumber2 = new LottoNumber(20);
        assertThat(lottoNumber1).isNotEqualTo(lottoNumber2);
    }

}
