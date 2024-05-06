package exceptionTest;

import exception.BuyDirectNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("수동으로 구매할 번호들 Test")
class BuyDirectNumberTest {
    @Test
    void 수동으로_구매할_번호가_올바르게_주어지면_문제없이_생성한다() {

        String text = "1, 2, 3, 4, 5, 6";

        assertThatCode(() -> {
            BuyDirectNumber.from(text);
        }).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1,2,3,4,5,6",
            "1,  2,  3,  4,  5,  6",
            "      ",
    })
    void 잘못된_형식으로_수동번호_입력시_오류가_발생한다(String text) {

        assertThatThrownBy(() -> BuyDirectNumber.from(text))
                .isInstanceOf(Exception.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1, 2, 3, 4, 5, 6 ,7",
            "1, 12, 32",
            "1, 2, 3, 4, 5"
    })
    void 수동번호가_6개가_입력되지_않으면_오류를_발생한다(String text) {

        assertThatThrownBy(() -> BuyDirectNumber.from(text))
                .isInstanceOf(Exception.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1, 2, 3, 4, 5, 46",
            "-1, -2, -3, 4, 5, 6",
            "0, 1, 2, 3, 4, 5, 6"
    })
    void 수동번호가_로또범위에_속하지_않으면_오류를_발생한다(String text) {

        assertThatThrownBy(() -> BuyDirectNumber.from(text))
                .isInstanceOf(Exception.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1, 1, 1, 1, 1, 1",
            "1, 2, 3, 4, 5, 1"
    })
    void 수동번호가_서로_같은숫자가_있으면_오류를_발생한다(String text) {

        assertThatThrownBy(() -> BuyDirectNumber.from(text))
                .isInstanceOf(Exception.class);
    }
}
