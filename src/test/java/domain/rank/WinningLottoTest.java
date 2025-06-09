package domain.rank;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.lotto.LottoNumber;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class WinningLottoTest {

    @Nested
    @DisplayName("지난 당첨 번호 관련 테스트")
    class LastWeekWinningNumberTest {

        @Test
        @DisplayName("지난 당첨 번호를 정상적으로 로또 객체로 생성한다.")
        void shouldReturnLotto_whenValidInputGiven() {
            // given
            String input = "1,2,3,4,5,6";

            // when
            WinningLotto winningLotto = WinningLotto.of(input, "7");
            List<LottoNumber> numbers = winningLotto.getWinningLotto().getNumbers();

            // then
            assertThat(numbers)
                    .hasSize(6)
                    .extracting(LottoNumber::number)
                    .containsExactlyInAnyOrder(1, 2, 3, 4, 5, 6);
        }

        @ParameterizedTest
        @NullAndEmptySource
        @DisplayName("당첨 번호를 입력하지 않았을 경우 예외가 발생한다.")
        void shouldThrowException_whenEmptyLotto(String input) {
            assertThatThrownBy(() -> WinningLotto.of(input, "7"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("입력값이 비어있을 수 없습니다.");
        }

        @Test
        @DisplayName("숫자가 6개보다 적을 경우 예외가 발생한다.")
        void shouldThrowException_whenLessThanSixNumbers() {
            // given
            String input = "1,2,3,4,5";

            // when & then
            assertThatThrownBy(() -> WinningLotto.of(input, "7"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("로또 번호는 6개여야 합니다.");
        }

        @Test
        @DisplayName("숫자가 6개보다 많을 경우 예외가 발생한다.")
        void shouldThrowException_whenMoreThanSixNumbers() {
            // given
            String input = "1,2,3,4,5,6,7";

            // when & then
            assertThatThrownBy(() -> WinningLotto.of(input, "8"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("로또 번호는 6개여야 합니다.");
        }

        @Test
        @DisplayName("중복된 번호가 있을 경우 예외가 발생한다.")
        void shouldThrowException_whenNumbersAreDuplicated() {
            // given
            String input = "1,2,3,4,5,5";

            // when & then
            assertThatThrownBy(() -> WinningLotto.of(input, "7"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("중복된 로또 번호가 있습니다.");
        }

        @Test
        @DisplayName("숫자가 아닌 문자열이 포함될 경우 예외가 발생한다.")
        void shouldThrowException_whenNonNumericInput() {
            // given
            String input = "1,2,삼,4,5,6";

            // when & then
            assertThatThrownBy(() -> WinningLotto.of(input, "7"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("모든 번호는 숫자여야 합니다.");
        }

        @ParameterizedTest
        @ValueSource(strings = {"0,2,3,4,5,6", "1,2,3,4,5,77"})
        @DisplayName("범위를 벗어나는 숫자가 포함될 경우 예외가 발생한다.")
        void shouldThrowException_whenNumberOutOfRange(String input) {
            // given & when & then
            assertThatThrownBy(() -> WinningLotto.of(input, "7"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("로또 번호는 1 ~ 45 사이여야 합니다.");
        }
    }

    @Nested
    @DisplayName("보너스 번호 관련 테스트")
    class BonusNumberTest {

        @Test
        @DisplayName("정상적인 보너스 번호가 들어올 경우 객체가 정상 생성된다.")
        void shouldCreateWinningLottoWithBonusNumber() {
            // given
            String input = "1,2,3,4,5,6";
            String bonus = "7";

            // when
            WinningLotto winningLotto = WinningLotto.of(input, bonus);

            // then
            assertThat(winningLotto.getBonusNumber().number())
                    .isEqualTo(7);
        }

        @ParameterizedTest
        @NullAndEmptySource
        @DisplayName("보너스 번호를 입력하지 않았을 경우 예외가 발생한다.")
        void shouldThrowException_whenBonusNumberIsEmpty(String bonus) {
            // given
            String input = "1,2,3,4,5,6";

            // when & then
            assertThatThrownBy(() -> WinningLotto.of(input, bonus))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("입력값이 비어있을 수 없습니다.");
        }

        @ParameterizedTest
        @ValueSource(strings = {"0", "46", "100"})
        @DisplayName("보너스 번호가 1~45 범위를 벗어날 경우 예외가 발생한다.")
        void shouldThrowException_whenBonusNumberOutOfRange(String bonus) {
            // given
            String input = "1,2,3,4,5,6";

            // when & then
            assertThatThrownBy(() -> WinningLotto.of(input, bonus))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("로또 번호는 1 ~ 45 사이여야 합니다.");
        }

        @Test
        @DisplayName("보너스 번호가 당첨 번호와 중복될 경우 예외가 발생한다.")
        void shouldThrowException_whenBonusNumberIsDuplicated() {
            // given
            String input = "1,2,3,4,5,6";
            String bonus = "6";

            // when & then
            assertThatThrownBy(() -> WinningLotto.of(input, bonus))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }

    }
}
