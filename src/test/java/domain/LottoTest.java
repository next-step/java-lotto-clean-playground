package domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {

    @Test
    @DisplayName("숫자가 주어졌을 경우 정상적으로 객체가 생성된다.")
    void shouldReturnLotto_whenValidNumbers() {
        // given & when & then
        assertThatCode(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6)))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("숫자의 개수가 적을 때 예외가 발생한다.")
    void shouldThrowException_whenLessLottoCount() {
        // given & when & then
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호는 6개여야 합니다.");
    }

    @Test
    @DisplayName("숫자의 개수가 많을 때 예외가 발생한다.")
    void shouldThrowException_whenMoreLottoCount() {
        // given & when & then
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호는 6개여야 합니다.");
    }

    @Test
    @DisplayName("중복된_숫자가_있으면_예외가_발생한다")
    void shouldThrowException_whenDuplicateNumbers() {
        // given & when & then
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 5, 5, 6)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복된 로또 번호가 있습니다.");
    }

    @Test
    @DisplayName("범위보다_작은_숫자가_있으면_예외가_발생한다")
    void shouldThrowException_whenMinRangeNumber() {
        // given & when & then
        assertThatThrownBy(() -> new Lotto(List.of(0, 2, 3, 4, 5, 6)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호는 1 ~ 45 사이의 값이어야 합니다.");
    }

    @Test
    @DisplayName("범위보다_큰_숫자가_있으면_예외가_발생한다")
    void shouldThrowException_whenMaxRangeNumber() {
        // given & when & then
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 46)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호는 1 ~ 45 사이의 값이어야 합니다.");
    }
}
