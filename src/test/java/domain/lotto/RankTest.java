package domain.lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {

    @DisplayName("6개 일치하면 1등이다")
    @Test
    void firstRank() {
        // given
        int matchCount = 6;
        boolean matchBonus = false;

        // when
        Rank rank = Rank.valueOf(matchCount, matchBonus);

        // then
        assertThat(rank).isEqualTo(Rank.FIRST);
        assertThat(rank.getPrize()).isEqualTo(2_000_000_000);
    }

    @DisplayName("5개 일치하고 보너스 번호가 일치하면 2등이다")
    @Test
    void secondRank() {
        // given
        int matchCount = 5;
        boolean matchBonus = true;

        // when
        Rank rank = Rank.valueOf(matchCount, matchBonus);

        // then
        assertThat(rank).isEqualTo(Rank.SECOND);
        assertThat(rank.getPrize()).isEqualTo(30_000_000);
    }

    @DisplayName("5개 일치하고 보너스 번호가 일치하지 않으면 3등이다")
    @Test
    void thirdRank() {
        // given
        int matchCount = 5;
        boolean matchBonus = false;

        // when
        Rank rank = Rank.valueOf(matchCount, matchBonus);

        // then
        assertThat(rank).isEqualTo(Rank.THIRD);
        assertThat(rank.getPrize()).isEqualTo(1_500_000);
    }

    @DisplayName("4개 일치하면 4등이다")
    @Test
    void fourthRank() {
        // given
        int matchCount = 4;
        boolean matchBonus = false;

        // when
        Rank rank = Rank.valueOf(matchCount, matchBonus);

        // then
        assertThat(rank).isEqualTo(Rank.FOURTH);
        assertThat(rank.getPrize()).isEqualTo(50_000);
    }

    @DisplayName("3개 일치하면 5등이다")
    @Test
    void fifthRank() {
        // given
        int matchCount = 3;
        boolean matchBonus = false;

        // when
        Rank rank = Rank.valueOf(matchCount, matchBonus);

        // then
        assertThat(rank).isEqualTo(Rank.FIFTH);
        assertThat(rank.getPrize()).isEqualTo(5_000);
    }

    @DisplayName("2개 이하로 일치하면 MISS이다")
    @Test
    void missRank() {
        // given
        int matchCount = 2;
        boolean matchBonus = false;

        // when
        Rank rank = Rank.valueOf(matchCount, matchBonus);

        // then
        assertThat(rank).isEqualTo(Rank.MISS);
        assertThat(rank.getPrize()).isEqualTo(0);
    }
}
