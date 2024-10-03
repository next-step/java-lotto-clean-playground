import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.NumberFormatter;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NumberFormatterTest {

    @DisplayName("입력_받은_숫자를_리스트로_변환하여_반환한다")
    @Test
    void 입력_받은_숫자를_리스트로_변환하여_반환한다() {
        String inputNumbers = "1,2,3,4,5,6";

        List<Integer> result = NumberFormatter.formNumbers(inputNumbers);

        assertThat(result).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @DisplayName("빈_문자열_입력_시_예외가_발생한다")
    @Test
    void 빈_문자열_입력_시_예외가_발생한다() {
        String emptyInput = "";

        assertThatThrownBy(() -> NumberFormatter.formNumbers(emptyInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력된 숫자가 없습니다. 숫자를 입력해 주세요.");
    }

    @DisplayName("숫자가_아닌_값이_있으면_예외가_발생한다")
    @Test
    void 숫자가_아닌_값이_있으면_예외가_발생한다() {
        String invalidInput = "1,2,a,4,5,6";

        assertThatThrownBy(() -> NumberFormatter.formNumbers(invalidInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력 값에 숫자가 아닌 값이 포함되어 있습니다.");
    }

}
