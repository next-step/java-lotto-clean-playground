package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.EnumMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class LottoResultTest {
    @Test
    void calculatesTotalPrizeBySumOfRankPrizes() {
        // 준비
        Map<Rank, Integer> rankCount = zeroCounts();
        rankCount.put(Rank.FIFTH, 2);
        rankCount.put(Rank.FOURTH, 1);
        LottoResult result = new LottoResult(rankCount);
        // 실행 & 검증
        assertThat(result.calculateTotalPrize()).isEqualTo(60_000L);
    }

    @Test
    void calculatesTotalPrizeWithoutOverflow() {
        // 준비
        Map<Rank, Integer> rankCount = zeroCounts();
        rankCount.put(Rank.FIRST, 2);
        LottoResult result = new LottoResult(rankCount);
        // 실행 & 검증
        assertThat(result.calculateTotalPrize()).isEqualTo(4_000_000_000L);
    }

    @Test
    void calculatesReturnRate() {
        // 준비
        Map<Rank, Integer> rankCount = zeroCounts();
        rankCount.put(Rank.FOURTH, 1);
        LottoResult result = new LottoResult(rankCount);
        // 실행
        double returnRate = result.calculateReturnRate(10);
        // 검증
        assertThat(returnRate).isEqualTo(5.0);
    }

    @Test
    void returnsZeroWhenNothingWon() {
        // 준비
        LottoResult result = new LottoResult(zeroCounts());
        // 실행 & 검증
        assertThat(result.calculateTotalPrize()).isZero();
        assertThat(result.calculateReturnRate(5)).isZero();
    }

    private Map<Rank, Integer> zeroCounts() {
        Map<Rank, Integer> counts = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            counts.put(rank, 0);
        }
        return counts;
    }
}
