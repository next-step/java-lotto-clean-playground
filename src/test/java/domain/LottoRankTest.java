package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottoRankTest {
    @Test
    void 일치_개수에_해당하는_당첨_등급을_반환한다() {
        assertThat(LottoRank.findByMatchResult(3, false))
                .contains(LottoRank.THREE_MATCH);
    }

    @Test
    void 당첨_기준보다_적게_일치하면_당첨_등급을_반환하지_않는다() {
        assertThat(LottoRank.findByMatchResult(2, false))
                .isEmpty();
    }

    @Test
    void 다섯개_일치에서는_보너스_일치_여부에_따라_등급이_나뉜다() {
        assertThat(LottoRank.findByMatchResult(5, false))
                .contains(LottoRank.FIVE_MATCH);

        assertThat(LottoRank.findByMatchResult(5, true))
                .contains(LottoRank.FIVE_MATCH_WITH_BONUS);
    }
}
