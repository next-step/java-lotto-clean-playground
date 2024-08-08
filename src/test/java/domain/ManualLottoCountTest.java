package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import util.Errors;

class ManualLottoCountTest {

    private static Stream<Arguments> methodSourceOfValidateMoreLottoCase() {
        return Stream.of(
            Arguments.arguments(14000, 15),
            Arguments.arguments(500, 1),
            Arguments.arguments(3500, 4)
        );
    }

    @ParameterizedTest(name = "구매 금액이 {0}인데 수동 로또 개수로 {1}이 입력되면 구매할 수 있는 개수보다 많아서 에러가 발생한다.")
    @MethodSource("methodSourceOfValidateMoreLottoCase")
    @DisplayName("구매할 수 있는 개수보다 수동으로 구매한 로또 개수가 더 많을 순 없다.")
    void validateCountOfManualLotto(int purchasePrice, int manualLottoCount) {
        // given
        final LottoPurchasePrice lottoPurchasePrice = new LottoPurchasePrice(purchasePrice);
        // when
        // then
        assertThatThrownBy(() -> new ManualLottoCount(manualLottoCount, lottoPurchasePrice))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(Errors.INVALID_MANUAL_LOTTO_COUNT);
    }

    @ParameterizedTest()
    @ValueSource(ints = {-1, -100})
    @DisplayName("구매한 수동 로또 개수가 음수일 수 없다")
    void validateNegativeCase(int manualLottoCount) {
        // given
        final LottoPurchasePrice lottoPurchasePrice = new LottoPurchasePrice(1000);
        // when
        // then
        assertThatThrownBy(() -> new ManualLottoCount(manualLottoCount, lottoPurchasePrice))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(Errors.MANUAL_LOTTO_COUNT_IS_NEGATIVE);
    }

}
