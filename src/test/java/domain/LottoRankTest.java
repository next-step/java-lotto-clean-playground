package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoRankTest {
    @Test
    @DisplayName("6개 번호가 일치하면 1등이다")
    void returnsFirstWhenSixNumbersMatch() {
        LottoRank rank = LottoRank.from(6, false);

        assertEquals(LottoRank.FIRST, rank);
    }

    @Test
    @DisplayName("5개 번호와 보너스 번호가 일치하면 2등이다")
    void returnsSecondWhenFiveNumbersAndBonusMatch() {
        LottoRank rank = LottoRank.from(5, true);

        assertEquals(LottoRank.SECOND, rank);
    }

    @Test
    @DisplayName("5개 번호가 일치하고 보너스 번호가 일치하지 않으면 3등이다")
    void returnsThirdWhenFiveNumbersMatchWithoutBonus() {
        LottoRank rank = LottoRank.from(5, false);

        assertEquals(LottoRank.THIRD, rank);
    }

    @Test
    @DisplayName("4개 번호가 일치하면 4등이다")
    void returnsFourthWhenFourNumbersMatch() {
        LottoRank rank = LottoRank.from(4, false);

        assertEquals(LottoRank.FOURTH, rank);
    }

    @Test
    @DisplayName("3개 번호가 일치하면 5등이다")
    void returnsFifthWhenThreeNumbersMatch() {
        LottoRank rank = LottoRank.from(3, false);

        assertEquals(LottoRank.FIFTH, rank);
    }

    @Test
    @DisplayName("일치하는 번호가 3개 미만이면 당첨되지 않는다")
    void returnsNullWhenMatchCountIsLessThanThree() {
        LottoRank rank = LottoRank.from(2, false);

        assertNull(rank);
    }
}
