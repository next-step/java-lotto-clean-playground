package model;

import java.util.List;

public class WinningLotto {
    private final Lotto winningLotto;
    private final int bonusBall;

    public WinningLotto(List<Integer> winningNumbers, int bonusBall) {
        this.winningLotto = new Lotto(winningNumbers);
        this.bonusBall = bonusBall;
    }

    public List<Integer> getWinningNumbers() {
        return winningLotto.getNumbers();
    }

    public int getBonusBall() {
        return bonusBall;
    }

    public Lotto getWinningLotto() {
        return winningLotto;
    }
}
