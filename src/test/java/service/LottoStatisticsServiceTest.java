package service;

import domain.*;
import org.junit.jupiter.api.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class LottoStatisticsServiceTest {

    @Test
    @DisplayName("로또 매치가 정확하게 계산되는지 테스트")
    void shouldCalculateStatisticsCorrectly() {
        Lottos lottos = new Lottos(List.of(
                new Lotto(List.of(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3), LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(6))),
                new Lotto(List.of(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3), LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(7))),
                new Lotto(List.of(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3), LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(8))),
                new Lotto(List.of(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3), LottoNumber.of(4), LottoNumber.of(9), LottoNumber.of(10))),
                new Lotto(List.of(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3), LottoNumber.of(11), LottoNumber.of(12), LottoNumber.of(13)))
        ));

        WinningLottoNumbers winningNumbers = new WinningLottoNumbers(
                List.of(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3), LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(6)), new BonusBall(7)
        );

        LottoStatisticsService statisticsService = new LottoStatisticsService(lottos, winningNumbers);
        Map<WinningRank, Integer> statistics = statisticsService.getStatistics();

        assertEquals(1, statistics.getOrDefault(WinningRank.SIX_MATCH, 0));
        assertEquals(1, statistics.getOrDefault(WinningRank.FIVE_MATCH_WITH_BONUS, 0));
        assertEquals(1, statistics.getOrDefault(WinningRank.FIVE_MATCH, 0));
        assertEquals(1, statistics.getOrDefault(WinningRank.FOUR_MATCH, 0));
        assertEquals(1, statistics.getOrDefault(WinningRank.THREE_MATCH, 0));
    }

    @Test
    @DisplayName("수익률이 정확하게 계산되는지 테스트")
    void shouldCalculateProfitRateCorrectly() {
        Lottos lottos = new Lottos(List.of(
                new Lotto(List.of(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3), LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(6)))
        ));

        WinningLottoNumbers winningNumbers = new WinningLottoNumbers(
                List.of(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3), LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(6)), new BonusBall(7)
        );

        LottoStatisticsService statisticsService = new LottoStatisticsService(lottos, winningNumbers);
        Amount purchaseAmount = new Amount(5000);

        double expectedProfitRate = 2_000_000_000.00 / 5000;
        assertEquals(expectedProfitRate, statisticsService.calculateProfitRate(purchaseAmount), 0.01);
    }
}
