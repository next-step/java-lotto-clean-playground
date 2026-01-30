package domain;

import java.util.List;

public class WinningNumbers {
    private final LottoNumbers numbers;

    private WinningNumbers(List<Integer> numbers) {
        this.numbers = new LottoNumbers(numbers);
    }

    public static WinningNumbers of(List<Integer> numbers) {
        return new WinningNumbers(numbers);
    }

    public boolean contains(int n) {
        return numbers.contains(n);
    }
}
