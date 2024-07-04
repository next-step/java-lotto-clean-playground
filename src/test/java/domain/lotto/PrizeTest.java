package domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PrizeTest {

    @ParameterizedTest
    @MethodSource("test")
    void match_수에_맞는_prize를_반환한다(int match, Prize resultPrize) {
        final Prize foundPrize = Prize.findByMatch(match);
        assertThat(foundPrize).isEqualTo(resultPrize);
    }

    private static Stream<Arguments> test() {
        return Stream.of(
            Arguments.arguments(6, Prize.FIRST),
            Arguments.arguments(5, Prize.SECOND),
            Arguments.arguments(4, Prize.THIRD),
            Arguments.arguments(3, Prize.FOURTH),
            Arguments.arguments(2, Prize.LOSING_TICKET)
        );
    }
}
