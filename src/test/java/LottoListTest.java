import model.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.within;

public class LottoListTest {
    @Nested
    @DisplayName("Test #1 - 기본 기능 테스트")
    class GeneralTest {
        @Test
        @DisplayName("로또 목록에 대해 관련 정보의 반환을 요청할 경우 올바른 정보가 반환되어야 한다.")
        void should_returnLottoListInfo() {
            LottoList lottoList = new LottoList(Arrays.asList(
                    new Lotto(Arrays.asList(1, 4, 9, 16, 25, 45)),
                    new Lotto(Arrays.asList(3, 8, 10, 11, 12, 13))
            ));

            assertThat(lottoList.getValue())
                    .usingRecursiveComparison()
                    .isEqualTo(Arrays.asList(
                            new Lotto(Arrays.asList(1, 4, 9, 16, 25, 45)),
                            new Lotto(Arrays.asList(3, 8, 10, 11, 12, 13))
                    ));
            assertThat(lottoList.size()).isEqualTo(2);
        }
    }

    @Nested
    @DisplayName("Test #2 - 등수 판정 테스트")
    class LottoPrizeCalculationTest {
        @Test
        @DisplayName("로또 목록에 대해 각 등수의 복권이 몇 개인지를 정확히 판정하여 결과를 반환하여야 한다.")
        void should_calculatePrizeCountCorrectly() {
            LottoList lottoList = new LottoList(Arrays.asList(
                    new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)),
                    new Lotto(Arrays.asList(1, 2, 4, 5, 6, 7)),
                    new Lotto(Arrays.asList(1, 8, 9, 10, 11, 12)),
                    new Lotto(Arrays.asList(1, 2, 3, 10, 11, 12)),
                    new Lotto(Arrays.asList(1, 2, 3, 4, 13, 14)),
                    new Lotto(Arrays.asList(3, 5, 6, 45, 44, 11)),
                    new Lotto(Arrays.asList(1, 2, 3, 4, 5, 8))
            ));
            Lotto winningLotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
            BonusNumber bonusNumber = new BonusNumber(7);
            WinningLottoInfo winningLottoInfo = new WinningLottoInfo(winningLotto, bonusNumber);

            LottoPrizeResult lottoPrizeResult = lottoList.getLottoResultByWinningLottoInfo(winningLottoInfo);
            List<Integer> prizeCounts = new ArrayList<>();

            lottoPrizeResult.getValue().forEach((key, value) -> {
                prizeCounts.add(value);
            });

            double profitRate = lottoPrizeResult.getProfitRate();

            assertThat(prizeCounts).isEqualTo(Arrays.asList(2, 1, 1, 1, 1));
            assertThat(profitRate).isCloseTo(290222.85714285716, within(0.000001));
        }
    }
}
