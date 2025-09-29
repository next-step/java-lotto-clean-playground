// src/test/java/domain/MatchCountTest.java
import domain.Lotto;
import domain.LottoPrice;
import domain.LottoNumber;
import domain.Match;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

class MatchCountTest {
    @ParameterizedTest
    @CsvSource({
            "1,2,3,4,5,6,1,2,3,4,5,6,MATCH_6",
            "1,2,3,4,5,7,1,2,3,4,5,6,MATCH_5",
            "1,2,3,4,8,9,1,2,3,4,5,6,MATCH_4"
    })
    void 당첨_통계_테스트(int n1, int n2, int n3, int n4, int n5, int n6,
                   int w1, int w2, int w3, int w4, int w5, int w6,
                   LottoPrice expectedRank) {
        Lotto lotto = new Lotto(Arrays.asList(
                new LottoNumber(n1), new LottoNumber(n2), new LottoNumber(n3),
                new LottoNumber(n4), new LottoNumber(n5), new LottoNumber(n6)
        ));
        Lotto winningLotto = new Lotto(Arrays.asList(
                new LottoNumber(w1), new LottoNumber(w2), new LottoNumber(w3),
                new LottoNumber(w4), new LottoNumber(w5), new LottoNumber(w6)
        ));
        int matchCount = Match.getMatchCount(lotto, winningLotto);
        LottoPrice actualRank = LottoPrice.valueOf("MATCH_" + matchCount);
        assertEquals(expectedRank, actualRank);
    }
}
