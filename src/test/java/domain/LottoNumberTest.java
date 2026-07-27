package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoNumberTest {

    @Test
    void 최솟값이면_생성된다() {
        assertDoesNotThrow(() -> new LottoNumber(1));
    }

    @Test
    void 최댓값이면_생성된다() {
        assertDoesNotThrow(() -> new LottoNumber(45));
    }

    @Test
    void 최솟값보다_작으면_생성되지_않는다() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new LottoNumber(0)
        );
        assertEquals("로또 번호는 1~45 사이여야 합니다.", exception.getMessage());
    }

    @Test
    void 최대값보다_크면_생성되지_않는다() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new LottoNumber(46)
        );
        assertEquals("로또 번호는 1~45 사이여야 합니다.", exception.getMessage());
    }

    @Test
    void 내부_값이_같으면_같다() {
        // Given
        LottoNumber number1 = new LottoNumber(7);
        LottoNumber number2 = new LottoNumber(7);

        // When & Then
        assertEquals(number1, number2);
    }

    @Test
    void 내부_값이_다르면_다르다() {
        // Given
        LottoNumber number1 = new LottoNumber(7);
        LottoNumber number2 = new LottoNumber(8);

        // When & Then
        assertNotEquals(number1, number2);
    }
}
