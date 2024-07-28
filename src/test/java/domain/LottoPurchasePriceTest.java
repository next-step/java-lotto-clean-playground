package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import util.Errors;

class LottoPurchasePriceTest {

    @Nested
    @DisplayName("구입금액 생성 테스트")
    class createPurchasePrice {

        @ParameterizedTest(name = "input으로 들어온 구입금액이 {0}이라면 음수여서 에러가 발생한다")
        @ValueSource(ints = {-1, -10000, -9000})
        @DisplayName("입력된 금액만큼 구입금액 객체를 생성한다.")
        void createInputPriceTest(int inputPrice) {
            // given
            // when
            // then
            assertThatThrownBy(() -> new LottoPurchasePrice(inputPrice))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Errors.INPUT_PRICE_IS_NEGATIVE);
        }
    }

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
        final LottoPurchasePrice lottoPurchasePrice = new LottoPurchasePrice(inputPurchasePrice);
        // when
        final int lottoCount = lottoPurchasePrice.getLottoCount();
        // then
        assertThat(lottoCount)
            .isEqualTo(expectedCount);
    }

}
