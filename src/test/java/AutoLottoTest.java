import domain.AutoLotto;
import domain.Lotto;
import domain.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("AutoLotto 테스트")
public class AutoLottoTest {

    @Test
    @DisplayName("요청한 개수만큼 자동 로또 생성")
    void generateRequestCount() {

        // 준비 & 실행
        List<Lotto> result = AutoLotto.generateAutoLotto(5);

        // 검증
        assertEquals(5, result.size());
    }

    @Test
    @DisplayName("자동 로또는 중복 없는 6개 번호로 구성")
    void autoLottoContain6UniqueNumbers() {

        // 준비
        List<Lotto> lottos = AutoLotto.generateAutoLotto(3);

        // 실행 & 검증
        for (int i = 0; i < lottos.size(); i++) {
            List<LottoNumber> numbers = lottos.get(i).getLotto();
            assertEquals(6, numbers.size());
            assertEquals(6, new HashSet<>(numbers).size());
        }
    }
}
