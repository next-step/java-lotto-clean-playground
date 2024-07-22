package domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PrizeTest {

    @ParameterizedTest
    @MethodSource("test")
    void match_수에_맞는_prize를_반환한다(int match, boolean hasBonusNumber, Prize resultPrize) {
        final Prize foundPrize = Prize.findByMatch(match, hasBonusNumber);
        assertThat(foundPrize).isEqualTo(resultPrize);
    }

    private static Stream<Arguments> test() {
        return Stream.of(
            Arguments.arguments(6, false, Prize.FIRST),
            Arguments.arguments(5, true, Prize.SECOND_BONUS_BALL),
            Arguments.arguments(5, false, Prize.SECOND),
            Arguments.arguments(4, false, Prize.THIRD),
            Arguments.arguments(3, false, Prize.FOURTH),
            Arguments.arguments(2, false, Prize.LOSING_TICKET)
        );
    }
}
