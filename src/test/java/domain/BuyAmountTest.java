package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BuyAmountTest {

    @Test
    @DisplayName("구매 금액이 1000원 이상일 경우 객체 생성 성공")
    void success_createMoney_when_input_1000_or_over() {
        //Given & When
        BuyAmount buyAmount = new BuyAmount(1000, 1);

        //Then
        assertThat(buyAmount.getAmount()).isEqualTo(1000);
    }

    @Test
    @DisplayName("구매 금액이 1000원 미만일 경우 예외를 던진다")
    void exception_if_money_under_1000() {
        //Given
        int input = 500;

        //When & Then
        assertThatThrownBy(() -> new BuyAmount(input, 3))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("금액은 1000원 이상이어야 합니다.");
    }

    @Test
    @DisplayName("로또 구입 개수를 정상적으로 반환한다")
    void return_ticketCount_when_validAmountGiven() {
        //Given
        BuyAmount buyAmount = new BuyAmount(14000, 3);

        //When & Then
        assertThat(buyAmount.getTotalCount()).isEqualTo(14);
    }

    @Test
    @DisplayName("수동 구매 수가 총 구매 수를 초과할 경우 예외를 던진다")
    void exception_if_handCount_exceeds_total_Count() {
        // Given
        int amount = 2000;
        int handCount = 3;

        // When & Then
        assertThatThrownBy(() -> new BuyAmount(amount, handCount))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("수동으로 구매하려는 횟수가 구입 금액을 초과하였습니다.");
    }

    @DisplayName("자동 구매 수를 정상적으로 반환한다")
    @Test
    void return_autoCount_when_validAmountGiven() {
        // Given
        BuyAmount buyAmount = new BuyAmount(5000, 2);

        // When & Then
        assertThat(buyAmount.getAutoCount()).isEqualTo(3);
    }
}
