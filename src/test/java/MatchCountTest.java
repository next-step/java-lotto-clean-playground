import domain.Lotto;
import domain.LottoNumber;
import domain.MatchCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class MatchCountTest {

    @Test
    @DisplayName("구매한 로또 티켓들의 당첨 통계를 올바르게 계산한다")
    void calculateStatisticsTest() {

        Lotto winningLotto = createLotto(1, 2, 3, 4, 5, 6);
        LottoNumber bonusBall = new LottoNumber(7);

        List<Lotto> tickets = Arrays.asList(
                createLotto(1, 2, 3, 4, 5, 6),
                createLotto(1, 2, 3, 4, 5, 7),
                createLotto(1, 2, 3, 4, 5, 8),
                createLotto(1, 2, 3, 4, 10, 11),
                createLotto(1, 2, 3, 12, 13, 14)
        );

        MatchCount result = MatchCount.calculateStatistics(tickets, winningLotto, bonusBall);

        assertEquals(1, result.getCount(domain.LottoPrice.MATCH_6));
        assertEquals(1, result.getCount(domain.LottoPrice.MATCH_5_BONUS));
        assertEquals(1, result.getCount(domain.LottoPrice.MATCH_5));
        assertEquals(1, result.getCount(domain.LottoPrice.MATCH_4));
        assertEquals(1, result.getCount(domain.LottoPrice.MATCH_3));
    }

    private Lotto createLotto(int... numbers) {
        return new Lotto(
                new TreeSet<>(
                        Arrays.stream(numbers)
                              .mapToObj(LottoNumber::new)
                              .toList()
                )
        );
    }
}
