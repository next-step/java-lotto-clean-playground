package domain;

import domain.lotto.LotteryStatistics;
import domain.lotto.Lotto;
import domain.lotto.LottoNumber;
import domain.lotto.Lottos;
import domain.lotto.Money;
import domain.lotto.Rank;
import domain.lotto.WinningResult;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LotteryStatisticsTest {

    @Test
    void 통계를_정상적으로_검사한다() {
        // Given
        Lotto winningLotto = createLotto(1, 2, 3, 4, 5, 6);

        Lotto lotto1 = createLotto(1, 2, 3, 7, 8, 9);      // 3개 일치
        Lotto lotto2 = createLotto(1, 2, 3, 4, 7, 8);      // 4개 일치
        Lotto lotto3 = createLotto(7, 8, 9, 10, 11, 12);   // 일치 x

        Lottos lottos = new Lottos(List.of(lotto1, lotto2, lotto3));

        LottoNumber bonusNumber = new LottoNumber(45);

        // When
        WinningResult winningResult = lottos.matchRanks(winningLotto, bonusNumber);
        LotteryStatistics lotteryStatistics = new LotteryStatistics(winningResult);

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
        Lotto winningLotto = createLotto(1, 2, 3, 4, 5, 6);

        Lotto lotto1 = createLotto(1, 2, 3, 7, 8, 9);      // 3개 일치
        Lotto lotto2 = createLotto(1, 2, 3, 4, 7, 8);      // 4개 일치
        Lotto lotto3 = createLotto(7, 8, 9, 10, 11, 12);   // 일치 x

        Lottos lottos = new Lottos(List.of(lotto1, lotto2, lotto3));

        LottoNumber bonusNumber = new LottoNumber(45);

        // When
        WinningResult winningResult = lottos.matchRanks(winningLotto, bonusNumber);
        LotteryStatistics lotteryStatistics = new LotteryStatistics(winningResult);
        Money totalPrize = lotteryStatistics.calculatePrize();

        // Then
        assertEquals(new Money(55000), totalPrize);
    }

    @Test
    void 보너스볼이_일치하고_5개가_일치하면_2등이다() {
        // Given
        Lotto winningLotto = createLotto(1, 2, 3, 4, 5, 6);
        Lotto lotto = createLotto(1, 2, 3, 4, 5, 7);
        Lottos lottos = new Lottos(List.of(lotto));

        LottoNumber bonusNumber = new LottoNumber(7);

        // When
        WinningResult winningResult = lottos.matchRanks(winningLotto, bonusNumber);
        LotteryStatistics lotteryStatistics = new LotteryStatistics(winningResult);

        // Then
        Map<Rank, Integer> result = lotteryStatistics.getStatistics();

        assertEquals(1, result.get(Rank.SECOND));
        assertEquals(0, result.get(Rank.FIVE));
    }

    private Lotto createLotto(int... numbers) {
        return new Lotto(
                Arrays.stream(numbers)
                        .mapToObj(LottoNumber::new)
                        .toList()
        );
    }
}
