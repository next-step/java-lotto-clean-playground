import domain.LottoTicket;
import domain.WinningNumbers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class WinningNumbersTest {

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
        @DisplayName("규칙 위반이면 예외(중복)")
        void throwsWhenDuplicated() {
            assertThatThrownBy(() -> WinningNumbers.of(List.of(1, 2, 3, 4, 5, 5),6))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("번호는 중복될 수 없습니다.");
        }
    }
    @Nested
    @DisplayName("보너스 매칭")
    class BonusMatch {

        @Test
        @DisplayName("티켓이 보너스 번호를 포함하면 bonusMatched는 true")
        void bonusMatchedTrue() {
            WinningNumbers winning = WinningNumbers.of(List.of(1, 2, 3, 4, 5, 6), 7);
            LottoTicket ticket = new LottoTicket(List.of(1, 2, 3, 4, 5, 7));

            assertThat(winning.bonusMatched(ticket)).isTrue();
        }

        @Test
        @DisplayName("티켓이 보너스 번호를 포함하지 않으면 bonusMatched는 false")
        void bonusMatchedFalse() {
            WinningNumbers winning = WinningNumbers.of(List.of(1, 2, 3, 4, 5, 6), 7);
            LottoTicket ticket = new LottoTicket(List.of(1, 2, 3, 4, 5, 8));

            assertThat(winning.bonusMatched(ticket)).isFalse();
        }
    }
}
