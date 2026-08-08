import domain.PurchasePrice;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("PurchasePrice 테스트")
public class PurchasePriceTest {

    @Test
    @DisplayName("구입 금액과 수동 로또 수로 자동 로또 수 계산")
    void calculateAutoLottoCount() {

        // 준비
        PurchasePrice purchasePrice = new PurchasePrice(15000, 5);

        // 실행
        int autoCount = purchasePrice.getAutoLottoCount();

        // 검증
        assertEquals(10, autoCount);
    }

    @Test
    @DisplayName("수동 로또를 0장 구매")
    void manualCountIsZero() {

        // 준비 & 실행
        PurchasePrice purchasePrice = new PurchasePrice(4000, 0);

        // 검증
        assertEquals(0, purchasePrice.getManualLottoCount());
        assertEquals(4, purchasePrice.getAutoLottoCount());
    }

    @Test
    @DisplayName("구입 금액이 0 이하면 예외")
    void exceptIfPurchasePriceIsNegative() {

        assertThrows(IllegalArgumentException.class,
                () -> new PurchasePrice(0, 0));
    }

    @Test
    @DisplayName("구입 금액이 1000원의 배수가 아니면 예외")
    void exceptIfPurchasePriceIsNotMultipleOf1000() {

        assertThrows(IllegalArgumentException.class,
                () -> new PurchasePrice(1500, 0)
        );
    }

    @Test
    @DisplayName("수동 로또 수가 구매 가능한 수보다 많으면 예외")
    void exceptIfManualLottoCountIsMoreThanLottoCount() {

        assertThrows(IllegalArgumentException.class,
                () -> new PurchasePrice(5000, 6)
        );
    }

    @Test
    @DisplayName("수동 로또 수가 음수이면 예외")
    void exceptIfManualLottoCountIsNegative() {

        assertThrows(IllegalArgumentException.class,
                () -> new PurchasePrice(5000, -1)
        );
    }

    @Test
    @DisplayName("총 당첨 금액으로 수익률 계산")
    void calculateProfit() {

        // 준비
        PurchasePrice purchasePrice = new PurchasePrice(10000, 0);

        // 실행
        float result = purchasePrice.calculateProfit(5000);

        // 검증
        assertEquals(0.5, result);
    }
}
