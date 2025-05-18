package utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;


class LottoPurchaseAmountParserTest {

    @ParameterizedTest(name = "입력값 \"{0}\"은 정수로 파싱된다")
    @ValueSource(strings = {"0", "1000", "99999"})
    @DisplayName("정상적인 숫자 문자열은 정수로 변환된다")
    void parseValidNumbers(String input) {
        int parsed = LottoPurchaseAmountParser.parse(input);
        assertThat(parsed).isEqualTo(Integer.parseInt(input));
    }

    @Test
    @DisplayName("null 입력 시 예외가 발생한다")
    void throwIfNullInput() {
        assertThatThrownBy(() -> LottoPurchaseAmountParser.parse(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구입 금액은 null일 수 없습니다.");
    }

    @ParameterizedTest(name = "입력값 \"{0}\"은 숫자가 아니므로 예외 발생")
    @ValueSource(strings = {"abc", "1000원", "", "   "})
    @DisplayName("숫자가 아닌 문자열 입력 시 예외가 발생한다")
    void throwIfNotNumber(String input) {
        assertThatThrownBy(() -> LottoPurchaseAmountParser.parse(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구입 금액은 숫자로만 입력해야 합니다.");
    }
}
