package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import java.util.List;
import org.junit.jupiter.api.Test;

class LottoResultTest {

    @Test
    void 등급별_당첨_개수를_계산한다() {
        LottoResult result = new LottoResult(
                List.of(3, 3, 4, 5, 6, 2, 1, 0)
        );

        assertThat(result.getResults())
                .containsEntry(Rank.FIRST, 1)
                .containsEntry(Rank.SECOND, 1)
                .containsEntry(Rank.THIRD, 1)
                .containsEntry(Rank.FOURTH, 2);
    }

    @Test
    void 세개_미만_일치는_당첨결과에_포함되지_않는다() {
        LottoResult result = new LottoResult(
                List.of(0, 1, 2)
        );

        assertThat(result.getResults())
                .containsEntry(Rank.FIRST, 0)
                .containsEntry(Rank.SECOND, 0)
                .containsEntry(Rank.THIRD, 0)
                .containsEntry(Rank.FOURTH, 0);
    }

    @Test
    void 당첨금과_구입금액으로_수익률을_계산한다() {
        LottoResult result = new LottoResult(
                List.of(3)
        );

        PurchasePrice purchasePrice =
                new PurchasePrice(14000);

        double rate = result.calculateRateOfReturn(purchasePrice);

        assertThat(rate)
                .isCloseTo(0.3571428571, within(0.0000001));
    }
}