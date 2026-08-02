package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LottoStatisticsTest {
    @Test
    @DisplayName("로또 당첨 결과를 등수별로 집계한다")
    void calculatesWinningCountForEachRank() {
        List<Integer> winningNumberValues = List.of(1, 2, 3, 4, 5, 6);
        WinningNumbers winningNumbers = new WinningNumbers(winningNumberValues);
        LottoNumber bonusNumber = new LottoNumber(7);

        List<List<Integer>> lottoNumbers = List.of(
                List.of(1, 2, 3, 4, 5, 6),
                List.of(1, 2, 3, 4, 5, 7),
                List.of(1, 2, 3, 4, 5, 8),
                List.of(1, 2, 3, 4, 8, 9),
                List.of(1, 2, 3, 8, 9, 10)
        );

        Lottos lottos = new Lottos(lottoNumbers, 0);
        LottoStatistics lottoStatistics = new LottoStatistics(lottos, winningNumbers, bonusNumber);

        assertEquals(1, lottoStatistics.getWinningCount(LottoRank.FIRST));
        assertEquals(1, lottoStatistics.getWinningCount(LottoRank.SECOND));
        assertEquals(1, lottoStatistics.getWinningCount(LottoRank.THIRD));
        assertEquals(1, lottoStatistics.getWinningCount(LottoRank.FOURTH));
        assertEquals(1, lottoStatistics.getWinningCount(LottoRank.FIFTH));
    }
}
