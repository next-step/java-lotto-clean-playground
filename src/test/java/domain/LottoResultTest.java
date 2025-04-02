package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class LottoResultTest {

    @Test
    @DisplayName("1등 당첨자가 있으면 1등이 정상적으로 집계되어야 한다")
    void firstPrizeWinner() {
        // 당첨 번호 설정
        WinningLotto winningLotto = new WinningLotto(
                new Lotto(Arrays.asList(
                        new LottoNumber(1),
                        new LottoNumber(2),
                        new LottoNumber(3),
                        new LottoNumber(4),
                        new LottoNumber(5),
                        new LottoNumber(6)
                )),
                new LottoNumber(7) // 보너스 번호
        );

        // 로또 리스트 (1등 1개, 나머지 꽝)
        List<Lotto> purchasedLottos = Arrays.asList(
                new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)), // 1등
                new Lotto(Arrays.asList(10, 11, 12, 13, 14, 15)), // 꽝
                new Lotto(Arrays.asList(20, 21, 22, 23, 24, 25))  // 꽝
        );

        LottoResult result = LottoResult.from(purchasedLottos, winningLotto);

        Map<Prize, Integer> matchCountMap = result.getMatchCountMap();
        assertThat(matchCountMap.get(Prize.FIRST_PRIZE)).isEqualTo(1);
    }

    @Test
    @DisplayName("2등과 3등이 정확히 구분되어야 한다")
    void secondAndThirdPrizeWinner() {
        WinningLotto winningLotto = new WinningLotto(
                new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)),
                new LottoNumber(7) // 보너스 번호
        );

        List<Lotto> purchasedLottos = Arrays.asList(
                new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)), // 1등
                new Lotto(Arrays.asList(1, 2, 3, 4, 5, 7)), // 2등 (보너스 일치)
                new Lotto(Arrays.asList(1, 2, 3, 4, 5, 8))  // 3등 (보너스 불일치)
        );

        LottoResult result = LottoResult.from(purchasedLottos, winningLotto);
        Map<Prize, Integer> matchCountMap = result.getMatchCountMap();

        assertThat(matchCountMap.get(Prize.FIRST_PRIZE)).isEqualTo(1);
        assertThat(matchCountMap.get(Prize.SECOND_PRIZE)).isEqualTo(1);
        assertThat(matchCountMap.get(Prize.THIRD_PRIZE)).isEqualTo(1);
    }

    @Test
    @DisplayName("총 당첨 금액이 정확히 계산되어야 한다")
    void totalPrizeCalculation() {
        WinningLotto winningLotto = new WinningLotto(
                new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)),
                new LottoNumber(7) // 보너스 번호
        );

        List<Lotto> purchasedLottos = Arrays.asList(
                new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)), // 1등 (20억)
                new Lotto(Arrays.asList(1, 2, 3, 4, 5, 7)), // 2등 (3천만)
                new Lotto(Arrays.asList(1, 2, 3, 4, 5, 8))  // 3등 (150만)
        );

        LottoResult result = LottoResult.from(purchasedLottos, winningLotto);

        long totalPrize = result.calculateTotalPrize();
        long expectedPrize = 2_000_000_000L + 30_000_000L + 1_500_000L;

        assertThat(totalPrize).isEqualTo(expectedPrize);
    }
}
