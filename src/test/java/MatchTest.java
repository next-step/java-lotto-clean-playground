import domain.Match;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class MatchTest {
    @ParameterizedTest
    @MethodSource("matchCountWithRank")
    void 당첨_숫자의_개수와_보너스_볼_여부에_따라_순위가_결정된다(int matchCount, boolean bonusFlag, Match match) {
        // when
        Match actual = Match.from(matchCount, bonusFlag);

        // then
        assertThat(actual).isEqualTo(match);
    }

    static Stream<Arguments> matchCountWithRank() {
        return Stream.of(
                Arguments.of(6, false, Match.SIX),
                Arguments.of(5, true, Match.FIVEWITHBONUS),
                Arguments.of(5, false, Match.FIVE),
                Arguments.of(4, false, Match.FOUR),
                Arguments.of(3, false, Match.THREE)
        );
    }
}
