package domain;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class LottoStatisticsTest {
    @Test
    void 구매_로또들의_당첨_등급별_개수를_계산한다() {
        // given
        PurchaseAmount purchaseAmount = new PurchaseAmount(5000);
        WinningLotto winningLotto = WinningLotto.from(List.of(1, 2, 3, 4, 5, 6), 7);
        List<Lotto> lottos = List.of(
                Lotto.from(List.of(1, 2, 3, 7, 8, 9)),      // 3개 일치 + 보너스
                Lotto.from(List.of(1, 2, 3, 4, 8, 9)),      // 4개 일치
                Lotto.from(List.of(1, 2, 3, 4, 5, 8)),      // 5개 일치
                Lotto.from(List.of(1, 2, 3, 4, 5, 7)),      // 5개 일치 + 보너스
                Lotto.from(List.of(1, 2, 7, 8, 9, 10))      // 2개 일치
        );
        LottoStatistics statistics = new LottoStatistics(lottos, winningLotto, purchaseAmount);

        // when
        Map<LottoRank, Integer> rankCounts = statistics.getRankCounts();

        // then
        assertThat(rankCounts.get(LottoRank.THREE_MATCH)).isEqualTo(1);
        assertThat(rankCounts.get(LottoRank.FOUR_MATCH)).isEqualTo(1);
        assertThat(rankCounts.get(LottoRank.FIVE_MATCH)).isEqualTo(1);
        assertThat(rankCounts.get(LottoRank.FIVE_MATCH_WITH_BONUS)).isEqualTo(1);
        assertThat(rankCounts.get(LottoRank.SIX_MATCH)).isEqualTo(0);
    }

    @Test
    void 총_당첨금을_구입_금액으로_나누어_수익률을_계산한다() {
        // given
        PurchaseAmount purchaseAmount = new PurchaseAmount(10000);
        WinningLotto winningLotto = WinningLotto.from(List.of(1, 2, 3, 4, 5, 6), 7);
        List<Lotto> lottos = List.of(Lotto.from(List.of(1, 2, 3, 7, 8, 9)));
        LottoStatistics statistics = new LottoStatistics(lottos, winningLotto, purchaseAmount);

        // when
        double profitRate = statistics.getProfitRate();

        // then
        assertThat(profitRate).isEqualTo(0.5);
    }

    @Test
    void 일등이_두_장이면_수익률을_정상적으로_계산한다() {
        // given
        PurchaseAmount purchaseAmount = new PurchaseAmount(2000);
        WinningLotto winningLotto = WinningLotto.from(List.of(1, 2, 3, 4, 5, 6), 7);
        List<Lotto> lottos = List.of(
                Lotto.from(List.of(1, 2, 3, 4, 5, 6)),
                Lotto.from(List.of(1, 2, 3, 4, 5, 6))
        );
        LottoStatistics statistics = new LottoStatistics(lottos, winningLotto, purchaseAmount);

        // when
        double profitRate = statistics.getProfitRate();

        // then
        assertThat(profitRate).isEqualTo(2_000_000.0);
    }
}
