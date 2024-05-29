package org.duckstudy.model.lotto;

import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.assertThat;
import static org.duckstudy.model.lotto.Lotto.createLottoForTest;

import java.util.List;
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
        @DisplayName("구매 가격을 입력하면 가격에 맞는 로또 묶음을 생성한다")
        void createLottosWhenInputPrice() {
            Price price = new Price(10000);

            Lottos lottos = Lottos.generateLottosByPrice(price);

            assertThat(lottos.getLottos()).hasSize(10);
        }
    }

    @Nested
    @DisplayName("로또 당첨 결과 테스트")
    class LottosWinningTest {

        @Test
        @DisplayName("당첨된 로또 번호를 입력하면 당첨된 로또 묶음의 결과를 계산한다")
        void calculateWinningResultWhenInputWinningLotto() {
            Lotto winningLotto = createLottoForTest(1, 2, 3, 4, 5, 6);
            Lottos totalLottos = new Lottos(List.of(
                    winningLotto,
                    createLottoForTest(8, 9, 10, 11, 12, 13),
                    createLottoForTest(14, 15, 16, 17, 18, 19),
                    createLottoForTest(20, 21, 22, 23, 24, 25)
            ));

            LottoResult lottoResult = totalLottos.calculateWinningResult(winningLotto);

            assertThat(lottoResult.getResult())
                    .containsExactly(entry(0, 3), entry(6, 1));
        }
    }
}
