package domain;

import org.junit.jupiter.api.DisplayName;

import Exception.CollectNumber;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("당첨 번호 입력 테스트")
class CollectNumberTest {

    @Test
    void 당첨번호가_올바르게_주어지면_문제없이_생성한다() {

        String text = "1, 2, 3, 4, 5, 6";

        assertThatCode(() -> {
            CollectNumber.from(text);
        }).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1,2,3,4,5,6",
            "1,  2,  3,  4,  5,  6",
            "      ",
    })
    void 잘못된_형식으로_당첨번호_입력시_오류가_발생한다(String text) {

        assertThatThrownBy(() -> CollectNumber.from(text))
                .isInstanceOf(Exception.class);
    }


    @ParameterizedTest
    @ValueSource(strings = {
            "1, 2, 3, 4, 5, 6 ,7",
            "1, 12, 32",
            "1, 2, 3, 4, 5"
    })
    void 당첨번호가_6개가_입력되지_않으면_오류를_발생한다(String text) {

        assertThatThrownBy(() -> CollectNumber.from(text))
                .isInstanceOf(Exception.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1, 2, 3, 4, 5, 46",
            "-1, -2, -3, 4, 5, 6",
            "0, 1, 2, 3, 4, 5, 6"
    })
    void 당첨번호가_로또범위에_속하지_않으면_오류를_발생한다(String text) {

        assertThatThrownBy(() -> CollectNumber.from(text))
                .isInstanceOf(Exception.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1, 1, 1, 1, 1, 1",
            "1, 2, 3, 4, 5, 1"
    })
    void 당첨번호가_서로_같은숫자가_있으면_오류를_발생한다(String text) {

        assertThatThrownBy(() -> CollectNumber.from(text))
                .isInstanceOf(Exception.class);
    }
}
