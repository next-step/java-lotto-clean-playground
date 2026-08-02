package domain.lotto;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LottosTest {
    private Lotto createLotto(int... numbers) {
        return new Lotto(
                Arrays.stream(numbers)
                        .mapToObj(LottoNumber::from)
                        .toList()
        );
    }

    @Test
    void 수동_로또_없이_자동_로또만_생성한다() {
        PurchaseCount purchaseCount = new PurchaseCount(3, 0);

        Lottos lottos = Lottos.createLottos(purchaseCount, List.of());

        assertEquals(3, lottos.size());
    }

    @Test
    void 수동_로또와_자동_로또를_합쳐_생성한다() {
        Lotto manualLotto = createLotto(1, 2, 3, 4, 5, 6);
        PurchaseCount purchaseCount = new PurchaseCount(3, 1);

        Lottos lottos = Lottos.createLottos(purchaseCount, List.of(manualLotto));

        assertEquals(3, lottos.size());
        assertTrue(lottos.getLottos().contains(manualLotto));
    }
}
