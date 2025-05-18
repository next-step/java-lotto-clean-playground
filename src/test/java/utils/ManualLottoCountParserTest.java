package utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ManualLottoCountParserTest {

    @Test
    @DisplayName("유효한 수동 로또 수를 입력하면 정확한 값을 반환한다.")
    void testParse_validInput() {
        String input = "3";
        int purchaseAmount = 10000;

        int manualCount = ManualLottoCountParser.parse(input, purchaseAmount);
        assertEquals(3, manualCount);
    }

    @Test
    @DisplayName("수동 로또 수가 음수일 경우 IllegalArgumentException이 발생한다.")
    void testParse_negativeInput() {
        String input = "-1";
        int purchaseAmount = 10000;

        assertThatThrownBy(() -> ManualLottoCountParser.parse(input, purchaseAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 수동 로또 수는 0 이상의 정수여야 합니다.");
    }

    @Test
    @DisplayName("구입 금액에 맞는 최대 수동 로또 수를 초과하지 않으면 정상적으로 동작한다.")
    void testParse_withinPurchaseAmount() {
        String input = "5";
        int purchaseAmount = 50000;

        int manualCount = ManualLottoCountParser.parse(input, purchaseAmount);
        assertEquals(5, manualCount);
    }

    @Test
    @DisplayName("수동 로또 수가 숫자가 아닌 경우 IllegalArgumentException이 발생한다.")
    void testParse_nonNumericInput() {
        String input = "abc";
        int purchaseAmount = 10000;

        assertThatThrownBy(() -> ManualLottoCountParser.parse(input, purchaseAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 수동 로또 수는 0 이상의 정수여야 합니다.");
    }
}
