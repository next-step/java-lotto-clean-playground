package lotto.model;

import java.util.List;

public class LottoNumbers {

    private static final int LOTTO_SIZE = 6;
    private final List<Integer> numbers;

    public LottoNumbers(List<Integer> numbers) {
        validate(numbers);
        this.numbers = List.copyOf(numbers);
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
    }

    public MatchCount match(WinningNumbers winningNumbers) {
        long count = numbers.stream()
            .filter(winningNumbers.getNumbers()::contains)
            .count();
        boolean isBonus = numbers.contains(winningNumbers.getBonusBall());
        return new MatchCount((int) count, isBonus);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
