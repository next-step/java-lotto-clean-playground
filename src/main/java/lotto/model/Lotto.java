package lotto.model;

import java.util.List;

public class Lotto {

    private final LottoNumbers numbers;

    public Lotto(List<Integer> numbers) {
        this.numbers = new LottoNumbers(numbers);
    }

    public List<Integer> getNumbers() {
        return numbers.getNumbers();
    }

    public MatchCount matchWith(WinningNumbers winningNumbers) {
        return new MatchCount(calculateMatchCount(winningNumbers));
    }

    private int calculateMatchCount(WinningNumbers winningNumbers) {
        return (int) numbers.getNumbers().stream()
            .filter(winningNumbers.getNumbers()::contains)
            .count();
    }
}
