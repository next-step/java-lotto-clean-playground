package domain.winning;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoResultTest {
    @Test
    @DisplayName("등수별 당첨 장수를 집계한다.")
    void countByRank() {
        List<Integer> matchCounts = List.of(3, 3, 6, 0);

        LottoResult result = new LottoResult(matchCounts);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(result.countOf(LottoRank.THREE)).isEqualTo(2);
        softAssertions.assertThat(result.countOf(LottoRank.FOUR)).isEqualTo(0);
        softAssertions.assertThat(result.countOf(LottoRank.SIX)).isEqualTo(1);
        softAssertions.assertThat(result.countOf(LottoRank.MISS)).isEqualTo(1);
        softAssertions.assertAll();
    }

    @Test
    @DisplayName("일치 개수 목록이 비어있으면 모든 등수가 0장이다.")
    void countZeroWhenEmpty() {
        List<Integer> matchCounts = List.of();
        LottoResult result = new LottoResult(matchCounts);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(result.countOf(LottoRank.THREE)).isEqualTo(0);
        softAssertions.assertThat(result.countOf(LottoRank.FOUR)).isEqualTo(0);
        softAssertions.assertThat(result.countOf(LottoRank.FIVE)).isEqualTo(0);
        softAssertions.assertThat(result.countOf(LottoRank.SIX)).isEqualTo(0);
        softAssertions.assertThat(result.countOf(LottoRank.MISS)).isEqualTo(0);
        softAssertions.assertAll();
    }

    @Test
    @DisplayName("여러 등수의 상금을 모두 더한다.")
    void sumPrizeOfAllRank() {
        List<Integer> matchCounts = List.of(3, 4, 5, 6);
        LottoResult result = new LottoResult(matchCounts);

        assertThat(result.calculateTotalPrize()).isEqualTo(2001555000);
    }

    @Test
    @DisplayName("모두 낙첨이면 총 상금은 0이다.")
    void zeroPrizeWhenAllMiss() {
        List<Integer> matchCounts = List.of(0, 1, 2);
        LottoResult result = new LottoResult(matchCounts);

        assertThat(result.calculateTotalPrize()).isEqualTo(0);
    }
}
