package domain.lottoWiningsStatistics;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.lottoWinningStatistics.LottoWinningStatistics;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoWiningStatisticsTest {

    @Test
    @DisplayName("로또당첨통계 객체 생성")
    public void lottoWinningStatisticsTest() {
        //given
        List<Integer> expectedLottoWinningNumbers = List.of(1, 2, 3, 4, 5, 6);
        List<Integer> expectedLottoWinningCount = List.of(1, 0, 0, 1, 0);
        double expectedReturnOfInvestment = 0.35;

        //when
        LottoWinningStatistics lottoWinningStatistics =
                new LottoWinningStatistics(
                        expectedLottoWinningNumbers,
                        expectedLottoWinningCount,
                        expectedReturnOfInvestment);

        //then
        assertAll(
                () -> assertThat(expectedLottoWinningNumbers).isEqualTo(lottoWinningStatistics.getLottoWinnerNumbers()),
                () -> assertThat(expectedLottoWinningCount).isEqualTo(lottoWinningStatistics.getLottoWinnerCount()),
                () -> assertThat(expectedReturnOfInvestment).isEqualTo(lottoWinningStatistics.getReturnOfInvestment())
        );
    }
}
