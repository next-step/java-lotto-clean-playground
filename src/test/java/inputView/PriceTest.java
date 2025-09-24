package inputView;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PriceTest {

    @Test
    void 올바른_금액이면_Price_객체가_생성된다() {
        // given
        int value = 1000;

        // when
        Price price = new Price(value);

        // then
        assertThat(price.getValue()).isEqualTo(1000);
    }

    @Test
    void _0원_미만이면_예외가_발생한다() {
        // given
        int negativeValue = -1;

        // when & then
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Price(negativeValue)
        );
        assertThat(exception.getMessage()).contains("구입 금액은 0원 이상");
    }

    @Test
    void 천원_단위로_로또_개수를_계산한다() {
        // given
        Price price = new Price(6000);

        // when
        int count = price.howManyLottos();

        // then
        assertThat(count).isEqualTo(6);
    }
}
