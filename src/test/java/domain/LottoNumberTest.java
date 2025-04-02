package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class LottoNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 23, 45})
    @DisplayName("로또 번호가 1부터 45까지의 범위 이내라면 객체가 생성된다.")
    void range_validation_pass_test(int number) {
        assertDoesNotThrow(() -> new LottoNumber(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 46, -5})
    @DisplayName("로또 번호가 1부터 45의 범위를 벗어나면 예외가 발생한다.")
    void range_validation_fail_test(int number) {
        assertThrows(IllegalArgumentException.class, () -> new LottoNumber(number));
    }
}