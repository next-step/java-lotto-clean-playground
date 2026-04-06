package lotto.domain;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RankTest {

    @Test
    void 일치_개수가_5개이고_보너스가_일치하면_2등이다() {
        // given
        int matchCount = 5;
        boolean matchBonus = true;

        // when
        Rank rank = Rank.valueOf(matchCount, matchBonus);

        // then
        assertThat(rank).isEqualTo(Rank.SECOND);
    }

    @Test
    void 일치_개수가_5개이고_보너스가_일치하지_않으면_3등이다() {
        // given
        int matchCount = 5;
        boolean matchBonus = false;

        // when
        Rank rank = Rank.valueOf(matchCount, matchBonus);

        // then
        assertThat(rank).isEqualTo(Rank.THIRD);
    }

    @ParameterizedTest
    @CsvSource({
            "6, false, FIRST",
            "4, false, FOURTH",
            "3, true, FIFTH",
            "0, false, MISS"
    })
    void 일치_개수에_따른_순위_판정_확인(int matchCount, boolean matchBonus, Rank expected) {
        // when
        Rank rank = Rank.valueOf(matchCount, matchBonus);

        // then
        assertThat(rank).isEqualTo(expected);
    }
}
