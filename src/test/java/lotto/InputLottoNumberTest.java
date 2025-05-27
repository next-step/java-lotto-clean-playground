package lotto;

import lotto.domain.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InputLottoNumberTest {

    @Test
    @DisplayName("로또 번호의 범위는 1 ~ 45 사이")
    void validNumberCreatesLottoNumber() {
        assertDoesNotThrow(() -> new LottoNumber(10));
    }


    @Test
    @DisplayName("로또 번호가 1 ~ 45 사이가 아닌 경우")
    void invalidNumberThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new LottoNumber(0));
        assertThrows(IllegalArgumentException.class, () -> new LottoNumber(46));
    }
}