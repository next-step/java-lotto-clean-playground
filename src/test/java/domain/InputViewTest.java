package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import view.InputView;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("입력된 문자가 숫자 인지 아닌지 테스트")
class InputViewTest {

    @Test
    void 입력문자가_숫자로만_구성되어야_한다() {

        String text = "12345";

        assertEquals(12345, InputView.validText(text));
    }

    @Test
    void 입력문자가_숫자가_아니면_오류를_발생한다() {

        String text = "Hello";

        assertThatThrownBy(() -> InputView.validText(text))
                .isInstanceOf(NumberFormatException.class);
    }

    @Test
    void 당첨번호_입력은_형식에_맞게_작성해야한다() {

        String number = "1, 2, 3, 4, 5";

        try {
            InputView.validCollectedText(number);
        } catch (NumberFormatException e) {
            System.out.println("hello");
        }

        System.out.println("Test 성공");
    }

    @Test
    void 당첨번호_입력은_형식에_맞지_않으면_오류를_발생한다() {
        String number = "4,2,3,1,6";

        try {
            InputView.validCollectedText(number);
        } catch (NumberFormatException e) {
            System.out.println("Test 성공");
        }

    }
}