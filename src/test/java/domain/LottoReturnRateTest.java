package domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LottoReturnRateTest {

    @Test
    void calculateLottoReturnRate() {
        //given
        final List<Integer> lottoRank = List.of(1,0,0,0);
        final int lottoMoney = 3000;
        final LottoReturnRate lottoReturnRate = new LottoReturnRate(lottoRank,lottoMoney);
        final double expected = lottoReturnRate.calculateLottoReturnRate();

        //when
        final double actual = 1.66;

        //then
        assertEquals(expected, actual);
    }

    @Test
    void getLottoPrice() {
        //given
        final List<Integer> lottoRank = List.of(1,0,0,0);
        final int lottoMoney = 3000;
        final LottoReturnRate lottoReturnRate = new LottoReturnRate(lottoRank,lottoMoney);
        lottoReturnRate.calculateLottoReturnRate();
        final List<Integer> expected = List.of(5000,50000,1500000,2000000000);

        //when
        final List<Integer> actual = lottoReturnRate.getLottoPrice();

        //then
        assertEquals(expected, actual);
    }
}
