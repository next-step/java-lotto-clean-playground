package domain.winning;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LottoRankTest {
    @ParameterizedTest
    @DisplayName("일치 개수와 보너스 일치 여부에 해당하는 등수를 반환한다.")
    @CsvSource({
            "3, false, FIFTH",
            "4, false, FOURTH",
            "5, false, THIRD",
            "5, true, SECOND",
            "6, false, FIRST"
    })
    void returnRankByMatchCount(int matchCount, boolean bonusMatched, LottoRank expectedRank) {
        // when
        LottoRank rank = LottoRank.from(matchCount, bonusMatched);

        // then
        assertEquals(expectedRank, rank);
    }

    @ParameterizedTest
    @DisplayName("보너스 볼은 5개 일치일 때만 등수에 영향을 준다.")
    @CsvSource({
            "3, true, FIFTH",
            "4, true, FOURTH"
    })
    void bonusAffectsOnlyFiveMatches(int matchCount, boolean bonusMatched, LottoRank expectedRank) {
        // when
        LottoRank rank = LottoRank.from(matchCount, bonusMatched);

        // then
        assertEquals(expectedRank, rank);
    }

    @ParameterizedTest
    @DisplayName("해당하는 등수가 없으면 MISS를 반환한다.")
    @CsvSource({
            "0, false",
            "1, true",
            "2, false"
    })
    void returnMissWhenNoRank(int matchCount, boolean bonusMatched) {
        // when
        LottoRank rank = LottoRank.from(matchCount, bonusMatched);

        // then
        assertEquals(LottoRank.MISS, rank);
    }

    @ParameterizedTest
    @DisplayName("등수 상금에 당첨 장수를 곱한 값을 반환한다.")
    @CsvSource({
            "FIFTH, 2, 10000",
            "SECOND, 1, 30000000",
            "FIRST, 2, 4000000000"
    })
    void calculatePrizeByCount(LottoRank rank, int count, long expectedTotalPrize) {
        // when
        long totalPrize = rank.calculatePrize(count);

        // then
        assertEquals(expectedTotalPrize, totalPrize);
    }
}
