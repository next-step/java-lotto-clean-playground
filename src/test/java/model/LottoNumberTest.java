package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoNumberTest {

    @ParameterizedTest
    @DisplayName("최소치보다 작은 숫자를 전달시 예외가 발생한다")
    @ValueSource(ints = {Integer.MIN_VALUE, -1000, -100, -10, -1, 0})
    void ifLessThanMinimumThenThrowException(int illegalNumber) {
        assertThatThrownBy(() -> new LottoNumber(illegalNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("최대치보다 높은 숫자를 전달시 예외가 발생한다")
    @ValueSource(ints = {46, 100, 999, 15000, Integer.MAX_VALUE})
    void ifBiggerThanMaximumThenThrowException(int illegalNumber) {
        assertThatThrownBy(() -> new LottoNumber(illegalNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
