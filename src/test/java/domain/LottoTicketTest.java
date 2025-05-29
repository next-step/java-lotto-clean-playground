package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTicketTest {
    @Test
    @DisplayName("올바른 숫자 입력 시 로또 티켓이 정상적으로 생성된다")
    void createLottoTicket_validNumbers_success() {
        // Given
        List<Integer> inputNumbers = List.of(1, 2, 3, 4, 5, 6);

        // When
        LottoTicket ticket = new LottoTicket(inputNumbers);

        // Then
        assertThat(ticket.getNumbers()).containsExactlyElementsOf(inputNumbers);
    }

    @Test
    @DisplayName("당첨 번호를 입력시 맞은 개수가 정상적으로 반환된다")
    void correctMatchCount_when_put_winningNumbers() {
        // Given
        LottoTicket lottoTicket = new LottoTicket(List.of(4, 5, 6, 7, 8, 9));
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));

        // When
        Rank matchCount = lottoTicket.countMatch(winningNumbers);

        // Then
        assertThat(matchCount).isEqualTo(3);
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
