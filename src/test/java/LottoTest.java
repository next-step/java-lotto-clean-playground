import domain.Lotto;
import domain.LottoNumber;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

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
    @DisplayName("로또 번호가 6개가 아닌가")
    void notSixLotto() {
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
}
