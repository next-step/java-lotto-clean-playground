package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 1000, 50000})
    @DisplayName("0 이상의 금액으로 Money 객체를 생성한다.")
    void createMoneyTest(int amount) {
        //given //when
        Money money = new Money(amount);

        //then
        assertThat(money.getAmount()).isEqualTo(amount);
    }

    @Test
    @DisplayName("금액이 음수이면 예외가 발생한다.")
    void validatePositiveTest() {
        //given
        int negativeAmount = -1000;

        //when //then
        assertThatThrownBy(() -> new Money(negativeAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("금액은 음수일 수 없습니다.");
    }

    @Test
    @DisplayName("금액 정보를 정확히 반환한다.")
    void getAmountTest() {
        //given
        int amount = 15000;
        Money money = new Money(amount);

        //when
        int result = money.getAmount();

        //then
        assertThat(result).isEqualTo(amount);
    }
}
