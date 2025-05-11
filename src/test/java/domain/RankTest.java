package domain;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RankTest {

    @Test
    @DisplayName("일치 개수에 따라 적절한 Rank를 반환한다")
    void matchCountOf_returnsCorrectRank() {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(Rank.matchCountOf(3)).isEqualTo(Rank.THREE);
        softly.assertThat(Rank.matchCountOf(4)).isEqualTo(Rank.FOUR);
        softly.assertThat(Rank.matchCountOf(5)).isEqualTo(Rank.FIVE);
        softly.assertThat(Rank.matchCountOf(6)).isEqualTo(Rank.SIX);
        softly.assertAll();
    }

    @ParameterizedTest(name = "matchCountOf({0}) 는 NONE을 반환한다")
    @ValueSource(ints = {0, 1, 2, 7, 10})
    @DisplayName("유효하지 않은 개수는 NONE을 반환한다")
    void matchCountOf_returnsNoneForInvalidCount(int count) {
        assertThat(Rank.matchCountOf(count)).isEqualTo(Rank.NONE);
    }

    @Test
    @DisplayName("당첨 Rank는 isWinning()이 true를 반환한다")
    void isWinning_returnsTrueForWinningRanks() {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(Rank.THREE.isWinning()).isTrue();
        softly.assertThat(Rank.FOUR.isWinning()).isTrue();
        softly.assertThat(Rank.FIVE.isWinning()).isTrue();
        softly.assertThat(Rank.SIX.isWinning()).isTrue();
        softly.assertAll();
    }

    @Test
    @DisplayName("Rank.NONE은 isWinning()이 false를 반환한다")
    void isWinning_returnsFalseForNone() {
        assertThat(Rank.NONE.isWinning()).isFalse();
    }
}
