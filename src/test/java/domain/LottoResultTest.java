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
        Map<Integer, Integer> matchResult = Map.of(
            3, 2, // 번호 3개 맞은 게 2장 => 5000 * 2 = 10000
            4, 1, // 번호 4개 맞은 게 1장 => 50000 * 1 = 50000
            5, 0, // 번호 5개 맞은 게 0장 => 1500000 * 0 = 0
            6, 0 // 번호 6개 맞은 게 0장 => 2000000000 * 0 = 0
        );

        //When
        int totalPrize = LottoResult.calculateTotalPrize(matchResult);

        //Then
        assertThat(totalPrize).isEqualTo(60000);
    }

    @Test
    @DisplayName("총 상금이 5000, 사용 금액이 14000일 때 수익률은 0.36으로 계산된다")
    void return_correct_calculateRateOfReturn() {
        //Given
        int totalPrize = 5000;
        int paidMoney = 14000;

        //When
        double rateOfReturn = LottoResult.calculateRateOfReturn(totalPrize, paidMoney);

        //Then
        assertThat(rateOfReturn).isEqualTo(0.36, within(0.01));
    }
}
