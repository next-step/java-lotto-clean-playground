package lotto.domain.model;

import java.util.List;

public class WinningLotto {

    private final Lotto winningLotto;


    public WinningLotto(List<Integer> numbers) {
        this.winningLotto = Lotto.from(numbers);
    }

    public Lotto getWinningLotto() {
        return winningLotto;
    }
}
