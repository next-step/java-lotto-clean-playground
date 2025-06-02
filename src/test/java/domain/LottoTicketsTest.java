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
        Lotto first = Lotto.from("1,2,3,4,5,6");  // 1등
        Lotto second = Lotto.from("1,2,3,4,5,7"); // 2등 (보너스 포함)
        Lotto third = Lotto.from("1,2,3,4,5,8");  // 3등
        Lotto fourth = Lotto.from("1,2,3,4,8,9"); // 4등
        Lotto fifth = Lotto.from("1,2,3,9,10,11"); // 5등
        Lotto none = Lotto.from("11,12,13,14,15,16"); // 낙첨

        List<Lotto> tickets = List.of(first, second, third, fourth, fifth, none);
        LottoTickets lottoTickets = new LottoTickets(tickets);

        WinningNumbers winningNumbers = new WinningNumbers(
            List.of(1, 2, 3, 4, 5, 6), 7
        );

        // When
        MatchResult result = lottoTickets.countMatchResults(winningNumbers);

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
}
