package domain;

import generator.NumberGenerator;
import generator.TestNumberGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoShopTest {

    @Test
    void 구입금액만큼_로또를_구매한다() {
        NumberGenerator numberGenerator = new TestNumberGenerator(List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        ));
        LottoShop lottoShop = new LottoShop(numberGenerator);

        Lottos lottos = lottoShop.purchase(new PurchaseAmount(3000));

        assertThat(lottos.toNumberLists()).containsExactly(
                List.of(1, 2, 3, 4, 5, 6),
                List.of(1, 2, 3, 4, 5, 6),
                List.of(1, 2, 3, 4, 5, 6)
        );
    }
}
