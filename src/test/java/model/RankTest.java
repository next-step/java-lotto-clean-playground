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
    void 당첨_숫자의_개수와_보너스_볼_여부에_따라_순위가_결정된다(int matchCount, boolean matchBonus, Rank rank) {
        // when
        Rank actual = Rank.of(matchCount, matchBonus);

        // then
        assertThat(actual).isEqualTo(rank);
    }

    static Stream<Arguments> matchCountWithRank() {
        return Stream.of(
                Arguments.of(6, false, Rank._1ST_PRIZE),
                Arguments.of(5, true, Rank._2ND_PRIZE),
                Arguments.of(5, false, Rank._3RD_PRIZE),
                Arguments.of(4, false, Rank._4TH_PRIZE),
                Arguments.of(3, false, Rank._5TH_PRIZE),
                Arguments.of(2, false, Rank._LAST_PRIZE),
                Arguments.of(1, false, Rank._LAST_PRIZE),
                Arguments.of(1, false, Rank._LAST_PRIZE)
                        );
    }
}
