package exceptionTest;

import exception.DirectCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("수동으루 고매할 로또 수 Test")
class DirectCountTest {

    @ParameterizedTest
    @ValueSource(strings = {
            " ",
            "hello",
            "1fe2"
    })
    void 숫자가_아닌수가_입력되면_오류를_발생한다(String directText) {

        assertThatThrownBy(() -> DirectCount.from(directText, 14000))
                .isInstanceOf(Exception.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "-1",
            "15",
            "100"
    })
    void 입력_수가_음수이거나_구입금액으로_부족할_경우_오류가_발생한다(String directText) {

        assertThatThrownBy(() -> DirectCount.from(directText, 14000))
                .isInstanceOf(Exception.class);
    }

}
