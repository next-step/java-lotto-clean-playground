package domain;

import java.util.List;

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
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
    }

}
