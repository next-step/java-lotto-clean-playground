package utils.parser;

import domain.LottoNumber;
import domain.WinningLotto;

public class BonusNumberParser {
    private static final String ERROR_INVALID_FORMAT = "[ERROR] 보너스 번호는 숫자여야 합니다.";

    public static LottoNumber parse(String input, WinningLotto winningLotto) {
        try {
            int bonusValue = Integer.parseInt(input.trim());
            LottoNumber bonusNumber = new LottoNumber(bonusValue);
            LottoNumber.validateBonusNumber(winningLotto.getNumbers(), bonusNumber);
            return bonusNumber;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_INVALID_FORMAT);
        }
    }
}
