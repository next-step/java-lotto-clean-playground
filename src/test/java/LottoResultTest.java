import domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.Offset.offset;

class LottoResultTest {

    @Nested
    @DisplayName("총당첨금/수익률 계산")
    class MoneyCalculation {

        @Test
        @DisplayName("총당첨금과 수익률이 등수별 상금 합으로 계산된다")
        void calculatesTotalPrizeAndProfitRate() {
            WinningNumbers winning = WinningNumbers.of(List.of(1, 2, 3, 4, 5, 6), 7);

            List<LottoTicket> tickets = List.of(
                    new LottoTicket(List.of(1, 2, 3, 4, 5, 7)), // 5개정답 + 보너스 -> 30,000,000
                    new LottoTicket(List.of(1, 2, 3, 10, 11, 12)) // 3개정답 -> 5,000
            );

            Money money = Money.from(2000); // 티켓 투장

            LottoResult result = LottoResult.of(tickets, winning, money);

            long expectedTotalPrize = Rank.BONUS.prize() + Rank.THREE.prize(); // 30,000,000 + 5,000
            assertThat(result.totalPrize()).isEqualTo(expectedTotalPrize);

            double expectedProfitRate = (double) expectedTotalPrize / 2000;
            assertThat(String.format("%.2f", result.profitRate()))
                    .isEqualTo(String.format("%.2f", expectedProfitRate));
        }

        @Test
        @DisplayName("당첨이 하나도 없으면 총당첨금은 0이고 수익률도 0이다")
        void calculatesZeroWhenNoPrize() {
            WinningNumbers winning = WinningNumbers.of(List.of(1, 2, 3, 4, 5, 6), 7);

            List<LottoTicket> tickets = List.of(
                    new LottoTicket(List.of(8, 9, 10, 11, 12, 13)),
                    new LottoTicket(List.of(14, 15, 16, 17, 18, 19))
            );

            Money money = Money.from(2000);

            LottoResult result = LottoResult.of(tickets, winning, money);

            assertThat(result.totalPrize()).isZero();
            assertThat(result.profitRate()).isZero();
        }
    }
}
