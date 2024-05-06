import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class LottoPriceTest {
    @Test
    void 로또_금액은_0이상_이어야_한다() {
        int price = -1000;

        assertThatThrownBy(() -> new LottoPrice(price)).isInstanceOf(IllegalArgumentException.class).hasMessage("로또 금액은 0이상 이어야 한다.");
    }

    @Test
    void 로또_금액은_1000원_단위여야_한다() {
        int price = 9999;

        assertThatThrownBy(() -> new LottoPrice(price)).isInstanceOf(IllegalArgumentException.class).hasMessage("로또 금액은 1000원 단위여야 한다.");
    }
}
