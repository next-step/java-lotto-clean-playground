package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {

    @DisplayName("일치하는 개수에 따라 정확한 등수(Rank)를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"6:SIX", "5:FIVE", "4:FOUR", "3:THREE"}, delimiter = ':')
    void valueOfMatchTest(int matchCount, Rank expectedRank) {
        assertThat(Rank.valueOf(matchCount)).isEqualTo(expectedRank);
    }

    @DisplayName("일치하는 개수가 0, 1, 2개일 경우 모두 MISS(꽝)를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"0", "1", "2"})
    void valueOfMissTest(int matchCount) {
        assertThat(Rank.valueOf(matchCount)).isEqualTo(Rank.MISS);
    }
}