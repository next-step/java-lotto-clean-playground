package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultTest {

    @DisplayName("수익률을 계산한다.")
    @Test
    void calculateProfitRate() {
        ArrayList<LottoWinningType> winningTypes = new ArrayList<>(List.of(LottoWinningType.FIFTH_PLACE));
        LottoStatistics lottoStatistics = new LottoStatistics();
        lottoStatistics.countMatches(winningTypes);

        int purchaseAmount = 8000;
        LottoResult lottoResult = new LottoResult(lottoStatistics, purchaseAmount);

        double profitRate = lottoResult.calculateProfitRate();

        assertThat(profitRate).isEqualTo(0.625);
    }

    @DisplayName("여러 당첨 건에 대한 수익률을 계산한다.")
    @Test
    void calculateProfitRateWithMultipleWinnings() {
        ArrayList<LottoWinningType> winningTypes = new ArrayList<>(List.of(
                LottoWinningType.FOURTH_PLACE,
                LottoWinningType.FIFTH_PLACE
        ));
        LottoStatistics lottoStatistics = new LottoStatistics();
        lottoStatistics.countMatches(winningTypes);

        int purchaseAmount = 10000;
        LottoResult lottoResult = new LottoResult(lottoStatistics, purchaseAmount);

        double profitRate = lottoResult.calculateProfitRate();
        assertThat(profitRate).isEqualTo(5.5);
    }

    @DisplayName("당첨금이 없을 때 수익률은 0이다.")
    @Test
    void calculateProfitRateWithNoWinnings() {
        ArrayList<LottoWinningType> winningTypes = new ArrayList<>();
        LottoStatistics lottoStatistics = new LottoStatistics();
        lottoStatistics.countMatches(winningTypes);

        int purchaseAmount = 1000;
        LottoResult lottoResult = new LottoResult(lottoStatistics, purchaseAmount);

        double profitRate = lottoResult.calculateProfitRate();

        assertThat(profitRate).isEqualTo(0.0);
    }
}
