import domain.Lotto;
import domain.LottoPrice;
import domain.LottoNumber;
import domain.Match;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static domain.MatchCount.determineLottoPrice;
import static org.junit.jupiter.api.Assertions.*;

class MatchCountTest {
    @ParameterizedTest
    @CsvSource({
            "1,2,3,4,5,6,1,2,3,4,5,6,7,false,MATCH_6",
            "1,2,3,4,5,7,1,2,3,4,5,6,7,true,MATCH_5_BONUS",
            "1,2,3,4,5,8,1,2,3,4,5,6,7,false,MATCH_5",
            "1,2,3,4,8,9,1,2,3,4,5,6,7,false,MATCH_4",
            "1,2,3,8,9,10,1,2,3,4,5,6,7,false,MATCH_3"
    })
    @DisplayName("당첨 통계 테스트-보너스볼 포함")
    void MatchBonus(int n1, int n2, int n3, int n4, int n5, int n6,
                    int w1, int w2, int w3, int w4, int w5, int w6, int bonus,
                    LottoPrice expectedRank) {
        Lotto lotto = new Lotto(new java.util.TreeSet<>(Arrays.asList(
                new LottoNumber(n1), new LottoNumber(n2), new LottoNumber(n3),
                new LottoNumber(n4), new LottoNumber(n5), new LottoNumber(n6)
        )));

        Lotto winningLotto = new Lotto(new java.util.TreeSet<>(Arrays.asList(
                new LottoNumber(w1), new LottoNumber(w2), new LottoNumber(w3),
                new LottoNumber(w4), new LottoNumber(w5), new LottoNumber(w6)
        )));

        LottoNumber bonusBall = new LottoNumber(bonus);
        int matchCount = Match.getMatchCount(lotto, winningLotto);
        boolean hasBonusBall = (lotto.contains(bonusBall) == 1);

        LottoPrice actualRank = determineLottoPrice(matchCount, hasBonusBall);
        assertEquals(expectedRank, actualRank);
    }

}
