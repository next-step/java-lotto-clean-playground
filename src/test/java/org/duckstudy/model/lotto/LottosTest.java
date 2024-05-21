package org.duckstudy.model.lotto;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import java.util.stream.Stream;
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
        @DisplayName("당첨 로또 번호를 입력하면 로또 묶음의 당첨 결과를 계산한다")
        void calculateWinningResultWhenInputWinningLotto() {
            Lotto winningLotto = Stream.of(1, 2, 3, 4, 5, 6)
                    .map(LottoNumber::new)
                    .collect(collectingAndThen(toList(), Lotto::new));

            List<Lotto> lottos = Stream.of(
                            Stream.of(1, 2, 3, 4, 5, 6),
                            Stream.of(1, 2, 3, 7, 8, 9),
                            Stream.of(1, 2, 3, 10, 11, 12)
                    )
                    .map(stream -> stream
                            .map(LottoNumber::new)
                            .collect(collectingAndThen(toList(), Lotto::new)))
                    .toList();
            Lottos compareLottos = new Lottos(lottos);

            LottoResult lottoResult = compareLottos.calculateWinningResult(winningLotto);

            assertAll(
                    () -> assertThat(lottoResult.getResult()).containsEntry(3, 2),
                    () -> assertThat(lottoResult.getResult()).containsEntry(6, 1)
            );
        }
    }
}
