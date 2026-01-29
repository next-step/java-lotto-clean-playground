package domain.lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultTest {

    @DisplayName("LottoResult 생성 시 모든 등수의 카운트가 0이다")
    @Test
    void createLottoResult() {
        // given & when
        LottoResult result = new LottoResult();

        // then
        for (Rank rank : Rank.values()) {
            assertThat(result.getCount(rank)).isEqualTo(0);
        }
    }

    @DisplayName("등수를 추가하면 해당 등수의 카운트가 증가한다")
    @Test
    void addRank() {
        // given
        LottoResult result = new LottoResult();

        // when
        result.addRank(Rank.FIRST);
        result.addRank(Rank.FIRST);
        result.addRank(Rank.FIFTH);

        // then
        assertThat(result.getCount(Rank.FIRST)).isEqualTo(2);
        assertThat(result.getCount(Rank.FIFTH)).isEqualTo(1);
        assertThat(result.getCount(Rank.SECOND)).isEqualTo(0);
    }

    @DisplayName("총 당첨금을 계산한다")
    @Test
    void getTotalPrize() {
        // given
        LottoResult result = new LottoResult();
        result.addRank(Rank.FIFTH);
        result.addRank(Rank.FIFTH);
        result.addRank(Rank.FOURTH);

        // when
        long totalPrize = result.getTotalPrize();

        // then
        assertThat(totalPrize).isEqualTo(5_000 + 5_000 + 50_000);
    }

    @DisplayName("수익률을 계산한다")
    @Test
    void calculateProfitRate() {
        // given
        LottoResult result = new LottoResult();
        result.addRank(Rank.FIFTH);
        int purchaseAmount = 10_000;

        // when
        double profitRate = result.calculateProfitRate(purchaseAmount);

        // then
        assertThat(profitRate).isEqualTo(0.5);
    }

    @DisplayName("당첨이 없으면 수익률은 0이다")
    @Test
    void calculateProfitRateWhenNoPrize() {
        // given
        LottoResult result = new LottoResult();
        result.addRank(Rank.MISS);
        result.addRank(Rank.MISS);
        int purchaseAmount = 2_000;

        // when
        double profitRate = result.calculateProfitRate(purchaseAmount);

        // then
        assertThat(profitRate).isEqualTo(0.0);
    }

    @DisplayName("1등 당첨 시 수익률을 계산한다")
    @Test
    void calculateProfitRateWhenFirstPrize() {
        // given
        LottoResult result = new LottoResult();
        result.addRank(Rank.FIRST);
        int purchaseAmount = 1_000;

        // when
        double profitRate = result.calculateProfitRate(purchaseAmount);

        // then
        assertThat(profitRate).isEqualTo(2_000_000.0);
    }
}
