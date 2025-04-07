package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StatisticsTest {

  @Test
  @DisplayName("로또 번호와 당첨 번호를 비교하여 당첨 등수를 제대로 계산하고 상금과 수익률을 계산한다.")
  void calculateMatchCounts() {
    // Given
    List<Lotto> lottoList = Arrays.asList(
        Lotto.createManual(Arrays.asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
            new LottoNumber(5), new LottoNumber(6))),
        Lotto.createManual(Arrays.asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
            new LottoNumber(5), new LottoNumber(7))),
        Lotto.createManual(Arrays.asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
            new LottoNumber(5), new LottoNumber(9))),
        Lotto.createManual(Arrays.asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
            new LottoNumber(8), new LottoNumber(9))),
        Lotto.createManual(Arrays.asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(9),
            new LottoNumber(10), new LottoNumber(11))),
        Lotto.createManual(
            Arrays.asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(9), new LottoNumber(10),
                new LottoNumber(11), new LottoNumber(12))),
        Lotto.createManual(
            Arrays.asList(new LottoNumber(1), new LottoNumber(9), new LottoNumber(10), new LottoNumber(11),
                new LottoNumber(12), new LottoNumber(13)))
    );

    Lottos lottos = new Lottos(lottoList, 7, 7, new LottoPrice(7000));

    WinningNumbers winningNumbers = new WinningNumbers(
        Arrays.asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4),
            new LottoNumber(5), new LottoNumber(6)));
    BonusNumber bonusNumber = new BonusNumber(new LottoNumber(7), winningNumbers);
    LottoPrice investedMoney = new LottoPrice(7000);
    Statistics statistics = new Statistics(lottos, winningNumbers, investedMoney, bonusNumber);

    // When
    Map<WinningRank, Integer> matchCounts = statistics.calculateMatchCounts();
    long totalPrize = statistics.calculateTotalPrize(matchCounts);
    double profitRate = statistics.calculateProfitRate(matchCounts);

    // Then: 매칭된 로또 개수 검증
    assertThat(matchCounts.get(WinningRank.SIX)).isEqualTo(1);
    assertThat(matchCounts.get(WinningRank.FIVE_WITH_BONUS)).isEqualTo(1);
    assertThat(matchCounts.get(WinningRank.FIVE)).isEqualTo(1);
    assertThat(matchCounts.get(WinningRank.FOUR)).isEqualTo(1);
    assertThat(matchCounts.get(WinningRank.THREE)).isEqualTo(1);
    assertThat(matchCounts.get(WinningRank.NONE)).isEqualTo(2);

    // Then: 총 상금 계산
    assertThat(totalPrize).isEqualTo(
        WinningRank.SIX.getPrize() +
        WinningRank.FIVE_WITH_BONUS.getPrize() +
        WinningRank.FIVE.getPrize() +
        WinningRank.FOUR.getPrize() +
        WinningRank.THREE.getPrize()
    );

    // Then: 수익률 계산
    assertThat(profitRate).isEqualTo((double) totalPrize / investedMoney.getPurchaseMoney());
  }
}
