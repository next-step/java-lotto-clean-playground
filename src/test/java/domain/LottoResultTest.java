package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultTest {

    @DisplayName("자동과 수동 로또 티켓의 당첨 결과를 합산하여 정확히 계산한다.")
    @Test
    void calculate() {
        // given
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;

        Lotto autoLotto1 = new Lotto(List.of(1, 2, 3, 4, 5, 6)); // SIX
        Lotto autoLotto2 = new Lotto(List.of(1, 2, 3, 10, 11, 12)); // THREE
        LottoTickets autoTickets = new LottoTickets(List.of(autoLotto1, autoLotto2));

        Lotto manualLotto1 = new Lotto(List.of(1, 2, 3, 4, 5, 7)); // FIVE_BONUS
        Lotto manualLotto2 = new Lotto(List.of(10, 11, 12, 13, 14, 15)); // NONE
        LottoTickets manualTickets = new LottoTickets(List.of(manualLotto1, manualLotto2));

        // when
        LottoResult lottoResult = new LottoResult(autoTickets, winningLotto, bonusNumber, manualTickets);

        // then
        assertThat(lottoResult.getRankCount(Rank.SIX)).isEqualTo(1);
        assertThat(lottoResult.getRankCount(Rank.FIVE_BONUS)).isEqualTo(1);
        assertThat(lottoResult.getRankCount(Rank.FIVE)).isEqualTo(0);
        assertThat(lottoResult.getRankCount(Rank.THREE)).isEqualTo(1);
        assertThat(lottoResult.getRankCount(Rank.NONE)).isEqualTo(1);
    }

    @DisplayName("총 구입 금액과 당첨금을 바탕으로 수익률을 소수점 둘째 자리 아래로 내림하여 계산한다.")
    @Test
    void calculateProfitRate() {
        // given
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;

        Lotto autoLotto = new Lotto(List.of(1, 2, 3, 10, 11, 12)); // THREE (5,000원)
        LottoTickets autoTickets = new LottoTickets(List.of(autoLotto));

        Lotto manualLotto = new Lotto(List.of(10, 11, 12, 13, 14, 15)); // NONE (0원)
        LottoTickets manualTickets = new LottoTickets(List.of(manualLotto));

        LottoResult lottoResult = new LottoResult(autoTickets, winningLotto, bonusNumber, manualTickets);

        int purchaseAmount = 8000;

        // when
        double profitRate = lottoResult.calculateProfitRate(purchaseAmount);

        // then (0.625 내림 -> 0.62)
        assertThat(profitRate).isEqualTo(0.62);
    }
}