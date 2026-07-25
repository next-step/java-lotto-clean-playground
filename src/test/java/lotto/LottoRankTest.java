package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottoRankTest {

    @Test
    @DisplayName("3개부터 6개까지 일치하면 당첨 등수를 반환한다")
    void findWinningRank() {
        assertThat(LottoRank.from(3)).isEqualTo(LottoRank.FOURTH);
        assertThat(LottoRank.from(4)).isEqualTo(LottoRank.THIRD);
        assertThat(LottoRank.from(5)).isEqualTo(LottoRank.SECOND);
        assertThat(LottoRank.from(6)).isEqualTo(LottoRank.FIRST);
    }

    @Test
    @DisplayName("3개 미만으로 일치하면 미당첨을 반환한다")
    void findMissRank() {
        assertThat(LottoRank.from(2)).isEqualTo(LottoRank.MISS);
    }
}
