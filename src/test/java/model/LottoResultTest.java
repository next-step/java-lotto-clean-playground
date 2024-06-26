package model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class LottoResultTest {

    @DisplayName("구매 금액이 들어오면 수익률을 반환한다.")
    @Test
    void get_rate_of_reward() {
        // given
        final int purchaseMoney = 1000;
        final Map<Rank, Integer> rank = new HashMap<>();
        rank.put(Rank.FOURTH_PLACE, 1);

        final LottoResult lottoResult = new LottoResult(rank);

        // when
        final double result = lottoResult.getRateOfReturn(purchaseMoney);

        // then
        double expect = 5000 / (double) purchaseMoney;
        Assertions.assertThat(result).isEqualTo(expect);
    }
}
