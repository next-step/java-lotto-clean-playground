package model;

import global.Rank;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

class LottoResultTest {

    @DisplayName("구매 금액이 들어오면 수익률을 반환한다.")
    @MethodSource("resultAndRateOfReturn")
    @ParameterizedTest(name = "등수가 {0}이면 수익률은 {1}이다.")
    void get_rate_of_reward(Rank rank, double expect) {
        // given
        final int purchaseMoney = 1000;
        final Map<Rank, Integer> ranks = Map.of(rank, 1);
        final LottoResult lottoResult = new LottoResult(ranks);

        // when
        final double result = lottoResult.getRateOfReturn(purchaseMoney);

        // then
        Assertions.assertThat(result).isEqualTo(expect);
    }

    private static Stream<Arguments> resultAndRateOfReturn() {
        return Stream.of(
                Arguments.of(Rank.FIRST_PLACE, 2000000.00),
                Arguments.of(Rank.SECOND_PLACE, 30000.00),
                Arguments.of(Rank.THIRD_PLACE, 1500.00),
                Arguments.of(Rank.FOURTH_PLACE, 50.00),
                Arguments.of(Rank.FIFTH_PLACE, 5.00),
                Arguments.of(Rank.LAST_PLACE, 0.00));
    }
}
