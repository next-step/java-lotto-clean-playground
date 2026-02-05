import domain.Lotto;
import domain.LottoNumber;
import domain.LottoTicket;
import domain.WinningNumbers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class WinningNumbersTest {
    private LottoTicket ticket(int... nums) {
        List<LottoNumber> numbers = new ArrayList<>();
        for (int n : nums) {
            numbers.add(LottoNumber.of(n));
        }
        return new LottoTicket(new Lotto(numbers));
    }

    @Nested
    @DisplayName("생성")
    class createWinningNumbers {

        @Test
        @DisplayName("정상 번호면 생성")
        void createsNormally() {
            assertThatCode(() -> WinningNumbers.of(List.of(1, 2, 3, 4, 5, 6),7))
                    .doesNotThrowAnyException();
        }

        @Test
        @DisplayName("당첨번호에 중복이 있으면 예외")
        void throwsWhenWinningNumbersDuplicated() {
            assertThatThrownBy(() -> WinningNumbers.of(List.of(1, 2, 3, 4, 5, 5), 6))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("로또 번호는 중복될 수 없습니다.");
        }

        @Test
        @DisplayName("보너스가 당첨번호와 중복되면 예외")
        void throwsWhenBonusDuplicatedWithWinning() {
            assertThatThrownBy(() -> WinningNumbers.of(List.of(1, 2, 3, 4, 5, 6), 6))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }

        @Test
        @DisplayName("보너스가 0이면 예외")
        void throwsWhenBonusIsZero() {
            assertThatThrownBy(() -> WinningNumbers.of(List.of(1, 2, 3, 4, 5, 6), 0))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("로또 번호는 1~45 범위여야 합니다.");
        }

        @Test
        @DisplayName("보너스가 46이면 예외")
        void throwsWhenBonusIs46() {
            assertThatThrownBy(() -> WinningNumbers.of(List.of(1, 2, 3, 4, 5, 6), 46))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("로또 번호는 1~45 범위여야 합니다.");
        }
    }
    @Nested
    @DisplayName("보너스 번호의 매치 여부")
    class BonusMatch {

        @Test
        @DisplayName("티켓이 보너스 번호를 포함하면 bonusMatched는 true")
        void bonusMatchedTrue() {
            WinningNumbers winning = WinningNumbers.of(List.of(1, 2, 3, 4, 5, 6), 7);
            LottoTicket ticket = ticket(1, 2, 3, 4, 5, 7);

            assertThat(winning.bonusMatched(ticket)).isTrue();
        }

        @Test
        @DisplayName("티켓이 보너스 번호를 포함하지 않으면 bonusMatched는 false")
        void bonusMatchedFalse() {
            WinningNumbers winning = WinningNumbers.of(List.of(1, 2, 3, 4, 5, 6), 7);
            LottoTicket ticket = ticket(1, 2, 3, 4, 5, 8);

            assertThat(winning.bonusMatched(ticket)).isFalse();
        }
    }
}
