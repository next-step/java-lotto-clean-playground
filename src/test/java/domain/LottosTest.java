package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("NonAsciiCharacters") // 한글과 같은 아스키 코드가 아닌 문자에 밑줄이 쳐지지 않음
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class) // UnderScore(_)가 공백으로 표시
class LottosTest {

    private Lottos lottos;

    @BeforeEach
    void setUp() {
        lottos = Lottos.fromAutoLottos(3); // 정적 팩토리 메서드 사용
    }

    @Test
    void 구입한_개수만큼_로또_자동_생성되는지_테스트() {
        List<Lotto> lottoList = lottos.getLottos();

        // 구입한 개수만큼 생성되는지
        assertEquals(3, lottoList.size(), "로또 3개가 생성되었습니다.");
    }

    @Test
    void 생성된_로또_번호에대한_테스트() {
        List<Lotto> lottoList = lottos.getLottos();

        // 생성된 로또가 6개의 숫자를 가지는지
        for (Lotto lotto : lottoList) {
            assertEquals(6, lotto.getLottoNumbers().size(), "각 로또는 6개의 번호를 가져야 합니다.");
        }

        // 생성된 로또의 번호가 1~45 사이인지
        for (Lotto lotto : lottoList) {
            List<Integer> lottoNumbers = lotto.getLottoNumbers();
            for (Integer number : lottoNumbers) {
                assertTrue(number >= 1 && number <= 45, "로또 번호는 1이상 45이하입니다.");
            }
        }

        // 생성된 로또의 번호에 중복이 없는지
        for (Lotto lotto : lottoList) {
            long uniqueCount = lotto.getLottoNumbers().stream().distinct().count();
            assertEquals(6, uniqueCount, "로또 번호는 중복이 없습니다.");
        }
    }
}
