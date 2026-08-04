import domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProfitRateTest {
    @Test
    @DisplayName("당첨 통계를 기반으로 총 수익률을 정확히 계산한다.")
    void calculateProfitRateTest() {
        //given
        int price = 4000;
        List<Integer> winninggLotto = List.of(1, 2, 3, 4, 5, 6);
        int testBonusNum = 7;

        Lottos testLottos = new Lottos(List.of(
                createLotto(1, 2, 3, 4, 5, 6), //1등
                createLotto(1, 2, 3, 4, 5, 7), //보너스 2등
                createLotto(1, 2, 3, 4, 5, 8), //2등
                createLotto(8, 9, 10, 11, 12, 13) //MISS
        ));
        WinningStatistics testWinningStatistics = new WinningStatistics();

        //when
        testWinningStatistics.compareLottos(winninggLotto, testLottos, testBonusNum);

        ProfitRate profitRate = new ProfitRate(price, testWinningStatistics);

        //then
        long expectedTotalProfit = (long) Rank.FIRST_PLACE.getPrize() + Rank.SECOND_PLACE_BONUS.getPrize() + Rank.SECOND_PLACE.getPrize() + Rank.MISS.getPrize();
        double expectedProfitRate = (double) expectedTotalProfit / price;

        assertEquals(expectedTotalProfit, profitRate.getTotalProfit());
        assertEquals(expectedProfitRate, profitRate.getProfitRate());
    }
    private LottoNumber createLotto(Integer... numbers) {
        return new LottoNumber(List.of(numbers));
    }
}
