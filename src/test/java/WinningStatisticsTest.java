import domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class WinningStatisticsTest {
    @Test
    @DisplayName("등수 별 통계를 정확히 집계한다.")
    void comparingLottosTest() {
        //given
        LottoNumber winninggLotto = new LottoNumber(List.of(1, 2, 3, 4, 5, 6));
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

        //then
        Map<Rank, WinnerNum> result = testWinningStatistics.getWinningStatistics();
        assertEquals(1, result.get(Rank.FIRST_PLACE).getWinnerNum());
        assertEquals(1, result.get(Rank.SECOND_PLACE_BONUS).getWinnerNum());
        assertEquals(1, result.get(Rank.SECOND_PLACE).getWinnerNum());
        assertEquals(1, result.get(Rank.MISS).getWinnerNum());



    }
    private LottoNumber createLotto(Integer... numbers) {
        return new LottoNumber(List.of(numbers));
    }
}
