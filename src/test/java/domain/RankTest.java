package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {

    @DisplayName("일치 개수와 보너스 일치 여부에 따라 정확한 등수를 반환한다.")
    @Test
    void valueOfMatchTest() {
        assertThat(Rank.valueOf(6, false)).isEqualTo(Rank.FIRST);
        assertThat(Rank.valueOf(5, true)).isEqualTo(Rank.SECOND); // 2등
        assertThat(Rank.valueOf(5, false)).isEqualTo(Rank.THIRD); // 3등
        assertThat(Rank.valueOf(4, false)).isEqualTo(Rank.FOURTH);
    }

    @DisplayName("일치하는 개수가 0, 1, 2개일 경우 모두 MISS(꽝)를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"0", "1", "2"})
    void valueOfMissTest(int matchCount) {
        assertThat(Rank.valueOf(matchCount, false)).isEqualTo(Rank.MISS);
        assertThat(Rank.valueOf(matchCount, true)).isEqualTo(Rank.MISS);
    }
}