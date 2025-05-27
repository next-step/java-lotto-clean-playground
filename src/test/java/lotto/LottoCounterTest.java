package lotto;

import lotto.domain.InputLottoNumber;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LottoCounterTest {

    @Test
    void generateAuto() {
        int total = 5;
        int passivity = 2;

        List<InputLottoNumber> autos = LottoCounter.generateAuto(total, passivity);

        assertEquals(3, autos.size(), "자동 로또 개수는 총 5장에서 수동 2장을 뺀 3장입니다.");
    }
}