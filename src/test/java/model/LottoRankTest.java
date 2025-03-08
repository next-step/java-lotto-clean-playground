package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class LottoRankTest {

    @Test
    @DisplayName("일치한 개수와 보너스 일치 여부에 따라 올바른 랭크를 반환하는 지 검증한다.")
    void should_Return_Correct_LottoRank() {

        LottoRank rank1 = LottoRank.getLottoRank(3, false);
        LottoRank rank2 = LottoRank.getLottoRank(4, false);
        LottoRank rank3 = LottoRank.getLottoRank(5, false);
        LottoRank rank4 = LottoRank.getLottoRank(5, true);
        LottoRank rank5 = LottoRank.getLottoRank(6, false);

        assertThat(rank1).isEqualTo(LottoRank.THREE_MATCHES);
        assertThat(rank2).isEqualTo(LottoRank.FOUR_MATCHES);
        assertThat(rank3).isEqualTo(LottoRank.FIVE_MATCHES);
        assertThat(rank4).isEqualTo(LottoRank.FIVE_MATCHES_BONUS);
        assertThat(rank5).isEqualTo(LottoRank.SIX_MATCHES);
    }

    @Test
    @DisplayName("LottoRank에 따른 당첨 금액을 반환하는 지 검증한다.")
    void should_Return_Correct_Price() {

        assertThat(LottoRank.THREE_MATCHES.getPrice()).isEqualTo(5_000);
        assertThat(LottoRank.FOUR_MATCHES.getPrice()).isEqualTo(50_000);
        assertThat(LottoRank.FIVE_MATCHES.getPrice()).isEqualTo(1_500_000);
        assertThat(LottoRank.FIVE_MATCHES_BONUS.getPrice()).isEqualTo(30_000_000);
        assertThat(LottoRank.SIX_MATCHES.getPrice()).isEqualTo(2_000_000_000);
        assertThat(LottoRank.NO_WINNER.getPrice()).isEqualTo(0);
    }

    @Test
    @DisplayName("각 LottoRank에 맞는 결과를 반환하는 지 검증한다.")
    void should_Return_Correct_Rank_String() {

        assertThat(LottoRank.THREE_MATCHES.getMatchCount()).isEqualTo(3);
        assertThat(LottoRank.FOUR_MATCHES.getMatchCount()).isEqualTo(4);
        assertThat(LottoRank.FIVE_MATCHES.getMatchCount()).isEqualTo(5);
        assertThat(LottoRank.FIVE_MATCHES_BONUS.getMatchCount()).isEqualTo(5);
        assertThat(LottoRank.SIX_MATCHES.getMatchCount()).isEqualTo(6);
        assertThat(LottoRank.NO_WINNER.getMatchCount()).isEqualTo(0);
    }

    @Test
    @DisplayName("일치 개수가 3 미만이거나 잘못된 경우 NO_WINNER를 반환하는 지 검증한다.")
    void should_Return_NoWinner_When_Invalid_MatchCount() {

        LottoRank rank1 = LottoRank.getLottoRank(2, false);
        LottoRank rank2 = LottoRank.getLottoRank(1, true);
        LottoRank rank3 = LottoRank.getLottoRank(0, false);
        LottoRank rank4 = LottoRank.getLottoRank(10, true); 
        
        assertThat(rank1).isEqualTo(LottoRank.NO_WINNER);
        assertThat(rank2).isEqualTo(LottoRank.NO_WINNER);
        assertThat(rank3).isEqualTo(LottoRank.NO_WINNER);
        assertThat(rank4).isEqualTo(LottoRank.NO_WINNER);
    }
}
