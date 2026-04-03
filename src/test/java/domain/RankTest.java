package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {

    @DisplayName("일치하는 번호 개수에 따라 정확한 Rank를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {
            "6, SIX",
            "5, FIVE",
            "4, FOUR",
            "3, THREE",
            "2, NONE",
            "0, NONE"
    })
    void valueOfMatchCount(int matchCount, Rank expectedRank) {
        // when
        Rank actualRank = Rank.valueOfMatchCount(matchCount);

        // then
        assertThat(actualRank).isEqualTo(expectedRank);
    }
}