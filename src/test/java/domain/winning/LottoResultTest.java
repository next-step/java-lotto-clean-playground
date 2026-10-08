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
        // given
        List<LottoRank> ranks = List.of(LottoRank.FIFTH, LottoRank.FIFTH, LottoRank.SECOND, LottoRank.MISS);

        // when
        LottoResult result = new LottoResult(ranks);

        // then
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(result.countOf(LottoRank.FIFTH)).isEqualTo(2);
        softAssertions.assertThat(result.countOf(LottoRank.FOURTH)).isEqualTo(0);
        softAssertions.assertThat(result.countOf(LottoRank.SECOND)).isEqualTo(1);
        softAssertions.assertThat(result.countOf(LottoRank.MISS)).isEqualTo(1);
        softAssertions.assertAll();
    }

    @Test
    @DisplayName("등수 목록이 비어있으면 모든 등수가 0장이다.")
    void countZeroWhenEmpty() {
        // given
        List<LottoRank> ranks = List.of();

        // when
        LottoResult result = new LottoResult(ranks);

        // then
        SoftAssertions softAssertions = new SoftAssertions();
        for (LottoRank rank : LottoRank.values()) {
            softAssertions.assertThat(result.countOf(rank)).isEqualTo(0);
        }
        softAssertions.assertAll();
    }

    @Test
    @DisplayName("여러 등수의 상금을 모두 더한다.")
    void sumPrizeOfAllRank() {
        // given
        LottoResult result = new LottoResult(List.of(
                LottoRank.FIFTH, LottoRank.FOURTH, LottoRank.THIRD, LottoRank.SECOND, LottoRank.FIRST));

        // when
        long totalPrize = result.calculateTotalPrize();

        // then
        assertThat(totalPrize).isEqualTo(2_031_555_000L);
    }

    @Test
    @DisplayName("모두 낙첨이면 총 상금은 0이다.")
    void zeroPrizeWhenAllMiss() {
        // given
        LottoResult result = new LottoResult(List.of(LottoRank.MISS, LottoRank.MISS));

        // when
        long totalPrize = result.calculateTotalPrize();

        // then
        assertThat(totalPrize).isEqualTo(0L);
    }

    @Test
    @DisplayName("1등이 여러 장이어도 총 상금이 음수가 되지 않는다.")
    void totalPrizeWithoutOverflow() {
        // given
        LottoResult result = new LottoResult(List.of(LottoRank.FIRST, LottoRank.FIRST));

        // when
        long totalPrize = result.calculateTotalPrize();

        // then
        assertThat(totalPrize).isEqualTo(4_000_000_000L);
    }
}
