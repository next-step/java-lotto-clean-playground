package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoMachineTest {

    @Test
    @DisplayName("구입 금액을 1000으로 나눈 로또 개수를 반환한다.")
    void calculateTicketCountTest() {
        //given
        Money money = new Money(5000);

        //when
        int ticketCount = LottoMachine.calculateTicketCount(money);

        //then
        assertThat(ticketCount).isEqualTo(5);
    }

    @Test
    @DisplayName("구입 금액이 1000원 미만이면 예외가 발생한다.")
    void validateLottoUnitUnderPriceTest() {
        //given
        Money money = new Money(500);

        //when //then
        assertThatThrownBy(() -> LottoMachine.calculateTicketCount(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또는 1000원 단위로만 구매 가능합니다.");
    }

    @Test
    @DisplayName("구입 금액이 1000원 단위로 나누어 떨어지지 않으면 예외가 발생한다.")
    void validateLottoUnitRemainderTest() {
        //given
        Money money = new Money(2500);

        //when //then
        assertThatThrownBy(() -> LottoMachine.calculateTicketCount(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또는 1000원 단위로만 구매 가능합니다.");
    }
}
