package domain.lotto;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WinningResultTest {
    @Test
    void 등수별_당첨_개수를_정확히_계산한다() {
        //Given
        WinningResult winningResult = new WinningResult(
                List.of(Rank.THREE, Rank.THREE, Rank.FOUR, Rank.NONE)
        );

        //When
        Map<Rank, Integer> result = winningResult.countByRank();

        //Then
        assertEquals(2, result.get(Rank.THREE));
        assertEquals(1, result.get(Rank.FOUR));
        assertEquals(1, result.get(Rank.NONE));
        assertEquals(0, result.get(Rank.FIVE));
        assertEquals(0, result.get(Rank.SECOND));
        assertEquals(0, result.get(Rank.SIX));
    }

    @Test
    void 당첨된_등수가_없으면_모두_0이다() {
        // Given
        WinningResult winningResult = new WinningResult(List.of());

        // When
        Map<Rank, Integer> result = winningResult.countByRank();

        // Then
        for (Rank rank : Rank.values()) {
            assertEquals(0, result.get(rank));
        }
    }
}
