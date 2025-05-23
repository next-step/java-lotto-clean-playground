package domain;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

class RankTest {

    @Test
    void matchCountOf_정상입력시_해당하는Rank반환() {

        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(Rank.matchCountOf(3,true)).isEqualTo(Rank.FIFTH);
        softly.assertThat(Rank.matchCountOf(4,false)).isEqualTo(Rank.FOURTH);
        softly.assertThat(Rank.matchCountOf(5, false)).isEqualTo(Rank.THIRD);
        softly.assertThat(Rank.matchCountOf(5,true)).isEqualTo(Rank.SECOND);
        softly.assertThat(Rank.matchCountOf(6,false)).isEqualTo(Rank.FIRST);

        softly.assertAll();
    }

    @Test
    void matchCountOf_prize가없는일치개수의경우_NONE반환() {

        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(Rank.matchCountOf(0,true)).isEqualTo(Rank.NONE);
        softly.assertThat(Rank.matchCountOf(1,true)).isEqualTo(Rank.NONE);
        softly.assertThat(Rank.matchCountOf(2,false)).isEqualTo(Rank.NONE);

        softly.assertAll();
    }
}
