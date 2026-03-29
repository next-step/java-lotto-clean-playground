package domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoShopTest {

    @Test
    void 구입금액만큼_로또를_구매한다() {
        NumberGenerator numberGenerator = new TestNumberGenerator(List.of(1, 2, 3, 4, 5, 6));
        LottoShop lottoShop = new LottoShop(numberGenerator);

        Lottos lottos = lottoShop.purchase(3000);

        assertThat(lottos.toNumberLists()).containsExactly(
                List.of(1, 2, 3, 4, 5, 6),
                List.of(1, 2, 3, 4, 5, 6),
                List.of(1, 2, 3, 4, 5, 6)
        );
    }

    @Test
    void 구입금액이_1000원_미만이면_예외가_발생한다() {
        NumberGenerator numberGenerator = new TestNumberGenerator(List.of(1, 2, 3, 4, 5, 6));
        LottoShop lottoShop = new LottoShop(numberGenerator);

        assertThatThrownBy(() -> lottoShop.purchase(500))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구입 금액은 1000원 이상이어야 합니다.");
    }

    @Test
    void 구입금액이_1000원_단위가_아니면_예외가_발생한다() {
        NumberGenerator numberGenerator = new TestNumberGenerator(List.of(1, 2, 3, 4, 5, 6));
        LottoShop lottoShop = new LottoShop(numberGenerator);

        assertThatThrownBy(() -> lottoShop.purchase(1500))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구입 금액은 1000원 단위여야 합니다.");
    }
}
