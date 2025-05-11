package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoStatisticsTest {

    @Test
    @DisplayName("3개 일치하면 Rank.THREE로 집계된다")
    void rankThreeWhenThreeMatches() {
        LottoNumbers lotto = new LottoNumbers(toLottoNumbers(List.of(1, 2, 3, 10, 11, 12)));
        Lottos lottos = new Lottos(List.of(new Lotto(lotto)));
        WinningNumbers winningNumbers = new WinningNumbers(toWinningNumbers(List.of(1, 2, 3, 40, 41, 42)));

        LottoStatistics statistics = new LottoStatistics(lottos, winningNumbers);

        assertThat(statistics.countOf(Rank.THREE)).isEqualTo(1);
    }

    @Test
    @DisplayName("4개 일치하면 Rank.FOUR로 집계된다")
    void rankFourWhenFourMatches() {
        LottoNumbers lotto = new LottoNumbers(toLottoNumbers(List.of(1, 2, 3, 4, 11, 12)));
        Lottos lottos = new Lottos(List.of(new Lotto(lotto)));
        WinningNumbers winningNumbers = new WinningNumbers(toWinningNumbers(List.of(1, 2, 3, 4, 40, 41)));

        LottoStatistics statistics = new LottoStatistics(lottos, winningNumbers);

        assertThat(statistics.countOf(Rank.FOUR)).isEqualTo(1);
    }

    @Test
    @DisplayName("0개 일치하면 Rank.NONE이며 통계에 저장되지 않는다")
    void noneRankNotStored() {
        LottoNumbers lotto = new LottoNumbers(toLottoNumbers(List.of(10, 11, 12, 13, 14, 15)));
        Lottos lottos = new Lottos(List.of(new Lotto(lotto)));
        WinningNumbers winningNumbers = new WinningNumbers(toWinningNumbers(List.of(1, 2, 3, 4, 5, 6)));

        LottoStatistics statistics = new LottoStatistics(lottos, winningNumbers);

        assertThat(statistics.countOf(Rank.NONE)).isEqualTo(0);
    }

    private List<LottoNumber> toLottoNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::new)
                .toList();
    }

    private List<WinningNumber> toWinningNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(WinningNumber::new)
                .toList();
    }
}
