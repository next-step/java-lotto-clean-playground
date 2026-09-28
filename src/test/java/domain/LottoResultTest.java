package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class LottoResultTest {

    @Test
    void 등급별_당첨_개수를_계산한다() {
        LottoResult result = new LottoResult(
                List.of(3, 3, 4, 5, 6, 2, 1, 0)
        );

        assertThat(result.getWinningCount(Rank.FOURTH))
                .isEqualTo(2);

        assertThat(result.getWinningCount(Rank.THIRD))
                .isEqualTo(1);

        assertThat(result.getWinningCount(Rank.SECOND))
                .isEqualTo(1);

        assertThat(result.getWinningCount(Rank.FIRST))
                .isEqualTo(1);
    }

    @Test
    void 세개_미만_일치는_당첨결과에_포함되지_않는다() {
        LottoResult result = new LottoResult(
                List.of(0, 1, 2)
        );

        assertThat(result.getWinningCount(Rank.FOURTH))
                .isZero();

        assertThat(result.getWinningCount(Rank.THIRD))
                .isZero();

        assertThat(result.getWinningCount(Rank.SECOND))
                .isZero();

        assertThat(result.getWinningCount(Rank.FIRST))
                .isZero();
    }

    @Test
    void 당첨금과_구입금액으로_수익률을_계산한다() {
        LottoResult result = new LottoResult(
                List.of(3)
        );

        PurchasePrice purchasePrice =
                new PurchasePrice("14000");

        double rate = result.calculateRateOfReturn(purchasePrice);

        assertThat(rate).isEqualTo(0.35);
    }
}