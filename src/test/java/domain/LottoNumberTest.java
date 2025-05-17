package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    @DisplayName("범위보다_작은_숫자가_있으면_예외가_발생한다")
    void shouldThrowException_whenMinRangeNumber(int number) {
        // given & when & then
        assertThatThrownBy(() -> new LottoNumber(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호는 1 ~ 45 사이여야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {46, 50})
    @DisplayName("범위보다_큰_숫자가_있으면_예외가_발생한다")
    void shouldThrowException_whenMaxRangeNumber(int number) {
        // given & when & then
        assertThatThrownBy(() -> new LottoNumber(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호는 1 ~ 45 사이여야 합니다.");
    }
}
