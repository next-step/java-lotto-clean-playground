package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {

    @DisplayName("일치하는 번호 개수와 보너스 번호 여부에 따라 정확한 Rank를 반환한다.")
    @ParameterizedTest
    @CsvSource({
            "6, false, SIX",
            "5, true, FIVE_BONUS",
            "5, false, FIVE",
            "4, true, FOUR",
            "3, false, THREE",
            "2, true, NONE",
            "0, false, NONE"
    })
    void valueOf(int matchCount, boolean matchBonus, Rank expected) {
        Rank rank = Rank.valueOf(matchCount, matchBonus);
        assertThat(rank).isEqualTo(expected);
    }

    @DisplayName("당첨 Rank 목록 조회 시 NONE은 제외된다.")
    @Test
    void getWinningRanks() {
        List<Rank> winningRanks = Rank.getWinningRanks();

        assertThat(winningRanks).doesNotContain(Rank.NONE);
        assertThat(winningRanks).hasSize(5);
    }

    @DisplayName("각 Rank에 맞는 출력 메시지를 반환한다.")
    @Test
    void getMessage() {
        assertThat(Rank.FIVE_BONUS.getMessage()).isEqualTo("5개 일치, 보너스 볼 일치 (30000000원)- ");
        assertThat(Rank.THREE.getMessage()).isEqualTo("3개 일치 (5000원)- ");
    }
}