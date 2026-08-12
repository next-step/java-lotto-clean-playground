import domain.Lotto;
import domain.LottoNumber;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LottoTest {

    @Test
    @DisplayName("로또 번호가 6개인가")
    void createLotto() {
        List<LottoNumber> numbers = List.of(
                LottoNumber.from(1),
                LottoNumber.from(2),
                LottoNumber.from(3),
                LottoNumber.from(4),
                LottoNumber.from(5),
                LottoNumber.from(6)
        );

        Lotto.from(numbers);
    }

    @Test
    @DisplayName("로또 번호가 5개면 예외")
    void fiveNotSixLotto() {
        List<LottoNumber> numbers = List.of(
                LottoNumber.from(1),
                LottoNumber.from(2),
                LottoNumber.from(3),
                LottoNumber.from(4),
                LottoNumber.from(5)
        );

        assertThrows(IllegalArgumentException.class, () -> Lotto.from(numbers));
    }

    @Test
    @DisplayName("로또 번호가 7개면 예외")
    void sevenNotSixLotto() {
        List<LottoNumber> numbers = List.of(
                LottoNumber.from(1),
                LottoNumber.from(2),
                LottoNumber.from(3),
                LottoNumber.from(4),
                LottoNumber.from(5),
                LottoNumber.from(6),
                LottoNumber.from(7)
        );

        assertThrows(IllegalArgumentException.class, () -> Lotto.from(numbers));
    }

    @Test
    @DisplayName("중복된 번호가 있나")
    void duplicateLotto() {
        List<LottoNumber> numbers = List.of(
                LottoNumber.from(1),
                LottoNumber.from(2),
                LottoNumber.from(3),
                LottoNumber.from(4),
                LottoNumber.from(5),
                LottoNumber.from(5)
        );

        assertThrows(IllegalArgumentException.class, () -> Lotto.from(numbers));
    }

    @Test
    @DisplayName("방어적 기법으로 외부에서 내용을 바꿔도 유지된다.")
    void maintainLotto() {
        List<LottoNumber> numbers = new ArrayList<>(List.of(
                LottoNumber.from(1),
                LottoNumber.from(2),
                LottoNumber.from(3),
                LottoNumber.from(4),
                LottoNumber.from(5),
                LottoNumber.from(6)
        ));

        Lotto lotto = Lotto.from(numbers);
        numbers.clear();

        assertTrue(lotto.contains(LottoNumber.from(1)));

    }
}
