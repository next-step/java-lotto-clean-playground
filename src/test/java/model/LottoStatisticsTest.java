package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoStatisticsTest {
    private static Lotto lottoOf(int... numbers) {
        return new Lotto(
                List.of(numbers[0], numbers[1], numbers[2], numbers[3], numbers[4], numbers[5])
                        .stream()
                        .map(LottoNumber::new)
                        .toList()
        );
    }

    private static List<LottoNumber> winningNumbers() {
        return List.of(1, 2, 3, 4, 5, 6).stream()
                .map(LottoNumber::new)
                .toList();
    }

    @Test
    @DisplayName("통계 결과가 맞게 집계되어야 한다")
    void matchCounts_GroupToRanks() {
        // given
        List<Lotto> purchased = List.of(
                lottoOf(1, 2, 3, 4, 5, 6),
                lottoOf(1, 2, 3, 4, 5, 7),
                lottoOf(1, 2, 3, 4, 5, 8),
                lottoOf(1, 2, 3, 4, 10, 11),
                lottoOf(1, 2, 3, 9, 10, 11)
        );
        LottoNumber bonus = new LottoNumber(7);
        int purchaseAmount = 6000;

        // when
        LottoStatistics statistics = new LottoStatistics(purchased, winningNumbers(), bonus, purchaseAmount);

        // then
        assertThat(statistics.getCount(Rank.FIRST)).isEqualTo(1);
        assertThat(statistics.getCount(Rank.SECOND)).isEqualTo(1);
        assertThat(statistics.getCount(Rank.THIRD)).isEqualTo(1);
        assertThat(statistics.getCount(Rank.FOURTH)).isEqualTo(1);
        assertThat(statistics.getCount(Rank.FIFTH)).isEqualTo(1);
    }

    @Test
    @DisplayName("수익률은 총 상금 / 구매금액 으로 계산된다")
    void calculate_profitRate() {
        // given
        List<Lotto> purchased = List.of(
                lottoOf(1, 2, 3, 4, 5, 6),
                lottoOf(1, 2, 3, 4, 5, 7)
        );
        LottoNumber bonus = new LottoNumber(7);
        int purchaseAmount = 2000;

        // when
        LottoStatistics statistics = new LottoStatistics(purchased, winningNumbers(), bonus, purchaseAmount);

        // then
        long totalPrize = Rank.FIRST.getPrize() + Rank.SECOND.getPrize();
        double expectedProfitRate = (double) totalPrize / purchaseAmount;

        assertThat(statistics.getProfitRate()).isEqualTo(expectedProfitRate);
    }
}
