package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {

    @Test
    @DisplayName("1등: 6개 일치, 보너스 상관없음")
    void firstPrize() {
        assertThat(Rank.of(6, false)).isEqualTo(Rank.FIRST);
        assertThat(Rank.of(6, true)).isEqualTo(Rank.FIRST);
    }

    @Test
    @DisplayName("2등: 5개 + 보너스 번호 일치")
    void secondPrize() {
        assertThat(Rank.of(5, true)).isEqualTo(Rank.SECOND);
    }

    @Test
    @DisplayName("3등: 5개 일치, 보너스 번호 불일치")
    void thirdPrize() {
        assertThat(Rank.of(5, false)).isEqualTo(Rank.THIRD);
    }

    @Test
    @DisplayName("4등: 4개 일치")
    void fourthPrize() {
        assertThat(Rank.of(4, true)).isEqualTo(Rank.FOURTH);
        assertThat(Rank.of(4, false)).isEqualTo(Rank.FOURTH);
    }

    @Test
    @DisplayName("5등: 3개 일치")
    void fifthPrize() {
        assertThat(Rank.of(3, true)).isEqualTo(Rank.FIFTH);
        assertThat(Rank.of(3, false)).isEqualTo(Rank.FIFTH);
    }

    @Test
    @DisplayName("낙첨: 2개 이하 또는 5개인데 보너스도 없음")
    void noPrize() {
        assertThat(Rank.of(2, true)).isEqualTo(Rank.NONE);
        assertThat(Rank.of(0, false)).isEqualTo(Rank.NONE);
        }

}
