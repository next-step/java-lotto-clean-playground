package lotto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import lotto.model.LottoGenerator;
import lotto.model.LottoNumbers;
import lotto.model.Money;
import lotto.model.PurchaseLotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PurchaseLottoTest {

    @Test
    @DisplayName("금액에 맞는 전체 로또 개수(수동+자동) 확인")
    void calculateTotalLottoCountWithManualAndAuto() {
        Money money = new Money(5000);
        List<LottoNumbers> manualLotto = Arrays.asList(
            new LottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6)),
            new LottoNumbers(Arrays.asList(7, 8, 9, 10, 11, 12))
        );
        PurchaseLotto purchaseLotto = new PurchaseLotto(money, new LottoGenerator(), manualLotto);

        assertThat(purchaseLotto.purchaseCount()).isEqualTo(5);
        assertThat(purchaseLotto.getLotto().asList().subList(0, 2)).isEqualTo(manualLotto);
        assertThat(purchaseLotto.getLotto().asList().subList(2, 5).size()).isEqualTo(3);
    }

    @Test
    @DisplayName("수동 로또 없이 전체 자동 로또 개수 확인")
    void calculateTotalLottoCountWithAutoOnly() {
        Money money = new Money(5000);
        PurchaseLotto purchaseLotto = new PurchaseLotto(money, new LottoGenerator(), Collections.emptyList());

        assertThat(purchaseLotto.purchaseCount()).isEqualTo(5);
    }
}
