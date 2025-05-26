package domain.store;

import static domain.store.Cashier.LOTTO_PRICE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.money.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CashierTest {

    @Test
    @DisplayName("수동 로또 입력 개수가 음수일 경우 예외가 발생한다.")
    void shouldThrowException_whenManualLottoNegativeCount() {
        // given
        Money amount = Money.from("3000");
        int manualCount = -3;

        // when & then
        assertThatThrownBy(() -> new Cashier(amount, manualCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수동 로또 개수는 음수가 될 수 없습니다.");
    }

    @Test
    @DisplayName("구입 금액이 정해진 금액 단위가 아니면 예외가 발생한다.")
    void shouldThrowException_whenInvalidUnit() {
        // given
        Money amount = Money.from("1100");
        int manualCount = 1;

        // when & then
        assertThatThrownBy(() -> new Cashier(amount, manualCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구입 금액은 %s원 단위로 입력해야 합니다.".formatted(LOTTO_PRICE.amount()));
    }

    @Test
    @DisplayName("구입 금액이 정해진 금액 미만이면 예외가 발생한다.")
    void shouldThrowException_whenBelowMinimumAmount() {
        // given
        Money amount = Money.from("500");
        int manualCount = 0;

        // when & then
        assertThatThrownBy(() -> new Cashier(amount, manualCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("최소 %s원 이상 입력해야 합니다.".formatted(LOTTO_PRICE.amount()));
    }

    @Test
    @DisplayName("수동 로또 개수가 구매 가능한 총 개수를 초과하면 예외가 발생한다.")
    void shouldThrowException_whenManualCountExceedsTotal() {
        // given
        Money amount = Money.from("3000");
        int manualCount = 4;

        // when & then
        assertThatThrownBy(() -> new Cashier(amount, manualCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수동 로또 개수가 전체 구매 가능한 개수보다 적어야 합니다.");
    }

    @Test
    @DisplayName("정상적인 값이면 객체가 생성되고 자동 개수를 올바르게 반환한다.")
    void shouldCreateObjectCorrectly_withValidInput() {
        // given
        Money amount = Money.from("5000");
        int manualCount = 2;

        // when
        Cashier cashier = new Cashier(amount, manualCount);

        // then
        assertThat(cashier.getTotalCount()).isEqualTo(5);
        assertThat(cashier.getAutoCount()).isEqualTo(3);
    }
}
