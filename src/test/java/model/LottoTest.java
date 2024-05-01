package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LottoTest {
    @DisplayName("금액 입력 시, 금액/1000 장의 로또가 생성되는지 확인한다.")
    @Test
    void countCompare() {
        Lotto lotto = new Lotto(14000);
        lotto.makeMyLottos();

        assertEquals(lotto.getCount(), lotto.getMyLottos().size());
    }

}