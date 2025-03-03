package service;

import model.Lotto;
import model.LottoRank;
import model.LottoResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class LottoServiceTest {

    private LottoService lottoService;

    @BeforeEach
    void setUp() {
        lottoService = new LottoService();
    }

    @Test
    @DisplayName("주어진 개수만큼 로또 번호를 생성하는 지 검증한다.")
    void should_GenerateLottos() {
        List<Lotto> lottos = lottoService.generateLottos(5000, List.of("1,2,3,4,5,6"));
        assertThat(lottos).hasSize(5);
    }

    @Test
    @DisplayName("로또 결과에 따라 맞는 랭크를 가져오는 지 검증한다.")
    void should_CalculateRank() {
        List<Lotto> lottos = List.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)));
        LottoResult result = new LottoResult(List.of(1, 2, 3, 4, 5, 6), 7);
        List<LottoRank> ranks = lottoService.calculateRank(result, lottos);

        assertThat(ranks).containsExactly(LottoRank.SIX_MATCHES);
    }

    @Test
    @DisplayName("로또 랭크에 대한 수익률을 계산하는 지 검증한다.")
    void should_CalculateEarningsRate() {
        List<LottoRank> lottoRanks = List.of(
                LottoRank.SIX_MATCHES,
                LottoRank.FIVE_MATCHES,
                LottoRank.NO_WINNER
        );

        String result = lottoService.calculateEarningsRate(lottoRanks);

        double expectedRate = (double) (2_000_000_000 + 1_500_000) / (lottoRanks.size() * 1000);
        String formattedRate = String.format("%.2f", expectedRate);

        assertThat(result)
                .contains("총 수익률은")
                .contains(formattedRate)
                .contains("이익");
    }

    @Test
    @DisplayName("로또 랭크가 모두 없으면 수익률은 손해로 계산되는 지 검증한다.")
    void should_CalculateEarningsRate_Loss() {
        List<LottoRank> lottoRanks = List.of(
                LottoRank.NO_WINNER,
                LottoRank.NO_WINNER,
                LottoRank.NO_WINNER
        );

        String result = lottoService.calculateEarningsRate(lottoRanks);

        assertThat(result).contains("총 수익률은")
                .contains("손해");
    }
}
