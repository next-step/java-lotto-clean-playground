import model.Lotto;
import model.LottoNumber;
import model.Lottos;
import model.Rank;
import model.WinningNumbers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("model.Lottos 클래스 테스트")
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class LottosTest {
    @Test
    void calculateResult_호출_시_올바른_당첨_통계를_반환한다() {
        WinningNumbers winningNumbers = new WinningNumbers(Arrays.asList(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
        ));

        Lottos userLottos = new Lottos(Arrays.asList(
                new Lotto(Arrays.asList(1, 2, 3, 10, 11, 12)),
                new Lotto(Arrays.asList(1, 2, 3, 4, 11, 12)),
                new Lotto(Arrays.asList(1, 2, 3, 4, 5, 12)),
                new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)),
                new Lotto(Arrays.asList(10, 11, 12, 13, 14, 15))
        ));

        Map<Rank, Integer> result = userLottos.calculateResult(winningNumbers);

        assertEquals(1, result.get(Rank.FIRST));
        assertEquals(1, result.get(Rank.SECOND));
        assertEquals(1, result.get(Rank.THIRD));
        assertEquals(1, result.get(Rank.FOURTH));
        assertEquals(1, result.getOrDefault(Rank.NONE, 0));
    }
}
