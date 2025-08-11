import exception.CustomException;
import exception.ErrorMessage;
import model.LottoNumber;
import org.junit.jupiter.api.Test;
import util.Parser;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ParserTest {

    @Test
    void 정상적인_숫자_문자열_파싱_테스트() {
        int result = Parser.parseInt("123");
        assertThat(result).isEqualTo(123);
    }

    @Test
    void 숫자가_아닌_문자열을_파싱하면_예외가_발생한다() {
        assertThatThrownBy(() -> Parser.parseInt("abc"))
            .isInstanceOf(CustomException.class)
            .hasMessageContaining(ErrorMessage.INVALID_NUMBER_FORMAT.getMessage());
    }

    @Test
    void 문자열로_받은_로또_번호_파싱_테스트() {
        List<LottoNumber> numbers = Parser.parseManualLottoNumbers("1, 2, 3,4,5,6");

        assertThat(numbers).hasSize(6);
        assertThat(numbers).extracting("number")
            .containsExactly(1, 2, 3, 4, 5, 6);
    }

    @Test
    void 문자열로_받은_로또_번호는_정수가_아니면_예외를_발생한다() {
        assertThatThrownBy(() -> Parser.parseManualLottoNumbers("1,2,asd,4"))
            .isInstanceOf(CustomException.class)
            .hasMessageContaining("정수를 입력해야 합니다.");
    }
}
