package domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TicketLottoTest {

    @Test
    void 로또_개수_추출() {

        // given
        TicketLotto lotto = new TicketLotto();

        // when
        int value = lotto.getCountLotto(14000);
        int expectedNum = 14;

        // then
        Assertions.assertEquals(expectedNum, value);
    }
}