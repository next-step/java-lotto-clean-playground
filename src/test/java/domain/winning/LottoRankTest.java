package domain.winning;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LottoRankTest {
    @ParameterizedTest
    @DisplayName("일치 개수에 해당하는 등수를 반환한다.")
    @CsvSource({
            "3, THREE",
            "4, FOUR",
            "5, FIVE",
            "6, SIX"
    })
    void returnRankByMatchCount(int matchCount, LottoRank expectedRank) {
        LottoRank rank = LottoRank.from(matchCount);
        assertEquals(expectedRank, rank);
    }

    @ParameterizedTest
    @DisplayName("해당하는 등수가 없으면 MISS를 반환한다.")
    @CsvSource({
            "0, MISS",
            "1, MISS",
            "2, MISS"
    })
    void returnMissWhenNoRank(int matchCount, LottoRank expectedRank) {
        LottoRank rank = LottoRank.from(matchCount);
        assertEquals(expectedRank, rank);
    }

    @ParameterizedTest
    @DisplayName("등수 상금에 당첨 장수를 곱한 값을 반환한다.")
    @CsvSource({
            "THREE, 2,  10000",
            "SIX, 1, 2000000000"
    })
    void calculatePrizeByCount(LottoRank rank, int count, long expectedTotalPrize) {
        assertEquals(rank.calculatePrize(count), expectedTotalPrize);
    }
}
