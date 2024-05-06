package controller;

import model.Lotto;
import model.LottoList;
import model.UserCount;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LottoControllerTest {
    @Test
    void testCalculateWinningRate() {
        // Given
        LottoController controller = new LottoController();
        List<Integer> matchCounts = Arrays.asList(1, 0, 0, 0, 0, 0);
        String price = "14000";
        double priceNum = 14000.0;
        UserCount userCount = new UserCount(price);

        // When
        double winningRate = controller.calculateWinningRate(matchCounts, userCount);

        // Then
        double expectedRate = (double) (1 * 5000 + 0 * 50000 + 0 * 150000 + 0 * 30000000 + 0 * 2000000000) / priceNum;
        expectedRate = Math.floor(expectedRate * 100) / 100;
        System.out.println("dd" + winningRate);
        assertEquals(expectedRate, winningRate);
    }

    @Test
    void testGenerateLottoList() {
        // Given
        LottoController controller = new LottoController();
        int count = 5;

        // When
        LottoList lottoList = controller.generateLottoList(count, null);

        // Then
        List<Lotto> lottos = lottoList.getLottoList();
        assertEquals(count, lottos.size());

    }
}