package domain;

import java.util.List;

public class WinningLotto {

    private final Lotto lotto;
    private final int bonusNumber;

    public WinningLotto(final List<Integer> lotto, final int bonusNumber) {
        this.lotto = new Lotto(lotto);
        this.bonusNumber = bonusNumber;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }

    public Lotto getLotto() {
        return lotto;
    }
}
