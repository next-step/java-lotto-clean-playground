package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LottoTest {

    private Lotto autoLotto;
    private Lotto handLotto;

    @BeforeEach
    void setUp() {
        autoLotto = new Lotto();
        handLotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
    }

    @Test
    void 자동_로또_생성_테스트() {
        List<Integer> generatedNumbers = autoLotto.getLottoNumbers();

        // 로또 개수는 6개이다
        assertEquals(6, generatedNumbers.size());

        // 로또 번호의 범위는 1~45 사이이다
        for (Integer number : generatedNumbers) {
            assertTrue(number >= 1 && number <= 45, "로또 번호는 1에서 45 사이어야 합니다.");
        }

        // 로또 번호는 중복이 없어야 한다
        long uniqueCount = generatedNumbers.stream().distinct().count();
        assertEquals(6, uniqueCount, "로또 번호는 중복이 없어야 합니다.");
    }

    @Test
    void 수동_로또_생성_테스트() {
        List<Integer> generatedNumbers = handLotto.getLottoNumbers();
        // 수동 로또 번호에 대한 테스트 내용 추가 필요
        assertEquals(6, generatedNumbers.size(), "수동 로또 번호는 6개여야 합니다.");
        assertTrue(generatedNumbers.containsAll(Arrays.asList(1, 2, 3, 4, 5, 6)), "수동 로또 번호가 예상과 일치하지 않습니다.");
    }

    @Test
    void 보너스_번호가_로또_번호와_일치하는지_테스트() {
        boolean containBonus1 = handLotto.contains(7);
        assertFalse(containBonus1, "보너스 번호 7은 로또 번호에 포함되지 않습니다.");

        boolean containBonus2 = handLotto.contains(6);
        assertTrue(containBonus2, "보너스 번호 6은 로또 번호에 포함됩니다.");
    }
}
