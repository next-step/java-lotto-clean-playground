package service;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import domain.Lottos;
import domain.PurchasePrice;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoServiceTest {

    private final LottoService lottoService = new LottoService();

    private static Stream<Arguments> methodSourceOfGenerateLottos() {
        return Stream.of(
            Arguments.arguments(14000, 14),
            Arguments.arguments(0, 0),
            Arguments.arguments(3500, 3)
        );
    }

    @ParameterizedTest(name = "구매 금액이 {0}원이면 총 {1}개의 로또가 만들어진다.")
    @MethodSource("methodSourceOfGenerateLottos")
    @DisplayName("구매 금액을 기준으로 1000원짜리 로또를 몇 개 만들 수 있는지 구할 수 있다.")
    void generateLottosTest(int inputPurchasePrice, int expectedCount) {
        // given
        final PurchasePrice purchasePrice = new PurchasePrice(inputPurchasePrice);
        // when
        final Lottos lottos = lottoService.generateLottos(purchasePrice);
        // then
        assertThat(lottos.getSize())
            .isEqualTo(expectedCount);
    }

}
