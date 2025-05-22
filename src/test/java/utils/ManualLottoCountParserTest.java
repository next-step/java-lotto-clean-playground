package utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ManualLottoCountParserTest {

    @Test
    @DisplayName("정상 입력이면 수동 로또 개수를 반환한다.")
    void parse_validInput_returnsManualCount() {
        String input = "3";
        int purchaseAmount = 10000;

        int result = ManualLottoCountParser.parse(input, purchaseAmount);
        assertEquals(3, result);
    }

    @Test
    @DisplayName("음수 입력이면 예외가 발생한다.")
    void parse_negativeInput_throwsException() {
        assertThatThrownBy(() -> ManualLottoCountParser.parse("-1", 10000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 수동 로또 수는 0 이상의 정수여야 합니다.");
    }

    @Test
    @DisplayName("비숫자 입력이면 예외가 발생한다.")
    void parse_nonNumericInput_throwsException() {
        assertThatThrownBy(() -> ManualLottoCountParser.parse("abc", 10000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 수동 로또 수는 0 이상의 정수여야 합니다.");
    }

    @Test
    @DisplayName("구입 금액을 초과한 수동 로또 수는 예외가 발생한다.")
    void parse_exceedsPurchaseAmount_throwsException() {
        assertThatThrownBy(() -> ManualLottoCountParser.parse("11", 10000)) // 11개 * 1000 = 11000 > 10000
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구입 금액을 초과하여 수동 로또를 구매할 수 없습니다.");
    }
}
