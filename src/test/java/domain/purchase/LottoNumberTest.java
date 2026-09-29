package domain.purchase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoNumberTest {
    @Test
    @DisplayName("로또 숫자가 1이면 오류가 발생하지 않는다.")
    void SafeWhenLottoNumberIs1() {
        LottoNumber lottoNumber = new LottoNumber(1);
        assertEquals(1, lottoNumber.getValue());
    }

    @Test
    @DisplayName("로또 숫자가 45이면 오류가 발생하지 않는다.")
    void SafeWhenLottoNumberIs45() {
        LottoNumber lottoNumber = new LottoNumber(45);
        assertEquals(45, lottoNumber.getValue());
    }

    @Test
    @DisplayName("로또 숫자가 0이면 오류가 발생한다.")
    void ErrorWhenLottoNumberIs0() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new LottoNumber(0));
        assertEquals("로또의 범위는 1~45 사이여야 합니다.", exception.getMessage());
    }

    @Test
    @DisplayName("로또 숫자가 46이면 오류가 발생한다.")
    void ErrorWhenLottoNumberIs46() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new LottoNumber(46));
        assertEquals("로또의 범위는 1~45 사이여야 합니다.", exception.getMessage());
    }
}
