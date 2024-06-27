package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LottoNumberGeneratorTest {

    private final LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator();

    @Nested
    @DisplayName("로또 생성 테스트")
    class generateLotto {

        @ParameterizedTest(name = "구입금액이 {0}이면 로또는 {1}개 만들어진다.")
        @CsvSource({"14000,14", "25200,25", "900,0", "3000,3"})
        @DisplayName("구입금액으로 1000원짜리 로또를 몇 개 살 수 있는지 구할 수 있다.")
        void getNumberOfLottosTest(int inputPrice, int expectedNumberOfLotto) {
            // given
            PurchasePrice purchasePrice = new PurchasePrice(inputPrice);
            // when
            Lottos lottos = lottoNumberGenerator.generateLottos(purchasePrice);
            // then
            assertThat(lottos.getSize())
                .isEqualTo(expectedNumberOfLotto);
        }


    }

}
