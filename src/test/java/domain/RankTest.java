package domain;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class RankTest {

    @Test
    @DisplayName("일치 개수에 따라 보너스 없이 적절한 Rank를 반환한다")
    void matchCountOf_returnsCorrectRank() {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(Rank.value(3, false)).isEqualTo(Rank.FIFTH);
        softly.assertThat(Rank.value(4, false)).isEqualTo(Rank.FOURTH);
        softly.assertThat(Rank.value(5, false)).isEqualTo(Rank.THIRD);
        softly.assertThat(Rank.value(5, true)).isEqualTo(Rank.SECOND);
        softly.assertThat(Rank.value(6, false)).isEqualTo(Rank.FIRST);
        softly.assertAll();
    }

    @ParameterizedTest(name = "valueOf({0}, false)는 MISS를 반환한다")
    @ValueSource(ints = {0, 1, 2, 7, 10})
    @DisplayName("유효하지 않은 개수는 MISS를 반환한다")
    void matchCountOf_returnsNoneForInvalidCount(int count) {
        assertThat(Rank.value(count, false)).isEqualTo(Rank.MISS);
    }

    @Test
    @DisplayName("당첨 Rank는 isWinning()이 true를 반환한다")
    void isWinning_returnsTrueForWinningRanks() {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(Rank.FIFTH.isWinning()).isTrue();
        softly.assertThat(Rank.FOURTH.isWinning()).isTrue();
        softly.assertThat(Rank.THIRD.isWinning()).isTrue();
        softly.assertThat(Rank.SECOND.isWinning()).isTrue();
        softly.assertThat(Rank.FIRST.isWinning()).isTrue();
        softly.assertAll();
    }

    @Test
    @DisplayName("Rank.MISS는 isWinning()이 false를 반환한다")
    void isWinning_returnsFalseForMiss() {
        assertThat(Rank.MISS.isWinning()).isFalse();
    }
}
