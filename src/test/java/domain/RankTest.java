package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RankTest {

    @ParameterizedTest
    @CsvSource({
            "6, false, FIRST",   // 6개 일치
            "5, true, SECOND",   // 5개 일치 + 보너스 일치
            "5, false, THIRD",   // 5개 일치 + 보너스 불일치
            "4, false, FOURTH",
            "3, false, FIFTH",
            "2, false, MISS",
            "0, false, MISS"
    })
    @DisplayName("일치 개수와 보너스 일치 여부에 따라 올바른 Rank를 반환한다.")
    void findTest(int matchCount, boolean matchBonus, Rank expectedRank) {
        // when
        Rank actualRank = Rank.find(matchCount, matchBonus);

        // then
        assertThat(actualRank).isEqualTo(expectedRank);
    }

    @ParameterizedTest
    @CsvSource({
            "FIRST, 2000000000",
            "SECOND, 30000000",
            "THIRD, 1500000",
            "FOURTH, 50000",
            "FIFTH, 5000",
            "MISS, 0"
    })
    @DisplayName("각 등수별 당첨금을 정확히 반환한다.")
    void getPrizeMoneyTest(Rank rank, int expectedPrize) {
        // when
        int actualPrize = rank.getPrizeMoney();

        // then
        assertThat(actualPrize).isEqualTo(expectedPrize);
    }

    @ParameterizedTest
    @CsvSource({
            "FIRST, 6",
            "SECOND, 5",
            "THIRD, 5",
            "FOURTH, 4",
            "FIFTH, 3"
    })
    @DisplayName("각 등수별 필요 일치 개수를 정확히 반환한다.")
    void getMatchCountTest(Rank rank, int expectedCount) {
        // when
        int actualCount = rank.getMatchCount();

        // then
        assertThat(actualCount).isEqualTo(expectedCount);
    }
}
