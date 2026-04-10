package domain;

import dto.LottoStatistics;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class LottosTest {
    @Test
    void 우승_로또와_비교해서_정확한_통계를_제공한다() {
        //given
        List<LottoNumber> threeMatched = generateLottoList(1, 2, 3, 45, 44, 43);
        List<LottoNumber> fourMatched = generateLottoList(1, 2, 3, 4, 45, 44);
        List<LottoNumber> fiveMatched = generateLottoList(1, 2, 3, 4, 5, 45);
        List<LottoNumber> sixMatched = generateLottoList(1, 2, 3, 4, 5, 6);
        List<LottoNumber> bonusFiveMatched = generateLottoList(1, 2, 3, 4, 5, 40);
        List<Lotto> lottoList = List.of(
                new Lotto(threeMatched),
                new Lotto(fourMatched), new Lotto(fourMatched),
                new Lotto(fiveMatched), new Lotto(fiveMatched), new Lotto(fiveMatched),
                new Lotto(bonusFiveMatched), new Lotto(bonusFiveMatched), new Lotto(bonusFiveMatched), new Lotto(bonusFiveMatched),
                new Lotto(sixMatched), new Lotto(sixMatched), new Lotto(sixMatched), new Lotto(sixMatched), new Lotto(sixMatched)
        );
        Lottos lottos = new Lottos(lottoList);

        List<LottoNumber> winningNumbers = generateLottoList(1, 2, 3, 4, 5, 6);
        Lotto winningLotto = new Lotto(winningNumbers);
        LottoNumber bonusNumber = LottoNumber.valueOf(40);
        //when
        LottoWinningResult lottoWinningResult = lottos.generateWinningResult(winningLotto, bonusNumber);
        Map<LottoRank, Integer> matchedCount = lottoWinningResult.getMatchedCounts();
        BigDecimal profitRate = lottoWinningResult.calculateProfitRate();
        LottoStatistics lottoStatistics = LottoStatistics.of(matchedCount, profitRate);

        //then
        assertAll(
                () -> assertThat(lottoStatistics.matchedCount().get(LottoRank.THREE_MATCHED)).isEqualTo(1),
                () -> assertThat(lottoStatistics.matchedCount().get(LottoRank.FOUR_MATCHED)).isEqualTo(2),
                () -> assertThat(lottoStatistics.matchedCount().get(LottoRank.FIVE_MATCHED)).isEqualTo(3),
                () -> assertThat(lottoStatistics.matchedCount().get(LottoRank.BONUS_FIVE_MATCHED)).isEqualTo(4),
                () -> assertThat(lottoStatistics.matchedCount().get(LottoRank.SIX_MATCHED)).isEqualTo(5)
        );
    }

    private List<LottoNumber> generateLottoList(int... num) {
        return Arrays.stream(num)
                .mapToObj(LottoNumber::valueOf)
                .toList();
    }
}
