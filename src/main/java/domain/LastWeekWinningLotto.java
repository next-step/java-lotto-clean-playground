package domain;

import java.util.List;

public class LastWeekWinningLotto extends Lotto {

    private final int bonusNumber;

    public LastWeekWinningLotto(final List<Integer> lotto, final int bonusNumber) {
        super(lotto);
        this.bonusNumber = bonusNumber;
    }
}
