package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LottoNumberTest {
    @Test
    @DisplayName("1~45 범위의 숫자라면 LottoNumber 객체가 정상 생성된다")
    void validNumberCreatesLottoNumber() {
        assertDoesNotThrow(() -> new LottoNumber(10));
    }

    @Test
    @DisplayName("숫자가 1 미만 또는 45 초과일 경우 예외가 발생한다")
    void invalidNumberThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new LottoNumber(0));
        assertThrows(IllegalArgumentException.class, () -> new LottoNumber(46));
    }
}