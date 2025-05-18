package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoStatisticsTest {

    @Test
    @DisplayName("3개 일치하면 Rank.FIFTH로 집계된다")
    void rankFifthWhenThreeMatches() {
        List<Integer> lottoNumbers = List.of(1, 2, 3, 10, 11, 12);
        List<Integer> winningNumbers = List.of(1, 2, 3, 40, 41, 42);
        int bonusNumber = 7;

        LottoStatistics statistics = statisticsOf(lottoNumbers, winningNumbers, bonusNumber);

        assertThat(statistics.countOf(Rank.FIFTH)).isEqualTo(1);
    }

    @Test
    @DisplayName("4개 일치하면 Rank.FOURTH로 집계된다")
    void rankFourthWhenFourMatches() {
        List<Integer> lottoNumbers = List.of(1, 2, 3, 4, 11, 12);
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 40, 41);
        int bonusNumber = 7;

        LottoStatistics statistics = statisticsOf(lottoNumbers, winningNumbers, bonusNumber);

        assertThat(statistics.countOf(Rank.FOURTH)).isEqualTo(1);
    }

    @Test
    @DisplayName("5개 일치하면 보너스 볼 없이 Rank.THIRD로 집계된다")
    void rankThirdWhenFiveMatchesWithoutBonus() {
        List<Integer> lottoNumbers = List.of(1, 2, 3, 4, 5, 12);
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;

        LottoStatistics statistics = statisticsOf(lottoNumbers, winningNumbers, bonusNumber);

        assertThat(statistics.countOf(Rank.THIRD)).isEqualTo(1);
    }

    @Test
    @DisplayName("5개 + 보너스 볼 일치하면 Rank.SECOND로 집계된다")
    void rankSecondWhenFiveMatchesWithBonus() {
        List<Integer> lottoNumbers = List.of(1, 2, 3, 4, 5, 7);
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;

        LottoStatistics statistics = statisticsOf(lottoNumbers, winningNumbers, bonusNumber);

        assertThat(statistics.countOf(Rank.SECOND)).isEqualTo(1);
    }

    @Test
    @DisplayName("6개 일치하면 Rank.FIRST로 집계된다")
    void rankFirstWhenSixMatches() {
        List<Integer> lottoNumbers = List.of(1, 2, 3, 4, 5, 6);
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;

        LottoStatistics statistics = statisticsOf(lottoNumbers, winningNumbers, bonusNumber);

        assertThat(statistics.countOf(Rank.FIRST)).isEqualTo(1);
    }

    @Test
    @DisplayName("일치 개수가 2 이하이면 Rank.MISS로 간주되며 통계에 포함되지 않는다")
    void missRankNotStored() {
        List<Integer> lottoNumbers = List.of(10, 11, 12, 13, 14, 15);
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;

        LottoStatistics statistics = statisticsOf(lottoNumbers, winningNumbers, bonusNumber);

        assertThat(statistics.countOf(Rank.MISS)).isEqualTo(0);
    }

    private LottoStatistics statisticsOf(List<Integer> lottoNums, List<Integer> winningNums, int bonus) {
        Lotto lotto = new Lotto(toLottoNumbers(lottoNums));
        Lottos lottos = new Lottos(List.of(lotto));
        WinningNumbers winningNumbers = new WinningNumbers(toWinningNumbers(winningNums));
        BonusNumber bonusNumber = new BonusNumber(bonus, winningNumbers);
        return new LottoStatistics(lottos, winningNumbers, bonusNumber);
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
