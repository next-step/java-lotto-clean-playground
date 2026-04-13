package domain;

import static domain.Price.PRICE_OF_ONE_LOTTO;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LottoResultTest {
    @DisplayName("로또 결과와 가격으로 수익률을 계산한다.")
    @ParameterizedTest
    @MethodSource
    public void testGetProfitRate(List<LottoRank> lottoRankOfEachTicket) {
        // given
        LottoResult lottoResult = new LottoResult(lottoRankOfEachTicket);

        int cost = PRICE_OF_ONE_LOTTO * lottoRankOfEachTicket.size();
        Price price = new Price(cost);
        int totalProfit = lottoRankOfEachTicket.stream()
                .mapToInt(LottoRank::getPrizeMoney)
                .sum();

        // when
        double actual = lottoResult.getProfitRate(price);
        double expected = (double) totalProfit / cost;

        // then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> testGetProfitRate() {
        return Stream.of(
                Arguments.arguments(List.of(LottoRank.FIRST)),
                Arguments.arguments(List.of(LottoRank.FIFTH, LottoRank.FOURTH, LottoRank.THIRD, LottoRank.SECOND)),
                Arguments.arguments(List.of(LottoRank.FIFTH, LottoRank.FIFTH, LottoRank.FOURTH, LottoRank.THIRD,
                        LottoRank.THIRD, LottoRank.FIRST, LottoRank.MISS, LottoRank.MISS, LottoRank.MISS))
        );
    }
}
