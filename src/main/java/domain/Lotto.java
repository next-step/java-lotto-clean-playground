package domain;

import java.util.List;
import java.util.Set;

public class Lotto {

    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    private static final int LOTTO_SIZE = 6;

    private final Set<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = Set.copyOf(numbers);
    }


    private void validate(List<Integer> numbers) {
        validateSize(numbers);
        validateDuplicates(numbers);
        validateRange(numbers);
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers == null || numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    private void validateDuplicates(List<Integer> numbers) {
        long uniqueCount = numbers.stream().distinct().count();
        if (uniqueCount != LOTTO_SIZE) {
            throw new IllegalArgumentException("[ERROR] 로또 번호에 중복된 숫자가 있습니다.");
        }
    }

    private void validateRange(List<Integer> numbers) {
        for (int number : numbers) {
            if (number < MIN_NUMBER || number > MAX_NUMBER) {
                throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이여야 합니다. 잘못된 값: " + number);
            }
        }
    }

    public int countMatches(Lotto winningLotto) {
        return (int) numbers.stream()
                .filter(winningLotto.numbers::contains)
                .count();
    }

    public List<Integer> getSortedNumbers() {
        return numbers.stream().sorted().toList();
    }

}
