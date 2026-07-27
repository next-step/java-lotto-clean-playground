package domain;

import domain.lotto.LottoNumber;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 45})
    void 최소값과_최대값일때_생성된다(int number) {
        assertDoesNotThrow(() -> new LottoNumber(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    void 범위를_벗어나면_생성되지_않는다(int number) {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new LottoNumber(number)
        );
        assertEquals("로또 번호는 1~45 사이여야 합니다.", exception.getMessage());
    }

    @Test
    void 내부_값이_같으면_같다() {
        LottoNumber number1 = new LottoNumber(7);
        LottoNumber number2 = new LottoNumber(7);

        assertEquals(number1, number2);
    }

    @Test
    void 내부_값이_다르면_다르다() {
        LottoNumber number1 = new LottoNumber(7);
        LottoNumber number2 = new LottoNumber(8);

        assertNotEquals(number1, number2);
    }
}
