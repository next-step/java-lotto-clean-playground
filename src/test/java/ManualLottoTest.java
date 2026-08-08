import domain.Lotto;
import domain.LottoNumber;
import domain.ManualLotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("ManualLotto 테스트")
public class ManualLottoTest {

    @Test
    @DisplayName("입력 문자열을 수동 로또로 변환")
    void convertStringToManualLotto() {

        // 준비
        List<String> values = List.of("1, 2, 3, 4, 5, 6");

        // 실행
        List<Lotto> result = ManualLotto.generateManualLotto(1, values);

        // 검증
        assertEquals(
                List.of(
                        new LottoNumber(1),
                        new LottoNumber(2),
                        new LottoNumber(3),
                        new LottoNumber(4),
                        new LottoNumber(5),
                        new LottoNumber(6)
                ),
                result.get(0).getLotto());
    }

    @Test
    @DisplayName("수동 로또 번호가 중복되면 예외")
    void exceptIfOverlapManualNumber() {

        // 준비
        List<String> values = List.of("1, 2, 3, 4, 5, 5");

        // 실행 & 검증
        assertThrows(IllegalArgumentException.class,
                () -> ManualLotto.generateManualLotto(1, values));
    }

    @Test
    @DisplayName("수동 로또에 문자가 포함되면 예외")
    void exceptIfManualContainChar() {

        // 준비
        List<String> values = List.of("1, 2, 3, 4, 5, a");

        // 실행 & 검증
        assertThrows(IllegalArgumentException.class,
                () -> ManualLotto.generateManualLotto(1, values));
    }
}
