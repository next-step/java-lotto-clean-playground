import domain.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MoneyTest {

    @ParameterizedTest
    @ValueSource(ints = {0, -5000, -10000})
    @DisplayName("금액이 0 이하면 예외 발생 테스트")
    void 금액이_0이하면_예외_발생(int value) {
        String throwMessage = "금액은 0원보다 커야 합니다.";

        assertThatThrownBy(() -> new Money(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(throwMessage);
    }

    @Test
    @DisplayName("금액 나눈 몫 반환 테스트")
    void 나눈_몫_반환() {
        Money money = new Money(14000);
        int divisor = 1000;
        int expectedCount = 14;

        assertThat(money.divide(divisor)).isEqualTo(expectedCount);
    }
}
