package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottosTest {
    @Test
    @DisplayName("구입 금액으로 계산된 개수만큼 로또를 생성한다")
    void createsLottosBasedOnPurchaseAmount() {
        PurchaseAmount purchaseAmount = new PurchaseAmount(14000);
        Lottos lottos = new Lottos(purchaseAmount);

        assertEquals(14, lottos.getLottos().size());
    }
}
