package model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class LottosTest {

    @Test
    void 구매한_개수만큼_로또_번호가_발급되어야_한다() {
        int purchasePrice = 5000;
        PurchaseAmount amount = PurchaseAmount.create(purchasePrice);
        Lottos lottos = Lottos.createLottos(amount, new LottoNumbersGenerator());
        assertThat(lottos.size()).isEqualTo(5);
    }

    @Test
    void getLottos를_통해_얻은_객체를_변경하면_예외가_발생해야_한다() {
        int purchasePrice = 5000;
        PurchaseAmount amount = PurchaseAmount.create(purchasePrice);
        Lottos lottos = Lottos.createLottos(amount, new LottoNumbersGenerator());
        List<Lotto> findLottos = lottos.getLottos();

        assertThatThrownBy(() -> findLottos.add(new Lotto(new LottoNumbersGenerator())))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
