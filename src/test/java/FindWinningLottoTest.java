import static org.junit.jupiter.api.Assertions.*;

import domain.FindWinningLotto;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class FindWinningLottoTest {

    @Test
    public void testCalculateWinningResult_Six() {
        // Given
        List<List<Integer>> manualLottoInputs = List.of(
                List.of(1, 2, 3, 4, 5, 6),
                List.of(11, 12, 13, 14, 15, 16)
        );
        List<Integer> lastWeekWinningNumbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusWinningNumber = 7;

        FindWinningLotto findWinningLotto = new FindWinningLotto();

        // When
        Map<FindWinningLotto.LottoRank, Integer> result = findWinningLotto.calculateWinningResult(manualLottoInputs, lastWeekWinningNumbers, bonusWinningNumber);

        // Then
        assertEquals(1, result.get(FindWinningLotto.LottoRank.SIX));  // 6개 일치
        assertEquals(0, result.get(FindWinningLotto.LottoRank.FIVE_WITH_BONUS));  // 보너스 번호 포함 5개 일치 없음
        assertEquals(1, result.get(FindWinningLotto.LottoRank.NONE));  // 나머지 번호는 일치하지 않음
    }

    @Test
    public void testCalculateWinningResult_FiveWithBonus() {
        // Given
        List<List<Integer>> manualLottoInputs = List.of(
                List.of(1, 2, 3, 4, 5, 7),
                List.of(11, 12, 13, 14, 15, 16)
        );
        List<Integer> lastWeekWinningNumbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusWinningNumber = 7;

        FindWinningLotto findWinningLotto = new FindWinningLotto();

        // When
        Map<FindWinningLotto.LottoRank, Integer> result = findWinningLotto.calculateWinningResult(manualLottoInputs, lastWeekWinningNumbers, bonusWinningNumber);

        // Then
        assertEquals(0, result.get(FindWinningLotto.LottoRank.SIX));
        assertEquals(1, result.get(FindWinningLotto.LottoRank.FIVE_WITH_BONUS));
        assertEquals(0, result.get(FindWinningLotto.LottoRank.FIVE));
        assertEquals(1, result.get(FindWinningLotto.LottoRank.NONE));
    }

    @Test
    public void testCalculateWinningResult_Five() {
        // Given
        List<List<Integer>> manualLottoInputs = List.of(
                List.of(1, 2, 3, 4, 5, 7),
                List.of(11, 12, 13, 14, 15, 16)
        );
        List<Integer> lastWeekWinningNumbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusWinningNumber = 8;

        FindWinningLotto findWinningLotto = new FindWinningLotto();

        // When
        Map<FindWinningLotto.LottoRank, Integer> result = findWinningLotto.calculateWinningResult(manualLottoInputs, lastWeekWinningNumbers, bonusWinningNumber);

        // Then
        assertEquals(0, result.get(FindWinningLotto.LottoRank.SIX));
        assertEquals(0, result.get(FindWinningLotto.LottoRank.FIVE_WITH_BONUS));
        assertEquals(1, result.get(FindWinningLotto.LottoRank.FIVE));
        assertEquals(1, result.get(FindWinningLotto.LottoRank.NONE));
    }

    @Test
    public void testCalculateWinningResult_Four() {
        // Given
        List<List<Integer>> manualLottoInputs = List.of(
                List.of(1, 2, 3, 4, 5, 6),
                List.of(11, 12, 13, 14, 15, 16)
        );
        List<Integer> lastWeekWinningNumbers = List.of(1, 2, 3, 4, 44, 45);
        int bonusWinningNumber = 43;

        FindWinningLotto findWinningLotto = new FindWinningLotto();

        // When
        Map<FindWinningLotto.LottoRank, Integer> result = findWinningLotto.calculateWinningResult(manualLottoInputs, lastWeekWinningNumbers, bonusWinningNumber);

        // Then
        assertEquals(0, result.get(FindWinningLotto.LottoRank.SIX));
        assertEquals(0, result.get(FindWinningLotto.LottoRank.FIVE_WITH_BONUS));
        assertEquals(0, result.get(FindWinningLotto.LottoRank.FIVE));
        assertEquals(1, result.get(FindWinningLotto.LottoRank.FOUR));
        assertEquals(0, result.get(FindWinningLotto.LottoRank.THREE));
        assertEquals(1, result.get(FindWinningLotto.LottoRank.NONE));
    }

    @Test
    public void testCalculateWinningResult_Three() {
        // Given
        List<List<Integer>> manualLottoInputs = List.of(
                List.of(1, 2, 3, 4, 5, 6),
                List.of(11, 12, 13, 14, 15, 16)
        );
        List<Integer> lastWeekWinningNumbers = List.of(1, 2, 3, 42, 43, 44);
        int bonusWinningNumber = 45;

        FindWinningLotto findWinningLotto = new FindWinningLotto();

        // When
        Map<FindWinningLotto.LottoRank, Integer> result = findWinningLotto.calculateWinningResult(manualLottoInputs, lastWeekWinningNumbers, bonusWinningNumber);

        // Then
        assertEquals(0, result.get(FindWinningLotto.LottoRank.SIX));
        assertEquals(0, result.get(FindWinningLotto.LottoRank.FIVE_WITH_BONUS));
        assertEquals(0, result.get(FindWinningLotto.LottoRank.FIVE));
        assertEquals(0, result.get(FindWinningLotto.LottoRank.FOUR));
        assertEquals(1, result.get(FindWinningLotto.LottoRank.THREE));
        assertEquals(1, result.get(FindWinningLotto.LottoRank.NONE));
    }

    @Test
    public void testCalculateWinningResult_Two() {
        // Given
        List<List<Integer>> manualLottoInputs = List.of(
                List.of(1, 2, 3, 4, 5, 6),
                List.of(11, 12, 13, 14, 15, 16)
        );
        List<Integer> lastWeekWinningNumbers = List.of(1, 2, 41, 42, 43, 44);
        int bonusWinningNumber = 45;

        FindWinningLotto findWinningLotto = new FindWinningLotto();

        // When
        Map<FindWinningLotto.LottoRank, Integer> result = findWinningLotto.calculateWinningResult(manualLottoInputs, lastWeekWinningNumbers, bonusWinningNumber);

        // Then
        assertEquals(0, result.get(FindWinningLotto.LottoRank.SIX));
        assertEquals(0, result.get(FindWinningLotto.LottoRank.FIVE_WITH_BONUS));
        assertEquals(0, result.get(FindWinningLotto.LottoRank.FIVE));
        assertEquals(0, result.get(FindWinningLotto.LottoRank.FOUR));
        assertEquals(0, result.get(FindWinningLotto.LottoRank.THREE));
        assertEquals(2, result.get(FindWinningLotto.LottoRank.NONE));
    }

    @Test
    public void testCalculateWinningResult_One() {
        // Given
        List<List<Integer>> manualLottoInputs = List.of(
                List.of(1, 2, 3, 4, 5, 6),
                List.of(11, 12, 13, 14, 15, 16)
        );
        List<Integer> lastWeekWinningNumbers = List.of(1, 40, 41, 42, 43, 44);
        int bonusWinningNumber = 45;

        FindWinningLotto findWinningLotto = new FindWinningLotto();

        // When
        Map<FindWinningLotto.LottoRank, Integer> result = findWinningLotto.calculateWinningResult(manualLottoInputs, lastWeekWinningNumbers, bonusWinningNumber);

        // Then
        assertEquals(0, result.get(FindWinningLotto.LottoRank.SIX));
        assertEquals(0, result.get(FindWinningLotto.LottoRank.FIVE_WITH_BONUS));
        assertEquals(0, result.get(FindWinningLotto.LottoRank.FIVE));
        assertEquals(0, result.get(FindWinningLotto.LottoRank.FOUR));
        assertEquals(0, result.get(FindWinningLotto.LottoRank.THREE));
        assertEquals(2, result.get(FindWinningLotto.LottoRank.NONE));
    }

    @Test
    public void testCalculateWinningResult_Zero() {
        // Given
        List<List<Integer>> manualLottoInputs = List.of(
                List.of(1, 2, 3, 4, 5, 6),
                List.of(11, 12, 13, 14, 15, 16)
        );
        List<Integer> lastWeekWinningNumbers = List.of(39, 40, 41, 42, 43, 44);
        int bonusWinningNumber = 45;

        FindWinningLotto findWinningLotto = new FindWinningLotto();

        // When
        Map<FindWinningLotto.LottoRank, Integer> result = findWinningLotto.calculateWinningResult(manualLottoInputs, lastWeekWinningNumbers, bonusWinningNumber);

        // Then
        assertEquals(0, result.get(FindWinningLotto.LottoRank.SIX));
        assertEquals(0, result.get(FindWinningLotto.LottoRank.FIVE_WITH_BONUS));
        assertEquals(0, result.get(FindWinningLotto.LottoRank.FIVE));
        assertEquals(0, result.get(FindWinningLotto.LottoRank.FOUR));
        assertEquals(0, result.get(FindWinningLotto.LottoRank.THREE));
        assertEquals(2, result.get(FindWinningLotto.LottoRank.NONE));
    }
}
