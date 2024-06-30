import domain.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static domain.LottoRank.*;
import static org.junit.jupiter.api.Assertions.*;

public class LottoRankTest {

    @Test
    void 당첨번호_일치_개수_테스트() {
        final List<Integer> lottoNumber = Arrays.asList(1, 2, 3, 4, 5, 6);
        final List<Integer> winNumber = Arrays.asList(1, 3, 5, 7, 9, 11);
        final LottoWin lottoWinning = new LottoWin();
        int expected = 3;

        int actual = lottoWinning.checkWinNumber(lottoNumber, winNumber);

        assertEquals(expected, actual);
    }

    @Test
    void 당첨등수_확인_테스트() {
        final List<Integer> LottoNumber = Arrays.asList(1, 3, 5, 7, 9, 11);
        final Lotto lotto= Lotto.createLotto(LottoNumber);
        final String winNumber="1,3,5,7,9,12";
        final int userBonusNumber=5;
        final int generateBonusNumber=10;

        LottoWin lottoWinning = new LottoWin();
        List<LottoRank> expectedRank= Arrays.asList(SECOND);

        List<LottoRank> actualRank = lottoWinning.calculateWinCounts(Collections.singletonList(lotto), winNumber, userBonusNumber, generateBonusNumber);
        assertEquals(expectedRank, actualRank);
    }

    @Test
    void 보너스볼_당첨된_경우(){
        final List<Integer> LottoNumber = Arrays.asList(1, 3, 5, 7, 9, 11);
        final Lotto lotto= Lotto.createLotto(LottoNumber);
        final String winNumber="1,3,5,7,9,12";
        final int userBonusNumber=10;
        final int generateBonusNumber=10;

        LottoWin lottoWinning = new LottoWin();
        List<LottoRank> expectedRank= Arrays.asList(BONUS);

        List<LottoRank> actualRank = lottoWinning.calculateWinCounts(Collections.singletonList(lotto), winNumber, userBonusNumber, generateBonusNumber);
        assertEquals(expectedRank, actualRank);
    }

    @Test
    void 당첨금액_확인_테스트() {
        final List<Integer> lottoNumber = Arrays.asList(1, 3, 5, 7, 9, 11);
        final Lotto lotto = Lotto.createLotto(lottoNumber);
        final String winNumber = "1,3,5,7,9,11";
        final int userBonusNumber = 10;
        final int generatedBonusNumber = 10;

        LottoWin lottoWinning = new LottoWin();
        List<LottoRank> winCounts = lottoWinning.calculateWinCounts(Collections.singletonList(lotto), winNumber, userBonusNumber, generatedBonusNumber);
        LottoResult lottoResult = new LottoResult(winCounts, 1000);

        int expected = 2000000000;

        int actual = (int) lottoResult.getTotalPrize();
        assertEquals(expected, actual);
    }

    @Test
    void 수익률_테스트() {
        final List<Integer> lottoNumber = Arrays.asList(1, 3, 5, 7, 9, 11);
        final Lotto lotto = Lotto.createLotto(lottoNumber);
        final String winNumber = "1,3,5,7,9,11";
        final int userBonusNumber = 10;
        final int generatedBonusNumber = 10;

        LottoWin lottoWinning = new LottoWin();
        List<LottoRank> winCounts = lottoWinning.calculateWinCounts(Collections.singletonList(lotto), winNumber, userBonusNumber, generatedBonusNumber);

        LottoResult lottoResult = new LottoResult(winCounts, 1000);
        double expected = 2000000.0;

        double actual = lottoResult.getProfitRate();
        assertEquals(expected, actual);
    }

    @Test
    void 잘못된_당첨번호_입력_테스트() {
        final List<Integer> lottoNumber = Arrays.asList(1, 3, 5, 7, 9, 11);
        final Lotto lotto = Lotto.createLotto(lottoNumber);
        final String winNumber = "1,3,5,7,9,A";
        final int userBonusNumber = 10;
        final int generatedBonusNumber = 10;

        LottoWin lottoWinning = new LottoWin();
        assertThrows(NumberFormatException.class, () ->
                lottoWinning.calculateWinCounts(Collections.singletonList(lotto), winNumber, userBonusNumber, generatedBonusNumber));
    }
}
