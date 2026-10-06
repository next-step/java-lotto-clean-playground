package domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.purchase.PurchasePrice;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class PurchasePriceTest {
    @Test
    void 구입금액이_1000원_미만이면_예외가_발생한다() {
        assertThatThrownBy(() -> new PurchasePrice(999))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 최소 구입 금액은 1000원입니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {1001, 1500, 2500, 9999})
    void 구입금액이_1000원_단위가_아니면_예외가_발생한다(int amount) {
        assertThatThrownBy(() -> new PurchasePrice(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구입 금액은 1000원 단위여야 합니다.");
    }

    @Test
    void 구입금액이_1000원_단위면_정상적으로_생성된다() {
        assertThatCode(() -> new PurchasePrice(3000))
                .doesNotThrowAnyException();
    }
}
