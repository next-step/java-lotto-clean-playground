package domain;

import enums.LottoRank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ProfitCalculatorTest {

    @Test
    @DisplayName("lottoResult가_상금수익률을_정확히_계산한다")
    void lottoResult가_상금수익률을_정확히_계산한다() {
        //given
        Map<LottoRank, Integer> resultByRank = new EnumMap<>(LottoRank.class);
        resultByRank.put(LottoRank.MATCH_6, 1);
        resultByRank.put(LottoRank.MATCH_5_BONUS, 1);
        resultByRank.put(LottoRank.MATCH_5, 1);
        resultByRank.put(LottoRank.MATCH_4, 1);
        resultByRank.put(LottoRank.MATCH_3, 1);

        //when
        double profitRate = ProfitCalculator.calculateProfitRate(resultByRank, LottoCount.from(5));

        //then
        assertThat(profitRate).isEqualTo(406311);
    }
}
