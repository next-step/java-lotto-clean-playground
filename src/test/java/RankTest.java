import domain.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RankTest {
    @Test
    @DisplayName("6개가 일치하면 1등이다")
    void firstRank() {
        assertEquals(Rank.FIRST, Rank.find(6, false));
    }

    @Test
    @DisplayName("5개가 일치하고 보너스볼이 일치하면 2등이다")
    void secondRank() {
        assertEquals(Rank.SECOND, Rank.find(5, true));
    }

    @Test
    @DisplayName("5개가 일치하고 보너스볼이 일치하지 않으면 3등이다")
    void thirdRank() {
        assertEquals(Rank.THIRD, Rank.find(5, false));
    }

    @Test
    @DisplayName("4개가 일치하면 4등이다")
    void fourthRank() {
        assertEquals(Rank.FOURTH, Rank.find(4, false));
    }

    @Test
    @DisplayName("3개가 일치하면 5등이다")
    void fifthRank() {
        assertEquals(Rank.FIFTH, Rank.find(3, false));
    }

    @Test
    @DisplayName("3개 미만으로 일치하면 당첨되지 않는다")
    void missRank() {
        assertEquals(Rank.MISS, Rank.find(2, false));
    }
}
