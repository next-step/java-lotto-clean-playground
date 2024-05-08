package domain.lottoWiningsStatistics;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.Rank;
import domain.lottoWinningStatistics.LottoWinningStatistics;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoWiningStatisticsTest {

    @Test
    @DisplayName("로또당첨통계 객체 생성")
    public void lottoWinningStatisticsTest() {
        //given
        Map<Rank, Integer> expectedRankStatistic = new HashMap<>();
        for(Rank rank : Rank.values()) {
            expectedRankStatistic.put(rank, 0);
        }

        double expectedReturnOfInvestment = 0.35;

        //when
        LottoWinningStatistics lottoWinningStatistics = new LottoWinningStatistics(expectedRankStatistic, expectedReturnOfInvestment);

        Map<Rank, Integer> rankStatistic = lottoWinningStatistics.getRankStatistic();

        //then
        assertAll(
                () -> assertThat(0).isEqualTo(rankStatistic.get(Rank.FIFTH_PRIZE)),
                () -> assertThat(0).isEqualTo(rankStatistic.get(Rank.FOURTH_PRIZE)),
                () -> assertThat(0).isEqualTo(rankStatistic.get(Rank.THIRD_PRIZE)),
                () -> assertThat(0).isEqualTo(rankStatistic.get(Rank.SECOND_PRIZE)),
                () -> assertThat(0).isEqualTo(rankStatistic.get(Rank.FIRST_PRIZE))
        );
    }
}
