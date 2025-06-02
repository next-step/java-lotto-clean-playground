package domain;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class LottoNumber {
    private static final int REQUIRED_COUNT = 6;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    private final List<Integer> numbers;

    public LottoNumber(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
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

    public Rank match(WinningNumbers winningNumbers) {
        LottoNumber winning = winningNumbers.getWinningNumbers();
        int matchCount = (int) numbers.stream()
            .filter(winning.getNumbers()::contains)
            .count();
        boolean isBonusMatched = numbers.contains(winningNumbers.getBonusNumber().getNumber());

        return Rank.from(matchCount, isBonusMatched);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof LottoNumber that)) {
            return false;
        }
        return Objects.equals(numbers, that.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(numbers);
    }
}
