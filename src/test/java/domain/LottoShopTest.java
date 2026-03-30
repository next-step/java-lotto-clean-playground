package domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoShopTest {

    @Test
    void 구입금액만큼_로또를_구매한다() {
        NumberGenerator numberGenerator = new TestNumberGenerator(List.of(1, 2, 3, 4, 5, 6));
        LottoShop lottoShop = new LottoShop(numberGenerator);

        Lottos lottos = lottoShop.purchase(new PurchaseAmount(3000));

        assertThat(lottos.toNumberLists()).containsExactly(
                List.of(1, 2, 3, 4, 5, 6),
                List.of(1, 2, 3, 4, 5, 6),
                List.of(1, 2, 3, 4, 5, 6)
        );
    }
}
