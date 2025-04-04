package model;

import model.lotto.Lotto;

public class BonusBall {

    private final int bonusNumber;

    private BonusBall(int bonusNumber) {
        this.bonusNumber = bonusNumber;
    }

    public static BonusBall of(int bonusNumber, Lotto winningNumber) {
        if (winningNumber.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복되면 안 됩니다!");
        }
        return new BonusBall(bonusNumber);
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
