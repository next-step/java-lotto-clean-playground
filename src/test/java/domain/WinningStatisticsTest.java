package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WinningStatisticsTest {

    @Test
    void 등수를_추가하면_개수가_증가한다() {
        WinningStatistics winningStatistics = new WinningStatistics();

        winningStatistics.add(Rank.THREE_MATCH);
        winningStatistics.add(Rank.THREE_MATCH);
        winningStatistics.add(Rank.FOUR_MATCH);

        assertThat(winningStatistics.countOf(Rank.THREE_MATCH)).isEqualTo(2);
        assertThat(winningStatistics.countOf(Rank.FOUR_MATCH)).isEqualTo(1);
    }

    @Test
    void 총_당첨금을_계산한다() {
        WinningStatistics winningStatistics = new WinningStatistics();

        winningStatistics.add(Rank.THREE_MATCH);
        winningStatistics.add(Rank.THREE_MATCH);
        winningStatistics.add(Rank.FOUR_MATCH);

        assertThat(winningStatistics.calculateTotalPrize()).isEqualTo(60000);
    }

    @Test
    void 수익률을_계산한다() {
        WinningStatistics winningStatistics = new WinningStatistics();
        PurchaseAmount purchaseAmount = new PurchaseAmount(10000);

        winningStatistics.add(Rank.THREE_MATCH);
        winningStatistics.add(Rank.FOUR_MATCH);

        assertThat(winningStatistics.calculateProfitRate(purchaseAmount)).isEqualTo(5.5);
    }
}
