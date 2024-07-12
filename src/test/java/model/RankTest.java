package model;

import global.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {

    @DisplayName("로또의 일치 개수에 따라 알맞은 Rank를 반환한다.")
    @MethodSource("correctCntAndRank")
    @ParameterizedTest(name = "로또의 일치 개수가 {0}개이고 보너스 볼 일치 여부가 {1}이면 등수는 {2}이다.")
    void get_Rank_with_correct_count(int correctCnt, boolean hasBonusNumber, Rank expect) {
        // given
        // when
        final Rank result = Rank.findPlace(correctCnt, hasBonusNumber);

        // then
        assertThat(result).isEqualTo(expect);
    }

    private static Stream<Arguments> correctCntAndRank() {
        return Stream.of(
                Arguments.of(6, false, Rank.FIRST_PLACE),
                Arguments.of(5, true, Rank.SECOND_PLACE),
                Arguments.of(5, false, Rank.THIRD_PLACE),
                Arguments.of(4, false, Rank.FOURTH_PLACE),
                Arguments.of(3, false, Rank.FIFTH_PLACE),
                Arguments.of(2, false, Rank.LAST_PLACE));
    }
}
