package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@SuppressWarnings("NonAsciiCharacters")
class RankTest {

    @ParameterizedTest
    @MethodSource("matchCountWithRank")
    void 당첨_숫자의_개수에_따라_순위가_결정된다(int matchCount, Rank rank) {
        // when
        Rank actual = Rank.of(matchCount);

        // then
        assertThat(actual).isEqualTo(rank);
    }

    static Stream<Arguments> matchCountWithRank() {
        return Stream.of(
            Arguments.of(6, Rank._1ST_PRIZE),
            Arguments.of(5, Rank._3RD_PRIZE),
            Arguments.of(4, Rank._4TH_PRIZE),
            Arguments.of(3, Rank._5TH_PRIZE),
            Arguments.of(2, Rank.NONE),
            Arguments.of(1, Rank.NONE),
            Arguments.of(1, Rank.NONE)
        );
    }
}
