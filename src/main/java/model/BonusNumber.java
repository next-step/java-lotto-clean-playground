package model;

public class BonusNumber {
    public final int bonusNumber;

    public BonusNumber(int bonusNumber) {
        validateBonusNumber(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumber(int bonusNumber) {
        if (bonusNumber < LottoConstants.MIN_NUMBER.getValue() || bonusNumber > LottoConstants.MAX_NUMBER.getValue()) {
            throw new Error("보너스 넘버는 " + LottoConstants.MIN_NUMBER.getValue()
                    + " 이상 " + LottoConstants.MAX_NUMBER.getValue() + " 이하여야 합니다.");
        }
    }

    public int getValue() {
        return bonusNumber;
    }
}
