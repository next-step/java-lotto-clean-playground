package utils.parser;

import domain.BonusNumber;
import domain.WinningLotto;

public class BonusNumberParser {
    private static final String ERROR_INVALID_FORMAT = "[ERROR] 보너스 번호는 숫자여야 합니다.";

    public static BonusNumber parse(String input, WinningLotto winningLotto) {
        try {
            int bonusValue = Integer.parseInt(input.trim());
            return new BonusNumber(bonusValue, winningLotto);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_INVALID_FORMAT);
        }
    }
}
