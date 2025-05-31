package lotto;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import lotto.model.LottoGenerator;
import lotto.model.Money;
import lotto.model.PurchaseLotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PurchaseLottoTest {

    @Test
    @DisplayName("금액에 맞는 로또 개수인지 확인")
    void calculateLottoCountByAmount() {
        Money money = new Money(5000);
        PurchaseLotto purchase = new PurchaseLotto(money, new LottoGenerator());

        assertThat(purchase.purchaseCount()).isEqualTo(5);
        assertThat(purchase.getLottos().size()).isEqualTo(5);
    }

}
