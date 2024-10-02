package domain;

import java.util.List;

public class WinningLotto extends Lotto {

    private final int bonusNumber;

    public WinningLotto(final List<Integer> lotto, final int bonusNumber) {
        super(lotto);
        this.bonusNumber = bonusNumber;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
