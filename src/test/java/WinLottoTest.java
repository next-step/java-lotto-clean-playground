import model.Lotto;
import model.LottoNumber;
import model.WinLotto;
import model.LottoPrize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;


class WinLottoTest {

    private WinLotto winLotto;
    private final String winningNumbers = "1, 2, 3, 4, 5, 6";
    private final String bonusBall = "7";

    @BeforeEach
    void setUp() {
        winLotto = new WinLotto(winningNumbers, List.of(), bonusBall);
    }

    private Lotto createLotto(String numbers) {
        List<LottoNumber> lottoNumbers = Arrays.stream(numbers.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .map(number -> String.valueOf(number))
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        return new Lotto(lottoNumbers);
    }


    @Test
    void testFirstPrize() {
        Lotto userLotto = createLotto("1, 2, 3, 4, 5, 6");
        winLotto.updateWinningStates(List.of(userLotto));
        assertEquals(1, winLotto.getWinningStates().get(LottoPrize.FIRST));
    }

    @Test
    void testSecondPrize() {
        Lotto userLotto = createLotto("1, 2, 3, 4, 5, 7");
        winLotto.updateWinningStates(List.of(userLotto));
        assertEquals(1, winLotto.getWinningStates().get(LottoPrize.SECOND));
    }

    @Test
    void testThirdPrize() {
        Lotto userLotto = createLotto("1, 2, 3, 4, 5, 8");
        winLotto.updateWinningStates(List.of(userLotto));
        assertEquals(1, winLotto.getWinningStates().get(LottoPrize.THIRD));
    }

    @Test
    void testFourthPrize() {
        Lotto userLotto = createLotto("1, 2, 3, 4, 8, 9");
        winLotto.updateWinningStates(List.of(userLotto));
        assertEquals(1, winLotto.getWinningStates().get(LottoPrize.FOURTH));
    }

    @Test
    void testFifthPrize() {
        Lotto userLotto = createLotto("1, 2, 3, 7, 8, 9");
        winLotto.updateWinningStates(List.of(userLotto));
        assertEquals(1, winLotto.getWinningStates().get(LottoPrize.FIFTH));
    }
}
