package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LottoReturnRateTest {

    @Test
    @DisplayName("수익률 계산 테스트")
    void calculateLottoReturnRate() {
        //given
        final List<Integer> lottoRank = List.of(1, 0, 0, 0);
        final int getLottoMoney = 3000;
        final LottoReturnRate lottoReturnRate = new LottoReturnRate(lottoRank, getLottoMoney);
        final double expected = 1.66;

        //when
        final double actual = lottoReturnRate.getLottoReturnRate();

        //then
        assertEquals(expected, actual);
    }
}
