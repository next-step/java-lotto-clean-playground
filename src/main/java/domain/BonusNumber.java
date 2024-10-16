package domain;


import utils.LottoNumberGenerator;

import static utils.LottoNumberGenerator.MAX_NUMBER;
import static utils.LottoNumberGenerator.MIN_NUMBER;

public class BonusNumber {
    private final int bonusNumber;

    private BonusNumber(int bonusNumber) {
        checkRange(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    public static BonusNumber inputBonusNumber(int bonusNumber){
        return new BonusNumber(bonusNumber);
    }

    public boolean match(Lotto lotto) {
        return lotto.getLotto().contains(bonusNumber);
    }

    private void checkRange(int bonusNumber) {
        if (bonusNumber < MIN_NUMBER || bonusNumber > MAX_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
    }
}
