package domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicketCountTest {

    @Test
    void 수동로또_테스트() {

        // given
        int amount = 6000;
        int count = 3;
        int expectValue = 3000;

        // when
        TicketCount.BuyMoney buyMoney = new TicketCount.BuyMoney(amount, count);
        int actualValue = buyMoney.getAmount();

        // then
        Assertions.assertEquals(actualValue, expectValue);
    }

    @Test
    void 로또금액_음수_테스트() {

        // given
        int amount = -2000;
        int count = 3;

        // when&then
        assertThrows(RuntimeException.class, () -> {
                    new TicketCount.BuyMoney(amount, count);
                }
        );
    }
}