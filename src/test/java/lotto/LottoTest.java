package lotto;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
public class LottoTest {
    @Test
    void 로또를_생성할_수_있다() {
        List<LottoNumber> numbers = createNumbers(1, 3, 6, 7, 13, 20);

        assertThatCode(() -> new Lotto(numbers))
                .doesNotThrowAnyException();
    }

    @Test
    void 로또_숫자는_6개여야_한다() {
        List<LottoNumber> numbers = createNumbers(1, 3, 6, 7, 13);

        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(Lotto.LottoException.WrongNumberCount.class);
    }

    @Test
    void 로또_숫자는_중복되면_안된다() {
        List<LottoNumber> numbers = createNumbers(1, 1, 6, 7, 13, 20);

        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(Lotto.LottoException.DuplicateNumber.class);
    }

    private List<LottoNumber> createNumbers(int... numbers) {
        return Arrays.stream(numbers)
                .mapToObj(LottoNumber::new)
                .toList();
    }
}
