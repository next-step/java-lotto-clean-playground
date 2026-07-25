package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PurchaseAmountTest {

    @Test
    @DisplayName("구입 금액으로 구매할 로또 개수를 계산한다")
    void calculateLottoCount() {
        PurchaseAmount purchaseAmount = new PurchaseAmount(14_000);

        assertThat(purchaseAmount.calculateLottoCount()).isEqualTo(14);
    }

    @Test
    @DisplayName("구입 금액과 당첨 금액으로 수익률을 계산한다")
    void calculateProfitRate() {
        PurchaseAmount purchaseAmount = new PurchaseAmount(10_000);

        ProfitRate profitRate = purchaseAmount.calculateProfitRate(new PrizeMoney(5_000));

        assertThat(profitRate).isEqualTo(new ProfitRate(0.5));
    }

    @Test
    @DisplayName("수익률은 소수점 둘째 자리까지 버림하여 표현한다")
    void formatProfitRate() {
        PurchaseAmount purchaseAmount = new PurchaseAmount(14_000);

        ProfitRate profitRate = purchaseAmount.calculateProfitRate(new PrizeMoney(5_000));

        assertThat(profitRate.toString()).isEqualTo("0.35");
    }
}
