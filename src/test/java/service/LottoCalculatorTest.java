package service;

import model.LottoRank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class LottoCalculatorTest {
    @Test
    @DisplayName("로또 구매 금액에 따라 구매할 수 있는 로또 티켓 수를 계산한다.")
    void testCalculateTotalLottoTicketCount(){
        int purchaseAmount = 5000;
        int expectedCount = 5;

        int actualCount = LottoCalculator.calculateTotalLottoTicketCount(purchaseAmount);

        assertThat(actualCount).isEqualTo(expectedCount);
    }

    @Test
    @DisplayName("수동 티켓 수에 따른 자동 티켓 수를 계산한다.")
    void testCalculateAutomaticLottoTicketCount(){
        int totalTicketCount = 15;
        int manualTicketCount = 5;
        int expectedCount = 10;

        int actualAutomaticCount = LottoCalculator.calculateAutomaticLottoTicketCount(totalTicketCount,manualTicketCount);

        assertThat(actualAutomaticCount).isEqualTo(expectedCount);
    }

    @Test
    @DisplayName("로또 등수와 해당 등수인 티켓의 개수를 사용해 총 상금을 계산한다.")
    void TestCalculateTotalPrize(){
        Map<LottoRank, Integer> result = new EnumMap<>(LottoRank.class);
        result.put(LottoRank.FIRST, 1);
        result.put(LottoRank.SECOND, 2);
        result.put(LottoRank.THIRD, 3);

        int expectedTotalPrize = LottoRank.FIRST.getPrize() * 1 +
                LottoRank.SECOND.getPrize() * 2 +
                LottoRank.THIRD.getPrize() * 3;

        int actualTotalPrize = LottoCalculator.calculateTotalPrize(result);

        assertThat(actualTotalPrize).isEqualTo(expectedTotalPrize);
    }


    @Test
    @DisplayName("총 상금과 구매 금액을 사용해 수익률을 계산한다.")
    void testCalculateEarningRate(){
        int totalPrize = 10000;
        int purchaseAmount = 5000;
        double expectedEarningRate = 2.0;

        double actualEarningRate = LottoCalculator.calculateEarningRate(totalPrize,purchaseAmount);

        assertThat(actualEarningRate).isEqualTo(expectedEarningRate);
    }

}
