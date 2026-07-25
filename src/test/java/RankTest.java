import domain.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {

    @Nested
    @DisplayName("일치 개수로 등수 판정 테스트")
    class from {

        @ParameterizedTest
        @CsvSource({"3, THREE", "4, FOUR", "5, FIVE", "6, SIX"})
        @DisplayName("일치 개수에 맞는 등수 반환 테스트")
        void 일치_개수에_맞는_등수_반환(int matchCount, Rank expectedRank) {
            assertThat(Rank.from(matchCount)).isEqualTo(expectedRank);
        }

        @ParameterizedTest
        @ValueSource(ints = {0, 1, 2})
        @DisplayName("2개 이하 일치는 MISS 반환 테스트")
        void 낮은_일치_개수는_MISS(int matchCount) {
            Rank expectedRank = Rank.MISS;

            assertThat(Rank.from(matchCount)).isEqualTo(expectedRank);
        }
    }

    @Nested
    @DisplayName("등수별 상금 테스트")
    class prize {

        @ParameterizedTest
        @CsvSource({"THREE, 5000", "FOUR, 50000", "FIVE, 1500000", "SIX, 2000000000"})
        @DisplayName("등수에 맞는 상금 반환 테스트")
        void 등수별_상금(Rank rank, long expectedPrize) {
            assertThat(rank.getPrize()).isEqualTo(expectedPrize);
        }
    }
}
