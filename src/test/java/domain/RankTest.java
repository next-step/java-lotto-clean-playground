package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {

    @DisplayName("일치 개수와 보너스 일치 여부에 따라 정확한 등수를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {
            "6, false, FIRST",
            "5, true, SECOND",
            "5, false, THIRD",
            "4, false, FOURTH"
    })
    void rankOfTest(int matchCount, boolean matchBonus, Rank expectedRank) {
        assertThat(Rank.of(matchCount, matchBonus)).isEqualTo(expectedRank);
    }

    @DisplayName("일치하는 개수가 0, 1, 2개일 경우 모두 MISS(꽝)를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"0", "1", "2"})
    void valueOfMissTest(int matchCount) {
        assertThat(Rank.valueOf(matchCount, false)).isEqualTo(Rank.MISS);
        assertThat(Rank.valueOf(matchCount, true)).isEqualTo(Rank.MISS);
    }
}