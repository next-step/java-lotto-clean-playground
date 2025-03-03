package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoNumberTest {

    @Test
    @DisplayName("올바른 로또 번호를 주면 로또 넘버 객체를 생성하는 지 검증한다.")
    void should_Create_Instance_With_Valid_Number() {
        int validNumber = 10;

        LottoNumber lottoNumber = new LottoNumber(validNumber);

        assertThat(lottoNumber)
                .isNotNull()
                .extracting(LottoNumber::getNumber)
                .isEqualTo(validNumber);
    }

    @Test
    @DisplayName("유효하지 않은 로또 번호로 객체 생성 시 예외를 던지는 지 검증한다.")
    void should_Throw_Exception_For_Invalid_Number() {

        int invalidNumberLow = 0;
        int invalidNumberHigh = 46;
        
        assertThatThrownBy(() -> new LottoNumber(invalidNumberLow))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 1~45 사이여야 합니다.");

        assertThatThrownBy(() -> new LottoNumber(invalidNumberHigh))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 1~45 사이여야 합니다.");
    }
}
