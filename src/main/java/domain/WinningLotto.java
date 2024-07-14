package domain;

import java.util.List;
import util.Errors;

public class WinningLotto {

    Lotto lotto;
    BonusBall bonusBall;

    public WinningLotto(Lotto lotto, BonusBall bonusBall) {
        this.lotto = lotto;
        isBonusNumberNotInLottoNumber(lotto, bonusBall);
        this.bonusBall = bonusBall;
    }

    private void isBonusNumberNotInLottoNumber(Lotto lotto, BonusBall bonusBall) {
        if (lotto.isContains(bonusBall.number())) {
            throw new IllegalArgumentException(Errors.BONUS_NUMBER_IS_IN_LOTTO_NUMBER);
        }
    }

    public List<Integer> getLottoNumber() {
        return lotto.numbers();
    }
}
