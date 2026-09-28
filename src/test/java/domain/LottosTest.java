package domain;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class LottosTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6})
    void countsTicketAtMatchingIndex(int matchCount) {
        // 준비
        Lottos lottos = new Lottos(List.of(createTicket(matchCount)));
        // 실행
        List<Integer> result = lottos.getMatchCount(new Lotto(List.of(6, 5, 4, 3, 2, 1)));
        // 검증
        assertThat(result).containsExactlyElementsOf(expectedCounts(matchCount));
    }

    @Test
    void accumulatesTicketsWithSameMatchCount() {
        // 준비
        Lottos lottos = new Lottos(List.of(createTicket(3), createTicket(3), createTicket(6)));
        // 실행
        List<Integer> result = lottos.getMatchCount(new Lotto(List.of(1, 2, 3, 4, 5, 6)));
        // 검증
        assertThat(result).containsExactly(0, 0, 0, 2, 0, 0, 1);
    }

    @Test
    void returnsZeroCountsForEmptyTickets() {
        // 준비
        Lottos lottos = new Lottos(List.of());
        // 실행
        List<Integer> result = lottos.getMatchCount(new Lotto(List.of(1, 2, 3, 4, 5, 6)));
        // 검증
        assertThat(result).containsExactly(0, 0, 0, 0, 0, 0, 0);
    }

    private Lotto createTicket(int matchCount) {
        List<Integer> numbers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6).subList(0, matchCount));
        numbers.addAll(List.of(7, 8, 9, 10, 11, 12).subList(0, 6 - matchCount));
        return new Lotto(numbers);
    }

    private List<Integer> expectedCounts(int matchCount) {
        List<Integer> counts = new ArrayList<>(List.of(0, 0, 0, 0, 0, 0, 0));
        counts.set(matchCount, 1);
        return counts;
    }
}
