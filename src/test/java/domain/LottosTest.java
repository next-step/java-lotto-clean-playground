package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottosTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6})
    void countsTicketAtMatchingIndex(int matchCount) {
        // 준비
        Lottos lottos = new Lottos(List.of(createTicket(matchCount)));
        // 실행
        Map<Rank, Integer> result = lottos.getRankCount(new WinningLotto(List.of(6, 5, 4, 3, 2, 1),7));
        // 검증
        Map<Rank, Integer> expected = zeroCounts();
        expected.put(Rank.from(matchCount,false), 1);
        assertThat(result).isEqualTo(expected);
    }
    @Test
    void countsSecondPlaceWhenBonusMatches() {
        // 준비
        Lotto secondTicket = Lotto.from(List.of(1, 2, 3, 4, 5, 7));
        Lottos lottos = new Lottos(List.of(secondTicket));

        // 실행
        Map<Rank, Integer> result = lottos.getRankCount(new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7));

        // 검증
        Map<Rank, Integer> expected = zeroCounts();
        expected.put(Rank.SECOND, 1);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void accumulatesTicketsWithSameMatchCount() {
        // 준비
        Lottos lottos = new Lottos(List.of(createTicket(3), createTicket(3), createTicket(6)));
        // 실행
        Map<Rank, Integer> result = lottos.getRankCount(new WinningLotto(List.of(1, 2, 3, 4, 5, 6),7));
        // 검증
        Map<Rank, Integer> expected = zeroCounts();
        expected.put(Rank.FIFTH, 2);
        expected.put(Rank.FIRST, 1);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void returnsZeroCountsForEmptyTickets() {
        // 준비
        Lottos lottos = new Lottos(List.of());
        // 실행
        Map<Rank, Integer> result = lottos.getRankCount(new WinningLotto(List.of(1, 2, 3, 4, 5, 6),7));
        // 검증
        assertThat(result).isEqualTo(zeroCounts());
    }

    private Lotto createTicket(int matchCount) {
        List<Integer> numbers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6).subList(0, matchCount));
        numbers.addAll(List.of(8, 9, 10, 11, 12, 13).subList(0, 6 - matchCount));
        return Lotto.from(numbers);
    }

    private Map<Rank, Integer> zeroCounts() {
        Map<Rank, Integer> counts = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            counts.put(rank, 0);
        }
        return counts;
    }
}
