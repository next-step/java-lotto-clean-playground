package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultTest {

    @DisplayName("당첨 번호와 로또 번호를 비교하여 올바른 수익률을 계산한다.")
    @Test
    void calculateProfitRate() {
        // given
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        Lotto lotto1 = new Lotto(List.of(1, 2, 3, 4, 5, 6)); // 1등 (2,000,000,000)
        Lotto lotto2 = new Lotto(List.of(1, 2, 3, 10, 11, 12)); // 5등 (5,000)
        Lotto lotto3 = new Lotto(List.of(10, 11, 12, 13, 14, 15)); // 꽝 (0)

        LottoTickets tickets = new LottoTickets(List.of(lotto1, lotto2, lotto3));
        int purchaseAmount = 3000;

        // when
        LottoResult result = new LottoResult(tickets, winningLotto);
        double profitRate = result.calculateProfitRate(purchaseAmount);

        // then
        // (2000000000 + 5000) / 3000 = 666668.333... -> 666668.33 (소수점 둘째자리 버림/반올림 처리 기준)
        assertThat(profitRate).isEqualTo(666668.33);
        assertThat(result.getRankCount(Rank.SIX)).isEqualTo(1);
        assertThat(result.getRankCount(Rank.THREE)).isEqualTo(1);
        assertThat(result.getRankCount(Rank.NONE)).isEqualTo(1);
    }
}