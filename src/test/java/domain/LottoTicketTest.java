package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class LottoTicketTest {
    @Test
    @DisplayName("올바른 숫자 입력 시 로또 티켓이 정상적으로 생성된다")
    void createLottoTicket_validNumbers_success() {
        // Given
        String input = "1, 2, 3, 4, 5, 6";

        // When
        LottoTicket ticket = LottoTicket.from(input);

        // Then
        assertThat(ticket.getNumbers()).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  "})
    @DisplayName("입력이 null 또는 빈 문자열일 경우 예외를 던진다")
    void exception_when_input_is_null_or_black(String input){
        // When & Then
        assertThatThrownBy(() -> LottoTicket.from(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("입력된 로또 번호가 비어 있습니다.");
    }

    @Test
    @DisplayName("당첨 번호 및 보너스 번호와의 일치 개수에 따라 Rank를 계산한다")
    void count_match_and_return_correct_rank() {
        // Given
        LottoTicket ticket = LottoTicket.from("1,2,3,4,5,6");
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = new BonusNumber(7);

        // When
        Rank rank = ticket.countMatch(winningNumbers, bonusNumber);

        // Then
        assertThat(rank).isEqualTo(Rank.FIRST);
    }

    @Test
    @DisplayName("번호가 6개가 아닌 경우 예외를 던진다")
    void exception_when_Lotto_count_is_not_6() {
        //Given
        List<Integer> inputNumbers = List.of(1, 2, 5, 6);

        //When & Then
        assertThatThrownBy(() -> new LottoTicket(inputNumbers))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("로또 번호는 6개여야 합니다.");
    }

    @Test
    @DisplayName("로또 번호가 1~45 범위를 벗어나면 예외를 던진다")
    void exception_when_Lotto_number_OutOfRange() {
        //Given
        List<Integer> inputNumbers = List.of(1, 2, 3, 4, 5, 99);

        //When & Then
        assertThatThrownBy(() -> new LottoTicket(inputNumbers))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("로또 번호는 1~45 사이여야 합니다.");
    }

    @Test
    @DisplayName("당첨 번호에 중복된 로또 번호가 입력되면 예외를 던진다")
    void exception_when_putting_Lotto_number_is_Duplicate() {
        //Given
        List<Integer> inputNumbers = List.of(1, 2, 3, 4, 5, 5);

        //When & Then
        assertThatThrownBy(() -> new LottoTicket(inputNumbers))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("중복된 번호는 입력할 수 없습니다.");
    }
}
