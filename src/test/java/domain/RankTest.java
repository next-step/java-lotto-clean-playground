package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RankTest {
    @Test
    void 일치하는_숫자의_갯수에_해당하는_등수를_반환한다() {
        assertEquals(Rank.NONE, Rank.findByMatchCount(0));
        assertEquals(Rank.NONE, Rank.findByMatchCount(1));
        assertEquals(Rank.NONE, Rank.findByMatchCount(2));
        assertEquals(Rank.THREE, Rank.findByMatchCount(3));
        assertEquals(Rank.FOUR, Rank.findByMatchCount(4));
        assertEquals(Rank.FIVE, Rank.findByMatchCount(5));
        assertEquals(Rank.SIX, Rank.findByMatchCount(6));
        assertEquals(Rank.NONE, Rank.findByMatchCount(7));
    }
}
