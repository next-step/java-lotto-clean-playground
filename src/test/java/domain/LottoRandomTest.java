package domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LottoRandomTest {
    @Test
    void 로또_난수_범위_테스트() {
        // given
        LottoRandom lottoRandom = new LottoRandom();
        List<Integer> lottoNum=lottoRandom.getLottoRandomNum();
        // when&then
        for (int num : lottoNum) {
            assertTrue(1 <= num && num <= 45,"범위오버");
        }
    }

}