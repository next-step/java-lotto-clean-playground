package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.domain.model.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {

    @Test
    @DisplayName("올바른 정수를 입력하면 Money 객체가 생성된다.")
    void create_Money_With_Valid_Integer() {
        // given
        String input = "5000";

        // when
        Money money = new Money(input);

        // then
        assertThat(money.calculateLottoCount()).isEqualTo(5);
    }

    @Test
    @DisplayName("올바르지 않은 정수를 입력하면 IllegalArgumentException이 발생한다.")
    void create_Money_With_Invalid_Integer() {
        // given
        String input = "오천원";

        // when & then
        assertThatThrownBy(() -> new Money(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구입 금액은 숫자여야 합니다.");
    }

    @Test
    @DisplayName("입력된 금액이 1000원 단위가 아니면 IllegalArgumentException이 발생한다.")
    void create_Money_With_Remainder_After_Division() {
        // given
        String input = "1100";

        // when & then
        assertThatThrownBy(() -> new Money(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("금액은 1000원 단위로 입력 가능합니다.");
    }

    @Test
    @DisplayName("입력된 금액이 1000원 보다 적으면 IllegalArgumentException이 발생한다.")
    void create_Money_With_Less_Than_1000() {
        // given
        String input = "900";

        // when & then
        assertThatThrownBy(() -> new Money(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("금액은 1000원 이상부터 가능합니다.");
    }

}
