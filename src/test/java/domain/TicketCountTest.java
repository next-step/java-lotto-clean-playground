package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

public class TicketCountTest {
    @DisplayName("유효한 값이 들어오면 ticketCount를 생성한다.")
    @ParameterizedTest(name = "value가 {0}일때 ticketCount 생성")
    @ValueSource(ints = {1, 10000})
    void whenValidInputCome_thenCreateAmount(int value) {
        TicketCount ticketCount = new TicketCount(value);
        assertThat(ticketCount.getValue()).isEqualTo(value);
    }

    @DisplayName("음수 또는 0의 값의 경우 예외를 발생시킨다.")
    @ParameterizedTest(name = "value가 {0} => throw IllegalArgumentException")
    @ValueSource(ints = {0, -1})
    void whenValueIsNotPositive_thenThrowsException(int value) {
        assertThatThrownBy(() -> new Amount(value))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("가지고 있는 티켓의 갯수보다 더 많은 양의 티켓이 차감되면 예외를 발생시키고, 차감 결과가 0인경우는 예외를 발생시키지 않는다.")
    @Test
    void whenSubstractedResultIsLessThenZero_thenThrowsException() {
        TicketCount ticketCount = new TicketCount(5);

        assertAll(
                () -> assertThatCode(() -> ticketCount.subtractTicketCount(new TicketCount(5)))
                        .doesNotThrowAnyException(),

                () -> assertThatThrownBy(() -> ticketCount.subtractTicketCount(new TicketCount(6)))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("차감 가능한 티켓의 갯수를 초과했습니다.")
        );

    }
}
