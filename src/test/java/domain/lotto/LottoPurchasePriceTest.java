package domain.lotto;

import domain.common.ExceptionMessage;
import domain.common.Money;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class LottoPurchasePriceTest {

    @Test
    void 음수입력시_예외발생() {
        Assertions.assertThatThrownBy(() -> new LottoPurchasePrice(new Money(-1000)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ExceptionMessage.NEGATIVE_NUMBER);
    }

    @Test
    void 단위가_1000원이_아니면_예외발생() {
        Assertions.assertThatThrownBy(() -> new LottoPurchasePrice(new Money(500)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ExceptionMessage.LOTTO_ALLOWED_MONEY_UNIT);
    }
}
