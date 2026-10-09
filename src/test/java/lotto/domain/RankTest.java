package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RankTest {

    @Test
    @DisplayName("4개 일치하고 보너스가 맞아도 4등이다")
    void 네개_일치하고_보너스가_맞아도_사등이다() {
        assertThat(Rank.of(4, true)).isEqualTo(Rank.FOURTH);
    }

    @Test
    @DisplayName("5개 일치하고 보너스가 맞으면 2등이다")
    void 다섯개_일치하고_보너스가_맞으면_이등이다() {
        assertThat(Rank.of(5, true)).isEqualTo(Rank.SECOND);
    }

    @Test
    @DisplayName("5개 일치하고 보너스가 안 맞으면 3등이다")
    void 다섯개_일치하고_보너스가_안맞으면_삼등이다() {
        assertThat(Rank.of(5, false)).isEqualTo(Rank.THIRD);
    }

    @Test
    @DisplayName("6개 일치하면 보너스와 무관하게 1등이다")
    void 여섯개_일치하면_보너스와_무관하게_일등이다() {
        assertThat(Rank.of(6, true)).isEqualTo(Rank.FIRST);
    }

    @Test
    @DisplayName("2개 이하 일치하면 낙첨이다")
    void 두개_이하_일치하면_낙첨이다() {
        assertThat(Rank.of(2, false)).isEqualTo(Rank.MISS);
    }
}
