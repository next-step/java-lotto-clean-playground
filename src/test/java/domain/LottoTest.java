package domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class LottoTest {
    @Test
    void 로또_개수_추출() {
        // given
        Lotto lotto = new Lotto();
        // when
        int value = lotto.getLottoNum(14000);
        int expectedNum = 14;
        // then
        Assertions.assertEquals(expectedNum,value );
    }


}