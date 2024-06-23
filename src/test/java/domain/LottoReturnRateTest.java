package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LottoReturnRateTest {

    @Test
    @DisplayName("수익률 계산 테스트")
    void calculateLottoReturnRate() {
        // given
        final int getLottoMoney = 3000;
        final List<LottoRank> lottoRanks = List.of(new LottoRank("3", 1), new LottoRank("4", 0), new LottoRank("5", 0), new LottoRank("6", 0));
        final LottoReturnRate lottoReturnRate = new LottoReturnRate(lottoRanks, getLottoMoney);
        final double expected = 1.66;

        // when
        final double actual = lottoReturnRate.getLottoReturnRate();

        // then
        assertEquals(expected, actual);
    }
}
