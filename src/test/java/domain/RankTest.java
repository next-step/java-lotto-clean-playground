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
            "3, false, FIFTH",
            "4, false, FOURTH",
            "5, false, THIRD",
            "5, true, SECOND",
            "6, false, FIRST",
    })
    void 일치개수와_보너스볼_일치_여부에_따라_당첨등급을_반환한다(
            int matchCount,
            boolean bonusMatched,
            Rank expected
    ) {
        Rank rank = Rank.from(matchCount, bonusMatched);

        assertThat(rank).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void 당첨되지_않으면_MISS를_반환한다(int matchCount) {
        Rank rank = Rank.from(matchCount, false);

        assertThat(rank).isEqualTo(Rank.MISS);
    }

    @ParameterizedTest
    @CsvSource({
            "5, true, SECOND",
            "5, false, THIRD"
    })
    void 보너스볼_일치_여부에_따라_5개_일치시_2등과_3등이_결정된다(
            int matchCount,
            boolean bonusMatched,
            Rank expected
    ) {
        Rank rank = Rank.from(matchCount, bonusMatched);

        assertThat(rank).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "FIFTH, 5000",
            "FOURTH, 50000",
            "THIRD, 1500000",
            "SECOND, 30000000",
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
                        Rank.FOURTH,
                        Rank.FIFTH
                );
    }
}