package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import domain.lotto.Lotto;
import domain.lotto.Lottos;
import domain.winning.BonusBall;
import domain.winning.WinningLotto;
import domain.winning.WinningStatistics;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningStatisticsTest {

    @Test
    @DisplayName("calculate profit rate")
    void calculateProfitRate() {
        Lottos lottos = new Lottos();
        lottos.add(new Lotto(List.of(1, 2, 3, 4, 5, 6)));

        WinningLotto winningLottoNumber = new WinningLotto(List.of(1, 2, 3, 4, 5, 6));
        BonusBall bonusBall = new BonusBall(7, List.of(1, 2, 3, 4, 5, 6));

        WinningStatistics statistics = new WinningStatistics(lottos, winningLottoNumber, bonusBall);

        double profitRate = statistics.calculateProfitRate(1000);

        assertThat(profitRate).isEqualTo(2000000.0);
    }
}
