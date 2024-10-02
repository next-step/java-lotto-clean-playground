package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class LottoRankTest {
    @Test
    @DisplayName("당첨 번호 6개와 일치할 경우, FIRST 등수를 반환한다.")
    void testWhenMatchingCountIs6(){
        LottoRank result = LottoRank.valueOf(6, false);
        assertThat(result).isEqualTo(LottoRank.FIRST);
    }

    @Test
    @DisplayName("당첨 번호 5개와 일치하고 보너스 볼이 맞을 경우, SECOND 등수를 반환한다.")
    void testWhenMatchingCountIs6AndMatchBonusBall(){
        LottoRank result = LottoRank.valueOf(5, true);
        assertThat(result).isEqualTo(LottoRank.SECOND);
    }

    @Test
    @DisplayName("당첨 번호 5개와 일치하고 보너스 볼이 맞지 않을 경우, THIRD 등수를 반환한다.")
    void testWhenMatchingCountIs5AndNotMatchBonusBall(){
        LottoRank result = LottoRank.valueOf(5, false);
        assertThat(result).isEqualTo(LottoRank.THIRD);
    }

    @Test
    @DisplayName("당첨 번호 4개와 일치할 경우, FOURTH 등수를 반환한다.")
    void testWhenMatchingCountIs4(){
        LottoRank result = LottoRank.valueOf(4, false);
        assertThat(result).isEqualTo(LottoRank.FOURTH);
    }
    @Test
    @DisplayName("당첨 번호 3개와 일치할 경우, FIFTH 등수를 반환한다.")
    void testWhenMatchingCountIs3(){
        LottoRank result = LottoRank.valueOf(3, false);
        assertThat(result).isEqualTo(LottoRank.FIFTH);
    }

    @Test
    @DisplayName("당첨 번호 개수가 0일 경우, NONE 랭크를 반환한다.")
    void testWhenMatchingCountIs0(){
        LottoRank result = LottoRank.valueOf(0, false);
        assertThat(result).isEqualTo(LottoRank.NONE);
    }
}
