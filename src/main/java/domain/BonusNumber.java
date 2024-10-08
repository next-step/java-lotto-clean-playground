package domain;


import utils.LottoNumberGenerator;

import static utils.LottoNumberGenerator.MAX_NUMBER;
import static utils.LottoNumberGenerator.MIN_NUMBER;

public class BonusNumber {
    private final int bonusNumber;

    public BonusNumber(int bonusNumber) {
        this.bonusNumber = bonusNumber;
        checkRange();
    }

    public boolean match(Lotto lotto) {
        return lotto.getLotto().contains(bonusNumber);
    }

    private void checkRange() {
        if (bonusNumber < MIN_NUMBER || bonusNumber > MAX_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
    }
}
