package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {

    @Test
    @DisplayName("일치 개수에 따라 올바른 Rank를 반환한다.")
    void valueOfTest() {
        assertThat(Rank.valueOf(6)).isEqualTo(Rank.FIRST);
        assertThat(Rank.valueOf(3)).isEqualTo(Rank.FIFTH);
        assertThat(Rank.valueOf(0)).isEqualTo(Rank.MISS);
    }
}
