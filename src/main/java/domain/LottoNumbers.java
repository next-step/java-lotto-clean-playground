package domain;

import java.util.HashSet;
import java.util.List;

public class LottoNumbers {
    private static final int REQUIRED_COUNT = 6;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    private final List<Integer> numbers;

    public LottoNumbers(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public Rank countMatch(LottoNumbers winningNumbers) {
        long matchCount = numbers.stream()
            .filter(winningNumbers.numbers::contains)
            .count();

        return Rank.from((int) matchCount);
    }

    private void validate(List<Integer> numbers) {
        validateSize(numbers);
        validateRange(numbers);
        validateDuplicateNumber(numbers);
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != REQUIRED_COUNT) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
    }

    private void validateRange(List<Integer> numbers) {
        boolean hasOutOfRange = numbers.stream()
            .anyMatch(number -> number < MIN_NUMBER || number > MAX_NUMBER);

        if (hasOutOfRange) {
            throw new IllegalArgumentException("로또 번호는 1~45 사이여야 합니다.");
        }
    }

    private void validateDuplicateNumber(List<Integer> numbers) {
        if (hasDuplicate(numbers)) {
            throw new IllegalArgumentException("중복된 번호는 입력할 수 없습니다.");
        }
    }

    private boolean hasDuplicate(List<Integer> numbers) {
        return numbers.size() != new HashSet<>(numbers).size();
    }

    public List<Integer> getNumbers() {
        return List.copyOf(numbers);
    }
}
