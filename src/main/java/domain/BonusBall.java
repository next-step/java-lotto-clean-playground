package domain;

import util.Errors;

public class BonusBall extends LottoNumber {

    private BonusBall(int value) {
        super(value);
    }

    public static BonusBall createIfNotInList(int value, Lotto lotto) {
        if (validateValueInLotto(value, lotto)) {
            throw new IllegalArgumentException(Errors.BONUS_NUMBER_IS_IN_LOTTO_NUMBER);
        }
        return new BonusBall(value);
    }

    private static boolean validateValueInLotto(int value, Lotto lotto) {
        return lotto.isContains(value);
    }
}
