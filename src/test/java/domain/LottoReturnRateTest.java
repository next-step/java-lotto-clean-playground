package domain;

import FakeRandomNumber.FakeLottoNumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LottoReturnRateTest {

    @Test
    @DisplayName("로또 당첨금 테스트")
    void makeLottoPrice() {
        //given
        final List<Integer> lottoRank = List.of(1, 2, 3, 4);
        final int getLottoMoney = 3000;
        final LottoReturnRate lottoReturnRate = new LottoReturnRate(lottoRank, getLottoMoney);
        final List<Integer> expected = List.of(5000, 50000, 1500000, 2000000000);

        //when
        final List<Integer> actual = lottoReturnRate.makeLottoPrice();

        //then
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("수익률 계산 테스트")
    void calculateLottoReturnRate() {
        //given
        final List<Integer> lottoRank = List.of(1, 0, 0, 0);
        final int getLottoMoney = 3000;
        final LottoReturnRate lottoReturnRate = new LottoReturnRate(lottoRank, getLottoMoney);
        final double expected = 1.66;

        //when
        final double actual = lottoReturnRate.calculateLottoReturnRate();

        //then
        assertEquals(expected, actual);
    }
}
