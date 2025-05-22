package domain.lotto;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    @DisplayName("범위보다 작은 숫자가 있으면 예외가 발생한다.")
    void shouldThrowException_whenMinRangeNumber(int number) {
        // given & when & then
        assertThatThrownBy(() -> LottoNumber.of(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 1 ~ 45 사이여야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {46, 50})
    @DisplayName("범위보다 큰 숫자가 있으면 예외가 발생한다.")
    void shouldThrowException_whenMaxRangeNumber(int number) {
        // given & when & then
        assertThatThrownBy(() -> LottoNumber.of(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 1 ~ 45 사이여야 합니다.");
    }
}
