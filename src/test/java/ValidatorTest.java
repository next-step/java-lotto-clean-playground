import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.Validator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {
    @DisplayName("구매금액 정상 입력")
    @Test
    void validAmountDoesNotThrow() {
        assertDoesNotThrow(() -> Validator.validateLottoPurchaseAmount(15000));
    }

    @DisplayName("구매금액 예외 입력")
    @Test
    void amountNotDividedByThousand() {
        assertThrows(IllegalArgumentException.class, () -> Validator.validateLottoPurchaseAmount(3483));
    }

    @DisplayName("수동 로또 번호 예외 입력")
    @Test
    void manualLottoNumberOutOfRange() {
        List<Integer> testNumber = List.of(45, 98, 21, 34, 100, 22);
        assertThrows(IllegalArgumentException.class, () -> Validator.validateManualLottoNumberInRange(testNumber));
    }

    @DisplayName("지난주 당첨 번호 개수 예외 입력")
    @Test
    void lastWeekNumbersOutOfRange() {
        List<Integer> testNumber = List.of(45, 12, 21, 34, 23, 22, 43);
        assertThrows(IllegalArgumentException.class, () -> Validator.validateLastWeekNumbers(testNumber));
    }

    @DisplayName("보너스 번호 예외 입력")
    @Test
    void bonusNumberOutOfRange() {
        int num = 0;
        assertThrows(IllegalArgumentException.class, () -> Validator.validateNumberRange(num));
    }
}
