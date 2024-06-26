package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {

    @DisplayName("로또의 일치 개수에 따라 알맞은 Rank를 반환한다.")
    @MethodSource("correctCntAndRank")
    @ParameterizedTest(name = "로또의 일치 개수가 {0}개이면 등수는 {1}이다.")
    void get_Rank_with_correct_count(int correctCnt, Rank expect) {
        // given
        // when
        final Rank result = Rank.findPlace(correctCnt);

        // then
        assertThat(result).isEqualTo(expect);
    }

    private static Stream<Arguments> correctCntAndRank() {
        return Stream.of(
                Arguments.of(6, Rank.FIRST_PLACE),
                Arguments.of(5, Rank.SECOND_PLACE),
                Arguments.of(4, Rank.THIRD_PLACE),
                Arguments.of(3, Rank.FOURTH_PLACE),
                Arguments.of(2, Rank.LAST_PLACE),
                Arguments.of(1, Rank.LAST_PLACE));
    }
}
