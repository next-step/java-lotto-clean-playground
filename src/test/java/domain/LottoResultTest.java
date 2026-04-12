package domain;

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
    public void testGetProfitRate(List<LottoRank> lottoRankOfEachTicket, Price price, double expected) {
        // given
        LottoResult lottoResult = new LottoResult(lottoRankOfEachTicket);

        // when
        double actual = lottoResult.getProfitRate(price);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> testGetProfitRate() {
        return Stream.of(
                Arguments.arguments(List.of(LottoRank.FIFTH, LottoRank.FOURTH, LottoRank.FOURTH, LottoRank.THIRD),
                        new Price(10000), 160.5),
                Arguments.arguments(List.of(), new Price(10000), 0.0),
                Arguments.arguments(
                        List.of(LottoRank.FIFTH, LottoRank.FIFTH, LottoRank.FOURTH, LottoRank.THIRD, LottoRank.THIRD,
                                LottoRank.FIRST), new Price(10000), 200306.0)
        );
    }
}
