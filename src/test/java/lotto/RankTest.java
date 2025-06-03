package lotto;

import static org.assertj.core.api.Assertions.assertThat;

import lotto.model.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RankTest {

    @Test
    @DisplayName("6개 일치시 1등 반환")
    void returnFirstWhenSixMatched() {
        assertThat(Rank.from(6, false)).isEqualTo(Rank.FIRST);
    }

    @Test
    @DisplayName("5개 일치 + 보너스: 2등 / 5개 일치만: 3등 반환")
    void returnSecondOrThirdWhenFiveMatched() {
        assertThat(Rank.from(5, true)).isEqualTo(Rank.SECOND);
        assertThat(Rank.from(5, false)).isEqualTo(Rank.THIRD);
    }

    @Test
    @DisplayName("4개, 3개 일치시 각각 4등, 5등 반환")
    void returnFourthAndFifth() {
        assertThat(Rank.from(4, false)).isEqualTo(Rank.FOURTH);
        assertThat(Rank.from(3, false)).isEqualTo(Rank.FIFTH);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    @DisplayName("2개 이하 일치시 NONE 반환")
    void returnNullWhenUnderThreeMatched(int count) {
        assertThat(Rank.from(count, false)).isEqualTo(Rank.NONE);
    }
}
