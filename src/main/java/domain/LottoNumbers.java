package domain;

import java.util.List;

public class LottoNumbers {
    private static final int REQIRED_COUNT = 6;
    private final List<Integer> numbers;

    public LottoNumbers(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public int countMatch(LottoNumbers winningNumbers) {
        return (int) numbers.stream()
            .filter(winningNumbers.numbers::contains)
            .count();
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != REQIRED_COUNT) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
