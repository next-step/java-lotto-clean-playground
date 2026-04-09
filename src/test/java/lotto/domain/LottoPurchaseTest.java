package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
public class LottoPurchaseTest {
    private final LottoMaker maker = new LottoMaker();

    @Test
    void 시나리오_테스트() {
        LottoPurchase purchase = new LottoPurchase(12345, maker);
        assertThat(purchase.getChange()).isEqualTo(345);
        assertThat(purchase.getNumberOfLotto()).isEqualTo(12);

        LottoReceipt receipt = purchase.printReceipt();
        assertThat(receipt.lottoRows().size()).isEqualTo(12);
        assertThat(receipt.totalPrice()).isEqualTo(12345);
    }
}
