package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LottoRankTest {

    @ParameterizedTest(name = "당첨 번호 개수 : {0}, 보너스 번호 당첨 : {1}, 순위 : {2}")
    @MethodSource("arguments")
    @DisplayName("일치한 당첨 번호 개수와 보너스 번호 일치 여부에 따라 알맞게 순위를 매겨야 한다.")
    void testLottoRankMatch(int matchCount, boolean matchBonusNumber, LottoRank rank) {
        assertThat(LottoRank.matchRank(matchCount, matchBonusNumber)).isEqualTo(rank);
    }

    static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.arguments(6,false, LottoRank.FIRST_PRIZE),
                Arguments.arguments(5,true, LottoRank.SECOND_PRIZE),
                Arguments.arguments(5,false, LottoRank.THIRD_PRIZE),
                Arguments.arguments(4,false, LottoRank.FOURTH_PRIZE),
                Arguments.arguments(3,false, LottoRank.FIFTH_PRIZE),
                Arguments.arguments(2,false, LottoRank.NO_PRIZE)
        );
    }

}
