import domain.CorrectLotto;
import domain.ManualLotto;
import domain.Lottos;
import domain.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Lottos 테스트")
public class LottosTest {

    @Test
    @DisplayName("등수별 당첨 개수 계산")
    void calculateRankCounts() {

        // 준비
        List<String> manualLottos = List.of(
                "1, 2, 3, 4, 5, 6",
                "1, 2, 3, 4, 5, 7",
                "1, 2, 3, 7, 9, 10");
        Lottos lottos = new Lottos(ManualLotto.generateManualLotto(3, manualLottos));
        CorrectLotto correctLotto = new CorrectLotto(
                new String[]{"1", "2", "3", "4", "5", "6"}, 7);

        // 실행
        Map<Rank, Integer> result = lottos.getRanksCount(correctLotto);

        // 검증
        assertEquals(1, result.get(Rank.FIRST));
        assertEquals(1, result.get(Rank.SECOND));
        assertEquals(1, result.get(Rank.FIFTH));
        assertEquals(0, result.get(Rank.THIRD));
        assertEquals(0, result.get(Rank.FOURTH));
        assertEquals(0, result.get(Rank.MISS));
    }
}
