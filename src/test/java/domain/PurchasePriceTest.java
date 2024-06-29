package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import util.Errors;

class PurchasePriceTest {

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
            assertThatThrownBy(() -> new PurchasePrice(inputPrice))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Errors.INPUT_PRICE_IS_NEGATIVE);
        }
    }
}
