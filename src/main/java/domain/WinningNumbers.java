package domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WinningNumbers {
    private static final int REQUIRED_SIZE = 6;
    private static final String ERROR_INVALID_SIZE = "[ERROR] 당첨 번호는 6개여야 합니다.";
    private static final String ERROR_DUPLICATED = "[ERROR] 당첨 번호는 중복될 수 없습니다.";

    private final List<WinningNumber> numbers;

    public WinningNumbers(List<WinningNumber> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public List<WinningNumber> getNumbers() {
        return numbers;
    }

    public int countMatch(Lotto lotto) {
        Set<Integer> winningValues = new HashSet<>();
        for (WinningNumber number : numbers) {
            winningValues.add(number.value());
        }

        long count = lotto.getNumbers().stream()
                .map(LottoNumber::value)
                .filter(winningValues::contains)
                .count();

        return (int) count;
    }

    private void validate(List<WinningNumber> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);
    }

    private void validateSize(List<WinningNumber> numbers) {
        if (numbers.size() != REQUIRED_SIZE) {
            throw new IllegalArgumentException(ERROR_INVALID_SIZE);
        }
    }

    private void validateDuplicate(List<WinningNumber> numbers) {
        Set<WinningNumber> unique = new HashSet<>(numbers);
        if (unique.size() != REQUIRED_SIZE) {
            throw new IllegalArgumentException(ERROR_DUPLICATED);
        }
    }
}
