package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.EnumMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MatchResultTest {

    @Test
    @DisplayName("Rank 별 당첨 개수를 반환한다")
    void return_count_by_rank() {
        // Given
        Map<Rank, Integer> resultMap = new EnumMap<>(Rank.class);
        resultMap.put(Rank.FIRST, 2);
        resultMap.put(Rank.SECOND, 1);
        MatchResult result = new MatchResult(resultMap);

        // When & Then
        assertThat(result.getCount(Rank.FIRST)).isEqualTo(2);
        assertThat(result.getCount(Rank.SECOND)).isEqualTo(1);
        assertThat(result.getCount(Rank.THIRD)).isEqualTo(0); // 없는 값은 0 반환
    }

    @Test
    @DisplayName("총 당첨 상금을 계산한다")
    void calculate_total_prize() {
        // Given
        Map<Rank, Integer> resultMap = new EnumMap<>(Rank.class);
        resultMap.put(Rank.FIRST, 1);
        resultMap.put(Rank.FIFTH, 2);
        MatchResult result = new MatchResult(resultMap);

        // When
        Prize total = result.calculateTotalPrize();

        // Then
        assertThat(total.getAmount()).isEqualTo(2_000_010_000L);
    }

    @Test
    @DisplayName("당첨 내역이 없을 경우 총 상금은 0이다")
    void return_zero_prize_when_no_matches() {
        // Given
        MatchResult result = new MatchResult(new EnumMap<>(Rank.class));

        // When
        Prize total = result.calculateTotalPrize();

        // Then
        assertThat(total.getAmount()).isEqualTo(0L);
    }

    @Test
    @DisplayName("총 상금 대비 수익률을 계산한다")
    void calculate_profit_rate() {
        // Given
        Map<Rank, Integer> resultMap = new EnumMap<>(Rank.class);
        resultMap.put(Rank.FIFTH, 4); // 4 * 5000 = 20_000
        MatchResult result = new MatchResult(resultMap);
        BuyAmount buyAmount = new BuyAmount(10000, 0);

        // When
        double profitRate = result.calculateProfitRate(buyAmount);

        // Then
        assertThat(profitRate).isEqualTo(2.0); // 20_000 / 10_000
    }
}
