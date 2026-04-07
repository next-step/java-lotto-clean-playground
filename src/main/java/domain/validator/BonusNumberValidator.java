package domain.validator;

import domain.Lotto;

public class BonusNumberValidator {

    public static void validate(Lotto winningLotto, int bonusNumber) {
        LottoNumberValidator.validateRange(bonusNumber);
        if (winningLotto.getNumbers().contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }
}
