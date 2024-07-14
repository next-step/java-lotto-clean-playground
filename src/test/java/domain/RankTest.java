package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RankTest {

    private static Stream<Arguments> methodSourceOfRankTest() {
        return Stream.of(
            Arguments.arguments(0, true, Rank.LAST_PLACE),
            Arguments.arguments(0, false, Rank.LAST_PLACE),
            Arguments.arguments(1, true, Rank.LAST_PLACE),
            Arguments.arguments(1, false, Rank.LAST_PLACE),
            Arguments.arguments(2, true, Rank.LAST_PLACE),
            Arguments.arguments(2, false, Rank.LAST_PLACE),
            Arguments.arguments(3, true, Rank.FIFTH_PLACE),
            Arguments.arguments(3, false, Rank.FIFTH_PLACE),
            Arguments.arguments(4, true, Rank.FOURTH_PLACE),
            Arguments.arguments(4, false, Rank.FOURTH_PLACE),
            Arguments.arguments(5, true, Rank.SECOND_PLACE),
            Arguments.arguments(5, false, Rank.THIRD_PLACE),
            Arguments.arguments(6, true, Rank.FIRST_PLACE),
            Arguments.arguments(6, false, Rank.FIRST_PLACE)
        );
    }

    @ParameterizedTest(name = "{0}개가 일치하고 보너스 번호의 일치여부는 {1}이면 등수는 {2}이다.")
    @MethodSource("methodSourceOfRankTest")
    @DisplayName("맞춘 개수(Score)로 등수(Rank)를 구할 수 있다.")
    void rankTest(int inputScore, boolean isBonusBallMatching, Rank expectedRank) {
        // given
        Score score = new Score(inputScore, isBonusBallMatching);
        // when
        final Rank rank = Rank.getByScore(score);
        // then
        assertThat(rank)
            .isEqualTo(expectedRank);
    }

}
