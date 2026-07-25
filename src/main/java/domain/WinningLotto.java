package domain;

import java.util.List;

public class WinningLotto {
    private final Lotto lotto;

    public WinningLotto(Lotto lotto) {
        this.lotto = lotto;
    }

    public static WinningLotto from(List<Integer> numbers) {
        return new WinningLotto(Lotto.from(numbers));
    }

    public int countMatches(Lotto lotto) {
        return (int) this.lotto.getNumbers()
                .stream()
                .filter(winningNumber -> lotto.getNumbers().contains(winningNumber))
                .count();
    }
}
