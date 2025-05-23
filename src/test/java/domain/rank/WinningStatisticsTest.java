package domain.rank;

import static org.assertj.core.api.Assertions.assertThat;
import static support.LottoTestHelper.lotto;

import domain.lotto.Lottos;
import domain.money.Money;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningStatisticsTest {

    @Test
    @DisplayName("구매한 로또 번호와 지난 당첨 번호의 비교해 등수별 당첨 통계를 정확히 보여준다.")
    void shouldReturnWinningStatistics_whenComparingPurchasedLottoAndLastWinningNumber() {
        // given
        WinningLotto winningLotto = new WinningLotto("1,2,3,4,5,6", "7");

        Lottos purchasedLottos = new Lottos(List.of(
                lotto(1, 2, 3, 4, 5, 6),
                lotto(1, 2, 3, 4, 5, 7),
                lotto(1, 2, 3, 4, 5, 8),
                lotto(1, 2, 3, 4, 8, 9),
                lotto(1, 2, 3, 8, 9, 10),
                lotto(10, 11, 12, 13, 14, 15)
        ));

        // when
        WinningStatistics statistics = new WinningStatistics(winningLotto, purchasedLottos);

        // then
        assertThat(Rank.values())
                .extracting(statistics::getCount)
                .containsExactly(1, 1, 1, 1, 1, 1);
    }

    @Test
    @DisplayName("1등과 2등에 해당하는 로또가 각각 1장 있을 때 총 당첨 금액을 계산한다.")
    void shouldCalculateTotalPrize_whenFirstAndSecondExists() {
        // given
        WinningLotto winningLotto = new WinningLotto("1,2,3,4,5,6", "7");
        Lottos purchasedLottos = new Lottos(List.of(
                lotto(1, 2, 3, 4, 5, 6),
                lotto(1, 2, 3, 4, 5, 7)
        ));

        WinningStatistics statistics = new WinningStatistics(winningLotto, purchasedLottos);

        // when
        Money totalPrize = statistics.calculateTotalPrize();
        BigDecimal expected = Rank.FIRST.getPrize().add(Rank.SECOND.getPrize()).amount();

        // then
        assertThat(totalPrize.amount())
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("총 당첨 금액과 구매 금액으로부터 수익률을 정확히 계산한다.")
    void shouldCalculateProfitRate_whenGivenTotalPrizeAndPurchaseAmount() {
        // given
        WinningLotto winningLotto = new WinningLotto("1,2,3,4,5,6", "7");
        Lottos purchasedLottos = new Lottos(List.of(
                lotto(1, 2, 3, 4, 5, 6),
                lotto(1, 2, 3, 4, 5, 7)
        ));
        WinningStatistics statistics = new WinningStatistics(winningLotto, purchasedLottos);
        String purchaseAmount = "3000";

        // when
        Money profitRate = statistics.calculateProfitRate(purchaseAmount);

        BigDecimal totalPrizeAmount = Rank.FIRST.getPrize().add(Rank.SECOND.getPrize()).amount();
        BigDecimal expectedProfitRate = totalPrizeAmount.divide(
                BigDecimal.valueOf(Long.parseLong(purchaseAmount)),
                2,
                RoundingMode.HALF_UP);

        // then
        assertThat(profitRate.amount())
                .isEqualTo(expectedProfitRate);
    }
}
