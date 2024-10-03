package domain;

import java.util.ArrayList;
import java.util.List;

public class Lotto {
    private final List<Integer> lotto;

    public Lotto(List<Integer> lotto) {
        this.lotto = new ArrayList<>(lotto);
    }

    public List<Integer> getLotto() {
        return lotto;
    }

    public int matchingNumbers(List<Integer> winningNumbers) {
        return (int) lotto.stream()
                .filter(winningNumbers::contains)
                .count();
    }

}
