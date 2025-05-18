package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class ProfitTest {

    @Test
    @DisplayName("수익률이 1.5이면 손해가 아니다")
    void profitIsNotLossIfRateOverOne() {
        LottoStatistics statistics = stubStatistics(Map.of(
                Rank.THIRD, 1
        ));
        int purchaseAmount = 1_000_000;

        Profit profit = new Profit(statistics, purchaseAmount);

        assertThat(profit.rate()).isEqualTo(1.5);
        assertThat(profit.isLoss()).isFalse();
    }

    @Test
    @DisplayName("수익률이 0.5이면 손해이다")
    void profitIsLossIfRateBelowOne() {
        LottoStatistics statistics = stubStatistics(Map.of(
                Rank.FIFTH, 1
        ));
        int purchaseAmount = 10_000;

        Profit profit = new Profit(statistics, purchaseAmount);

        assertThat(profit.rate()).isEqualTo(0.5);
        assertThat(profit.isLoss()).isTrue();
    }

    @Test
    @DisplayName("수익률이 정확히 1이면 손해가 아니다")
    void profitIsNotLossIfRateEqualsOne() {
        LottoStatistics statistics = stubStatistics(Map.of(
                Rank.FIFTH, 2
        ));
        int purchaseAmount = 10_000;

        Profit profit = new Profit(statistics, purchaseAmount);

        assertThat(profit.rate()).isEqualTo(1.0);
        assertThat(profit.isLoss()).isFalse();
    }

    @Test
    @DisplayName("구입 금액이 0이면 예외가 발생한다")
    void profitThrowsIfPurchaseAmountIsZero() {
        LottoStatistics statistics = stubStatistics(Map.of());

        assertThatThrownBy(() -> new Profit(statistics, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구입 금액은 0보다 커야 합니다.");
    }

    @Test
    @DisplayName("구입 금액이 음수이면 예외가 발생한다")
    void profitThrowsIfPurchaseAmountIsNegative() {
        LottoStatistics statistics = stubStatistics(Map.of());

        assertThatThrownBy(() -> new Profit(statistics, -1000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구입 금액은 0보다 커야 합니다.");
    }

    private LottoStatistics stubStatistics(Map<Rank, Integer> countMap) {
        return new LottoStatisticsStub(countMap);
    }

    private static class LottoStatisticsStub extends LottoStatistics {
        private final Map<Rank, Integer> countMap;

        public LottoStatisticsStub(Map<Rank, Integer> countMap) {
            super(
                    new Lottos(List.of()),
                    new WinningLotto(toLottoNumbers(List.of(1, 2, 3, 4, 5, 6))),
                    new BonusNumber(7, new WinningLotto(toLottoNumbers(List.of(1, 2, 3, 4, 5, 6))))
            );
            this.countMap = countMap;
        }

        @Override
        public int countOf(Rank rank) {
            return countMap.getOrDefault(rank, 0);
        }
    }

    private static List<LottoNumber> toLottoNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::new)
                .toList();
    }
}
