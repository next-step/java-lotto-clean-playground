package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


public class PurchasePriceTest {
    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void 구입금액을_입력하지_않으면_예외가_발생한다(String input) {
        assertThatThrownBy(() -> new PurchasePrice(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구입 금액을 입력해야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "ㄱ", "1000원"})
    void 구입금액이_숫자가_아니면_예외가_발생한다(String input) {
        assertThatThrownBy(() -> new PurchasePrice(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구입 금액은 숫자로 입력해야 합니다.");
    }

    @Test
    void 구입금액이_1000원_미만이면_예외가_발생한다() {
        assertThatThrownBy(() -> new PurchasePrice("999"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 최소 구입 금액은 1000원입니다.");
    }
}
