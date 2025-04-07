import static java.util.Locale.LanguageRange.parse;
import static model.LottoType.AUTO;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import model.Lotto;
import model.LottoNumber;
import model.Lottos;
import model.Rank;
import model.WinningLotto;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LottosTest {

    @ParameterizedTest
    @CsvSource({
            "'1,2,3,4,5,6', SIX_MATCH",
            "'1,2,3,4,5,45', FIVE_MATCH",
            "'1,2,3,4,44,45', FOUR_MATCH",
            "'1,2,3,43,44,45', THREE_MATCH"
    })
    void 등수_정확히_판별한다(String numbers, Rank expectedRank) {
        Lotto winning = new Lotto(List.of(
                LottoNumber.valueOf(1), LottoNumber.valueOf(2),
                LottoNumber.valueOf(3), LottoNumber.valueOf(4),
                LottoNumber.valueOf(5), LottoNumber.valueOf(6)
        ),AUTO);
        LottoNumber bonus = LottoNumber.valueOf(7);
        WinningLotto winningLotto = new WinningLotto(winning, bonus);

        List<LottoNumber> matchedNumbers = Arrays.stream(numbers.split(","))
                .map(s -> LottoNumber.valueOf(Integer.parseInt(s.trim())))
                .collect(Collectors.toList());

        Lottos lottos = new Lottos(List.of(new Lotto(matchedNumbers,AUTO)));
        Map<Rank, Integer> result = lottos.countResult(winningLotto);

        assertEquals(1, result.get(expectedRank));
    }
}
