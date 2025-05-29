package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoResultTest {
    @Test
    @DisplayName("당첨 결과에 따라 총 상금을 계산한다")
    void return_correct_calculateTotalPrize() {
        //Given
        MatchResult matchResult = new MatchResult(Map.of(
            Rank.FOURTH, 2,
            Rank.THIRD, 0,
            Rank.SECOND, 0,
            Rank.FIRST, 0
        ));

        //When
        Prize totalPrize = LottoResult.calculateTotalPrize(matchResult);

        //Then
        assertThat(totalPrize).isEqualTo(10000);
    }

    @Test
    @DisplayName("총 상금이 5000, 사용 금액이 14000일 때 수익률은 0.36으로 계산된다")
    void return_correct_calculateRateOfReturn() {
        //Given
        Prize totalPrize = Prize.from(5000);
        int paidMoney = 14000;

        //When
        double rateOfReturn = LottoResult.calculateRateOfReturn(totalPrize, paidMoney);

        //Then
        assertThat(rateOfReturn).isEqualTo(0.36, within(0.01));
    }
}
