package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RankTest {
    @Test
    void 일치하는_갯수와_보너스_번호의_일치_여부에_따라_해당하는_등수를_반환한다() {
        assertEquals(Rank.NONE, Rank.findByMatchCount(0, false));
        assertEquals(Rank.NONE, Rank.findByMatchCount(1, false));
        assertEquals(Rank.NONE, Rank.findByMatchCount(2, false));
        assertEquals(Rank.THREE, Rank.findByMatchCount(3, false));
        assertEquals(Rank.FOUR, Rank.findByMatchCount(4, false));
        assertEquals(Rank.FIVE, Rank.findByMatchCount(5, false));
        assertEquals(Rank.SECOND, Rank.findByMatchCount(5, true));
        assertEquals(Rank.SIX, Rank.findByMatchCount(6, false));
        assertEquals(Rank.SIX, Rank.findByMatchCount(6, true));
        assertEquals(Rank.NONE, Rank.findByMatchCount(7, false));
    }
}
