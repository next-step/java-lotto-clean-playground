package domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static support.LottoTestHelper.lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {

    @Test
    @DisplayName("숫자가 주어졌을 경우 정상적으로 객체가 생성된다.")
    void shouldReturnLotto_whenValidNumbers() {
        // given & when & then
        assertThatCode(() -> lotto(1, 2, 3, 4, 5, 6))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("숫자의 개수가 적을 때 예외가 발생한다.")
    void shouldThrowException_whenLessLottoCount() {
        // given & when & then
        assertThatThrownBy(() -> lotto(1, 2, 3, 4, 5))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호는 6개여야 합니다.");
    }

    @Test
    @DisplayName("숫자의 개수가 많을 때 예외가 발생한다.")
    void shouldThrowException_whenMoreLottoCount() {
        // given & when & then
        assertThatThrownBy(() -> lotto(1, 2, 3, 4, 5, 6, 7))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호는 6개여야 합니다.");
    }

    @Test
    @DisplayName("중복된 숫자가 있으면 예외가 발생한다.")
    void shouldThrowException_whenDuplicateNumbers() {
        // given & when & then
        assertThatThrownBy(() -> lotto(1, 2, 2, 4, 5, 6))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복된 로또 번호가 있습니다.");
    }
}
