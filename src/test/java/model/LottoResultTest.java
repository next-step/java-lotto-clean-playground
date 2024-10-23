package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoResultTest {

    private LottoResult lottoResult;
    private Map<LottoRank, Integer> winningResult;

    @BeforeEach
    public void setUp() {
        winningResult = new HashMap<>();
        lottoResult = new LottoResult(winningResult);
    }

    @Test
    @DisplayName("각 등수별 로또 당첨 개수는 0으로 초기화 되어야 한다.")
    public void testInitializeWinningResult() {
        Map<LottoRank, Integer> result = lottoResult.getWinningResult();

        assertThat(result.values()).allMatch(count -> count == 0);
    }

    @Test
    @DisplayName("총 상금과 구매 금액을 기준으로 수익률을 계산한다.")
    public void testCalculateEarningRate() {
        int purchaseAmount = 10000;
        winningResult.put(LottoRank.FOURTH, 1);

        double earningRate = lottoResult.calculateEarningRate(purchaseAmount);

        assertThat(earningRate).isEqualTo(5.0);
    }

    @Test
    @DisplayName("로또 당첨 번호 확인 작업을 통해 당첨 결과를 갱신한다.")
    public void testOperateLottoCheckMachine() {
        LottoTickets lottoTickets = new LottoTickets();
        LottoTicket winningTicket = LottoTicket.generateAutomaticNumbers();
        LottoNumber bonusNumber = new LottoNumber(7);

        lottoResult.operateLottoCheckMachine(lottoTickets, winningTicket, bonusNumber);

        int totalTicketsChecked = winningResult.values().stream().mapToInt(Integer::intValue).sum();
        assertThat(totalTicketsChecked).isEqualTo(lottoTickets.ticketsCount());
    }
}
