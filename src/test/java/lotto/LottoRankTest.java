package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottoRankTest {

    @Test
    @DisplayName("3개부터 6개까지 일치하면 당첨 등수를 반환한다")
    void findWinningRank() {
        assertThat(LottoRank.from(3, false)).isEqualTo(LottoRank.FIFTH);
        assertThat(LottoRank.from(4, false)).isEqualTo(LottoRank.FOURTH);
        assertThat(LottoRank.from(5, false)).isEqualTo(LottoRank.THIRD);
        assertThat(LottoRank.from(6, false)).isEqualTo(LottoRank.FIRST);
    }

    @Test
    @DisplayName("5개와 보너스 번호가 일치하면 2등을 반환한다")
    void findSecondRank() {
        assertThat(LottoRank.from(5, true)).isEqualTo(LottoRank.SECOND);
    }

    @Test
    @DisplayName("3개 미만으로 일치하면 미당첨을 반환한다")
    void findMissRank() {
        assertThat(LottoRank.from(2, false)).isEqualTo(LottoRank.MISS);
    }
}
