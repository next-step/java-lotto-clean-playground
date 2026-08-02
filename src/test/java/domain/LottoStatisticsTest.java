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

    @Test
    @DisplayName("총 당첨 금액과 구입 금액을 기준으로 수익률을 계산한다")
    void calculatesProfitRateBasedOnTotalPrize() {
        Lottos lottos = new Lottos(createLottoNumbersForProfitRate(), 0);
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = new LottoNumber(7);
        PurchaseAmount purchaseAmount = new PurchaseAmount(5000);

        LottoStatistics lottoStatistics = new LottoStatistics(lottos, winningNumbers, bonusNumber);

        assertEquals(1.0, lottoStatistics.calculateProfitRate(purchaseAmount), 0.0001);
    }

    private List<List<Integer>> createLottoNumbersForProfitRate() {
        return List.of(
                List.of(1, 2, 3, 8, 9, 10),
                List.of(1, 2, 8, 9, 10, 11),
                List.of(1, 8, 9, 10, 11, 12),
                List.of(8, 9, 10, 11, 12, 13),
                List.of(14, 15, 16, 17, 18, 19)
        );
    }
}
