package utils.parser;

import domain.BonusNumber;
import domain.WinningNumbers;

public class BonusNumberParser {
    private static final String ERROR_INVALID_FORMAT = "[ERROR] 보너스 번호는 숫자여야 합니다.";

    public static BonusNumber parse(String input, WinningNumbers winningNumbers) {
        try {
            int bonusValue = Integer.parseInt(input.trim());
            return new BonusNumber(bonusValue, winningNumbers);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_INVALID_FORMAT);
        }
    }
}
