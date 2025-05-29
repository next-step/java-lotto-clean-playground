package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTicketsTest {
    @Test
    @DisplayName("당첨 번호와 비교하여 일치 개수별로 결과를 집계한다")
    void return_CorrectMatchCount_Map_compare_With_WinningNumbers() {
        // Given
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
        List<LottoTicket> tickets = List.of(
            new LottoTicket(List.of(1, 2, 3, 4, 5, 6)),  // 6개 일치
            new LottoTicket(List.of(1, 2, 3, 7, 8, 9)),  // 3개 일치
            new LottoTicket(List.of(10, 11, 12, 13, 14, 15)) // 0개 일치
        );
        LottoTickets lottoTickets = new LottoTickets(tickets);

        // When
        MatchResult results = lottoTickets.countMatchResults(winningNumbers);

        // Then
        assertThat(results.getCount(Rank.FIRST)).isEqualTo(1); // 6개 일치한 건 1개
        assertThat(results.getCount(Rank.FOURTH)).isEqualTo(1); // 3개 일치한 건 1개
        assertThat(results.getCount(Rank.NONE)).isEqualTo(1); // 0개 일치한 건 1개
    }
}
