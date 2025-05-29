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
        BuyAmount buyAmount = new BuyAmount(1000);

        //Then
        assertThat(buyAmount.getAmount()).isEqualTo(1000);
    }

    @Test
    @DisplayName("구매 금액이 1000원 미만일 경우 예외를 던진다")
    void exception_if_money_under_1000() {
        //Given
        int input = 500;

        //When & Then
        assertThatThrownBy(() -> new BuyAmount(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("금액은 1000원 이상이어야 합니다.");
    }

    @Test
    @DisplayName("로또 구입 개수를 정상적으로 반환한다")
    void return_ticketCount_when_validAmountGiven() {
        //Given
        BuyAmount buyAmount = new BuyAmount(14000);

        //When & Then
        assertThat(buyAmount.getPurchasableTicketCount()).isEqualTo(14);
    }
}
