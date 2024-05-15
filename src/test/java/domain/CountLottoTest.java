package domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class CountLottoTest {
    @Test
    void 로또_개수_추출() {
        // given
        CountLotto lotto = new CountLotto();
        // when
        int value = lotto.getCountLotto(14000);
        int expectedNum = 14;
        // then
        Assertions.assertEquals(expectedNum,value );
    }


}