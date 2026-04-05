package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MoneyTest {

    @DisplayName("구입 금액이 1000원 단위가 아니거나 1000원 미만이면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {999, 1500, -1000})
    void invalidMoneyTest(int invalidAmount) {
        assertThatThrownBy(() -> new Money(invalidAmount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("구입 금액에 맞는 로또 발행 개수를 정확히 계산한다.")
    @Test
    void calculateCountTest() {
        Money money = new Money(14000);
        assertThat(money.getNumber()).isEqualTo(14);
    }
}