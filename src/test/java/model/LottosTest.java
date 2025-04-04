package model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class LottosTest {

    private final NumbersGenerator numbersGenerator = new LottoNumbersGenerator();

    @Test
    void 수동_구매한_로또의_개수를_제외한_만큼_자동_로또_번호가_발급되어야_한다() {
        int purchasePrice = 5000;
        int manualPurchaseAmount = 2;
        PurchaseAmount amount = PurchaseAmount.of(purchasePrice, manualPurchaseAmount);
    }
}
