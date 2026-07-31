package domain;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class LottoRankTest {
    @Test
    void 일치_개수에_해당하는_당첨_등급을_반환한다() {
        // given
        int matchCount = 3;
        boolean bonusMatch = false;

        // when
        Optional<LottoRank> rank = LottoRank.findByMatchResult(matchCount, bonusMatch);

        // then
        assertThat(rank).contains(LottoRank.THREE_MATCH);
    }

    @Test
    void 당첨_기준보다_적게_일치하면_당첨_등급을_반환하지_않는다() {
        // given
        int matchCount = 2;
        boolean bonusMatch = false;

        // when
        Optional<LottoRank> rank = LottoRank.findByMatchResult(matchCount, bonusMatch);

        // then
        assertThat(rank).isEmpty();
    }

    @Test
    void 다섯개_일치하고_보너스_번호가_일치하지_않으면_3등을_반환한다() {
        // given
        int matchCount = 5;
        boolean bonusMatch = false;

        // when
        Optional<LottoRank> rank = LottoRank.findByMatchResult(matchCount, bonusMatch);

        // then
        assertThat(rank).contains(LottoRank.FIVE_MATCH);
    }

    @Test
    void 다섯개_일치하고_보너스_번호가_일치하면_2등을_반환한다() {
        // given
        int matchCount = 5;
        boolean bonusMatch = true;

        // when
        Optional<LottoRank> rank = LottoRank.findByMatchResult(matchCount, bonusMatch);

        // then
        assertThat(rank).contains(LottoRank.FIVE_MATCH_WITH_BONUS);
    }
}
