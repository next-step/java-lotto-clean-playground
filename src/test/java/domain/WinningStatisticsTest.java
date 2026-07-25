package domain;

<<<<<<< HEAD
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningStatisticsTest {

    @Test
    @DisplayName("calculate profit rate")
    void calculateProfitRate() {
        Lottos lottos = new Lottos();
        lottos.add(new Lotto(List.of(1, 2, 3, 4, 5, 6)));

        WinningLottoNumber winningLottoNumber = new WinningLottoNumber(List.of(1, 2, 3, 4, 5, 6));
        BonusBall bonusBall = new BonusBall(7, List.of(1, 2, 3, 4, 5, 6));

        WinningStatistics statistics = new WinningStatistics(lottos, winningLottoNumber, bonusBall);

        double profitRate = statistics.calculateProfitRate(1000);

        assertThat(profitRate).isEqualTo(2000000.0);
    }
=======
public class WinningStatisticsTest {
>>>>>>> ea80e16 (feat : 5단ãã계 리팩토ã링 및 테스트 코드 추가)
}
