package org.duckstudy.model.lotto;

import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.assertThat;
import static org.duckstudy.model.lotto.constant.WinningRank.FIRST;
import static org.duckstudy.model.lotto.constant.WinningRank.NONE;
import static org.duckstudy.model.lotto.constant.WinningRank.SECOND;

import java.util.List;
import java.util.Set;
import org.duckstudy.model.Price;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("로또 테스트")
class LottosTest {

    @Nested
    @DisplayName("로또 묶음 생성 테스트")
    class LottosCreationTest {

        @Test
        @DisplayName("구매 개수를 입력하면 가격에 맞는 로또 묶음을 생성한다")
        void createLottosWhenInputPrice() {

            Price purchasePrice = new Price(10000);
            int lottoCount = purchasePrice.calculateLottoCount()
                    .getCount();

            Lottos lottos = Lottos.generateLottos(lottoCount);

            assertThat(lottos.getLottos()).hasSize(10);
        }
    }

    @Nested
    @DisplayName("로또 당첨 결과 테스트")
    class LottosWinningTest {

        @Test
        @DisplayName("당첨된 로또 번호와 보너스 번호를 입력하면 당첨된 로또 묶음의 결과를 계산한다")
        void calculateWinningResultWhenInputWinningLotto() {

            Lotto winningLotto = Lotto.from(Set.of(1, 2, 3, 4, 5, 6));
            LottoNumber bonusNumber = LottoNumber.valueOf(7);
            Lottos totalLottos = new Lottos(List.of(
                    winningLotto,
                    Lotto.from(Set.of(1, 2, 3, 4, 5, 7)),
                    Lotto.from(Set.of(8, 9, 10, 11, 12, 13)),
                    Lotto.from(Set.of(14, 15, 16, 17, 18, 19)),
                    Lotto.from(Set.of(20, 21, 22, 23, 24, 25))
            ));

            LottoStatistics lottoStatistics = totalLottos.accumulateLottoResult(winningLotto, bonusNumber);

            assertThat(lottoStatistics.getStatistics())
                    .containsExactly(
                            entry(NONE, 3),
                            entry(SECOND, 1),
                            entry(FIRST, 1)
                    );
        }
    }

    @Nested
    @DisplayName("로또 묶음 병합 테스트")
    class LottosMergeTest {

        @Test
        @DisplayName("로또 묶음을 병합하면 하나의 로또 묶음으로 생성한다")
        void mergeLottos() {

            Lottos lottos1 = new Lottos(List.of(
                    Lotto.from(Set.of(1, 2, 3, 4, 5, 6))
            ));

            Lottos lottos2 = new Lottos(List.of(
                    Lotto.from(Set.of(7, 8, 9, 10, 11, 12))
            ));

            Lottos mergedLottos = lottos1.merge(lottos2);

            assertThat(mergedLottos.getLottos()).hasSize(2);
        }
    }
}
