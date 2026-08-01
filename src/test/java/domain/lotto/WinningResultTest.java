package domain.lotto;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WinningResultTest {
    @Test
    void 등수별_당첨_개수를_정확히_계산한다() {
        //Given
        WinningResult winningResult = new WinningResult(
                List.of(Rank.THREE, Rank.THREE, Rank.FOUR, Rank.NONE)
        );

        //When
        Map<Rank, Integer> result = winningResult.getStatistics();

        //Then
        assertEquals(2, result.get(Rank.THREE));
        assertEquals(1, result.get(Rank.FOUR));
        assertEquals(1, result.get(Rank.NONE));
        assertEquals(0, result.get(Rank.FIVE));
        assertEquals(0, result.get(Rank.SECOND));
        assertEquals(0, result.get(Rank.SIX));
    }

    @Test
    void 당첨된_등수가_없으면_모두_0이다() {
        // Given
        WinningResult winningResult = new WinningResult(List.of());

        // When
        Map<Rank, Integer> result = winningResult.getStatistics();

        // Then
        for (Rank rank : Rank.values()) {
            assertEquals(0, result.get(rank));
        }
    }

    @Test
    void 통계를_정상적으로_검사한다() {
        Lotto winningLottoNumbers = createLotto(1, 2, 3, 4, 5, 6);

        Lotto lotto1 = createLotto(1, 2, 3, 7, 8, 9);      // 3개 일치
        Lotto lotto2 = createLotto(1, 2, 3, 4, 7, 8);      // 4개 일치
        Lotto lotto3 = createLotto(7, 8, 9, 10, 11, 12);   // 일치 x

        Lottos lottos = new Lottos(List.of(lotto1, lotto2, lotto3));
        WinningLotto winningLotto = new WinningLotto(winningLottoNumbers, LottoNumber.from(45));

        WinningResult winningResult = lottos.matchRanks(winningLotto);
        Map<Rank, Integer> result = winningResult.getStatistics();

        assertEquals(1, result.get(Rank.NONE));
        assertEquals(1, result.get(Rank.THREE));
        assertEquals(1, result.get(Rank.FOUR));
        assertEquals(0, result.get(Rank.FIVE));
        assertEquals(0, result.get(Rank.SIX));
    }

    @Test
    void 총_당첨금을_정확히_계산한다() {
        Lotto winningLottoNumbers = createLotto(1, 2, 3, 4, 5, 6);

        Lotto lotto1 = createLotto(1, 2, 3, 7, 8, 9);      // 3개 일치
        Lotto lotto2 = createLotto(1, 2, 3, 4, 7, 8);      // 4개 일치
        Lotto lotto3 = createLotto(7, 8, 9, 10, 11, 12);   // 일치 x

        Lottos lottos = new Lottos(List.of(lotto1, lotto2, lotto3));
        WinningLotto winningLotto = new WinningLotto(winningLottoNumbers, LottoNumber.from(45));

        WinningResult winningResult = lottos.matchRanks(winningLotto);
        Money totalPrize = winningResult.calculatePrize();

        assertEquals(Money.from(55000), totalPrize);
    }

    @Test
    void 보너스볼이_일치하고_5개가_일치하면_2등이다() {
        Lotto winningLottoNumbers = createLotto(1, 2, 3, 4, 5, 6);
        Lotto lotto = createLotto(1, 2, 3, 4, 5, 7);
        Lottos lottos = new Lottos(List.of(lotto));
        WinningLotto winningLotto = new WinningLotto(winningLottoNumbers, LottoNumber.from(7));

        WinningResult winningResult = lottos.matchRanks(winningLotto);
        Map<Rank, Integer> result = winningResult.getStatistics();

        assertEquals(1, result.get(Rank.SECOND));
        assertEquals(0, result.get(Rank.FIVE));
    }

    private Lotto createLotto(int... numbers) {
        return new Lotto(
                Arrays.stream(numbers)
                        .mapToObj(LottoNumber::from)
                        .toList()
        );
    }
}
