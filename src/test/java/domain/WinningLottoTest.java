package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class WinningLottoTest {

    @Test
    @DisplayName("지난 당첨 번호를 정상적으로 로또 객체로 생성한다.")
    void shouldReturnLotto_whenValidInputGiven() {
        // given
        String input = "1,2,3,4,5,6";

        // when
        WinningLotto winningLotto = new WinningLotto(input);
        List<LottoNumber> numbers = winningLotto.getWinningLotto().getNumbers();

        // then
        assertThat(numbers)
                .hasSize(6)
                .extracting(LottoNumber::number)
                .containsExactlyInAnyOrder(1, 2, 3, 4, 5, 6);
    }

    @Test
    @DisplayName("숫자가 6개보다 적으면 예외가 발생한다.")
    void shouldThrowException_whenLessThanSixNumbers() {
        // given
        String input = "1,2,3,4,5";

        // when & then
        assertThatThrownBy(() -> new WinningLotto(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 6개여야 합니다.");
    }

    @Test
    @DisplayName("숫자가 6개보다 많으면 예외가 발생한다.")
    void shouldThrowException_whenMoreThanSixNumbers() {
        // given
        String input = "1,2,3,4,5,6,7";

        // when & then
        assertThatThrownBy(() -> new WinningLotto(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 6개여야 합니다.");
    }

    @Test
    @DisplayName("중복된 번호가 있을 경우 예외가 발생한다.")
    void shouldThrowException_whenNumbersAreDuplicated() {
        // given
        String input = "1,2,3,4,5,5";

        // when & then
        assertThatThrownBy(() -> new WinningLotto(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 로또 번호가 있습니다.");
    }

    @Test
    @DisplayName("숫자가 아닌 문자열이 포함되면 예외가 발생한다.")
    void shouldThrowException_whenNonNumericInput() {
        // given
        String input = "1,2,삼,4,5,6";

        // when & then
        assertThatThrownBy(() -> new WinningLotto(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("모든 번호는 숫자 형식이어야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"0,2,3,4,5,6", "1,2,3,4,5,77"})
    @DisplayName("범위를 벗어나는 숫자가 포함되면 예외가 발생한다.")
    void shouldThrowException_whenNumberOutOfRange(String input) {
        // given & when & then
        assertThatThrownBy(() -> new WinningLotto(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호는 1 ~ 45 사이여야 합니다.");
    }
}
