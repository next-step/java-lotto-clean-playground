package domain;

import generator.NumberGenerator;
import generator.TestNumberGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoShopTest {

    @Test
    void 구입금액에_맞게_자동_로또를_생성한다() {
        NumberGenerator numberGenerator = new TestNumberGenerator(List.of(
                new LottoNumber(1), new LottoNumber(2),
                new LottoNumber(3), new LottoNumber(4),
                new LottoNumber(5), new LottoNumber(6)
        ));
        LottoShop lottoShop = new LottoShop(numberGenerator);

        Lottos lottos = lottoShop.purchase(new PurchaseAmount(3000), new Lottos(List.of()));

        assertThat(lottos.toNumberLists()).hasSize(3);
        assertThat(lottos.toNumberLists()).containsExactly(
                List.of(1, 2, 3, 4, 5, 6),
                List.of(1, 2, 3, 4, 5, 6),
                List.of(1, 2, 3, 4, 5, 6)
        );
    }

    @Test
    void 수동_로또와_자동_로또를_함께_반환한다() {
        NumberGenerator numberGenerator = new TestNumberGenerator(List.of(
                new LottoNumber(7), new LottoNumber(8),
                new LottoNumber(9), new LottoNumber(10),
                new LottoNumber(11), new LottoNumber(12)
        ));
        LottoShop lottoShop = new LottoShop(numberGenerator);

        Lottos manualLottos = new Lottos(List.of(
                new Lotto(List.of(
                        new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                        new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
                ))
        ));

        Lottos lottos = lottoShop.purchase(new PurchaseAmount(2000), manualLottos);

        assertThat(lottos.toNumberLists()).containsExactly(
                List.of(1, 2, 3, 4, 5, 6),
                List.of(7, 8, 9, 10, 11, 12)
        );
    }
}
