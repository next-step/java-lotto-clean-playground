package lotto;

import lotto.model.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MoneyTest {

    @Test
    @DisplayName("음수 금액 입력 시 예외 발생")
    void throwExceptionIfAmountIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Money(-1000));
    }

    @Test
    @DisplayName("1000원 단위가 아니면 예외 발생")
    void throwExceptionIfAmountIsNotThousandUnit() {
        assertThrows(IllegalArgumentException.class, () -> new Money(1500));
    }

    @Test
    @DisplayName("같은 금액을 가진 Money는 같은 값")
    public void voCheck() {
        Money first = new Money(1000);
        Money second = new Money(1000);

        assertThat(first).isEqualTo(second);
        assertThat(first.hashCode()).isEqualTo(second.hashCode());
    }
}
