package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class RankTest {

    @ParameterizedTest
    @CsvSource({
            "3, FOURTH",
            "4, THIRD",
            "5, SECOND",
            "6, FIRST"
    })
    void 일치개수에_따라_당첨등급을_반환한다(
            int matchCount,
            Rank expected
    ) {
        Rank rank = Rank.from(matchCount);

        assertThat(rank).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void 당첨되지_않으면_MISS를_반환한다(int matchCount) {
        Rank rank = Rank.from(matchCount);

        assertThat(rank).isEqualTo(Rank.MISS);
    }

    @ParameterizedTest
    @CsvSource({
            "FOURTH, 5000",
            "THIRD, 50000",
            "SECOND, 1500000",
            "FIRST, 2000000000"
    })
    void 당첨등급에_따라_상금을_반환한다(
            Rank rank,
            long expectedPrize
    ) {
        assertThat(rank.getPrize()).isEqualTo(expectedPrize);
    }

    @Test
    void 당첨등급만_반환한다() {
        assertThat(Rank.winningRanks())
                .containsExactly(
                        Rank.FIRST,
                        Rank.SECOND,
                        Rank.THIRD,
                        Rank.FOURTH
                );
    }
}