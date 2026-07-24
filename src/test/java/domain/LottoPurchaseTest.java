package domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoPurchaseTest {
    @Test
    void 구매_금액을_로또_가격으로_나눈_몫만큼_로또를_발급한다() {
        PurchaseAmount purchaseAmount = new PurchaseAmount(3500);
        LottoPurchase lottoPurchase = new LottoPurchase(purchaseAmount, new RandomLottoGenerator());

        List<Lotto> lottos = lottoPurchase.issueLottos();

        assertThat(lottos).hasSize(3);
    }
}
