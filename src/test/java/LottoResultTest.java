import domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultTest {
    private LottoTicket ticket(int... nums) {
        List<LottoNumber> numbers = new ArrayList<>();
        for (int n : nums) {
            numbers.add(LottoNumber.of(n));
        }
        return new LottoTicket(new Lotto(numbers));
    }

    @Nested
    @DisplayName("총당첨금/수익률 계산")
    class MoneyCalculation {
        private Money money;
        private LottoResult result;
        private long expectedTotalPrize;

        @BeforeEach
        void setUp() {
            WinningNumbers winning = WinningNumbers.of(List.of(1, 2, 3, 4, 5, 6), 7);

            List<LottoTicket> tickets = List.of(
                    ticket(1, 2, 3, 4, 5, 7), // 5개정답 + 보너스 -> 30,000,000
                    ticket(1, 2, 3, 10, 11, 12) // 3개정답 -> 5,000
            );

            money = Money.from(2000);
            result = LottoResult.of(tickets, winning, money);

            expectedTotalPrize = Rank.BONUS.prize() + Rank.THREE.prize(); // 30,000,000 + 5,000
        }

        @Test
        @DisplayName("총당첨금은 등수별 상금 합으로 계산된다")
        void calculatesTotalPrize() {
            assertThat(result.totalPrize()).isEqualTo(expectedTotalPrize);
        }

        @Test
        @DisplayName("수익률은 총당첨금 / 구입금액으로 계산된다")
        void calculatesProfitRate() {
            double expectedProfitRate = (double) expectedTotalPrize / money.amount();

            assertThat(String.format("%.2f", result.profitRate()))
                    .isEqualTo(String.format("%.2f", expectedProfitRate));
        }

        @Test
        @DisplayName("당첨이 하나도 없으면 총당첨금은 0이고 수익률도 0이다")
        void calculatesZeroWhenNoPrize() {
            WinningNumbers winning = WinningNumbers.of(List.of(1, 2, 3, 4, 5, 6), 7);

            List<LottoTicket> tickets = List.of(
                    ticket(8, 9, 10, 11, 12, 13),
                    ticket(14, 15, 16, 17, 18, 19)
            );

            Money money = Money.from(2000);

            LottoResult result = LottoResult.of(tickets, winning, money);

            assertThat(result.totalPrize()).isZero();
            assertThat(result.profitRate()).isZero();
        }
    }
    @Nested
    @DisplayName("등수별 개수 집계")
    class RankCounting {

        @Test
        void countsEachRankCorrectly() {
            WinningNumbers winning = WinningNumbers.of(List.of(1, 2, 3, 4, 5, 6), 7);

            List<LottoTicket> tickets = List.of(
                    ticket(1,2,3,4,5,7),
                    ticket(1,2,3,4,5,8),
                    ticket(1,2,3,10,11,12),
                    ticket(8,9,10,11,12,13)
            );

            Money money = Money.from(4000);

            LottoResult result = LottoResult.of(tickets, winning, money);

            assertThat(result.countOf(Rank.BONUS)).isEqualTo(1);
            assertThat(result.countOf(Rank.FIVE)).isEqualTo(1);
            assertThat(result.countOf(Rank.THREE)).isEqualTo(1);
            assertThat(result.countOf(Rank.FOUR)).isZero();
            assertThat(result.countOf(Rank.SIX)).isZero();
        }
    }
}
