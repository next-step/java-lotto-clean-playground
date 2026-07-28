package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PurchaseAmountTest {
    @Test
    void 구매_금액이_1000원_미만이면_예외를_던진다() {
        assertThatThrownBy(() -> new PurchaseAmount(999))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 구매_금액이_1000원_이상이면_정상적으로_생성된다() {
        assertThatCode(() -> new PurchaseAmount(1000))
                .doesNotThrowAnyException();
    }

    @Test
    void 구매_금액_단위가_1000원이_아니면_예외를_던진다() {
        assertThatThrownBy(() -> new PurchaseAmount(1001))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
