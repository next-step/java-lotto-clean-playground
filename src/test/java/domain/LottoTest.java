package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class LottoTest {

    @Test
    @DisplayName("올바른 숫자 입력 시 로또 티켓이 정상적으로 생성된다")
    void createLottoTicket_validNumbers_success() {
        // Given
        String input = "1, 2, 3, 4, 5, 6";

        // When
        Lotto ticket = Lotto.from(input);

        // Then
        assertThat(ticket.getNumbers()).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  "})
    @DisplayName("입력이 null 또는 빈 문자열일 경우 예외를 던진다")
    void exception_when_input_is_null_or_black(String input) {
        // When & Then
        assertThatThrownBy(() -> Lotto.from(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("입력된 로또 번호가 비어 있습니다.");
    }

    @Test
    @DisplayName("당첨 번호와 비교해 알맞은 Rank를 반환한다")
    void count_match_and_return_correct_rank() {
        // Given
        Lotto ticket = Lotto.from("1,2,3,4,5,6");
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);

        // When
        Rank rank = ticket.countMatch(winningNumbers);

        // Then
        assertThat(rank).isEqualTo(Rank.FIRST);
    }
}
