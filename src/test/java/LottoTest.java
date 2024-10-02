import domain.LottoGame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;
import java.util.List;

public class LottoTest {

    @Test
    @DisplayName("로또번호_6개_일치여부를_확인한다")
    public void 로또번호_6개_일치여부를_확인한다() {
        List<String> lotto = Arrays.asList("1", "2", "3", "4", "5", "6");
        List<String> lastWeekLotto = Arrays.asList("6", "5", "4", "3", "2", "1");
        int result = LottoGame.matchNum(lotto, lastWeekLotto);
        assertEquals(6, result);
    }

    @Test
    @DisplayName("로또번호_3개_일치여부를_확인한다")
    public void 로또번호_3개_일치여부를_확인한다() {
        List<String> lotto = Arrays.asList("1", "2", "3", "4", "5", "6");
        List<String> lastWeekLotto = Arrays.asList("10", "9", "8", "3", "2", "1");
        int result = LottoGame.matchNum(lotto, lastWeekLotto);
        assertEquals(3, result);
    }

    @Test
    @DisplayName("로또번호_0개_일치여부를_확인한다")
    public void 로또번호_0개_일치여부를_확인한다() {
        List<String> lotto = Arrays.asList("1", "2", "3", "4", "5", "6");
        List<String> lastWeekLotto = Arrays.asList("7", "8", "9", "10", "11", "12");
        int result = LottoGame.matchNum(lotto, lastWeekLotto);
        assertEquals(0, result);
    }

    @Test
    @DisplayName("보너스번호_일치여부를_확인한다")
    public void 보너스번호_일치여부를_확인한다() {
        List<String> lotto = Arrays.asList("1", "2", "3", "4", "5", "7");
        String bonusBall = "7";
        int result = LottoGame.matchBonus(lotto, bonusBall);
        assertEquals(1, result); // 보너스볼이 일치함
    }

    @Test
    @DisplayName("보너스번호_불일치여부를_확인한다")
    public void 보너스번호_불일치여부를_확인한다() {
        List<String> lotto = Arrays.asList("1", "2", "3", "4", "5", "7");
        String bonusBall = "8";
        int result = LottoGame.matchBonus(lotto, bonusBall);
        assertEquals(0, result); // 보너스볼이 일치하지 않음
    }
}