package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RankTest {
    @ParameterizedTest
    @CsvSource({
            "6, false, FIRST",
            "5, true, SECOND",
            "5, false, THIRD",
            "4, false, FOURTH",
            "4, true, FOURTH",
            "3, false, FIFTH",
            "3, true, FIFTH",
            "2, true, MISS",
            "0, false, MISS"
    })
    void returnsRankByMatchCountAndBonus(int matchCount, boolean matchBonus, Rank expected) {
        assertThat(Rank.from(matchCount, matchBonus)).isEqualTo(expected);
    }
}
