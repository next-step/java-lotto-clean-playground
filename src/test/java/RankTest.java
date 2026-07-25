import domain.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {

    @Nested
    @DisplayName("일치 개수와 보너스로 등수 판정 테스트")
    class of {

        @ParameterizedTest
        @CsvSource({"3, FIFTH", "4, FOURTH", "5, THIRD", "6, FIRST"})
        @DisplayName("보너스 미일치 시 개수에 맞는 등수 반환 테스트")
        void 보너스_미일치_등수_반환(int matchCount, Rank expectedRank) {
            assertThat(Rank.of(matchCount, false)).isEqualTo(expectedRank);
        }

        @Test
        @DisplayName("5개 일치 + 보너스 일치면 SECOND 반환 테스트")
        void 다섯개와_보너스_일치_SECOND() {
            Rank expectedRank = Rank.SECOND;

            assertThat(Rank.of(5, true)).isEqualTo(expectedRank);
        }

        @ParameterizedTest
        @ValueSource(ints = {0, 1, 2})
        @DisplayName("2개 이하 일치는 MISS 반환 테스트")
        void 낮은_일치_개수는_MISS(int matchCount) {
            Rank expectedRank = Rank.MISS;

            assertThat(Rank.of(matchCount, false)).isEqualTo(expectedRank);
        }
    }

    @Nested
    @DisplayName("등수별 상금 테스트")
    class prize {

        @ParameterizedTest
        @CsvSource({
                "FIFTH, 5000",
                "FOURTH, 50000",
                "THIRD, 1500000",
                "SECOND, 30000000",
                "FIRST, 2000000000"
        })
        @DisplayName("등수에 맞는 상금 반환 테스트")
        void 등수별_상금(Rank rank, long expectedPrize) {
            assertThat(rank.getPrize()).isEqualTo(expectedPrize);
        }
    }
}
