import domain.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;


class RankTest {
    @ParameterizedTest
    @DisplayName("일치하는 번호 수와 보너스 볼 유무에 따라 정확한 Rank를 반환한다")

    @CsvSource({
            "6, false, FIRST_PLACE",
            "5, true, SECOND_PLACE_BONUS",
            "5, false, SECOND_PLACE",
            "4, false, THIRD_PLACE",
            "3, false, FOURTH_PLACE",
            "2, false, MISS",
            "0, false, MISS"
    })
    void getRankTest(int matchCount, boolean hasBonus, Rank expectedRank) {
        Rank rank = Rank.getRank(matchCount, hasBonus);
        assertThat(rank).isEqualTo(expectedRank);
    }
}