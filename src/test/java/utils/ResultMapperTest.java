package utils;

import domain.*;
import dto.MatchResultDto;
import dto.ProfitDto;
import dto.WinningResultDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class ResultMapperTest {

    @Test
    @DisplayName("LottoStatistics를 MatchResultDto 리스트로 매핑한다")
    void mapLottoStatisticsToWinningResultDto() {
        LottoStatistics statistics = stubStatistics(Map.of(
                Rank.FIFTH, 2,
                Rank.THIRD, 1
        ));

        WinningResultDto winningResult = ResultMapper.toWinningResultDto(statistics);

        assertThat(winningResult.matches())
                .extracting(MatchResultDto::matchCount, MatchResultDto::prize, MatchResultDto::count)
                .containsExactlyInAnyOrder(
                        tuple(3, 5_000, 2),
                        tuple(4, 50_000, 0),
                        tuple(5, 1_500_000, 1),
                        tuple(5, 30_000_000, 0),
                        tuple(6, 2_000_000_000, 0)
                );
    }

    @ParameterizedTest(name = "수익률 {0}, 손해 여부 {1} → 수익률: {0}, 손해여부: {1}")
    @CsvSource({
            "1.5,false",
            "1.0,false",
            "0.75,true"
    })
    @DisplayName("Profit 객체를 ProfitDto로 매핑한다")
    void mapProfitToProfitDto(double expectedRate, boolean expectedIsLoss) {
        Profit profit = new StubProfit(expectedRate, expectedIsLoss);

        ProfitDto profitResult = ResultMapper.toProfitDto(profit);

        assertThat(profitResult.rate()).isEqualTo(expectedRate);
        assertThat(profitResult.isLoss()).isEqualTo(expectedIsLoss);
    }

    private LottoStatistics stubStatistics(Map<Rank, Integer> winningCounts) {
        WinningNumbers winningNumbers = new WinningNumbers(toWinningNumbers(List.of(1, 2, 3, 4, 5, 6)));
        BonusNumber dummyBonus = new BonusNumber(7, winningNumbers);

        return new LottoStatistics(new Lottos(List.of()), winningNumbers, dummyBonus) {
            @Override
            public int countOf(Rank rank) {
                return winningCounts.getOrDefault(rank, 0);
            }
        };
    }

    private static class StubProfit extends Profit {
        private final double overriddenRate;
        private final boolean overriddenIsLoss;

        public StubProfit(double overriddenRate, boolean overriddenIsLoss) {
            super(dummyStatistics(), 1);
            this.overriddenRate = overriddenRate;
            this.overriddenIsLoss = overriddenIsLoss;
        }

        @Override
        public double rate() {
            return overriddenRate;
        }

        @Override
        public boolean isLoss() {
            return overriddenIsLoss;
        }
    }

    private static LottoStatistics dummyStatistics() {
        WinningNumbers winningNumbers = new WinningNumbers(toWinningNumbers(List.of(1, 2, 3, 4, 5, 6)));
        BonusNumber dummyBonus = new BonusNumber(7, winningNumbers);
        return new LottoStatistics(new Lottos(List.of()), winningNumbers, dummyBonus);
    }

    private static List<WinningNumber> toWinningNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(WinningNumber::new)
                .toList();
    }
}
