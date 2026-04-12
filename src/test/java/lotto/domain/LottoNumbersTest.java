package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
public class LottoNumbersTest {
    @Test
    void 로또를_생성할_수_있다() {
        assertThatCode(() -> createNumbers(1, 3, 6, 7, 13, 20))
                .doesNotThrowAnyException();
    }

    @Test
    void 로또_숫자는_6개여야_한다() {
        assertThatThrownBy(() -> createNumbers(1, 3, 6, 7, 13))
                .isInstanceOf(LottoException.WrongNumberCount.class);
    }

    @Test
    void 로또_숫자는_중복되면_안된다() {
        assertThatThrownBy(() -> createNumbers(1, 1, 6, 7, 13, 20))
                .isInstanceOf(LottoException.DuplicateNumber.class);
    }

    private LottoNumbers createNumbers(int... numbers) {
        List<LottoNumber> numbersList = Arrays.stream(numbers)
                .mapToObj(LottoNumber::new)
                .toList();

        return new LottoNumbers(numbersList);
    }
}
