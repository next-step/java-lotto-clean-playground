package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("LottoStatistics 클래스")
class LottoStatisticsTest {

    private LottoStatistics lottoStatistics;

    @BeforeEach
    void setUp() {
        lottoStatistics = new LottoStatistics();
    }

    @Test
    @DisplayName("당첨 결과를 집계한다.")
    void shouldCountMatchesCorrectly() {
        ArrayList<LottoWinningType> winningTypes = new ArrayList<>(List.of(
                LottoWinningType.FIRST_PLACE,
                LottoWinningType.FIFTH_PLACE,
                LottoWinningType.NO_PRIZE,
                LottoWinningType.FIFTH_PLACE,
                LottoWinningType.NO_PRIZE,
                LottoWinningType.NO_PRIZE
        ));

        Map<LottoWinningType, Integer> stats = lottoStatistics.countMatches(winningTypes);

        assertThat(stats.get(LottoWinningType.FIRST_PLACE)).isEqualTo(1);
        assertThat(stats.get(LottoWinningType.SECOND_PLACE)).isEqualTo(0);
        assertThat(stats.get(LottoWinningType.THIRD_PLACE)).isEqualTo(0);
        assertThat(stats.get(LottoWinningType.FOURTH_PLACE)).isEqualTo(0);
        assertThat(stats.get(LottoWinningType.FIFTH_PLACE)).isEqualTo(2);
        assertThat(stats.get(LottoWinningType.NO_PRIZE)).isEqualTo(3);
    }

    @Test
    @DisplayName("빈 당첨 결과 리스트를 전달하면 모든 횟수는 0으로 유지된다.")
    void shouldHandleEmptyWinningList() {
        ArrayList<LottoWinningType> winningTypes = new ArrayList<>();

        Map<LottoWinningType, Integer> stats = lottoStatistics.countMatches(winningTypes);

        assertThat(stats.values()).allMatch(count -> count == 0);
    }

    @Test
    @DisplayName("getMatchStatistics는 현재 통계를 반환한다.")
    void getMatchStatisticsShouldReturnCurrentStats() {
        ArrayList<LottoWinningType> winningTypes = new ArrayList<>(List.of(
                LottoWinningType.FOURTH_PLACE
        ));
        lottoStatistics.countMatches(winningTypes);

        Map<LottoWinningType, Integer> stats = lottoStatistics.getMatchStatistics();

        assertThat(stats.get(LottoWinningType.FOURTH_PLACE)).isEqualTo(1);
        assertThat(stats.get(LottoWinningType.FIFTH_PLACE)).isEqualTo(0);
    }
}
