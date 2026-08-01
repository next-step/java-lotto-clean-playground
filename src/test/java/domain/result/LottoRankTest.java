package domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoRankTest {

    @Test
    @DisplayName("5개 일치하고 보너스 볼이 일치하면 보너스 볼 일치 등수다")
    void findBonusBallMatches() {
        LottoResult lottoResult = LottoResult.of(MatchCount.from(5), true);

        LottoRank lottoRank = LottoRank.findBy(lottoResult).orElseThrow();

        assertThat(lottoRank).isEqualTo(LottoRank.BONUS_BALL_MATCHES);
    }

    @Test
    @DisplayName("5개 일치하고 보너스 볼이 일치하지 않으면 5개 일치 등수다")
    void findFiveMatches() {
        LottoResult lottoResult = LottoResult.of(MatchCount.from(5), false);

        LottoRank lottoRank = LottoRank.findBy(lottoResult).orElseThrow();

        assertThat(lottoRank).isEqualTo(LottoRank.FIVE_MATCHES);
    }
}
