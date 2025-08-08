import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RankTest {

    @Test
    void 당첨_순위_조회_테스트() {
        assertEquals(Rank.FIRST, Rank.getRank(6, false));
        assertEquals(Rank.SECOND, Rank.getRank(5, true));
        assertEquals(Rank.THIRD, Rank.getRank(5, false));
        assertEquals(Rank.FOURTH, Rank.getRank(4, false));
        assertEquals(Rank.FIFTH, Rank.getRank(3, false));
        assertEquals(Rank.UNRANK, Rank.getRank(2, false));
    }

    @Test
    void 상금_조회_테스트() {
        assertEquals(2_000_000_000, Rank.FIRST.getReward());
        assertEquals(1_500_000, Rank.THIRD.getReward());
        assertEquals(50_000, Rank.FOURTH.getReward());
        assertEquals(5_000, Rank.FIFTH.getReward());
    }
}
