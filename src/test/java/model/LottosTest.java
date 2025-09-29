package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;

import static model.LottoFixture.DEFAULT_WINNING_NUMBERS;
import static model.LottoFixture.LOTTO_MATCH_0;
import static model.LottoFixture.LOTTO_MATCH_3;
import static model.LottoFixture.LOTTO_MATCH_4;
import static model.LottoFixture.LOTTO_MATCH_5;
import static model.LottoFixture.LOTTO_MATCH_6;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("model.Lottos 클래스 테스트")
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class LottosTest {
    @Test
    void calculateResult_호출_시_올바른_당첨_통계를_반환한다() {
        WinningNumbers winningNumbers = new WinningNumbers(DEFAULT_WINNING_NUMBERS);
        Lottos userLottos = new Lottos(Arrays.asList(
                LOTTO_MATCH_3, LOTTO_MATCH_4, LOTTO_MATCH_5, LOTTO_MATCH_6, LOTTO_MATCH_0
        ));

        Map<Rank, Integer> result = userLottos.calculateResult(winningNumbers);

        assertEquals(1, result.get(Rank.FIRST));
        assertEquals(1, result.get(Rank.SECOND));
        assertEquals(1, result.get(Rank.THIRD));
        assertEquals(1, result.get(Rank.FOURTH));
        assertEquals(1, result.getOrDefault(Rank.NONE, 0));
    }
}
