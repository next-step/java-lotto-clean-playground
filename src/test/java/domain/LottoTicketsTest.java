package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTicketsTest {

    @Test
    @DisplayName("여러 로또 티켓의 당첨 결과를 정확하게 집계한다")
    void count_match_results_correctly() {
        // Given
        LottoTicket first = LottoTicket.from("1,2,3,4,5,6");  // 1등
        LottoTicket second = LottoTicket.from("1,2,3,4,5,7"); // 2등 (보너스 포함)
        LottoTicket third = LottoTicket.from("1,2,3,4,5,8");  // 3등
        LottoTicket fourth = LottoTicket.from("1,2,3,4,8,9"); // 4등
        LottoTicket fifth = LottoTicket.from("1,2,3,9,10,11"); // 5등
        LottoTicket none = LottoTicket.from("11,12,13,14,15,16"); // 낙첨

        List<LottoTicket> tickets = List.of(first, second, third, fourth, fifth, none);
        LottoTickets lottoTickets = new LottoTickets(tickets);

        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = new BonusNumber(7);

        // When
        MatchResult result = lottoTickets.countMatchResults(winningNumbers, bonusNumber);

        // Then
        assertAll(
            () -> assertThat(result.getCount(Rank.FIRST)).isEqualTo(1),
            () -> assertThat(result.getCount(Rank.SECOND)).isEqualTo(1),
            () -> assertThat(result.getCount(Rank.THIRD)).isEqualTo(1),
            () -> assertThat(result.getCount(Rank.FOURTH)).isEqualTo(1),
            () -> assertThat(result.getCount(Rank.FIFTH)).isEqualTo(1),
            () -> assertThat(result.getCount(Rank.NONE)).isEqualTo(1)
        );
    }

    @Test
    @DisplayName("같은 번호를 가진 티켓이 여러 장일 때도 각각 집계된다")
    void same_number_tickets_are_counted_individually() {
        // Given
        LottoTicket duplicate1 = LottoTicket.from("1,2,3,4,5,6");
        LottoTicket duplicate2 = LottoTicket.from("1,2,3,4,5,6");
        LottoTickets lottoTickets = new LottoTickets(List.of(duplicate1, duplicate2));

        WinningNumbers winningNumbers = new WinningNumbers(List.of(1,2,3,4,5,6));
        BonusNumber bonusNumber = new BonusNumber(7);

        // When
        MatchResult result = lottoTickets.countMatchResults(winningNumbers, bonusNumber);

        // Then
        assertThat(result.getCount(Rank.FIRST)).isEqualTo(2);
    }

    @Test
    @DisplayName("당첨 결과가 하나도 없을 경우 MatchResult는 모두 0 또는 NONE만 포함한다")
    void all_losing_tickets_should_return_none_rank() {
        // Given
        LottoTicket none1 = LottoTicket.from("11,12,13,14,15,16");
        LottoTicket none2 = LottoTicket.from("17,18,19,20,21,22");
        LottoTickets lottoTickets = new LottoTickets(List.of(none1, none2));

        WinningNumbers winningNumbers = new WinningNumbers(List.of(1,2,3,4,5,6));
        BonusNumber bonusNumber = new BonusNumber(7);

        // When
        MatchResult result = lottoTickets.countMatchResults(winningNumbers, bonusNumber);

        // Then
        assertThat(result.getCount(Rank.NONE)).isEqualTo(2);
    }
}
