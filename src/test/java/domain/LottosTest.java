package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottosTest {
    @Test
    @DisplayName("구입 금액에 해당하는 개수만큼 로또를 생성한다")
    void createsLottosBasedOnPurchaseAmount() {
        Lottos lottos = new Lottos(14000);

        assertEquals(14, lottos.getLottos().size());
    }

    @Test
    @DisplayName("최소 구입 금액으로도 로또 한 장을 생성한다")
    void createsOneLottoForMinimumPurchaseAmount() {
        Lottos lottos = new Lottos(1000);

        assertEquals(1, lottos.getLottos().size());
    }

    @ParameterizedTest
    @ValueSource(ints = {999, 0, -5000})
    @DisplayName("구입 금액이 최소 구입 금액보다 작으면 예외가 발생한다")
    void throwsExceptionWhenPurchaseAmountIsLessThanLottoPrice(int amount) {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Lottos(amount)
        );
    }
}
