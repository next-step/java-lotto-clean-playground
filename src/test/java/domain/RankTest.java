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
            Arguments.arguments(0, Rank.LAST_PLACE),
            Arguments.arguments(1, Rank.LAST_PLACE),
            Arguments.arguments(2, Rank.LAST_PLACE),
            Arguments.arguments(3, Rank.FOURTH_PLACE),
            Arguments.arguments(4, Rank.THIRD_PLACE),
            Arguments.arguments(5, Rank.SECOND_PLACE),
            Arguments.arguments(6, Rank.FIRST_PLACE)
        );
    }

    @ParameterizedTest(name = "{0}개가 일치하면 등수는 {1}이다.")
    @MethodSource("methodSourceOfRankTest")
    @DisplayName("맞춘 개수(Score)로 등수(Rank)를 구할 수 있다.")
    void rankTest(int inputScore, Rank expectedRank) {
        // given
        Score score = new Score(inputScore);
        // when
        final Rank rank = Rank.getByScore(score);
        // then
        assertThat(rank)
            .isEqualTo(expectedRank);
    }

}
