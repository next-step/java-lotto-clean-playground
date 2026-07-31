package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {
    @ParameterizedTest
    @ValueSource(ints = {1, 45})
    @DisplayName("로또 번호가 1 이상 45 이하이면 생성할 수 있다")
    void createLottoNumberWithinValidRange(int value) {
        LottoNumber lottoNumber = new LottoNumber(value);

        assertEquals(value, lottoNumber.getNumber());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    @DisplayName("로또 번호가 유효한 범위를 벗어나면 예외가 발생한다")
    void throwsExceptionWhenLottoNumberIsOutOfRange(int value) {
        assertThrows(
                IllegalArgumentException.class,
                () -> new LottoNumber(value)
        );
    }
}
