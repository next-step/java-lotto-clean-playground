import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StaticsTest {
    private final Statics statics = new Statics();

    @Test
    void 당첨_개수_계산_테스트() {
        List<Lotto> lottos = Arrays.asList(
                new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)),
                new Lotto(Arrays.asList(10, 11, 12, 13, 14, 15))
        );
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;

        Map<Rank, Long> result = statics.calcWinningLottos(lottos, winningNumbers, bonusNumber);

        assertEquals(1, result.get(Rank.FIRST)); // 첫 번째 로또가 6개 일치
        assertEquals(0, result.get(Rank.FIFTH)); // 두 번째 로또는 당첨 X
    }

    @Test
    void 수익률_계산_테스트() {
        Map<Rank, Long> winingLottos = Map.of(Rank.FIFTH, 1L, Rank.FOURTH, 1L);
        int totalLottoAmount = 2000; // 2개 구매 (2000원)

        double profitRate = statics.calcProfitRate(winingLottos, totalLottoAmount);

        assertEquals(55_000 / 2000.0, profitRate, 0.001); // 50000 + 5000
    }
}
