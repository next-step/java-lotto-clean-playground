import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoResultAnalyzerTest {

    private LottoResultAnalyzer analyzer;
    private WinningNumbers winningNumbers;

    @BeforeEach
    void setUp() {
        analyzer = new LottoResultAnalyzer();

        winningNumbers = new WinningNumbers("1, 2, 3, 4, 5, 6", "7");
    }

    @Test
    void 로또_등수_계산_테스트() {
        Lotto lotto1 = new Lotto(List.of(
            new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5),
            new LottoNumber(6)
        ));

        Lotto lotto2 = new Lotto(List.of(
            new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5),
            new LottoNumber(7)
        ));

        Lotto lotto3 = new Lotto(List.of(
            new LottoNumber(10),
            new LottoNumber(11),
            new LottoNumber(12),
            new LottoNumber(13),
            new LottoNumber(14),
            new LottoNumber(15)
        ));

        LottoTicket ticket = new LottoTicket(List.of(lotto1, lotto2, lotto3));

        analyzer.analyze(ticket, winningNumbers);

        Map<Rank, Integer> result = analyzer.getResult();

        assertThat(result.get(Rank.FIRST)).isEqualTo(1);
        assertThat(result.get(Rank.SECOND)).isEqualTo(1);
        assertThat(result.get(Rank.THIRD)).isEqualTo(0);
        assertThat(result.get(Rank.FOURTH)).isEqualTo(0);
        assertThat(result.get(Rank.FIFTH)).isEqualTo(0);
    }

    @Test
    void 수익률_계산_테스트() {
        analyzer.getResult().put(Rank.FIRST, 1);
        analyzer.getResult().put(Rank.SECOND, 1);
        analyzer.getResult().put(Rank.FIFTH, 1);

        PurchaseAmount purchaseAmount = new PurchaseAmount("3000");

        double profitRate = analyzer.calculateProfitRate(purchaseAmount);

        long totalPrize = Rank.FIRST.getPrize() + Rank.SECOND.getPrize() + Rank.FIFTH.getPrize();

        assertThat(profitRate).isEqualTo((double) totalPrize / 3000);
    }
}
