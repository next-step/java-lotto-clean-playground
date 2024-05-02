import static org.assertj.core.api.Assertions.assertThatThrownBy;

import model.LottoPrice;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@SuppressWarnings("NonAsciiCharacters")
class LottoPriceTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 1000, 2000, 3000, 4000, 5000})
    void 로또_금액은_0원_혹은_1000원_단위이어야_한다(int price) {
        // when & then
        Assertions.assertThatCode(() -> new LottoPrice(price)).doesNotThrowAnyException();
    }

    @Test
    void 로또_금액은_0원_보다_작을_수_없다() {
        // given
        int price = -1000;

        // when & then
        assertThatThrownBy(() -> new LottoPrice(price))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 금액은 0원 보다 작을 수 없습니다.");
    }

    @Test
    void 로또_금액은_1000원_단위여야_한다() {
        // given
        int price = 1234;

        // when & then
        assertThatThrownBy(() -> new LottoPrice(price))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 금액은 1000원 단위여야 합니다.");
    }

    @Test
    void 로또_금액은_숫자여야_한다() {
        // given
        String price = "천원";

        // when & then
        assertThatThrownBy(() -> LottoPrice.valueOf(price))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 금액은 숫자여야 합니다.");
    }
}
