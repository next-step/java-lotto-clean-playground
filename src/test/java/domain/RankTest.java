package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {

    @DisplayName("일치하는 숫자 개수와 보너스 번호 일치 여부에 따라 정확한 당첨 순위를 반환한다.")
    @ParameterizedTest
    @CsvSource({
            "6, false, SIX",
            "6, true, SIX",
            "5, true, FIVE_BONUS",
            "5, false, FIVE",
            "4, true, FOUR",
            "4, false, FOUR",
            "3, true, THREE",
            "3, false, THREE",
            "2, true, NONE",
            "0, false, NONE"
    })
    void valueOfRank(int matchCount, boolean matchBonus, Rank expectedRank) {
        // when
        Rank actual = Rank.valueOfRank(matchCount, matchBonus);

        // then
        assertThat(actual).isEqualTo(expectedRank);
    }

    @DisplayName("당첨 순위 목록 조회 시 NONE은 제외된다.")
    @Test
    void getWinningRanks() {
        // when
        List<Rank> winningRanks = Rank.getWinningRanks();

        // then
        assertThat(winningRanks).containsExactly(
                Rank.THREE,
                Rank.FOUR,
                Rank.FIVE,
                Rank.FIVE_BONUS,
                Rank.SIX
        );
        assertThat(winningRanks).doesNotContain(Rank.NONE);
    }
}
