package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RankTest {

    @ParameterizedTest
    @CsvSource({
            "6, false, FIRST",
            "5, true, SECOND",
            "5, false, THIRD",
            "4, false, FOURTH",
            "3, false, FIFTH",
            "2, false, MISS",
            "0, true, MISS"
    })
    @DisplayName("일치하는 숫자 개수와 보너스 번호 일치 여부에 따라 올바른 등수를 반환한다.")
    void findTest(int matchCount, boolean matchBonus, Rank expectedRank) {
        //given
        Rank rankFinder = Rank.MISS;

        //when
        Rank result = rankFinder.find(matchCount, matchBonus);

        //then
        assertThat(result).isEqualTo(expectedRank);
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
    @DisplayName("각 등수에 해당하는 상금을 정확히 반환한다.")
    void getPrizeMoneyTest(Rank rank, int expectedPrize) {
        //given //when
        int prizeMoney = rank.getPrizeMoney();

        //then
        assertThat(prizeMoney).isEqualTo(expectedPrize);
    }
}
