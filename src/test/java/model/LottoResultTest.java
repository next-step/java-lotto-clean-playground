package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoResultTest {
    @Test
    @DisplayName("일차히는 갯수에 따라 결과 반환")
    void testWhenNoBonusCount() {
        //given
        int matchCount = 5;
        int matchBonusCount = 0;

        // when
        LottoResult lottoResult = LottoResult.calculateLottoResult(matchCount, matchBonusCount);

        // then
        Assertions.assertEquals(LottoResult.FIVE, lottoResult);
    }

    @Test
    @DisplayName("일차히는 갯와 보너스볼 결과에 따라 결과 반환")
    void testWithBonusCount() {
        //given
        int matchCount = 5;
        int matchBonusCount = 1;

        // when
        LottoResult lottoResult = LottoResult.calculateLottoResult(matchCount, matchBonusCount);

        // then
        Assertions.assertEquals(LottoResult.FIVE_WITH_BONUS, lottoResult);
    }
}
