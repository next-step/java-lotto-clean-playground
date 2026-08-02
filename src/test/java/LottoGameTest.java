import domain.LottoGame;
import domain.LottoNumber;
import domain.PurchasePrice;
import domain.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("LottoGame 테스트")
public class LottoGameTest {

    @Test
    @DisplayName("당첨 등수 & 수익률 계산")
    void calculateRankAndProfit() {

        // 준비
        PurchasePrice purchasePrice = new PurchasePrice(1000, 1);
        List<String> manualLotto = List.of("1, 2, 3, 8, 9, 10");
        LottoGame game = new LottoGame(purchasePrice, manualLotto);
        game.createCorrectLotto(new String[]{"1", "2", "3", "4", "5", "6"}, new LottoNumber(7));

        // 실행
        Map<Rank, Integer> ranksCount = game.getRanksCount();
        float profit = game.calculateProfit();

        // 검증
        assertEquals(1, ranksCount.get(Rank.FIFTH));
        assertEquals(5, profit);
    }
}
