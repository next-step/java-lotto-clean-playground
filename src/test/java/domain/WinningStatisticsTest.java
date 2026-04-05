package domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WinningStatisticsTest {
    private static final WinningLotto WINNING_LOTTO = new WinningLotto(createLotto(1, 2, 3, 4, 5, 6), new BonusBall(new LottoNumber(7)));
    private static final Lotto THREE_MATCH_LOTTO = createLotto(1, 2, 3, 7, 8, 9);
    private static final Lotto FOUR_MATCH_LOTTO = createLotto(1, 2, 3, 4, 10, 11);
    private static final Lotto FIVE_MATCH_LOTTO = createLotto(1, 2, 3, 4, 5, 12);
    private static final Lotto FIVE_BONUS_MATCH_LOTTO = createLotto(1, 2, 3, 4, 5, 7);

    @Test
    void 당첨_결과별_개수를_집계한다() {
        Lottos lottos = new Lottos(List.of(
                THREE_MATCH_LOTTO,
                FOUR_MATCH_LOTTO,
                FIVE_MATCH_LOTTO,
                FIVE_BONUS_MATCH_LOTTO
        ));

        WinningStatistics winningStatistics = WinningStatistics.from(lottos, WINNING_LOTTO);

        assertThat(winningStatistics.countOf(Rank.THREE_MATCH)).isEqualTo(1);
        assertThat(winningStatistics.countOf(Rank.FOUR_MATCH)).isEqualTo(1);
        assertThat(winningStatistics.countOf(Rank.FIVE_MATCH)).isEqualTo(1);
        assertThat(winningStatistics.countOf(Rank.FIVE_BONUS_MATCH)).isEqualTo(1);
    }

    @Test
    void 총_당첨금을_계산한다() {
        Lottos lottos = new Lottos(List.of(
                THREE_MATCH_LOTTO,
                THREE_MATCH_LOTTO,
                FOUR_MATCH_LOTTO
        ));

        WinningStatistics winningStatistics = WinningStatistics.from(lottos, WINNING_LOTTO);

        assertThat(winningStatistics.calculateTotalPrize()).isEqualTo(60000);
    }

    @Test
    void 수익률을_계산한다() {
        Lottos lottos = new Lottos(List.of(
                THREE_MATCH_LOTTO,
                FOUR_MATCH_LOTTO
        ));
        PurchaseAmount purchaseAmount = new PurchaseAmount(10000);

        WinningStatistics winningStatistics = WinningStatistics.from(lottos, WINNING_LOTTO);

        assertThat(winningStatistics.calculateProfitRate(purchaseAmount)).isEqualTo(5.5);
    }

    private static Lotto createLotto(int... numbers) {
        return new Lotto(Arrays.stream(numbers)
                .mapToObj(LottoNumber::new)
                .toList());
    }
}
