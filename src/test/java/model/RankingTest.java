package model;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class RankingTest {

    @Test
    void 일치하는_당첨볼의_개수가_6개이면_로또_1등_당첨이다() {
        int matchingCount = 6;
        boolean hasMatchedBonusBall = false;
        Ranking ranking = Ranking.getRanking(matchingCount, hasMatchedBonusBall);
        assertThat(ranking).isEqualTo(Ranking.FIRST);
    }

    @Test
    void 일치하는_당첨볼의_개수가_5개이고_보너스볼이_일치하면_로또_2등_당첨이다() {
        int matchingCount = 5;
        boolean hasMatchedBonusBall = true;
        Ranking ranking = Ranking.getRanking(matchingCount, hasMatchedBonusBall);
        assertThat(ranking).isEqualTo(Ranking.SECOND);
    }

    @Test
    void 일치하는_당첨볼의_개수가_5개이고_보너스볼이_일치하지_않으면_로또_3등_당첨이다() {
        int matchingCount = 5;
        boolean hasMatchedBonusBall = false;
        Ranking ranking = Ranking.getRanking(matchingCount, hasMatchedBonusBall);
        assertThat(ranking).isEqualTo(Ranking.THIRD);
    }

    @Test
    void 일치하는_당첨볼의_개수가_4개이면_로또_4등_당첨이다() {
        int matchingCount = 4;
        boolean hasMatchedBonusBall = false;
        Ranking ranking = Ranking.getRanking(matchingCount, hasMatchedBonusBall);
        assertThat(ranking).isEqualTo(Ranking.FOURTH);
    }

    @Test
    void 일치하는_당첨볼의_개수가_3개이면_로또_5등_당첨이다() {
        int matchingCount = 3;
        boolean hasMatchedBonusBall = false;
        Ranking ranking = Ranking.getRanking(matchingCount, hasMatchedBonusBall);
        assertThat(ranking).isEqualTo(Ranking.FIFTH);
    }

    @Test
    void 일치하는_당첨볼의_개수가_2개_이하면_낙첨이다() {
        int matchingCount = 1;
        boolean hasMatchedBonusBall = false;
        Ranking ranking = Ranking.getRanking(matchingCount, hasMatchedBonusBall);
        assertThat(ranking).isEqualTo(Ranking.MISS);
    }

    @Test
    void 일치하는_당첨볼의_개수가_0개_이상_6개_이하가_아니면_예외가_발생해야_한다() {
        int matchingCount = 7;
        boolean hasMatchedBonusBall = false;
        assertThatThrownBy(() -> Ranking.getRanking(matchingCount, hasMatchedBonusBall))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("당첨번호와 일치하는 숫자의 개수는 최소 0개 최대 6개 입니다!");
    }
}
