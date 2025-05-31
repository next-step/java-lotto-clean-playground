package lotto.model;

import java.util.List;

public class WinningNumbers {

    private final LottoNumbers numbers;

    public WinningNumbers(List<Integer> numbers) {
        this.numbers = new LottoNumbers(numbers);
    }

    public List<Integer> getNumbers() {
        return numbers.getNumbers();
    }
}
