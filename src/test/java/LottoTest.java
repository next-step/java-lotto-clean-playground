import domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Lotto 테스트")
public class LottoTest {

    CorrectLotto correctLotto = createCorrectLotto();

    @Test
    @DisplayName("당첨 번호 6개가 일치하면 1등")
    void firstRank() {

        // 준비
        List<Integer> lottoNumbers = List.of(1, 2, 3, 4, 5, 6);
        Lotto lotto = createLotto(lottoNumbers);

        // 실행
        Rank result = lotto.findRank(correctLotto);

        // 검증
        assertEquals(Rank.FIRST, result);
    }

    @Test
    @DisplayName("당첨 번호 5개와 보너스 볼이 일치하면 2등")
    void secondRank() {

        // 준비
        List<Integer> lottoNumbers = List.of(1, 2, 3, 4, 5, 7);
        Lotto lotto = createLotto(lottoNumbers);

        // 실행
        Rank result = lotto.findRank(correctLotto);

        // 검증
        assertEquals(Rank.SECOND, result);
    }

    @Test
    @DisplayName("당첨 번호 5개가 일치하면 3등")
    void thirdRank() {

        // 준비
        List<Integer> lottoNumbers = List.of(1, 2, 3, 4, 5, 8);
        Lotto lotto = createLotto(lottoNumbers);

        // 실행
        Rank result = lotto.findRank(correctLotto);

        // 검증
        assertEquals(Rank.THIRD, result);
    }

    @Test
    @DisplayName("당첨 번호 4개가 일치하면 4등")
    void fourthRank() {

        // 준비
        List<Integer> lottoNumbers = List.of(1, 2, 3, 4, 8, 9);
        Lotto lotto = createLotto(lottoNumbers);

        // 실행
        Rank result = lotto.findRank(correctLotto);

        // 검증
        assertEquals(Rank.FOURTH, result);
    }

    @Test
    @DisplayName("당첨 번호 3개가 일치하면 5등")
    void fifthRank() {

        // 준비
        List<Integer> lottoNumbers = List.of(1, 2, 45, 22, 8, 6);
        Lotto lotto = createLotto(lottoNumbers);

        // 실행
        Rank result = lotto.findRank(correctLotto);

        // 검증
        assertEquals(Rank.FIFTH, result);
    }

    @Test
    @DisplayName("당첨 번호가 2개 이하로 일치하면 낙첨")
    void missRank() {

        // 준비
        List<Integer> lottoNumbers = List.of(1, 2, 45, 22, 8, 7);
        Lotto lotto = createLotto(lottoNumbers);

        // 실행
        Rank result = lotto.findRank(correctLotto);

        // 검증
        assertEquals(Rank.MISS, result);
    }

    @Test
    @DisplayName("당첨 번호가 6개가 아니면 예외")
    void exceptIfNumberCountIsNot6() {

        // 준비
        String[] values = {"1", "2", "3", "4", "5"};

        // 실행 & 검증
        assertThrows(IllegalArgumentException.class,
                () -> new CorrectLotto(values, 7));
    }

    @Test
    @DisplayName("당첨 번호가 숫자가 아니면 예외")
    void exceptIfNumberCountIsNotNumber() {

        // 준비
        String[] values = {"1", "2", "3", "4", "5", "a"};

        // 실행 & 검증
        assertThrows(IllegalArgumentException.class,
                () -> new CorrectLotto(values, 7));
    }


    private CorrectLotto createCorrectLotto() {
        String[] correctLotto = {"1", "2", "3", "4", "5", "6"};

        return new CorrectLotto(correctLotto, 7);
    }

    private Lotto createLotto(List<Integer> lottoNumbers) {
        List<LottoNumber> numbers = new ArrayList<>();

        for (int i = 0; i < lottoNumbers.size(); i++) {
            numbers.add(new LottoNumber(lottoNumbers.get(i)));
        }

        return new Lotto(numbers);
    }
}
