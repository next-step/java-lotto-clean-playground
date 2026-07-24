package domain;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LotteryStatisticsTest {
    @Test
    void 통계를_정상적으로_검사한다() {
        // Given
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        Lotto lotto1 = new Lotto(List.of(1, 2, 3, 7, 8, 9));      // 3개 일치
        Lotto lotto2 = new Lotto(List.of(1, 2, 3, 4, 7, 8));      // 4개 일치
        Lotto lotto3 = new Lotto(List.of(7, 8, 9, 10, 11, 12));   // 일치 x

        Lottos lottos = new Lottos(List.of(lotto1, lotto2, lotto3));

        LotteryStatistics lotteryStatistics = new LotteryStatistics();

        // When
        lotteryStatistics.calculateStatistics(lottos, winningLotto);

        // Then
        Map<Rank, Integer> result = lotteryStatistics.getStatistics();

        assertEquals(1, result.get(Rank.NONE));
        assertEquals(1, result.get(Rank.THREE));
        assertEquals(1, result.get(Rank.FOUR));
        assertEquals(0, result.get(Rank.FIVE));
        assertEquals(0, result.get(Rank.SIX));
    }

    @Test
    void 총_당첨금을_정확히_계산한다() {
        // Given
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        Lotto lotto1 = new Lotto(List.of(1, 2, 3, 7, 8, 9));      // 3개 일치
        Lotto lotto2 = new Lotto(List.of(1, 2, 3, 4, 7, 8));      // 4개 일치
        Lotto lotto3 = new Lotto(List.of(7, 8, 9, 10, 11, 12));   // 일치 x

        Lottos lottos = new Lottos(List.of(lotto1, lotto2, lotto3));

        LotteryStatistics lotteryStatistics = new LotteryStatistics();

        // When
        lotteryStatistics.calculateStatistics(lottos, winningLotto);
        Money totalPrize = lotteryStatistics.calculatePrize();

        // Then
        assertEquals(new Money(55000), totalPrize);
    }
}
