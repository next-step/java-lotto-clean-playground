import exception.CustomException;
import exception.ErrorMessage;
import model.Lotto;
import model.LottoNumber;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class LottoTest {

    @Test
    void 정상적인_6개의_LottoNumber로_객체를_생성한다() {
        List<LottoNumber> numbers = List.of(
            new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
            new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
        );

        assertThatCode(() -> new Lotto(numbers))
            .doesNotThrowAnyException();
    }

    @Test
    void LottoNumber의_개수가_6개가_아니면_예외를_발생한다() {
        List<LottoNumber> numbers = List.of(
            new LottoNumber(1), new LottoNumber(2)
        );

        assertThatThrownBy(() -> new Lotto(numbers))
            .isInstanceOf(CustomException.class)
            .hasMessage(ErrorMessage.INVALID_NUMBERS_SIZE.getMessage());
    }

    @Test
    void toString은_내부_번호_리스트_문자열을_반환한다() {
        List<LottoNumber> numbers = List.of(
            new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
            new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
        );

        Lotto lotto = new Lotto(numbers);

        assertThat(lotto.toString()).isEqualTo("[1, 2, 3, 4, 5, 6]");
    }
}
