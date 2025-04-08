package view;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class InputValidatorTest {

    @Test
    void 구분자가_쉼표가_아니면_예외가_발생해야_한다() {
        String string = "123,124,234,23&1,2";
        Assertions.assertThatThrownBy(() -> InputValidator.validateLottoNumbersInputPattern(string))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호 입력은 쉼표로 구분되어야 합니다!");
    }
}
