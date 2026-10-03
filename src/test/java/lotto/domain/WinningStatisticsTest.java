package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningStatisticsTest {

    @Test
    @DisplayName("보너스 번호가 틀릴 때 등수별로 정확히 센다")
    void 보너스_번호가_틀릴_때_등수별로_정확히_센다() {
        WinningNumbers winningNumbers = new WinningNumbers(
                new LottoNumbers(List.of(1, 2, 3, 4, 5, 6)),
                new LottoNumber(40));
        Lottos lottos = new Lottos(List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(10, 11, 12, 13, 14, 15))));
        WinningStatistics statistics = new WinningStatistics(lottos, winningNumbers);
        assertThat(statistics.countRank(Rank.FIRST)).isEqualTo(1);
        assertThat(statistics.countRank(Rank.MISS)).isEqualTo(1);
    }

    @Test
    @DisplayName("보너스 번호가 맞을 때 등수별로 정확히 센다")
    void 보너스_번호가_맞을_때_등수별로_정확히_센다() {
        WinningNumbers winningNumbers = new WinningNumbers(
                new LottoNumbers(List.of(1, 2, 3, 4, 5, 6)),
                new LottoNumber(40));
        Lottos lottos = new Lottos(List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 7)),
                new Lotto(List.of(1, 2, 3, 4, 5, 40))));
        WinningStatistics statistics = new WinningStatistics(lottos, winningNumbers);
        assertThat(statistics.countRank(Rank.SECOND)).isEqualTo(1);
        assertThat(statistics.countRank(Rank.THIRD)).isEqualTo(1);
    }

    @Test
    @DisplayName("수익률을 정확히 계산한다")
    void 수익률을_정확히_계산한다() {
        WinningNumbers winningNumbers = new WinningNumbers(
                new LottoNumbers(List.of(1, 2, 3, 4, 5, 6)),
                new LottoNumber(40));
        Lottos lottos = new Lottos(List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(10, 11, 12, 13, 14, 15))));
        WinningStatistics statistics = new WinningStatistics(lottos, winningNumbers);
        float expectedProfitRate = (float) Rank.FIRST.getPrize() / 2000;
        assertThat(statistics.getProfitRate()).isEqualTo(expectedProfitRate);
    }
}
