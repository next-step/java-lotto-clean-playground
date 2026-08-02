import domain.CorrectLotto;
import domain.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("CorrectLotto 테스트")
public class CorrectLottoTest {

    @Test
    @DisplayName("당첨 번호 6개와 보너스 볼을 저장")
    void createCorrectLotto() {

        // 준비
        String[] values = {"1", "2", "3", "4", "5", "6"};
        LottoNumber bonusBall = new LottoNumber(7);

        // 실행
        CorrectLotto correctLotto = new CorrectLotto(values, bonusBall);

        // 검증
        assertEquals(6, correctLotto.getCorrectLotto().size());
        assertEquals(new LottoNumber(7), correctLotto.getBonusBall());
    }

    @Test
    @DisplayName("당첨 번호가 6개가 아니면 예외")
    void exceptIfNumberCountIsNot6() {

        // 준비
        String[] values = {"1", "2", "3", "4", "5"};

        // 실행 & 검증
        assertThrows(IllegalArgumentException.class,
                () -> new CorrectLotto(values, new LottoNumber(7)));
    }

    @Test
    @DisplayName("보너스 볼이 당첨 번호와 중복되면 예외")
    void exceptIfBonusBallAndLottoNumbersAreSame() {

        // 준비
        String[] values = {"1", "2", "3", "4", "5", "6"};

        // 실행 & 검증
        assertThrows(IllegalArgumentException.class,
                () -> new CorrectLotto(values, new LottoNumber(6)));
    }
}
