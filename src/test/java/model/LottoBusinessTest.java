package model;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LottoBusinessTest {
    private final LottoBusiness business = new LottoBusiness();

    @Test
    void 고정된로또가_생성된다() {
        // Given
        LottoBusiness business =
                new LottoBusiness(new FixedLottoNumberGenerator(List.of(1, 2, 3, 4, 5, 6)));

        // When
        LottoNumbers lotto = business.createOneLotto();

        // Then
        assertEquals(List.of(1, 2, 3, 4, 5, 6),
                lotto.getNumbers().stream().map(LottoNumber::getNumber).toList());
    }

    @Test
    void 요청한_개수만큼_로또가_생성된다() {
        // Given
        int count = 3;

        // When
        LottoNumbersRepository re = business.createLottos(count);

        // Then
        assertEquals(count, re.readLottoNumbersRepository().size());
        assertTrue(re.readLottoNumbersRepository()
                .stream().allMatch(n -> n.getNumbers().size() == 6));
    }

    @Test
    void 지난주_로또가_생성된다() {
        // Given
        String input = "1, 2,3, 4 ,5,6";

        // When
        LottoNumbers last = business.createLastLotto(input);

        // Then
        assertEquals(6, last.getNumbers().size());
        assertEquals(List.of(1, 2, 3, 4, 5, 6),
                last.getNumbers().stream().map(LottoNumber::getNumber).toList());
    }

    @Test
    void countMatchResults_4개일치면_FOUR가_1로_집계된다() {
        // Given
        LottoNumbers mine = new LottoNumbers(List.of(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
        ));
        LottoNumbers last = new LottoNumbers(List.of(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(7), new LottoNumber(8)
        ));

        // When
        Map<MatchResult, Integer> result =
                business.countMatchResults(List.of(mine), last.getNumbers());

        // Then
        assertEquals(1, result.get(MatchResult.FOUR));
    }

    @Test
    void calculateProfitRrate_3개일치_한장_구입1000원은_5배다() {
        // Given
        Map<MatchResult, Integer> matchCounts = Map.of(
                MatchResult.THREE, 1,
                MatchResult.ZERO, 0,
                MatchResult.ONE, 0,
                MatchResult.TWO, 0,
                MatchResult.FOUR, 0,
                MatchResult.FIVE, 0,
                MatchResult.SIX, 0
        );
        int money = 1000;

        // When
        String rate = business.calculateProfitRrate(matchCounts, money);

        // Then
        assertEquals("5.00", rate);
    }
}
