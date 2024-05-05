package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class ResultStatisticsTest {

    @Test
    void 순위_목록을_통해_당첨_결과가_생성된다() {
        // given
        List<Rank> ranks = List.of(
                Rank._5TH_PRIZE,
                Rank._5TH_PRIZE,
                Rank._LAST_PRIZE,
                Rank._LAST_PRIZE,
                Rank._3RD_PRIZE
        );

        // when
        var resultStatistics = ResultStatistics.from(ranks);

        // then
        assertThat(resultStatistics.countOf(Rank._5TH_PRIZE)).isEqualTo(2);
        assertThat(resultStatistics.countOf(Rank._LAST_PRIZE)).isEqualTo(2);
        assertThat(resultStatistics.countOf(Rank._3RD_PRIZE)).isEqualTo(1);
    }


    @Test
    void 수익률을_계산한다() {
        // given
        List<Rank> ranks = List.of(
                Rank._5TH_PRIZE,
                Rank._5TH_PRIZE,
                Rank._LAST_PRIZE,
                Rank._LAST_PRIZE,
                Rank._LAST_PRIZE
        );
        ResultStatistics resultStatistics = ResultStatistics.from(ranks);

        // when
        double profitRate = resultStatistics.calculateProfitRate();

        // then
        assertThat(profitRate).isEqualTo(2);
    }
}
