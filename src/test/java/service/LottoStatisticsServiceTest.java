package service;

import domain.*;
import org.junit.jupiter.api.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class LottoStatisticsServiceTest {

    private final LottoStatisticsService statisticsService = new LottoStatisticsService();

    @Test
    @DisplayName("로또 매치가 정확하게 계산되는지 테스트")
    void shouldCalculateStatisticsCorrectly() {
        Lottos lottos = new Lottos(List.of(
                new Lotto(List.of(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3), LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(6))), // 6개 일치
                new Lotto(List.of(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3), LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(7))), // 5개 + 보너스볼
                new Lotto(List.of(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3), LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(8))), // 5개 일치
                new Lotto(List.of(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3), LottoNumber.of(4), LottoNumber.of(9), LottoNumber.of(10))), // 4개 일치
                new Lotto(List.of(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3), LottoNumber.of(11), LottoNumber.of(12), LottoNumber.of(13))) // 3개 일치
        ));

        Lotto winningLotto = new Lotto(List.of(
                LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3),
                LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(6)
        ));
        int bonusNumber = 7;
        WinningLottoNumbers winningNumbers = new WinningLottoNumbers(winningLotto, bonusNumber);

        LottoStatistics statistics = statisticsService.calculateStatistics(lottos, winningNumbers);

        assertEquals(1, statistics.getStatistics().getOrDefault(WinningRank.SIX_MATCH, 0));
        assertEquals(1, statistics.getStatistics().getOrDefault(WinningRank.FIVE_MATCH_WITH_BONUS, 0));
        assertEquals(1, statistics.getStatistics().getOrDefault(WinningRank.FIVE_MATCH, 0));
        assertEquals(1, statistics.getStatistics().getOrDefault(WinningRank.FOUR_MATCH, 0));
        assertEquals(1, statistics.getStatistics().getOrDefault(WinningRank.THREE_MATCH, 0));
    }

    @Test
    @DisplayName("수익률이 정확하게 계산되는지 테스트")
    void shouldCalculateProfitRateCorrectly() {
        Lottos lottos = new Lottos(List.of(
                new Lotto(List.of(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3), LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(6)))
        ));

        Lotto winningLotto = new Lotto(List.of(
                LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3),
                LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(6)
        ));

        int bonusNumber = 7;
        WinningLottoNumbers winningNumbers = new WinningLottoNumbers(winningLotto, bonusNumber);

        LottoStatistics statistics = statisticsService.calculateStatistics(lottos, winningNumbers);
        LottoPurchaseAmount purchaseLottoPurchaseAmount = new LottoPurchaseAmount(5000);

        double expectedProfitRate = 2_000_000_000.00 / 5000;
        assertEquals(expectedProfitRate, statistics.calculateProfitRate(purchaseLottoPurchaseAmount), 0.01);
    }
}
