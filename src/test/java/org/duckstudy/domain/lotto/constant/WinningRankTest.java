package org.duckstudy.domain.lotto.constant;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("당첨 순위 테스트")
class WinningRankTest {

    @DisplayName("당첨 횟수와 보너스 볼 여부를 입력하면 당첨 순위를 반환한다")
    @ParameterizedTest
    @MethodSource("generateMatchCountAndMatchBonus")
    void findWinningRankSuccess(int matchCount, boolean matchBonus,
                                WinningRank expected) {
        assertThat(WinningRank.findByMatchCountAndBonus(matchCount, matchBonus))
                .isEqualTo(expected);
    }

    static Stream<Arguments> generateMatchCountAndMatchBonus() {
        return Stream.of(
                Arguments.of(0, false, WinningRank.NONE),
                Arguments.of(3, false, WinningRank.FIFTH),
                Arguments.of(4, false, WinningRank.FOURTH),
                Arguments.of(5, false, WinningRank.THIRD),
                Arguments.of(5, true, WinningRank.SECOND),
                Arguments.of(6, false, WinningRank.FIRST)
        );
    }
}
