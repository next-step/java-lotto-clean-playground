package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LottoNumberTest {

    @Test
    @DisplayName("로또 숫자 하나 반환 테스트")
    void getLottoNumber() {
        // given
        final LottoNumber lottoNumber = new LottoNumber(1);
        final var expected = 1;

        // when
        final int actual = lottoNumber.getLottoNumber();

        // then
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("로또 숫자 범위 예외처리 테스트")
    void getLottoNumber_generate_incorrect_exception() {
        // given
        final int lottoNumber = 47;
        final var expected = "로또 범위 아님";

        // when
        final RuntimeException actual = assertThrows(RuntimeException.class, () -> new LottoNumber(lottoNumber));

        // then
        assertEquals(expected, actual.getMessage());
    }
}
