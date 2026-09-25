package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class RankTest {

    @ParameterizedTest
    @CsvSource({
            "3, THREE",
            "4, FOUR",
            "5, FIVE",
            "6, SIX"
    })
    void 일치개수에_따라_당첨등급을_반환한다(
            int matchCount,
            Rank expected
    ) {
        Optional<Rank> rank = Rank.from(matchCount);

        assertThat(rank).contains(expected);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void 당첨되지_않으면_등급이_존재하지_않는다(int matchCount) {
        Optional<Rank> rank = Rank.from(matchCount);

        assertThat(rank).isEmpty();
    }

    @ParameterizedTest
    @CsvSource({
            "THREE, 5000",
            "FOUR, 50000",
            "FIVE, 1500000",
            "SIX, 2000000000"
    })
    void 당첨등급에_따라_상금을_반환한다(
            Rank rank,
            long expectedPrize
    ) {
        assertThat(rank.getPrize()).isEqualTo(expectedPrize);
    }
}