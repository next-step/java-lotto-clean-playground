package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottoRankTest {
    @Test
    void 일치_개수에_해당하는_당첨_등급을_반환한다() {
        assertThat(LottoRank.findByMatchCount(3))
                .contains(LottoRank.THREE_MATCH);
    }

    @Test
    void 당첨_기준보다_적게_일치하면_당첨_등급을_반환하지_않는다() {
        assertThat(LottoRank.findByMatchCount(2))
                .isEmpty();
    }
}
