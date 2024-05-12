package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoNumberTest {

    @Test
    @DisplayName("1~45외 숫자가 들어가면 예외가 발생한다.")
    public void LottoNumber_객체는_1부터_45까지의_수만_가질수있다() {
        assertThrows(IllegalArgumentException.class, this::causeException);
    }

    private void causeException() {
        LottoNumber lottoNumber = new LottoNumber(0);
    }
}