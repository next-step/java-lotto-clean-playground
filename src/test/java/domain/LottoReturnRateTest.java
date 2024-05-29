package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LottoReturnRateTest {

    @Test
    @DisplayName("수익률 계산 테스트")
    void calculateLottoReturnRate() {
        //given
        final Map<String, Integer> lottoRank = Map.of("3", 1, "4", 0, "5", 0, "6", 0);
        final int getLottoMoney = 3000;
        final LottoReturnRate lottoReturnRate = new LottoReturnRate(lottoRank, getLottoMoney);
        final double expected = 1.66;

        //when
        final double actual = lottoReturnRate.getLottoReturnRate();

        //then
        assertEquals(expected, actual);
    }
}
