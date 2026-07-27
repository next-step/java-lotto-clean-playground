package domain;

import domain.lotto.Rank;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RankTest {
    @ParameterizedTest
    @CsvSource({
            "0, false, NONE",
            "1, false, NONE",
            "2, false, NONE",
            "3, false, THREE",
            "4, false, FOUR",
            "5, false, FIVE",
            "5, true, SECOND",
            "6, false, SIX",
            "6, true, SIX",
            "7, false, NONE"
    })
    void 일치_개수와_보너스_일치_여부에_따라_등수를_반환한다(int matchCount, boolean bonusMatched, Rank expected) {
        assertEquals(expected, Rank.findByMatchCount(matchCount, bonusMatched));
    }
}
