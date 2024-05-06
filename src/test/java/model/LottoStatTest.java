package model;

import static org.assertj.core.api.Assertions.assertThat;

import config.ResultType;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoStatTest {

    private static LottoStat lottoStat = new LottoStat();

    @BeforeAll
    static void before() {
        lottoStat.updateWinningStat(ResultType.FIFTH);
    }

    @Test
    @DisplayName("ResultType이 업데이트하면 ResultType의 개수가 1 증가해야 한다.")
    void updateWinningStatTest() {
        final Map<ResultType, Integer> statics = lottoStat.getStatics();
        assertThat(statics.get(ResultType.FIFTH)).isEqualTo(1);
        assertThat(statics.get(ResultType.SECOND)).isEqualTo(0);
    }

    @Test
    @DisplayName("LottoStat은 수익률을 반환할 수 있다.")
    void getTotalReturnRateTest() {
        int inputPrice = 10000;
        final double totalReturnRate = lottoStat.getTotalReturnRate(inputPrice);
        assertThat(totalReturnRate).isEqualTo(
                (double) ResultType.FIFTH.getWinningPrice() / inputPrice);
    }

}
