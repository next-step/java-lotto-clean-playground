package domain.winning;

import static org.assertj.core.api.Assertions.assertThat;

import domain.lotto.Lotto;
import domain.lotto.LottoNumber;
import domain.lotto.Lottos;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningStatisticsTest {

    @Test
    @DisplayName("calculate profit rate")
    void calculateProfitRate() {
        Lottos lottos = new Lottos();

        List<LottoNumber> lottoNumbers = Stream.of(1, 2, 3, 4, 5, 6)
                .map(LottoNumber::new)
                .toList();

        lottos.add(new Lotto(lottoNumbers));

        LottoNumber bonusNumber = new LottoNumber(7);
        WinningLotto winningLottoNumber = new WinningLotto(List.of(1, 2, 3, 4, 5, 6));
        BonusBall bonusBall = new BonusBall(bonusNumber, List.of(1, 2, 3, 4, 5, 6));

        WinningStatistics statistics = new WinningStatistics(lottos, winningLottoNumber, bonusBall);
        double profitRate = statistics.calculateProfitRate(1000);

        assertThat(profitRate).isEqualTo(2000000.0);
    }
}
